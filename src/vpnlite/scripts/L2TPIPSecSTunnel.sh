#!/bin/sh
#
# Script to configure and start a L2TP/IPsec VPN server
#
# THIS SCRIPT IS ONLY MEANT TO BE RUN IN A UBUNTU PC WITH
# STRONGSWAN 5.5.2
#
# Copyright (C) 2016-2018 Lin Song <marzouk.souhaieb@gmail.com>
#
# This work is licensed by Sagemcom
#
# Attribution required: please include my name in any derivative and let me
# know how you have improved it!

LEFT_ADDR=$1
LEFT_SUBNET=$2
RIGHT_ADDR=$3
RIGHT_SUBNET=$4
KEY_EX=$5

iptables -F
iptables -X
iptables -t nat -F
iptables -t nat -X
iptables -t mangle -F
iptables -t mangle -X
service strongswan stop
service xl2tpd stop
ipsec stop

cat > /etc/ipsec.conf <<EOF
version 2.0

config setup
	virtual_private=%v4:10.0.0.0/8,%v4:192.168.0.0/16,%v4:172.16.0.0/12,%v4:!192.168.42.0/24
	interfaces=$LEFT_ADDR
	uniqueids=yes
	
conn shared
	left=$LEFT_ADDR
	leftsubnet=$LEFT_SUBNET
	leftid=$LEFT_ADDR
	right=%any
	rightsubnet=$RIGHT_SUBNET
	forceencaps=yes
	authby=secret
	pfs=no
	rekey=yes
	keyingtries=5
	dpddelay=30
	dpdtimeout=60
	dpdaction=restart
	ike=aes128-sha1-modp1024,aes128-sha1-modp1536,aes128-sha1-modp2048,aes128-sha256-ecp256,aes128-sha256-modp1024,aes128-sha256-modp1536,aes128-sha256-modp2048,aes256-aes128-sha256-sha1-modp2048-modp4096-modp1024,aes256-sha1-modp1024,aes256-sha256-modp1024,aes256-sha256-modp1536,aes256-sha256-modp2048,aes256-sha256-modp4096,aes256-sha384-ecp384,aes256-sha384-modp1024,aes256-sha384-modp1536,aes256-sha384-modp2048,aes256-sha384-modp4096,aes256gcm16-aes256gcm12-aes128gcm16-aes128gcm12-sha256-sha1-modp2048-modp4096-modp1024,3des-sha1-modp1024!
	esp=aes128-aes256-sha1-sha256-modp2048-modp4096-modp1024,aes128-sha1,aes128-sha1-modp1024,aes128-sha1-modp1536,aes128-sha1-modp2048,aes128-sha256,aes128-sha256-ecp256,aes128-sha256-modp1024,aes128-sha256-modp1536,aes128-sha256-modp2048,aes128gcm12-aes128gcm16-aes256gcm12-aes256gcm16-modp2048-modp4096-modp1024,aes128gcm16,aes128gcm16-ecp256,aes256-sha1,aes256-sha256,aes256-sha256-modp1024,aes256-sha256-modp1536,aes256-sha256-modp2048,aes256-sha256-modp4096,aes256-sha384,aes256-sha384-ecp384,aes256-sha384-modp1024,aes256-sha384-modp1536,aes256-sha384-modp2048,aes256-sha384-modp4096,aes256gcm16,aes256gcm16-ecp384,3des-sha1!
#	ike=aes128-md5-modp1536
#	esp=aes128-sha1

conn l2tp-psk
	auto=start
	leftprotoport=17/1701
	rightprotoport=17/%any
	type=tunnel
	also=shared
EOF

cat > /usr/local/etc/ipsec.conf <<EOF
version 2.0

config setup
	virtual_private=%v4:10.0.0.0/8,%v4:192.168.0.0/16,%v4:172.16.0.0/12,%v4:!192.168.42.0/24
	interfaces=$LEFT_ADDR
	uniqueids=yes
	
conn shared
	left=$LEFT_ADDR
	leftsubnet=$LEFT_SUBNET
	leftid=$LEFT_ADDR
	right=%any
	rightsubnet=$RIGHT_SUBNET
	forceencaps=yes
	authby=secret
	pfs=no
	rekey=yes
	keyingtries=5
	dpddelay=30
	dpdtimeout=60
	dpdaction=restart
	ike=aes128-md5-modp1536
	esp=aes128-sha1

