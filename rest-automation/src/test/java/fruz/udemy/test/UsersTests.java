package fruz.udemy.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import EndpointHelpers.UserHelper;
import fruz.udemy.model.ApiResponse;
import fruz.udemy.model.RandomDataGenerator;
import fruz.udemy.model.User;


import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;

public class UsersTests extends BasicTest {

    UserHelper userHelper = new UserHelper();

    @Test
    public void addUser() {

        User user = setPayload();

        given()
                .contentType(ContentType.JSON).log().all()
                .body(user)
                .when()
                .post("/api/user")
                .then()
                .statusCode(200);
    }



    @Test
    public void addUserAndCheckIfExists() {
        String username = RandomDataGenerator.randomAlphanumeric(10);
        User user = setPayload();
        user.setUsername(username);
        userHelper.createUser(user);
        User users = userHelper.getUserByUsername(username);
        assertEquals(username, users.getUsername());
    }

    @Test
    public void deleteUserAndCheckIfExists() {
        String username = RandomDataGenerator.randomAlphanumeric(10);
        User user = setPayload();
        user.setUsername(username);
        given()
                .contentType(ContentType.JSON).log().all()
                .body(user)
                .when()
                .post("/api/user")
                .then()
                .statusCode(200);

         given().log().all()
                .pathParam("username", username)
                .when()
                .delete("/api/user/{username}")
                .then()
                .statusCode(200);

        ApiResponse response =given().log().all()
                .pathParam("username", username)
                .when()
                .get("/api/user/{username}")
                .then()
                .log().all()
                .extract()
                .as(ApiResponse.class);

        assertEquals(response.getMessage(), "User not found");
    }

    public User setPayload() {
        User payload = new User();
        payload.setId(RandomDataGenerator.getRandomNumber(100, 10000));
        payload.setUsername(RandomDataGenerator.randomAlphanumeric(10));
        payload.setPassword(RandomDataGenerator.randomAlphanumeric(5));
        payload.setFirstName(RandomDataGenerator.randomAlphanumeric(10));
        payload.setLastName(RandomDataGenerator.randomAlphanumeric(10));
        payload.setEmail(RandomDataGenerator.randomAlphanumeric(7) + "@gmail.com");
        payload.setPhone(RandomDataGenerator.getRandomNumber(100000, 999999).toString());
        payload.setUserStatus(1);
        return payload;
    }
}
