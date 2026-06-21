#!/bin/bash
 
# -q quiet
# -c nb of pings to perform

IP=$1
TIME=$2
PORT=$3

count=$2        # Maximum number to try.

while [ $count -ne 0 ]
do
    iperf3 -c $IP -i 1 -t $TIME -p $PORT | tee /tmp/VPNResults/IPerf3ClientTCP.txt
    rc=$?
    if [ $rc -eq 0 ] ; then
        count=1                     # If okay, flag to exit loop.
    fi
    ((count = count - 1))           # So we don't go forever.
done
if [ $rc -eq 0 ] ; then             # Make final determination.
    echo "Test PASSED"
else
    echo "Test FAILED"
fi