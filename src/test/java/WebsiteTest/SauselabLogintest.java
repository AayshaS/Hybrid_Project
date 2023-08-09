package WebsiteTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import website.common.BaseClass1;
import website.sauselabPageObject.SauselapLoginPO;

import java.lang.reflect.Method;

import static website.utils.extentreports.ExtentTestManager.startTest;

public class SauselabLogintest extends BaseClass1 {
SauselapLoginPO sauselapLoginPO;
    @BeforeClass
    public void setup() {
        initialize();
        sauselapLoginPO=new SauselapLoginPO();
    }

@Test
    public void userLoginTest(){
//    startTest(method.getName(), "Testing Login");
    test=report.createTest("loginUser");
    sauselapLoginPO.loadWebsiteURL();
    sauselapLoginPO.userLogin();

}

    @Test
    public void userLogoutTest() throws InterruptedException {
//    startTest(method.getName(), "Testing Login");
        test=report.createTest("LogoutUser");
        sauselapLoginPO.userLogout();
    }

//        @AfterClass
        public void tearDown(){
            closeDriver();
        }

}
