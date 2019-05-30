package petstore.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import petstore.endpoint.PetEndpoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TegModel;

public class PetUpdateTest {

    private PetEndpoint petEndpoint = new PetEndpoint();

    private PetModel petModel = new PetModel(
            888,
            "new name for pet",
            new CategoryModel(),
            new String[]{},
            new TegModel[]{new TegModel()},
            "AVAILABLE"
    );

    @Before
    public void precondition() {

        petEndpoint.createNewPet(petModel).statusCode(200);
    }

    @After
    public void postcondition(){
        petEndpoint.deletePet(petModel.getId()).statusCode(200);
    }

    @Test
    public void updateTest(){
         petModel.setStatus("SOLD");
        petModel.setName("xaxa");

        petEndpoint.editPet(petModel).statusCode(200);


    }


}
