#!/bin/bash

PWD=$(cd `dirname $0` ; pwd)
pem_key=~/kimhyunwoo_aws.pem
 
function execAndSleep() {
        ./run_all.sh $@
        echo "sleep 2 sec"
        sleep 2
}
 
function step1_setting_ssh() {
        ssh-keygen -t rsa -f ~/.ssh/id_rsa -P ''
        mkdir -p ~/temp/.ssh
        cat ~/.ssh/id_rsa.pub > ~/temp/.ssh/authorized_keys
 
        ./scp.sh $pem_key  ~/temp/.ssh ~/.ssh
        cp ~/temp/.ssh/authorized_keys ~/.ssh/
        rm -rf ~/.ssh/.ssh
}
 
function step2_say_yes_ssh() {
        sudo yum install -y expect
        for server in `cat serverList`
        do
          ./ssh.exp $server
        done
}
 
function step3_basic_setting() {
        execAndSleep sudo chmod 700 ~/.ssh
        execAndSleep sudo chmod 600 ~/.ssh/authorized_keys
        execAndSleep sudo yum install -y ntp
        execAndSleep sudo systemctl enable ntpd
        execAndSleep sudo systemctl disable firewalld
        execAndSleep sudo /usr/sbin/service firewalld stop
        execAndSleep sudo /usr/sbin/setenforce 0
        execAndSleep sudo chattr -i /etc/login.defs
        execAndSleep sudo chmod 644 /etc/login.defs
        execAndSleep sudo chattr +i /etc/login.defs
}
 
# /etc/hosts
function step4_set_hosts() {
        mkdir -p ~/temp
        cp /etc/hosts ~/temp/hosts
        cp /etc/hosts ~/temp/hosts.backup
 
        for slave in `egrep -v "^#" $PWD/serverList`
        do
                re1=`ssh $slave hostname -i`
                re2=`ssh $slave hostname -f`
                re3=`ssh $slave hostname`
                echo $re1 $re2 $re3 >> ~/temp/hosts
        done
 
        head -2 ~/temp/hosts > ~/temp/h
        sed -n '3,$ p' ~/temp//hosts | sort | uniq >> ~/temp/h
        mv ~/temp/h ~/temp/hosts
        ./scp.sh $pem_key ~/temp/hosts ~/hosts
        ./run_all.sh sudo mv -f ~/hosts /etc/hosts
}
 
function step5_install_mysqlcli() {
     ./run_all.sh sudo yum install -y mysql-connector-java*
}
 
step1_setting_ssh
step2_say_yes_ssh
step3_basic_setting
step4_set_hosts
step5_install_mysqlcli
