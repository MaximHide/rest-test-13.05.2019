package petstore.endpoint;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import petstore.endpoint.Config;
import petstore.models.PetModel;


public class PetEndpoint {


    public enum Status {
        AVAILABLE,
        PENDING,
        SOLD
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json")
                .log().uri();
    }

    public ValidatableResponse getPetById(int id) {
        return given()
                .get(Config.GET_PET_BY_ID, id)
                .then()
                .statusCode(200);
    }

    public ValidatableResponse createNewPet(PetModel petModel) {

        return given()

                .body(petModel)
                .post(Config.CREATE_PET)
                .then()
                .statusCode(200);

    }

    public ValidatableResponse deletePet(int petId) {

        return given()

//                .body("{\"id\":\"777\"}")
                .delete(Config.GET_PET_BY_ID, petId)
                .then()
                .statusCode(200);
//                .log().all();
    }

    public ValidatableResponse editPet(PetModel petModel) {

        return given()

                .body(petModel)
                .put(Config.UPDATE_PET)
                .then()
                .statusCode(200);
    }

    public ValidatableResponse getPetByStatus(Status status) {

        return given()
                .param("status", status)
                .get(Config.GET_PET_BY_STATUS)
                .then()
                .statusCode(200);

    }
}
