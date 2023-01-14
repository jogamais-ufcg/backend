#!/bin/sh -l

# Build Maven project
./mvnw clean package -Dspring.profiles.active=prod -Dmaven.test.skip spring-boot:repackage

# Creating output folder
mkdir output
mv ./target/ufcg-0.0.1-SNAPSHOT.jar ./output/app.jar
