@echo off
echo ================================
echo  Setting Project-X DB Variables
echo ================================

set DB_USERNAME=root
set DB_PASSWORD=12345678
set DB_NAME=lmsdb

echo DB_USERNAME=%DB_USERNAME%
echo DB_PASSWORD=********
echo DB_NAME=%DB_NAME%

echo ================================
echo  Running Maven Build
echo ================================
mvn clean package

echo ================================
echo  Build Complete
echo ================================
pause
