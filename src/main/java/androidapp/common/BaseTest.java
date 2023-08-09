package androidapp.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTest {
  public   AndroidDriver driver;
    Properties prop;
    AppiumDriverLocalService service;

    public   ExtentSparkReporter spark;
    public  ExtentReports report;
    public static  ExtentTest test;

//    @BeforeSuite
//    public void init() throws IOException {
//        SimpleDateFormat d=new SimpleDateFormat(" dd-MM-yyyy_hh_mm_ss_a_z");
//        String d1=d.format(new Date());
//
//        spark=new ExtentSparkReporter(new File("./Extent-Reports/AndroidReport/DemoTest"+d1+".html"));
//        report=new ExtentReports();
//        report.attachReporter(spark);
//        spark.config().setDocumentTitle("GoRestAPITesting");
//        spark.config().setTheme(Theme.DARK);
//        spark.config().setReportName("Gorest");
//        report.setSystemInfo("Environment", "TestEnv");
//        report.setSystemInfo("TesterName", "Aaysha");
//    }



    public void configureAppium() throws IOException {
//        String appiumServerPath = "C:\\Users\\LeGioN\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
//
//        // Create an AppiumServiceBuilder
//        AppiumServiceBuilder builder = new AppiumServiceBuilder();
//        builder.usingAnyFreePort();
//        builder.withAppiumJS(new File(appiumServerPath));
//
//        // Start the Appium server
//         service = AppiumDriverLocalService.buildService(builder);
//        service.start();
//
//        // Get the server URL
//        String serverUrl = service.getUrl().toString();
//        System.out.println("Appium server started at: " + serverUrl);
//
//        // ... Your code for running tests using the Appium server ...
//
//        // Stop the Appium server
//        service.stop();
//        System.out.println("Appium server stopped");
//        AppiumDriverLocalService service=new AppiumServiceBuilder().withAppiumJS(new File("//Users//LeGioN//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
//                .withIPAddress("127.0.0.1").usingPort(4723).build();
//        service.start();
        prop =new Properties();

        FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/androidapp/config"+"/android.properties");
        prop.load(ip);
        UiAutomator2Options options=new UiAutomator2Options();
        options.setDeviceName(prop.getProperty("deviceName"));
        options.setApp(prop.getProperty("appPath"));
        options.setApp("D:\\AppiumApp_10-05-2022\\DemoMobile_Automation\\src\\main\\resources\\ApiDemos-debug.apk");
//         this line is use for convert string to ip address
        URL url = new URL("http://0.0.0.0:4723/wd/hub");
        driver=new AndroidDriver(url,options);
    }

    public void longPressAction(WebElement ele){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),"duration",2000
        ));
    }
    public void scrollAction(WebElement ele) {
//          (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
//                "left", 100, "top", 100, "width", 200, "height", 200,
//                "direction", "down",
//                "percent", 3.0
//        ));
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "endX", 813,
                "endY", 737
        ));
    }

    public void swipeAction(WebElement ele,String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)ele).getId(),
                "direction", direction,
                "percent", 0.25
        ));
    }

//    @AfterClass
    private void tearDown(){
        driver.quit();
//        service.stop();
    }

    @AfterSuite
    public void saveReport() {
        report.flush();
    }


}
