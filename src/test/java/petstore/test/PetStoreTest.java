package petstore.test;

import io.restassured.RestAssured;
import org.junit.Test;
import petstore.endpoint.Config;
import petstore.endpoint.PetEndpoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TegModel;

import java.util.Random;

import static org.hamcrest.Matchers.is;
import static petstore.endpoint.PetEndpoint.*;


public class PetStoreTest {

    private int petId = 0 ;



    private PetEndpoint petEndpoint = new PetEndpoint();

    @Test
    public void getPetById() {
        if (petId > 0) {
            System.out.println(petId);
            petEndpoint.getPetById(petId).log().all();
        }
        petEndpoint.getPetById(getRandomPetId()).log().all();
    }

    @Test
    public void createNewPetTest() {

        PetModel petModel = new PetModel(
                petId,
                "name",
                new CategoryModel(),
                new String[]{"www.zoo.com"},
                new TegModel[]{new TegModel()},
                "AVAILABLE"
        );

        petEndpoint.createNewPet(petModel).log().all();

    }


    @Test
    public void editPetTest() {

        PetModel petModel = new PetModel(
                petId,
                "new name for pet",
                new CategoryModel(),
                new String[]{},
                new TegModel[]{new TegModel()},
                "AVAILABLE"
        );

        petEndpoint.editPet(petModel).log().all();
    }

    @Test
    public void checkEditPetTest() {

        RestAssured.given()
                .get(Config.GET_PET_BY_ID, petId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", is(petId))
                .body("name", is("new name for pet"));

    }


    @Test
    public void getPetByStatusTest() {
        for (Status status : Status.values()) {
            petEndpoint.getPetByStatus(status);
        }
    }

    @Test
    public void deletePetTest() {

        petEndpoint.deletePet(petId).log();

    }
//
    public int getRandomPetId(){
        Random random = new Random();
        int randomPetId = random.nextInt(100);

        return randomPetId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}
