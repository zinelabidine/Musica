#!/bin/bash

echo $1

SERVER=~/wildfly-8.2.1.Final

cp $1 $SERVER/standalone/deployments/

cd $SERVER

rm -r standalone/deployments/*

bin/standalone.sh
