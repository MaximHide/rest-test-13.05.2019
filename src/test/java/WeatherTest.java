import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.json.JSONObject;
import org.junit.Test;


import static io.restassured.RestAssured.get;


public class WeatherTest {


    @Test()
    public void getWeatherByCityTest() {


        RestAssured.baseURI = "https://pinformer.sinoptik.ua/search.php";
        ValidatableResponse response = RestAssured.given()
                .param("lang", "ua")
                .param("return_id", 1)
                .param("q", "Lviv")
                .get()
                .then()
//                .log().all()
                .statusCode(200);
        String cityInfo = response.extract().asString();

        System.out.println("Температура во Львове = " + getWeather(getCityId(cityInfo)));


    }


    private String getCityId(String cityDate) {
//        Response response = get("https://pinformer.sinoptik.ua/search.php?lang=ua&return_id=1&q=Lviv");
//        System.out.println(response.asString());
        String response[] = cityDate.split("\\|");
        String cityId = response[response.length - 1];

        return cityId;

    }

    private String getWeather(String cityDate) {


        RestAssured.baseURI = ("https://pinformer.sinoptik.ua/pinformer4.php");

        ValidatableResponse response = RestAssured.given()
                .param("type", "js")
                .param("lang", "ua")
                .param("id", cityDate)
                .get()//.log().uri().get()
                .then()
//                        .log().all()

                .statusCode(200);


        Response resp = get("https://pinformer.sinoptik.ua/pinformer4.php?type=js&lang=ua&id=" + cityDate);

        JSONObject jsonObject = new JSONObject(resp.getBody().asString());
        String temp = jsonObject.getString("{temp}");
//        System.out.println("Температура во Львове = " + temp);

        return temp;
    }

}
