package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Common.BaseTest;

public class DoctorCornerDetailPage extends BaseTest {
	By doctorsOnDoctorCornerTxt = By.xpath(
			"//app-doctor-shelf/section/div/div/div/div/div[1]/div[1]/p[contains(text(),'Doctors on Dr. Corner')]");
	By medicalProductsTxt = By
			.xpath("//app-doctor-picks/div/div/div[1]/div[1]/div/p[contains(text(),'MEDICAL PRODUCT')]");
	By doctorNameHeader = By.xpath("//app-doctor-profile/div/div[2]/div/div[1]/div[2]/div[3]/div/div[1]/h2");
	By exclusiveItemsRecommendedTxt = By.xpath(
			"//app-doctor-details/section[1]/div[2]/div/div/div[1]/div/p[contains(text(),'Exclusive Items Recommended @ Dr. Corner’s Shelf')]");
	By priceSlider = By.id("myinput");
	By priceValue = By.xpath("//app-doctor-picks/div/div/div[1]/div[2]/div[2]/div[6]/div/div[3]/div[2]/div/span");

	public DoctorCornerDetailPage(WebDriver driver) {
		super();
	}

	// Filter doctors from doctor's shelves from Doctor Corner Details page
	public boolean filterDoctorShelves(String filterType, String filterValue, String filterResult, String tcID,
			String scenario) throws Exception {
		boolean result = false;
		waitSometime();
		explicitWait(doctorsOnDoctorCornerTxt);
		waitSometime();
		if (filterType.equalsIgnoreCase("Specialities")) {
			moveToElement(driver.findElement(By.xpath("//button[contains(text(),'Specialities')]")));
			driver.findElement(By.xpath("//button[contains(text(),'Specialities')]")).click();
		}
		waitSometime();
		moveToElement(driver.findElement(By.xpath("//app-doctor/div/div")));
		moveToElement(driver.findElement(By.xpath("//h2[contains(text(),'" + filterResult.trim() + "')]")));
		if (driver.findElement(By.xpath("//h2[contains(text(),'" + filterResult.trim() + "')]")).getText().trim()
				.equalsIgnoreCase(filterResult.trim())) {
			takeScreenShot(getDriver(), "FilterResult_" + filterResult, tcID, scenario);
			result = true;
		}
		return result;
	}

