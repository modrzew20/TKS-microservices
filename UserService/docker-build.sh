mvn package -DskipTests
cd ApplicationDocker || exit
mkdir target/dependency
cd target/dependency
jar -xf ../ApplicationDocker-1.0-SNAPSHOT.jar
