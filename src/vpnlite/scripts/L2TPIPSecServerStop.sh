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

ipsec stop
service strongswan stop
service xl2tpd stop
