package petstore.test;

import org.junit.Test;
import petstore.endpoint.UploadPhotoEndpoint;

import static org.hamcrest.CoreMatchers.is;

public class UploadPhotoTest {


    @Test
    public void uploadPhoto() {

        UploadPhotoEndpoint uploadPhotoEndpoint = new UploadPhotoEndpoint();

        String fileName = "test.jpg";
        uploadPhotoEndpoint.uploadPhoto(2, fileName)
                .body("code", is(200));
    }

}
