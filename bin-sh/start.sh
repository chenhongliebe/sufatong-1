#!/bin/sh

rm -f tpid

nohup java -jar sufatong.jar --spring.profiles.active=dev > /dev/null 2>&1 &

echo $! > tpid

echo Start Success!