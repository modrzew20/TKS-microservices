package applicationcontroller;


import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.PostConstruct;

import static org.hamcrest.Matchers.equalTo;

public class UserControllerTest implements SpringTest {

    @LocalServerPort
    private int port;

    private String URL;

    @PostConstruct
    private void init() {
        URL = BASE_URL + port + "/";
    }

    @Test
    public void create() {
        // create user
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "Client")
                .formParam("login", "login5")
                .formParam("password", "password5")
                .when().post(URL + "user/add")
                .then()
                .assertThat()
                .statusCode(200)
                .body("login", equalTo("login5"));

    }

    @Test
    public void read() {
        // create user
        String uuid = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("login", "login6")
                .when().post(URL + "user/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        // read user
        RestAssured.when().get(URL + "user/{uuid}", uuid)
                .then()
                .assertThat()
                .statusCode(200)
                .body("login", equalTo("login6"))
                .body("uuid", equalTo(uuid));
    }

    @Test
    public void update() {
        // create user
        String uuid = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("login", "login7")
                .when().post(URL + "user/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        // update user
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("id", uuid)
                .formParam("login", "updatedUser3")
                .when().post(URL + "user/update")
                .then()
                .assertThat()
                .statusCode(200)
                .body("login", equalTo("updatedUser3"))
                .body("uuid", equalTo(uuid));
    }

    @Test
    public void updateWithWrongLogin() {
        // create user
        String uuid = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("login", "login8")
                .when().post(URL + "user/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        // update user
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("id", uuid)
                .formParam("login", "")
                .when().post(URL + "user/update")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void uniqueLogin() {
        // create user
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("login", "login12")
                .when().post(URL + "user/add")
                .then()
                .assertThat()
                .statusCode(200);
        // create user with same login
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("login", "login12")
                .when().post(URL + "user/add")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void emptyLogin() {
        // create user with empty login
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("login", "")
                .when().post(URL + "user/add")
                .then()
                .assertThat()
                .statusCode(400);
    }

}