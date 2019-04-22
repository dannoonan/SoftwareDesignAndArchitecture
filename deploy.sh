#!/bin/bash

ssh -oStrictHostKeyChecking=no -tt -i "cs4227_key.pem" ubuntu@ec2-54-208-196-22.compute-1.amazonaws.com "cd SoftwareDesignAndArchitecture; git pull; cd springBootDemo; mvn clean install -DskipTests; nohup mvn spring-boot:run -DskipTests &"
