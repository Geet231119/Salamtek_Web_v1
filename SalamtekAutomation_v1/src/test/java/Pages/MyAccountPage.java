
package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Common.BaseTest;

public class MyAccountPage extends BaseTest {
	By verifyMyAccount = By.xpath("//*[@id=\"navbarNav\"]/div[1]/ul/li[2]/a[contains(text(),'MY ACCOUNT')]");
	By dashboardBtn = By.xpath("//*[@id=\"v-pills-tab\"]/button[1]");
	By profileBtn = By.id("ACTIVENEW");
	By myOrdersBtn = By.id("ORDERS-tab");
	By medicalRecordBtn = By.xpath("//*[@id=\"v-pills-tab\"]/span[2]/button");
	By myFavoritesBtn = By.id("FAVORITES-tab");
	By walletValue = By.xpath("//div[@class='PAGE-DATA']/div[5]/div/div[2]/h3/span");
	By firstBookingID = By.xpath(
			"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[1]/div[2]/div/div[2]/div[2]/ul[1]/li[2]");
	By viewOrderHeader = By.xpath(
			"//*[@id=\"VIEW-ORDER-DETAILS\"]/div/div/div/div[2]/h2[1]/span[contains(text(),'THANK YOU FOR ORDERING FROM')]");
	By viewOrderCloseBtn = By.xpath("//*[@id=\"VIEW-ORDER-DETAILS\"]/div/div/span/img");
	By firstTrackValue = By
			.xpath("//*[@id=\"VIEW-ORDER-DETAILS\"]/div/div/div/div[4]/div/div[1]/p[contains(text(),'Accepted')]");
	By appointmentCancellationHeader = By
			.xpath("//*[@id=\"ORDER-APPOINTMENT\"]/div/div/div/div[1]/h3[contains(text(),'Appointment cancellation')]");
	By soOrderCancellationHeader = By
			.xpath("//*[@id=\"CANCEL-ORDER-COMMON\"]/div/div/div/div[1]/h3[contains(text(),'Order cancellation')]");
	By refundToSalamtek = By.xpath(
			"//*[@id=\"ORDER-APPOINTMENT\"]/div/div/div/div[2]/div/div/div/div/div/div/div[3]/div[2]/ul/li[1]/p/label[contains(text(),'Salamtek Wallet')]");
	By soRefundToSalamtek = By.xpath(
			"//*[@id=\"CANCEL-ORDER-COMMON\"]/div/div/div/div[2]/div/div/div/div/div/div/div[5]/div[2]/ul/li[1]/p/label[contains(text(),'Salamtek Wallet')]");
	// By cancellationSubmitBtn =
	// By.xpath("//*[@id=\"ORDER-APPOINTMENT\"]/div/div/div/div[2]/div/div/div/div/div/div/div[3]/div/button");
	// ----- staging
	By cancellationSubmitBtnForAppointment = By.xpath("//button[contains(text(),'SEND CANCELLATION REQUEST')]");
	By soCancellationSubmitBtn = By
			.xpath("//*[@id=\"CANCEL-ORDER-COMMON\"]/div/div/div/div[2]/div/div/div/div/div/div/div[5]/div[3]/button");
	By appointmentCancellationSuccessHeader = By
			.xpath("//*[@id=\"ORDER-CANCELED-SUCCESSFULLY\"]/div/div/div/div[1]/h3");
	By refundValue = By.xpath(
			"//*[@id=\"ORDER-CANCELED-SUCCESSFULLY\"]/div/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div/div[2]/div/h4");
	By cancellationCloseBtn = By.xpath("//*[@id=\"ORDER-CANCELED-SUCCESSFULLY\"]/div/div/span/img");
	By orderCancellatioSuccessHeader = By.xpath(
			"//*[@id=\"CANCELED-SUCCESSFULLY\"]/div/div/div/div[1]/h3[contains(text(),'Order cancellation successful')]");
	By orderCancellationSuccessCloseBtn = By.xpath("//*[@id=\"CANCELED-SUCCESSFULLY\"]/div/div/span/img");
	By listOfAllOrders = By.xpath("//app-my-order/div[1]/div/div/div/div[4]/div/div");
	By totalPriceFromCancellation = By.xpath(
			"//*[@id=\"CANCEL-ORDER-COMMON\"]/div/div/div/div[2]/div/div/div/div/div/div/div[4]/div[2]/div/ul[3]/li[2]/span");
	By myOrdersHeader = By.xpath("//app-my-order/div[1]/div/div/div/div[1]/h2[contains(text(),'MY ORDERS')]");
	By myWalletBtn = By.xpath("//*[@id=\"WALLET-tab\"]");
	By orderIDFromWallet = By.xpath("//app-my-wallet/div/div/div/div/div[3]/div/div[2]/div[1]/ul/li[1]");
	By refundAmountFromWallet = By.xpath("//app-my-wallet/div/div/div/div/div[3]/div/div[2]/div[1]/ul/li[4]");
	By consultationFee = By.xpath(
			"//*[@id=\"ORDER-APPOINTMENT\"]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div[2]/div/div[2]/ul[1]/li[2]");
	By labGrandTotal = By.xpath(
			"//*[@id=\"ORDER-APPOINTMENT\"]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div[2]/div/div[2]/ul[2]/li[2]");
	By checkOutHeader = By.xpath("//h4[contains(text(),'CHECKOUT')]");

	// Elements related to Medical record - E-Health
	By ehealthRecordBtn = By.id("HEALTHCARE-tab");
	By ehealthRecordHeader = By.xpath("//app-e-healthcare-record/div[1]/div/div/div/div[1]/h2");
	By medicalRecordLnk = By.xpath("//*[@id=\"nav-medicalrecord-tab\"]/h2/span");
	By ehealthBookingIDTxt = By
			.xpath("//app-my-ehr-list/span/app-ehealth-care-record/div/div[1]/div[2]/div/div[2]/ul[1]/li[2]");
	By reportBtn = By.xpath("//app-my-ehr-list/span/app-ehealth-care-record/div/div[2]/ul/li[4]/button");
	By reportFileName = By.xpath(
			"//app-my-ehr-list/span/app-ehealth-care-record/span/app-ehealth-reports/section/div/div[2]/div[1]/div/ul/li[2]");
	By viewBtnInReport = By.xpath(
			"//app-my-ehr-list/span/app-ehealth-care-record/span/app-ehealth-reports/section/div/div[2]/div[2]/div/ul/li[3]/button/img");
	By myReportsHeader = By.xpath("//*[@id=\"VIEW-PDF\"]/div/div/div/div/h3");
	By myReportCloseBtn = By.xpath("//*[@id=\"VIEW-PDF\"]/div/div/span/img");

