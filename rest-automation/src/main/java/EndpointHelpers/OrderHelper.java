package EndpointHelpers;

import com.jayway.restassured.http.ContentType;
import fruz.udemy.model.Order;
import fruz.udemy.model.User;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class OrderHelper {

    public Order getOrderById(int orderId){
        return given().log().all()
                .pathParam("orderId", orderId)
                .when()
                .get("/api/store/order/{orderId}")
                .then()
                .statusCode(200)
                .extract()
                .as(Order.class);

    }

        /*public Pet getByStatus(){
        return List<Pet>
        }*/

    public void createOrder(Order order){
        given()
                .contentType(ContentType.JSON).log().all()
                .body(order)
                .when()
                .post("/api/store/order")
                .then()
                .statusCode(200);
    }
}
