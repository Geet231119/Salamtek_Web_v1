package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.BaseTest;
import Utils.ExcelUtils;

public class SearchPage extends BaseTest {
	ExcelUtils excelUtils = new ExcelUtils();
	
	By searchTxt = By.id("inputSearch");
	By searchBtn = By.xpath("//app-header/div[1]/div/div/div[2]/div/div/div[3]/div/button[contains(text(),'SEARCH') and @class='search-btn']");
	
	//For medical Service
	By bookAnAppointmentBtn = By.xpath("//button[contains(text(),'BOOK AN APPOINTMENT')]");
	By profileServiceLnk = By.id("profileTestTab");

	public SearchPage(WebDriver driver) {
		super();
	}

	//Validate selection of search section in GLobal Search
	public void selectSearchSection(String searchSection) {
		List<WebElement> searchSections = driver
				.findElements(By.xpath("//app-search-result/section/div/div/div[4]/div[2]/ul/span/li"));
		for (int i = 1; i <= searchSections.size(); i++) {
			if (driver
					.findElement(
							By.xpath("//app-search-result/section/div/div/div[4]/div[2]/ul/span/li[" + i + "]/span"))
					.getText().trim().contains(searchSection)) {
				driver.findElement(
						By.xpath("//app-search-result/section/div/div/div[4]/div[2]/ul/span/li[" + i + "]/span"))
						.click();
			}
		}
	}

