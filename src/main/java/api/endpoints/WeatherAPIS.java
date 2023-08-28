package api.endpoints;

import api.common.BaseClass;
import com.aventstack.extentreports.Status;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import website.common.BaseClass1;
import website.utils.logs.Log;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static api.common.BaseClass.log;
import static io.restassured.RestAssured.given;

public class WeatherAPIS {
    Properties pro = new Properties();


//    public Response getUser(int id) {

    public Response getWeatherDetailes() {
        try {
            pro.load(new FileInputStream("src/main/java/api/config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response res =
                given().log().all()
                        .header("X-RapidAPI-Key", pro.getProperty("weatherToken"))
                        .header("X-RapidAPI-Host", pro.getProperty("host"))
                        .queryParam("q", "53.1,-0.13")
                        .when().get(Routes.realtime);
//                        .then().log().body();

        return res;
    }


    public Response getWeatherForecast() {
        try {
            pro.load(new FileInputStream("src/main/java/api/config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response res =
                given().log().all()
                        .header("X-RapidAPI-Key", pro.getProperty("weatherToken"))
                        .header("X-RapidAPI-Host", pro.getProperty("host"))
                        .queryParams("q", "London", "day", "3")
                        .when().get(Routes.forecast);

        return res;
    }

    public Response getTimeZone() {
        try {
            pro.load(new FileInputStream("src/main/java/api/config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response res =
                given().log().all()
                        .header("X-RapidAPI-Key", pro.getProperty("weatherToken"))
                        .header("X-RapidAPI-Host", pro.getProperty("host"))
                        .queryParams("q",pro.getProperty("locationName"))
                        .when().get(Routes.timeZone);

        return res;
    }

    public void WeatherDetailsValidation1(String object, String keyValue) {
        Response resp = getWeatherDetailes();
        resp.then().log().all().header("X-RapidAPI-Version", "1.2.8");
//        System.out.println(resp.getBody().asString());
//    JsonPath jsonPath=new JsonPath(resp.getBody().asString());
//    Log.info(jsonPath.getString("location.name"));
//    }
        if (resp.statusCode() == 200) {
            Log.info("The reponse status code is 200");
        } else {
            Log.info("The reponse status code is not 200");
        }
//        if (resp.header("X-RapidAPI-Version")=="1.2.8"){
//            Log.info("The reponse header have this version");
//        }else {
//            Log.info("The reponse header don't have this version");
//        }

        JSONObject jsonObject = new JSONObject(resp.getBody().asString());
        JSONObject jsonJO = jsonObject.getJSONObject(object);
        for (String key : jsonJO.keySet()) {
            Object value = jsonJO.get(key);

            if (key.equals(keyValue)) {
                System.out.println(key + " : " + value);
                Log.info(key + " : " + value);
            }
        }
    }

    public void WeatherDetailsValidation2(String object) {
        Response resp = getWeatherForecast();
        resp.then().log().all();
        JSONObject jsonObject = new JSONObject(resp.getBody().asString());
        JSONObject jsonObj = jsonObject.getJSONObject(object);
//            JsonPath jsonPath=new JsonPath(resp.getBody().asString());
//            System.out.println(jsonPath.getString("forecast.forecastday"));

//            for (String key : jsonJO.keySet()) {
//                Object value = jsonJO.get(key);
//
//                if (key.equals("last_updated")) {
//                    System.out.println(key + " : " + value);
//                    Log.info(key + " : " + value);
//                }
//            }

        JSONArray arr = jsonObj.getJSONArray("forecastday");
        jsonObj.names();
        for (int i = 0; i <= arr.length() - 1; i++) {
//                System.out.println(arr.get(i).toString());
            System.out.println(arr.get(i) + "===");
            JSONObject singleObj = arr.getJSONObject(i);
            JSONArray allElement = singleObj.names();
            System.out.println(allElement + " *");
                   saveResponseIntoJsonFile(allElement.toString());
                for (int j = 0; j <= allElement.length() - 1; j++) {
                    if (allElement.get(1).equals("astro")) {
                        saveResponseIntoJsonFile(singleObj.get(allElement.getString(j)).toString());
                    System.out.println(singleObj.get(allElement.getString(j)));
                }
            }

        }


    }

    public void getTimeZoneValidation(){
        Response resp=getTimeZone();
        resp.then().log().all();
//        JsonPath jsonObject=new JsonPath(resp.asString());
////        String jsonObject1=jsonObject.getString("location.name");
////        System.out.println(jsonObject1);
//        Map<String, Object> locationData = jsonObject.getMap("location");
//        for (Map.Entry<String, Object> entry : locationData.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//                      System.out.println(key + ": " + value);}

        JSONObject jsonObject=new JSONObject(resp.asString());
        JSONObject jsonObject1=jsonObject.getJSONObject("location");
        for (String key: jsonObject1.keySet()){
            Object value=jsonObject1.get(key);

            System.out.println(key+" : "+value);}


    }

    public void saveResponseIntoJsonFile(String responseData ){

        String filePath = "src/main/resources/api_response.json";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            writer.append(responseData).append(System.lineSeparator());
            System.out.println("API response has been written to '" + filePath + "'");
            log("API response has been written to '" + filePath + "'" +responseData+ "'");
        } catch (IOException e) {
            System.err.println("Error writing API response to file: " + e.getMessage());
            log("Error writing API response to file: " + e.getMessage());
        }
    }


    public void schemaValidation(Response resp,String filename){
//        Response resp = getWeatherForecast();
        resp.then().log().all().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(filename));
//        JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchemaValidator.json");
            log("API response has been validated against the schema.");
        System.out.println("API response has been validated against the schema.");
    }

}