package petstore.test;

import org.junit.*;
import petstore.endpoint.ShopEndpoint;
import petstore.models.ShopModel;

import java.util.Random;

public class ShopTest {


    private Random random = new Random();
    private int petId;
    private ShopEndpoint shopEndpoint = new ShopEndpoint();
    private int randomOrderId ;
    private PetStoreTest petStoreTest = new PetStoreTest();


    @Before
    public void findPet() {

        this.petId = random.nextInt(100);
        this.randomOrderId = random.nextInt(10);
        System.out.println("pet id = " + petId +" " +"order id = " +  randomOrderId);

        petStoreTest.setPetId(petId);
        if (shopEndpoint.getStatusCodeByPetId(petId) == false) {

            petStoreTest.createNewPetTest();// Just for create new pet if his is not found

        }


    }

    @Test
    public void createOrder() {

        System.out.println("pet id = " + petId +" " +"order id = " +  randomOrderId);
        ShopModel shopModel = new ShopModel(
                randomOrderId,
                petId,
                1,
                "2019-05-31T16:59:07.189Z",
                "sell",
                false
        );

        shopEndpoint.createOrder(shopModel).log().all();
        System.out.println("xz");
    }

    @Test
    public void getOrderByIdTest() {

        shopEndpoint.getOrderById(randomOrderId).log().all();
    }

        @After
    public void deleteOrderByOrderIdTest() {
            System.out.println("pet id = " + petId +" " +"order id = " +  randomOrderId);
        shopEndpoint.deleteOrderById(randomOrderId).log().all();
    }
}
