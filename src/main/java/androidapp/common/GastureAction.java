package androidapp.common;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class GastureAction extends BaseTest{
//    AndroidDriver driver;
//    Properties prop;
//    private AppiumDriverLocalService service;
//    private AppiumServiceBuilder builder;
//    private DesiredCapabilities cap;


   // @Test
    public void appiumTest() throws MalformedURLException ,IOException {
//        AppiumDriverLocalService service=new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\LeGioN\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//                .withIPAddress("0.0.0.0").usingPort(4723).build();
//        service.start();
//        C:\\Users\\LeGioN\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js
//        "C://Users//LeGioN//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"
            //Set Capabilities
//            cap = new DesiredCapabilities();
//            cap.setCapability("noReset", "false");
//
//            //Build the Appium service
//            builder = new AppiumServiceBuilder();
//            builder.withIPAddress("0.0.0.0");
//            builder.usingPort(4723);
//            builder.withCapabilities(cap);
//            builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
//            builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
//
//            //Start the server with the builder
//            service = AppiumDriverLocalService.buildService(builder);
//            service.start();

        prop =new Properties();

        FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources"+"/android.properties");
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability();
        prop.load(ip);
        UiAutomator2Options options=new UiAutomator2Options();
        options.setDeviceName(prop.getProperty("deviceName"));
        options.setApp(prop.getProperty("appPath"));
        options.setApp("D:\\AppiumApp_10-05-2022\\DemoMobile_Automation\\src\\main\\resources\\ApiDemos-debug.apk");
//         this line is use for convert string to ip address
        URL url = new URL("http://0.0.0.0:4723/wd/hub");
         driver=new AndroidDriver(url,options);

//         driver.quit();
//         service.stop();
    }

    @Test(priority = 2)
    public  void WiFiSettingName() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.CheckBox")).click();
        Thread.sleep(2000);
        DeviceRotation landscape= new DeviceRotation(0,0,90);
        driver.rotate(landscape);
        driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Aaysha");
        Thread.sleep(3000);
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        ((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        Thread.sleep(4000);
        ((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));
    }

  //  @Test(priority = 3)
    public void longpress() throws InterruptedException {
        Thread.sleep(4000);
      driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(2000);
      driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Expandable Lists\"]")).click();
        Thread.sleep(2000);
      driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        Thread.sleep(2000);
      WebElement ele= driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[2]/android.widget.ExpandableListView/android.widget.TextView[1]"));
//        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
//                "elementId", ((RemoteWebElement) ele).getId(),"duration",2000
//        ));
        longPressAction(ele);
        Thread.sleep(2000);
        String menuText=driver.findElement(By.id("android:id/title")).getText();
        Assert.assertEquals(menuText,"Sample action");
        Assert.assertTrue(driver.findElement(By.id("android:id/alertTitle")).isDisplayed());
    }

//    @Test(priority = 4)
    public void scroller() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
        Thread.sleep(4000);
    }


   // @Test(priority = 5)
    public  void swipeDemo() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"1. Photos\"]")).click();
        Thread.sleep(2000);
        //check wather it's true or not
        WebElement firstImg=  driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
      String attri=  driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable");
      Assert.assertEquals(attri,"true");
      //swipe
        swipeAction(firstImg,"left");

        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"),"false");
    }

//    @Test(priority = 6)
    public void dragAndDrop() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        Thread.sleep(2000);
        WebElement source=driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) source).getId(),
                "endX", 813,
                "endY", 737
        ));
        Thread.sleep(000);
        String resultText=driver.findElement(By.id("com.example.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(resultText,"Dropped!");
        Thread.sleep(2000);
    }

}
