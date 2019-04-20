#scp -i "cs4227_key.pem" -r springBootDemo/. ubuntu@ec2-54-208-196-22.compute-1.amazonaws.com:~/SoftwareDesignAndArchitecture
#ssh -i "cs4227_key.pem" ubuntu@ec2-54-208-196-22.compute-1.amazonaws.com 'cd SoftwareDesignAndArchitecture/springBootDemo | mvn clean install | nohup spring-boot:run &' 
cd SoftwareDesignAndArchitecture/springBootDemo && mvn clean install -DskipTests && nohup mvn spring-boot:run -DskipTests &
