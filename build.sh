#/bin/bash
mvn clean
# mvn -Dmaven.test.skip=true package
mvn package
docker-compose build --no-cache