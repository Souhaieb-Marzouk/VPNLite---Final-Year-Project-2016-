#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

#ADDR=$(ifconfig eth0 | grep 'inet adr:' | cut -d: -f2 | awk '{ print $1}' | sed 's/\.[0-9]*$/\.'$1'/')
ADDR=$(ip -4 route get 1 | awk '{print $NF;exit}' | sed 's/\.[0-9]*$/\.'$1'/')
echo $ADDR
