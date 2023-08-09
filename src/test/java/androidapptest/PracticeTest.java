package androidapptest;

import androidapp.common.BaseTest;
import androidapp.pageObjectClass.Practice;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PracticeTest extends BaseTest {
Practice practice;
//    public static ExtentTest test;
//    public static ExtentReports report  =new ExtentReports();

    @BeforeSuite
    public void ini() throws IOException {
        SimpleDateFormat d=new SimpleDateFormat(" dd-MM-yyyy_hh_mm_ss_a_z");
        String d1=d.format(new Date());

        spark=new ExtentSparkReporter(new File("./Extent-Reports/AndroidReport/DemoTest"+d1+".html"));
        report=new ExtentReports();
        report.attachReporter(spark);
        spark.config().setDocumentTitle("GoRestAPITesting");
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("Gorest");
        report.setSystemInfo("Environment", "TestEnv");
        report.setSystemInfo("TesterName", "Aaysha");
    }

    @BeforeClass
    public void init() throws IOException {
        test=report.createTest("Open Appium");
        configureAppium();
        practice =new Practice();
        PageFactory.initElements(driver, practice);

    }

    @Test(priority = 1)
    public void alertDailogBox() throws InterruptedException {

        practice.openApp();
        practice.okCancelAction();
//        practice.okCancelActionWithLongText();
        test=report.createTest("AlertBoxTest Completed");
//        Thread.sleep(10000);
    }

    @AfterClass
    private void tearDown() throws InterruptedException {
        driver.quit();
        Thread.sleep(5000);
    }

    @AfterSuite
    public void saveReport() {
        report.flush();
    }
}
