#!/bin/sh -l

# Build Maven project
./mvnw clean package -Dmaven.test.skip spring-boot:repackage

mkdir output
mv ./target/ufcg-0.0.1-SNAPSHOT.jar ./output/app.jar
