package fruz.udemy.test;

import static com.jayway.restassured.RestAssured.given;
import static fruz.udemy.model.MyStrings.strings;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

import EndpointHelpers.PetHelper;
import EndpointHelpers.UserHelper;
import fruz.udemy.model.*;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;

import java.util.List;
import java.util.Random;

public class PetsTests extends BasicTest {

    String NAME_FOR_TESTING = "Tom";
    PetHelper petHelper = new PetHelper();

    @Test
    public void addPet() {      //POST request at /pet

        Pet pet = setPayload();

        given()
                .contentType(ContentType.JSON).log().all()
                .body(pet)
                .when()
                .post("/api/pet")
                .then()
                .statusCode(200);
    }

    @Test
    public void addPetAndCheckIfExists() {
        int petId = RandomDataGenerator.getRandomNumber(5, 10);
        Pet pet = setPayload();
        pet.setId(petId);
        petHelper.createPet(pet);
        Pet pets = petHelper.getPetById(petId);
        assertEquals(petId, pets.getId());
    }

    @Test
    public void checkPetExistsByName() {
        String name = "Tom";
        Pet initial = setPayload();
        initial.setName("zzzz");
        for (int i = 0; i < 10; i++) {
            if(petHelper.getPetById(i).getName()==name){
                initial = petHelper.getPetById(i);
                break;
            }
           else continue;
        }
        Assert.assertNotEquals(initial.getName(),"zzzz");
    }

    @Test
    public void checkIfPetExistsByName(){

        Pet tmp = setPayload();
        Pet[] availablePets = petHelper.getByStatus("available");
        for (Pet p : availablePets) {
            if (p.getName() == NAME_FOR_TESTING){
                tmp.setName(p.getName());
                break;
            }
        }
    Assert.assertEquals(NAME_FOR_TESTING,tmp.getName());
    }

    @Test
    public void updatePet() {        //PUT request to update an already existing pet
        int chosenId = 8;
        Pet pet = petHelper.getPetById(chosenId);
        pet.setName("Spike");
        given()
                .contentType(ContentType.JSON).log().all()
                .body(pet)
                .when()
                .put("/api/pet")
                .then()
                .statusCode(200);


    }


    public Pet setPayload() {
        Pet payload = new Pet();

        payload.setId(RandomDataGenerator.getRandomNumber(1, 10));
        Category category = new Category(RandomDataGenerator.getRandomNumber(1, 10), RandomDataGenerator.randomAlphanumeric(10));
        payload.setCategory(category);
        Random random = new Random();

        payload.setStatus(strings[random.nextInt(strings.length)]);
        Tag tag1 = new Tag(1, RandomDataGenerator.randomAlphanumeric(10));
        Tag tag2 = new Tag(2, RandomDataGenerator.randomAlphanumeric(10));
        Tag tag3 = new Tag(3, RandomDataGenerator.randomAlphanumeric(10));
        Tag tag4 = new Tag(4, RandomDataGenerator.randomAlphanumeric(10));
        ArrayList<Tag> tags = new ArrayList<Tag>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);
        List<Tag> newTags = tags;
        Tag[] finalTags = new Tag[4];
        for (int i = 0; i < newTags.size(); i++) {
            finalTags[i] = newTags.get(i);
        }
        payload.setTag(finalTags);
        payload.setName(RandomDataGenerator.randomAlphanumeric(10));

        ArrayList<String> photoURL = new ArrayList<String>();
        int numberOfPhotos = RandomDataGenerator.getRandomNumber(1, 10);
        for (int i = 0; i < numberOfPhotos; i++) {
            String tmp = RandomDataGenerator.randomAlphanumeric(10);
            photoURL.add(tmp);
        }
        String[] photos = new String[numberOfPhotos];
        for (int j = 0; j < photoURL.size(); j++) {
            photos[j] = photoURL.get(j);
        }
        payload.setPhotoUrl(photos);
        return payload;
    }
}
