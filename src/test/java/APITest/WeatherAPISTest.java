package APITest;

import api.common.BaseClass;
import api.endpoints.WeatherAPIS;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import website.utils.logs.Log;

public class WeatherAPISTest extends BaseClass {
    WeatherAPIS weatherAPIS;
    @BeforeClass
    public void init(){
        weatherAPIS = new WeatherAPIS();
    }

    @Test(priority = 1)
    public void testWeatherDetails() {
        test = report.createTest("GetWeatherDetailsAPI");
//        weatherAPIS = new WeatherAPIS();
        weatherAPIS.WeatherDetailsValidation1("location", "name");
        log("validate Weather API Response");
        Log.info("validate Weather API Response");
    }

    @Test(priority = 2)
    public void VerifyWeatherSchemavalidation(){
        test = report.createTest("VerifySchemaValidationOfWeatherDetails");
        Response resp =weatherAPIS. getWeatherDetailes();
        weatherAPIS.schemaValidation(resp, "WeatherSchemaValidation.json");
       log("verify Weather Schema validation of Response");
    }

    @Test(priority = 3)
    public void testWeatherDetails2() {
        test = report.createTest("GetWeatherDetailsAPI");
//        weatherAPIS = new WeatherAPIS();
        weatherAPIS.WeatherDetailsValidation2("forecast");
      log("validate Forecast API Response");
        Log.info("validate Response");
    }

    @Test(priority = 4)
    public void VerifyforecastSchemavalidation(){
        test = report.createTest("VerifySchemaValidationOfForecast");
        Response resp =weatherAPIS.getWeatherForecast();
        weatherAPIS.schemaValidation(resp, "ForcastSchemaValidation.json");
        log("verify forecast Schema validation of Response");
    }

    @Test(priority = 5)
    public void verifyTimeZone(){
        test=report.createTest("verifyTimeZone");
        weatherAPIS.getTimeZoneValidation();
        log("verify Time Zone Response");
    }

}
