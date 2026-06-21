#!/bin/bash
 
# -q quiet
# -c nb of pings to perform

HOST=$1
USER=$2
PASSWD=$3
CMD=$4
count=$5
#wget -v --user=$USER --password=$PASSWD --report-speed=bits ftp://$HOST/$CMD 2> /tmp/FTPResult.txt
#cat /tmp/FTPResult.txt

#curl ftp://$HOST/$CMD --user $USER:$PASSWD -o $CMD

while [ $count -ne 0 ]
do
#    wget -v --user=$USER --password=$PASSWD --report-speed=bits ftp://$HOST/$CMD 2> file$count.txt
    curl ftp://$HOST/$CMD --user $USER:$PASSWD -o $CMD 2> file$count.txt
    sed -n '1 p' < file$count.txt
    sed -n '2 p' < file$count.txt
#    sed -n '3 p' < file$count.txt
#    sed -n '4 p' < file$count.txt
#    sed -n '5 p' < file$count.txt
#    sed -n '6 p' < file$count.txt
#    sed -n '7 p' < file$count.txt
#    sed -n '8 p' < file$count.txt
#    sed -n '9 p' < file$count.txt
#    tail -n 3 file$count.txt
    sed '$!d' file$count.txt
    ((count = count - 1))
#    rc=$?
done

if [ $count -eq 0 ] ; then
    echo "Test PASSED"
else
    echo "Test FAILED"
fi