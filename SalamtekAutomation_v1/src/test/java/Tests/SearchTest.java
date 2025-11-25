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
import Pages.CartDetailsPage;
import Pages.CartMainPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.HospitalsAndClinicsDetailsPage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.SearchPage;
import Pages.ShopOnlineDetailsPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class SearchTest extends BaseTest {
	Boolean testResult = false;
	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	HospitalsAndClinicsDetailsPage hospitalsAndClinicsDetailsPage = new HospitalsAndClinicsDetailsPage(getDriver());
	BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(getDriver());
	ShopOnlineDetailsPage shopOnlineDetailsPage = new ShopOnlineDetailsPage(getDriver());
	CartMainPage cartMainPage = new CartMainPage(getDriver());
	CartDetailsPage cartDetailsPage = new CartDetailsPage(getDriver());
	CheckoutPage checkoutPage = new CheckoutPage(getDriver());
	SearchPage searchPage = new SearchPage(getDriver());
	LogoutPage logoutPage = new LogoutPage(getDriver());

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "Search";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "Verify whether the user is able to get the searched result in advanced search.", groups = {
			"Search" }, dataProvider = "dataForSearch")
	public void Search(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String operationName, String typeOfSearch, String searchSection,
			String searchValue, String expectedSearchResult, String serviceProviderType, String serviceProviderName,
			String specialties, String doctorName, String serviceName, String profileIndividualServiceSelection,
			String profileIndividualTestSelection, String pharmacyName, String productName, String colorPresent,
			String uploadNeeded, String paymentMode, String payOnlyBookingFee, String bankName, String firstName,
			String lastName, String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualError");
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
		testResult = searchPage.verifySearchResult(typeOfSearch, searchSection, searchValue, expectedSearchResult,
				testCaseID, sheetName);
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

	@Test(description = "Verify whether the user is able to book a service from a lab when providing complete lab name in advance search", groups = {
			"Search" }, dataProvider = "dataForSearch")
	public void Search_BookMedicalService(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String typeOfSearch,
			String searchSection, String searchValue, String expectedSearchResult, String serviceProviderType,
			String serviceProviderName, String specialties, String doctorName, String serviceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection, String pharmacyName,
			String productName, String colorPresent, String uploadNeeded, String paymentMode, String payOnlyBookingFee,
			String bankName, String firstName, String lastName, String address, String city, String country,
			String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV,
			String pin) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualError");
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
		testResult = searchPage.verifySearchResult(typeOfSearch, searchSection, searchValue, expectedSearchResult,
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = searchPage.selectSearchResult(typeOfSearch, searchSection, searchValue, testCaseID, sheetName);
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
		takeScreenShot(getDriver(), "BookingMedicalServiceSuccess", testCaseID, sheetName);
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

	@Test(description = "Verify whether the user is able to get the searched result when providing the complete or partial product name in advanced search", groups = {
			"Search" }, dataProvider = "dataForSearch")
	public void Search_PurchaseProduct(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String typeOfSearch,
			String searchSection, String searchValue, String expectedSearchResult, String serviceProviderType,
			String serviceProviderName, String specialties, String doctorName, String serviceName,
			String profileIndividualServiceSelection, String profileIndividualTestSelection, String pharmacyName,
			String productName, String colorPresent, String uploadNeeded, String paymentMode, String payOnlyBookingFee,
			String bankName, String firstName, String lastName, String address, String city, String country,
			String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV,
			String pin) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualError");
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
		testResult = searchPage.verifySearchResult(typeOfSearch, searchSection, searchValue, expectedSearchResult,
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = searchPage.selectSearchResult(typeOfSearch, searchSection, searchValue, testCaseID, sheetName);
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

	@Test(description = "Verify whether the user is able to book a doctor appointment when providing complete clinic name in advance search", groups = {
			"Search" }, dataProvider = "dataForSearch")
	public void Search_BookDoctorAppointment_HospitalsAndClinic(String testCaseID, String testCaseName,
			String description, String executionStatus, String username, String password, String operationName,
			String typeOfSearch, String searchSection, String searchValue, String expectedSearchResult,
			String serviceProviderType, String serviceProviderName, String specialties, String doctorName,
			String serviceName, String profileIndividualServiceSelection, String profileIndividualTestSelection,
			String pharmacyName, String productName, String colorPresent, String uploadNeeded, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualError");
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
		testResult = searchPage.verifySearchResult(typeOfSearch, searchSection, searchValue, expectedSearchResult,
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = searchPage.selectSearchResult(typeOfSearch, searchSection, searchValue, testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = hospitalsAndClinicsDetailsPage.selectDoctorFromClinic(searchValue, specialties, doctorName,
				testCaseID, sheetName);
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

	@Test(description = "Verify whether the user is able to get the searched result in advanced search.", groups = {
			"Search" }, dataProvider = "dataForSearch")
	public void SearchError(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String operationName, String typeOfSearch, String searchSection,
			String searchValue, String expectedSearchResult, String serviceProviderType, String serviceProviderName,
			String specialties, String doctorName, String serviceName, String profileIndividualServiceSelection,
			String profileIndividualTestSelection, String pharmacyName, String productName, String colorPresent,
			String uploadNeeded, String paymentMode, String payOnlyBookingFee, String bankName, String firstName,
			String lastName, String address, String city, String country, String cardNumber, String cardExpiryMonth,
			String cardExpiryYear, String cardHolderName, String CVV, String pin) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("actualError");
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
		testResult = searchPage.verifySearchError_2Letter(searchValue,expectedSearchResult,
				testCaseID, sheetName);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("actualError", expectedSearchResult);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
			assertEquals(testResult, true);
		}
	}

	@DataProvider(name = "dataForSearch")
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
		Object[][] data = new Object[countIteration][ColNum - 5];
		for (int i1 = 1; i1 < lastRow; i1++) {
			String executionStatus1 = sheet.getRow(i1).getCell(3).getStringCellValue();
			String testCaseName1 = sheet.getRow(i1).getCell(1).getStringCellValue();
			if (executionStatus1.equalsIgnoreCase("Y") && testCaseName1.equalsIgnoreCase(m.getName())) {
				for (int j = 0; j < ColNum - 5; j++) {
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
