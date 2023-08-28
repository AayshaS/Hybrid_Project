package api.common;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import website.utils.logs.Log;

public class BaseClass {
	
	public   ExtentSparkReporter spark;
	public   ExtentReports report;
	public static ExtentTest test;
	
	@BeforeSuite
	public void initialSetup() {
		// TODO Auto-generated method stub
//		SimpleDateFormat d=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//		String d1=d.format(new Date());
		Date currentDateTime = new Date();;
		DateFormat dateFormat = new SimpleDateFormat(" dd-MM-yyyy_hh_mm_ss_a");
		String d1 = dateFormat.format(currentDateTime);
		spark=new ExtentSparkReporter(new File("./Extent-Reports/APIReport/GoRest"+d1+".html"));
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

	public static void log(String log) {
		BaseClass.test.log(Status.PASS,log);
		Log.info(log);
	}
}
