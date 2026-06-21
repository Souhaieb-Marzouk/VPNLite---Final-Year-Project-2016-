#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

HOST=$1
USER=$2
PASSWD=$3

ftp -nv $HOST > /tmp/FTPList.txt <<END_SCRIPT
quote USER $USER
quote PASS $PASSWD
#hash
#bin
ls
bye
END_SCRIPT

FLIST=$(cat /tmp/FTPList.txt | grep - | awk '{print $NF}')
echo $FLIST