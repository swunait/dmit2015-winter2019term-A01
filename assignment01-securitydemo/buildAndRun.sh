#!/bin/sh
mvn clean package && docker build -t dmit2015/assignment01-securitydemo .
docker rm -f assignment01-securitydemo || true && docker run -d -p 8080:8080 -p 4848:4848 --name assignment01-securitydemo dmit2015/assignment01-securitydemo