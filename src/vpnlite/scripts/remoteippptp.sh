#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

ADDR=$(ip -4 route get 1 | awk '{print $NF;exit}' | sed 's/\.[0-9]*$/\.'$1'/')

echo $ADDR
