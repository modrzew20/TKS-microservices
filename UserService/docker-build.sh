#!/bin/bash
mvn package
cd ApplicationDocker || exit
mkdir target/dependency
cd target/dependency
jar -xf ../ApplicationDocker-0.0.1-SNAPSHOT.war
cd ../.. && docker build -t TKS-microservices/RentService .