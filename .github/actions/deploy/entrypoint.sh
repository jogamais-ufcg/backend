#!/bin/sh -l

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