	// Elements related to Medical Record - E-Prescription
	By ePrescriptionBtn = By.xpath("//*[@id=\"PRESCRIPTIONS-tab\"]");
	By ePrescriptionHeader = By.xpath("//app-e-prescriptions/div[1]/div/div/div/div[1]/h2/span");
	By exrNumberTxt = By.id("exr");
	By subTotalPriceFromEPrescription = By
			.xpath("//app-eprescriptions-listing/section/div/div/div[4]/div[2]/div[2]/div[1]/ul[1]/li[2]");

	// Elements related to Medical Record - Medical Request
	By medicalRequestBtn = By.id("MEDICALREQUEST-tab");
	By medicalRequestHeader = By.xpath("//app-medical-requests/div[1]/div/div/div/div[1]/h2");
	By subTotalPriceFromMedicalRequest = By
			.xpath("//app-eprescriptions-listing/section/div/div/div[4]/div[2]/div[2]/div[1]/ul[1]/li[2]");

	// Elements related to EHR Permissions
	By acceptDoctorPermissionHeader = By
			.xpath("//app-appointment-card/div/div/div/div[2]/h5[contains(text(),'ACCEPT DOCTOR')]");
	By permissionDurationDrpDwn = By.xpath(
			"//*[@id=\"doctorPermissionModalLisiting\"]/div/div/div[3]/div/app-acceptdoctorpermission/div/div/div[2]/div[1]/select");
	By acceptBtn = By.xpath(
			"//*[@id=\"doctorPermissionModalLisiting\"]/div/div/div[3]/div/app-acceptdoctorpermission/div/div/div[2]/div[2]/button[contains(text(),'Accept')]");
	By rejectBtn = By.xpath(
			"//*[@id=\"doctorPermissionModalLisiting\"]/div/div/div[3]/div/app-acceptdoctorpermission/div/div/div[2]/div[2]/button[contains(text(),'Reject')]");
	By completedRequestTxt = By.xpath(
			"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li[2]/button/span");

	// Elements Related to Doctor Notes
	By doctorNoteBtn = By
			.xpath("//app-appointment-details/div[1]/div/div/div/div[2]/div[1]/div/button[contains(text(),'NOTE')]");
	By doctorNotesHeader = By
			.xpath("//app-appointment-details/app-doctornotes/div/div/div/div/div[1]/h3[contains(text(),'Note')]");
	By doctorNotesTxt = By.xpath("//app-appointment-details/app-doctornotes/div/div/div/div/div[2]/div/div/div/span/p");
	By doctorNotesCloseBtn = By.xpath("//*[@id=\"DOCTOR-NOTES\"]/div/div/span/img");

	// Elements related to Medical Record - Appointments
	By appointmentBtn = By.id("APPOINTMENT-tab");
	By appointmentScheduleHeader = By.xpath("//app-appointments/div[1]/div/div/div/div[1]/h2");
	By dateTxt = By.id("date");
	By searchBtn = By.xpath("//button[contains(text(),'SEARCH')]");
	By patientNameDrpDwn = By.id("patName");
	By providerNameDrpDwn = By.id("proName");
	By statusDrpDwn = By.id("status");
	By clearFilterLnk = By.xpath("//p[contains(text(),'Clear Filters')]");

	// Elements related to My Favorites
	By favoriteSubjectDrpDwn = By.xpath("//app-my-favorites/div[1]/div/div/div/div[2]/div[1]/div/select");
	By favoriteEventTxt = By
			.xpath("//app-my-favorites/div[1]/div/div/div/div[2]/div[4]/div/app-event-card/div/a/div/p[1]");
	By favoriteArticleTxt = By
			.xpath("//app-my-favorites/div[1]/div/div/div/div[2]/div[5]/div/app-article-card/div/div[1]/h5");
	By favoriteHealthTipsTxt = By.xpath(
			"//app-my-favorites/div[1]/div/div/div/div[2]/div[6]/div/app-health-tip-card/div/div/div[1]/div[2]/div/div[1]/p");
	By favoriteDoctorTxt = By.xpath(
			"//app-my-favorites/div[1]/div/div/div/div[2]/div[7]/div/app-doctor/div/div/div/div/div[3]/div/div/div[1]/div[1]/div/h2");
	By favoriteProductTxt = By.xpath(
			"//app-my-favorites/div[1]/div/div/div/div[2]/div[9]/div/app-product-list-card/div/div/div/div[3]/h4");
	By favoriteProviderTxt = By
			.xpath("//app-my-favorites/div[1]/div/div/div/div[2]/div[10]/div/app-lab-single-card/div/div/div[4]/h3");
	By favoriteClinicTxt = By
			.xpath("//app-my-favorites/div[1]/div/div/div/div[2]/div[11]/div/app-clinic-single/span/div/div/div[5]/h3");

	// Variables for storing wallet old value, new value and refund value
	Double walletOldValue;
	Double walletNewValue;
	Double refundAmount;
	Double knetCommission = 0.2;
	Double totalPriceOfOrder;
	Double expectedRefundAmount = 0.000;
	Double actualRefundAmount;

	// E-Prescription elements
	By ePrescriptionPopup = By.xpath("//app-my-account/app-notification-view-e-prescription/div/div");
	By myEPrescriptionHeader = By.id("staticBackdropPrescrLabel");
	By referredPharmacyName = By.xpath(
			"//app-notification-view-e-prescription/div/div/div/div[3]/div/div/div[1]/div/div/div/div[1]/ul/li[2]");
	By proceedBtn = By.xpath(
			"//app-notification-view-e-prescription/div/div/div/div[3]/div/div/div[5]/div/button[contains(text(),'PROCEED')]");
	// Elements of profile tab
	By myProfileBtn = By.id("PROFILE-tab");
	By firstNameTxt = By.xpath(
			"/html/body/cdk-virtual-scroll-viewport/app-root/main/div/app-my-account/section[1]/div/div/div[2]/div/div[2]/app-my-profile/section/div[1]/div/div/div/div[2]/app-personal-info/section/div[3]/div[1]/div/input");
	By lastNameTxt = By.xpath(
			"/html/body/cdk-virtual-scroll-viewport/app-root/main/div/app-my-account/section[1]/div/div/div[2]/div/div[2]/app-my-profile/section/div[1]/div/div/div/div[2]/app-personal-info/section/div[3]/div[2]/div/input");

