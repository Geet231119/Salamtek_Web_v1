package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import Common.BaseTest;

public class AdminProfilePromotionPage extends BaseTest {
	By promotionHeader = By.xpath("//app-promotion-index/nb-card/nb-card-header[contains(text(),'Promotion')]");
	By createBtn = By.xpath("//app-promotion-index/nb-card/nb-card-header/p/a[contains(text(),'Create')]");
	By createPromotionHeader = By
			.xpath("//app-promotion-create-update/nb-card/nb-card-header[contains(text(),'Create Promotion')]");
	By codeTxt = By.name("code");
	By titleEnTxt = By.name("titleEn");
	By titleArTxt = By.name("titleAr");
	By startDateTxt = By.name("startDate");
	By endDateTxt = By.name("endDate");
	By userPerCouponTxt = By.xpath(
			"//app-promotion-create-update/nb-card/nb-card-body/form/div[4]/div[1]/div/ng-select/div/div/div[3]/input");
	By numberOfCouponTxt = By.name("promoCount");
	By discountTypeTxt = By.xpath(
			"//app-promotion-create-update/nb-card/nb-card-body/form/div[5]/div[1]/div/ng-select/div/div/div[3]/input");
	By discountTxt = By.name("discount");
	By promoForTxt = By.xpath(
			"//app-promotion-create-update/nb-card/nb-card-body/form/div[5]/div[3]/div/ng-select/div/div/div[3]/input");
	By saveBtn = By.xpath("//button[contains(text(),'Save')]");
	By codeSearchTxt = By.xpath(
			"//app-promotion-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[2]/ng2-smart-table-filter/div/default-table-filter/input-filter/input");
	By searchedPromoCode = By.xpath(
			"//app-promotion-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[2]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	By deleteBtn = By.xpath(
			"//app-promotion-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[1]/ng2-st-tbody-custom/a[3]");

	// Doctor related fields
	By doctorTxt = By.xpath(
			"//app-promotion-create-update/nb-card/nb-card-body/form/div[5]/div[4]/div/ng-select/div/div/div[3]/input");
	By doctorServiceTxt = By.xpath(
			"//app-promotion-create-update/nb-card/nb-card-body/form/div[5]/div[5]/div/ng-select/div/div/div[2]/input");

	// Lab related fields
	By labTxt = By.xpath(
			"//app-promotion-create-update/nb-card/nb-card-body/form/div[5]/div[4]/div/ng-select/div/div/div[3]/input");
	By labTestTxt = By.xpath(
			"//app-promotion-create-update/nb-card/nb-card-body/form/div[5]/div[5]/div/ng-select/div/div/div[2]/input");
	By labTestTxt1 = By.xpath(
			"//app-promotion-create-update/nb-card/nb-card-body/form/div[5]/div[5]/div/ng-select/div/div/div[3]/input");

	// pharmacy related fields
	By pharmacyTxt = By.xpath(
			"//app-promotion-create-update/nb-card/nb-card-body/form/div[5]/div[4]/div/ng-select/div/div/div[@role='combobox']/input");
	By selectedProductTxt = By.xpath(
			"//app-promotion-create-update/nb-card/nb-card-body/form/div[5]/div[5]/div/ng-select/div/div/div[@role='combobox']/input");

	// Brand related fields
	By brandTxt = By.xpath(
			"//app-promotion-create-update/nb-card/nb-card-body/form/div[5]/div[4]/div/ng-select/div/div/div[2]/input");

	By applyOnOfferCheck =By.xpath("//label/span[contains(text(),' Apply On Discounted Items')]/preceding-sibling::span");
	public AdminProfilePromotionPage(WebDriver driver) {
		super();
	}

