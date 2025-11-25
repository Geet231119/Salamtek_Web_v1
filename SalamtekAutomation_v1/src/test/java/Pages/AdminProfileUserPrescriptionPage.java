package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import Common.BaseTest;

public class AdminProfileUserPrescriptionPage extends BaseTest {
	By userPrescriptionHeader = By
			.xpath("//app-user-prescription-index/nb-card/nb-card-header[contains(text(),'User Prescription')]");
	By firstPrescriptionLbl = By.xpath(
			"//app-user-prescription-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr[1]/td[2]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	By firstAssignLnk = By.xpath(
			"//app-user-prescription-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr[1]/td[1]/ng2-st-tbody-custom/a[1]");
	By assignPrescriptionHeader = By
			.xpath("//app-assign-prescription/nb-card/nb-card-header[contains(text(),'Assign Prescription')]");
	By searchProductName = By.xpath(
			"//app-assign-prescription/nb-card/nb-card-body/form/div[1]/div[1]/nb-card/nb-card-body/div[1]/div/div/ng-select/div/div/div[2]/input");
	By selectProductNameDrpDwn = By.xpath(
			"//app-assign-prescription/nb-card/nb-card-body/form/div[1]/div[1]/nb-card/nb-card-body/div[1]/div/div/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By searchPharmacyName = By.xpath(
			"//app-assign-prescription/nb-card/nb-card-body/form/div[1]/div[1]/nb-card/nb-card-body/div[2]/div/div/ng-select/div/div/div[2]/input");
	By searchPharmacyNameDrpDwn = By.xpath(
			"//app-assign-prescription/nb-card/nb-card-body/form/div[1]/div[1]/nb-card/nb-card-body/div[2]/div/div/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By firstValueInPrescriptionProducts = By.xpath(
			"//app-assign-prescription/nb-card/nb-card-body/form/div[2]/div/nb-card/nb-card-body/table/tr[2]/td[1]/input[2]");
	By saveBtn = By
			.xpath("//app-assign-prescription/nb-card/nb-card-body/form/div[3]/div/button[contains(text(),'Save')]");
	By acceptedCheckBx = By.xpath(
			"//app-user-prescription-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr[1]/td[4]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-checkbox-switch/div/input");

	public AdminProfileUserPrescriptionPage(WebDriver driver) {
		super();
	}

	// Validate assigning of product to the user prescription
	public Boolean verifyAssignProductToPrescription(String prescriptionName, String productName, String pharmacyName,
			String pageName, String tcID, String scenario) {
		boolean result = false;
		waitSometime();
		explicitWait(userPrescriptionHeader);
		if (driver.findElement(firstPrescriptionLbl).getText().trim().equalsIgnoreCase(prescriptionName)) {
			driver.findElement(firstAssignLnk).click();
			waitASecond();
			explicitWait(assignPrescriptionHeader);
			driver.findElement(searchProductName).sendKeys(productName);
			waitSometime();
			driver.findElement(searchProductName).sendKeys(Keys.DOWN);
			driver.findElement(searchProductName).sendKeys(Keys.RETURN);
			waitSometime();
			driver.findElement(searchPharmacyName).sendKeys(pharmacyName);
			waitSometime();
			driver.findElement(searchPharmacyName).sendKeys(Keys.DOWN);
			driver.findElement(searchPharmacyName).sendKeys(Keys.RETURN);
			waitSometime();
			takeScreenShot(driver, pageName, tcID, scenario);
			driver.findElement(saveBtn).click();
			waitASecond();
			if (driver.findElement(acceptedCheckBx).getAttribute("value").equalsIgnoreCase("1")) {
				result = true;
			}
		} else {
			result = false;
		}
		return result;
	}
}
