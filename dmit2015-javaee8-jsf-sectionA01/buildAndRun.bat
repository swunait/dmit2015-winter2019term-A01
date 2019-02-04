@echo off
call mvn clean package
call docker build -t dmit2015/dmit2015-javaee8-jsf-sectionA01 .
call docker rm -f dmit2015-javaee8-jsf-sectionA01
call docker run -d -p 8080:8080 -p 4848:4848 --name dmit2015-javaee8-jsf-sectionA01 dmit2015/dmit2015-javaee8-jsf-sectionA01