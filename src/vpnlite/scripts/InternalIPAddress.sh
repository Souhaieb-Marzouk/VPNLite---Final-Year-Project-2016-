#!/bin/sh

# hostname -I | awk '{ print $1}'

PRIVATE_IP=$(ip -4 route get 1 | awk '{print $NF;exit}')

echo $PRIVATE_IP