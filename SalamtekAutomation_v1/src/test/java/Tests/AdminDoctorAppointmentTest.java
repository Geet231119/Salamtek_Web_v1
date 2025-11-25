package Tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WindowType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Common.BaseTest;
import Pages.AdminDoctorAppointmentPage;
import Pages.AdminOrderPage;
import Pages.AdminProfileHomePage;
import Pages.AdminProfileLabAppointmentPage;
import Pages.AdminProfileLoginPage;
import Pages.AdminProfileLogoutPage;
import Pages.AdminReferralReportPage;
import Pages.BookAppointmentPage;
import Pages.CartDetailsPage;
import Pages.CartMainPage;
import Pages.CheckoutPage;
import Pages.DoctorProfileAppointmentPage;
import Pages.DoctorProfileHomePage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.MyAccountPage;
import Pages.NotificationListPage;
import Pages.ShopOnlineDetailsPage;
import Pages.ShopOnlineMainPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class AdminDoctorAppointmentTest extends BaseTest {
	Boolean testResult = false;
	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(getDriver());
	CheckoutPage checkoutPage = new CheckoutPage(getDriver());
	LogoutPage logoutPage = new LogoutPage(getDriver());
	MyAccountPage myAccountPage = new MyAccountPage(getDriver());
	AdminProfileLoginPage adminProfileLoginPage = new AdminProfileLoginPage(getDriver());
	AdminProfileHomePage adminProfileHomePage = new AdminProfileHomePage(getDriver());
	DoctorProfileHomePage doctorProfileHomePage = new DoctorProfileHomePage(getDriver());
	AdminProfileLabAppointmentPage adminProfileLabAppointmentPage = new AdminProfileLabAppointmentPage(getDriver());
	AdminDoctorAppointmentPage adminDoctorAppointmentPage = new AdminDoctorAppointmentPage(getDriver());
	DoctorProfileAppointmentPage doctorProfileAppointmentPage = new DoctorProfileAppointmentPage(getDriver());
	AdminOrderPage adminOrderPage = new AdminOrderPage(getDriver());
	AdminProfileLogoutPage adminProfileLogoutPage = new AdminProfileLogoutPage(getDriver());
	NotificationListPage notificationListPage = new NotificationListPage(getDriver());
	ShopOnlineMainPage shopOnlineMainPage = new ShopOnlineMainPage(getDriver());
	ShopOnlineDetailsPage shopOnlineDetailsPage = new ShopOnlineDetailsPage(getDriver());
	CartMainPage cartMainPage = new CartMainPage(getDriver());
	CartDetailsPage cartDetailsPage = new CartDetailsPage(driver);
	AdminReferralReportPage adminReferralReportPage = new AdminReferralReportPage(getDriver());

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "AdminDoctorAppointment";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "verify whether admin is able to start the appointment", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_Start(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorSearchName,
			String doctorName, String serviceName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
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
		testResult = adminProfileLoginPage.verifyDoctorProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = doctorProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = doctorProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = doctorProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = doctorProfileAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = doctorProfileAppointmentPage.verifyStartingAnAppointment(bookingID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether admin is able to reschedule the appointment", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_Reschedule(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorSearchName,
			String doctorName, String serviceName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
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
		testResult = adminProfileLoginPage.verifyDoctorProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyRescheduleAnAppointment(bookingID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String notification = notificationListPage.verifyNotificationPresenceForReschedule(bookingID, doctorName,
				testCaseID, sheetName);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("notification", notification);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether admin is able to upload report and complete the appointment", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_UploadResult_Complete(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorSearchName,
			String doctorName, String serviceName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
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
		testResult = adminProfileLoginPage.verifyDoctorProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyUploadAndComplete(titleEnglish, titleArabic, descriptionEnglish,
				descriptionArabic, bookingID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String notification = notificationListPage.verifyNotificationPresenceForComplete(bookingID, doctorName,
				testCaseID, sheetName);
		testResult = myAccountPage.verifyMedicalReportForPatient(bookingID);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = myAccountPage.verifyReportUploadReflectedForPatient(bookingID, titleEnglish);
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
		}
	}

	@Test(description = "verify whether admin is able to add diagnostic notes in the appointment", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_AddDiagnosticNotes(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorSearchName,
			String doctorName, String serviceName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
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
		testResult = adminProfileLoginPage.verifyDoctorProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String addedNotes = doctorProfileAppointmentPage.verifyAddingDiagnosisNote(notes, bookingID, testCaseID,
				sheetName);
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String notification = notificationListPage.verifyNotificationPresenceForDiagnosticNotes(doctorName, testCaseID,
				sheetName);
		testResult = myAccountPage.verifyDiagnosticNotesInAppointment(addedNotes);
		driver.get("https://dev.salamtek.com/en/my-account");
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("addedNotes", addedNotes);
			excelUtils.updateValueToExcel("notification", notification);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether admin is able to cancel the doctor appointment and patient is able to view the money got refunded to the wallet_VISA", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_Cancel_VISA(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorSearchName,
			String doctorName, String serviceName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.enterVISADetails(firstName, lastName, address, city, country, cardNumber,
				cardExpiryMonth, cardExpiryYear, cardHolderName, CVV, username);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			excelUtils.updateValueToExcel("ErrorMessage", "Visa Payment Failed. Your order was declined. Please verify your information.");
			setUp();
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		String grandTotalAmount = checkoutPage.getDoctorGrandTotalAmount(payOnlyBookingFee);
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
		testResult = adminProfileLoginPage.verifyDoctorProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyCancel(refundType, bookingID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String actualRefundAmount = myAccountPage.verifyRefundAmount(bookingID);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String valueAfterCancellation = myAccountPage.getWalletUpdatedValue();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = myAccountPage.verifyTotalWalletAmountAfterRefund_ShopOnline_MS();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String notification = notificationListPage.verifyNotificationPresenceForCancel(bookingID, doctorName,
				testCaseID, sheetName);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("expectedRefundAmount", expectedRefundAmount);
			excelUtils.updateValueToExcel("actualRefundAmount", actualRefundAmount);
			excelUtils.updateValueToExcel("valueAfterCancellation", valueAfterCancellation);
			excelUtils.updateValueToExcel("notification", notification);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether admin is able to cancel the doctor appointment and patient is able to view the money got refunded to the wallet_KNET", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_Cancel_KNET(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorSearchName,
			String doctorName, String serviceName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.enterKNETDetails(bankName, cardNumber, cardExpiryMonth, cardExpiryYear, pin);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			excelUtils.updateValueToExcel("ErrorMessage", "KNET Payment Failed.");
			setUp();
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		String grandTotalAmount = checkoutPage.getDoctorGrandTotalAmount(payOnlyBookingFee);
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
		testResult = adminProfileLoginPage.verifyDoctorProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyCancel(refundType, bookingID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String actualRefundAmount = myAccountPage.verifyRefundAmount(bookingID);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String valueAfterCancellation = myAccountPage.getWalletUpdatedValue();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = myAccountPage.verifyTotalWalletAmountAfterRefund_ShopOnline_MS();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String notification = notificationListPage.verifyNotificationPresenceForCancel(bookingID, doctorName,
				testCaseID, sheetName);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("expectedRefundAmount", expectedRefundAmount);
			excelUtils.updateValueToExcel("actualRefundAmount", actualRefundAmount);
			excelUtils.updateValueToExcel("valueAfterCancellation", valueAfterCancellation);
			excelUtils.updateValueToExcel("notification", notification);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether admin is able to schedule a followup appointment for the patient", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_FollowUpAppointment(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorSearchName,
			String doctorName, String serviceName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
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
		testResult = adminProfileLoginPage.verifyDoctorProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyFollowUpAppointment(bookingID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String notification = notificationListPage.verifyNotificationPresenceForFollowUpAppointment(bookingID,
				doctorName, testCaseID, sheetName);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("notification", notification);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether admin is able to prescribe medicine to the patient", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_PrescribeMedicine(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorSearchName,
			String doctorName, String serviceName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
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
		testResult = adminProfileLoginPage.verifyDoctorProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String exrNumber = doctorProfileAppointmentPage.verifyPrescribeMedicine(prescriptionType, totalUsage,
				productName, duration, instruction, referredPharmacy, bookingID, testCaseID, sheetName);
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String notification = notificationListPage.verifyNotificationPresenceForPrescribeMedicineConductTest(bookingID,
				doctorName, testCaseID, sheetName);
		String grandTotalAmount = myAccountPage.verifyPrescribeMedicineInEPrescription(exrNumber);
		testResult = cartDetailsPage.verifyProceedToCheckout(uploadNeeded);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();

		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("notification", notification);
			excelUtils.updateValueToExcel("exrNumber", exrNumber);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether admin is able to request conduct test for the patient", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_ConductTest(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorSearchName,
			String doctorName, String serviceName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
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
		testResult = adminProfileLoginPage.verifyDoctorProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String exrNumber = doctorProfileAppointmentPage.verifyConductTest(prescriptionType, totalUsage, serviceType,
				service, referredLab, bookingID, testCaseID, sheetName);
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String notification = notificationListPage.verifyNotificationPresenceForPrescribeMedicineConductTest(bookingID,
				doctorName, testCaseID, sheetName);
		String grandTotalAmount = myAccountPage.verifyConductTestInMedicalRequest(exrNumber);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartDetailsPage.verifyProceedToCheckoutForConductTest(uploadNeeded);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = bookAppointmentPage.selectTimeSlotAndBook();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();

		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("notification", notification);
			excelUtils.updateValueToExcel("exrNumber", exrNumber);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether admin is able to request EHR permission to the patient", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_EHRPermissions(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorSearchName,
			String doctorName, String serviceName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
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
		testResult = adminProfileLoginPage.verifyDoctorProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = doctorProfileAppointmentPage.verifyEHRPermission(recordType, reportName, bookingID, testCaseID,
				sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.switchTo().window(currentHandle);
		String notification = notificationListPage.verifyNotificationPresenceForEHRPermissions(bookingID, doctorName,
				testCaseID, sheetName);
		testResult = myAccountPage.verifyAppointmentFromDashboard(bookingID);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = myAccountPage.verifyAcceptRejectEHRPermission(bookingID, permissionDuration, permissionNeeded);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		ArrayList<String> wid = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(wid.get(1));
		driver.navigate().refresh();
		testResult = doctorProfileAppointmentPage.verifyViewEHRAfterAccept(permissionNeeded, reportName, bookingID,
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("notification", notification);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether admin is able to calculate referral_REFERRAL_DOCTOR_To_LAB", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_REFERRAL_DOCTOR_To_LAB(String testCaseID, String testCaseName,
			String description, String executionStatus, String username, String password, String operationName,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");

		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "appointments");
		excelUtils.updateValueToExcel("requiredAppointment", "doctorAppointment");
		excelUtils.updateValueToExcel("requiredStatus", "allInDoctor");

		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

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
				excelUtils.updateValueToExcel("ErrorMessage", "Visa Payment Failed. Your order was declined. Please verify your information.");
				setUp();
				assertEquals(testResult, true);
			}
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
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
		testResult = adminProfileLoginPage.verifyAdminLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminDoctorAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String exrNumber = adminDoctorAppointmentPage.verifyConductTest(prescriptionType, totalUsage, serviceType,
				service, referredLab, bookingID, testCaseID, sheetName);
		testResult = adminProfileHomePage.closeOpenedTabs("doctorAppointment");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("appointments");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.switchTo().window(currentHandle);
		String notification = notificationListPage.verifyNotificationPresenceForPrescribeMedicineConductTest(bookingID,
				doctorName, testCaseID, sheetName);
		String grandTotalAmount = myAccountPage.verifyConductTestInMedicalRequest(exrNumber);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartDetailsPage.verifyProceedToCheckoutForConductTest(uploadNeeded);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = bookAppointmentPage.selectTimeSlotAndBook();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

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
				excelUtils.updateValueToExcel("ErrorMessage", "Visa Payment Failed. Your order was declined. Please verify your information.");
				setUp();
				assertEquals(testResult, true);
			}
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		// String grandTotalAmount = checkoutPage.getLabGrandTotalAmount();
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		ArrayList<String> wid = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(wid.get(1));
		driver.navigate().refresh();
		testResult = adminProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("requiredAppointment", "labAppointment");
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("requiredStatus", "allInLab");
		testResult = adminProfileHomePage.selectSuboperationOfLab(excelUtils.getValue("requiredStatus"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileLabAppointmentPage.verifyAppointmentPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileLabAppointmentPage.verifyComplete(orderID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileLabAppointmentPage.verifyAppointmentPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
//				testResult = adminProfileLabAppointmentPage.verifyCalculateReferralFromLab(orderID, testCaseID,
//						sheetName);
//				if (testResult.equals(false)) {
//					logoutPage.verifyLogout();
//					excelUtils.updateResult(testResult);
//					
//				}

		testResult = adminProfileHomePage.closeOpenedTabs("labAppointment");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("appointments");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "settings");
		excelUtils.updateValueToExcel("requiredAppointment", "referrals");
		testResult = adminProfileHomePage
				.selectOperationFromLeftPanel(excelUtils.getValue("selectionOperationFromLeftPanel"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String referralCommission = adminReferralReportPage.verifyReferralCommission(doctorName, doctorSearchName,
				referredPharmacy, referredLab, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("referrals");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("settings");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "reports");
		testResult = adminProfileHomePage
				.selectOperationFromLeftPanel(excelUtils.getValue("selectionOperationFromLeftPanel"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("requiredAppointment", "referralCalculation");
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String referralAmount = adminReferralReportPage.verifyReferralCalculationInReport(orderID, doctorName,
				referredLab, referralCommission, grandTotalAmount, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "appointments");
		excelUtils.updateValueToExcel("requiredAppointment", "doctorAppointment");
		excelUtils.updateValueToExcel("requiredStatus", "allInDoctor");
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("notification", notification);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("actualReferralAmount", referralAmount);
			excelUtils.updateValueToExcel("expectedReferralAmount", referralAmount);
			excelUtils.updateValueToExcel("referralCommission", referralCommission);
			excelUtils.updateValueToExcel("exrNumber", exrNumber);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether admin is able to calculate referral_REFERRAL_SALAMTEK_To_LAB", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_REFERRAL_SALAMTEK_To_LAB(String testCaseID, String testCaseName,
			String description, String executionStatus, String username, String password, String operationName,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");

		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "settings");
		excelUtils.updateValueToExcel("requiredAppointment", "referrals");

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
		testResult = homePage.selectMedicalService(referredLab, referredLab);
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
		testResult = bookAppointmentPage.selectProfileService(serviceName, profileIndividualServiceSelection, service);
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
				excelUtils.updateValueToExcel("ErrorMessage", "Visa Payment Failed. Your order was declined. Please verify your information.");
				setUp();
				assertEquals(testResult, true);
			}
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		String grandTotalAmount = checkoutPage.getLabGrandTotalAmount();
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyAdminLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "settings");
		excelUtils.updateValueToExcel("requiredAppointment", "referrals");
		testResult = adminProfileHomePage
				.selectOperationFromLeftPanel(excelUtils.getValue("selectionOperationFromLeftPanel"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String referralCommission = adminReferralReportPage.verifyReferralCommissionForSalamtek(doctorName,
				referredPharmacy, referredLab, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("referrals");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("settings");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "reports");
		testResult = adminProfileHomePage
				.selectOperationFromLeftPanel(excelUtils.getValue("selectionOperationFromLeftPanel"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("requiredAppointment", "referralCalculation");
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String referralAmount = adminReferralReportPage.verifyReferralCalculationInReportForSalamtek(bookingID,
				referredLab, referralCommission, grandTotalAmount, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "settings");
		excelUtils.updateValueToExcel("requiredAppointment", "referrals");
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("actualReferralAmount", referralAmount);
			excelUtils.updateValueToExcel("expectedReferralAmount", referralAmount);
			excelUtils.updateValueToExcel("referralCommission", referralCommission);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether admin is able to calculate referral_REFERRAL_DOCTOR_To_PHARMACY", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_REFERRAL_DOCTOR_To_PHARMACY(String testCaseID, String testCaseName,
			String description, String executionStatus, String username, String password, String operationName,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");

		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "appointments");
		excelUtils.updateValueToExcel("requiredAppointment", "doctorAppointment");
		excelUtils.updateValueToExcel("requiredStatus", "allInDoctor");

		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

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
				excelUtils.updateValueToExcel("ErrorMessage", "Visa Payment Failed. Your order was declined. Please verify your information.");
				setUp();
				assertEquals(testResult, true);
			}
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
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
		testResult = adminProfileLoginPage.verifyAdminLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminDoctorAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String exrNumber = adminDoctorAppointmentPage.verifyPrescribeMedicine(prescriptionType, totalUsage, productName,
				duration, instruction, referredPharmacy, bookingID, testCaseID, sheetName);
		testResult = adminProfileHomePage.closeOpenedTabs("doctorAppointment");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("appointments");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.switchTo().window(currentHandle);
		String notification = notificationListPage.verifyNotificationPresenceForPrescribeMedicineConductTest(bookingID,
				doctorName, testCaseID, sheetName);
		String grandTotalAmount = myAccountPage.verifyPrescribeMedicineInEPrescription(exrNumber);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartDetailsPage.verifyProceedToCheckout(uploadNeeded);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

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
				excelUtils.updateValueToExcel("ErrorMessage", "Visa Payment Failed. Your order was declined. Please verify your information.");
				setUp();
				assertEquals(testResult, true);
			}
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		// String grandTotalAmount = checkoutPage.getShopOnineTotalPrice();
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		ArrayList<String> wid = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(wid.get(1));
		driver.navigate().refresh();
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "orders");
		testResult = adminProfileHomePage
				.selectOperationFromLeftPanel(excelUtils.getValue("selectionOperationFromLeftPanel"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("requiredAppointment", "allInOrders");
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminOrderPage.verifyOrderPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
//				testResult = adminOrderPage.verifyCalculateReferralFromOrders(orderID, testCaseID, sheetName);
//				if (testResult.equals(false)) {
//					logoutPage.verifyLogout();
//					excelUtils.updateResult(testResult);
//					
//				}

		testResult = adminProfileHomePage.closeOpenedTabs("allInOrders");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("orders");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "settings");
		excelUtils.updateValueToExcel("requiredAppointment", "referrals");
		testResult = adminProfileHomePage
				.selectOperationFromLeftPanel(excelUtils.getValue("selectionOperationFromLeftPanel"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String referralCommission = adminReferralReportPage.verifyReferralCommission(doctorName, doctorSearchName,
				referredPharmacy, referredLab, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("referrals");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("settings");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "reports");
		testResult = adminProfileHomePage
				.selectOperationFromLeftPanel(excelUtils.getValue("selectionOperationFromLeftPanel"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("requiredAppointment", "referralCalculation");
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String referralAmount = adminReferralReportPage.verifyReferralCalculationInReport(orderID, doctorName,
				referredPharmacy, referralCommission, grandTotalAmount, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "appointments");
		excelUtils.updateValueToExcel("requiredAppointment", "doctorAppointment");
		excelUtils.updateValueToExcel("requiredStatus", "allInDoctor");
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("notification", notification);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("actualReferralAmount", referralAmount);
			excelUtils.updateValueToExcel("expectedReferralAmount", referralAmount);
			excelUtils.updateValueToExcel("referralCommission", referralCommission);
			excelUtils.updateValueToExcel("exrNumber", exrNumber);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}

	}

	@Test(description = "verify whether admin is able to calculate referral_REFERRAL_SALAMTEK_To_PHARMACY", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_REFERRAL_SALAMTEK_To_PHARMACY(String testCaseID, String testCaseName,
			String description, String executionStatus, String username, String password, String operationName,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");

		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "settings");
		excelUtils.updateValueToExcel("requiredAppointment", "referrals");

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
		testResult = homePage.selectPharmacy(referredPharmacy, referredPharmacy);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineMainPage.selectProduct(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, "No");
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(referredPharmacy);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		String subTotalAmount = checkoutPage.getShopOnineSubTotalPrice();
		testResult = logoutPage.verifyLogout();

		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyAdminLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "settings");
		excelUtils.updateValueToExcel("requiredAppointment", "referrals");
		testResult = adminProfileHomePage
				.selectOperationFromLeftPanel(excelUtils.getValue("selectionOperationFromLeftPanel"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String referralCommission = adminReferralReportPage.verifyReferralCommissionForSalamtek(doctorName,
				referredPharmacy, referredLab, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("referrals");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("settings");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "reports");
		testResult = adminProfileHomePage
				.selectOperationFromLeftPanel(excelUtils.getValue("selectionOperationFromLeftPanel"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("requiredAppointment", "referralCalculation");
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String referralAmount = adminReferralReportPage.verifyReferralCalculationInReportForSalamtek(orderID,
				referredPharmacy, referralCommission, subTotalAmount, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "settings");
		excelUtils.updateValueToExcel("requiredAppointment", "referrals");

		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("grandTotalAmount", subTotalAmount);
			excelUtils.updateValueToExcel("actualReferralAmount", referralAmount);
			excelUtils.updateValueToExcel("expectedReferralAmount", referralAmount);
			excelUtils.updateValueToExcel("referralCommission", referralCommission);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}

	}

	@Test(description = "verify whether admin is able to calculate referral_REFERRAL_SALAMTEK_To_DOCTOR", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_REFERRAL_SALAMTEK_To_DOCTOR(String testCaseID, String testCaseName,
			String description, String executionStatus, String username, String password, String operationName,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");

		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "settings");
		excelUtils.updateValueToExcel("requiredAppointment", "referrals");

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
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
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
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		String grandTotalAmount = checkoutPage.getDoctorGrandTotalAmount(payOnlyBookingFee);
		testResult = logoutPage.verifyLogout();

		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyAdminLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "settings");
		excelUtils.updateValueToExcel("requiredAppointment", "referrals");
		testResult = adminProfileHomePage
				.selectOperationFromLeftPanel(excelUtils.getValue("selectionOperationFromLeftPanel"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		System.out.println("Doctor Name: " + doctorName.substring(3).trim());
		String referralCommission = adminReferralReportPage.verifyReferralCommissionForSalamtek(
				doctorName.substring(3).trim(), referredPharmacy, referredLab, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("referrals");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileHomePage.closeOpenedTabs("settings");
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "reports");
		testResult = adminProfileHomePage
				.selectOperationFromLeftPanel(excelUtils.getValue("selectionOperationFromLeftPanel"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("requiredAppointment", "referralCalculation");
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(excelUtils.getValue("requiredAppointment"));
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String referralAmount = adminReferralReportPage.verifyReferralCalculationInReportForSalamtek(bookingID,
				doctorName.substring(3).trim(), referralCommission, grandTotalAmount, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		excelUtils.updateValueToExcel("selectionOperationFromLeftPanel", "settings");
		excelUtils.updateValueToExcel("requiredAppointment", "referrals");

		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("actualReferralAmount", referralAmount);
			excelUtils.updateValueToExcel("expectedReferralAmount", referralAmount);
			excelUtils.updateValueToExcel("referralCommission", referralCommission);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}

	}

	@Test(description = "Verify whether Payment Received button is clickable and mark as complete or delivered  for Cash on Delivery Appointment in Dashboard", groups = {
			"AdminDoctorAppointment" }, dataProvider = "dataForAdminDoctorAppointment")
	public void AdminDoctorAppointment_CashReceived(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorSearchName,
			String doctorName, String serviceName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String descriptionEnglish, String descriptionArabic,
			String refundType, String notes, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String serviceType,
			String profileIndividualServiceSelection, String service, String referredLab, String recordType,
			String reportName, String permissionDuration, String permissionNeeded) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = homePage.selectDoctorFromLink(doctorSearchName, doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.bookDoctor(doctorName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectAndBookDoctorAppointment(serviceName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
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
		testResult = adminProfileLoginPage.verifyDoctorProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = doctorProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = doctorProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = doctorProfileHomePage.selectSuboperationOfLab(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = doctorProfileAppointmentPage.verifyAppointmentPresent(bookingID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		String cashReceivedOrNot = doctorProfileAppointmentPage.verifyCashReceivedAndComplete(bookingID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String notification = notificationListPage.verifyNotificationPresenceForComplete(bookingID, doctorName,
				testCaseID, sheetName);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("notification", notification);
			excelUtils.updateValueToExcel("cashReceivedOrNot", cashReceivedOrNot);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@DataProvider(name = "dataForAdminDoctorAppointment")
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
		Object[][] data = new Object[countIteration][ColNum - 16];
		for (int i1 = 1; i1 < lastRow; i1++) {
			String executionStatus1 = sheet.getRow(i1).getCell(3).getStringCellValue();
			String testCaseName1 = sheet.getRow(i1).getCell(1).getStringCellValue();
			if (executionStatus1.equalsIgnoreCase("Y") && testCaseName1.equalsIgnoreCase(m.getName())) {
				for (int j = 0; j < ColNum - 16; j++) {
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
