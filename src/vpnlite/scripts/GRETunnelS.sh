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

iptables -F
iptables -X
iptables -t nat -F
iptables -t nat -X
iptables -t mangle -F
iptables -t mangle -X
modprobe ip_gre
ip tunnel add neta mode gre remote $2 local $3  ttl 255
ip link set neta up
ip addr add $1/24 dev neta
ip route add $4 dev neta
iptables -A FORWARD -i neta -j ACCEPT
iptables -A FORWARD -o neta -j ACCEPT
