package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Common.BaseTest;

public class HospitalsAndClinicsListingPage extends BaseTest {
	By findADoctorWithinHeader = By.xpath(
			"//app-clinic-listing/section/div/div/div[3]/div/div[3]/div[3]/div/div/div[1]/h2[contains(text(),'DOCTOR WITHIN')]");
	By findMoreHeader = By.xpath("//h2[contains(text(),'MORE')]");

	// Elements in Filter
	By manufacturesSearchTxt = By.xpath(
			"//app-product-listing/section/div/div[2]/div/div[3]/div/div[1]/div[2]/div[2]/div[4]/div/div/div/div/div[1]/input");

	public HospitalsAndClinicsListingPage(WebDriver driver) {
		super();
	}

	// Validate filter functionality of service providers
	public boolean filterServiceProviders(String serviceProviderType, String filterType, String filterValue,
			String filterResult, String pageName, String tcID, String scenario) {
		boolean result = false;
		waitSometime();
		explicitWait(findADoctorWithinHeader);
		scrollTillASpecificPoint(300);
		waitSometime();
		System.out.println(
				"++++++ " + driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).getText());
		driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).click();
		waitSometime();
		System.out.println(filterType + ": "
				+ driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")).getText());
		if (driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")).getText().trim()
				.equalsIgnoreCase(filterValue)) {
			driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")).click();
			waitSometime();
			takeScreenShot(getDriver(), "Selected_" + filterValue, tcID, scenario);
		}
		waitSometime();
		WebElement findMoreHeaderEle = driver.findElement(findMoreHeader);
		scrollTillElementVisible(findMoreHeaderEle);
		waitSometime();
		scrollTillASpecificPoint(-300);
		waitSometime();
		List<WebElement> filterResults = driver.findElements(By.xpath(
				"//app-clinic-listing/section/div/div/div[3]/div/div[3]/div[3]/div/div/div[7]/div/div[1]/div/div/div"));
		for (int i = 1; i <= filterResults.size(); i++) {
			if (driver.findElement(By.xpath(
					"//app-clinic-listing/section/div/div/div[3]/div/div[3]/div[3]/div/div/div[7]/div/div[1]/div/div/div["
							+ i + "]/app-clinic-single/span/div/div/div[5]/h3"))
					.getText().trim().equalsIgnoreCase(filterResult.trim())) {
				takeScreenShot(getDriver(), pageName, tcID, scenario);
				result = true;
				break;
			}
			if (i % 3 == 0) {
				scrollTillASpecificPoint(400);
				waitSometime();
			}
		}
		return result;
	}

}
