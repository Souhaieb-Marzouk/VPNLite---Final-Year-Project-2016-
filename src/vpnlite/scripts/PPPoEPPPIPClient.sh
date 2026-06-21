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
# https://linux2me.wordpress.com/2016/05/20/pppoe-server-setup-in-ubuntu/

INTERFACE=$1
USERNAME=$2
PASS=$3

iptables -F
iptables -X
iptables -t nat -F
iptables -t nat -X
iptables -t mangle -F
iptables -t mangle -X

cat > /etc/ppp/peers/dsl-provider <<EOF
# Minimalistic default options file for DSL/PPPoE connections

noipdefault
defaultroute
replacedefaultroute
hide-password
#lcp-echo-interval 30
#lcp-echo-failure 4
noauth
persist
#mtu 1492
#persist
#maxfail 0
#holdoff 20
plugin rp-pppoe.so eth0
user "$USERNAME"
usepeerdns
EOF

cat >> /etc/ppp/chap-secrets <<EOF
"$USERNAME" * "$PASS"
EOF
cat >> /etc/ppp/pap-secrets <<EOF
"$USERNAME" * "$PASS"
EOF

pon dsl-provider
sleep 1
INTER=$(ip -4 route get 1 | awk '{print $NF;exit}')
#INTERFACE_NAME=$(ip -4 route get 1 | awk '{print $5;exit}')
echo $INTER