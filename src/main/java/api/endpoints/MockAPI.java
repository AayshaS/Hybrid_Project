package api.endpoints;

import api.common.BaseClass;
import api.payload.Employee;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import website.utils.logs.Log;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;
import static api.common.BaseClass.log;
import static api.common.Utility.saveResponseIntoJsonFile;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MockAPI extends BaseClass {
    Properties pro = new Properties();
//    UserPOJO user=new UserPOJO();
    Faker faker;
    String username ,useremail,gender="female",status="active";
    String userid;
     RequestSpecification requestSpec;
     ResponseSpecification responseSpec;
    Response res;
    String  userData;

    public  RequestSpecification requestSpec() {
        requestSpec = new RequestSpecBuilder().
                addHeader("Content-Type","application/json")
                .build();
return requestSpec;
    }

    public  ResponseSpecification responseSpec() {
        responseSpec= new ResponseSpecBuilder()
                .expectResponseTime(Matchers.lessThan(2000L))
                .expectStatusCode(Matchers.oneOf(200,201,204,404))
                .expectContentType(ContentType.JSON)
//                .expectHeader("Content-Type",equalTo("application/json"))
                .build();
        return responseSpec;
    }

    public Response createNewUserResponse(String data) {

        try {
            pro.load(new FileInputStream("src/main/java/api/config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response res =
        given()
                .spec(requestSpec())
                .body(data)
                .when().log().all()
                .post(Routes.employeeBaseUrl);
        return res;
    }

    public Response getUserResponse(String id) {

        Response res =
    given().log().all()
            .spec(requestSpec)
            .pathParam("userid", id)
            .when().get(Routes.mokeGetUrl);
//            .then().log().body();

        return res;
}

public Response updateUserResponse( String id){
    HashMap<String, Object> data=new HashMap<String, Object>();
    data.put("email", "Shan@gmail.com");
    Response res =
            given().log().all()
                    .spec(requestSpec)
                    .pathParam("userid", id)
                    .body(data)
                    .when().patch(Routes.mokeUpdateUrl);
        return res;
}

    public Response deleteUserResponse(String id) {
        try {
            pro.load(new FileInputStream("src/main/java/api/config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response res =
                given().log().all()
                        .spec(requestSpec)
                        .pathParam("userid", id)
                        .when().delete(Routes.mokeDeleteUrl);
//            .then().log().body();

        return res;
    }

    public void extractResponse(String response){
        JSONObject js=new JSONObject(response);
        JSONArray arr=js.getJSONArray("Employee");
        for(int i=0;i<arr.length();i++) {
            System.out.println(arr.length());
            JSONObject singleObj=arr.getJSONObject(i);
        if (singleObj.getString("id")=="Bushra")
//			System.out.println(singleObj.getString("name"));
//			System.out.println(singleObj.getString("gender"));
//			System.out.println(singleObj.getString("email"));
//
            System.out.println("===================================");
//
//            JSONArray allElement =	singleObj.names();
//            for(int j=0;j<arr.length();j++) {
//
//                System.out.println(singleObj.get(allElement.getString(j)));
//
//            }

        }

    }


    public void createUserWithJsonFile() {
//        try {
//
//            byte arr[] = Files.readAllBytes(Paths.get("./config/Employee.json"));
//              userData = new String(arr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        res = createNewUserResponse(Employee.empdata1());
        log("Pass payload");
        res.then().spec(responseSpec()).log().body().extract().response();
        saveResponseIntoJsonFile(res.asString());
        log("verify create Response body");

        JsonPath js=new JsonPath(res.asString());
        userid=js.get("id").toString();
    }

    public void getUserDetails1(){
        res=getUserResponse(userid);
        if (res!=null){
        res.then().spec(responseSpec()).log().body();
        saveResponseIntoJsonFile(res.asString());
            log("verify get Response body");
        }else {
      log("Response Body not verified");
        }
        if(res.statusCode()==200) {
            test.log(Status.PASS, "The response status code is 200");
        }else {
            test.log(Status.FAIL, "the response status code is not 200");
            ;
        }

    }

    public void updateUserDetails(){
//        String emailId=faker.internet().safeEmailAddress();
       res=updateUserResponse(userid);
        res.statusCode();
        res.then().log().body();
        if(res.statusCode()==200) {
            log("The reponse status code is 200");
        }else {

            log("the reponse status code is not 200");
        }

    }

    public void DeleteUserDetails(){
        res=deleteUserResponse(userid);
        if (res!=null){
            res.then().spec(responseSpec()).log().body();
            saveResponseIntoJsonFile(res.asString());
            System.out.println(res.statusCode());
            log("verify get Response body");
        }else {
            log("Response Body not verified");
        }
        if(res.statusCode()==200) {
            test.log(Status.PASS, "The response status code is 200");
        }else {
            test.log(Status.FAIL, "the response status code is not 200");
            ;
        }

    }

}
