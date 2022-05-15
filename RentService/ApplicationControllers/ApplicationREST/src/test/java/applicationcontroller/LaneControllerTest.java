package applicationcontroller;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.PostConstruct;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class LaneControllerTest implements SpringTest {

    @LocalServerPort
    private int port;

    private String URL;

    public static io.restassured.response.Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }

    @PostConstruct
    private void init() {
        URL = BASE_URL + port + "/";
    }

    @Test
    void addLane() {
        io.restassured.response.Response response = doGetRequest(URL + "lane");
        List<String> jsonResponse = response.jsonPath().getList("$");
        int size = jsonResponse.size();

        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("type", "vip")
                .when().post(URL + "lane/add")
                .then()
                .assertThat()
                .statusCode(200);

        RestAssured.given().contentType("application/x-www-form-urlencoded; charset=utf-8").get(URL + "lane").then().assertThat()
                .statusCode(200).body("size()", is(size + 1));

    }


    @Test
    void updateLane() {
        //create
        String uuid = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("type", "vip")
                .when().post(URL + "lane/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("id", uuid)
                .formParam("type", "normal")
                .when().post(URL + "lane/update")
                .then()
                .assertThat()
                .statusCode(200);

        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .when().get(URL + "lane/" + uuid)
                .then()
                .assertThat()
                .statusCode(200)
                .body("uuid", equalTo(uuid))
                .body("type", equalTo("normal"));

    }


    @Test
    void deleteLane() {
        //create
        String uuid = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("type", "vip")
                .when().post(URL + "lane/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        io.restassured.response.Response response = doGetRequest(URL + "lane");
        List<String> jsonResponse = response.jsonPath().getList("$");
        int size = jsonResponse.size();

        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .when().delete(URL + "lane/delete/" + uuid)
                .then()
                .assertThat()
                .statusCode(200);

        RestAssured.given().contentType("application/x-www-form-urlencoded; charset=utf-8").get(URL + "lane").then().assertThat()
                .statusCode(200).body("size()", is(size - 1));

    }
}
