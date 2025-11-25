package Tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Common.BaseTest;
import Pages.BookAppointmentPage;
import Pages.CheckoutPage;
import Pages.DoctorListingPage;
import Pages.FindDoctorMainPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.MyAccountPage;
import Pages.NotificationListPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class FindADoctorTest extends BaseTest {
	Boolean testResult = false;
	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	FindDoctorMainPage findDoctorMainPage = new FindDoctorMainPage(getDriver());
	DoctorListingPage doctorListingPage = new DoctorListingPage(getDriver());
	BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(getDriver());
	CheckoutPage checkoutPage = new CheckoutPage(getDriver());
	LogoutPage logoutPage = new LogoutPage(getDriver());
	MyAccountPage myAccountPage = new MyAccountPage(getDriver());
	NotificationListPage notificationListPage = new NotificationListPage(getDriver());

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "FindADoctor";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "verify whether user is able to proceed appointment with a doctor", groups = {
			"FindADoctor" }, dataProvider = "dataForFindDoctor")
	public void BookingAppointment_Cash(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String symptom,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String filterType, String filterValue, String filterResult, String expectedMessage) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("consultationFee");
		excelUtils.updateField("refundAmount");
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

	@Test(description = "verify whether user is able to proceed appointment with a doctor", groups = {
			"FindADoctor" }, dataProvider = "dataForFindDoctor")
	public void BookingAppointment_Wallet(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String symptom,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String filterType, String filterValue, String filterResult, String expectedMessage) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("consultationFee");
		excelUtils.updateField("refundAmount");
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

	@Test(description = "verify whether user is able to proceed appointment with a doctor_VISA", groups = {
			"FindADoctor" }, dataProvider = "dataForFindDoctor")
	public void BookingAppointment_VISA(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String symptom,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String filterType, String filterValue, String filterResult, String expectedMessage) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("consultationFee");
		excelUtils.updateField("refundAmount");
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
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
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

	@Test(description = "verify whether user is able to proceed appointment with a doctor_KNET", groups = {
			"FindADoctor" }, dataProvider = "dataForFindDoctor")
	public void BookingAppointment_KNET(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String symptom,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String filterType, String filterValue, String filterResult, String expectedMessage) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("bookingID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");excelUtils.updateField("consultationFee");
		excelUtils.updateField("refundAmount");
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
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
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

	@Test(description = "verify whether user is able to proceed appointment with a doctor_CANCELLATION", groups = {
			"FindADoctor" }, dataProvider = "dataForFindDoctor")
	public void BookingAppointment_CANCELLATION(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String symptom,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String filterType, String filterValue, String filterResult, String expectedMessage) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("consultationFee");
		excelUtils.updateField("refundAmount");
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
		takeScreenShot(getDriver(), "BookingMedicalServiceSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		String consultationFee = checkoutPage.getDoctorConsultationFee();
		String firstWalletValue = myAccountPage.getFirstWalletValue();
		takeScreenShot(getDriver(), "BookedAppointmentInDashboard", testCaseID, sheetName);
		testResult = myAccountPage.verifyOrderCancellationFromDashboard_Lab_Doctor(bookingID);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "CancellationMainPage", testCaseID, sheetName);
		testResult = myAccountPage.verifyOrderCancellationMain_Lab_Doctor(payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "CancellationDetailPage", testCaseID, sheetName);
		String refundAmount = myAccountPage.getRefundAmountValue(payOnlyBookingFee);
		String valueAfterCancellation = myAccountPage.getWalletUpdatedValue();
		testResult = myAccountPage.verifyTotalWalletAmountAfterRefund_Lab_Doctor();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("consultationFee", consultationFee);
			excelUtils.updateValueToExcel("refundAmount", refundAmount);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("valueAfterCancellation", valueAfterCancellation);
			excelUtils.updateValueToExcel("bookingID", bookingID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "verify whether user is able to proceed appointment with a doctor_VISA_CANCELLATION", groups = {
			"FindADoctor" }, dataProvider = "dataForFindDoctor")
	public void BookingAppointment_VISA_CANCELLATION(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String symptom,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String filterType, String filterValue, String filterResult, String expectedMessage) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("consultationFee");
		excelUtils.updateField("refundAmount");
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
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "BookingMedicalServiceSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		String consultationFee = checkoutPage.getDoctorConsultationFee();
		String firstWalletValue = myAccountPage.getFirstWalletValue();
		takeScreenShot(getDriver(), "BookedAppointmentInDashboard", testCaseID, sheetName);
		testResult = myAccountPage.verifyOrderCancellationFromDashboard_Lab_Doctor(bookingID);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "CancellationMainPage", testCaseID, sheetName);
		testResult = myAccountPage.verifyOrderCancellationMain_Lab_Doctor(payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "CancellationDetailPage", testCaseID, sheetName);
		String refundAmount = myAccountPage.getRefundAmountValue(payOnlyBookingFee);
		String valueAfterCancellation = myAccountPage.getWalletUpdatedValue();
		testResult = myAccountPage.verifyTotalWalletAmountAfterRefund_Lab_Doctor();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("consultationFee", consultationFee);
			excelUtils.updateValueToExcel("refundAmount", refundAmount);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("valueAfterCancellation", valueAfterCancellation);
			excelUtils.updateValueToExcel("bookingID", bookingID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "verify whether user is able to proceed appointment with a doctor_KNET_CANCELLATION", groups = {
			"FindADoctor" }, dataProvider = "dataForFindDoctor")
	public void BookingAppointment_KNET_CANCELLATION(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String symptom,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String filterType, String filterValue, String filterResult, String expectedMessage) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("consultationFee");
		excelUtils.updateField("refundAmount");
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
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "BookingMedicalServiceSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		String consultationFee = checkoutPage.getDoctorConsultationFee();
		String firstWalletValue = myAccountPage.getFirstWalletValue();
		takeScreenShot(getDriver(), "BookedAppointmentInDashboard", testCaseID, sheetName);
		testResult = myAccountPage.verifyOrderCancellationFromDashboard_Lab_Doctor(bookingID);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "CancellationMainPage", testCaseID, sheetName);
		testResult = myAccountPage.verifyOrderCancellationMain_Lab_Doctor(payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "CancellationDetailPage", testCaseID, sheetName);
		String refundAmount = myAccountPage.getRefundAmountValue(payOnlyBookingFee);
		String valueAfterCancellation = myAccountPage.getWalletUpdatedValue();
		testResult = myAccountPage.verifyTotalWalletAmountAfterRefund_Lab_Doctor();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("consultationFee", consultationFee);
			excelUtils.updateValueToExcel("refundAmount", refundAmount);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("valueAfterCancellation", valueAfterCancellation);
			excelUtils.updateValueToExcel("bookingID", bookingID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "Verify whether the user is able to reschedule a doctor appointment after paying with Cash", groups = {
			"FindADoctor" }, dataProvider = "dataForFindDoctor")
	public void BookingAppointment_Reschedule(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String symptom,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String filterType, String filterValue, String filterResult, String expectedMessage) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("consultationFee");
		excelUtils.updateField("refundAmount");
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
			assertEquals(testResult, true);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		checkoutPage.verifyReschedule();
		String notification = notificationListPage.verifyNotificationPresenceForPatientReschedule(bookingID, doctorName,
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);

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

	@Test(description = "verify whether user is able to proceed appointment with a doctor", groups = {
			"FindADoctor" }, dataProvider = "dataForFindDoctor")
	public void BookingAppointment_FromSymptom(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String symptom,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String filterType, String filterValue, String filterResult, String expectedMessage) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("consultationFee");
		excelUtils.updateField("refundAmount");
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
		testResult = homePage.selectDoctorFromNeedMoreHelp_Symptom(symptom, testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = doctorListingPage.selectDoctorFromDoctorListing(doctorName, testCaseID, sheetName);
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
			assertEquals(testResult, true);

		}
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
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

	@Test(description = "Verify whether the user is able to filter Doctors", groups = {
			"FindADoctor" }, dataProvider = "dataForFindDoctor")
	public void FindDoctor_FilterFromMainPage(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String symptom,
			String doctorSearchName, String doctorName, String serviceName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String filterType, String filterValue, String filterResult, String expectedMessage) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("consultationFee");
		excelUtils.updateField("refundAmount");
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
		testResult = findDoctorMainPage.selectShowAllDoctorsList();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = doctorListingPage.filterDoctors(filterType, filterValue, filterResult, testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@DataProvider(name = "dataForFindDoctor")
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
		Object[][] data = new Object[countIteration][ColNum - 8];
		for (int i1 = 1; i1 < lastRow; i1++) {
			String executionStatus1 = sheet.getRow(i1).getCell(3).getStringCellValue();
			String testCaseName1 = sheet.getRow(i1).getCell(1).getStringCellValue();
			if (executionStatus1.equalsIgnoreCase("Y") && testCaseName1.equalsIgnoreCase(m.getName())) {
				for (int j = 0; j < ColNum - 8; j++) {
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
