mvn package -DskipTests
mkdir target/dependency
cd target/dependency
jar -xf ../EurekaService-1.0-SNAPSHOT.jar