#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

RPATH=$1
COUNT=$2

cat /tmp/VPNResults/$RPATH | egrep 'sec' | awk '{print $7}' | sed -n $COUNT