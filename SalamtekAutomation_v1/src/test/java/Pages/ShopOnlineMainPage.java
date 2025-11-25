package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Common.BaseTest;

public class ShopOnlineMainPage extends BaseTest {
	By pharmacyHeader = By.xpath("//div[@class='DOCTER-PROFILE-NAME']/h2");
	By productHeader = By.xpath("//app-product-profile/div/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/h2");
	By featuredProductHeader = By.xpath("//div[@class='search-results']/div[1]/div[@class='LOOKING']/h2/span");
	By thousandsOfProductsHeader = By.xpath("//div[@class='PRODUCT-SERVICE mt-5']/div/div[@class='LOOKING']/h2/span");
	By medicalProductHeader = By.xpath("//p[contains(text(),'MEDICAL PRODUCT')]");
	By quantityTxt = By.xpath("//h4[contains(text(),'Quantity')]");
	By whatAreYouLookingLbl = By
			.xpath("//app-shop-online-home/div[1]/div/div/div/div/div[1]/div/h2[contains(text(),'WHAT ARE YOU')]");
	By salamtekHasItAll = By
			.xpath("//app-shop-online-home/div[2]/section[1]/div/div/div/div/h2[contains(text(),'HAS IT ALL')]");
	By pharmacyImg = By.xpath("//app-new-single-offer/div/div/div/div[1]/img");
	By buyNowBtn = By.xpath("///app-new-single-offer/div/div/div/div[3]/button/span");
	By topSellingLnk = By.id("SELLING-tab");
	By offersAndPromotionsLnk = By.id("MEDICAL-tab");
	By todayDealsLnk = By.id("PHARMACIES-tab");
	By mostViewedCategoryLbl = By
			.xpath("//app-shop-online-home/div[2]/section[2]/div[1]/h2[contains(text(),'MOST VIEWED')]");
	By checkAllProductBtn = By.xpath(
			"//app-shop-online-home/div[2]/section[2]/div[3]/div/div/div[1]/button[contains(text(),'CHECK ALL PRODUCTS')]");
	By pharmaciesLnk = By.xpath(
			"//app-shop-online-home/div[2]/section[2]/div[2]/div/div/app-need-more-section/div[1]/ul/li[1]/button");
	By medicalEquipmentLnk = By.xpath(
			"//app-shop-online-home/div[2]/section[2]/div[2]/div/div/app-need-more-section/div[1]/ul/li[2]/button");
	By productListingHeader = By.xpath(
			"//app-product-listing/section/div/div[2]/div/div[3]/div/div[3]/div/div[3]/h2/span[contains(text(),'PRODUCTS LISTING')]");
	By medicalEquipmentHeader = By.xpath(
			"//app-product-listing/section/div/div[2]/div/div[3]/div/div[3]/div/div[6]/div/div[1]/h2[contains(text(),'MEDICAL EQUIPMENT')]");
	By pharmacyProductsLnk = By.xpath("//app-product-listing/section/div/div[2]/div/div[3]/div/div[3]/div/div[4]/div[1]/ul/li[1]/button");
	By equipmentLnk = By.xpath("//app-product-listing/section/div/div[2]/div/div[3]/div/div[3]/div/div[4]/div[1]/ul/li[2]/button");
	By otherProductsLnk = By.xpath("//app-product-listing/section/div/div[2]/div/div[3]/div/div[3]/div/div[4]/div[1]/ul/li[3]/button");
	// Elements in Filter
	By manufacturesSearchTxt = By.xpath(
			"//app-product-listing/section/div/div[2]/div/div[3]/div/div[1]/div[2]/div[2]/div[4]/div/div/div/div/div[1]/input");
	By priceSlider = By.id("myinput");

	// Variables
	String expectedPriceValue = "";

	public ShopOnlineMainPage(WebDriver driver) {
		super();
	}

