package website.utils.listeners;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import website.common.BaseClass1;
import website.utils.extentreports.ExtentManager;
import website.utils.logs.Log;

import java.util.Objects;

import static website.utils.extentreports.ExtentTestManager.getTest;

public class TestListener extends BaseClass1 implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("I am in on Start " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("I am in on Finish " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
//        Get driver from BasePage and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
//        WebDriver webDriver = ((BasePage) testClass).driver;
        driver = ((BaseClass1) testClass).driver;
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for passed tests.
        getTest().log(Status.PASS, "Test passed", getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is failed.");
//        Get driver from BasePage and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
//        WebDriver webDriver = ((BasePage) testClass).driver;
        driver = ((BaseClass1) testClass).driver;
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, "Test Failed", getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is skipped.");
//        ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}
