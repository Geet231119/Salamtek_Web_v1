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
import Pages.CartDetailsPage;
import Pages.CartMainPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.MyAccountPage;
import Pages.ShopOnlineDetailsPage;
import Pages.ShopOnlineMainPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class ShopOnlineTest extends BaseTest {
	Boolean testResult = false;
	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	ShopOnlineMainPage shopOnlineMainPage = new ShopOnlineMainPage(getDriver());
	ShopOnlineDetailsPage shopOnlineDetailsPage = new ShopOnlineDetailsPage(getDriver());
	CartMainPage cartMainPage = new CartMainPage(getDriver());
	CartDetailsPage cartDetailsPage = new CartDetailsPage(getDriver());
	CheckoutPage checkoutPage = new CheckoutPage(getDriver());
	LogoutPage logoutPage = new LogoutPage(getDriver());
	MyAccountPage myAccountPage = new MyAccountPage(getDriver());

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "ShopOnline";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "verify whether user is able to proceed purchasing a pharmacy product", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_Pharmacy_CASH(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
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
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
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
		// String grandTotalAmount = checkoutPage.getShopOnineTotalPrice();
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "verify whether user is able to proceed purchasing a pharmacy product", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_Pharmacy_WALLET(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
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
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
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
		// String grandTotalAmount = checkoutPage.getShopOnineTotalPrice();
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "verify whether user is able to proceed purchasing a pharmacy product_VISA", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_Pharmacy_VISA(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
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
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
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
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "verify whether user is able to proceed purchasing a pharmacy product_KNET", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_Pharmacy_KNET(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
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
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
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
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "verify whether user is able to proceed purchasing a pharmacy product_VISA_Cancellation", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_Pharmacy_VISA_Cancellation(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
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
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
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
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		String firstWalletValue = myAccountPage.getFirstWalletValue();
		testResult = myAccountPage.verifyOrderCancellationFromOrder_ShopOnline(orderID, "OrderInMyOrderPage",
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "OrderInCancellationMainPage", testCaseID, sheetName);
		testResult = myAccountPage.verifyOrderCancellationMain_ShopOnline(paymentMode);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "OrderInCancellationSuccessPage", testCaseID, sheetName);
		testResult = myAccountPage.verifyOrderCancellationSuccess_ShopOnline();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		String expectedRefundAmount = myAccountPage.getRefundAmountAfterDeduction();
		String grandTotalAmount = myAccountPage.getTotalPriceOfOrder();
		String actualRefundAmount = myAccountPage.verifyRefundAmount(orderID);
		String valueAfterCancellation = myAccountPage.getWalletUpdatedValue();
		testResult = myAccountPage.verifyTotalWalletAmountAfterRefund_ShopOnline_MS();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("expectedRefundAmount", expectedRefundAmount);
			excelUtils.updateValueToExcel("actualRefundAmount", actualRefundAmount);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("valueAfterCancellation", valueAfterCancellation);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "verify whether user is able to proceed purchasing a pharmacy product_KNET_Cancellation", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_Pharmacy_KNET_Cancellation(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
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
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
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
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		String firstWalletValue = myAccountPage.getFirstWalletValue();
		testResult = myAccountPage.verifyOrderCancellationFromOrder_ShopOnline(orderID, "OrderInMyOrderPage",
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = myAccountPage.verifyOrderCancellationMain_ShopOnline(paymentMode);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "OrderInCancellationSuccessPage", testCaseID, sheetName);
		testResult = myAccountPage.verifyOrderCancellationSuccess_ShopOnline();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		String expectedRefundAmount = myAccountPage.getRefundAmountAfterDeduction();
		String grandTotalAmount = myAccountPage.getTotalPriceOfOrder();
		String actualRefundAmount = myAccountPage.verifyRefundAmount(orderID);
		String valueAfterCancellation = myAccountPage.getWalletUpdatedValue();
		testResult = myAccountPage.verifyTotalWalletAmountAfterRefund_ShopOnline_MS();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("expectedRefundAmount", expectedRefundAmount);
			excelUtils.updateValueToExcel("actualRefundAmount", actualRefundAmount);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("valueAfterCancellation", valueAfterCancellation);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "Verify whether the user is able to cancel a product purchased from pharmacy using my wallet as a payment method", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_Pharmacy_WALLET_Cancellation(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
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
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
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
			assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyBookingSuccess();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		String firstWalletValue = myAccountPage.getFirstWalletValue();
		testResult = myAccountPage.verifyOrderCancellationFromOrder_ShopOnline(orderID, "OrderInMyOrderPage",
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = myAccountPage.verifyOrderCancellationMain_ShopOnline(paymentMode);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "OrderInCancellationSuccessPage", testCaseID, sheetName);
		testResult = myAccountPage.verifyOrderCancellationSuccess_ShopOnline();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		String expectedRefundAmount = myAccountPage.getRefundAmountAfterDeduction();
		String grandTotalAmount = myAccountPage.getTotalPriceOfOrder();
		String actualRefundAmount = myAccountPage.verifyRefundAmount(orderID);
		String valueAfterCancellation = myAccountPage.getWalletUpdatedValue();
		testResult = myAccountPage.verifyTotalWalletAmountAfterRefund_ShopOnline_MS();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("expectedRefundAmount", expectedRefundAmount);
			excelUtils.updateValueToExcel("actualRefundAmount", actualRefundAmount);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("valueAfterCancellation", valueAfterCancellation);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "Verify whether the user is able to view, track and cancel an order while proceeding from my account", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_Pharmacy_View_Track_Cancel(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
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
		testResult = homePage.selectPharmacy(pharmacySearchName, pharmacyName);
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
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
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
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
		testResult = myAccountPage.verifyViewOrder(orderID, "OrderInMyOrderPage", testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = myAccountPage.verifyTrackOrder(orderID, "OrderInMyOrderPage", testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		String firstWalletValue = myAccountPage.getFirstWalletValue();
		testResult = myAccountPage.verifyOrderCancellationFromOrder_ShopOnline(orderID, "OrderInMyOrderPage",
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = myAccountPage.verifyOrderCancellationMain_ShopOnline(paymentMode);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		takeScreenShot(getDriver(), "OrderInCancellationSuccessPage", testCaseID, sheetName);
		testResult = myAccountPage.verifyOrderCancellationSuccess_ShopOnline();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		String expectedRefundAmount = myAccountPage.getRefundAmountAfterDeduction();
		String grandTotalAmount = myAccountPage.getTotalPriceOfOrder();
		String actualRefundAmount = myAccountPage.verifyRefundAmount(orderID);
		String valueAfterCancellation = myAccountPage.getWalletUpdatedValue();
		testResult = myAccountPage.verifyTotalWalletAmountAfterRefund_ShopOnline_MS();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("grandTotalAmount", grandTotalAmount);
			excelUtils.updateValueToExcel("expectedRefundAmount", expectedRefundAmount);
			excelUtils.updateValueToExcel("actualRefundAmount", actualRefundAmount);
			excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
			excelUtils.updateValueToExcel("valueAfterCancellation", valueAfterCancellation);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "Verify whether the user is able to purchase a product from medical equipments", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_MedicalEquipment(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
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
		testResult = shopOnlineMainPage.selectMedicalEquipment();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineMainPage.select_ME_Pharmacy_Product(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
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
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
// String grandTotalAmount = checkoutPage.getShopOnineTotalPrice();
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "Verify whether the user is able to purchase a product from pharmacy from home page", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_PurchaseFromHome(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		if (operationName.equalsIgnoreCase("SalamtekHasItAll")) {
			testResult = homePage.selectProductFromSalamtekHasItAll("SalamtekHasItAll", testCaseID, sheetName);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
		} else if (operationName.equalsIgnoreCase("QuickServices")) {
			testResult = homePage.selectProductFromQuickServices(productCategory,"QuickService", testCaseID, sheetName);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
		}
		testResult = shopOnlineMainPage.select_ME_Pharmacy_Product(productName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
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
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
//String grandTotalAmount = checkoutPage.getShopOnineTotalPrice();
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "Verify whether the user is able to purchase a product from Shop Online Main page", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_PurchaseFromShopOnlineMain(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
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
		if(productCategory.equalsIgnoreCase("OFFERSANDPROMOTIONS")) {
			testResult = shopOnlineMainPage.selectProductFromSalamtekHasItAll(productCategory, productName, testCaseID, sheetName);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
			testResult = shopOnlineDetailsPage.goToCart();
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
		}
		else {
			testResult = shopOnlineMainPage.selectProductFromMostViewedCategories(productCategory);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
			testResult = shopOnlineMainPage.select_ME_Pharmacy_Product(productName);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
			testResult = shopOnlineDetailsPage.addProductToCart(productName, colorPresent);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
		}
		testResult = cartMainPage.verifyProductListInCart(pharmacyName);
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
		takeScreenShot(getDriver(), "OrderPlacedSuccess", testCaseID, sheetName);
		String orderID = checkoutPage.getBookingID();
//String grandTotalAmount = checkoutPage.getShopOnineTotalPrice();
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@Test(description = "Verify whether the user is able to filter Pharmacy Products", groups = {
			"ShopOnline" }, dataProvider = "dataForShopOnline")
	public void ShopOnline_FilterFromHome(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String pharmacySearchName,
			String pharmacyName, String productName, String productCategory, String colorPresent, String uploadNeeded,
			String paymentMode, String payOnlyBookingFee, String bankName, String firstName, String lastName,
			String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("grandTotalAmount");
		excelUtils.updateField("expectedRefundAmount");
		excelUtils.updateField("actualRefundAmount");
		excelUtils.updateField("firstWalletValue");
		excelUtils.updateField("valueAfterCancellation");
		excelUtils.updateField("orderID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		if (operationName.equalsIgnoreCase("SalamtekHasItAll")) {
			testResult = homePage.selectProductFromSalamtekHasItAll("SalamtekHasItAll", testCaseID, sheetName);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
		} else if (operationName.equalsIgnoreCase("QuickServices")) {
			testResult = homePage.selectProductFromQuickServices(productCategory, "QuickService", testCaseID, sheetName);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
		}
		testResult = shopOnlineMainPage.filterPharmacyEquipmentProducts(filterBy, filterType, filterValue, filterResult, "Filter_"+filterBy+"_"+filterType, testCaseID, sheetName);
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

	@DataProvider(name = "dataForShopOnline")
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
