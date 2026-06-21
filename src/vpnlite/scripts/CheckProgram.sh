#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

installed="$(dpkg --get-selections | grep -m 1 $1 | awk '{print $2}')"

if [ $installed = "install" ]
then
    echo $1 "is installed"
else
    echo "nothing"
fi