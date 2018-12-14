pipeline {
     agent any
     stages {
          stage("Compile") {
               steps {
                    sh "./gradlew clean build"
               }
          }
          stage("Unit test") {
               steps {
                    sh "./gradlew test"
               }
          }

          stage("Package") {
               steps {
                    sh "./gradlew build docker"
               }
          }

          stage("Docker build") {
               steps {
                    sh "docker build -t com.shineSolutions/nab-preparation-transaction-docker"
               }
          }

          stage("Deploy to docker 8091") {
               steps {

                    sh "docker run -d --rm -p 8091:8090 --name nab-preparation com.shineSolutions/nab-preparation-transaction-docker"

               }
          }

          stage("Deploy to docker with PostSQL"){
               steps{
                    sh "docker-compose up"
               }
          }
     }

}