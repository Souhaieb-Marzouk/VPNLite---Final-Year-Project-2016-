#!/bin/bash
 
# -q quiet
# -c nb of pings to perform
count=$1                            # Maximum number to try.
while [ $count -ne 0 ]
do
    ping -c $1 $2 | tee /tmp/VPNResults/ResultPing.txt
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
