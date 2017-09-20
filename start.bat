@echo off
:a
cls
echo Booting up the server
cd %~dp0/target
java -jar wine-0.0.1-SNAPSHOT.war