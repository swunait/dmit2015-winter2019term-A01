@echo off
call mvn clean package
call docker build -t dmit2015/security-database-demo .
call docker rm -f security-database-demo
call docker run -d -p 8080:8080 -p 4848:4848 --name security-database-demo dmit2015/security-database-demo