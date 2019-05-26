import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

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
    public void createNewPet() {

        RestAssured.given()
                .contentType(ContentType.JSON)
//                .log().uri()
                .body("{ \"id\": \"777\" , \"name\" : \"MSSWtest\", \"photoUrls\": [], \"tags\":[], \"status\": \"available\"}")
                .post("pet/")
                .then()
                .statusCode(200);
//                .log().all();
    }


    @Test
    public void getPetByIdTest() {
//        int petId = 777;

        RestAssured.given()
//                .log().uri()
                .get(Config.GET_PET_BY_ID, petId)
                .then()
                .log().all()
                .statusCode(200);
    }


    @Test
    public void getPetByStatusTest() {

        for (Status statusDate : Status.values()) {
            RestAssured.given()
                    .param("status", statusDate)
//                    .log().uri()
                    .get(Config.GET_PET_BY_STATUS)

                    .then()
//                    .log().all()
                    .statusCode(200);
        }

    }

    @Test
    public void deletePet() {
        RestAssured.given()
                .contentType(ContentType.JSON)
//                .body("{\"id\":\"777\"}")
                .delete(Config.GET_PET_BY_ID, petId)
                .then()
                .statusCode(200);
//                .log().all();

    }


}