	//Validate search results
	public boolean verifySearchResult(String typeOfSearch, String searchSection, String searchValue, String expectedSearchResult,String tcID,
			String sheet) throws Exception {
		boolean status = false;
		explicitWait(searchTxt);
		driver.findElement(searchTxt).sendKeys(searchValue);
		driver.findElement(searchBtn).sendKeys(Keys.RETURN);
		waitSometime();
		switch (typeOfSearch) {
		case "test":
			selectSearchSection(searchSection);
			List<WebElement> searchTests = driver
					.findElements(By.xpath("//app-search-result/section/div/div/div[5]/div/div"));
			if (searchTests.size() == 1) {
				if (driver.findElement(By.xpath(
								"//app-search-result/section/div/div/div[5]/div/div/app-search-article-card/div/div/div[1]/h2"))
								.getText().trim().equalsIgnoreCase(searchValue)) {
					waitSometime();
					status = true;
					takeScreenShot(driver, "SearchResultForService", tcID, sheet);
					break;
				}
			} else {
				for (int i = 1; i <= searchTests.size(); i++) {
					if (driver
							.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div[" + i
									+ "]/app-test-card/div/div/div/div[1]/h2"))
							.getText().trim().equalsIgnoreCase(searchValue)) {
						waitSometime();
						status = true;
						takeScreenShot(driver, "SearchResultForService", tcID, sheet);
						break;
					}
					if (i % 4 == 0) {
						scrollTillASpecificPoint(200);
						waitSometime();
					}
				}
			}
			break;
		case "articles":
			selectSearchSection(searchSection);
			List<WebElement> searchArticles = driver
					.findElements(By.xpath("//app-search-result/section/div/div/div[5]/div/div"));
			if (searchArticles.size() == 1) {
				if (driver.findElement(By.xpath(
								"//app-search-result/section/div/div/div[5]/div/div/app-search-article-card/div/div/div[1]/h5"))
								.getText().trim().equalsIgnoreCase(searchValue)) {
					waitSometime();
					status = true;
					takeScreenShot(driver, "SearchResultForArticles", tcID, sheet);
					break;
				}
			} else {
				for (int i = 1; i <= searchArticles.size(); i++) {
					if (driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-search-article-card/div/div/div[1]/h5")).getText().trim().equalsIgnoreCase(searchValue)) {
						waitSometime();
						status = true;
						takeScreenShot(driver, "SearchResultForArticles", tcID, sheet);
						break;
					}
					if (i % 4 == 0) {
						scrollTillASpecificPoint(200);
						waitSometime();
					}
				}
			}
			break;
		case "clinics":
			selectSearchSection(searchSection);
			scrollTillASpecificPoint(200);
			List<WebElement> searchClinics = driver
					.findElements(By.xpath("//app-search-result/section/div/div/div[5]/div/div"));
			if (searchClinics.size() == 1) {
				if (driver.findElement(By.xpath(
								"//app-search-result/section/div/div/div[5]/div/div/app-clinic-single/span/div/div/div[5]/h3"))
								.getText().trim().equalsIgnoreCase(searchValue)) {
					waitSometime();
					status = true;
					takeScreenShot(driver, "SearchResultForClinics", tcID, sheet);
					break;
				}
			} else {
				for (int i = 1; i <= searchClinics.size(); i++) {
					if (driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-clinic-single/span/div/div/div[5]/h3")).getText().trim().equalsIgnoreCase(searchValue)) {
						waitSometime();
						status = true;
						takeScreenShot(driver, "SearchResultForClinics", tcID, sheet);
						break;
					}
					if (i % 4 == 0) {
						scrollTillASpecificPoint(200);
						waitSometime();
					}
				}
			}
			break;
		case "labs":
			selectSearchSection(searchSection);
			scrollTillASpecificPoint(200);
			List<WebElement> searchLabs = driver
					.findElements(By.xpath("//app-search-result/section/div/div/div[5]/div/div"));
			if (searchLabs.size() == 1) {
				if (driver.findElement(By.xpath(
								"//app-search-result/section/div/div/div[5]/div/div/app-new-lab-single/div/div/div[4]/h3"))
								.getText().trim().equalsIgnoreCase(searchValue)) {
					waitSometime();
					status = true;
					takeScreenShot(driver, "SearchResultForLabs", tcID, sheet);
					break;
				}
			} else {
				for (int i = 1; i <= searchLabs.size(); i++) {
					if (driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-new-lab-single/div/div/div[4]/h3")).getText().trim().equalsIgnoreCase(searchValue)) {
						waitSometime();
						status = true;
						takeScreenShot(driver, "SearchResultForLabs", tcID, sheet);
						break;
					}
					if (i % 4 == 0) {
						scrollTillASpecificPoint(200);
						waitSometime();
					}
				}
			}
			break;
		case "products":
			selectSearchSection(searchSection);
			scrollTillASpecificPoint(200);
			List<WebElement> searchProducts = driver
					.findElements(By.xpath("//app-search-result/section/div/div/div[5]/div/div"));
			if (searchProducts.size() == 1) {
				if (driver.findElement(By.xpath(
								"//app-search-result/section/div/div/div[5]/div/div/app-product-card/div/div/div[3]/h4"))
								.getText().trim().equalsIgnoreCase(searchValue)) {
					waitSometime();
					status = true;
					takeScreenShot(driver, "SearchResultForProducts", tcID, sheet);
					break;
				}
			} else {
				for (int i = 1; i <= searchProducts.size(); i++) {
					if (driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-product-card/div/div/div[3]/h4")).getText().trim().equalsIgnoreCase(searchValue)) {
						waitSometime();
						status = true;
						takeScreenShot(driver, "SearchResultForProducts", tcID, sheet);
						break;
					}
					if (i % 4 == 0) {
						scrollTillASpecificPoint(200);
						waitSometime();
					}
				}
			}
			break;
		case "tips":
			selectSearchSection(searchSection);
			scrollTillASpecificPoint(200);
			List<WebElement> searchTips = driver
					.findElements(By.xpath("//app-search-result/section/div/div/div[5]/div/div"));
			if (searchTips.size() == 1) {
				if (driver.findElement(By.xpath(
								"//app-search-result/section/div/div/div[5]/div/div/app-health-tip-card/div/div/div[1]/div[2]/div/div[1]/p"))
								.getText().trim().equalsIgnoreCase(searchValue)) {
					waitSometime();
					status = true;
					takeScreenShot(driver, "SearchResultForTips", tcID, sheet);
					break;
				}
			} else {
				for (int i = 1; i <= searchTips.size(); i++) {
					if (driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-health-tip-card/div/div/div[1]/div[2]/div/div[1]/p")).getText().trim().equalsIgnoreCase(searchValue)) {
						waitSometime();
						status = true;
						takeScreenShot(driver, "SearchResultForTips", tcID, sheet);
						break;
					}
					if (i % 4 == 0) {
						scrollTillASpecificPoint(200);
						waitSometime();
					}
				}
			}
			break;
		case "doctors":
			selectSearchSection(searchSection);
			scrollTillASpecificPoint(200);
			List<WebElement> searchDoctors = driver
					.findElements(By.xpath("//app-search-result/section/div/div/div[5]/div/div"));
			if (searchDoctors.size() == 1) {
				if (driver.findElement(By.xpath(
								"//app-search-result/section/div/div/div[5]/div/div/app-new-doc-card/div/div/div[3]/div/div/div[1]/div[1]/div/h2"))
								.getText().trim().equalsIgnoreCase(searchValue)) {
					waitSometime();
					status = true;
					takeScreenShot(driver, "SearchResultForDoctors", tcID, sheet);
					break;
				}
			} else {
				for (int i = 1; i <= searchDoctors.size(); i++) {
					if (driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-new-doc-card/div/div/div[3]/div/div/div[1]/div[1]/div/h2")).getText().trim().equalsIgnoreCase(searchValue)) {
						waitSometime();
						status = true;
						takeScreenShot(driver, "SearchResultForDoctors", tcID, sheet);
						break;
					}
					if (i % 4 == 0) {
						scrollTillASpecificPoint(200);
						waitSometime();
					}
				}
			}
			break;
		case "offers":
			selectSearchSection(searchSection);
			List<WebElement> searchOffers = driver
					.findElements(By.xpath("//app-search-result/section/div/div/div[5]/div/div"));
			if (searchOffers.size() == 1) {
				String actualSrcValue = driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div/app-new-single-offer/div/div/div/div[1]/img")).getAttribute("src");
				if (actualSrcValue.equalsIgnoreCase(expectedSearchResult)) {
					waitSometime();
					status = true;
					takeScreenShot(driver, "SearchResultForService", tcID, sheet);
					break;
				}
			} else {
				for (int i = 1; i <= searchOffers.size(); i++) {
					String actualSrcValue = driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div[" +i+ "]/app-new-single-offer/div/div/div/div[1]/img")).getAttribute("src");
					if (actualSrcValue.equalsIgnoreCase(expectedSearchResult)) {
						waitSometime();
						status = true;
						takeScreenShot(driver, "SearchResultForService", tcID, sheet);
						break;
					}
					if (i % 4 == 0) {
						scrollTillASpecificPoint(200);
						waitSometime();
					}
				}
			}
			break;
		}
		return status;
	}
	
	//Validate search result selection from Global Search
	public boolean selectSearchResult(String typeOfSearch, String searchSection, String searchValue, String tcID,
			String sheet) throws Exception {
		boolean status = false;
		switch (typeOfSearch) {
		case "labs":
			List<WebElement> searchLabs = driver
					.findElements(By.xpath("//app-search-result/section/div/div/div[5]/div/div"));
			if (searchLabs.size() == 1) {
				if (driver.findElement(By.xpath(
								"//app-search-result/section/div/div/div[5]/div/div/app-new-lab-single/div/div/div[4]/h3"))
								.getText().trim().equalsIgnoreCase(searchValue)) {
					takeScreenShot(driver, "SearchResultForLabs", tcID, sheet);
					driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div/app-new-lab-single/div/div/div[5]/button")).click();
					status = true;
					break;
				}
			} else {
				for (int i = 1; i <= searchLabs.size(); i++) {
					if (driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-new-lab-single/div/div/div[4]/h3")).getText().trim().equalsIgnoreCase(searchValue)) {
						takeScreenShot(driver, "SearchResultForLabs", tcID, sheet);
						moveToElement(driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-new-lab-single/div/div/div[5]/button")));
						driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-new-lab-single/div/div/div[5]/button")).click();
						status = true;
						takeScreenShot(driver, "SearchResultForLabs", tcID, sheet);
						break;
					}
					if (i % 4 == 0) {
						scrollTillASpecificPoint(200);
						waitSometime();
					}
				}
			}
			waitSometime();
			scrollTillTop();
			waitSometime();
			explicitWait(bookAnAppointmentBtn);
			moveToElement(driver.findElement(bookAnAppointmentBtn));
			waitSometime();
			driver.findElement(bookAnAppointmentBtn).click();
			waitSometime();
			System.out.println("++++++++++++++++     " + driver.findElement(profileServiceLnk).getText().trim());
			Assert.assertEquals(driver.findElement(profileServiceLnk).getText().trim(), "PROFILE SERVICE",
					"Profile service link is not present");
			break;
		case "products":
			List<WebElement> searchProducts = driver
					.findElements(By.xpath("//app-search-result/section/div/div/div[5]/div/div"));
			if (searchProducts.size() == 1) {
				if (driver.findElement(By.xpath(
								"//app-search-result/section/div/div/div[5]/div/div/app-product-card/div/div/div[3]/h4"))
								.getText().trim().equalsIgnoreCase(searchValue)) {
					status = true;
					takeScreenShot(driver, "SearchResultForProducts", tcID, sheet);
					driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div/app-product-card/div/div/div[4]/ul/li[2]/button")).click();
					break;
				}
			} else {
				for (int i = 1; i <= searchProducts.size(); i++) {
					if (driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-product-card/div/div/div[3]/h4")).getText().trim().equalsIgnoreCase(searchValue)) {
						status = true;
						takeScreenShot(driver, "SearchResultForProducts", tcID, sheet);
						moveToElement(driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-product-card/div/div/div[5]/ul/li[2]/button")));
						driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-product-card/div/div/div[5]/ul/li[2]/button")).click();
						break;
					}
					if (i % 4 == 0) {
						scrollTillASpecificPoint(200);
						waitSometime();
					}
				}
			}
			break;
		case "clinics":
			List<WebElement> searchClinics = driver
					.findElements(By.xpath("//app-search-result/section/div/div/div[5]/div/div"));
			if (searchClinics.size() == 1) {
				if (driver.findElement(By.xpath(
								"//app-search-result/section/div/div/div[5]/div/div/app-clinic-single/span/div/div/div[5]/h3"))
								.getText().trim().equalsIgnoreCase(searchValue)) {
					status = true;
					driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div/app-clinic-single/span/div/div/div[6]/button")).click();
					takeScreenShot(driver, "SearchResultForClinics", tcID, sheet);
					break;
				}
			} else {
				for (int i = 1; i <= searchClinics.size(); i++) {
					if (driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-clinic-single/span/div/div/div[5]/h3")).getText().trim().equalsIgnoreCase(searchValue)) {
						status = true;
						moveToElement(driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-clinic-single/span/div/div/div[6]/button")));
						driver.findElement(By.xpath("//app-search-result/section/div/div/div[5]/div/div["+i+"]/app-clinic-single/span/div/div/div[6]/button")).click();
						takeScreenShot(driver, "SearchResultForClinics", tcID, sheet);
						break;
					}
					if (i % 4 == 0) {
						scrollTillASpecificPoint(200);
						waitSometime();
					}
				}
			}
			break;
		}
		return status;
	}
	
	public boolean verifySearchError_2Letter(String searchValue, String expectedSearchResult,String tcID,
			String sheet) {
		boolean result = false;
		waitForSpecificTime(5000);
		explicitWait(searchTxt);
		driver.findElement(searchTxt).sendKeys(searchValue);
		driver.findElement(searchBtn).sendKeys(Keys.RETURN);
		result = validateToast(expectedSearchResult, tcID, sheet);
		excelUtils.updateValueToExcel("expectedSearchResult", expectedSearchResult);	
		Assert.assertEquals(result, true);
		return result;
	}
}
