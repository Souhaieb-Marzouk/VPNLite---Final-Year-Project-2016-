#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

RECEIVED=$(cat $1 | grep 'received' | awk -F',' '{ print $2}' | awk '{print $1}')
LOSS=$(cat $1 | grep 'received' | awk -F',' '{print $3}' | awk '{print $1}')
echo "PacketReceived =" $RECEIVED
echo "PacketLoss =" $LOSS