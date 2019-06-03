package petstore.test;

import org.junit.*;
import org.junit.runner.RunWith;
import petstore.endpoint.ShopEndpoint;
import petstore.models.ShopModel;

import java.util.Random;

import static org.hamcrest.core.Is.is;

public class ShopTest {


    private Random random = new Random();
    private int petId;
    private ShopEndpoint shopEndpoint = new ShopEndpoint();
    private int randomOrderId;
    private PetStoreTest petStoreTest = new PetStoreTest();


    @Before
//    @Test
    public  void findPetTest() {
        System.out.println("findPet :");
        this.petId = random.nextInt(1000)+100;
        this.randomOrderId = random.nextInt(10);
        petStoreTest.setPetId(petId);

       if ( shopEndpoint.getStatusCodeByPetId(petId) == false) {

           petStoreTest.createNewPetTest();
       }
    }

    @Test
    public void createOrderTest() {

        ShopModel shopModel = new ShopModel(
                randomOrderId,
                petId,
                1,
//                "2019-05-31T16:59:07.189Z",
                "sell",
                false
        );

        System.out.println(shopModel.getQuantity() + "  " + shopModel.getStatus()) ;

        shopEndpoint.createOrder(shopModel);//.log().all();

    }

    @Test
    public void getOrderByIdTest() {

        ShopModel shopModel = new ShopModel();

        shopEndpoint.getOrderById(randomOrderId)
                .body("id",is(randomOrderId))
                .body("petId",is(petId))
                .body("quantity",is(shopModel.getQuantity()))
//                .body("date", is ("2019-05-31T16:59:07.189Z"))
                .body("status",is(shopModel.getStatus()))
                .body("complete",is(false));
    }

    @After
//  @Test
   public void deleteOrderByOrderIdTest() {

        shopEndpoint.deleteOrderById(randomOrderId);//.log().all();
    }
}
