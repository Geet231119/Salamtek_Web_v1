package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Common.BaseTest;

public class DoctorProfileAppointmentPage extends BaseTest {
	By doctorAppointmentScreenIdentifier = By
			.xpath("//app-doctor-appointment-index/nb-card/nb-card-header[contains(text(),'Doctor Appointment')]");
	By appointmentNumberTxt = By.xpath(
			"//app-doctor-appointment-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[2]/ng2-smart-table-filter/div/default-table-filter/input-filter/input");
	By viewBtn = By.xpath(
			"//app-doctor-appointment-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[1]/ng2-st-tbody-custom/a/i");
	By accountInformationHeader = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[1]/table/tr[1]/th/b");
	By rightArrowBtn = By.xpath("//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[3]/button");
	By startLnk = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[2]/div[1]/a/div/span/a/b[contains(text(),'START')]");
	By rescheduleLnk = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[2]/div[2]/a/div/span/a[contains(text(),'RESCHEDULE')]");
	By uploadReportLnk = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[2]/div[7]/a/div/span/a[contains(text(),'UPLOAD REPORTS')]");
	By completeLnk = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[2]/div[9]/a/div/span/a[contains(text(),'COMPLETE')]");
	By diagnosticNotesLnk = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[2]/div[4]/a/div/span/a[contains(text(),'DIAGNOSTIC NOTES')]");
	By cancelLnk = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[2]/div[10]/a/div/span/a[contains(text(),'CANCEL')]");
	By followUpAppointmentLnk = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[2]/div[8]/a/div/span/a[contains(text(),'FOLLOW UP')]");
	By prescribeMedicineLnk = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[2]/div[6]/a/div/span/a[contains(text(),'PRESCRIBE MEDICINE')]");
	By conductTestLnk = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[2]/div[5]/a/div/span/a[contains(text(),'CONDUCT TEST')]");
	By ehrPermissionLnk = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[2]/div[3]/a/div/span/a[contains(text(),'EHR PERMISSIONS')]");
	By viewEHRLnk = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/section/div/div[2]/div[3]/a/div/span/a[contains(text(),'VIEW EHR')]");
	By rescheduleHeader = By.xpath(
			"//div[@class='cdk-overlay-pane']/nb-dialog-container/app-date-time-picker-modal/nb-card/nb-card-header");
	By datePickerTxt = By.xpath(
			"//div[@class='cdk-overlay-pane']/nb-dialog-container/app-date-time-picker-modal/nb-card/nb-card-body/form/div[1]/div/div/input");
	By getTimeSlotBtn = By.xpath("//app-date-time-picker-modal/nb-card/nb-card-body/form/button");
	By selectFirstTimeBtn = By.xpath(
			"//div[@class='cdk-overlay-pane']/nb-dialog-container/app-date-time-picker-modal/nb-card/nb-card-body/form/div[2]/div/nb-radio-group/nb-radio[1]/label/span[2]");
	By bookBtn_r = By.xpath(
			"/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[2]/div[4]/div/nb-dialog-container/app-date-time-picker-modal/nb-card/nb-card-body/form/div[3]/div/button[1]");
	By bookBtn = By.xpath(
			"//app-date-time-picker-modal/nb-card/nb-card-body/form/div[3]/div/button[1]");
	By appointmentDayInBookingInfo = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[2]/table/tr[6]/td");
	By startedSectionFromLeftPanel = By
			.xpath("//a[@title='Started' and @href='/pages/doctor-appointment/started']/span");
	By completeSectionFromLeftPanel = By
			.xpath("//a[@title='Completed' and @href='/pages/doctor-appointment/completed']/span");
	By appointmentNumberPresence = By.xpath(
			"//app-doctor-appointment-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr[1]/td[2]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	By followUpAppointmentInfo = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[5]/table/tr[3]/td[3]");
	By cashReceivedTxt = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[3]/table/tr[7]/th[contains(text(),'Cash Received?')]");
	By cashReceivedChkbx = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[3]/table/tr[7]/td/div/input");
	By resultLbl = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[4]/table/tr[3]/td");
	By nextBtn = By.xpath("//*[@id=\"next-slide\"]/button");
	// Upload related elements
	By reportUploadHeader = By
			.xpath("//div[@class='cdk-overlay-pane']/nb-dialog-container/app-report/nb-card/nb-card-header");
	By titleInEnglishTxt = By.name("titleEn");
	By titleInArabicTxt = By.name("titleAr");
	By descriptionInEnglishTxt = By.name("descriptionEn");
	By descriptionInArabicTxt = By.name("descriptionAr");
	By fileUploadTxt = By.name("file");
	By saveBtn = By.xpath(
			"//div[@class='cdk-overlay-pane']/nb-dialog-container/app-report/nb-card/nb-card-body/form/div[3]/div/button");