	// Filter doctors from doctor's picks from Doctor Corner Details page
	public boolean filterDoctorPicks(String filterType, String filterValue, String filterResult, String tcID,
			String scenario) throws Exception {
		boolean result = false;
		waitSometime();
		explicitWait(medicalProductsTxt);
		waitSometime();
		if (filterType.equalsIgnoreCase("Ratings")) {
			moveToElement(driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")));
			if (driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).getText().trim()
					.equalsIgnoreCase(filterType)) {
				driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).click();
			}
		} else if (filterType.equalsIgnoreCase("Price")) {
			try {
				moveToElement(driver.findElement(priceSlider));
				WebElement priceSliderEle = driver.findElement(priceSlider);
				Actions builder = new Actions(driver);
				builder.moveToElement(priceSliderEle).click().dragAndDropBy(priceSliderEle, -110, 0).build().perform();
			} catch (JavascriptException e) {
				System.out.println("Exception in finding the element:" + e.getMessage());
			}
			waitSometime();
			takeScreenShot(getDriver(), "Selected_" + filterType + "_" + filterValue, tcID, scenario);
		} else {
			moveToElement(driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")));
			if (driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).getText().trim()
					.equalsIgnoreCase(filterType)) {
				driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).click();
			}
		}
		waitSometime();
		if (filterType.equalsIgnoreCase("Doctors")||filterType.equalsIgnoreCase("Brands")||filterType.equalsIgnoreCase("Categories")) {
			moveToElement(driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")));
			if (driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")).getText().trim()
					.equalsIgnoreCase(filterValue)) {
				driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")).click();
				takeScreenShot(getDriver(), "Selected_" + filterType + "_" + filterValue, tcID, scenario);
			}
		}
		else if (filterType.equalsIgnoreCase("Ratings")) {
			moveToElement(driver.findElement(By.xpath("//input[@value='"+ filterValue + "']")));
			driver.findElement(By.xpath("//input[@value='"+ filterValue + "']"))
					.click();
			takeScreenShot(getDriver(), "Selected_" + filterType, tcID, scenario);
		}
		if (filterType.equalsIgnoreCase("Price")) {
			int counter = 0;
			for (int i = 1; i <= 5; i++) {
				Double actualPrice = Double.parseDouble(driver
						.findElement(By.xpath("//app-doctor-picks/div/div/div[2]/div[2]/div/div/div/div[" + i
								+ "]/app-productlist/div[1]/div/div[6]/ul/li[1]/div[1]/span"))
						.getText().trim().substring(2));
				Double expectedPrice = Double.parseDouble(filterResult);
				if (actualPrice <= expectedPrice) {
					counter++;
				}
				if (i % 3 == 0) {
					scrollTillASpecificPoint(600);
					waitSometime();
				}
			}
			if (counter == 5) {
				takeScreenShot(getDriver(), "FilterResult_" + filterResult, tcID, scenario);
				result = true;
			} else {
				result = false;
			}
		} else {
			moveToElement(driver.findElement(By.xpath("//h4[contains(text(),'" + filterResult.trim() + "')]")));
			if (driver.findElement(By.xpath("//h4[contains(text(),'" + filterResult.trim() + "')]"))
					.getText().trim().equalsIgnoreCase(filterResult.trim())) {
				takeScreenShot(getDriver(), "FilterResult_" + filterResult, tcID, scenario);
				result = true;
			}
		}
		return result;
	}

	// Select doctor from doctor's corner Main Page
	public boolean selectDoctor(String filterBy, String doctorName, String tcID, String scenario) throws Exception {
		waitSometime();
		explicitWait(doctorsOnDoctorCornerTxt);
		moveToElement(driver.findElement(By.xpath("//h2[contains(text(),'" + doctorName.trim() + "')]")));
		if (driver.findElement(By.xpath("//h2[contains(text(),'" + doctorName.trim() + "')]"))
				.getText().trim().equalsIgnoreCase(doctorName.trim())) {
			takeScreenShot(getDriver(), "DoctorCorner_DoctorFromList", tcID, scenario);
			moveToElement(driver.findElement(By.xpath("//h2[contains(text(),'"+doctorName.trim()+"')]/following::button")));
			driver.findElement(By.xpath("//h2[contains(text(),'"+doctorName.trim()+"')]/following::button"))
					.sendKeys(Keys.RETURN);
		}
		explicitWait_60(exclusiveItemsRecommendedTxt);
		System.out.println("++++++++++++++++     "
				+ driver.findElement(exclusiveItemsRecommendedTxt).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(exclusiveItemsRecommendedTxt).getText().trim().toUpperCase(),
				"EXCLUSIVE ITEMS RECOMMENDED @ DR. CORNER’S SHELF", "Text is not present");
		return true;
	}

	// Select doctor from doctor's corner Main Page
	public boolean selectProduct(String filterBy, String productName, String tcID, String scenario) throws Exception {
		boolean result = false;
		waitSometime();
		explicitWait(medicalProductsTxt);
		moveToElement(driver.findElement(By.xpath("//h4[contains(text(),'" + productName.trim() + "')]")));
		if (driver.findElement(By.xpath("//h4[contains(text(),'" + productName.trim() + "')]"))
				.getText().trim().equalsIgnoreCase(productName.trim())) {
			takeScreenShot(getDriver(), "DoctorCorner_ProductsFromList", tcID, scenario);
			moveToElement(driver.findElement(By.xpath("//h4[contains(text(),'"+productName.trim()+"')]/following::button")));
			waitSometime();
			driver.findElement(By.xpath("//h4[contains(text(),'"+productName.trim()+"')]/following::button")).click();
			result = true;
		}
		return result;
	}
}
