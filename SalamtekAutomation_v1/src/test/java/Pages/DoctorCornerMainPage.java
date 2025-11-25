package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.BaseTest;

public class DoctorCornerMainPage extends BaseTest {
	By doctorsShelfHeader = By.xpath(
			"//app-doctor-corner-home/div/section/div[2]/section[1]/div/div/div/div/h2[contains(text(),'SHELF')]");
	By checkoutAllDoctorsShelvesBtn = By.xpath(
			"//app-doctor-corner-home/div/section/div[2]/section[1]/div/div/div/div/div[2]/div/div/div/div/button[contains(text(),'CHECKOUT ALL DOCTORS SHELVES')]");
	By doctorsOnDoctorCornerTxt = By.xpath(
			"//app-doctor-shelf/section/div/div/div/div/div[1]/div[1]/p[contains(text(),'Doctors on Dr. Corner')]");
	By doctorsPicksHeader = By.xpath("//span[contains(text(),'DOCTORS PICKS')]");
	By checkoutAllDoctorsPicksBtn = By.xpath("//button[contains(text(),'CHECKOUT ALL DOCTORS PICKS')]");
	By medicalProductsTxt = By
			.xpath("//app-doctor-picks/div/div/div[1]/div[1]/div/p[contains(text(),'MEDICAL PRODUCT')]");
	By weWillMatchSubHeader = By.xpath("//app-doctor-corner-home/div/section/div[2]/section[2]/div/div[2]/div/app-need-more-section/p[contains(text(),'consultant that can help')]");

	public DoctorCornerMainPage(WebDriver driver) {
		super();
	}

	// select doctors from doctor's shelves from Doctor Corner Main page
	public boolean selectCheckoutAllDoctorShelves() throws Exception {
		explicitWait_60(doctorsShelfHeader);
		WebElement doctorsShelfHeaderEle = driver.findElement(doctorsShelfHeader);
		moveToElement(doctorsShelfHeaderEle);
		moveToElement(driver.findElement(checkoutAllDoctorsShelvesBtn));
		driver.findElement(checkoutAllDoctorsShelvesBtn).click();
		explicitWait_60(doctorsOnDoctorCornerTxt);
		System.out.println(
				"++++++++++++++++     " + driver.findElement(doctorsOnDoctorCornerTxt).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(doctorsOnDoctorCornerTxt).getText().trim().toUpperCase(),
				"DOCTORS ON DR. CORNER", "DOCTORS ON DR. CORNER text is not present");
		return true;
	}

	// select doctors from doctor's shelves from Doctor Corner Main page
	public boolean selectCheckoutAllDoctorPicks() throws Exception {
		waitSometime();
		WebElement doctorsPicksHeaderEle = driver.findElement(doctorsPicksHeader);
		moveToElement(doctorsPicksHeaderEle);
		waitSometime();
		moveToElement(driver.findElement(checkoutAllDoctorsPicksBtn));
		driver.findElement(checkoutAllDoctorsPicksBtn).click();
		waitForSpecificTime(5000);
		System.out.println(
				"++++++++++++++++     " + driver.findElement(medicalProductsTxt).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(medicalProductsTxt).getText().trim().toUpperCase(),
				"MEDICAL PRODUCT", "MEDICAL PRODUCT text is not present");
		return true;
	}
}