	// Add diagnostic notes related elements
	By addDiagnosticNotesHeader = By
			.xpath("//div[@class='cdk-overlay-pane']/nb-dialog-container/app-note/nb-card/nb-card-header");
	By addNotesTxt = By.xpath("//div[@class='cke_contents cke_reset']/div/p");
	By noteSaveBtn = By.xpath(
			"//div[@class='cdk-overlay-pane']/nb-dialog-container/app-note/nb-card/nb-card-body/form/div[2]/div/button");
	By actualNotes = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[5]/table/tr[3]/td[1]/p");
	By diagnosisNoteInfo = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[5]/table/tr[1]/th");

	// Cancel related elements
	By selectRefundTypeHeader = By
			.xpath("//*[@class='cdk-overlay-pane']/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-header");
	By refundToWalletBtn = By.xpath(
			"//*[@class='cdk-overlay-pane']/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-body/form/div[1]/div/nb-radio-group/nb-radio[1]/label/span[2]");
	By refundToBank = By.xpath(
			"//*[@class='cdk-overlay-pane']/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-body/form/div[1]/div/nb-radio-group/nb-radio[2]/label/span[1]");
	By saveBtnForRefund = By.xpath(
			"//*[@class='cdk-overlay-pane']/nb-dialog-container/app-refund-type-popup/nb-card/nb-card-body/form/div[2]/div/button");
	By cancelSectionFromLeftPanel = By
			.xpath("//a[@title='Cancelled' and @href='/pages/doctor-appointment/cancelled']/span");

	// followup appointment related elements
	By followUpAppointmentSection = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[5]/table/tr[1]/th");