	// Validate selection of product from Most Viewed Categories
	public boolean selectProductFromMostViewedCategories(String productCategory) {
		waitSometime();
		WebElement mostViewedCategoryLblEle = driver.findElement(mostViewedCategoryLbl);
		moveToElement(mostViewedCategoryLblEle);
		moveToElement(driver.findElement(pharmaciesLnk));
		driver.findElement(pharmaciesLnk).click();
		waitSometime();
		moveToElement(driver.findElement(checkAllProductBtn));
		String categoryNameTxt = driver.findElement(By.xpath("//h2[contains(text(),'"+productCategory.trim()+"')]")).getText().trim();
		if (categoryNameTxt.contains(productCategory.trim())) {
			clickUsingJS(By.xpath("//h2[contains(text(),'"+productCategory.trim()+"')]"));
		}
		explicitWait(productListingHeader);
		moveToElement(driver.findElement(productListingHeader));
		explicitWait(productListingHeader);
		System.out.println(
				"++++++++++++++++     " + driver.findElement(productListingHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(productListingHeader).getText().trim().toUpperCase(), "PRODUCTS LISTING",
				"Product is not present");
		return true;
	}

	// Validate selection of product from Salamtek Has It All
	public boolean selectProductFromSalamtekHasItAll(String productCategory, String productName, String tcID,
			String scenario) {
		boolean result = false;
		waitSometime();
		WebElement salamtekHasItAllEle = driver.findElement(salamtekHasItAll);
		moveToElement(salamtekHasItAllEle);
		if (productCategory.equalsIgnoreCase("OFFERSANDPROMOTIONS")) {
			moveToElement(driver.findElement(offersAndPromotionsLnk));
			driver.findElement(offersAndPromotionsLnk).click();
			takeScreenShot(getDriver(), "Offer&Promotions", tcID, scenario);
			waitSometime();
		}
		moveToElement(driver.findElement(By.xpath("//h4[contains(text(),'"+productName.trim()+"')]")));
		String productNameTxt = driver.findElement(By.xpath("//h4[contains(text(),'"+productName.trim()+"')]")).getText().trim();
		if (productNameTxt.contains(productName.trim())) {
		moveToElement(driver.findElement(By.xpath("//h4[contains(text(),'"+productName.trim()+"')]/following::button")));
		takeScreenShot(getDriver(), "ProductFromOffer&Promotions", tcID, scenario);
		driver.findElement(By.xpath("//h4[contains(text(),'"+productName.trim()+"')]/following::button"))
				.click();
		result = true;
	}
		waitSometime();
		return result;
	}

	// Validate selection of medical equipment from Most Viewed Category
	public boolean selectMedicalEquipment() {
		waitSometime();
		WebElement mostViewedCategoryLblEle = driver.findElement(mostViewedCategoryLbl);
		waitSometime();
		moveToElement(mostViewedCategoryLblEle);
		waitSometime();
		moveToElement(driver.findElement(medicalEquipmentLnk));
		driver.findElement(medicalEquipmentLnk).click();
		moveToElement(driver.findElement(checkAllProductBtn));
		driver.findElement(checkAllProductBtn).click();
		waitSometime();
		waitSometime();
		explicitWait(productListingHeader);
		System.out.println(
				"++++++++++++++++     " + driver.findElement(productListingHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(productListingHeader).getText().trim().toUpperCase(), "PRODUCTS LISTING",
				"Product is not present");
		return true;
	}

	// Validate selection of medical equipment/pharmacy products in product listing
	public boolean select_ME_Pharmacy_Product(String productName) throws Exception {
		waitSometime();
		explicitWait(productListingHeader);
		waitSometime();
		moveToElement(driver.findElement(By.xpath("//h4[contains(text(),'"+productName.trim()+"')]")));
		String prodHeaderTxt = driver.findElement(By.xpath("//h4[contains(text(),'"+productName.trim()+"')]")).getText().trim();
		if (prodHeaderTxt.contains(productName.trim())) {
			clickUsingJS(By.xpath("//h4[contains(text(),'"+productName.trim()+"')]"));
		}
		waitSometime();
		waitSometime();
		explicitWait(productHeader);
		System.out.println("++++++++++++++++     " + driver.findElement(productHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(productHeader).getText().trim().toUpperCase(),
				productName.trim().toUpperCase(), "Product is not present");
		return true;
	}

	// Validate product from a selected pharmacy
	public boolean selectProduct(String productName) throws Exception {
		waitSometime();
		explicitWait(pharmacyHeader);
		if (driver.findElement(medicalProductHeader).isDisplayed()) {
			moveToElement(driver.findElement(medicalProductHeader));
			driver.findElement(medicalProductHeader).click();
		}
		waitSometime();
		WebElement thousandEle = driver.findElement(thousandsOfProductsHeader);
		moveToElement(thousandEle);
		waitSometime();
		By prodHeader = By.xpath("//h4[contains(text(),'"+productName.trim()+"')]");
		moveToElement(driver.findElement(prodHeader));
		String prodHeaderTxt = driver.findElement(prodHeader).getText().trim();
		if (prodHeaderTxt.contains(productName.trim())) {
			clickUsingJS(prodHeader);
		}
		waitSometime();
		waitSometime();
		explicitWait(productHeader);
		System.out.println("++++++++++++++++     " + driver.findElement(productHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(productHeader).getText().trim().toUpperCase(),
				productName.trim().toUpperCase(), "Product is not present");
		return true;
	}

	// Validate filter in product listing
	public boolean filterPharmacyEquipmentProducts(String filterBy, String filterType, String filterValue,
			String filterResult, String pageName, String tcID, String scenario) throws Exception {
		boolean result = false;
		waitSometime();
		explicitWait(productListingHeader);
		driver.findElement(productListingHeader).click();
		waitSometime();
		if(filterBy.equalsIgnoreCase("PHARMACY PRODUCTS")) {
			driver.findElement(pharmacyProductsLnk).click();
		}
		else if(filterBy.equalsIgnoreCase("MEDICAL EQUIPMENT")) {
			driver.findElement(equipmentLnk).click();
		}
		else {
			driver.findElement(otherProductsLnk).click();
		}
		waitSometime();
		moveToElement(driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")));
		if (driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).getText().trim()
				.equalsIgnoreCase(filterType)) {
			driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).click();
			takeScreenShot(getDriver(), "Selected_" + filterValue, tcID, scenario);
		}
		waitSometime();
		if (filterType.equalsIgnoreCase("Stores") || filterType.equalsIgnoreCase("Categories")
				|| filterType.equalsIgnoreCase("Brands") || filterType.equalsIgnoreCase("Manufactures")) {
			moveToElement(driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")));
			if (driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")).getText().trim()
					.equalsIgnoreCase(filterValue)) {
				driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")).click();
				takeScreenShot(getDriver(), "Selected_" + filterValue, tcID, scenario);
			}
		} else if (filterType.equalsIgnoreCase("Ratings")) {
			// scrollTillASpecificPoint(200);
			// waitSometime();
			moveToElement(driver.findElement(By.xpath("//input[@value='" + filterValue + "']")));
			driver.findElement(By.xpath("//input[@value='" + filterValue + "']")).click();
			takeScreenShot(getDriver(), "Selected_" + filterValue, tcID, scenario);
		} else if (filterType.equalsIgnoreCase("Price")) {
			try {
				WebElement priceSliderEle = driver.findElement(priceSlider);
				System.out.println("Default Location Value :" + priceSliderEle.getAttribute("value"));
				System.out.println("Default Location :" + priceSliderEle.getLocation());
				Actions actions = new Actions(driver);
				actions.clickAndHold(priceSliderEle).moveByOffset(20, 0).release().perform();
				System.out.println("After move Location Value :" + priceSliderEle.getAttribute("value"));
				System.out.println("After move Location :" + priceSliderEle.getLocation());
			} catch (JavascriptException e) {
				System.out.println("Exception in finding the element:" + e.getMessage());
			}
			waitSometime();
			takeScreenShot(getDriver(), "Selected_" + filterType, tcID, scenario);
		}
		waitForSpecificTime(5000);
		expectedPriceValue = driver
				.findElement(By.xpath("//*[@id=\"flush-collapseRating\"]/div/div/div/div/div[2]/div[2]/div/span/span"))
				.getText().trim();
		System.out.println("Expected Price Value: " + expectedPriceValue);
		waitSometime();
		WebElement medicalProductHeaderEle = driver.findElement(medicalProductHeader);
		moveToElement(medicalProductHeaderEle);
		if (filterType.equalsIgnoreCase("Price")) {
			int counter = 0;
			for (int i = 1; i <= 5; i++) {
				moveToElement(driver.findElement(By.xpath(
						"//app-product-listing/section/div/div[2]/div/div[3]/div/div[3]/div/div[6]/div/div[3]/div/div["
								+ i + "]/app-productlist/div[1]/div/div[6]/ul/li[1]/div[1]/span")));
				Double actualPrice = Double.parseDouble(driver.findElement(By.xpath(
						"//app-product-listing/section/div/div[2]/div/div[3]/div/div[3]/div/div[6]/div/div[3]/div/div["
								+ i + "]/app-productlist/div[1]/div/div[6]/ul/li[1]/div[1]/span"))
						.getText().trim().substring(2));
				Double expectedPrice = Double.parseDouble(expectedPriceValue);
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
			moveToElement(driver.findElement(By.xpath("//h4[contains(text(),'" + filterResult + "')]")));
			if (driver.findElement(By.xpath("//h4[contains(text(),'" + filterResult + "')]")).getText().trim()
					.equalsIgnoreCase(filterResult.trim())) {
				takeScreenShot(getDriver(), pageName, tcID, scenario);
				result = true;
			}
		}
		return result;
	}

	// Validate filter in product listing by brand
	public boolean filterByBrand(String filterValue, String tcID, String scenario) throws Exception {
		boolean result = false;
		waitSometime();
		explicitWait(medicalProductHeader);
		List<WebElement> filterByList = driver.findElements(
				By.xpath("//app-pharmacy-details/section/div/section[1]/div/div/div/div/div/div[1]/div[2]/div[2]/div/div"));
		for (int i = 1; i <= filterByList.size(); i++) {
			moveToElement(driver.findElement(
					By.xpath("//app-pharmacy-details/section/div/section[1]/div/div/div/div/div/div[1]/div[2]/div[2]/div/div["+ i +"]/h2/button")));
			if (driver.findElement(
					By.xpath("//app-pharmacy-details/section/div/section[1]/div/div/div/div/div/div[1]/div[2]/div[2]/div/div["
							+ i + "]/h2/button"))
					.getText().trim().equalsIgnoreCase("Brands")) {
				driver.findElement(By.xpath(
						"//app-pharmacy-details/section/div/section[1]/div/div/div/div/div/div[1]/div[2]/div[2]/div/div[" + i
								+ "]/h2/button"))
						.click();
				break;
			}
		}
		waitSometime();
		scrollTillASpecificPoint(200);
		List<WebElement> brands = driver.findElements(By.xpath(
				"//app-pharmacy-details/section/div/section[1]/div/div/div/div/div/div[1]/div[2]/div[2]/div/div[3]/div/div/div[2]/div"));
		for (int i = 1; i <= brands.size(); i++) {
			if (driver.findElement(By.xpath(
					"//app-pharmacy-details/section/div/section[1]/div/div/div/div/div/div[1]/div[2]/div[2]/div/div[3]/div/div/div[2]/div["
							+ i + "]/div/label"))
					.getText().trim().equalsIgnoreCase(filterValue.trim())) {
				driver.findElement(By.xpath(
						"//app-pharmacy-details/section/div/section[1]/div/div/div/div/div/div[1]/div[2]/div[2]/div/div[3]/div/div/div[2]/div["
								+ i + "]/div/label"))
						.click();
				waitSometime();
				takeScreenShot(getDriver(), "Selected_" + filterValue, tcID, scenario);
				result = true;
				break;
			}
			if (i % 3 == 0) {
				scrollTillASpecificPoint(100);
				waitSometime();
			}
		}

		return result;
	}
}
