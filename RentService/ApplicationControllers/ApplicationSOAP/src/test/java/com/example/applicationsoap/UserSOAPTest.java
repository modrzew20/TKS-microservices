package com.example.applicationsoap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSOAPTest implements SpringSOAPTest {
    @LocalServerPort
    int port;
    String accessLevel = "Client";
    String login = "login5";
    String password = "password5";
    private String URL;

    @PostConstruct
    private void init() {
        URL = "http://localhost:" + port + "/ws";
    }

    private String createUserRequest(String accessLevel, String login, String password) {
        return RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/usermodel")
                .body(String.format(CreateUserRequest, accessLevel, login, password))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().xmlPath().getString("Envelope.Body.CreateUserResponse.UserSoap.uuid");

    }

    private Response readUserRequest(String uuid) {
        return RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/usermodel")
                .body(String.format(ReadOneUserRequest, uuid))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
    }

    private void updateUserRequest(String uuid, String login, String password, int statusCode) {
        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/usermodel")
                .body(String.format(UpdateUserRequest, uuid, login, password))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(statusCode);
    }

    @Test
    public void createUser() {
        // create user
        String uuid = createUserRequest(accessLevel, login + "a", password);
        readUserRequest(uuid);
    }

    @Test
    public void updateUser() {
        String updatedLogin = "updatedUser3";
        String updatedPassword = "password7";
        // create user
        String uuid = createUserRequest(accessLevel, login + "b", password);
        updateUserRequest(uuid, updatedLogin, updatedPassword, 200);
        Response response = readUserRequest(uuid);
        assertEquals(uuid, response.getBody().xmlPath().getString("Envelope.Body.ReadOneUserResponse.UserSoap.uuid"));
        assertEquals(updatedLogin, response.getBody().xmlPath().getString("Envelope.Body.ReadOneUserResponse.UserSoap.login"));
        assertEquals(updatedPassword, response.getBody().xmlPath().getString("Envelope.Body.ReadOneUserResponse.UserSoap.password"));
    }

    @Test
    public void updateWithWrongLogin() {
        String updatedLogin = "";
        String updatedPassword = "password8";
        // create user
        String uuid = createUserRequest(accessLevel, login + "c", password);
        updateUserRequest(uuid, updatedLogin, updatedPassword, 500);
    }

    @Test
    public void uniqueLogin() {
        // create user
        String uuid = createUserRequest(accessLevel, login + "d", password);

        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/usermodel")
                .body(String.format(CreateUserRequest, accessLevel, login + "d", password))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(500);
    }

    @Test
    public void emptyLogin() {
        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/usermodel")
                .body(String.format(CreateUserRequest, accessLevel, "", password))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(500);
    }

    @Test
    @Disabled
    public void activate() {
        String uuid = createUserRequest(accessLevel, login + "e", password);

        // deactivate
        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/usermodel")
                .body(String.format(DeactivateUserRequest, uuid))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200);
        Response response = readUserRequest(uuid);
        assertEquals("false", response.getBody().xmlPath().getString("Envelope.Body.ReadOneUserResponse.UserSoap.isActive"));

        //activate
        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/usermodel")
                .body(String.format(ActivateUserRequest, uuid))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200);
        response = readUserRequest(uuid);
        assertEquals("true", response.getBody().xmlPath().getString("Envelope.Body.ReadOneUserResponse.UserSoap.isActive"));

    }
}
