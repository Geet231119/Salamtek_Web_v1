package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.BaseTest;

public class AdminProfileVoucherPage extends BaseTest {
	By voucherHeader = By.xpath("//app-voucher-index/nb-card/nb-card-header[contains(text(),'Vouchers')]");
	By createBtn = By.xpath("//a[contains(text(),'Create')]");
	By createVoucherHeader = By
			.xpath("//app-voucher-create-update/nb-card/nb-card-header[contains(text(),'Create Voucher')]");
	By codeTxt = By.xpath("//app-voucher-create-update/nb-card/nb-card-body/form/div[1]/div[2]/div/input");
	By nameEnTxt = By.name("nameEn");
	By nameArTxt = By.name("nameAr");
	By amountTxt = By.name("amount");
	By validityTxt = By.name("validity");
	By saveBtn = By.xpath("//button[contains(text(),'Save')]");
	By codeSearchTxt = By.xpath("//app-voucher-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[4]/ng2-smart-table-filter/div/default-table-filter/input-filter/input");
	By searchedVoucherCode = By.xpath("//app-voucher-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[4]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	By deleteBtn = By.xpath("//app-voucher-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[1]/ng2-st-tbody-custom/a[3]/i");
	
	public AdminProfileVoucherPage(WebDriver driver) {
		super();
	}

	// Verify creating voucher
	public String verifyCreateVoucher(String titleEn, String titleAr, String voucherAmount,
			String validity, String tcID, String scenario) {
		waitSometime();
		explicitWait(voucherHeader);
		driver.findElement(createBtn).click();
		explicitWait(createVoucherHeader);
		waitASecond();
		String codeValue = getValueUsingJS(driver.findElement(codeTxt));
		System.out.println("Code Value: " + codeValue);
		driver.findElement(nameEnTxt).sendKeys(titleEn+"_"+GenerateRandomNumber(2));
		driver.findElement(nameArTxt).sendKeys(titleAr);
		driver.findElement(amountTxt).sendKeys(voucherAmount);
		driver.findElement(validityTxt).sendKeys(validity);
		waitASecond();
		takeScreenShot(driver, "CreateVoucherDetails", tcID, scenario);
		moveToElement(driver.findElement(saveBtn));
		driver.findElement(saveBtn).click();
		waitASecond();
		driver.findElement(codeSearchTxt).sendKeys(codeValue);
		if (driver.findElement(searchedVoucherCode).getText().trim().equalsIgnoreCase(codeValue)) {
			waitSometime();
			clickUsingJS(By.xpath(
					"//app-voucher-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[9]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-checkbox-switch/div/input"));
			takeScreenShot(driver, "CreatedVoucher", tcID, scenario);
		}
		return codeValue;
	}

	// Deleting the created voucher after applying in the frontend.
	public boolean deleteCreatedVoucher(String voucherCode) {
		explicitWait(codeSearchTxt);
		driver.findElement(codeSearchTxt).sendKeys(voucherCode);
		waitSometime();
		driver.findElement(deleteBtn).click();
		driver.switchTo().alert().accept();
		waitSometime();
		waitForPageLoad();
		return true;
	}
}
