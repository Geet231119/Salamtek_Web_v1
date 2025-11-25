package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Common.BaseTest;

public class DoctorCornerDoctorPage extends BaseTest {
	By doctorNameHeader = By.xpath("//app-doctor-profile/div/div[2]/div/div[1]/div[2]/div[3]/div/div[1]/h2");
	By recommendationsHeader = By.xpath("//span[contains(text(),'RECOMMENDATIONS')]");
	

	public DoctorCornerDoctorPage(WebDriver driver) {
		super();
	}

	// Select product from recommendations from doctor's Page
	public boolean selectProductFromRecommendation(String productName, String tcID,
			String scenario) throws Exception {
		boolean result = false;
		waitSometime();
		explicitWait(doctorNameHeader);
		waitSometime();
		WebElement recommendationsHeaderEle = driver.findElement(recommendationsHeader); 
		moveToElement(recommendationsHeaderEle);
		waitSometime();
		moveToElement(driver.findElement(By.xpath("//h4[contains(text(),'" + productName.trim() + "')]")));
		if (driver.findElement(By.xpath("//h4[contains(text(),'" + productName.trim() + "')]"))
				.getText().trim().equalsIgnoreCase(productName.trim())) {
			takeScreenShot(getDriver(), "DoctorCorner_ProductFromDoctorPage", tcID, scenario);
			moveToElement(driver.findElement(By.xpath("//h4[contains(text(),'"+productName.trim()+"')]/following::button")));
			driver.findElement(By.xpath("//h4[contains(text(),'"+productName.trim()+"')]/following::button")).click();
			result = true;
		}
		return result;
	}
}
