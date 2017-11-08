package EndpointHelpers;

import com.jayway.restassured.http.ContentType;
import fruz.udemy.model.Pet;
import fruz.udemy.model.User;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class PetHelper {

    public Pet getPetById(int petId){
        return given().log().all()
                .pathParam("petId", petId)
                .when()
                .get("/api/pet/{petId}")
                .then()
                .statusCode(200)
                .extract()
                .as(Pet.class);

    }


    public Pet[] getByStatus(String status){
        return given().log().all()
                .pathParam("petStatus",status)
                .when()
                .get("api/pet/api/pet/findByStatus?status=" + status)
                .then()
                .statusCode(200)
                .extract()
                .as(Pet[].class);
    }

    /*public Pet updatePet(int petId){
        return given().log().all()
                .pathParam("petId",petId)
                .when()
                .put("/api/pet/")
                .then()
                .statusCode(200)
                .extract()
                .as(Pet.class);
    }*/

        /*public Pet getByStatus(){
        return List<Pet>
        }*/

    public void createPet(Pet pet){
        given()
                .contentType(ContentType.JSON).log().all()
                .body(pet)
                .when()
                .post("/api/pet")
                .then()
                .statusCode(200);
    }
}
