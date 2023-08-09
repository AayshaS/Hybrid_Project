package website.sauselabPageObject;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import website.common.BaseClass1;
import website.utils.logs.Log;

public class SauselapLoginPO extends BaseClass1 {

    @FindBy(xpath = "//input[@id='user-name']")
    public WebElement enterUserName;

    @FindBy(xpath = "//input[@id=\"password\"]")
    public WebElement enterPassword;

    @FindBy(xpath = "//input[@id=\"login-button\"]")
    public WebElement loginBtn;

    @FindBy(xpath = "//button[@id=\"react-burger-menu-btn\"]")
    public WebElement sidebar;


    @FindBy(xpath = "//a[@id=\"logout_sidebar_link\"]")
    public WebElement logOutBtn;

    @FindBy(xpath = "(//*[contains(text(),'Swag Labs')])[2]")
    public WebElement titleText;

 public  SauselapLoginPO(){
        PageFactory.initElements(driver,this);
    }

    public void loadWebsiteURL() {
        loadUrl(prop.getProperty("url"));
        pageLoadWait();
        Log.info("Sauselab URL is loaded");
    }

    public void userLogin(){
      //  Log.info("Enter username ");
        log("Enter username");
        enterUserName.sendKeys("standard_user");
       // Log.info("Enter  Password");
        log("Enter  Password");
        enterPassword.sendKeys("secret_sauce");
        loginBtn.click();
//        Log.info("Click login BTN");
//        Log.info("verify page title ");
        log("Click login BTN");
        Assert.assertEquals(titleText.getText(),"Swag Labs");
        log("verify page title");

    }
    public void userLoginTitleverify(){
        Log.info("verify page title ");
        Assert.assertEquals(titleText.getText(),"Swag Labs'");

    }

    public void userLogout() throws InterruptedException {
        Log.info("Click Side bar ");
        sidebar.click();
        Thread.sleep(2000);
        Log.info("verify page title ");
        Assert.assertEquals(logOutBtn.getText(), "Logout");
        log("verify Logot BTN");
//        Log.info("Click logout BTN");
        log("Click Logot BTN");
        logOutBtn.click();

    }

    private void log(String log) {
        BaseClass1.test.log(Status.PASS,log);
        Log.info(log);
    }

}
