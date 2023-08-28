package APITest;

import api.common.BaseClass;
import api.endpoints.UserAPI;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import api.payload.Users;

public class UserAPITest extends BaseClass {
	UserAPI userapi=new UserAPI();
	Users user=new Users();
	Faker faker=new Faker();
	String username, email, gender="male", status="active";
	int userid;
	
	
	@BeforeClass
	public void generateUserdata() {
		username=faker.name().fullName();
		email=faker.internet().safeEmailAddress();
		user.setName(username);
		user.setEmail(email);
		user.setGender(gender);
		user.setStatus(status);
	}
	
	@Test(priority=1)
	public void testCreateUser() {
	Response resp=userapi.createUser(user);
		test=report.createTest("CreateUserAPI");

	System.out.println(resp.body().asString());
	if(resp.statusCode()==201) {
		test.log(Status.PASS, "The reponse status code is 201");
	}else {
		
		test.log(Status.FAIL, "the reponse status code is not 201");
	}
	
	JsonPath js=new JsonPath(resp.asString());
	userid=js.get("id");
	
	if(js.getString("name").matches(username)) {
		test.log(Status.PASS, "New user is generated");
	}else {
		
		test.log(Status.FAIL, "New userid is not generated ");
	}
	
	if(userid>0) {
		test.log(Status.PASS, "username created is correct");
	}else {
		
		test.log(Status.FAIL, "username created is correct");

	}
	
}	
	@Test(priority=2)
	public void testGetUser() {
		Response res=userapi.getUser(userid);

		test=report.createTest("GetUserAPI");

		System.out.println(res.statusCode());

		System.out.println(res.getBody().asString());
		if(res.statusCode()==200) {
			test.log(Status.PASS, "The response status code is 200");
		}else {
			test.log(Status.FAIL, "the response status code is not 200");
			;
		}
	}

	@Test(priority = 3)
	public void verifySchemaValidation(){
		Response res=userapi.getUser(userid);
		userapi.schemaValidation(res);
	}


	//@Test(priority=3)
	public void testUpdatetUser() {
		email=faker.internet().safeEmailAddress();
		user.setEmail(email);
		Response res=userapi.updateUser(user,userid);
				test=report.createTest("UpdateUserAPI");
		
		if(res.statusCode()==200) {
			test.log(Status.PASS, "The reponse status code is 200");
		}else {
			
			test.log(Status.FAIL, "the reponse status code is not 200");
		}
		
		
	}
	
	//@Test(priority=4)
	public void testDeleteUser() {
		email=faker.internet().safeEmailAddress();
		user.setEmail(email);

		Response res=userapi.deleteUser(userid);
				test=report.createTest("DeleteUserAPI");
		System.out.println(res.statusCode());

		if(res.statusCode()==204) {
			test.log(Status.PASS, "The reponse status code is 204");
		}else {
			
			test.log(Status.FAIL, "the reponse status code is not 204");
		}
	}


}