	// Elements related to My Wallet
	By walletTab = By.id("WALLET-tab");
	By walletHeader = By.xpath("//app-my-wallet/div/div/div/div/div[1]/h2[contains(text(),'MY WALLET')]");
	By voucherTxt = By.xpath("//input[@placeholder='Enter voucher code']");
	By redeemBtn = By.xpath("//button[contains(text(),'REDEEM')]");
	By expectedStatus = By.xpath("//app-my-wallet/div/div/div/div/div[3]/div/div[2]/div[1]/ul/li[5]");
	By myWalletValue = By.xpath("//app-my-wallet/div/div/div/div/div[2]/div[1]/h2");
	
	public MyAccountPage(WebDriver driver) {
		super();
	}

	// Validate retrieval of wallet value before cancellation
	public String getFirstWalletValue() {
		driver.findElement(verifyMyAccount).sendKeys(Keys.ENTER);
		waitSometime();
		explicitWait(dashboardBtn);
		String actualWalletOldValue = driver.findElement(walletValue).getText().toString();
		boolean hasComma = actualWalletOldValue.contains(",");
		if (hasComma) {
			for (int i = 0; i < actualWalletOldValue.length(); i++) {
				if (actualWalletOldValue.charAt(i) == ',') {
					actualWalletOldValue = actualWalletOldValue.replaceAll(",", "");
				} else
					continue;
				System.out.println("Actual wallet amount: " + actualWalletOldValue);
				walletOldValue = roundNumberTo3Decimals(Double.valueOf(actualWalletOldValue));
			}
		} else {
			System.out.println("Actual wallet amount: " + actualWalletOldValue);
			walletOldValue = roundNumberTo3Decimals(Double.valueOf(actualWalletOldValue));
		}
		return walletOldValue.toString();
	}

	// Validate retrieval of wallet value after cancellation
	public String getWalletUpdatedValue() {
		waitSometime();
		explicitWait(dashboardBtn);
		driver.findElement(dashboardBtn).click();
		explicitWait(dashboardBtn);
		String actualWalletNewValue = driver.findElement(walletValue).getText().toString();
		boolean hasComma = actualWalletNewValue.contains(",");
		if (hasComma) {
			for (int i = 0; i < actualWalletNewValue.length(); i++) {
				if (actualWalletNewValue.charAt(i) == ',') {
					actualWalletNewValue = actualWalletNewValue.replaceAll(",", "");
				} else
					continue;
				System.out.println("Actual wallet new amount: " + actualWalletNewValue);
				walletNewValue = roundNumberTo3Decimals(Double.valueOf(actualWalletNewValue));
			}
		} else {
			System.out.println("Actual wallet new amount: " + actualWalletNewValue);
			walletNewValue = roundNumberTo3Decimals(Double.valueOf(actualWalletNewValue));
		}
		return walletNewValue.toString();
	}

	// Validate appointment cancellation from dashboard for lab and doctor
	public boolean verifyOrderCancellationFromDashboard_Lab_Doctor(String bookingID) throws Exception {
		Boolean status = false;
		if (driver.findElement(firstBookingID).getText().trim().equalsIgnoreCase(bookingID.trim())) {
			// getting list of buttons in list
			List<WebElement> buttonCnt = driver.findElements(By.xpath(
					"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li"));
			System.out.println(buttonCnt.size());
			moveToElement(driver.findElement(By.xpath(
					"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li["
							+ buttonCnt.size() + "]/button")));
			driver.findElement(By.xpath(
					"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li["
							+ buttonCnt.size() + "]/button"))
					.click();
			waitSometime();
			explicitWait(appointmentCancellationHeader);
			System.out.println(
					"++++++++++++++++     " + driver.findElement(appointmentCancellationHeader).getText().trim());
			Assert.assertEquals(driver.findElement(appointmentCancellationHeader).getText().trim().toUpperCase(),
					"APPOINTMENT CANCELLATION", "Appointment cancellation is not present");
			status = true;
		} else {
			status = false;
		}
		return status;
	}

	// Validate appointment cancellation details in the main cancellation screen for
	// lab and doctor
	public boolean verifyOrderCancellationMain_Lab_Doctor(String payOnlyBookingFee) throws Exception {
		explicitWait(appointmentCancellationHeader);
		waitSometime();
		if (!payOnlyBookingFee.equalsIgnoreCase("Yes")) {
			driver.findElement(refundToSalamtek).click();
		}
		moveToElement(driver.findElement(cancellationSubmitBtnForAppointment));
		waitSometime();
		driver.findElement(cancellationSubmitBtnForAppointment).click();
		explicitWait(appointmentCancellationSuccessHeader);
		System.out.println(
				"++++++++++++++++     " + driver.findElement(appointmentCancellationSuccessHeader).getText().trim());
		Assert.assertEquals(driver.findElement(appointmentCancellationSuccessHeader).getText().trim().toUpperCase(),
				"APPOINTMENT CANCELLED SUCCESSFULLY", "Appointment cancelled is not present");
		return true;
	}

	// Validate viewing of order details from Order section
	public boolean verifyViewOrder(String orderID, String pageName, String tcID, String scenario) throws Exception {
		driver.findElement(verifyMyAccount).sendKeys(Keys.ENTER);
		waitSometime();
		explicitWait(dashboardBtn);
		waitSometime();
		explicitWait(myOrdersBtn);
		driver.findElement(myOrdersBtn).click();
		waitForSpecificTime(10000);
		takeScreenShot(getDriver(), pageName, tcID, scenario);
		List<WebElement> listOfOrders = driver.findElements(listOfAllOrders);
		System.out.println("List of orders:" + listOfOrders.size());
		waitSometime();
		for (int i = 2; i <= listOfOrders.size(); i++) {
			if (driver
					.findElement(
							By.xpath("//app-my-order/div[1]/div/div/div/div[4]/div/div[" + i + "]/div[1]/ul/li[1]"))
					.getText().trim().equalsIgnoreCase(orderID)) {
				driver.findElement(By
						.xpath("//app-my-order/div[1]/div/div/div/div[4]/div/div[" + i + "]/div[1]/ul/li[6]/span/img"))
						.click();
				waitSometime();
				driver.findElement(
						By.xpath("//app-my-order/div[1]/div/div/div/div[4]/div/div[" + i + "]/div[2]/ul/li[1]/button"))
						.click();
				waitSometime();
			}
		}
		System.out.println("++++++++++++++++     " + driver.findElement(viewOrderHeader).getText().trim());
		Assert.assertEquals(driver.findElement(viewOrderHeader).getText().trim().toUpperCase(),
				"THANK YOU FOR ORDERING FROM", "view order header is not present");
		driver.findElement(viewOrderCloseBtn).click();
		return true;
	}

