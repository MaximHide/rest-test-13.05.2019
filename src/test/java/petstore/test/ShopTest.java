package petstore.test;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.*;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import petstore.endpoint.ShopEndpoint;
import petstore.models.ShopModel;



import static org.hamcrest.core.Is.is;
@RunWith(SerenityRunner.class)
@Concurrent
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopTest {

    private ShopEndpoint shopEndpoint = new ShopEndpoint();

    private ShopModel shopModel;

    @Before
    public void setUp(){
        shopModel = new ShopModel(
                9,
                777,
                1,
//                "2019-05-31T16:59:07.189Z",
                "sell",
                true
        );
    }

    @Test
    public void test1_createOrderTest() {

        shopEndpoint.createOrder(shopModel);//.log().all();

    }

    @Test
    public void test2_getOrderByIdTest() {



        shopEndpoint.getOrderById(shopModel.getId())
                .body("id",is(shopModel.getId()))
                .body("petId",is(shopModel.getPetId()))
                .body("quantity",is(shopModel.getQuantity()))
//                .body("date", is (shopModel.getDate()))
                .body("status",is(shopModel.getStatus()))
                .body("complete",is(false));
    }


  @Test
   public void test3_deleteOrderByOrderIdTest() {

        shopEndpoint.deleteOrderById(shopModel.getId());//.log().all();
    }
}
