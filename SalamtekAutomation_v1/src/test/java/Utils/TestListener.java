package Utils;

import java.util.Objects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import Common.BaseTest;



public class TestListener extends BaseTest implements ITestListener {
	
	private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
	
	@Override
    public void onStart(ITestContext iTestContext) {
        Log.info("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", getDriver());
    }
	  @Override
	    public void onTestSuccess(ITestResult iTestResult) {
	        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
	        //ExtentReports log operation for passed tests.
	        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	    }
	
	@Override
	 public void onTestFailure(ITestResult result) {
		
		Log.info(getTestMethodName(result) + " test is failed.");
        //Get driver from BaseTest and assign to local webdriver variable.
        WebDriver driver = getDriver();
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot)Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().getMessage(),
        		ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
	}
	
	   @Override
	    public void onFinish(ITestContext iTestContext) {
	        Log.info("I am in onFinish method " + iTestContext.getName());
	        //Do tier down operations for ExtentReports reporting!
	        ExtentManager.extentReports.flush();
	   }

}
