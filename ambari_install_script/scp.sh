#!/bin/bash

PWD=$(cd `dirname $0` ; pwd)

if [[ $# != 3 ]]
then
        echo "Usage: $PWD/$0 [pem_key] [source_path] [destination_path]"
        exit 1
fi

user_name=$(/usr/bin/whoami)
pem_key=$1
source_path=$2
destination_path=$3

for slave in `egrep -v "^#" $PWD/serverList`
do
        echo "rcp to $slave ..."
        scp -i ${pem_key} -r ${source_path} ${user_name}@${slave}:${destination_path} &
done
