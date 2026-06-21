#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

apt-get purge -yq --auto-remove cacti
apt-get purge -yq --auto-remove snmpd
apt-get purge -yq --auto-remove libsnmp-base
