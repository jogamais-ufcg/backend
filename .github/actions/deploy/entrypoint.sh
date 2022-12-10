#!/bin/sh -l

# Install OpenJDK 18
wget https://download.java.net/java/GA/jdk18/43f95e8614114aeaa8e8a5fcf20a682d/36/GPL/openjdk-18_linux-x64_bin.tar.gz
tar -xvf openjdk-18_linux-x64_bin.tar.gz
mv jdk-18* /opt/
export JAVA_HOME=/opt/jdk-18
export PATH=$PATH:$JAVA_HOME/bin
update-alternatives --install "/usr/bin/java" "java" "/opt/jdk-18/bin/java" 1
update-alternatives --install "/usr/bin/javac" "javac" "/opt/jdk-18/bin/javac" 1

# Save key file
echo "$1" >> key.pem

# Build Maven project
./mvnw clean install
./mvnw clean package -Dmaven.test.skip spring-boot:repackage -X

# Stop backend service
ssh -i ./key.pem ec2-user@ec2-52-91-233-221.compute-1.amazonaws.com "sudo systemctl stop jogamais"

# Remove old jar file
ssh -i ./key.pem ec2-user@ec2-52-91-233-221.compute-1.amazonaws.com "sudo rm /home/ec2-user/ufcg-0.0.1-SNAPSHOT.jar"

# Upload jar file to EC2
scp -i ./key.pem ./target/ufcg-0.0.1-SNAPSHOT.jar ec2-user@ec2-52-91-233-221.compute-1.amazonaws.com:~

# Start backend service
ssh -i ./key.pem ec2-user@ec2-52-91-233-221.compute-1.amazonaws.com "sudo systemctl start jogamais"
