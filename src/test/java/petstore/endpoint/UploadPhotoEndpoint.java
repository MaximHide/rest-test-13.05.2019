package petstore.endpoint;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;

public class UploadPhotoEndpoint {


    private RequestSpecification given() {
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
        return SerenityRest.given()
                .baseUri(Config.BASE_URI)
                .accept("application/json")
                .contentType("multipart/form-data");
    }


    public ValidatableResponse uploadPhoto(int petId, String fileName, String metadata) {

        File file = new File("./" + fileName);

        String checkMessage = "additionalMetadata: " + metadata + "\n" +
                "File uploaded to ./" + fileName + ", " + getSizeFile(fileName) + " bytes";

        return given()
                .multiPart(file)
                .multiPart("additionalMetadata", metadata)
                .post(Config.UPLOAD_PHOTO, petId)
                .then().log().all()
                .body("message", is(checkMessage))
                .statusCode(200);
//                .log().all();

    }

    public ValidatableResponse uploadPhoto(int petId, String fileName) {

        File file = new File("./" + fileName);
        String checkMessage = "additionalMetadata: null" + "\n" +
                "File uploaded to ./" + fileName + ", " + getSizeFile(fileName) + " bytes";


        return given()
                .multiPart(file)
                .multiPart("additionalMetadata", "null")
                .post(Config.UPLOAD_PHOTO, petId)
                .then().log().all()
                .body("message", is(checkMessage))
                .statusCode(200);
//                .log().all();

    }

    private long getSizeFile(String fileName) {

        return new File("./" + fileName).length();
    }


}
