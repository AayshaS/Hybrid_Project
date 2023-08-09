package website.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import website.utils.extentreports.ExtentTestManager;
import website.utils.logs.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static website.utils.extentreports.ExtentTestManager.startTest;


public class BaseClass1 {
    public  ExtentSparkReporter spark;
    public   ExtentReports report;
    public static ExtentTest test;

    //    static ExtentReports extent;
    public static WebDriver driver;
    public static Properties prop;
    private String bName, className;
    public BaseClass1() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/website/config/" + "config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize() {
        String browser = prop.getProperty("browser");
        System.out.println("Browser is: " + browser);
        if (browser.equals("chrome")) {
            EdgeOptions option = new EdgeOptions();
            option.addArguments("--disable-notifications");
            System.setProperty("webdriver.chrome.whitelistedIps", "");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/test/resources/driver/" + "chromedriver.exe");
            driver = new EdgeDriver(option);
        }
        else if (browser.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+ "src/test/resources/driver/"+"geckodriver.exe");
                driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//        pageLoadWait();
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.get(prop.getProperty("URL"));
        driver.get(prop.getProperty("url"));
    }



//    public void setup(Method method) {
//        startTest(method.getName(), "Testing Start");
//        Log.info("browser Started");
//        initialize();
//    }

    @BeforeSuite
    public void initialSetup() {
        // TODO Auto-generated method stub
//		SimpleDateFormat d=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//		String d1=d.format(new Date());
        Date currentDateTime = new Date();;
        DateFormat dateFormat = new SimpleDateFormat(" dd-MM-yyyy_hh_mm_ss_a");
        String d1 = dateFormat.format(currentDateTime);
        spark=new ExtentSparkReporter(new File("./Extent-Reports/WebsiteReport/sauceTest"+d1+".html"));
        report=new ExtentReports();
        report.attachReporter(spark);
        spark.config().setDocumentTitle("GoRestAPITesting");
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("Gorest");
        report.setSystemInfo("Environment", "TestEnv");
        report.setSystemInfo("TesterName", "Aaysha");
    }

    @AfterSuite
    public void saveReport() {
        report.flush();
    }

    public void loadUrl(String url) {
        driver.get(url);
//        log.info("Loading URL " + url);
    }

    //@BeforeMethod
    public void logBefore(Method m,String desc) {
        test =startTest(m.getName(),desc);
        test.log(Status.INFO, m.getName() + " test Started");
        Log.info("======== START  " + className + " " + m.getName());
    }

    public static void pageLoadWait(){
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    public void openNewTab(){
//        driver.get("http://test.wellbeingapp.in/#");
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void openNewWindow(){
//        driver.get("http://test.wellbeingapp.in/#");
        driver.switchTo().newWindow(WindowType.WINDOW);
    }

    public Set<String> getWindows() {

        return driver.getWindowHandles();
    }

    public String getCurrentWindow() {
        return driver.getWindowHandle();
    }

    public void switchToWindow(String windowId) {
        driver.switchTo().window(windowId);
    }

    public void closeCurrentWindow(String windowId) {
        driver.switchTo().window(windowId);
        driver.close();
        for (String w : getWindows()) {
            if (!w.equals(windowId)) {
                switchToWindow(w);
//                log.info("Switched to Base Page");
                break;
            }
        }
    }

    protected void closeDriver(){
        if(driver!= null) {
            assert driver != null;
            driver.quit();
//            driver.close();
        }
    }
}
