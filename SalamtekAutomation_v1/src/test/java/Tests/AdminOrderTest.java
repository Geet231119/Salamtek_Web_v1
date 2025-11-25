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
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Common.BaseTest;
import Pages.AdminDoctorAppointmentPage;
import Pages.AdminOrderPage;
import Pages.AdminProfileHomePage;
import Pages.AdminProfileLabAppointmentPage;
import Pages.AdminProfileLoginPage;
import Pages.AdminProfileLogoutPage;
import Pages.CartDetailsPage;
import Pages.CartMainPage;
import Pages.CheckoutPage;
import Pages.DoctorProfileAppointmentPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.MyAccountPage;
import Pages.NotificationListPage;
import Pages.ShopOnlineDetailsPage;
import Pages.ShopOnlineMainPage;
import Pages.StoreProfileHomePage;
import Pages.StoreProfileOrdersPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class AdminOrderTest extends BaseTest {
	Boolean testResult = false;
	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	ShopOnlineMainPage shopOnlineMainPage = new ShopOnlineMainPage(getDriver());
	ShopOnlineDetailsPage shopOnlineDetailsPage = new ShopOnlineDetailsPage(getDriver());
	CartMainPage cartMainPage = new CartMainPage(getDriver());
	CartDetailsPage cartDetailsPage = new CartDetailsPage(driver);
	CheckoutPage checkoutPage = new CheckoutPage(getDriver());
	LogoutPage logoutPage = new LogoutPage(getDriver());
	MyAccountPage myAccountPage = new MyAccountPage(getDriver());
	AdminProfileLoginPage adminProfileLoginPage = new AdminProfileLoginPage(getDriver());
	AdminProfileHomePage adminProfileHomePage = new AdminProfileHomePage(getDriver());
	StoreProfileHomePage storeProfileHomePage = new StoreProfileHomePage(getDriver());
	StoreProfileOrdersPage storeProfileOrdersPage = new StoreProfileOrdersPage(getDriver());
	AdminProfileLabAppointmentPage adminProfileLabAppointmentPage = new AdminProfileLabAppointmentPage(getDriver());
	AdminDoctorAppointmentPage adminDoctorAppointmentPage = new AdminDoctorAppointmentPage(getDriver());
	DoctorProfileAppointmentPage doctorProfileAppointmentPage = new DoctorProfileAppointmentPage(getDriver());
	AdminOrderPage adminOrderPage = new AdminOrderPage(getDriver());
	AdminProfileLogoutPage adminProfileLogoutPage = new AdminProfileLogoutPage(getDriver());
	NotificationListPage notificationListPage = new NotificationListPage(getDriver());

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "AdminOrder";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "verify whether store admin is able to change the statuses from Accept to Ready For Customer Pickup", groups = {
			"AdminOrder" }, dataProvider = "dataForAdminOrder")
	public void AdminOrder_StatusChange(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String colorPresent, String uploadNeeded, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredStatus, String orderOfStatusChange,
			String refundType, String productToBeAdded, String promoCode, String discount) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		System.out.println("entered");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = homePage.selectOperation(operationName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = shopOnlineMainPage.selectProduct(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = checkoutPage.selectPaymentAndProceed(paymentMode, payOnlyBookingFee);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyStoreProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = storeProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = storeProfileHomePage.selectSuboperationOfOrder(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = storeProfileOrdersPage.verifyOrderPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		String[] status = orderOfStatusChange.split(",");
		for (int i = 0; i < status.length; i++) {
			System.out.println("Status " + i + status[i]);
			testResult = storeProfileOrdersPage.verifyOrderStatusChange(status[i], testCaseID, sheetName);
			if (testResult.equals(false)) {
				adminProfileLogoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				Assert.assertEquals(testResult, true);
			}
			driver.switchTo().window(currentHandle);
			driver.navigate().refresh();
			waitSometime();
			String notification = notificationListPage.verifyNotificationPresenceForStatusChange(orderID, status[i],
					testCaseID, sheetName);
			excelUtils.updateValueToExcel("notification_" + i, notification);
			for (String actual : handles) {
				if (!actual.equalsIgnoreCase(currentHandle)) {
					driver.switchTo().window(actual);
				}
			}
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
	}

	@Test(description = "verify whether store admin is able to approve insurance and user is able to proceed purchase after approval", groups = {
			"AdminOrder" }, dataProvider = "dataForAdminOrder")
	public void AdminOrder_Insurance(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String operationName, String pharmacySearchName, String pharmacyName,
			String productName, String colorPresent, String uploadNeeded, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredStatus, String orderOfStatusChange,
			String refundType, String productToBeAdded, String promoCode, String discount) throws Exception {
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineMainPage.selectProduct(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.enterInsuranceDetailsForOrder(enableInsurance, insuranceCardNumber);
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyStoreProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectSuboperationOfOrder(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyOrderPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyApproveOrRejectInsurance(coveredByInsuranceAmount, approveOrReject);
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
		String notification = notificationListPage.verifyNotificationPresenceForInsurance_Order(approveOrReject,
				orderID, testCaseID, sheetName);
		if (approveOrReject.equalsIgnoreCase("Approve")) {
			testResult = cartDetailsPage.verifyInsuranceDeduction(coveredByInsuranceAmount);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);

			}
		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
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
		orderID = checkoutPage.getBookingID();
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("notification_0", notification);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether medical service provider / pharmacy (vendor) is able to review the insurance and civil id uploaded files and claim provided from the user after booking a medical service / purchasing a product ", groups = {
			"AdminOrder" }, dataProvider = "dataForAdminOrder")
	public void AdminOrder_Insurance_DocumentsVerification(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String colorPresent, String uploadNeeded, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredStatus, String orderOfStatusChange,
			String refundType, String productToBeAdded, String promoCode, String discount) throws Exception {
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineMainPage.selectProduct(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = checkoutPage.enterInsuranceDetailsForOrder(enableInsurance, insuranceCardNumber);
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyStoreProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectSuboperationOfOrder(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyOrderPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyClaim_Insurance_CivilID(orderID, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}

		testResult = storeProfileOrdersPage.verifyApproveOrRejectInsurance(coveredByInsuranceAmount, approveOrReject);
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
		String notification = notificationListPage.verifyNotificationPresenceForInsurance_Order(approveOrReject,
				orderID, testCaseID, sheetName);
		if (approveOrReject.equalsIgnoreCase("Approve")) {
			testResult = cartDetailsPage.verifyInsuranceDeduction(coveredByInsuranceAmount);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);

			}
		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
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
		orderID = checkoutPage.getBookingID();
		testResult = logoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("notification_0", notification);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "verify whether store admin is able to change the statuses from Accept to Ready For Customer Pickup", groups = {
			"AdminOrder" }, dataProvider = "dataForAdminOrder")
	public void AdminOrder_Cancel(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String operationName, String pharmacySearchName, String pharmacyName,
			String productName, String colorPresent, String uploadNeeded, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredStatus, String orderOfStatusChange,
			String refundType, String productToBeAdded, String promoCode, String discount) throws Exception {
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineMainPage.selectProduct(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
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
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyStoreProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectSuboperationOfOrder(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyOrderPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String expectedRefundAmount = storeProfileOrdersPage.verifyCancelOrder(orderOfStatusChange, refundType,
				testCaseID, sheetName);
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		waitSometime();
		String notification = notificationListPage.verifyNotificationPresenceForStatusChange(orderID,
				orderOfStatusChange, testCaseID, sheetName);
		excelUtils.updateValueToExcel("notification_0", notification);
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
			}
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String commentInWallet = myAccountPage.verifyRefundAfterAdminCancellation(orderID, expectedRefundAmount,
				pharmacyName, "MyWallet", testCaseID, sheetName);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("grandTotalAmount", expectedRefundAmount.substring(3).trim());
			excelUtils.updateValueToExcel("walletComment", commentInWallet);
			excelUtils.updateValueToExcel("actualRefundAmount", expectedRefundAmount.substring(3).trim());
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether the user is able to add items to an existing order", groups = {
			"AdminOrder" }, dataProvider = "dataForAdminOrder")
	public void AdminOrder_AddItem(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String operationName, String pharmacySearchName, String pharmacyName,
			String productName, String colorPresent, String uploadNeeded, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredStatus, String orderOfStatusChange,
			String refundType, String productToBeAdded, String promoCode, String discount) throws Exception {
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineMainPage.selectProduct(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
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
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyStoreProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectSuboperationOfOrder(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyOrderPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String newOrderID = storeProfileOrdersPage.verifyAddItemToOrder(productToBeAdded, testCaseID, sheetName);
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		waitSometime();
		String notification = notificationListPage.verifyNotificationPresenceForAddItems(newOrderID, pharmacyName,
				orderOfStatusChange, testCaseID, sheetName);
		excelUtils.updateValueToExcel("notification_0", notification);
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
			}
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
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("newOrderID", newOrderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether user is able to cancel the (product or category or brand) which was applied by a promo code and refund just the amount after discount", groups = {
			"AdminOrder" }, dataProvider = "dataForAdminOrder")
	public void AdminOrder_Cancel_Promotion(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String colorPresent, String uploadNeeded, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredStatus, String orderOfStatusChange,
			String refundType, String productToBeAdded, String promoCode, String discount) throws Exception {
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineMainPage.selectProduct(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String actualDiscount = checkoutPage.verifyPromotionForPharmacy(promoCode, discount, testCaseID, sheetName);
		String totalAmountAfterDiscount = checkoutPage.getTotalAmountAfterDiscount();
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
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyStoreProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectSuboperationOfOrder(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyOrderPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		String expectedRefundAmount = storeProfileOrdersPage.verifyCancelOrder(orderOfStatusChange, refundType,
				testCaseID, sheetName);
		driver.switchTo().window(currentHandle);
		driver.navigate().refresh();
		waitSometime();
		String notification = notificationListPage.verifyNotificationPresenceForStatusChange(orderID,
				orderOfStatusChange, testCaseID, sheetName);
		excelUtils.updateValueToExcel("notification_0", notification);
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
			}
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		driver.close();
		driver.switchTo().window(currentHandle);
		String commentInWallet = myAccountPage.verifyRefundAfterAdminCancellation(orderID, expectedRefundAmount,
				pharmacyName, "MyWallet", testCaseID, sheetName);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("grandTotalAmount", expectedRefundAmount.substring(3).trim());
			excelUtils.updateValueToExcel("walletComment", commentInWallet);
			excelUtils.updateValueToExcel("actualRefundAmount", expectedRefundAmount.substring(3).trim());
			excelUtils.updateValueToExcel("actualDiscount", actualDiscount);
			excelUtils.updateValueToExcel("totalAmountAfterDiscount", totalAmountAfterDiscount);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether Payment Received button is clickable and mark as complete or delivered  for Cash on Delivery Orders in Dashboard", groups = {
			"AdminOrder" }, dataProvider = "dataForAdminOrder")
	public void AdminOrder_CashReceived(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String colorPresent, String uploadNeeded, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredStatus, String orderOfStatusChange,
			String refundType, String productToBeAdded, String promoCode, String discount) throws Exception {
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineMainPage.selectProduct(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
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
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyStoreProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectSuboperationOfOrder(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyOrderPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyOrderStatusChange(orderOfStatusChange, testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		String cashReceivedOrNot = storeProfileOrdersPage.verifyCashReceivedAndComplete(testCaseID, sheetName);
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
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("cashReceivedOrNot", cashReceivedOrNot);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether the user is able to view Payment Information section in Order detail page in backend with details are specified", groups = {
			"AdminOrder" }, dataProvider = "dataForAdminOrder")
	public void AdminOrder_PaymentInfo(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String colorPresent, String uploadNeeded, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredStatus, String orderOfStatusChange,
			String refundType, String productToBeAdded, String promoCode, String discount) throws Exception {
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineMainPage.selectProduct(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
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
		String deliveryFee = checkoutPage.getShopOnineDeliveryFee();
		String subTotal = checkoutPage.getShopOnineSubTotalPrice();
		String grandTotal = checkoutPage.getShopOnineTotalPrice();
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyStoreProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectSuboperationOfOrder(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyOrderPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyPaymentInfo(paymentMode, subTotal, deliveryFee, grandTotal,
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("paymentMethod", "K-Net");
			excelUtils.updateValueToExcel("currency", "KWD");
			excelUtils.updateValueToExcel("orderAmount", "KWD "+subTotal);
			excelUtils.updateValueToExcel("subTotal", "KWD "+subTotal);
			excelUtils.updateValueToExcel("deliveryFee", "KWD "+deliveryFee);
			excelUtils.updateValueToExcel("paidByAmount", "KWD "+grandTotal);
			excelUtils.updateValueToExcel("grandTotal", "KWD "+grandTotal);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@Test(description = "Verify whether the user is able to view Order Summary section in Order detail page in backend with details are specified", groups = {
			"AdminOrder" }, dataProvider = "dataForAdminOrder")
	public void AdminOrder_OrderSummary(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String colorPresent, String uploadNeeded, String enableInsurance,
			String insuranceCardNumber, String coveredByInsuranceAmount, String approveOrReject, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredStatus, String orderOfStatusChange,
			String refundType, String productToBeAdded, String promoCode, String discount) throws Exception {
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineMainPage.selectProduct(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = cartDetailsPage.verifyProceedCheckout(uploadNeeded);
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
		String deliveryFee = checkoutPage.getShopOnineDeliveryFee();
		String subTotal = checkoutPage.getShopOnineSubTotalPrice();
		String grandTotal = checkoutPage.getShopOnineTotalPrice();
		String currentHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
				driver.get(adminURL);
			}
		}
		testResult = adminProfileLoginPage.verifyStoreProfileLogin(adminUsername, adminPassword);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileHomePage.selectSuboperationOfOrder(requiredStatus);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyOrderPresent(orderID);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);

		}
		testResult = storeProfileOrdersPage.verifyOrderSummary(paymentMode, subTotal, deliveryFee, grandTotal,
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		testResult = adminProfileLogoutPage.verifyLogout();
		if (testResult.equals(false)) {
			adminProfileLogoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			Assert.assertEquals(testResult, true);
		}
		driver.close();
		driver.switchTo().window(currentHandle);
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
			excelUtils.updateValueToExcel("paymentMethod", "K-Net");
			excelUtils.updateValueToExcel("orderAmount", "KWD "+subTotal);
			excelUtils.updateValueToExcel("subTotal", "KWD "+subTotal);
			excelUtils.updateValueToExcel("deliveryFee", "KWD "+deliveryFee);
			excelUtils.updateValueToExcel("paidByAmount", "KWD "+grandTotal);
			excelUtils.updateValueToExcel("grandTotal", "KWD "+grandTotal);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	@DataProvider(name = "dataForAdminOrder")
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
		Object[][] data = new Object[countIteration][ColNum - 22];
		for (int i1 = 1; i1 < lastRow; i1++) {
			String executionStatus1 = sheet.getRow(i1).getCell(3).getStringCellValue();
			String testCaseName1 = sheet.getRow(i1).getCell(1).getStringCellValue();
			if (executionStatus1.equalsIgnoreCase("Y") && testCaseName1.equalsIgnoreCase(m.getName())) {
				for (int j = 0; j < ColNum - 22; j++) {
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
