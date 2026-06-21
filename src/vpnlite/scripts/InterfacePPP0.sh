#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

#INTERFACE_NAME=$(ls /sys/class/net | awk '{ print $1; exit}')

INTERFACE_NAME=$(ifconfig ppp0 | awk '{print $1;exit}')
#INTERFACE_NAME=$(ip -4 route get 1 | awk '{print $5;exit}')
echo $INTERFACE_NAME