conn l2tp-psk
	auto=start
	leftprotoport=17/1701
	rightprotoport=17/%any
	type=tunnel
	also=shared
EOF

cat > /etc/ipsec.secrets <<EOF
# ipsec.secrets - strongSwan IPsec secrets file
%any %any : PSK IPSECPSK
EOF

cat > /usr/local/etc/ipsec.secrets <<EOF
# ipsec.secrets - strongSwan IPsec secrets file
%any %any : PSK IPSECPSK
EOF

cat > /etc/xl2tpd/xl2tpd.conf <<EOF
[global]
port = 1701

[lns default]
ip range = $L2TP_POOL
local ip = $L2TP_ADDR
require chap = yes
refuse pap = yes
require authentication = yes
name = l2tpd
pppoptfile = /etc/ppp/options.xl2tpd
length bit = yes
EOF

cat > /etc/ppp/options.xl2tpd <<EOF
+mschap-v2
ipcp-accept-local
ipcp-accept-remote
ms-dns 8.8.8.8
ms-dns 8.8.4.4
noccp
auth
mtu 1280
mru 1280
proxyarp
lcp-echo-failure 4
lcp-echo-interval 30
connect-delay 5000
EOF

cat > /etc/ppp/chap-secrets <<EOF
# Secrets for authentication using CHAP
# client    server  secret  IP addresses
admin l2tpd admin *
EOF

SYST='/sbin/sysctl -e -q -w'
if [ "$(getconf LONG_BIT)" = "64" ]; then
  SHM_MAX=68719476736
  SHM_ALL=4294967296
else
  SHM_MAX=4294967295
  SHM_ALL=268435456
fi
$SYST kernel.msgmnb=65536
$SYST kernel.msgmax=65536
$SYST kernel.shmmax=$SHM_MAX
$SYST kernel.shmall=$SHM_ALL
$SYST net.ipv4.ip_forward=1
$SYST net.ipv4.conf.all.accept_source_route=0
$SYST net.ipv4.conf.all.accept_redirects=0
$SYST net.ipv4.conf.all.send_redirects=0
$SYST net.ipv4.conf.all.rp_filter=0
$SYST net.ipv4.conf.default.accept_source_route=0
$SYST net.ipv4.conf.default.accept_redirects=0
$SYST net.ipv4.conf.default.send_redirects=0
$SYST net.ipv4.conf.default.rp_filter=0
$SYST net.ipv4.conf.eth0.send_redirects=0
$SYST net.ipv4.conf.eth0.rp_filter=0

iptables -I INPUT 1 -p udp --dport 1701 -m policy --dir in --pol none -j DROP
iptables -I INPUT 2 -m conntrack --ctstate INVALID -j DROP
iptables -I INPUT 3 -m conntrack --ctstate RELATED,ESTABLISHED -j ACCEPT
iptables -I INPUT 4 -p udp -m multiport --dports 500,4500 -j ACCEPT
iptables -I INPUT 5 -p udp --dport 1701 -m policy --dir in --pol ipsec -j ACCEPT
iptables -I INPUT 6 -p udp --dport 1701 -j DROP
iptables -I FORWARD 1 -m conntrack --ctstate INVALID -j DROP
iptables -I FORWARD 2 -i eth+ -o ppp+ -m conntrack --ctstate RELATED,ESTABLISHED -j ACCEPT
iptables -I FORWARD 3 -i ppp+ -o eth+ -j ACCEPT
iptables -I FORWARD 4 -i ppp+ -o ppp+ -s 192.168.42.0/24 -d 192.168.42.0/24 -j ACCEPT
iptables -A FORWARD -j DROP
iptables -t nat -A POSTROUTING -s 192.168.42.0/24 -o eth+ -j MASQUERADE

chmod 600 /etc/ipsec.secrets /etc/ppp/chap-secrets
service strongswan restart
service xl2tpd restart
modprobe af_key
mkdir -p /var/run/pluto /var/run/xl2tpd
rm -f /var/run/pluto/pluto.pid /var/run/xl2tpd.pid
/usr/local/sbin/ipsec start --conf /usr/local/etc/ipsec.conf
/usr/sbin/xl2tpd -D -c /etc/xl2tpd/xl2tpd.conf
