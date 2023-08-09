package website.utils.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./Extent-Reports/WebsiteReport/extent-report.html");
        reporter.config().setReportName("Website Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Blog Name", "SouceLap");
        extentReports.setSystemInfo("Author", "SWQA Automation Team");
        return extentReports;
    }
}