	// Validate tracking of order from Order section
	public boolean verifyTrackOrder(String orderID, String pageName, String tcID, String scenario) throws Exception {
		driver.navigate().refresh();
		waitForPageLoad();
		explicitWait(myOrdersBtn);
		driver.findElement(myOrdersBtn).click();
		waitForSpecificTime(10000);
		takeScreenShot(getDriver(), pageName, tcID, scenario);
		List<WebElement> listOfOrders = driver.findElements(listOfAllOrders);
		System.out.println("List of orders:" + listOfOrders.size());
		waitSometime();
		for (int i = 2; i <= listOfOrders.size(); i++) {
			if (driver
					.findElement(
							By.xpath("//app-my-order/div[1]/div/div/div/div[4]/div/div[" + i + "]/div[1]/ul/li[1]"))
					.getText().trim().equalsIgnoreCase(orderID)) {
				driver.findElement(By
						.xpath("//app-my-order/div[1]/div/div/div/div[4]/div/div[" + i + "]/div[1]/ul/li[6]/span/img"))
						.click();
				waitSometime();
				driver.findElement(
						By.xpath("//app-my-order/div[1]/div/div/div/div[4]/div/div[" + i + "]/div[2]/ul/li[2]/button"))
						.click();
				waitSometime();
			}
		}
		System.out.println("++++++++++++++++     " + driver.findElement(firstTrackValue).getText().trim());
		Assert.assertEquals(driver.findElement(firstTrackValue).getText().trim().toUpperCase(), "ACCEPTED",
				"Accepted is not present");
		driver.findElement(viewOrderCloseBtn).click();
		return true;
	}

	// Validate refund amount after cancellation from admin/store profile
	public String verifyRefundAfterAdminCancellation(String orderID, String expectedRefundAmount, String pharmacyName,
			String pageName, String tcID, String scenario) throws Exception {
		String commentInWallet = "";
		driver.findElement(verifyMyAccount).sendKeys(Keys.ENTER);
		waitSometime();
		explicitWait(dashboardBtn);
		waitSometime();
		explicitWait(myWalletBtn);
		driver.findElement(myWalletBtn).click();
		waitForSpecificTime(10000);
		takeScreenShot(getDriver(), pageName, tcID, scenario);
		List<WebElement> listOfOrders = driver.findElements(By.xpath("//app-my-wallet/div/div/div/div/div[3]/div/div"));
		System.out.println("List of orders:" + listOfOrders.size());
		waitSometime();
		for (int i = 2; i <= listOfOrders.size(); i++) {
			if (driver
					.findElement(By.xpath("//app-my-wallet/div/div/div/div/div[3]/div/div[" + i + "]/div[1]/ul/li[1]"))
					.getText().trim().equalsIgnoreCase(orderID.substring(1))
					&& driver
							.findElement(By
									.xpath("//app-my-wallet/div/div/div/div/div[3]/div/div[" + i + "]/div[1]/ul/li[2]"))
							.getText().trim().equalsIgnoreCase(pharmacyName.trim())
					&& driver
							.findElement(By.xpath(
									"//app-my-wallet/div/div/div/div/div[3]/div/div[" + i + "]/div[1]/ul/li[4]/span"))
							.getText().trim().equalsIgnoreCase(expectedRefundAmount.substring(3).trim())
					&& driver
							.findElement(By
									.xpath("//app-my-wallet/div/div/div/div/div[3]/div/div[" + i + "]/div[1]/ul/li[5]"))
							.getText().trim().equalsIgnoreCase("Refund")) {
				driver.findElement(
						By.xpath("//app-my-wallet/div/div/div/div/div[3]/div/div[" + i + "]/div[1]/ul/li[6]/span/img"))
						.click();
				waitSometime();
				commentInWallet = driver
						.findElement(
								By.xpath("//app-my-wallet/div/div/div/div/div[3]/div/div[" + i + "]/div[2]/ul/li[2]"))
						.getText();
				waitSometime();
				break;
			}
		}
		return commentInWallet;
	}

	// Validate order cancellation
	public boolean verifyOrderCancellationFromOrder_ShopOnline(String orderID, String pageName, String tcID,
			String scenario) throws Exception {
		Boolean status = false;
		explicitWait(myOrdersBtn);
		driver.findElement(myOrdersBtn).click();
		waitForSpecificTime(10000);
		takeScreenShot(getDriver(), pageName, tcID, scenario);
		List<WebElement> listOfOrders = driver.findElements(listOfAllOrders);
		System.out.println("List of orders:" + listOfOrders.size());
		waitSometime();
		for (int i = 2; i <= listOfOrders.size(); i++) {
			if (driver
					.findElement(
							By.xpath("//app-my-order/div[1]/div/div/div/div[4]/div/div[" + i + "]/div[1]/ul/li[1]"))
					.getText().trim().equalsIgnoreCase(orderID)) {
				moveToElement(driver.findElement(By.xpath(
						"//app-my-order/div[1]/div/div/div/div[4]/div/div[" + i + "]/div[1]/ul/li[6]/span/img")));
				driver.findElement(By
						.xpath("//app-my-order/div[1]/div/div/div/div[4]/div/div[" + i + "]/div[1]/ul/li[6]/span/img"))
						.click();
				List<WebElement> buttonCnt = driver.findElements(
						By.xpath("//app-my-order/div[1]/div/div/div/div[4]/div/div[" + i + "]/div[2]/ul/li"));
				driver.findElement(By.xpath("//app-my-order/div[1]/div/div/div/div[4]/div/div[" + i + "]/div[2]/ul/li["
						+ buttonCnt.size() + "]/button")).click();
				break;
			}
		}
		explicitWait(soOrderCancellationHeader);
		Assert.assertTrue(driver.findElement(soOrderCancellationHeader).isDisplayed(),
				"Order cancellation is not displayed");
		if (driver.findElement(soOrderCancellationHeader).getText().trim().toUpperCase()
				.equalsIgnoreCase("ORDER CANCELLATION"))
			status = true;
		else
			status = false;
		return status;
	}

