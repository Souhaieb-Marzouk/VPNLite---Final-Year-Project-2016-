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

SERVER_ADDR=$1
USERNAME=$2
PASSWORD=$3

iptables -F
iptables -X
iptables -t nat -F
iptables -t nat -X
iptables -t mangle -F
iptables -t mangle -X

modprobe ppp_generic
touch /etc/ppp/peers/pptpserver

cat > /etc/ppp/peers/pptpserver <<EOF
pty "pptp $SERVER_ADDR --nolaunchpppd"
lock
noauth
nobsdcomp
nodeflate
name $USERNAME
password $PASSWORD
remotename PPTP
ipparam pptpserver
require-mppe-128
file /etc/ppp/options.pptp
defaultroute
persist
EOF

cat >> /etc/ppp/chap-secrets <<EOF
$USERNAME pptpserver $PASSWORD *
EOF

touch /etc/ppp/ip-up.local

cat > /etc/ppp/ip-up.local <<EOF
#!/bin/bash
H=`ps aux | grep 'pppd pty' | grep -v grep | awk '{print $14}'`
DG=`route -n | grep UG | awk '{print $2}'`
DEV=`route -n | grep UG | awk '{print $8}'`
route add -host $H gw $DG dev $DEV
route del default $DEV
route add default dev ppp0
EOF

chmod +x /etc/ppp/ip-up.local
service pptpd restart
pon pptpserver