	// prescribe medicine related elements
	By prescribeMedicineHeader = By.xpath("//app-eprescription/nb-card/nb-card-header");
	By prescriptionTypeTxt = By
			.xpath("//app-eprescription/nb-card/nb-card-body/form/div[1]/div/div/ng-select/div/div/div[3]/input");
	By totalUsageTxt = By.name("totalUsage");
	By searchProductTxt = By.xpath(
			"//app-eprescription/nb-card/nb-card-body/form/div[3]/nb-card-body[1]/div[2]/div/div/ng-select/div/div/div[2]/input");
	By searchProductDrpDwn = By.xpath(
			"//app-eprescription/nb-card/nb-card-body/form/div[3]/nb-card-body[1]/div[2]/div/div/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By durationTxt = By.name("duration");
	By instructionTxt = By.name("comment");
	By referredPharmacyTxt = By.xpath(
			"//div[@class='cdk-overlay-pane']/nb-dialog-container/app-eprescription/nb-card/nb-card-body/form/div[3]/div[2]/div/div/ng-select/div/div/div[2]/input");
	By referredPharmacyDrpDwn = By.xpath(
			"//app-eprescription/nb-card/nb-card-body/form/div[3]/div[2]/div/div/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By saveBtnForPrescribeMedicine = By.xpath(
			"//div[@class='cdk-overlay-pane']/nb-dialog-container/app-eprescription/nb-card/nb-card-body/form/div[4]/div/button[1]");
	By prescriptionInformationHeader = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[5]/table/tr/th/b");
	By prescribedMedicineDetails = By.xpath(
			"//app-doctor-appointment-view/nb-card/nb-card-header/nb-card/nb-card-body/div/div[5]/table/nb-card-body/nb-card/nb-card-header/span[1]");
	By exrNumberTxt = By.name("exrNumber");
	String exrNumber;

	// Conduct test related elements
	By serviceTypeTxt = By.xpath(
			"//app-eprescription/nb-card/nb-card-body/form/div[3]/div[1]/div/div/ng-select/div/div/div[2]/input");
	By serviceTypeDrpDwn = By.xpath(
			"//app-eprescription/nb-card/nb-card-body/form/div[3]/div[1]/div/div/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By serviceTxt = By.xpath(
			"//app-eprescription/nb-card/nb-card-body/form/div[3]/div[2]/div[1]/div/ng-select/div/div/div[2]/input");
	By serviceDrpDwn = By.xpath(
			"//app-eprescription/nb-card/nb-card-body/form/div[3]/div[2]/div[1]/div/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By referredLabTxt = By.xpath(
			"//app-eprescription/nb-card/nb-card-body/form/div[3]/div[2]/div[2]/div/ng-select/div/div/div[3]/input");
	By referredDrpDwn = By.xpath(
			"//app-eprescription/nb-card/nb-card-body/form/div[3]/div[2]/div[2]/div/ng-select/ng-dropdown-panel/div/div[2]/div/span");

	// EHR permission related elements
	By requestUserReportHeader = By.xpath("//app-user-records-listing/nb-card/nb-card-header");
	By reportTypeTxt = By.xpath(
			"//app-user-records-listing/nb-card/nb-card-body/form/div[1]/div/div/ng-select/div/div/div[3]/input");
	By reportTypeDrpDwn = By.xpath(
			"//app-user-records-listing/nb-card/nb-card-body/form/div[1]/div/div/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By requestAccessButton = By.xpath("//app-user-records-listing/nb-card/nb-card-body/form/div[3]/div/button[1]");
	By acceptedReportRequestHeader = By
			.xpath("//*[@id=\"reportRequests\"]/table/tr[1]/th/b[contains(text(),'ACCEPTED REPORT REQUESTS')]");
	By reportNamePresence = By.xpath("//*[@id=\"reportRequests\"]/table/tr[2]/table/tr[2]/td[1]");
	By downloadAttachmentLnk = By.xpath("//*[@id=\"reportRequests\"]/table/tr[2]/table/tr[2]/td[3]/a");

	public DoctorProfileAppointmentPage(WebDriver driver) {
		super();
	}

	// Validates whether appointment is present or not
	public boolean verifyAppointmentPresent(String bookingID) throws Exception {
		driver.get("https://doctor-dev.salamtek.com/pages/doctor-appointment/upcoming");
		//waitForSpecificTime(15000);
		waitForSpecificTime(8000);
		explicitWait(doctorAppointmentScreenIdentifier);
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

	// Validates appointment start
	public boolean verifyStartingAnAppointment(String bookingID, String tcID, String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		driver.findElement(startLnk).click();
		waitSometime();
		driver.switchTo().alert().accept();
		driver.findElement(startedSectionFromLeftPanel).click();
		waitSometime();
		driver.findElement(appointmentNumberTxt).sendKeys(bookingID.substring(1));
		waitSometime();
		takeScreenShot(driver, "StartedApoointment", tcID, sheet);
		System.out.println("++++++++++++++++     " + driver.findElement(appointmentNumberPresence).getText().trim());
		Assert.assertEquals(driver.findElement(appointmentNumberPresence).getText().trim(), bookingID.substring(1),
				"Appointment number is not present");
		return true;
	}

	// Validates reschedule of an appointment
	public boolean verifyRescheduleAnAppointment(String bookingID, String tcID, String sheet) throws Exception {
		Boolean status = false;
		explicitWait(accountInformationHeader);
		driver.findElement(rescheduleLnk).click();
		waitSometime();
		if (driver.findElement(rescheduleHeader).getText().trim()
				.equalsIgnoreCase("Choose a Date and Time to Reschedule")) {
			WebElement inputField = driver.findElement(datePickerTxt);
			Actions actions = new Actions(driver);
			actions.sendKeys(inputField, getTomorrowDateInYYYYMMDD()).perform();
			waitSometime();
			clickUsingJS(bookBtn);
			waitSometime();
			driver.findElement(By.xpath(
					"/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[2]/div[4]/div/nb-dialog-container/app-date-time-picker-modal/nb-card/nb-card-body/form/button[contains(text(),'Get Timeslots')]")).click();
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
			takeScreenShot(driver, "RescheduleAppointmentDetails", tcID, sheet);
			driver.findElement(bookBtn_r).click();

		}
		waitSometime();
		takeScreenShot(driver, "RescheduledApoointment", tcID, sheet);
		Assert.assertTrue(driver.findElement(appointmentDayInBookingInfo).isDisplayed(),
				"Appointment Information is not present");
		if (driver.findElement(appointmentDayInBookingInfo).getText().contains(getTomorrowDateInYYYYMMDD()))
			status = true;
		else
			status = false;
		return status;
	}

	// Validates upload report and completion of an appointment
	public boolean verifyUploadAndComplete(String titleEn, String titleAr, String descEn, String descAr,
			String bookingID, String tcID, String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		driver.findElement(uploadReportLnk).click();
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
		// moveMousePointerAway();
		waitSometime();
		for (int i = 1; i <= 2; i++) {
			driver.findElement(rightArrowBtn).click();
			waitSometime();
		}
		driver.findElement(completeLnk).click();
		waitSometime();
		// driver.findElement(completeSectionFromLeftPanel).click();
		driver.get("https://doctor-dev.salamtek.com/pages/doctor-appointment/completed");
		waitForSpecificTime(15000);
		driver.findElement(appointmentNumberTxt).sendKeys(bookingID.substring(1));
		waitSometime();
		takeScreenShot(driver, "CompletedAppointment", tcID, sheet);
		System.out.println("++++++++++++++++     " + driver.findElement(appointmentNumberPresence).getText().trim());
		Assert.assertEquals(driver.findElement(appointmentNumberPresence).getText().trim(), bookingID.substring(1),
				"Appointment number is not present");
		return true;
	}

	// Validates cash received and completion of an appointment
	public String verifyCashReceivedAndComplete(String bookingID, String tcID, String sheet) throws Exception {
		explicitWait(cashReceivedTxt);
		clickUsingJS(cashReceivedChkbx);
		waitSometime();
		takeScreenShot(driver, "CashReceivedChecked", tcID, sheet);
		waitSometime();
		for (int i = 1; i <= 2; i++) {
			driver.findElement(rightArrowBtn).click();
			waitSometime();
		}
		driver.findElement(completeLnk).click();
		waitForSpecificTime(5000);
		takeScreenShot(driver, "PaidStatusUpdated", tcID, sheet);
		System.out.println("++++++++++++++++     " + driver.findElement(resultLbl).getText().trim());
		Assert.assertEquals(driver.findElement(resultLbl).getText().trim(), "PAID",
				"Still the appointment is NOT PAID");
		return driver.findElement(resultLbl).getText().trim();
	}

	// Validates adding of diagnostic notes to an appointment
	public String verifyAddingDiagnosisNote(String notes, String bookingID, String tcID, String sheet)
			throws Exception {
		explicitWait(accountInformationHeader);
		driver.findElement(diagnosticNotesLnk).click();
		waitSometime();
		explicitWait(addDiagnosticNotesHeader);
		waitSometime();
		driver.findElement(addNotesTxt).sendKeys(notes);
		waitSometime();
		takeScreenShot(driver, "AddingNotes", tcID, sheet);
		driver.findElement(noteSaveBtn).click();
		waitSometime();
		scrollTillElementVisible(driver.findElement(diagnosisNoteInfo));
		waitSometime();
		takeScreenShot(driver, "AddedNotes", tcID, sheet);
		System.out.println("++++++++++++++++     " + driver.findElement(actualNotes).getText().trim());
		Assert.assertEquals(driver.findElement(actualNotes).getText().trim(), notes.trim(), "Notes are not equal");
		return driver.findElement(actualNotes).getText().trim();
	}

	// Validate cancellation of an appointment from doctor side
	public boolean verifyCancel(String refundType, String bookingID, String tcID, String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		for (int i = 1; i <= 4; i++) {
			driver.findElement(nextBtn).click();
			waitSometime();
		}
		driver.findElement(cancelLnk).click();
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
		takeScreenShot(driver, "CancelledSuccessfully", tcID, sheet);
		driver.findElement(cancelSectionFromLeftPanel).click();
		waitSometime();
		driver.findElement(appointmentNumberTxt).sendKeys(bookingID.substring(1));
		waitSometime();
		takeScreenShot(driver, "CancelledAppointment", tcID, sheet);
		System.out.println("++++++++++++++++     " + driver.findElement(appointmentNumberPresence).getText().trim());
		Assert.assertEquals(driver.findElement(appointmentNumberPresence).getText().trim(), bookingID.substring(1),
				"Appointment number not present");
		return true;
	}

	// Validate follow up of an appointment
	public boolean verifyFollowUpAppointment(String bookingID, String tcID, String sheet) throws Exception {
		Boolean status = false;
		explicitWait(accountInformationHeader);
		waitSometime();
		for (int i = 1; i <= 4; i++) {
			driver.findElement(nextBtn).click();
			waitSometime();
		}
		driver.findElement(followUpAppointmentLnk).click();
		waitSometime();
		if (driver.findElement(rescheduleHeader).getText().trim()
				.equalsIgnoreCase("Choose a Date and Time to Reschedule")) {
			driver.findElement(datePickerTxt).sendKeys(getTomorrowDateInYYYYMMDD());
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
			takeScreenShot(driver, "FollowUpAppointmentDetails", tcID, sheet);
			driver.findElement(bookBtn).click();
		}
		waitSometime();
		scrollTillElementVisible(driver.findElement(followUpAppointmentSection));
		takeScreenShot(driver, "BookedFollowUpAppointment", tcID, sheet);
		scrollTillEnd();
		Assert.assertTrue(driver.findElement(followUpAppointmentInfo).isDisplayed(),
				"Followup information is not present");
		if (driver.findElement(followUpAppointmentInfo).getText().contains(getTomorrowDateInYYYYMMDD()))
			status = true;
		else
			status = false;
		return status;
	}

	// Validate prescribe medicine to an appointment
	public String verifyPrescribeMedicine(String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String bookingID, String tcID, String sheet)
			throws Exception {
		explicitWait(accountInformationHeader);
		driver.findElement(nextBtn).click();
		waitSometime();
		driver.findElement(prescribeMedicineLnk).click();
		waitSometime();
		if (driver.findElement(prescribeMedicineHeader).getText().trim().equalsIgnoreCase("E-Prescription")) {
			// driver.findElement(prescriptionTypeTxt).sendKeys(prescriptionType);
			driver.findElement(totalUsageTxt).sendKeys(totalUsage);
			driver.findElement(searchProductTxt).sendKeys(productName);
			waitSometime();
			clickUsingJS(searchProductDrpDwn);
			waitSometime();
			driver.findElement(durationTxt).sendKeys(duration);
			driver.findElement(instructionTxt).sendKeys(instruction);
			scrollTillEnd();
			waitSometime();
			driver.findElement(referredPharmacyTxt).sendKeys(referredPharmacy);
			waitSometime();
			clickUsingJS(referredPharmacyDrpDwn);
			driver.findElement(saveBtnForPrescribeMedicine).click();
		}
		waitSometime();
		scrollTillElementVisible(driver.findElement(prescriptionInformationHeader));
		takeScreenShot(driver, "PrescriptionInformation", tcID, sheet);
		if (driver.findElement(prescribedMedicineDetails).getText().trim().toUpperCase()
				.contains(referredPharmacy.toUpperCase()))
			exrNumber = driver.findElement(prescribedMedicineDetails).getText()
					.substring(4, driver.findElement(prescribedMedicineDetails).getText().indexOf("\n")).trim();
		;
		Assert.assertTrue(driver.findElement(prescribedMedicineDetails).isDisplayed(), "Details are not present");
		return exrNumber;
	}

	// Validate conduct test to an appointment
	public String verifyConductTest(String prescriptionType, String totalUsage, String serviceType, String service,
			String referredLab, String bookingID, String tcID, String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		driver.findElement(conductTestLnk).click();
		waitSometime();
		if (driver.findElement(prescribeMedicineHeader).getText().trim().equalsIgnoreCase("Conduct Test")) {
			// driver.findElement(prescriptionTypeTxt).sendKeys(prescriptionType);
			driver.findElement(totalUsageTxt).sendKeys(totalUsage);
			waitSometime();
			driver.findElement(serviceTypeTxt).sendKeys(serviceType);
			waitSometime();
			driver.findElement(serviceTypeDrpDwn).click();
			waitSometime();
			driver.findElement(serviceTxt).sendKeys(service);
			waitSometime();
			driver.findElement(serviceDrpDwn).click();
			waitSometime();
			driver.findElement(referredLabTxt).sendKeys(referredLab);
			waitSometime();
			driver.findElement(referredDrpDwn).click();
			driver.findElement(saveBtnForPrescribeMedicine).click();
		}
		waitSometime();
		scrollTillElementVisible(driver.findElement(prescriptionInformationHeader));
		takeScreenShot(driver, "ConductTestInformation", tcID, sheet);
		if (driver.findElement(prescribedMedicineDetails).getText().trim().toUpperCase()
				.contains(referredLab.toUpperCase()))
			exrNumber = driver.findElement(prescribedMedicineDetails).getText()
					.substring(4, driver.findElement(prescribedMedicineDetails).getText().indexOf("\n")).trim();
		Assert.assertTrue(driver.findElement(prescribedMedicineDetails).isDisplayed(), "Details are not present");
		return exrNumber;
	}

	// Validate EHR permission to an appointment
	public boolean verifyEHRPermission(String recordType, String reportName, String bookingID, String tcID,
			String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		driver.findElement(ehrPermissionLnk).click();
		waitSometime();
		if (driver.findElement(requestUserReportHeader).getText().trim().equalsIgnoreCase("Request User Reports")) {
			driver.findElement(reportTypeTxt).sendKeys(recordType);
			waitSometime();
			driver.findElement(reportTypeDrpDwn).click();
			waitSometime();
			List<WebElement> reportList = driver
					.findElements(By.xpath("//app-user-records-listing/nb-card/nb-card-body/form/div[2]/table/tr"));
			System.out.println("Size of the report: " + reportList.size());
			for (int i = 2; i <= reportList.size(); i++) {
				if (driver
						.findElement(By.xpath("//app-user-records-listing/nb-card/nb-card-body/form/div[2]/table/tr["
								+ i + "]/td[1]"))
						.getText().trim().toUpperCase().equalsIgnoreCase(reportName.toUpperCase())) {
					driver.findElement(By.xpath("//app-user-records-listing/nb-card/nb-card-body/form/div[2]/table/tr["
							+ i + "]/td[2]/div/div/nb-checkbox/label/span[1]")).click();
					break;
				}
				if (i % 4 == 0) {
					scrollTillASpecificPoint(200);
					waitSometime();
				}
			}
			driver.findElement(requestAccessButton).click();
		}
		waitForSpecificTime(1000);
		takeScreenShot(driver, "RequestSentSuccessfully", tcID, sheet);
		return true;
	}

	// Validate View EHR permission of an appointment
	public boolean verifyViewEHRAfterAccept(String permissionNeeded, String reportName, String bookingID, String tcID,
			String sheet) throws Exception {
		explicitWait(accountInformationHeader);
		waitSometime();
		if (permissionNeeded.equalsIgnoreCase("ACCEPT")) {
			if (driver.findElement(viewEHRLnk).isDisplayed()) {
				Assert.assertTrue(driver.findElement(viewEHRLnk).isDisplayed(), "View EHR is not present");
				takeScreenShot(driver, "ViewEHR", tcID, sheet);
				driver.findElement(viewEHRLnk).click();
				waitSometime();
				if (driver.findElement(acceptedReportRequestHeader).isDisplayed()) {
					if (driver.findElement(reportNamePresence).getText().trim().equalsIgnoreCase(reportName.trim())) {
						driver.findElement(downloadAttachmentLnk).click();
						waitSometime();
						ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
						driver.switchTo().window(tabs2.get(2));
						takeScreenShot(driver, "OpenedAttachment", tcID, sheet);
						driver.close();
						driver.switchTo().window(tabs2.get(1));
					}
				}
				return true;
			} else
				return false;
		} else {
			if (driver.findElement(ehrPermissionLnk).isDisplayed()) {
				Assert.assertTrue(driver.findElement(ehrPermissionLnk).isDisplayed(),
						"EHRPermission link is not present");
				takeScreenShot(driver, "EHRPermission", tcID, sheet);
				return true;
			} else
				return false;
		}
	}
}
