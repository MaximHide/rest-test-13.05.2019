package petstore.test;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import petstore.endpoint.PetEndpoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TegModel;

import static org.hamcrest.Matchers.is;
import static petstore.endpoint.PetEndpoint.Status;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Concurrent
public class PetStoreTest {

    private int petId = 777;

    private PetModel petModel;

    @Before
    public void setUp() {

        petModel = new PetModel(
                petId,
                "new name pet",
                new CategoryModel(),
                new String[]{},
                new TegModel[]{new TegModel()},
                "PENDING"
        );

    }

    @Steps
    private PetEndpoint petEndpoint = new PetEndpoint();

    @Test
    public void test1_createNewPetTest() {

        petModel = new PetModel(
                petId,
                "test pet",
                new CategoryModel(),
                new String[]{"www.zoo.com"},
                new TegModel[]{new TegModel()},
                "AVAILABLE"
        );

        petEndpoint.createNewPet(petModel);//.log().all();

    }


    @Test
    public void test2_getPetById() {

        petEndpoint.getPetById(petId).log().all();

    }


    @Test
    public void test3_editPetTest() {

        petEndpoint.editPet(petModel).log().all();

    }

    @Test
    public void test4_checkEditPetTest() {

        petEndpoint.getPetById(petId)
                .body("id", is(petId))
                .body("name", is(petModel.getName()))
                .body("status", is(petModel.getStatus()));


    }


    @Test
    public void getPetByStatusTest() {
        for (Status status : Status.values()) {
            petEndpoint.getPetByStatus(status);
        }
    }

    @Test
    public void test5_deletePetTest() {

        petEndpoint.deletePet(petId).log();

    }

//    public void getRandomPetId() {
//        Random random = new Random();
//        this.randomPetId = random.nextInt(100);
//    }

//    public int getRandomPetId() {
//        Random random = new Random();
//        int randomPetId = random.nextInt(100);
//
//        return this.randomPetId = randomPetId;
//    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}
