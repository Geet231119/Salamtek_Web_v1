package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class HomeServiceRequestPage extends BaseTest {
	By homeServiceRequestHeader = By.xpath("//*[@id=\"askDoctorModal\"]/span[contains(text(),'HOME SERVICE')]");
	By requestTextarea = By.xpath("//app-home-service-request/div/div/div/div[2]/textarea");
	By sendRequestBtn = By.xpath("//button[contains(text(),'SEND REQUEST')]");
	By acknowledgementTxt = By.xpath("//app-home-service-request/div/div/div/div[2]/div[3]/p/span");
	By doneBtn = By.xpath("//app-home-service-request/div/div/div/div[2]/div[4]/div/button[contains(text(),'DONE')]");

	public HomeServiceRequestPage(WebDriver driver) {
		super();
	}
	
	// Select document from system and upload as prescription
		public boolean sendHomeServiceRequest(String requestComment,String pageName, String tcID,
				String sheetName) throws Exception {
			waitSometime();
			explicitWait(homeServiceRequestHeader);
			driver.findElement(requestTextarea).sendKeys(requestComment);
			waitSometime();
			takeScreenShot(getDriver(), pageName, tcID, sheetName);
			clickUsingJS(sendRequestBtn);
			waitSometime();
			System.out.println("++++++++++++++++     "+driver.findElement(acknowledgementTxt).getText().trim().toUpperCase());
			Assert.assertEquals(driver.findElement(acknowledgementTxt).getText().trim().toUpperCase(), "THANK YOU FOR THE REQUEST! WE WILL CONTACT YOUR SHORTLY.", "THANK YOU FOR THE REQUEST! WE WILL CONTACT YOUR SHORTLY. is not present");
			driver.findElement(doneBtn).click();
			return true;
		}
}
