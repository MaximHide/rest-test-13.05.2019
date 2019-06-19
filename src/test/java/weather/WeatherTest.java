package weather;

import io.restassured.RestAssured;

import io.restassured.response.ValidatableResponse;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;


import static io.restassured.RestAssured.*;

@RunWith(SerenityRunner.class)
public class WeatherTest {

    @BeforeClass
     public void url() {

        RestAssured.baseURI = "https://pinformer.sinoptik.ua";

    }

    @Test
       public void getWeatherByCityTest() {

        String cityName = "Kharkiv";


        basePath = "search.php";
        ValidatableResponse response = SerenityRest.given()
                .param("lang", "ua")
                .param("return_id", 1)
                .param("q", cityName)
                .log().uri()
                .get()
                .then()
//                .log().all()
                .statusCode(200);
        String cityInfo = response.extract().asString();


        System.out.println("Температура в " + cityName+ "= " + getWeather(getCityId(cityInfo)));


    }


    private String getCityId(String cityDate) {

        String response[] = cityDate.split("\\|");
        String cityId = response[response.length - 1];

        return cityId;

    }

    private String getWeather(String cityId) {


        basePath = "pinformer4.php";
        ValidatableResponse response = SerenityRest.given()
                .param("type", "js")
                .param("lang", "ua")
                .param("id", cityId)
                .log().uri()
                .get()//.log().uri().get()
                .then()
//                        .log().all()

                .statusCode(200);


        JSONObject jsonObject = new JSONObject(response.extract().asString());

        return jsonObject.getString("{temp}");
    }

}
