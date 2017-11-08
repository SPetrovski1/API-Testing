package fruz.udemy.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

import EndpointHelpers.OrderHelper;
import EndpointHelpers.PetHelper;
import EndpointHelpers.UserHelper;
import fruz.udemy.model.*;


import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;

import java.util.List;

public class OrdersTests extends BasicTest {

    OrderHelper orderHelper = new OrderHelper();

    @Test
    public void addOrder() {

        Order order = setPayload();


        given()
                .contentType(ContentType.JSON).log().all()
                .body(order)
                .when()
                .post("api/store/order")
                .then()
                .statusCode(200);
    }

    @Test
    public void addOrderAndCheckIfExists() {
        int orderId = RandomDataGenerator.getRandomNumber(5, 10);
        Order order = setPayload();
        order.setId(orderId);
        orderHelper.createOrder(order);
        Order orders = orderHelper.getOrderById(orderId);
        assertEquals(orderId, orders.getId());
    }




    public Order setPayload() {
        Order payload = new Order();

        payload.setId(RandomDataGenerator.getRandomNumber(5,50));
        payload.setPetId(RandomDataGenerator.getRandomNumber(1,20));
        payload.setQuantity(RandomDataGenerator.getRandomNumber(1,5));
        int day = RandomDataGenerator.getRandomNumber(1,28);
        int month = RandomDataGenerator.getRandomNumber(1,12);
        int year = RandomDataGenerator.getRandomNumber(2017,2025);
        payload.setShipDate(day + "" + month + "" + year);
        payload.setStatus("placed");
        payload.setComplete(false);


        return payload;
    }

    public String quantityToString(int quantity) {
        if (quantity == 1) {
            return "one";
        } else if (quantity == 2)
            return "two";
        else if (quantity == 3)
            return "three";
        else if (quantity == 4)
            return "four";
        else return "five";// :(
    }
}
