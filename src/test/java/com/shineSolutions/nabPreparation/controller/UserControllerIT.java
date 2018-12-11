package com.shineSolutions.nabPreparation.controller;


import com.shineSolutions.nabPreparation.model.UserDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @LocalServerPort
    int port;

    private UserDTO userDTO;
    @Before
    public void setUp() {
        RestAssured.port = port;
        userDTO = UserDTO.builder().userName("Danny.Morphy").userId(5l).build();
    }

    @Test
    public void getUserReturn200(){
        given().basePath("/user").get("/")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("userId", hasItems(1,2,3))
                .body("userName",hasItems("James.Bond","Jimmy.White"));
    }

    @Test
    public void getOneUserReturn200(){
        given().contentType("application/json")
                .when().get("/user/{userId}",1)
                .then().statusCode(HttpStatus.SC_OK)
                .body("userId", equalTo(1))
                .body("userName",equalTo("James.Bond"));
    }
    @Test
    public void getUserNotFoundShouldReturnUserNotFoundException(){
        given().contentType("application/json")
                .when().get("/user/{userId}",10)
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void getTransactionsReturn200() throws InterruptedException {
        given().basePath("/transactions").get("/")
                .then()
                .statusCode(200)
                .body("originUserId", hasItems(1,1,2))
                .body("originUserName",hasItems("James.Bond"));
    }

    @Test
    public void postUserReturn200AndShouldReturnItem() throws InterruptedException{
        given().contentType("application/json")
                .body(userDTO).contentType(ContentType.JSON)
                .when().post("/user")
                .then().statusCode(HttpStatus.SC_OK)
                .body("userName",is("Danny.Morphy"));
    }

    @Test
    public void addUserShouldReturnItem(){
        given()
                .body(userDTO)
                .contentType(ContentType.JSON)
                .when().post("/user")
                .then().statusCode(HttpStatus.SC_OK)
                .body("userName",is("Danny.Morphy"));
    }

    @Test
    public void putUserReturn200(){
        given().contentType("application/json")
                .body(userDTO)
                .contentType(ContentType.JSON)
                .when().put("/user/{uerId}",4)
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void delUserReturn200(){
        given().contentType("application/json")
                .contentType(ContentType.JSON)
                .when().delete("/user/{userId}",1)
                .then().statusCode(HttpStatus.SC_OK);
    }
}
