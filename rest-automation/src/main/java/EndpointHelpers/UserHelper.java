package EndpointHelpers;

import com.jayway.restassured.http.ContentType;
import fruz.udemy.model.User;

import static com.jayway.restassured.RestAssured.given;

public class UserHelper {

   public User getUserByUsername(String username){
      return given().log().all()
               .pathParam("username", username)
               .when()
               .get("/api/user/{username}")
               .then()
               .statusCode(200)
              .extract()
              .as(User.class);

   }

   public void createUser(User user){
      given()
              .contentType(ContentType.JSON).log().all()
              .body(user)
              .when()
              .post("/api/user")
              .then()
              .statusCode(200);
   }
}
