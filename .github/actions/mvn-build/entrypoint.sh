#!/bin/sh -l

# Install OpenJDK 18
wget -q https://download.java.net/java/GA/jdk18/43f95e8614114aeaa8e8a5fcf20a682d/36/GPL/openjdk-18_linux-x64_bin.tar.gz
tar -xvf openjdk-18_linux-x64_bin.tar.gz
mv jdk-18* /opt/
export JAVA_HOME=/opt/jdk-18
export PATH=$PATH:$JAVA_HOME/bin
update-alternatives --install "/usr/bin/java" "java" "/opt/jdk-18/bin/java" 1
update-alternatives --install "/usr/bin/javac" "javac" "/opt/jdk-18/bin/javac" 1

# Build Maven project
./mvnw clean install > /dev/null
./mvnw clean package -Dmaven.test.skip spring-boot:repackage -X

mkdir output
mv ./target/ufcg-0.0.1-SNAPSHOT.jar ./output
