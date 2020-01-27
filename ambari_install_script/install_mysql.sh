#!/bin/bash

sudo yum localinstall -y https://dev.mysql.com/get/mysql57-community-release-el7-8.noarch.rpm
sudo yum install -y mysql-community-server
sudo systemctl start mysqld.service
sudo grep 'A temporary password is generated for root@localhost' /var/log/mysqld.log |tail -1 >> mysql.passwd
