package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Common.BaseTest;

public class LabProfileLabAppointmentPage extends BaseTest {
	By labAppointmentScreenIdentifier = By.xpath(
			"//app-lab-appointment-index/nb-card/nb-card-header[contains(text(),'Medical Services Appointment')]");
	By appointmentNumberTxt = By.xpath(
			"//app-lab-appointment-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[2]/ng2-smart-table-filter/div/default-table-filter/input-filter/input");
	By viewBtn = By.xpath(
			"//app-lab-appointment-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[1]/ng2-st-tbody-custom/a/i");
	By accountInformationHeader = By
			.xpath("//app-lab-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[1]/table/tr[1]/th");
	By startBtn = By.xpath("//a/b[contains(text(),'START')]");
	By uploadReportBtn = By.xpath("//a/b[contains(text(),'UPLOAD REPORTS')]");
	By cancelBtn = By.xpath("//a/b[contains(text(),'CANCEL')]");
	By reportUploadHeader = By
			.xpath("//*[@class='cdk-overlay-pane']/nb-dialog-container/app-report/nb-card/nb-card-header");
	By titleInEnglishTxt = By.name("titleEn");
	By titleInArabicTxt = By.name("titleAr");
	By descriptionInEnglishTxt = By.name("descriptionEn");
	By descriptionInArabicTxt = By.name("descriptionAr");
	By fileUploadTxt = By.name("file");
	By saveBtn = By.xpath(
			"//*[@id=\"cdk-overlay-0\"]/nb-dialog-container/app-report/nb-card/nb-card-body/form/div[3]/div/button");
	By completeBtn = By.xpath("//a/b[contains(text(),'COMPLETE')]");
	By completeSectionFromLeftPanel = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li/ul/li[4]/a/span");
	By appointmentNumberPresence = By.xpath(
			"//app-lab-appointment-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[2]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	By selectRefundTypeHeader = By
			.xpath("//*[@class='cdk-overlay-pane']/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-header");
	By refundToWalletBtn = By.xpath(
			"/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[2]/div[2]/div/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-body/form/div[1]/div/nb-radio-group/nb-radio[1]/label/span[2]");
	By refundToBank = By.xpath(
			"//*[@class='cdk-overlay-pane']/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-body/form/div[1]/div/nb-radio-group/nb-radio[2]/label/span[@class='inner-circle']");
	By saveBtnForRefund = By.xpath(
			"/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[2]/div[2]/div/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-body/form/div[2]/div/button");
	By cancelSectionFromLeftPanel = By
			.xpath("//a[@title='Cancelled' and @href='/pages/lab-appointment/cancelled']/span");
	By calculateReferralBtn = By.xpath("//a[contains(text(),'Calculate Referral')]");

	// Elements related to insurance
	By approveAppointmentBtn = By
			.xpath("//app-lab-appointment-view/nb-card/nb-card-header/p/a[contains(text(),'Approve Appointment?')]");
	By rejectAppointmentBtn = By
			.xpath("//app-lab-appointment-view/nb-card/nb-card-header/p/a[contains(text(),'Reject Appointment?')]");
	By approveAppointmentHeader = By
			.xpath("//*[@class='cdk-overlay-pane']/nb-dialog-container/app-approval/nb-card/nb-card-header");
	By coveredByInsuranceTxt = By.xpath("//input[@formcontrolname='discount']");
	By insuranceDocumentBtn = By.xpath("//input[@formcontrolname='insuranceApprovalDocument']");
	By insuranceSaveBtn = By.xpath(
			"//*[@class=\"cdk-overlay-pane\"]/nb-dialog-container/app-approval/nb-card/nb-card-body/form/div[3]/div/button");

	
	//Elements related to Walk In
	By createLabAppointmentHeader = By.xpath("//app-lab-appointment-patient-create-update/nb-card/nb-card-header");
	By userTxt = By.xpath("//app-lab-appointment-patient-create-update/nb-card/nb-card-body/form/div[1]/div/div/ng-select/div/div/div[2]/input");
	By testTxt = By.xpath("//app-lab-appointment-patient-create-update/nb-card/nb-card-body/form/div[3]/div[1]/div/ng-select/div/div/div[2]/input");
	By appointmentTypeTxt = By.xpath("//app-lab-appointment-patient-create-update/nb-card/nb-card-body/form/div[3]/div[2]/div/ng-select/div/div/div[3]/input");
	By walkInSaveBtn = By.xpath("//app-lab-appointment-patient-create-update/nb-card/nb-card-body/form/div[5]/div/button");
	By firstAppointmentNumber = By.xpath("//app-lab-appointment-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr[1]/td[2]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	By selectDateAndTimeBtn = By.xpath("//app-lab-appointment-patient-create-update/nb-card/nb-card-body/form/div[3]/div[3]/div/button");
	By datePickerTxt = By.xpath("//app-date-time-picker-modal/nb-card/nb-card-body/form/div[1]/div/div/input");
	By getTimeSlotBtn = By.xpath("//app-date-time-picker-modal/nb-card/nb-card-body/form/button");
	By bookBtn = By.xpath("//app-date-time-picker-modal/nb-card/nb-card-body/form/div[3]/div/button[1]");
	
	public LabProfileLabAppointmentPage(WebDriver driver) {
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
		driver.findElement(startBtn).click();
		waitForSpecificTime(1000);
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

	// Validate cancellation of an appointment from lab side
	public boolean verifyCancel(String refundType, String bookingID, String tcID, String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		driver.findElement(cancelBtn).click();
		driver.switchTo().alert().accept();
		waitSometime();
		explicitWait(selectRefundTypeHeader);
		waitSometime();
		if (driver.findElement(selectRefundTypeHeader).getText().trim().equalsIgnoreCase("Select Refund Type")) {
			switch (refundType) {
			case "wallet":
				explicitWait(refundToWalletBtn);
				clickUsingJS(refundToWalletBtn);
				break;
			case "bank":
				driver.findElement(refundToBank).click();
				break;
			}
			takeScreenShot(driver, "ReFundToWallet", tcID, sheet);
			explicitWait(saveBtnForRefund);
			clickUsingJS(saveBtnForRefund);
		}
		waitSometime();
		explicitWait(cancelSectionFromLeftPanel);
		clickUsingJS(cancelSectionFromLeftPanel);
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

	// Validate insurance approval or rejection
	public boolean verifyInsurance(String approveOrReject, String coveredByInsuranceAmount, String bookingID,
			String tcID, String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		waitSometime();
		if (approveOrReject.equalsIgnoreCase("Approve")) {
			driver.findElement(approveAppointmentBtn).click();
			waitSometime();
			explicitWait(approveAppointmentHeader);
			driver.findElement(coveredByInsuranceTxt).sendKeys(coveredByInsuranceAmount);
			waitSometime();
			clickUsingJS(insuranceDocumentBtn);
			waitSometime();
			uploadPDF();
			waitSometime();
			driver.findElement(insuranceSaveBtn).click();
		} else {
			driver.findElement(rejectAppointmentBtn).click();
		}
		return true;
	}

	// Validate Walk In appointment creation
	public boolean verifyCreateWalkIn(String userName, String medicalServiceName, String profileIndividualTestSelection,String appointmentType,
			String tcID, String sheet) throws Exception {
		explicitWait(createLabAppointmentHeader);
		driver.findElement(userTxt).sendKeys(userName);
		waitASecond();
		driver.findElement(userTxt).sendKeys(Keys.DOWN);
		driver.findElement(userTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(testTxt).sendKeys(profileIndividualTestSelection);
		waitASecond();
		driver.findElement(testTxt).sendKeys(Keys.DOWN);
		driver.findElement(testTxt).sendKeys(Keys.RETURN);
		waitASecond();
		driver.findElement(appointmentTypeTxt).sendKeys(appointmentType);
		waitASecond();
		driver.findElement(appointmentTypeTxt).sendKeys(Keys.DOWN);
		driver.findElement(appointmentTypeTxt).sendKeys(Keys.RETURN);
		waitASecond();
		takeScreenShot(driver, "WalkInCreation", tcID, sheet);
		driver.findElement(walkInSaveBtn).click();
		return true;
	}
	
	
	// Validate normal appointment creation from create walk in
		public boolean verifyCreateAppointment(String userName, String medicalServiceName, String profileIndividualTestSelection,String appointmentType,
				String tcID, String sheet) throws Exception {
			explicitWait(createLabAppointmentHeader);
			driver.findElement(userTxt).sendKeys(userName);
			waitASecond();
			driver.findElement(userTxt).sendKeys(Keys.DOWN);
			driver.findElement(userTxt).sendKeys(Keys.RETURN);
			waitASecond();
			driver.findElement(testTxt).sendKeys(profileIndividualTestSelection);
			waitASecond();
			driver.findElement(testTxt).sendKeys(Keys.DOWN);
			driver.findElement(testTxt).sendKeys(Keys.RETURN);
			waitASecond();
			driver.findElement(appointmentTypeTxt).sendKeys(appointmentType);
			waitASecond();
			driver.findElement(appointmentTypeTxt).sendKeys(Keys.DOWN);
			driver.findElement(appointmentTypeTxt).sendKeys(Keys.RETURN);
			waitASecond();
			driver.findElement(selectDateAndTimeBtn).click();
			WebElement inputField = driver.findElement(datePickerTxt);
			Actions actions = new Actions(driver);
			actions.sendKeys(inputField, getTomorrowDateInYYYYMMDD()).perform();
			waitSometime();
			clickUsingJS(bookBtn);
			waitSometime();
			driver.findElement(getTimeSlotBtn).click();
			List<WebElement> radioList = driver.findElements(By.xpath(
					"//div[@class='cdk-overlay-pane']/nb-dialog-container/app-date-time-picker-modal/nb-card/nb-card-body/form/div[2]/div/nb-radio-group/nb-radio"));
			System.out.println("size radiolist: " + radioList.size());
			for (int i = 1; i <= radioList.size(); i = i + 2) {
				System.out.println("text:" + driver.findElement(By.xpath(
						"//div[@class='cdk-overlay-pane']/nb-dialog-container/app-date-time-picker-modal/nb-card/nb-card-body/form/div[2]/div/nb-radio-group/nb-radio["
								+ i + "]/label/span[3]"))
						.getText());
				if (driver.findElement(By.xpath(
						"//div[@class='cdk-overlay-pane']/nb-dialog-container/app-date-time-picker-modal/nb-card/nb-card-body/form/div[2]/div/nb-radio-group/nb-radio["
								+ i + "]/label/span[3]"))
						.getText().contains("Booked")) {
					continue;
				} else {
					driver.findElement(By.xpath(
							"//div[@class='cdk-overlay-pane']/nb-dialog-container/app-date-time-picker-modal/nb-card/nb-card-body/form/div[2]/div/nb-radio-group/nb-radio["
									+ i + "]/label/span[2]"))
							.click();
					break;
				}
			}
			driver.findElement(bookBtn).click();
			waitSometime();
			takeScreenShot(driver, "AppointmentCreation", tcID, sheet);
			moveToElement(driver.findElement(walkInSaveBtn));
			driver.findElement(walkInSaveBtn).click();
			return true;
		}
	// Validate getting appointment number from WalkIn
		public String getAppointmentNumber() throws Exception {
			String appointmentNumber = "";
			explicitWait(labAppointmentScreenIdentifier);
			appointmentNumber = driver.findElement(firstAppointmentNumber).getText();
			return appointmentNumber;
		}
}
