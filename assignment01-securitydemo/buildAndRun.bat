@echo off
call mvn clean package
call docker build -t dmit2015/assignment01-securitydemo .
call docker rm -f assignment01-securitydemo
call docker run -d -p 8080:8080 -p 4848:4848 --name assignment01-securitydemo dmit2015/assignment01-securitydemo