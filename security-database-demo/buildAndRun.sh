#!/bin/sh
mvn clean package && docker build -t dmit2015/security-database-demo .
docker rm -f security-database-demo || true && docker run -d -p 8080:8080 -p 4848:4848 --name security-database-demo dmit2015/security-database-demo