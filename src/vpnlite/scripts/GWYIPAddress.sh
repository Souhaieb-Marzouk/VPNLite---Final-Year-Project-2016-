#!/bin/sh

# hostname -I | awk '{ print $1}'

GWY_IP=$(ip route list | awk '{print $3;exit}')

echo $GWY_IP