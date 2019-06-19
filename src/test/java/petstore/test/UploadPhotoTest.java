package petstore.test;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import petstore.endpoint.UploadPhotoEndpoint;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SerenityRunner.class)
@Concurrent
public class UploadPhotoTest {


    @Test
    public void uploadPhoto() {

        UploadPhotoEndpoint uploadPhotoEndpoint = new UploadPhotoEndpoint();

        String fileName = "test.jpg";
        uploadPhotoEndpoint.uploadPhoto(2, fileName)
                .body("code", is(200));
    }

}
