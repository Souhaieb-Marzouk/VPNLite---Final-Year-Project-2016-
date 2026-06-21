#!/bin/sh

# hostname -I | awk '{ print $1}'

GWY=$1
INTER=$2

route add default gw $GWY dev $INTER