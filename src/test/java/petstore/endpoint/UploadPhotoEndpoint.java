package petstore.endpoint;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

import java.io.File;

public class UploadPhotoEndpoint {


    private RequestSpecification given() {
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
        return SerenityRest.given()
                .baseUri(Config.BASE_URI)
                .accept("application/json")
                .contentType("multipart/form-data");
    }


    public ValidatableResponse uploadPhoto(int petId, String fileName) {
        File file = new File("./" + fileName);

        return given()
                .multiPart(file)
                .post(Config.UPLOAD_PHOTO, petId)
                .then().log().all()
                .statusCode(200);
//                .log().all();

    }

    public long getSizeFile(String fileName) {

        return new File("./" + fileName).length();
    }


}
