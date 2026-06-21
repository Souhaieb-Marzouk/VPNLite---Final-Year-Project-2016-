#!/bin/bash
 
# -q quiet
# -c nb of pings to perform

NUM=$1
TYPE=$2

#ADOWN=$(cat /tmp/Curlfile$NUM.txt | tail -n 1)
#DM=($ADOWN)
#echo ${DM[N-6]}

if [ $TYPE = "DOWN" ]
then
    ADOWN=$(cat /tmp/Curlfile$NUM.txt | tail -n 1)
    DM=($ADOWN)
    echo ${DM[N-6]}
elif [ $TYPE = "UP" ]
then
    ADOWN=$(cat /tmp/Curlfile$NUM.txt | tail -n 1)
    DM=($ADOWN)
    echo ${DM[N-5]}
elif [ $TYPE = "SIZE" ]
then
    ADOWN=$(cat /tmp/Curlfile$NUM.txt | tail -n 1)
    DM=($ADOWN)
    echo ${DM[N-11]}
elif [ $TYPE = "DOWNGRAPH" ]
then
    ADOWN=$(cat /tmp/Curlfile$NUM.txt | tail -n 1)
    DM=($ADOWN)
    echo ${DM[N-6] | x%?}
elif [ $TYPE = "UPGRAPH" ]
then
    ADOWN=$(cat /tmp/Curlfile$NUM.txt | tail -n 1)
    DM=($ADOWN)
    echo ${DM[N-5] | x%?}
fi