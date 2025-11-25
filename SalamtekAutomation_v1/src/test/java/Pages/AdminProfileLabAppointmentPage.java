package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class AdminProfileLabAppointmentPage extends BaseTest {
	By labAppointmentScreenIdentifier = By.xpath(
			"//app-lab-appointment-index/nb-card/nb-card-header[contains(text(),'Medical Services Appointment')]");
	By appointmentNumberTxt = By.xpath(
			"//app-lab-appointment-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[2]/ng2-smart-table-filter/div/default-table-filter/input-filter/input");
	By viewBtn = By.xpath(
			"//app-lab-appointment-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[1]/ng2-st-tbody-custom/a[1]/i");
	By accountInformationHeader = By
			.xpath("//app-lab-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[1]/table/tr[1]/th");
	By startBtn = By.xpath("//a/b[contains(text(),'START')]");
	By uploadReportBtn = By.xpath("//a/b[contains(text(),'UPLOAD RFEPORTS')]");
	By cancelBtn = By.xpath("//a/b[contains(text(),'CANCEL')]");
	By reportUploadHeader = By
			.xpath("//*[@id=\"cdk-overlay-0\"]/nb-dialog-container/app-report/nb-card/nb-card-header");
	By titleInEnglishTxt = By.name("titleEn");
	By titleInArabicTxt = By.name("titleAr");
	By descriptionInEnglishTxt = By.name("descriptionEn");
	By descriptionInArabicTxt = By.name("descriptionAr");
	By fileUploadTxt = By.name("file");
	By saveBtn = By.xpath(
			"//*[@id=\"cdk-overlay-0\"]/nb-dialog-container/app-report/nb-card/nb-card-body/form/div[3]/div/button");
	By completeBtn = By.xpath("//a/b[contains(text(),'COMPLETE')]");
	By completeSectionFromLeftPanel = By
			.xpath("//a[@title='Completed' and @href='/pages/lab-appointment/completed']/span");
	By appointmentNumberPresence = By.xpath(
			"//app-lab-appointment-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[2]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	By selectRefundTypeHeader = By
			.xpath("//*[@class='cdk-overlay-pane']/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-header");
	By refundToWalletBtn = By.xpath(
			"//*[@class='cdk-overlay-pane']/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-body/form/div[1]/div/nb-radio-group/nb-radio[1]/label/span[2]");
	By refundToBank = By.xpath(
			"//*[@class='cdk-overlay-pane']/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-body/form/div[1]/div/nb-radio-group/nb-radio[2]/label/span[1]");
	By saveBtnForRefund = By.xpath(
			"//*[@class='cdk-overlay-pane']/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-body/form/div[2]/div/button");
	By cancelSectionFromLeftPanel = By
			.xpath("//a[@title='Cancelled' and @href='/pages/lab-appointment/cancelled']/span");
	By calculateReferralBtn = By.xpath("//a[contains(text(),'Calculate Referral')]");

	public AdminProfileLabAppointmentPage(WebDriver driver) {
		super();
	}

	// Validate whether appointment is created or not
	public boolean verifyAppointmentPresent(String bookingID) throws Exception {
		explicitWait(labAppointmentScreenIdentifier);
		driver.findElement(appointmentNumberTxt).clear();
		driver.findElement(appointmentNumberTxt).sendKeys(bookingID.substring(1));
		waitSometime();
		driver.findElement(viewBtn).click();
		waitForSpecificTime(3000);
		System.out.println(
				"++++++++++++++++     " + driver.findElement(accountInformationHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(accountInformationHeader).getText().trim().toUpperCase(),
				"ACCOUNT INFORMATION");
		return true;
	}

	// Validates upload report and completion of an appointment
	public boolean verifyUploadAndComplete(String titleEn, String titleAr, String descEn, String descAr,
			String bookingID, String tcID, String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		driver.findElement(uploadReportBtn).click();
		explicitWait(reportUploadHeader);
		driver.findElement(titleInEnglishTxt).sendKeys(titleEn);
		driver.findElement(titleInArabicTxt).sendKeys(titleAr);
		driver.findElement(descriptionInEnglishTxt).sendKeys(descEn);
		driver.findElement(descriptionInArabicTxt).sendKeys(descAr);
		driver.findElement(fileUploadTxt).sendKeys(System.getProperty("user.dir") + "\\Files\\sample.pdf");
		waitSometime();
		takeScreenShot(driver, "UploadingFile", tcID, sheet);
		driver.findElement(saveBtn).click();
		waitForSpecificTime(2000);
		takeScreenShot(driver, "UploadedFileSuccessfully", tcID, sheet);
		moveMousePointerAway();
		waitSometime();
		driver.findElement(completeBtn).click();
		waitSometime();
		driver.findElement(completeSectionFromLeftPanel).click();
		waitSometime();
		driver.findElement(appointmentNumberTxt).sendKeys(bookingID.substring(1));
		waitSometime();
		takeScreenShot(driver, "CompletedAppointment", tcID, sheet);
		System.out.println("++++++++++++++++     " + driver.findElement(appointmentNumberPresence).getText().trim());
		Assert.assertEquals(driver.findElement(appointmentNumberPresence).getText().trim(), bookingID.substring(1));
		return true;
	}

	// Validate cancellation of an appointment from admin side
	public boolean verifyCancel(String refundType, String bookingID, String tcID, String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		driver.findElement(cancelBtn).click();
		driver.switchTo().alert().accept();
		explicitWait(selectRefundTypeHeader);
		waitSometime();
		if (driver.findElement(selectRefundTypeHeader).getText().trim().equalsIgnoreCase("Select Refund Type")) {
			switch (refundType) {
			case "wallet":
				driver.findElement(refundToWalletBtn).click();
				break;
			case "bank":
				driver.findElement(refundToBank).click();
				break;
			}
			takeScreenShot(driver, "ReFundToWallet", tcID, sheet);
			driver.findElement(saveBtnForRefund).click();
		}
		waitSometime();
		driver.findElement(cancelSectionFromLeftPanel).click();
		waitSometime();
		driver.findElement(appointmentNumberTxt).sendKeys(bookingID.substring(1));
		waitSometime();
		takeScreenShot(driver, "CancelledAppointment", tcID, sheet);
		System.out.println("++++++++++++++++     " + driver.findElement(appointmentNumberPresence).getText().trim());
		Assert.assertEquals(driver.findElement(appointmentNumberPresence).getText().trim(), bookingID.substring(1));
		return true;
	}

	// Validate completion of an appointment
	public boolean verifyComplete(String bookingID, String tcID, String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		waitSometime();
		driver.findElement(startBtn).click();
		waitSometime();
		driver.findElement(completeBtn).click();
		waitSometime();
		explicitWait(completeSectionFromLeftPanel);
		waitSometime();
		waitElementToBeClickable(completeSectionFromLeftPanel);
		driver.findElement(completeSectionFromLeftPanel).click();
		waitSometime();
		driver.findElement(appointmentNumberTxt).sendKeys(bookingID.substring(1));
		waitSometime();
		takeScreenShot(driver, "CompletedAppointment", tcID, sheet);
		System.out.println("++++++++++++++++     " + driver.findElement(appointmentNumberPresence).getText().trim());
		Assert.assertEquals(driver.findElement(appointmentNumberPresence).getText().trim(), bookingID.substring(1));
		return true;
	}

	// Validate calculation of referral while clicking Calculate Referral from
	// appointment details
	public boolean verifyCalculateReferralFromLab(String bookingID, String tcID, String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		waitSometime();
		driver.findElement(calculateReferralBtn).click();
		driver.switchTo().alert().accept();
		waitForSpecificTime(1000);
		takeScreenShot(driver, "CalculateReferralFromLab", tcID, sheet);
		Assert.assertTrue(driver.findElement(accountInformationHeader).isDisplayed());
		return true;
	}
}
