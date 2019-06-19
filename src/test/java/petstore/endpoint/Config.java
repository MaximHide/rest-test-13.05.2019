package petstore.endpoint;

public class Config {

    final static String BASE_URI = "http://petstore.swagger.io/v2/";
   public final static String GET_PET_BY_ID = "pet/{petId}";
    final static String GET_PET_BY_STATUS = "pet/findByStatus";
    final static String CREATE_PET = "pet";
    final static String UPDATE_PET = "pet";
    final static String BASE_SHOP_URL = "http://petstore.swagger.io/v2/store/";
    final static String CREATE_ORDER = "order/";
    final static String FIND_PET = "http://petstore.swagger.io/v2/pet/";

    final static String UPLOAD_PHOTO = BASE_URI + "pet/{petId}/uploadImage";



}
