package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.BaseTest;

public class AdminProfileAccountStatementPage extends BaseTest{
	By salamtekAccountStatementHeader = By.xpath("//app-report-account-statement/nb-card/nb-card-header[contains(text(),'Salamtek Account Statement')]");
	By salesReportHeader = By.xpath("//nb-tabset/ul/li[1]/a/span[contains(text(),'Sales')]");
	By doctorReportHeader = By.xpath("//nb-tabset/ul/li[2]/a/span[contains(text(),'Doctor Appointment')]");
	By labReportHeader = By.xpath("//nb-tabset/ul/li[3]/a/span[contains(text(),'Lab Appointment')]");
	
	public AdminProfileAccountStatementPage(WebDriver driver) 
	{
		super();
	}
	
	//Validate reports under Reports -> Account Statement
	public boolean verifyAllReports(String tcID, String scenario) {
		boolean result = false;
		waitSometime();
		explicitWait(salamtekAccountStatementHeader);
		if(driver.findElement(salesReportHeader).getText().trim().equalsIgnoreCase("Sales") 
				&& driver.findElement(doctorReportHeader).getText().trim().equalsIgnoreCase("Doctor Appointment")
				&& driver.findElement(labReportHeader).getText().trim().equalsIgnoreCase("Lab Appointment")) {
			result = true;
			takeScreenShot(driver, "Reports", tcID, scenario);
		}
		else {
			result = false;
		}
		return result;
	}
}
