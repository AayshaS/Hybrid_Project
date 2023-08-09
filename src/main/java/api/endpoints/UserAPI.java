package api.endpoints;

import io.restassured.response.Response;
import api.payload.Users;

import java.io.FileInputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserAPI {
    Properties pro=new Properties();

    public Response createUser(Users user) {
        try {
            pro.load(new FileInputStream("src/main/java/api/config/config.properties"));
        }catch(Exception e) {
            e.printStackTrace();
        }
        Response res=
                given().log().all()
                        .header("Content-Type","application/json")
                        .header("Authorization","Bearer "+pro.getProperty("token"))
                        .body(user)
                        .when().post(Routes.posturl)
                ;
        System.out.println(Routes.posturl);
        return res;

    }

    public Response getUser(int id) {
        try {
            pro.load(new FileInputStream("src/main/java/api/config/config.properties"));
        }catch(Exception e) {
            e.printStackTrace();
        }
        Response res=
                given().log().all()
                        .header("Content-Type","application/json")
                        .header("Authorization","Bearer "+pro.getProperty("token"))
                        .pathParam("userid", id)
                        .when().get(Routes.geturl)
                ;
        return res;

    }

    public Response updateUser(Users user, int id) {
        try {
            pro.load(new FileInputStream("src/main/java/api/config/config.properties"));
        }catch(Exception e) {
            e.printStackTrace();
        }
        Response res=
                given().log().all()
                        .header("Content-Type","application/json")
                        .header("Authorization","Bearer "+pro.getProperty("token"))
                        .pathParam("userid", id)
                        .body(user)
                        .when()
                        .patch(Routes.updateurl)
                ;
        return res;

    }
    public Response deleteUser( int id) {
        try {
            pro.load(new FileInputStream("src/main/java/api/config/config.properties"));
        }catch(Exception e) {
            e.printStackTrace();
        }
        Response res=
                given().log().all()
                        .header("Content-Type","application/json")
                        .header("Authorization","Bearer "+pro.getProperty("token"))
                        .pathParam("userid", id)
                        .when()
                        .delete(Routes.deleteurl)
                ;
        return res;

    }

}
