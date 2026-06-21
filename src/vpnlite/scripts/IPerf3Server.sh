#!/bin/bash
 
# -q quiet
# -c nb of pings to perform

PORT=$1

iperf3 -s -i 1 -p $PORT | tee /tmp/VPNResults/IPerf3Server.txt