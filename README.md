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

#2018-12-04 fix
- delete the  duplicate the root of keys in yaml.
- Use @Slf4j on the class instead of creating a logger
- Replace the setter injection with field or constructor injection in UserRepositoryImpl 

#2018-12-05 fix
- change the database name to postgres. By this way won’t need to create a database in your docker configuration
- revert the change you made in the Dockerfile
- add unit tests that do not use spring in any way.  Use mockito to mock dependencies 
- add some rest assured tests.  http://rest-assured.io/

