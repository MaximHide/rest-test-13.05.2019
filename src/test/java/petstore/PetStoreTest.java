package petstore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.junit.Test;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TegModel;

import static org.hamcrest.Matchers.is;


public class PetStoreTest {

    private int petId = 777;

    static {
        RestAssured.baseURI = Config.BASE_URI;

    }

    private enum Status {
        AVAILABLE,
        PENDING,
        SOLD
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

        //if pet not find  then create him & work

        ValidatableResponse response = RestAssured.given()
                .get(Config.GET_PET_BY_ID, petId)
                .then();
//                .log().all();

        JSONObject jsonObject = new JSONObject(response.extract().asString());

        if (jsonObject.isNull("id")) {

            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(petModel)
                    .post(Config.CREATE_PET)
                    .then()
                    .statusCode(200);
        }
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

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petModel)
                .put(Config.UPDATE_PET)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void checkEditPetTest(){

        RestAssured.given()
                .get(Config.GET_PET_BY_ID,petId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id",is(petId))
                .body("name", is ("new name for pet"));

    }

    @Test
    public void getPetByIdTest() {

        RestAssured.given()
//                .log().uri()
                .get(Config.GET_PET_BY_ID, petId)
                .then()
                .log().all()
                .statusCode(200);
    }


    @Test
    public void getPetByStatusTest() {

        for (Status status : Status.values()) {
            RestAssured.given()
                    .param("status", status)
//                    .log().uri()
                    .get(Config.GET_PET_BY_STATUS)

                    .then()
//                    .log().all()
                    .statusCode(200);
        }

    }

    @Test
    public void deletePetTest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
//                .body("{\"id\":\"777\"}")
                .delete(Config.GET_PET_BY_ID, petId)
                .then()
                .statusCode(200);
//                .log().all();

    }


}
