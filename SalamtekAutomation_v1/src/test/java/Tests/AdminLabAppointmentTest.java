package Tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WindowType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Common.BaseTest;
import Pages.AdminProfileLoginPage;
import Pages.AdminProfileLogoutPage;
import Pages.BookAppointmentPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LabProfileHomePage;
import Pages.LabProfileLabAppointmentPage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.MyAccountPage;
import Pages.NotificationListPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class AdminLabAppointmentTest extends BaseTest {
	Boolean testResult = false;
	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(getDriver());
	CheckoutPage checkoutPage = new CheckoutPage(getDriver());
	LogoutPage logoutPage = new LogoutPage(getDriver());
	NotificationListPage notificationListPage = new NotificationListPage(getDriver());
	MyAccountPage myAccountPage = new MyAccountPage(getDriver());
	AdminProfileLoginPage adminProfileLoginPage = new AdminProfileLoginPage(getDriver());
	LabProfileHomePage labProfileHomePage = new LabProfileHomePage(getDriver());
	LabProfileLabAppointmentPage labProfileLabAppointmentPage = new LabProfileLabAppointmentPage(getDriver());
	AdminProfileLogoutPage adminProfileLogoutPage = new AdminProfileLogoutPage(getDriver());

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "AdminLabAppointment";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "verify whether admin is able to upload and complete the lab appointment and patient is able to view uploaded report", groups = {
			"AdminLabAppointment" }, dataProvider = "dataForAdminLabAppointment")
	public void AdminLabAppointment_UploadResult_Complete(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName,
			String medicalServiceSearchName, String medicalServiceName, String serviceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String requiredSubStatus, String titleEnglish, String titleArabic, String descriptionEnglish,
			String descriptionArabic, String refundType, String appointmentType) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("notification");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.selectMedicalService(medicalServiceSearchName, medicalServiceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectMedicalService(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectProfileService(serviceName,
				excelUtils.getValue("profileIndividualServiceSelection"),
				excelUtils.getValue("profileIndividualTestSelection"));
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.verifySelectedService(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectTimeSlotAndBook();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "BookingMedicalServiceSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyLabProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = labProfileLabAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "AppointmentInLabSection", testCaseID, sheetName);
		testResult = labProfileLabAppointmentPage.verifyUploadAndComplete(titleEnglish, titleArabic, descriptionEnglish,
				descriptionArabic, bookingID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		testResult = myAccountPage.verifyMedicalReportForPatient(bookingID);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = myAccountPage.verifyReportUploadReflectedForPatient(bookingID, titleEnglish);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "verify whether admin is able to cancel the lab appointment and patient is able to view the money got refunded to the wallet_VISA", groups = {
			"AdminLabAppointment" }, dataProvider = "dataForAdminLabAppointment")
	public void AdminLabAppointment_Cancel_VISA(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName,
			String medicalServiceSearchName, String medicalServiceName, String serviceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String requiredSubStatus, String titleEnglish, String titleArabic, String descriptionEnglish,
			String descriptionArabic, String refundType, String appointmentType) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("notification");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.selectMedicalService(medicalServiceSearchName, medicalServiceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectMedicalService(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectProfileService(serviceName,
				excelUtils.getValue("profileIndividualServiceSelection"),
				excelUtils.getValue("profileIndividualTestSelection"));
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.verifySelectedService(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectTimeSlotAndBook();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.enterVISADetails(firstName, lastName, address, city, country, cardNumber,
				cardExpiryMonth, cardExpiryYear, cardHolderName, CVV, username);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			excelUtils.updateValueToExcel("ErrorMessage",
					"Visa Payment Failed. Your order was declined. Please verify your information.");
			setUp();
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "BookingMedicalServiceSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		String grandTotalAmount = checkoutPage.getLabGrandTotalAmount();
		String firstWalletValue = myAccountPage.getFirstWalletValue();
		String expectedRefundAmount = myAccountPage.getExpectedRefundForAdminLabDoctorCancel(grandTotalAmount);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyLabProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileLabAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "AppointmentInLabSection", testCaseID, sheetName);
		testResult = labProfileLabAppointmentPage.verifyCancel(refundType, bookingID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String actualRefundAmount = myAccountPage.verifyRefundAmount(bookingID);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		String valueAfterCancellation = myAccountPage.getWalletUpdatedValue();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = myAccountPage.verifyTotalWalletAmountAfterRefund_ShopOnline_MS();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("expectedRefundAmount", expectedRefundAmount);
			excelUtils.updateValueToExcel("actualRefundAmount", actualRefundAmount);
			excelUtils.updateValueToExcel("valueAfterCancellation", valueAfterCancellation);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "verify whether admin is able to approve the lab appointment with insurance and patient is able to book the appointment", groups = {
			"AdminLabAppointment" }, dataProvider = "dataForAdminLabAppointment")
	public void AdminLabAppointment_Insurance(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName,
			String medicalServiceSearchName, String medicalServiceName, String serviceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String requiredSubStatus, String titleEnglish, String titleArabic, String descriptionEnglish,
			String descriptionArabic, String refundType, String appointmentType) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("notification");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.selectMedicalService(medicalServiceSearchName, medicalServiceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectMedicalService(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectProfileService(serviceName,
				excelUtils.getValue("profileIndividualServiceSelection"),
				excelUtils.getValue("profileIndividualTestSelection"));
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.verifySelectedService(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectTimeSlotAndBook();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.enterInsuranceDetailsForLab(enableInsurance, insuranceCardNumber);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "BookingMedicalServiceSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyLabProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileLabAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "AppointmentInLabSection", testCaseID, sheetName);
		testResult = labProfileLabAppointmentPage.verifyInsurance(approveOrReject, coveredByInsuranceAmount, bookingID,
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String notification = notificationListPage.verifyNotificationPresenceForInsurance_Lab(approveOrReject,
				bookingID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		if (approveOrReject.equalsIgnoreCase("Approve")) {
			testResult = checkoutPage.verifyInsuranceDeduction(coveredByInsuranceAmount);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);

			}
		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		if (paymentMode.equalsIgnoreCase("KNET")) {
			testResult = checkoutPage.enterKNETDetails(bankName, cardNumber, cardExpiryMonth, cardExpiryYear, pin);
			if (testResult.equals(false)) {
				excelUtils.updateResult(false);
				excelUtils.updateValueToExcel("ErrorMessage", "KNET Payment Failed.");
				setUp();
				assertEquals(testResult, true);
			}
		} else if (paymentMode.equalsIgnoreCase("VISA")) {
			testResult = checkoutPage.enterVISADetails(firstName, lastName, address, city, country, cardNumber,
					cardExpiryMonth, cardExpiryYear, cardHolderName, CVV, username);
			if (testResult.equals(false)) {
				excelUtils.updateResult(false);
				excelUtils.updateValueToExcel("ErrorMessage",
						"Visa Payment Failed. Your order was declined. Please verify your information.");
				setUp();
				assertEquals(testResult, true);
			}
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("notification", notification);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "verify whether admin is able to cancel the lab appointment and patient is able to view the money got refunded to the wallet_KNET", groups = {
			"AdminLabAppointment" }, dataProvider = "dataForAdminLabAppointment")
	public void AdminLabAppointment_Cancel_KNET(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName,
			String medicalServiceSearchName, String medicalServiceName, String serviceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String requiredSubStatus, String titleEnglish, String titleArabic, String descriptionEnglish,
			String descriptionArabic, String refundType, String appointmentType) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("notification");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.selectMedicalService(medicalServiceSearchName, medicalServiceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectMedicalService(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectProfileService(serviceName,
				excelUtils.getValue("profileIndividualServiceSelection"),
				excelUtils.getValue("profileIndividualTestSelection"));
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.verifySelectedService(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectTimeSlotAndBook();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		if (paymentMode.equalsIgnoreCase("KNET")) {
			testResult = checkoutPage.enterKNETDetails(bankName, cardNumber, cardExpiryMonth, cardExpiryYear, pin);
			if (testResult.equals(false)) {
				excelUtils.updateResult(false);
				excelUtils.updateValueToExcel("ErrorMessage", "KNET Payment Failed.");
				setUp();
				assertEquals(testResult, true);
			}
		} else if (paymentMode.equalsIgnoreCase("VISA")) {
			testResult = checkoutPage.enterVISADetails(firstName, lastName, address, city, country, cardNumber,
					cardExpiryMonth, cardExpiryYear, cardHolderName, CVV, username);
			if (testResult.equals(false)) {
				excelUtils.updateResult(false);
				excelUtils.updateValueToExcel("ErrorMessage",
						"Visa Payment Failed. Your order was declined. Please verify your information.");
				setUp();
				assertEquals(testResult, true);
			}
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "BookingMedicalServiceSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		String grandTotalAmount = checkoutPage.getLabGrandTotalAmount();
		String firstWalletValue = myAccountPage.getFirstWalletValue();
		String expectedRefundAmount = myAccountPage.getExpectedRefundForAdminLabDoctorCancel(grandTotalAmount);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyLabProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = labProfileLabAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "AppointmentInLabSection", testCaseID, sheetName);
		testResult = labProfileLabAppointmentPage.verifyCancel(refundType, bookingID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String actualRefundAmount = myAccountPage.verifyRefundAmount(bookingID);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		String valueAfterCancellation = myAccountPage.getWalletUpdatedValue();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = myAccountPage.verifyTotalWalletAmountAfterRefund_ShopOnline_MS();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("expectedRefundAmount", expectedRefundAmount);
			excelUtils.updateValueToExcel("actualRefundAmount", actualRefundAmount);
			excelUtils.updateValueToExcel("valueAfterCancellation", valueAfterCancellation);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "Verify whether lab admin is able to create walkIn appointment from Create Walkin", groups = {
			"AdminLabAppointment" }, dataProvider = "dataForAdminLabAppointment")
	public void AdminLabAppointment_CreateWalkIn(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName,
			String medicalServiceSearchName, String medicalServiceName, String serviceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String requiredSubStatus, String titleEnglish, String titleArabic, String descriptionEnglish,
			String descriptionArabic, String refundType, String appointmentType) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("notification");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		waitASecond();
		driver.get(adminURL);
		testResult = adminProfileLoginPage.verifyLabProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = labProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = labProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = labProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = labProfileLabAppointmentPage.verifyCreateWalkIn(username, medicalServiceName,
				profileIndividualTestSelection, appointmentType, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		waitSometime();
		testResult = labProfileHomePage.selectSuboperationOfLab("WalkIn");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = labProfileHomePage.selectSubStatusOfLab(requiredSubStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		String bookingID = labProfileLabAppointmentPage.getAppointmentNumber();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "Verify whether lab admin is able to create normal appointment from Create Walkin", groups = {
			"AdminLabAppointment" }, dataProvider = "dataForAdminLabAppointment")
	public void AdminLabAppointment_CreateAppointment(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName,
			String medicalServiceSearchName, String medicalServiceName, String serviceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String requiredSubStatus, String titleEnglish, String titleArabic, String descriptionEnglish,
			String descriptionArabic, String refundType, String appointmentType) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("notification");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		waitASecond();
		driver.get(adminURL);
		testResult = adminProfileLoginPage.verifyLabProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = labProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = labProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = labProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = labProfileLabAppointmentPage.verifyCreateAppointment(username, medicalServiceName,
				profileIndividualTestSelection, appointmentType, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		waitSometime();
		testResult = labProfileHomePage.selectSuboperationOfLab("allInLab");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		String bookingID = labProfileLabAppointmentPage.getAppointmentNumber();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@DataProvider(name = "dataForAdminLabAppointment")
	public Object[][] getData(Method m) throws IOException {
		int countIteration = 0, cnt = 0;
		FileInputStream fs = new FileInputStream(getExcelSheetName());
		workbook = new XSSFWorkbook(fs);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				System.out.println("SheetName: " + workbook.getSheetName(i));
				sheet = workbook.getSheetAt(i);
				break;
			}
		}
		row = sheet.getRow(0);
		int lastRow = sheet.getLastRowNum() + 1;
		int ColNum = row.getLastCellNum(); // get last ColNum
		System.out.println("Last Row of the sheet: " + lastRow);
		for (int i = 1; i < lastRow; i++) {
			String executionStatus = sheet.getRow(i).getCell(3).getStringCellValue();
			String testCaseName = sheet.getRow(i).getCell(1).getStringCellValue();
			System.out.println("Execution Status Value: " + sheet.getRow(i).getCell(3).getStringCellValue());
			if (executionStatus.equalsIgnoreCase("Y") && testCaseName.equalsIgnoreCase(m.getName())) {
				countIteration = countIteration + 1;
			}
		}
		Object[][] data = new Object[countIteration][ColNum - 9];
		for (int i1 = 1; i1 < lastRow; i1++) {
			String executionStatus1 = sheet.getRow(i1).getCell(3).getStringCellValue();
			String testCaseName1 = sheet.getRow(i1).getCell(1).getStringCellValue();
			if (executionStatus1.equalsIgnoreCase("Y") && testCaseName1.equalsIgnoreCase(m.getName())) {
				for (int j = 0; j < ColNum - 9; j++) {
					cell = sheet.getRow(i1).getCell(j);
					if (cell == null)
						data[cnt][j] = "";
					else {
						System.out.println("Value: " + sheet.getRow(i1).getCell(j).getStringCellValue());
						data[cnt][j] = sheet.getRow(i1).getCell(j).getStringCellValue();
					}
				}
				cnt = cnt + 1;
			}
		}
		workbook.close();
		fs.close();
		return data;
	}
}