	// Validate order cancellation details in the main cancellation screen for shop
	// online
	public boolean verifyOrderCancellationMain_ShopOnline(String paymentMode) throws Exception {
		try {
			explicitWait(soOrderCancellationHeader);
			waitSometime();
			totalPriceOfOrder = Double.parseDouble(driver.findElement(totalPriceFromCancellation).getText().trim());
			driver.findElement(soRefundToSalamtek).click();
			driver.findElement(soCancellationSubmitBtn).click();
			if (paymentMode.equalsIgnoreCase("VISA")) {
				expectedRefundAmount = (double) (totalPriceOfOrder - (totalPriceOfOrder * (2.5d / 100.0d)));
			} else if (paymentMode.equalsIgnoreCase("KNET")) {
				expectedRefundAmount = (double) (totalPriceOfOrder - knetCommission);
			} else {
				expectedRefundAmount = (double) totalPriceOfOrder;
			}
			System.out.println("Expected Refund: " + expectedRefundAmount);
			walletNewValue = walletOldValue + expectedRefundAmount;
			System.out.println("Expected wallet amount: " + roundNumberTo3Decimals(walletNewValue));
			explicitWait(orderCancellatioSuccessHeader);
			System.out.println(
					"++++++++++++++++     " + driver.findElement(orderCancellatioSuccessHeader).getText().trim());
			Assert.assertEquals(driver.findElement(orderCancellatioSuccessHeader).getText().trim().toUpperCase(),
					"ORDER CANCELLATION SUCCESSFUL", "Order cancellation success screen is not displayed");
		} catch (ArithmeticException e) {
			System.out.println("Exception in finding the element:" + e.getMessage());
		}
		return true;
	}

	// Validate retrieval of refund amount after deduction
	public String getRefundAmountAfterDeduction() {
		return expectedRefundAmount.toString();
	}

	// Validate retrieval of expected refund amount after Lab/Doctor cancellation
	public String getExpectedRefundForAdminLabDoctorCancel(String total) {
		expectedRefundAmount = Double.parseDouble(total);
		return expectedRefundAmount.toString();
	}

	// Validate retrieval of total price for order
	public String getTotalPriceOfOrder() {
		return totalPriceOfOrder.toString();
	}

	// Validate order cancellation success screen details in shop online
	public boolean verifyOrderCancellationSuccess_ShopOnline() throws Exception {
		explicitWait(orderCancellatioSuccessHeader);
		waitSometime();
		waitElementToBeVisible(orderCancellationSuccessCloseBtn);
		clickUsingJS(orderCancellationSuccessCloseBtn);
		explicitWait(myOrdersHeader);
		System.out.println("++++++++++++++++     " + driver.findElement(myOrdersHeader).getText().trim());
		Assert.assertEquals(driver.findElement(myOrdersHeader).getText().trim().toUpperCase(), "MY ORDERS",
				"My order is not present");
		return true;
	}

	// Validate refund amount in Wallet
	public String verifyRefundAmount(String orderID) throws Exception {
		waitSometime();
		driver.findElement(myWalletBtn).click();
		waitSometime();
		if (driver.findElement(orderIDFromWallet).getText().trim().equalsIgnoreCase(orderID.substring(1))) {
			actualRefundAmount = Double
					.parseDouble(driver.findElement(refundAmountFromWallet).getText().trim().substring(2));
			System.out.println("Actual Refund: " + actualRefundAmount);
		}
		Assert.assertEquals(expectedRefundAmount, actualRefundAmount,
				"Actual and Expected refund values are different");
		return actualRefundAmount.toString();
	}

	// Validate refund amount in appointment cancellation screen
	public String getRefundAmountValue(String payOnlyBookingFee) {
		explicitWait(appointmentCancellationSuccessHeader);
		if (payOnlyBookingFee.equalsIgnoreCase("Yes")) {
			refundAmount = 0.000;
		} else {
			refundAmount = Double.parseDouble(driver.findElement(refundValue).getText().trim().substring(2));
			System.out.println("Refund Amount: " + refundAmount);
		}
		waitForSpecificTime(5000);
		driver.findElement(cancellationCloseBtn).click();
		driver.navigate().refresh();
		return refundAmount.toString();
	}

	// Validate total wallet amount after refund for lab and doctor
	public boolean verifyTotalWalletAmountAfterRefund_Lab_Doctor() {
		Boolean status = false;
		try {
			Double TotalAmount = refundAmount + walletOldValue;
			System.out.println("Rounded total Amount:  " + TotalAmount);
			System.out.println("Rounded wallet Amount:  " + walletNewValue);
			if (Double.compare(TotalAmount, walletNewValue) == 0 || Double.compare(TotalAmount, walletNewValue) > 0
					|| Double.compare(TotalAmount, walletNewValue) < 0) {
				status = true;
			} else
				status = false;
		} catch (ArithmeticException ae) {
			System.out.println("Exception in finding the element:" + ae.getMessage());
		}
		return status;
	}

	// Validate total wallet amount after refund for lab and shop online
	public boolean verifyTotalWalletAmountAfterRefund_ShopOnline_MS() {
		Boolean status = false;
		Double TotalAmount = actualRefundAmount + walletOldValue;
		if (Double.compare((TotalAmount), (walletNewValue)) == 0 || Double.compare((TotalAmount), (walletNewValue)) > 0
				|| Double.compare((TotalAmount), (walletNewValue)) < 0) {
			status = true;
		} else
			status = false;
		return status;
	}

	// Validate medical reports for patients
	public boolean verifyMedicalReportForPatient(String bookingID) throws Exception {
		explicitWait(verifyMyAccount);
		clickUsingJS(verifyMyAccount);
		waitSometime();
		explicitWait(dashboardBtn);
		driver.findElement(medicalRecordBtn).click();
		explicitWait(ehealthRecordBtn);
		driver.findElement(ehealthRecordBtn).click();
		explicitWait(ehealthRecordHeader);
		driver.findElement(medicalRecordLnk).click();
		waitSometime();
		List<WebElement> listOfReports = driver.findElements(By.xpath("//*[@id=\"medical-record\"]/span/div"));
		System.out.println("****** List of reports *****" + listOfReports.size());
		for (int i = 1; i <= listOfReports.size(); i++) {
			if (driver
					.findElement(By.xpath("//*[@id=\"medical-record\"]/span/div[" + i
							+ "]/div[1]/div[2]/div/div[2]/div[1]/div[1]/ul/li[2]"))
					.getText().trim().equals(bookingID)) {
				clickUsingJS(By.xpath(
						"//*[@id=\"medical-record\"]/span/div[" + i + "]/div[1]/div[2]/div/div[2]/div[2]/div/button"));
				break;
			}
			if (i % 3 == 0) {
				scrollTillASpecificPoint(300);
				waitSometime();
			}
		}
		scrollTillTop();
		waitSometime();
		System.out.println("++++++++++++++++     " + driver.findElement(ehealthBookingIDTxt).getText().trim());
		Assert.assertEquals(driver.findElement(ehealthBookingIDTxt).getText().trim(), bookingID,
				"Booking IDs are not equal");
		return true;
	}

