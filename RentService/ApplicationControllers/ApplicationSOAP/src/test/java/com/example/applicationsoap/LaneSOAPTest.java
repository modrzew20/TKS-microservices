package com.example.applicationsoap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LaneSOAPTest implements SpringSOAPTest {

    @LocalServerPort
    int port;
    String basicLaneType = "vip";
    private String URL;

    @PostConstruct
    private void init() {
        URL = "http://localhost:" + port + "/ws";
    }

    private String addLaneRequest(String laneType) {
        return RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/lanemodel")
                .body(String.format(addLaneRequest, laneType))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().xmlPath().getString("Envelope.Body.CreateLaneResponse.LaneSoap.uuid");
    }

    private void readOneLaneRequest(String uuid, String laneType) {
        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/lanemodel")
                .body(String.format(readOneLaneRequest, uuid))
                .post(URL)
                .then()
                .assertThat()
                .body("Envelope.Body.ReadOneLaneResponse.LaneSoap.uuid", Matchers.hasToString(uuid))
                .body("Envelope.Body.ReadOneLaneResponse.LaneSoap.type", Matchers.hasToString(laneType))
                .statusCode(200);
    }

    private int readAllLaneRequest() throws ParserConfigurationException, IOException, SAXException {
        Response response = RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/lanemodel")
                .body(readAllLanesRequest)
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(response.getBody().asInputStream());
        return doc.getElementsByTagName("ns2:LaneSoap").getLength();
    }

    @Test
    void addLane() throws ParserConfigurationException, IOException, SAXException {
        String uuid = addLaneRequest(basicLaneType);

        readOneLaneRequest(uuid, basicLaneType);

        assertNotEquals(0, readAllLaneRequest());
    }

    @Test
    void updateLane() {
        String editedLaneType = "premium";

        String uuid = addLaneRequest(basicLaneType);

        assertNotEquals("", uuid);

        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/lanemodel")
                .body(String.format(updateLaneRequest, uuid, editedLaneType))
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200);

        readOneLaneRequest(uuid, editedLaneType);
    }


    @Test
    void deleteLane() throws ParserConfigurationException, IOException, SAXException {

        String uuid = addLaneRequest(basicLaneType);

        int sizeBefore = readAllLaneRequest();


        RestAssured.given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "http://example.com/applicationsoap/soapmodel/lanemodel")
                .body(String.format(DeleteLaneRequest, uuid))
                .post(URL)
                .then()
                .assertThat()
                .body("Envelope.Body.DeleteLaneResponse.LaneSoap.uuid", Matchers.hasToString(uuid))
                .statusCode(200);

        int sizeAfter = readAllLaneRequest();

        assertEquals(sizeAfter + 1, sizeBefore);

    }
}
