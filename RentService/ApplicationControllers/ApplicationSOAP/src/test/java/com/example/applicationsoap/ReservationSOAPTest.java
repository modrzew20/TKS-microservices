package com.example.applicationsoap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReservationSOAPTest implements SpringSOAPTest {

    @LocalServerPort
    int port;

    String accessLevel = "Client";
    String login = "loginxas6";
    String password = "password5";
    String laneType = "vip";
    String start = "2021-12-13T12:10:31";
    String end = "2021-12-13T12:30:31";

    private String URL;

    @PostConstruct
    private void init() {
        URL = "http://localhost:" + port + "/ws";
    }

    @Test
    public void addReservation() {
        String clientUUID = RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/usermodel")
                .body(String.format(CreateUserRequest, login + "b"))
                .post(URL)
                .then()
                .assertThat()
                .log().body()
                .statusCode(200)
                .extract().xmlPath().getString("Envelope.Body.CreateUserResponse.UserSoap.uuid");

        String laneUUID = RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/lanemodel")
                .body(String.format(addLaneRequest, laneType))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().xmlPath().getString("Envelope.Body.CreateLaneResponse.LaneSoap.uuid");

        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/lanemodel")
                .body(String.format(CreateReservationRequest, clientUUID, laneUUID, start, end))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().xmlPath().getString("Envelope.Body.CreateLaneResponse.LaneSoap.uuid");
    }

    @Test
    public void readAllReservation() {
        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/usermodel")
                .body(ReadAllReservationRequest)
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void deleteReservation() {
        String clientUUID = RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/usermodel")
                .body(String.format(CreateUserRequest, login + "a"))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().xmlPath().getString("Envelope.Body.CreateUserResponse.UserSoap.uuid");

        String laneUUID = RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/lanemodel")
                .body(String.format(addLaneRequest, laneType))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().xmlPath().getString("Envelope.Body.CreateLaneResponse.LaneSoap.uuid");

        String reservationUUID = RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/reservationmodel")
                .body(String.format(CreateReservationRequest, clientUUID, laneUUID, start, end))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().xmlPath().getString("Envelope.Body.CreateReservationResponse.ReservationSoap.uuid");

        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/reservationmodel")
                .body(String.format(ReadOneReservationRequest, reservationUUID))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200);

        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/reservationmodel")
                .body(String.format(DeleteReservationRequest, reservationUUID))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200);

        Response response = RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/reservationmodel")
                .body(String.format(ReadOneReservationRequest, reservationUUID))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(500)
                .extract().response();
    }

}