	// Validate doctor uploaded report in Medical Reports of the patient
	public boolean verifyReportUploadReflectedForPatient(String bookingID, String titleEn) throws Exception {
		explicitWait(ehealthBookingIDTxt);
		driver.findElement(reportBtn).click();
		if (driver.findElement(reportFileName).getText().trim().toUpperCase().equals(titleEn.trim().toUpperCase())) {
			driver.findElement(viewBtnInReport).click();
			waitSometime();
		}
		System.out
				.println("++++++++++++++++     " + driver.findElement(myReportsHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(myReportsHeader).getText().trim().toUpperCase(), "MY REPORTS",
				"My report header is not present");
		driver.findElement(myReportCloseBtn).click();
		driver.findElement(dashboardBtn).click();
		return true;
	}

	// Validate diagnostic notes reflected in the appointment
	public boolean verifyDiagnosticNotesInAppointment(String notes) throws Exception {
		waitSometime();
		waitSometime();
		explicitWait(doctorNoteBtn);
		driver.findElement(doctorNoteBtn).click();
		explicitWait(doctorNotesHeader);
		System.out.println("++++++++++++++++     " + driver.findElement(doctorNotesTxt).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(doctorNotesTxt).getText().trim().toUpperCase(),
				notes.trim().toUpperCase(), "Notes are different");
		driver.findElement(doctorNotesCloseBtn).click();
		scrollTillEnd();
		return true;
	}

	// Validate prescribe medicine reflected in the Medical Report -> E-Prescription
	public String verifyPrescribeMedicineInEPrescription(String exrNumber) throws Exception {
		explicitWait(verifyMyAccount);
		driver.findElement(verifyMyAccount).sendKeys(Keys.ENTER);
		waitSometime();
		// explicitWait(dashboardBtn);
		driver.findElement(medicalRecordBtn).sendKeys(Keys.ENTER);
		waitSometime();
		explicitWait(ePrescriptionBtn);
		driver.findElement(ePrescriptionBtn).click();
		explicitWait(ePrescriptionHeader);
		waitForSpecificTime(5000);
		List<WebElement> listOfReports = driver.findElements(
				By.xpath("//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div"));
		System.out.println("****** List of RX *****" + listOfReports.size());
		for (int i = 1; i <= listOfReports.size(); i++) {
			System.out.println("number: " + driver.findElement(
					By.xpath("//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div[" + i
							+ "]/div[1]/div/div/div[2]/div/div[2]/ul[1]/li[2]"))
					.getText().trim());
			if (driver
					.findElement(
							By.xpath("//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div["
									+ i + "]/div[1]/div/div/div[2]/div/div[2]/ul[1]/li[2]"))
					.getText().trim().equalsIgnoreCase(exrNumber)) {
				driver.findElement(
						By.xpath("//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div[" + i
								+ "]/div[3]/div[1]/div[2]/div/ul/li[2]/button"))
						.click();
				break;
			}
			if (i % 2 == 0) {
				scrollTillASpecificPoint(300);
				waitSometime();
			}
		}
		waitSometime();
		String totalPrice = driver.findElement(subTotalPriceFromEPrescription).getText().trim().substring(2);
		System.out.println("Sub Total Amount: " + totalPrice);
		waitSometime();
		System.out.println("++++++++++++++++     " + driver.findElement(checkOutHeader).getText().trim());
		Assert.assertEquals(driver.findElement(checkOutHeader).getText().trim(), "CHECKOUT",
				"Checkout header is not present");
		return totalPrice;
	}

	// Validate conduct test reflected in the Medical Report -> Medical Request
	public String verifyConductTestInMedicalRequest(String exrNumber) throws Exception {
		driver.navigate().refresh();
		explicitWait(verifyMyAccount);
		clickUsingJS(verifyMyAccount);
		waitForSpecificTime(5000);
		explicitWait(dashboardBtn);
		driver.findElement(medicalRecordBtn).click();
		explicitWait(medicalRequestBtn);
		driver.findElement(medicalRequestBtn).click();
		explicitWait(medicalRequestHeader);
		waitForSpecificTime(5000);
		List<WebElement> listOfReports = driver.findElements(By.xpath("//app-prescription-card/div"));
		System.out.println("****** List of RX *****" + listOfReports.size());
		for (int i = 1; i <= listOfReports.size(); i++) {
			System.out.println("number: " + driver
					.findElement(By.xpath(
							"//app-prescription-card/div[" + i + "]/div[1]/div/div/div[2]/div/div[2]/ul[1]/li[2]"))
					.getText().trim());
			if (driver
					.findElement(By.xpath(
							"//app-prescription-card/div[" + i + "]/div[1]/div/div/div[2]/div/div[2]/ul[1]/li[2]"))
					.getText().trim().equalsIgnoreCase(exrNumber)) {
				driver.findElement(
						By.xpath("//app-prescription-card/div[" + i + "]/div[3]/div[1]/div[2]/div/ul/li[2]/button"))
						.click();
				break;
			}
			if (i % 2 == 0) {
				scrollTillASpecificPoint(300);
				waitSometime();
			}
		}
		waitSometime();
		String totalPrice = driver.findElement(subTotalPriceFromMedicalRequest).getText().trim().substring(2);
		System.out.println("Sub Total Amount: " + totalPrice);
		waitSometime();
		System.out.println("++++++++++++++++     " + driver.findElement(checkOutHeader).getText().trim());
		Assert.assertEquals(driver.findElement(checkOutHeader).getText().trim(), "CHECKOUT",
				"Checkout header is not present");
		return totalPrice;
	}

	// Validate appointment from dashboard
	public boolean verifyAppointmentFromDashboard(String bookingID) throws Exception {
		Boolean status = false;
		clickUsingJS(verifyMyAccount);
		waitSometime();
		explicitWait(dashboardBtn);
		if (driver.findElement(firstBookingID).getText().trim().equalsIgnoreCase(bookingID.trim())) {
			// getting list of buttons in list
			List<WebElement> buttonCnt = driver.findElements(By.xpath(
					"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li"));
			System.out.println(buttonCnt.size());
			for (int i = 1; i <= buttonCnt.size(); i++) {
				if (driver.findElement(By.xpath(
						"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li["
								+ i + "]/button"))
						.getText().trim().equalsIgnoreCase("ACCEPT DR. PERMISSION")) {
					waitSometime();
					moveToElement(driver.findElement(By.xpath(
							"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li["
									+ i + "]/button")));
					driver.findElement(By.xpath(
							"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li["
									+ i + "]/button"))
							.click();
					break;
				}
			}
			waitSometime();
			explicitWait(acceptDoctorPermissionHeader);
			System.out.println(
					"++++++++++++++++     " + driver.findElement(acceptDoctorPermissionHeader).getText().trim());
			Assert.assertEquals(driver.findElement(acceptDoctorPermissionHeader).getText().trim().toUpperCase(),
					"ACCEPT DOCTOR PERMISSION", "Accept doctor permission is not present");
			status = true;
		} else {
			status = false;
		}
		return status;
	}

	// Validate EHR accept or reject from the appointment
	public boolean verifyAcceptRejectEHRPermission(String bookingID, String permissionDuration, String permissionNeeded)
			throws Exception {
		Boolean status = false;
		explicitWait(acceptDoctorPermissionHeader);
		Select expiryYear1 = new Select(driver.findElement(permissionDurationDrpDwn));
		expiryYear1.selectByVisibleText(permissionDuration);
		if (permissionNeeded.equalsIgnoreCase("ACCEPT"))
			driver.findElement(acceptBtn).click();
		else
			driver.findElement(rejectBtn).click();
		waitSometime();
		List<WebElement> buttonCnt = driver.findElements(By.xpath(
				"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li"));
		System.out.println(buttonCnt.size());
		for (int i = 1; i <= buttonCnt.size(); i++) {
			if (driver.findElement(By.xpath(
					"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li["
							+ i + "]/button"))
					.getAttribute("data-bs-target").equalsIgnoreCase("#doctorPermissionModalLisiting")) {
				if (driver.findElement(By.xpath(
						"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li["
								+ i + "]/button/span"))
						.getText().trim().equalsIgnoreCase("COMPLETED REQUEST")) {
					Assert.assertTrue(driver.findElement(By.xpath(
							"//*[@id=\"DASHBOARD\"]/div[3]/div/div/div/div/div[2]/div/app-appointment-card/section/div[1]/div[3]/ul/li["
									+ i + "]/button/span"))
							.isDisplayed(), "Complete request button is not displayed");
					status = true;
					break;
				}
			}
		}
		return status;
	}

	// Function for verifying appointments
	public Boolean verifyAppointments(String appointmentDate, String patientName, String providerName,
			String statusFilter, String tcID, String sheet) {
		boolean result = false;
		waitSometime();
		explicitWait(dashboardBtn);
		driver.findElement(medicalRecordBtn).click();
		driver.findElement(appointmentBtn).click();
		if (driver.findElement(appointmentScheduleHeader).getText().trim().contains("SCHEDULE")) {
			driver.findElement(dateTxt).sendKeys(appointmentDate);
			driver.findElement(searchBtn).click();
			waitSometime();
			takeScreenShot(driver, "SearchResultForEPrescription", tcID, sheet);
			Select patientList = new Select(driver.findElement(patientNameDrpDwn));
			Select providerList = new Select(driver.findElement(providerNameDrpDwn));
			Select statusList = new Select(driver.findElement(statusDrpDwn));
			patientList.selectByVisibleText(patientName);
			providerList.selectByVisibleText(providerName);
			statusList.selectByVisibleText(statusFilter);
			driver.findElement(searchBtn).click();
			List<WebElement> appointmentList = driver.findElements(By.xpath("//app-appointment-card/section/div"));
			if (appointmentList.size() == 1) {
				if (driver
						.findElement(
								By.xpath("//app-appointment-card/section/div/div[1]/div[2]/div/div[2]/div[1]/div[2]/p"))
						.getText().trim().equalsIgnoreCase(patientName)
						&& driver
								.findElement(By.xpath(
										"//app-appointment-card/section/div/div[1]/div[2]/div/div[2]/div[1]/div[3]/p"))
								.getText().trim().equalsIgnoreCase(statusFilter)
						&& driver.findElement(By.xpath(
								"//app-appointment-card/section/div/div[1]/div[2]/div/div[2]/div[2]/ul[2]/li[2]"))
								.getText().trim().equalsIgnoreCase(providerName)) {
					takeScreenShot(driver, "FilteredAppointment", tcID, sheet);
					result = true;
				}
			} else {
				for (int i = 1; i <= appointmentList.size(); i++) {
					if (driver
							.findElement(By.xpath("//app-appointment-card/section/div[" + i
									+ "]/div[1]/div[2]/div/div[2]/div[1]/div[2]/p"))
							.getText().trim().equalsIgnoreCase(patientName)
							&& driver
									.findElement(By.xpath("//app-appointment-card/section/div[" + i
											+ "]/div[1]/div[2]/div/div[2]/div[1]/div[3]/p"))
									.getText().trim().equalsIgnoreCase(statusFilter)
							&& driver
									.findElement(By.xpath("//app-appointment-card/section/div[" + i
											+ "]/div[1]/div[2]/div/div[2]/div[2]/ul[2]/li[2]"))
									.getText().trim().equalsIgnoreCase(providerName)) {
						takeScreenShot(driver, "FilteredAppointment", tcID, sheet);
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}

	// Function for verifying My favorites
	public boolean verifyMyFavorites(String eventName, String articleName, String tipName, String doctorName,
			String productName, String providerName, String clinicName, String tcID, String sheet) {
		boolean status = false;
		waitSometime();
		explicitWait(dashboardBtn);
		driver.findElement(myFavoritesBtn).click();
		waitSometime();
		selectFavoritesOneByOne(" Favourite Events", favoriteEventTxt, eventName, tcID, sheet);
		selectFavoritesOneByOne(" Favourite Articles", favoriteArticleTxt, articleName, tcID, sheet);
		selectFavoritesOneByOne(" Favourite Health Tips ", favoriteHealthTipsTxt, tipName, tcID, sheet);
		selectFavoritesOneByOne(" Favourite Doctors", favoriteDoctorTxt, doctorName, tcID, sheet);
		selectFavoritesOneByOne(" Favorite Pharmacies & Stores", null, "", tcID, sheet);
		selectFavoritesOneByOne(" Favourite Products", favoriteProductTxt, productName, tcID, sheet);
		selectFavoritesOneByOne(" Favorite Medical Service Provider", favoriteProviderTxt, providerName, tcID, sheet);
		status = selectFavoritesOneByOne(" Favorite Clinics", favoriteClinicTxt, clinicName, tcID, sheet);
		return status;
	}

	// Sub-Function for verifying My favorites
	public boolean selectFavoritesOneByOne(String subjectName, By favorite, String name, String tcID, String sheet) {
		boolean status = false;
		Select subjectList = new Select(driver.findElement(favoriteSubjectDrpDwn));
		subjectList.selectByVisibleText(subjectName);
		if (subjectName.contains("Favorite Pharmacies & Stores")) {
			status = true;
			waitForSpecificTime(1000);
			takeScreenShot(driver, subjectName.trim(), tcID, sheet);
		} else if (driver.findElement(favorite).getText().trim().equalsIgnoreCase(name)
				&& !subjectName.contains("Favorite Pharmacies & Stores")) {
			status = true;
			waitForSpecificTime(1000);
			takeScreenShot(driver, subjectName.trim(), tcID, sheet);
		}
		return status;
	}

	// Function for verifying E-Prescription
	public Boolean verifyEPrescription(String appointmentDate, String patientName, String providerName,
			String statusFilter, String referredPharmacy, String exrNumber, String statusInResult, String tcID,
			String sheet) {
		boolean result = false;
		waitForSpecificTime(5000);
		explicitWait(dashboardBtn);
		driver.findElement(medicalRecordBtn).click();
		driver.findElement(ePrescriptionBtn).click();
		if (driver.findElement(ePrescriptionHeader).getText().trim().contains("E-PRESCRIPTION")) {
			driver.findElement(exrNumberTxt).sendKeys(exrNumber);
			driver.findElement(searchBtn).click();
			waitSometime();
			Select patientList = new Select(driver.findElement(patientNameDrpDwn));
			Select providerList = new Select(driver.findElement(providerNameDrpDwn));
			Select statusList = new Select(driver.findElement(statusDrpDwn));
			patientList.selectByVisibleText(patientName);
			providerList.selectByVisibleText(providerName);
			statusList.selectByVisibleText(statusFilter);
			driver.findElement(searchBtn).click();
			List<WebElement> prescriptionList = driver.findElements(
					By.xpath("//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div"));
			if (prescriptionList.size() == 1) {
				if (driver.findElement(By.xpath(
						"//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div/div[1]/div/div/div[2]/div/div[1]/ul[1]/li[2]"))
						.getText().trim().equalsIgnoreCase(patientName)
						&& driver.findElement(By.xpath(
								"//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div/div[1]/div/div/div[1]/div[3]/span"))
								.getText().trim().equalsIgnoreCase(statusInResult)
						&& driver.findElement(By.xpath(
								"//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div/div[1]/div/div/div[1]/div[2]/h4"))
								.getText().trim().contains(providerName)
						&& driver.findElement(By.xpath(
								"//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div/div[1]/div/div/div[2]/div/div[2]/ul[2]/li[2]"))
								.getText().trim().equalsIgnoreCase(referredPharmacy)
						&& driver.findElement(By.xpath(
								"//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div/div[1]/div/div/div[2]/div/div[2]/ul[1]/li[2]"))
								.getText().trim().equalsIgnoreCase(exrNumber)) {
					takeScreenShot(driver, "FilteredEPrescription", tcID, sheet);
					result = true;
				}
			} else {
				for (int i = 1; i <= prescriptionList.size(); i++) {
					if (driver
							.findElement(By.xpath(
									"//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div["
											+ i + "]/div[1]/div/div/div[2]/div/div[1]/ul[1]/li[2]"))
							.getText().trim().equalsIgnoreCase(patientName)
							&& driver.findElement(By.xpath(
									"//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div["
											+ i + "]/div[1]/div/div/div[1]/div[3]/span"))
									.getText().trim().equalsIgnoreCase(statusInResult)
							&& driver.findElement(By.xpath(
									"/app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div["
											+ i + "]div[1]/div/div/div[1]/div[2]/h4"))
									.getText().trim().contains(providerName)
							&& driver.findElement(By.xpath(
									"//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div["
											+ i + "]/div[1]/div/div/div[2]/div/div[2]/ul/li[2]"))
									.getText().trim().equalsIgnoreCase(referredPharmacy)
							&& driver.findElement(By.xpath(
									"//app-e-prescriptions/div[1]/div/div/div/div[2]/div[3]/app-prescription-card/div["
											+ i + "]/div[1]/div/div/div[2]/div/div[2]/ul[1]/li[2]"))
									.getText().trim().equalsIgnoreCase(exrNumber)) {
						takeScreenShot(driver, "FilteredEPrescription", tcID, sheet);
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}

	// Validate E-Prescription pop up from dashboard
	public boolean verifyEPrescriptionPopup(String pharmacyName) throws Exception {
		waitSometime();
		explicitWait(ePrescriptionPopup);
		if (driver.findElement(referredPharmacyName).getText().trim().equalsIgnoreCase(pharmacyName)) {
			driver.findElement(proceedBtn).click();
		}
		waitSometime();
		explicitWait(checkOutHeader);
		System.out.println("++++++++++++++++     " + driver.findElement(checkOutHeader).getText().trim());
		Assert.assertEquals(driver.findElement(checkOutHeader).getText().trim(), "CHECKOUT",
				"Checkout header is not present");
		return true;
	}

	// Sub-Function for verifying account name in profile
	public boolean verifyProfileName(String patientName, String tcID, String sheet) {
		waitForSpecificTime(20000);
		explicitWait_60(dashboardBtn);
		driver.findElement(profileBtn).click();
		waitSometime();
		driver.findElement(myProfileBtn).click();
		waitSometime();
		System.out.println("++++++++++++++++     " + driver.findElement(firstNameTxt).getAttribute("value").trim());
		System.out.println("++++++++++++++++     " + driver.findElement(lastNameTxt).getAttribute("value").trim());
		Assert.assertEquals(
				driver.findElement(firstNameTxt).getAttribute("value").trim() + " "
						+ driver.findElement(lastNameTxt).getAttribute("value").trim(),
				patientName.trim(), "Patient name is not correct as the login credentials");
		takeScreenShot(getDriver(), "ProfileInformation", tcID, sheet);
		return true;
	}

	// Sub-Function for verifying account name in profile
	public boolean verifyRedeemVoucher(String voucherCode, String tcID, String sheet) {
		waitForSpecificTime(20000);
		explicitWait_60(dashboardBtn);
		driver.findElement(walletTab).click();
		waitSometime();
		explicitWait(walletHeader);
		waitSometime();
		driver.findElement(voucherTxt).sendKeys(voucherCode);
		driver.findElement(redeemBtn).click();
		waitSometime();
		Assert.assertEquals(
				driver.findElement(expectedStatus).getText().trim(),
				"Redeem Voucher", "Redeem Voucher text is NOT displayed");
		takeScreenShot(getDriver(), "VoucherRecharge", tcID, sheet);
		return true;
	}
	
	public String getWalletValue() {
		String value = driver.findElement(myWalletValue).getText().trim().split(" ")[0].toString();
		boolean hasComma = value.contains(",");
		if (hasComma) {
			for (int i = 0; i < value.length(); i++) {
				if (value.charAt(i) == ',') {
					value = value.replaceAll(",", "");
				} else
					continue;
				System.out.println("Actual wallet new amount: " + value);
				walletNewValue = roundNumberTo3Decimals(Double.valueOf(value));
			}
		} else {
			System.out.println("Actual wallet new amount: " + value);
			walletNewValue = roundNumberTo3Decimals(Double.valueOf(value));
		}
		return walletNewValue.toString();
	}
}
