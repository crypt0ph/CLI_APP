#!/bin/bash
service mysql start
mysql -uroot -proot -e "CREATE DATABASE IF NOT EXISTS lms /*\!40100 DEFAULT CHARACTER SET utf8 */;"
mysql -uroot -proot lms < /home/user/workspace/table.sql