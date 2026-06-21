#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

apt-get install -yq strongswan
cd /etc/
apt-get install -yq libgmp10
apt-get install -yq libgmp-dev
apt-get install -yq libssl-dev
cd /usr/src/
wget http://download.strongswan.org/strongswan-5.5.2.tar.gz
tar -xvzf strongswan-5.5.2.tar.gz
cd strongswan-5.5.2
./configure --prefix=/usr/local --enable-openssl --enable-farp
make
make install
