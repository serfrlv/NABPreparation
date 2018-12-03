# NABPreparation
#preparation for the NAB 
Create a Rest API to retrieve transaction with the following tech stack
Spring Boot
Java 8
Hystrix
Gradle
Flyway
JPA
Lombok

Docker  use a Dockerfile
Swagger

Document the API with Swagger
Use flyway to create the DB schema and add test data
Wrap calls to the DB in Hystrix commands
Create a docker image with the app

/user/{userId}/transactions

#2018-11-30 fix
- Use Lombok instead of creating getters and setters
- Add the gradle wrapper to the source code. https://medium.com/@bherbst/understanding-the-gradle-wrapper-a62f35662ab7
- Add @EnableCircuitBreaker in the NabPreparationApplication class otherwise Hystrix won’t work
- Modify fallback method to have the same signature as the method that the @HystrixCommand is on
- Add yaml application.yml
- Use Java’s LocalDate instead of Date
- In the service class use the Lombok builder to create the dto objects
- Add a .gitignore and ignore all log files, the build folder, the .idea folder etc

#2018-12-03 fix
- Use Lombok's @Data when appropriate https://projectlombok.org/features/Data or @Value for immutable objects  https://projectlombok.org/features/Value
- Use BigDecimal instead of double for any fields that represent money.
- Returning an empty list instead of returning null in your fallback methods which will cause problems when the calling code tries to access the variable.
- Delete the application.properties as now have application.yml
- Add the actuator starter to the project.  https://www.baeldung.com/spring-boot-actuatorsd.
- Add the swagger Restful API documentation file.
- Add the docker file.
