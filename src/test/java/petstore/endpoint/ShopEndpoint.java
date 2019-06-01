package petstore.endpoint;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import petstore.models.ShopModel;


public class ShopEndpoint {

    private RequestSpecification given() {
        return RestAssured.given()
                .baseUri(Config.BASE_SHOP_URL)
                .contentType("application/json")
                .log().uri();
    }


    public ValidatableResponse getOrderById(int orderId) {

        return given()
                .get(Config.CREATE_ORDER + orderId)
                .then()
                .statusCode(200).log().all();
    }

    public boolean getStatusCodeByPetId(int petId) {

        RestAssured.baseURI = Config.FIND_PET + petId;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();
               if (response.getStatusCode() == 200) {
            return true;
        }
        return false;


    }


    public ValidatableResponse createOrder(ShopModel shopModel) {

        return given()
                .body(shopModel)
                .post(Config.CREATE_ORDER)
                .then()
                .statusCode(200);
    }

    public ValidatableResponse deleteOrderById(int orderId) {

        return given()
                .delete(Config.CREATE_ORDER + orderId)
                .then()
                .statusCode(200);
    }

}



