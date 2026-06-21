#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

apt-get purge -yq strongswan libstrongswan
rm -r /usr/src/strongswan-5.5.2.tar.gz
rm -r /usr/src/strongswan-5.5.2
rm -r /usr/local/etc/ipsec.conf
rm -r /usr/local/etc/ipsec.conf~
rm -r /usr/local/etc/ipsec.d
rm -r /usr/local/etc/ipsec.secrets
rm -r /usr/local/etc/ipsec.secrets~
rm -r /usr/local/etc/strongswan.conf
rm -r /usr/local/etc/strongswan.d
iptables -F
iptables -X
