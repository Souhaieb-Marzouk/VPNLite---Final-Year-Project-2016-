#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

apt-get remove -yq pppoe
apt-get purge -yq ppp ppp-dev pppoeconf
cd /tmp/
rm -r rp-pppoe-3.12