	// Verify creating promo code for doctor with a specific service
	public String verifyCreatePromotionForDoctor(String code, String titleEn, String titleAr, String userPerCoupon,
			String numberOfCoupons, String discountType, String discount, String promoFor, String doctor,
			String serviceName, String tcID, String scenario) {
		waitSometime();
		explicitWait(promotionHeader);
		driver.findElement(createBtn).click();
		explicitWait(createPromotionHeader);
		String promoCode = code + "_" + GenerateRandomNumber(3);
		driver.findElement(codeTxt).sendKeys(promoCode);
		driver.findElement(titleEnTxt).sendKeys(titleEn);
		driver.findElement(titleArTxt).sendKeys(titleAr);
		driver.findElement(startDateTxt).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3) + " "
				+ getDayValue(getTodayDateInYYYYMMDD()) + ", " + getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(endDateTxt).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3) + " "
				+ getDayValue(getTomorrowDateInYYYYMMDD()) + ", " + getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(userPerCouponTxt).sendKeys(userPerCoupon);
		waitASecond();
		driver.findElement(userPerCouponTxt).sendKeys(Keys.DOWN);
		driver.findElement(userPerCouponTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(numberOfCouponTxt).sendKeys(numberOfCoupons);
		driver.findElement(discountTypeTxt).sendKeys(discountType);
		waitASecond();
		driver.findElement(discountTypeTxt).sendKeys(Keys.DOWN);
		driver.findElement(discountTypeTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(discountTxt).sendKeys(discount);
		driver.findElement(promoForTxt).sendKeys(promoFor);
		waitASecond();
		driver.findElement(promoForTxt).sendKeys(Keys.DOWN);
		driver.findElement(promoForTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(doctorTxt).sendKeys(doctor);
		waitASecond();
		driver.findElement(doctorTxt).sendKeys(Keys.DOWN);
		driver.findElement(doctorTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(doctorServiceTxt).sendKeys(serviceName);
		waitASecond();
		driver.findElement(doctorServiceTxt).sendKeys(Keys.DOWN);
		driver.findElement(doctorServiceTxt).sendKeys(Keys.RETURN);
		waitASecond();
		takeScreenShot(driver, "CreatePromotion_Doctor", tcID, scenario);
		moveToElement(driver.findElement(saveBtn));
		driver.findElement(saveBtn).click();
		waitASecond();
		driver.findElement(codeSearchTxt).sendKeys(promoCode);
		if (driver.findElement(searchedPromoCode).getText().trim().equalsIgnoreCase(promoCode)) {
			waitSometime();
			clickUsingJS(By.xpath(
					"//app-promotion-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[11]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-checkbox-switch/div/input"));
			takeScreenShot(driver, "CreatedPromotion_SearchResult_Doctor", tcID, scenario);
		}
		return promoCode;
	}

	// Verify creating promo code for lab with a specific service
	public String verifyCreatePromotionForLab(String code, String titleEn, String titleAr, String userPerCoupon,
			String numberOfCoupons, String discountType, String discount, String promoFor, String medicalServiceName,
			String profileIndividualTestSelection, String profileIndividualTestSelection1, String tcID,
			String scenario) {
		waitSometime();
		explicitWait(promotionHeader);
		driver.findElement(createBtn).click();
		explicitWait(createPromotionHeader);
		String promoCode = code + "_" + GenerateRandomNumber(3);
		driver.findElement(codeTxt).sendKeys(promoCode);
		driver.findElement(titleEnTxt).sendKeys(titleEn);
		driver.findElement(titleArTxt).sendKeys(titleAr);
		driver.findElement(startDateTxt).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3) + " "
				+ getDayValue(getTodayDateInYYYYMMDD()) + ", " + getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(endDateTxt).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3) + " "
				+ getDayValue(getTomorrowDateInYYYYMMDD()) + ", " + getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(userPerCouponTxt).sendKeys(userPerCoupon);
		waitASecond();
		driver.findElement(userPerCouponTxt).sendKeys(Keys.DOWN);
		driver.findElement(userPerCouponTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(numberOfCouponTxt).sendKeys(numberOfCoupons);
		driver.findElement(discountTypeTxt).sendKeys(discountType);
		waitASecond();
		driver.findElement(discountTypeTxt).sendKeys(Keys.DOWN);
		driver.findElement(discountTypeTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(discountTxt).sendKeys(discount);
		driver.findElement(promoForTxt).sendKeys(promoFor);
		waitASecond();
		driver.findElement(promoForTxt).sendKeys(Keys.DOWN);
		driver.findElement(promoForTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(labTxt).sendKeys(medicalServiceName);
		waitASecond();
		driver.findElement(labTxt).sendKeys(Keys.DOWN);
		driver.findElement(labTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(labTestTxt).sendKeys(profileIndividualTestSelection);
		waitASecond();
		driver.findElement(labTestTxt).sendKeys(Keys.DOWN);
		driver.findElement(labTestTxt).sendKeys(Keys.RETURN);
		waitASecond();
		if (!profileIndividualTestSelection1.equalsIgnoreCase("")) {
			driver.findElement(labTestTxt1).sendKeys(profileIndividualTestSelection1);
			waitASecond();
			driver.findElement(labTestTxt1).sendKeys(Keys.DOWN);
			driver.findElement(labTestTxt1).sendKeys(Keys.RETURN);
			waitASecond();
		}
		takeScreenShot(driver, "CreatePromotion_Lab", tcID, scenario);
		moveToElement(driver.findElement(saveBtn));
		driver.findElement(saveBtn).click();
		waitASecond();
		driver.findElement(codeSearchTxt).sendKeys(promoCode);
		if (driver.findElement(searchedPromoCode).getText().trim().equalsIgnoreCase(promoCode)) {
			waitSometime();
			clickUsingJS(By.xpath(
					"//app-promotion-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[11]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-checkbox-switch/div/input"));
			takeScreenShot(driver, "CreatedPromotion_SearchResult_Lab", tcID, scenario);
		}
		return promoCode;
	}

	// Verify creating promo code for pharmacy with a specific product
	public String verifyCreatePromotionForPharmacy(String code, String titleEn, String titleAr, String userPerCoupon,
			String numberOfCoupons, String discountType, String discount, String promoFor, String pharmacy,
			String selectedProduct, String applyOnOffer, String tcID, String scenario) {
		waitSometime();
		explicitWait(promotionHeader);
		driver.findElement(createBtn).click();
		explicitWait(createPromotionHeader);
		String promoCode = code + "_" + GenerateRandomNumber(3);
		driver.findElement(codeTxt).sendKeys(promoCode);
		driver.findElement(titleEnTxt).sendKeys(titleEn);
		driver.findElement(titleArTxt).sendKeys(titleAr);
		driver.findElement(startDateTxt).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3) + " "
				+ getDayValue(getTodayDateInYYYYMMDD()) + ", " + getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(endDateTxt).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3) + " "
				+ getDayValue(getTomorrowDateInYYYYMMDD()) + ", " + getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(userPerCouponTxt).sendKeys(userPerCoupon);
		waitASecond();
		driver.findElement(userPerCouponTxt).sendKeys(Keys.DOWN);
		driver.findElement(userPerCouponTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(numberOfCouponTxt).sendKeys(numberOfCoupons);
		driver.findElement(discountTypeTxt).sendKeys(discountType);
		waitASecond();
		driver.findElement(discountTypeTxt).sendKeys(Keys.DOWN);
		driver.findElement(discountTypeTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(discountTxt).sendKeys(discount);
		driver.findElement(promoForTxt).sendKeys(promoFor);
		waitASecond();
		driver.findElement(promoForTxt).sendKeys(Keys.DOWN);
		driver.findElement(promoForTxt).sendKeys(Keys.RETURN);
		waitSometime();
		driver.findElement(pharmacyTxt).sendKeys(pharmacy);
		waitASecond();
		driver.findElement(pharmacyTxt).sendKeys(Keys.DOWN);
		driver.findElement(pharmacyTxt).sendKeys(Keys.RETURN);
		waitASecond();
		moveToElement(driver.findElement(selectedProductTxt));
		driver.findElement(selectedProductTxt).sendKeys(selectedProduct);
		waitASecond();
		driver.findElement(selectedProductTxt).sendKeys(Keys.DOWN);
		driver.findElement(selectedProductTxt).sendKeys(Keys.RETURN);
		waitASecond();
		if(applyOnOffer.equalsIgnoreCase("Yes")) {
			moveToElement(driver.findElement(applyOnOfferCheck));
			driver.findElement(applyOnOfferCheck).click();
		}
		takeScreenShot(driver, "CreatePromotion_Pharmacy", tcID, scenario);
		moveToElement(driver.findElement(saveBtn));
		driver.findElement(saveBtn).click();
		waitASecond();
		driver.findElement(codeSearchTxt).sendKeys(promoCode);
		if (driver.findElement(searchedPromoCode).getText().trim().equalsIgnoreCase(promoCode)) {
			waitSometime();
			clickUsingJS(By.xpath(
					"//app-promotion-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[11]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-checkbox-switch/div/input"));
			takeScreenShot(driver, "CreatedPromotion_SearchResult_Pharmacy", tcID, scenario);
		}
		return promoCode;
	}

	// Verify creating promo code for brand
	public String verifyCreatePromotionForBrand(String code, String titleEn, String titleAr, String userPerCoupon,
			String numberOfCoupons, String discountType, String discount, String promoFor, String brand, String tcID,
			String scenario) {
		waitSometime();
		explicitWait(promotionHeader);
		driver.findElement(createBtn).click();
		explicitWait(createPromotionHeader);
		String promoCode = code + "_" + GenerateRandomNumber(3);
		driver.findElement(codeTxt).sendKeys(promoCode);
		driver.findElement(titleEnTxt).sendKeys(titleEn);
		driver.findElement(titleArTxt).sendKeys(titleAr);
		driver.findElement(startDateTxt).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3) + " "
				+ getDayValue(getTodayDateInYYYYMMDD()) + ", " + getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(endDateTxt).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3) + " "
				+ getDayValue(getTomorrowDateInYYYYMMDD()) + ", " + getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(userPerCouponTxt).sendKeys(userPerCoupon);
		waitASecond();
		driver.findElement(userPerCouponTxt).sendKeys(Keys.DOWN);
		driver.findElement(userPerCouponTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(numberOfCouponTxt).sendKeys(numberOfCoupons);
		driver.findElement(discountTypeTxt).sendKeys(discountType);
		waitASecond();
		driver.findElement(discountTypeTxt).sendKeys(Keys.DOWN);
		driver.findElement(discountTypeTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(discountTxt).sendKeys(discount);
		driver.findElement(promoForTxt).sendKeys(promoFor);
		waitASecond();
		driver.findElement(promoForTxt).sendKeys(Keys.DOWN);
		driver.findElement(promoForTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(brandTxt).sendKeys(brand);
		waitASecond();
		driver.findElement(brandTxt).sendKeys(Keys.DOWN);
		driver.findElement(brandTxt).sendKeys(Keys.RETURN);
		waitASecond();
		takeScreenShot(driver, "CreatePromotion_Brand", tcID, scenario);
		moveToElement(driver.findElement(saveBtn));
		driver.findElement(saveBtn).click();
		waitASecond();
		driver.findElement(codeSearchTxt).sendKeys(promoCode);
		if (driver.findElement(searchedPromoCode).getText().trim().equalsIgnoreCase(promoCode)) {
			waitSometime();
			clickUsingJS(By.xpath(
					"//app-promotion-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[11]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-checkbox-switch/div/input"));
			takeScreenShot(driver, "CreatedPromotion_SearchResult_Brand", tcID, scenario);
		}
		return promoCode;
	}

	// Deleting the created promo after applying in the frontend.
	public boolean deleteCreatedPromotion(String promoCode) {
		explicitWait(codeSearchTxt);
		driver.findElement(codeSearchTxt).sendKeys(promoCode);
		waitSometime();
		driver.findElement(deleteBtn).click();
		driver.switchTo().alert().accept();
		waitSometime();
		waitForPageLoad();
		return true;
	}
}
