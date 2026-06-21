#!/bin/bash
 
# -q quiet
# -c nb of pings to perform

INTERFACE=$1
FILTER1=$2
FILTER2=$3
FILTER3=$4
FILTER4=$5
FILTER5=$6

if [[ -n "$FILTER5" ]]
	then
		wireshark -i $INTERFACE -k -Y "$FILTER1 || $FILTER2 || $FILTER3 || $FILTER4 || $FILTER5"
elif [[ -n "$FILTER4" ]]
	then
		wireshark -i $INTERFACE -k -Y "$FILTER1 || $FILTER2 || $FILTER3 || $FILTER4"
elif [[ -n "$FILTER3" ]]
	then
		wireshark -i $INTERFACE -k -Y "$FILTER1 || $FILTER2 || $FILTER3"
elif [[ -n "$FILTER2" ]]
	then
		wireshark -i $INTERFACE -k -Y "$FILTER1 || $FILTER2"
else
	wireshark -i $INTERFACE -k -Y "$FILTER1"
fi