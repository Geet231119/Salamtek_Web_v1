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
import Pages.HomePage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.MyAccountPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class MyAccountTest extends BaseTest {
	Boolean testResult = false;
	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	MyAccountPage myAccountPage = new MyAccountPage(getDriver());
	LogoutPage logoutPage = new LogoutPage(getDriver());

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "MyAccount";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "Verify whether the user can view the appointment schedule while proceeding from My account dashboard.", groups = {
			"MyAccount" }, dataProvider = "dataForMyAccount")
	public void appointment(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String operationName, String appointmentDate, String patientName,
			String providerName, String statusFilter, String event, String article, String tips, String doctorName,
			String productName, String medicalServiceProvider, String clinicName, String referredPharmacy,
			String exrNumber, String statusInResult) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
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
		testResult = myAccountPage.verifyAppointments(appointmentDate, patientName, providerName, statusFilter,
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

	@Test(description = "Verify whether the user is able to view my favorite section and is able to filter my favorite by subject", groups = {
			"MyAccount" }, dataProvider = "dataForMyAccount")
	public void favorites(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String operationName, String appointmentDate, String patientName,
			String providerName, String statusFilter, String event, String article, String tips, String doctorName,
			String productName, String medicalServiceProvider, String clinicName, String referredPharmacy,
			String exrNumber, String statusInResult) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
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
		testResult = myAccountPage.verifyMyFavorites(event, article, tips, doctorName, productName,
				medicalServiceProvider, clinicName, testCaseID, sheetName);
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

	@Test(description = "Verify whether the user is able to search for E-prescriptions by EXR NO and date and that is able to filter prescriptions by provider name, patient name or status.", groups = {
			"MyAccount" }, dataProvider = "dataForMyAccount")
	public void ePrescription(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String operationName, String appointmentDate, String patientName,
			String providerName, String statusFilter, String event, String article, String tips, String doctorName,
			String productName, String medicalServiceProvider, String clinicName, String referredPharmacy,
			String exrNumber, String statusInResult) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
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
		testResult = myAccountPage.verifyEPrescription(appointmentDate, patientName, providerName, statusFilter,
				referredPharmacy, exrNumber, statusInResult, testCaseID, sheetName);
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

	@Test(description = "Verify whether the user is able to view the correct account name after logging in to Salamtek Application", groups = {
			"MyAccount" }, dataProvider = "dataForMyAccount")
	public void profile(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String operationName, String appointmentDate, String patientName,
			String providerName, String statusFilter, String event, String article, String tips, String doctorName,
			String productName, String medicalServiceProvider, String clinicName, String referredPharmacy,
			String exrNumber, String statusInResult) throws Exception {
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
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
		testResult = myAccountPage.verifyProfileName(patientName, testCaseID, sheetName);
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

	@DataProvider(name = "dataForMyAccount")
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
		Object[][] data = new Object[countIteration][ColNum - 1];
		for (int i1 = 1; i1 < lastRow; i1++) {
			String executionStatus1 = sheet.getRow(i1).getCell(3).getStringCellValue();
			String testCaseName1 = sheet.getRow(i1).getCell(1).getStringCellValue();
			if (executionStatus1.equalsIgnoreCase("Y") && testCaseName1.equalsIgnoreCase(m.getName())) {
				for (int j = 0; j < ColNum - 1; j++) {
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
