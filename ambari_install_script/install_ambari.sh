#!/bin/bash

PWD=$(cd `dirname $0` ; pwd)
 
function execAndSleep() {
        ./run_all.sh $@
        echo "sleep 2 sec"
        sleep 2
}
 
function step1_init_mysql_server() {
        PASSWORD=admin1234
        MYSQLCMD="mysql -u root -p$PASSWORD"
        $MYSQLCMD -e "CREATE USER 'root'@'%' IDENTIFIED BY 'admin1234'; GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'; GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION; FLUSH PRIVILEGES;"
 
        ECO_SERVICES="registry streamline druid superset ambari oozie hive"
        for ECO_SERVICE in $ECO_SERVICES
        do
            $MYSQLCMD -e "create database $ECO_SERVICE; CREATE USER '$ECO_SERVICE'@'localhost' IDENTIFIED BY '$PASSWORD'; GRANT ALL PRIVILEGES ON *.* TO '$ECO_SERVICE'@'localhost'; CREATE USER '$ECO_SERVICE'@'%' IDENTIFIED BY '$PASSWORD'; GRANT ALL PRIVILEGES ON *.* TO '$ECO_SERVICE'@'%'; GRANT ALL PRIVILEGES ON *.* TO '$ECO_SERVICE'@'localhost' WITH GRANT OPTION; GRANT ALL PRIVILEGES ON *.* TO '$ECO_SERVICE'@'%' WITH GRANT OPTION; FLUSH PRIVILEGES;"
        done
        $MYSQLCMD -e "show databases"
}
 
function step2_install_ambari() {
        sudo yum install -y wget
        sudo wget -nv http://public-repo-1.hortonworks.com/ambari/centos7/2.x/updates/2.7.3.0/ambari.repo -O /etc/yum.repos.d/ambari.repo
        sudo yum install -y ambari-server
        sudo /usr/sbin/ambari-server setup
 
}
function step3_init_ambari_db() {
        mysql -uambari -padmin1234 ambari < /var/lib/ambari-server/resources/Ambari-DDL-MySQL-CREATE.sql
        sudo /usr/sbin/ambari-server setup --jdbc-db=mysql --jdbc-driver=/usr/share/java/mysql-connector-java.jar
}
 
function step4_start_ambari() {
        sudo /usr/sbin/ambari-server start
}
 
 
step1_init_mysql_server
step2_install_ambari
step3_init_ambari_db
step4_start_ambari
