#!/bin/sh
 
# -q quiet
# -c nb of pings to perform

ps -ef | grep $1 | grep -v "grep" | awk '{print $2}' | xargs kill -INT;