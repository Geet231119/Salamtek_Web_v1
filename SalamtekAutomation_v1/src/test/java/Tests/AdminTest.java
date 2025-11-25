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
import Pages.AdminDoctorAppointmentPage;
import Pages.AdminOrderPage;
import Pages.AdminProfileAccountStatementPage;
import Pages.AdminProfileHomePage;
import Pages.AdminProfileLabAppointmentPage;
import Pages.AdminProfileLoginPage;
import Pages.AdminProfileLogoutPage;
import Pages.AdminProfilePromotionPage;
import Pages.AdminProfileUserCustomerPage;
import Pages.AdminProfileVoucherPage;
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

public class AdminTest extends BaseTest {
	Boolean testResult = false;
	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(getDriver());
	CheckoutPage checkoutPage = new CheckoutPage(getDriver());
	LogoutPage logoutPage = new LogoutPage(getDriver());
	MyAccountPage myAccountPage = new MyAccountPage(getDriver());
	AdminProfileLoginPage adminProfileLoginPage = new AdminProfileLoginPage(getDriver());
	AdminProfileHomePage adminProfileHomePage = new AdminProfileHomePage(getDriver());
	AdminProfileAccountStatementPage adminProfileAccountStatementPage = new AdminProfileAccountStatementPage(
			getDriver());
	AdminProfileUserCustomerPage adminProfileUserCustomerPage = new AdminProfileUserCustomerPage(getDriver());
	AdminProfilePromotionPage adminProfilePromotionPage = new AdminProfilePromotionPage(getDriver());
	AdminProfileVoucherPage adminProfileVoucherPage = new AdminProfileVoucherPage(getDriver());
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
	String sheetName = "Admin";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "Verify whether 3 new reports are available for Salamtek Report section", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_Report_AccountStatement(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		testResult = adminProfileAccountStatementPage.verifyAllReports(testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether the user is able to apply a promo code to a specific service of a doctor in backend", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_BookWithPromotion_Doctor(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForDoctor(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, doctorName, serviceName, testCaseID,
				sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(dev_url);
			}
		}
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
		String actualDiscount = checkoutPage.verifyPromotionForDoctor(promoCode, discountType, discount, testCaseID,
				sheetName);
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
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
		String bookingID = checkoutPage.getBookingID();
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("actualDiscount", actualDiscount);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether the user is able to create a promocode for a service from a specific doctor and add either a percentage or a flat rate to it.", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_CreatePromotion_Doctor(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForDoctor(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, doctorName, serviceName, testCaseID,
				sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		driver.navigate().refresh();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether the user is able to apply a promo code to a specific category or a service of medical service provider", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_BookWithPromotion_Lab(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForLab(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, medicalServiceName,
				profileIndividualTestSelection, profileIndividualTestSelection1, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(dev_url);
			}
		}
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
		testResult = bookAppointmentPage.selectProfileService(serviceName, profileIndividualServiceSelection,
				profileIndividualTestSelection);
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
		String actualDiscount = checkoutPage.verifyPromotionForLab(promoCode, discount, testCaseID, sheetName);
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
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("actualDiscount", actualDiscount);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether the user is able to apply a promo code to a specific category or a service of medical service provider", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_CreatePromotion_Lab(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForLab(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, medicalServiceName,
				profileIndividualTestSelection, profileIndividualTestSelection1, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		driver.navigate().refresh();
		waitSometime();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether admin is able to create a promocode for a product, brand and category from a specific store and add either a percentage or a flat rate to it", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_CreatePromotion_Pharmacy(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForPharmacy(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, pharmacy, selectedProduct,
				applyOnOffer, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		driver.navigate().refresh();
		waitSometime();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether the user is able to apply a promo to a specific product of a specific store", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_OrderWithPromotion_Pharmacy(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForPharmacy(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, pharmacy, selectedProduct,
				applyOnOffer, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(dev_url);
			}
		}
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
		testResult = homePage.selectPharmacy(pharmacy, pharmacy);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineMainPage.selectProduct(selectedProduct);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineDetailsPage.addProductToCart(selectedProduct, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacy);
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
		String actualDiscount = checkoutPage.verifyPromotionForPharmacy(promoCode, discount, testCaseID, sheetName);
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
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("actualDiscount", actualDiscount);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether user is able to see the original price plus the price after discount in the confirmation page when applying a promo code for an offer lab service after purchasing it", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_OriginalAndDiscountAmount_Lab(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForLab(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, medicalServiceName,
				profileIndividualTestSelection, profileIndividualTestSelection1, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(dev_url);
			}
		}
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
		testResult = bookAppointmentPage.selectProfileService(serviceName, profileIndividualServiceSelection,
				profileIndividualTestSelection);
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
		String actualDiscount = checkoutPage.verifyPromotionForLab(promoCode, discount, testCaseID, sheetName);
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
		String amounts = checkoutPage.verifyOriginalAndDiscountPrice_Lab(actualDiscount, testCaseID, sheetName);
		String[] price = amounts.split(" ");
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("actualDiscount", actualDiscount);
			excelUtils.updateValueToExcel("actualOriginalAmount", price[0]);
			excelUtils.updateValueToExcel("actualDiscountedAmount", price[1]);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whther user can to see the original price plus the price after discount in the confirmation page when applying a promo code for a multi-services from a specific lab and purchase them", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_OriginalAndDiscountAmount_Lab_MultiService(String testCaseID, String testCaseName,
			String description, String executionStatus, String username, String password, String operationName,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String adminURL, String adminUsername,
			String adminPassword, String selectionOperationFromLeftPanel, String requiredAppointment,
			String requiredStatus, String titleEnglish, String titleArabic, String code, String userPerCoupon,
			String numberOfCoupons, String discountType, String discount, String promoFor, String doctorSearchName,
			String doctorName, String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForLab(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, medicalServiceName,
				profileIndividualTestSelection, profileIndividualTestSelection1, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(dev_url);
			}
		}
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
		testResult = bookAppointmentPage.selectProfileService(serviceName, profileIndividualServiceSelection,
				profileIndividualTestSelection);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = bookAppointmentPage.selectProfileService(serviceName, profileIndividualServiceSelection,
				profileIndividualTestSelection1);
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
		String actualDiscount = checkoutPage.verifyPromotionForLab(promoCode, discount, testCaseID, sheetName);
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
		String amounts = checkoutPage.verifyOriginalAndDiscountPrice_Lab(actualDiscount, testCaseID, sheetName);
		String[] price = amounts.split(" ");
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("actualDiscount", actualDiscount);
			excelUtils.updateValueToExcel("actualOriginalAmount", price[0]);
			excelUtils.updateValueToExcel("actualDiscountedAmount", price[1]);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether the user is able to apply a promo to a specific product of a specific store", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_OriginalAndDiscountAmount_Pharmacy(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForPharmacy(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, pharmacy, selectedProduct,
				applyOnOffer, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(dev_url);
			}
		}
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
		testResult = homePage.selectPharmacy(pharmacy, pharmacy);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineMainPage.selectProduct(selectedProduct);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineDetailsPage.addProductToCart(selectedProduct, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacy);
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
		String actualDiscount = checkoutPage.verifyPromotionForPharmacy(promoCode, discount, testCaseID, sheetName);
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
		takeScreenShot(getDriver(), "BookingDoctorSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		String amounts = checkoutPage.verifyOriginalAndDiscountPrice_Pharmacy(actualDiscount, testCaseID, sheetName);
		String[] price = amounts.split(" ");
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("actualDiscount", actualDiscount);
			excelUtils.updateValueToExcel("actualOriginalAmount", price[0]);
			excelUtils.updateValueToExcel("actualDiscountedAmount", price[1]);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether admin is able to add money to customer's wallet from dashboard", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_AddWalletMoney(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String operationName, String paymentMode, String payOnlyBookingFee,
			String bankName, String firstName, String lastName, String address, String city, String country,
			String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV,
			String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String amount = adminProfileUserCustomerPage.verifyAddingWalletMoney(username, walletRechargeAmount, testCaseID,
				sheetName);
		String firstWalletValue = amount.split(" ")[0];
		String lastWalletValue = amount.split(" ")[1];
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("lastWalletValue", lastWalletValue);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether user is able to cancel the lab service which was applied by a promo code and refund just the amount after dicount", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_Promo_Cancel_Lab(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForLab(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, medicalServiceName,
				profileIndividualTestSelection, profileIndividualTestSelection1, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(dev_url);
			}
		}
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
		testResult = bookAppointmentPage.selectProfileService(serviceName, profileIndividualServiceSelection,
				profileIndividualTestSelection);
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
		String actualDiscount = checkoutPage.verifyPromotionForLab(promoCode, discount, testCaseID, sheetName);
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
		String amounts = checkoutPage.verifyOriginalAndDiscountPrice_Lab(actualDiscount, testCaseID, sheetName);
		String[] price = amounts.split(" ");
		String firstWalletValue = myAccountPage.getFirstWalletValue();
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
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		if (paymentMode.equalsIgnoreCase("KNET")) {
			double amt = Double.parseDouble(price[1]) - 0.200;
			String refundKNET = Double.toString(amt);
			if (refundAmount.equalsIgnoreCase(refundKNET)) {
				excelUtils.updateResult(testResult);
				excelUtils.updateValueToExcel("bookingID", bookingID);
				excelUtils.updateValueToExcel("actualDiscount", actualDiscount);
				excelUtils.updateValueToExcel("actualOriginalAmount", price[0]);
				excelUtils.updateValueToExcel("actualDiscountedAmount", price[1]);
				excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
				excelUtils.updateValueToExcel("refundAmount", refundKNET);
				excelUtils.updateValueToExcel("lastWalletValue", valueAfterCancellation);
			}
		} else if (!paymentMode.equalsIgnoreCase("KNET")) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("bookingID", bookingID);
			excelUtils.updateValueToExcel("actualDiscount", actualDiscount);
			excelUtils.updateValueToExcel("actualOriginalAmount", price[0]);
			excelUtils.updateValueToExcel("actualDiscountedAmount", price[1]);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("refundAmount", refundAmount);
			excelUtils.updateValueToExcel("lastWalletValue", valueAfterCancellation);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(false);
		}
	}

	@Test(description = "Verify whether user is able to apply a promo code on a specific service from a specific lab and check that when adding other services from the same lab that are not on discount", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_PromoNotApplied_Lab(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForLab(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, medicalServiceName,
				profileIndividualTestSelection, profileIndividualTestSelection1, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(dev_url);
			}
		}
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
		testResult = bookAppointmentPage.selectProfileService(serviceName, profileIndividualServiceSelection,
				"Pregnancy Test");
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
		testResult = checkoutPage.verifyPromoNotApplied_lab(promoCode, expectedError, testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("actualError", expectedError);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether admin is able to create promo for Brand of specific pharmacies - Percentage discount and Flat discount", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_CreatePromotion_Brand(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForBrand(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, brand, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		driver.navigate().refresh();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether the user is able to apply a promo to a specific product of a specific store", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_OrderWithPromotion_Brand(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String promoCode = adminProfilePromotionPage.verifyCreatePromotionForBrand(code, titleEnglish, titleArabic,
				userPerCoupon, numberOfCoupons, discountType, discount, promoFor, brand, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedPromoCode", promoCode);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(dev_url);
			}
		}
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
		testResult = homePage.selectPharmacy(pharmacy, pharmacy);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineMainPage.filterByBrand(brand, testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineMainPage.selectProduct(selectedProduct);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineDetailsPage.addProductToCart(selectedProduct, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacy);
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
		String actualDiscount = checkoutPage.verifyPromotionForPharmacy(promoCode, discount, testCaseID, sheetName);
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
		takeScreenShot(getDriver(), "OrderSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		testResult = adminProfilePromotionPage.deleteCreatedPromotion(promoCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("actualDiscount", actualDiscount);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify create a voucher and redeem from salamtek", groups = {
			"Admin" }, dataProvider = "dataForAdmin")
	public void Admin_CreateVoucher(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String operationName, String paymentMode, String payOnlyBookingFee,
			String bankName, String firstName, String lastName, String address, String city, String country,
			String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV,
			String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment, String requiredStatus,
			String titleEnglish, String titleArabic, String code, String userPerCoupon, String numberOfCoupons,
			String discountType, String discount, String promoFor, String doctorSearchName, String doctorName,
			String serviceName, String medicalServiceSearchName, String medicalServiceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String profileIndividualTestSelection1, String pharmacy, String selectedProduct, String brand,
			String colorPresent, String uploadNeeded, String walletRechargeAmount, String expectedError,
			String voucherAmount, String validity, String applyOnOffer) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualOriginalAmount");
		excelUtils.updateField("actualDiscountedAmount");
		excelUtils.updateField("actualDiscount");
		excelUtils.updateField("expectedPromoCode");
		excelUtils.updateField("expectedVoucherCode");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("lastWalletValue");
		excelUtils.updateField("refundAmount");
		excelUtils.updateField("actualError");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		driver.get(adminURL);
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
		String voucherCode = adminProfileVoucherPage.verifyCreateVoucher(titleEnglish, titleArabic, voucherAmount,
				validity, testCaseID, sheetName);
		excelUtils.updateValueToExcel("expectedVoucherCode", voucherCode);
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(dev_url);
			}
		}
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
		String firstWalletValue = myAccountPage.getFirstWalletValue();
		testResult = myAccountPage.verifyRedeemVoucher(voucherCode, testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		String lastWalletValue = myAccountPage.getWalletValue();
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		testResult = adminProfileVoucherPage.deleteCreatedVoucher(voucherCode);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("lastWalletValue", lastWalletValue);
		} else {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@DataProvider(name = "dataForAdmin")
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
		Object[][] data = new Object[countIteration][ColNum - 13];
		for (int i1 = 1; i1 < lastRow; i1++) {
			String executionStatus1 = sheet.getRow(i1).getCell(3).getStringCellValue();
			String testCaseName1 = sheet.getRow(i1).getCell(1).getStringCellValue();
			if (executionStatus1.equalsIgnoreCase("Y") && testCaseName1.equalsIgnoreCase(m.getName())) {
				for (int j = 0; j < ColNum - 13; j++) {
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
