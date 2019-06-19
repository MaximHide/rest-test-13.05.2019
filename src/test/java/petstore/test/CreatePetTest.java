package petstore.test;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoint.PetEndpoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TegModel;


import java.util.Arrays;
import java.util.Collection;

@Concurrent
@RunWith(SerenityParameterizedRunner.class)
public class CreatePetTest {


    @Steps //вычитываем степы
    private PetEndpoint petEndpoint = new PetEndpoint();

    @TestData
    public static Collection<Object[]> testDataCreatePet() {
        return Arrays.asList(new Object[][]{
                {"zverushka", 200},
                {"zverenysh", 200},
                {"zverushka", 200},
                {"zverushka", 200},
                {"zverushka", 200},{"zverushka", 200},{"zverushka", 200},
                {"zverushka", 200},
                {"zverushka", 200},
                {"zverushka", 200},{"zverushka", 200},{"zverushka", 200},{"zverushka", 200},
                {"zverushka", 200},{"zverushka", 200},{"zverushka", 200},{"zverushka", 200},
                {"zverushka", 200},{"zverushka", 200},{"zverushka", 200},{"zverushka", 200},
                {"zverushka", 200},{"zverushka", 200},{"zverushka", 200},{"zverushka", 200},
                {"zverushka", 200},{"zverushka", 200},{"zverushka", 200},{"zverushka", 200},
                {"zverushka", 200},{"zverushka", 200},{"zverushka", 200},{"zverushka", 200},
                {"zverushka", 200},{"zverushka", 200},{"zverushka", 200},{"zverushka", 200},
                {"zverushka", 200},{"zverushka", 200},{"zverushka", 200},{"zverushka", 200},
                {"zverushka", 200}


        });
    }


    private final String petName;
    private final int expectedStatusCode;


    public CreatePetTest(String petName, int expectedStatusCode) {
        this.petName = petName;
        this.expectedStatusCode = expectedStatusCode;
    }


    @Test
    public void createPetNameCombinationsTest() {

        PetModel petModel = new PetModel(

                petName,
                new CategoryModel(),
                new String[]{"www.zoo.com"},
                new TegModel[]{new TegModel()},
                "AVAILABLE");

        petEndpoint
                .createNewPet(petModel)
                .statusCode(expectedStatusCode);
    }


}
