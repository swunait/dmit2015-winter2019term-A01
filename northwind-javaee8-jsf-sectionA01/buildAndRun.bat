@echo off
call mvn clean package
call docker build -t northwind/northwind-javaee8-jsf-sectionA01 .
call docker rm -f northwind-javaee8-jsf-sectionA01
call docker run -d -p 8080:8080 -p 4848:4848 --name northwind-javaee8-jsf-sectionA01 northwind/northwind-javaee8-jsf-sectionA01