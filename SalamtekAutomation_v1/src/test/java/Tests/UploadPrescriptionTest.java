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
import Pages.AdminProfileHomePage;
import Pages.AdminProfileLoginPage;
import Pages.AdminProfileLogoutPage;
import Pages.AdminProfileUserPrescriptionPage;
import Pages.BookAppointmentPage;
import Pages.CartDetailsPage;
import Pages.CartMainPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.MyAccountPage;
import Pages.NotificationListPage;
import Pages.ShopOnlineDetailsPage;
import Pages.ShopOnlineMainPage;
import Pages.UploadPrescriptionPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class UploadPrescriptionTest extends BaseTest {
	Boolean testResult = false;
	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	UploadPrescriptionPage uploadPrescriptionPage = new UploadPrescriptionPage(getDriver());
	BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(getDriver());
	CheckoutPage checkoutPage = new CheckoutPage(getDriver());
	LogoutPage logoutPage = new LogoutPage(getDriver());
	MyAccountPage myAccountPage = new MyAccountPage(getDriver());
	AdminProfileLoginPage adminProfileLoginPage = new AdminProfileLoginPage(getDriver());
	AdminProfileHomePage adminProfileHomePage = new AdminProfileHomePage(getDriver());
	AdminProfileUserPrescriptionPage adminProfileUserPrescriptionPage = new AdminProfileUserPrescriptionPage(getDriver());
	AdminProfileLogoutPage adminProfileLogoutPage = new AdminProfileLogoutPage(getDriver());
	NotificationListPage notificationListPage = new NotificationListPage(getDriver());
	ShopOnlineMainPage shopOnlineMainPage = new ShopOnlineMainPage(getDriver());
	ShopOnlineDetailsPage shopOnlineDetailsPage = new ShopOnlineDetailsPage(getDriver());
	CartMainPage cartMainPage = new CartMainPage(getDriver());
	CartDetailsPage cartDetailsPage = new CartDetailsPage(driver);

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "UploadPrescription";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "verify whether admin is able to start the appointment", groups = {
			"UploadPrescriptiont" }, dataProvider = "dataForUploadPrescription")
	public void UploadPrescription(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String paymentMode, String payOnlyBookingFee, String bankName,
			String firstName, String lastName, String address, String city,String country,
			String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName, String CVV,
			String pin, String uploadNeeded, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment,
			String prescriptionName, String productName, String pharmacyName) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("notification");
		excelUtils.updateField("bookingID");
		excelUtils.updateField("Status");
		excelUtils.updateField("ErrorMessage");
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
		testResult = uploadPrescriptionPage.uploadDocumentForUploadPrescription("UploadPrescription", testCaseID, sheetName);
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
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileHomePage.selectOperationFromLeftPanel(selectionOperationFromLeftPanel);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		testResult = adminProfileHomePage.selectSuboperationFromLeftPanel(requiredAppointment);
		if (testResult.equals(false)) {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
		
		testResult = adminProfileUserPrescriptionPage.verifyAssignProductToPrescription(prescriptionName, productName, pharmacyName, "AssignProduct_UserPrescription", testCaseID, sheetName);
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
		String notification = notificationListPage.verifyNotificationPresenceForUserPrescription(testCaseID, sheetName);
		testResult = myAccountPage.verifyEPrescriptionPopup(pharmacyName);
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

		testResult = logoutPage.verifyLogout();

		if (testResult.equals(true)) {
			excelUtils.updateResult(testResult);
			excelUtils.updateValueToExcel("notification", notification);
			excelUtils.updateValueToExcel("orderID", orderID);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	

	@DataProvider(name = "dataForUploadPrescription")
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
