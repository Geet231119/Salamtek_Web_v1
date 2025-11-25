package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class HospitalsAndClinicsMainPage extends BaseTest{
	By findOutOurFeaturedHeader = By.xpath("//app-hospital-home/div[1]/div[2]/div/div/div/div/div/h2[contains(text(),'OUR FEATURED')]");
	By hospitalsLnk = By.id("HOSPITAL-tab");
	By clinicsLnk = By.id("CLINICS-tab");
	By showMeTheFullClinicsListBtn = By.xpath("//app-hospital-home/div[1]/div[2]/div/div/div/div/div/div[4]/div/div/div/div/button[contains(text(),'SHOW ME THE FULL CLINICS LIST')]");
	By findADoctorWithinHeader = By.xpath("//app-clinic-listing/section/div/div/div[3]/div/div[3]/div[3]/div/div/div[1]/h2[contains(text(),'DOCTOR WITHIN')]");
	
	//Elements in Filter
	By manufacturesSearchTxt = By.xpath("//app-product-listing/section/div/div[2]/div/div[3]/div/div[1]/div[2]/div[2]/div[4]/div/div/div/div/div[1]/input");
	
	public HospitalsAndClinicsMainPage(WebDriver driver) 
	{
		super();
	}
	
	//Validate service provider from featured list
	public boolean selectServiceProviderList(String serviceProviderType) {
		waitSometime();
		driver.navigate().refresh();
		waitForPageLoad();
		explicitWait(findOutOurFeaturedHeader);
		if(serviceProviderType.equalsIgnoreCase("Clinic")) {
			moveToElement(driver.findElement(clinicsLnk));
			explicitWait(clinicsLnk);
			driver.findElement(clinicsLnk).click();
			moveToElement(driver.findElement(showMeTheFullClinicsListBtn));
			driver.findElement(showMeTheFullClinicsListBtn).click();
		}
		explicitWait_60(findADoctorWithinHeader);
		System.out.println("++++++++++++++++     "+driver.findElement(findADoctorWithinHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(findADoctorWithinHeader).getText().trim().toUpperCase(), "FIND A DOCTOR WITHIN", "FIND A DOCTOR WITHIN header is not present");
		return true;
	}
	
	
	
}
