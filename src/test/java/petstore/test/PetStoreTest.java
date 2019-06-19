package petstore.test;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.RestAssured;

import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoint.Config;
import petstore.endpoint.PetEndpoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TegModel;

import java.util.Random;

import static org.hamcrest.Matchers.is;
import static petstore.endpoint.PetEndpoint.*;

//@RunWith(SerenityRunner.class)
@RunWith(DataProviderRunner.class)
public class PetStoreTest {

    private int petId = 0;


    //    @Steps
    private PetEndpoint petEndpoint = new PetEndpoint();

//@DataProvider
//    public static PetModel getDateProviderCreate(){
//        return new PetModel({
//            {1,"s",new CategoryModel(),new String[]{"asdsd"},new TegModel[]{new TegModel()},"sdasd"}
//        };)
//}

    @DataProvider
    public static Object[][] getDateProviderCreatePet() {
        return new Object[][]{

                {new PetModel(1,"s",new CategoryModel(),new String[]{"asdsd"},new TegModel[]{new TegModel()},"sdasd"),200}
              //                {111, "somename", 400},
//                {0, "\"\"", 400}

        };

    }

//    @DataProvider
//    public static PetModel testPet() {
//
//        return new PetModel(
//                {
//
//                }
//        );
//    }

    /**
     * PetModel petModel = new PetModel(
     * petId,
     * "name",
     * new CategoryModel(),
     * new String[]{"www.zoo.com"},
     * new TegModel[]{new TegModel()},
     * "AVAILABLE"
     * );
     */


//        }
    @Test
    @UseDataProvider("getDateProviderCreatePet")
    public void createPetTest(PetModel petModel1,int code) {
       // PetModel petModel = new PetModel(petModel1);
//        PetModel petModel = new PetModel(
//                id,
//                name,
//                new CategoryModel(),
//                new String[]{"www.zoo.com"},
//                new TegModel[]{new TegModel()},
//                "AVAILABLE"
//        );

        petEndpoint.createNewPet(petModel1, code);

    }


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

        petEndpoint.createNewPet(petModel);//.log().all();

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
    public int getRandomPetId() {
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
