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
RIGHT_ADDR=$2
VPN_USER=$3
VPN_PASS=$4
GWY=$5
INTER=$6

iptables -F
iptables -X
iptables -t nat -F
iptables -t nat -X
iptables -t mangle -F
iptables -t mangle -X
ipsec stop

cat > /etc/ipsec.conf <<EOF
config setup

conn %default
	ikelifetime=360m
	keylife=20m
	rekeymargin=3m
	keyingtries=5
	keyexchange=ikev1
	authby=secret
	ike=aes128-sha1-modp1024,aes128-sha1-modp1536,aes128-sha1-modp2048,aes128-sha256-ecp256,aes128-sha256-modp1024,aes128-sha256-modp1536,aes128-sha256-modp2048,aes256-aes128-sha256-sha1-modp2048-modp4096-modp1024,aes256-sha1-modp1024,aes256-sha256-modp1024,aes256-sha256-modp1536,aes256-sha256-modp2048,aes256-sha256-modp4096,aes256-sha384-ecp384,aes256-sha384-modp1024,aes256-sha384-modp1536,aes256-sha384-modp2048,aes256-sha384-modp4096,aes256gcm16-aes256gcm12-aes128gcm16-aes128gcm12-sha256-sha1-modp2048-modp4096-modp1024,3des-sha1-modp1024!
	esp=aes128-aes256-sha1-sha256-modp2048-modp4096-modp1024,aes128-sha1,aes128-sha1-modp1024,aes128-sha1-modp1536,aes128-sha1-modp2048,aes128-sha256,aes128-sha256-ecp256,aes128-sha256-modp1024,aes128-sha256-modp1536,aes128-sha256-modp2048,aes128gcm12-aes128gcm16-aes256gcm12-aes256gcm16-modp2048-modp4096-modp1024,aes128gcm16,aes128gcm16-ecp256,aes256-sha1,aes256-sha256,aes256-sha256-modp1024,aes256-sha256-modp1536,aes256-sha256-modp2048,aes256-sha256-modp4096,aes256-sha384,aes256-sha384-ecp384,aes256-sha384-modp1024,aes256-sha384-modp1536,aes256-sha384-modp2048,aes256-sha384-modp4096,aes256gcm16,aes256gcm16-ecp384,3des-sha1!
#	ike=aes128-md5-modp1536!
#	esp=aes128-sha1!

conn myvpn
	keyexchange=ikev1
	left=$LEFT_ADDR
	auto=add
	authby=secret
	type=transport
	leftprotoport=17/1701
	rightprotoport=17/1701
	right=$RIGHT_ADDR
EOF

cat > /etc/ipsec.secrets <<EOF
%any %any : PSK IPSECPSK
EOF

chmod 600 /etc/ipsec.secrets

cat > /etc/xl2tpd/xl2tpd.conf <<EOF
[lac myvpn]
lns = $RIGHT_ADDR
ppp debug = yes
pppoptfile = /etc/ppp/options.l2tpd.client
length bit = yes
EOF

cat > /etc/ppp/options.l2tpd.client <<EOF
ipcp-accept-local
ipcp-accept-remote
refuse-eap
require-chap
noccp
noauth
mtu 1280
mru 1280
noipdefault
defaultroute
usepeerdns
connect-delay 5000
name $VPN_USER
password $VPN_PASS
EOF

chmod 600 /etc/ppp/options.l2tpd.client
mkdir -p /var/run/xl2tpd
touch /var/run/xl2tpd/l2tp-control
service strongswan restart
service xl2tpd restart
ipsec up myvpn
echo "c myvpn" > /var/run/xl2tpd/l2tp-control
sleep 5
route add -net 192.168.3.0 netmask 255.255.255.0 dev ppp0
route add -net 100.100.100.0 netmask 255.255.255.0 dev ppp0
route add default gw 192.168.1.1 dev eth0