package petstore.endpoint;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import petstore.endpoint.Config;
import petstore.models.PetModel;
//
//import java.util.logging.Logger;


public class PetEndpoint {

    //    Logger logger = Logger.getLogger(this.getClass().getName());
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public enum Status {
        AVAILABLE,
        PENDING,
        SOLD
    }

    private RequestSpecification given() {
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
        return SerenityRest.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json");
//                .log().uri();
    }

    public ValidatableResponse getPetById(int id) {
        log.info("++++++++++++++++++++++++++++getPetById");
        return given()
                .get(Config.GET_PET_BY_ID, id)
                .then()
                .statusCode(200);
    }

//    @Step
    public ValidatableResponse createNewPet(PetModel petModel) {

        return given()

                .body(petModel)
                .post(Config.CREATE_PET)
                .then()
                .statusCode(200);

    }

//    @Step
    public ValidatableResponse createNewPet(PetModel petModel,int code) {

        return given()

                .body(petModel)
                .post(Config.CREATE_PET)
                .then()
                .statusCode(code);

    }

    @Step
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

    @Step
    public ValidatableResponse getPetByStatus(Status status) {

        return given()
                .param("status", status)
                .get(Config.GET_PET_BY_STATUS)
                .then()
                .statusCode(200);

    }


}
