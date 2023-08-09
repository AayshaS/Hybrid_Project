package androidapp.pageObjectClass;

import androidapp.common.BaseTest;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import website.common.BaseClass1;
import website.utils.logs.Log;

public class Practice extends BaseTest {

    @FindBy(xpath="//android.widget.TextView[@content-desc=\"App\"]")
    public WebElement appOption;

   @FindBy(xpath="//android.widget.TextView[@content-desc=\"Alert Dialogs\"]")
    public WebElement alertOption;

   @FindBy(xpath = "//android.widget.Button[@content-desc=\"OK Cancel dialog with a message\"]")
   public WebElement dailogBoxOption;

   @FindBy(id = "android:id/button2")
   public WebElement cancelBtn;

    @FindBy(id ="android:id/button1")
    public WebElement okBtn;

    @FindBy(xpath ="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView")
    public WebElement dailogBoxText;

    @FindBy(xpath ="//android.widget.Button[@content-desc=\"OK Cancel dialog with a long message\"]")
    public WebElement dailogBox2Option;

    @FindBy(id = "android:id/message")
    public WebElement dailogBoxText2;

    @FindBy(id = "android:id/button3")
    public WebElement someThingBtn;



public Practice(){
    PageFactory.initElements(driver,this);
}

    public void openApp() throws InterruptedException {
//        viewOption.click();
        appOption.click();
//        log("click AppOption");
        Thread.sleep(3000);
        alertOption.click();
//        log("click alertOption");
    }

    public void okCancelAction() throws InterruptedException {
        try {
            Thread.sleep(3000);
            dailogBoxOption.click();
//            log("click dailogBoxOption");
            Thread.sleep(3000);
            String dailogBoxText1 = dailogBoxText.getText();
            Assert.assertEquals(dailogBoxText.getText(), dailogBoxText1);
//            log("dailogBoxText1 verify"+dailogBoxText.getText());
            System.out.println(dailogBoxText.getText());
            cancelBtn.click();
//            log("click cancelBtn");
            Thread.sleep(3000);
            dailogBoxOption.click();
//            log("click dailogBoxOption");
            Thread.sleep(2000);
            okBtn.click();
//            log("click Okay");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void okCancelActionWithLongText(){
        try {
            Thread.sleep(3000);
            dailogBox2Option.click();
//            log("click dailogBoxOption2");
            Thread.sleep(3000);
            String dailogBoxText1 = dailogBoxText2.getText();
            Assert.assertEquals(dailogBoxText2.getText(), dailogBoxText1);
            System.out.println(dailogBoxText2.getText());
//            log("dailogBoxText2 verify"+dailogBoxText2.getText());

            Thread.sleep(2000);
            cancelBtn.click();
//            log("click cancelBtn");
            Thread.sleep(1000);
            dailogBox2Option.click();
//            log("click dailogBox2Option");
            Thread.sleep(1000);
            someThingBtn.click();
//            log("click someThingBtn");


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void log(String log) {
        BaseTest.test.log(Status.PASS,log);
        Log.info(log);
    }
}
