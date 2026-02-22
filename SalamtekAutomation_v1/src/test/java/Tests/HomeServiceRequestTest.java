package Tests;

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
import Pages.AdminProfileHomeServiceRequestPage;
import Pages.AdminProfileLoginPage;
import Pages.AdminProfileLogoutPage;
import Pages.HomePage;
import Pages.HomeServiceRequestPage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.MyAccountPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class HomeServiceRequestTest extends BaseTest {
	Boolean testResult = false;
	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	HomeServiceRequestPage homeServiceRequestPage = new HomeServiceRequestPage(getDriver());
	MyAccountPage myAccountPage = new MyAccountPage(getDriver());
	LogoutPage logoutPage = new LogoutPage(getDriver());
	
	AdminProfileLoginPage adminProfileLoginPage = new AdminProfileLoginPage(getDriver());
	AdminProfileHomePage adminProfileHomePage = new AdminProfileHomePage(getDriver());
	AdminProfileHomeServiceRequestPage adminProfileHomeServiceRequestPage = new AdminProfileHomeServiceRequestPage(getDriver());
	AdminProfileLogoutPage adminProfileLogoutPage = new AdminProfileLogoutPage(getDriver());

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "HomeServiceRequest";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "Verify home service request Website", groups = {
			"HomeServiceRequest" }, dataProvider = "dataForHomeServiceRequest")
	public void HomeServiceRequest(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String requestComment, String adminURL, String adminUsername, String adminPassword,
			String selectionOperationFromLeftPanel, String requiredAppointment,
			String requesterName) throws Exception {
		ExtentTestManager.startTest(testCaseID+"_"+description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		excelUtils.updateField("expectedComment");
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
		testResult = homeServiceRequestPage.sendHomeServiceRequest(requestComment,testCaseName,testCaseID,sheetName);
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
		String expectedComment = adminProfileHomeServiceRequestPage.verifyHomeServiceRequest(requesterName, requestComment, testCaseName, testCaseID, sheetName);
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
			excelUtils.updateValueToExcel("expectedComment", expectedComment);
		} else {
			logoutPage.verifyLogout();
			excelUtils.updateResult(testResult);
		}
	}

	

	@DataProvider(name = "dataForHomeServiceRequest")
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
		Object[][] data = new Object[countIteration][ColNum - 3];
		for (int i1 = 1; i1 < lastRow; i1++) {
			String executionStatus1 = sheet.getRow(i1).getCell(3).getStringCellValue();
			String testCaseName1 = sheet.getRow(i1).getCell(1).getStringCellValue();
			if (executionStatus1.equalsIgnoreCase("Y") && testCaseName1.equalsIgnoreCase(m.getName())) {
				for (int j = 0; j < ColNum - 3; j++) {
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
