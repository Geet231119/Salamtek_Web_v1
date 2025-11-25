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
import Pages.DoctorCornerDetailPage;
import Pages.DoctorCornerDoctorPage;
import Pages.DoctorCornerMainPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.ShopOnlineDetailsPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class DoctorCornerTest extends BaseTest {
	Boolean testResult = false;
	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	DoctorCornerMainPage doctorCornerMainPage = new DoctorCornerMainPage(getDriver());
	DoctorCornerDetailPage doctorCornerDetailPage = new DoctorCornerDetailPage(getDriver());
	DoctorCornerDoctorPage doctorCornerDoctorPage = new DoctorCornerDoctorPage(getDriver());
	ShopOnlineDetailsPage shopOnlineDetailsPage = new ShopOnlineDetailsPage(getDriver());
	CartMainPage cartMainPage = new CartMainPage(getDriver());
	CartDetailsPage cartDetailsPage = new CartDetailsPage(getDriver());
	CheckoutPage checkoutPage = new CheckoutPage(getDriver());
	LogoutPage logoutPage = new LogoutPage(getDriver());

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "DoctorCorner";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "verify whether user is able to proceed appointment with a doctor", groups = {
			"DoctorCorner" }, dataProvider = "dataForDoctorCorner")
	public void DoctorCorner_DoctorShelves_FilterFromMainPage(String testCaseID, String testCaseName,
			String description, String executionStatus, String username, String password, String operationName,
			String doctorName, String serviceName, String pharmacyName, String productName,String colorPresent, String uploadNeeded, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("orderID");
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
		testResult = doctorCornerMainPage.selectCheckoutAllDoctorShelves();
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = doctorCornerDetailPage.filterDoctorShelves(filterType, filterValue, filterResult, testCaseID,
				sheetName);
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

	@Test(description = "verify whether user is able to proceed appointment with a doctor", groups = {
			"DoctorCorner" }, dataProvider = "dataForDoctorCorner")
	public void DoctorCorner_DoctorPicks_FilterFromHomeage(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String doctorName,
			String serviceName, String pharmacyName, String productName,String colorPresent, String uploadNeeded, String paymentMode, String payOnlyBookingFee, String bankName, String firstName,
			String lastName, String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin, String filterBy, String filterType,
			String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("orderID");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = homePage.selectDoctorRecommendationsFromQuickServices(filterBy, "QuickServices", testCaseID,
				sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = doctorCornerDetailPage.filterDoctorPicks(filterType, filterValue, filterResult, testCaseID,
				sheetName);
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

	@Test(description = "Verify whether the user is able to purchase a product from Doctor Corner", groups = {
			"DoctorCorner" }, dataProvider = "dataForDoctorCorner")
	public void DoctorCorner_ProductPurchase(String testCaseID, String testCaseName,
			String description, String executionStatus, String username, String password, String operationName,
			String doctorName, String serviceName, String pharmacyName, String productName,String colorPresent, String uploadNeeded, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city, String country, String cardNumber,
			String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV, String pin,
			String filterBy, String filterType, String filterValue, String filterResult) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("orderID");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		if(!operationName.equalsIgnoreCase("QuickServices")) {
			testResult = homePage.selectOperation(operationName);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
			if(filterBy.equalsIgnoreCase("doctorShelves")) {
				testResult = doctorCornerMainPage.selectCheckoutAllDoctorShelves();
				if (testResult.equals(false)) {
					logoutPage.verifyLogout();
					excelUtils.updateResult(testResult);
					assertEquals(testResult, true);
				}
			}
			else {
				testResult = doctorCornerMainPage.selectCheckoutAllDoctorPicks();
				if (testResult.equals(false)) {
					logoutPage.verifyLogout();
					excelUtils.updateResult(testResult);
					assertEquals(testResult, true);
				}
			}
		}
		else {
			testResult = homePage.selectDoctorRecommendationsFromQuickServices(filterBy, filterBy, testCaseID, sheetName);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
		}
		
		if(filterBy.equalsIgnoreCase("doctorShelves")) {
			testResult = doctorCornerDetailPage.selectDoctor(filterBy, doctorName, testCaseID, sheetName);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
			testResult = doctorCornerDoctorPage.selectProductFromRecommendation(productName, testCaseID, sheetName);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
		}
		else {
			testResult = doctorCornerDetailPage.selectProduct(filterBy, productName, testCaseID, sheetName);
			if (testResult.equals(false)) {
				logoutPage.verifyLogout();
				excelUtils.updateResult(testResult);
				assertEquals(testResult, true);
			}
		}
		testResult = shopOnlineDetailsPage.goToCart();
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

	@DataProvider(name = "dataForDoctorCorner")
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
		Object[][] data = new Object[countIteration][ColNum - 4];
		for (int i1 = 1; i1 < lastRow; i1++) {
			String executionStatus1 = sheet.getRow(i1).getCell(3).getStringCellValue();
			String testCaseName1 = sheet.getRow(i1).getCell(1).getStringCellValue();
			if (executionStatus1.equalsIgnoreCase("Y") && testCaseName1.equalsIgnoreCase(m.getName())) {
				for (int j = 0; j < ColNum - 4; j++) {
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
