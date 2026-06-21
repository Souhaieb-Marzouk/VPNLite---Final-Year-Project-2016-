#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

apt-get remove -yq pppoe
apt-get install -yq ppp ppp-dev pppoeconf
cd /tmp/
wget https://www.roaringpenguin.com/files/download/rp-pppoe-3.12.tar.gz
tar xvf rp-pppoe-3.12.tar.gz
cd rp-pppoe-3.12/src/
./configure
make
make install