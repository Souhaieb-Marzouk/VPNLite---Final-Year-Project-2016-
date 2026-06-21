#!/bin/bash
 
# -q quiet
# -c nb of pings to perform

HOST=$1
USER=$2
PASSWD=$3
CMD=$4
count=$5
ss=$5
TYPE=$6

if [ $TYPE = "GET" ]
then
    while [ $count -ne 0 ]
    do
        curl ftp://$HOST/$CMD --user $USER:$PASSWD -o $CMD 2> /tmp/Curlfile$count.txt
        ADOWN=$(cat /tmp/Curlfile$count.txt | tail -n 1)
        DM=($ADOWN)
        echo "Download Average for Test $count : ${DM[N-6]}"
        ((count = count - 1))
    done
elif [ $TYPE = "PUT" ]
then
    while [ $count -ne 0 ]
    do
        curl -T $CMD --user $USER:$PASSWD ftp://$HOST/  2> /tmp/Curlfile$count.txt
        ADOWN=$(cat /tmp/Curlfile$count.txt | tail -n 1)
        DM=($ADOWN)
        echo "Upload Average for Test $count : ${DM[N-5]}"
        ((count = count - 1))
    done
fi
#for i in $(seq 1 $ss);
#do
#    sed -n '1 p' < /tmp/Curlfile$i.txt
#    sed -n '2 p' < /tmp/Curlfile$i.txt
#    sed '$!d' /tmp/Curlfile$i.txt
#done

#if [ $count -eq 0 ] ; then
#    echo "Test PASSED"
#else
#    echo "Test FAILED"
#fi