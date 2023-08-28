package APITest;

import api.common.BaseClass;
import api.endpoints.MockAPI;
import api.endpoints.WeatherAPIS;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Files;

public class MokeAPITest extends BaseClass {
    MockAPI mockAPI;

    @BeforeTest
    public void init(){
        mockAPI = new MockAPI();
    }

    @Test(priority = 1)
    public void testCreatUserDetails(){
        test = report.createTest("CreateEmployeeData");
        mockAPI.createUserWithJsonFile();
        log("User Created or user verify");
  }

    @Test(priority = 2)
    public void testGetUserDetails(){
        test = report.createTest("GetEmployeeData");
        mockAPI.getUserDetails1();
        log("User Created or user verify");
    }

    @Test(priority = 3)
    public void testUpdateUser(){
        test = report.createTest("UpdateEmployeeData");
        mockAPI.updateUserDetails();
        log("User updates or user verify");
    }


    @Test(priority = 3)
    public void testDeleteUser(){
        test = report.createTest("DeleteEmployeeData");
        mockAPI.DeleteUserDetails();
        log("User deleted or user verify");
    }

}
