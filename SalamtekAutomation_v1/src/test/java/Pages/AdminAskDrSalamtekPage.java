package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class AdminAskDrSalamtekPage extends BaseTest{
	By askDrSalamtekScreenIdentifier = By.xpath("//app-customer-requests-index/nb-card/nb-card-header[contains(text(),'Ask Dr. Salamtek')]");
	By problemTypeTxt = By.xpath("//app-customer-requests-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[2]/ng2-smart-table-filter/div/default-table-filter/input-filter/input");
	By viewBtn = By.xpath("//app-customer-requests-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr[1]/td[1]/ng2-st-tbody-custom/a[1]/i");
	By requestTitle = By.xpath("//app-customer-requests-view/nb-card[3]/nb-card-body/table/tr[1]/td");
	By requestPresence = By.xpath("//app-customer-requests-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr[1]/td[3]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	
	//Elements for complete
	By completeBtn = By.xpath("//app-customer-requests-view/nb-card[1]/nb-card-header/p/a[contains(text(),'Complete')]");
	By completeSectionFromLeftPanel = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[14]/ul/li[2]/a/span");
	
	//Elements for No Answer
	By noAnswerBtn = By.xpath("//app-customer-requests-view/nb-card[1]/nb-card-header/p/a[contains(text(),'No Answer')]");
	By noAnswerFromLeftPanel = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[14]/ul/li[3]/a/span");
	
	//Elements for Delete
	By deleterBtn = By.xpath("//app-customer-requests-view/nb-card[1]/nb-card-header/p/a[contains(text(),'Delete')]");
	By pendingFromLeftPanel = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[14]/ul/li[1]/a/span");
	By noDataMessage = By.xpath("//app-customer-requests-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td");
	
	//Elements for Send Request to Customer Service
	By sendRequestToCustomerServiceBtn = By.xpath("//app-customer-requests-view/nb-card[1]/nb-card-header/p/a[contains(text(),'Send Request To Customer Service')]");
	By sendRequestToCustomerServiceFromLeftPanel = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[14]/ul/li[4]/a/span");
	
	//Elements for Diagnosis notes
	By addNotesBtn = By.xpath("//app-customer-requests-view/nb-card[1]/nb-card-header/p/a[contains(text(),'Add Note')]");
	By addDiagnosisNoteHeader = By.xpath("//app-note/nb-card/nb-card-header");
	By addNotesTxt = By.xpath("//*[@id=\"cke_1_contents\"]/div/p");
	By noteSaveBtn = By.xpath("//*[@class=\"cdk-overlay-pane\"]/nb-dialog-container/app-note/nb-card/nb-card-body/form/div[2]/div/button");
	By diagnosisNoteInfo = By.xpath("//app-customer-requests-view/nb-card[1]/nb-card-body/table/tr[16]/td/p");
	
	//Elements for conduct test
	By conductTestLnk = By.xpath("//app-customer-requests-view/nb-card[1]/nb-card-header/p/a[contains(text(),'Conduct Test')]");
	By prescribeMedicineHeader = By.xpath("//*[@class=\"cdk-overlay-pane\"]/nb-dialog-container/app-eprescription/nb-card/nb-card-header");
	By prescriptionTypeTxt = By.xpath("//*[@class=\"cdk-overlay-pane\"]/nb-dialog-container/app-eprescription/nb-card/nb-card-body/form/div[1]/div/div/ng-select/div/div/div[3]/input");
	By totalUsageTxt = By.name("totalUsage");
	By testTxt = By.xpath("//app-eprescription/nb-card/nb-card-body/form/div[3]/div/div[1]/div/ng-select/div/div/div[2]/input");
	By testDrpDwn = By.xpath("//app-eprescription/nb-card/nb-card-body/form/div[3]/div/div[1]/div/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By referredLabTxt = By.xpath("//app-eprescription/nb-card/nb-card-body/form/div[3]/div/div[2]/div/ng-select/div/div/div[3]/input");
	By referredDrpDwn = By.xpath("//app-eprescription/nb-card/nb-card-body/form/div[3]/div/div[2]/div/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By saveBtnForPrescribeMedicine = By.xpath("//app-eprescription/nb-card/nb-card-body/form/div[4]/div/button");
	By prescriptionInformationHeader = By.xpath("//app-customer-requests-view/nb-card[3]/nb-card-header/span");
	By prescribedMedicineDetails = By.xpath("//app-customer-requests-view/nb-card[3]/nb-card-body/nb-card/nb-card-header/span");
	By exrNumberTxt = By.name("exrNumber");
	String exrNumber;
	
	//Elements for prescribe medicine
	By prescribeMedicineLnk = By.xpath("//app-customer-requests-view/nb-card[1]/nb-card-header/p/a[contains(text(),'Prescribe Medicine')]");
	By searchProductTxt = By.xpath("//app-eprescription/nb-card/nb-card-body/form/div[3]/nb-card-body[1]/div[2]/div/div/ng-select/div/div/div[2]/input");
	By searchProductDrpDwn = By.xpath("//app-eprescription/nb-card/nb-card-body/form/div[3]/nb-card-body[1]/div[2]/div/div/ng-select/ng-dropdown-panel/div/div[2]/div");
	By durationTxt = By.name("duration");
	By instructionTxt = By.name("comment");
	By referredPharmacyTxt = By.xpath("//app-eprescription/nb-card/nb-card-body/form/div[3]/div[2]/div/div/ng-select/div/div/div[2]/input");
	By referredPharmacyDrpDwn = By.xpath("//app-eprescription/nb-card/nb-card-body/form/div[3]/div[2]/div/div/ng-select/ng-dropdown-panel/div/div[2]/div");
	
	public AdminAskDrSalamtekPage(WebDriver driver) {
		super();
	}
	
	public boolean verifyRequestPresent(String problemType, String testCaseID, String sheet) throws Exception {	
		explicitWait(askDrSalamtekScreenIdentifier);
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		driver.findElement(viewBtn).click();
		waitForSpecificTime(3000);
		System.out.println("++++++++++++++++     "+driver.findElement(requestTitle).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(requestTitle).getText().trim().toUpperCase(), problemType.trim().toUpperCase());
		takeScreenShot(driver, "RequestPresentInAskDrSalamtek", testCaseID, sheet);
		return true;
	}
	
	public String verifyAddNotes(String problemType, String notes, String testCaseID, String sheet) throws Exception {	
		explicitWait(askDrSalamtekScreenIdentifier);
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		driver.findElement(viewBtn).click();
		waitForSpecificTime(3000);
		driver.findElement(addNotesBtn).click();
		waitSometime();
		explicitWait(addDiagnosisNoteHeader);
		waitSometime();
		driver.findElement(addNotesTxt).sendKeys(notes);
		waitSometime();
		takeScreenShot(driver, "AddingNotes", testCaseID, sheet);
		driver.findElement(noteSaveBtn).click();
		waitSometime();
		scrollTillElementVisible(driver.findElement(diagnosisNoteInfo));
		waitSometime();
		takeScreenShot(driver, "AddedNotes", testCaseID, sheet);
		System.out.println("++++++++++++++++     "+driver.findElement(diagnosisNoteInfo).getText().trim());
		Assert.assertEquals(driver.findElement(diagnosisNoteInfo).getText().trim(), notes.trim(),"Notes are not equal");
		return driver.findElement(diagnosisNoteInfo).getText().trim();
	}
	
	public String verifyConductTest(String problemType, String prescriptionType, String totalUsage, String test, String referredLab, String testCaseID, String sheet) throws Exception {	
		explicitWait(askDrSalamtekScreenIdentifier);
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		driver.findElement(viewBtn).click();
		waitForSpecificTime(3000);
		driver.findElement(conductTestLnk).click();
		waitSometime();
		if(driver.findElement(prescribeMedicineHeader).getText().trim().equalsIgnoreCase("E-Prescription")) {
			driver.findElement(prescriptionTypeTxt).sendKeys(prescriptionType);
			driver.findElement(totalUsageTxt).sendKeys(totalUsage);
			waitSometime();
			driver.findElement(testTxt).sendKeys(test);
			waitSometime();
			driver.findElement(testDrpDwn).click();
			waitSometime();
			driver.findElement(referredLabTxt).sendKeys(referredLab);
			waitSometime();
			driver.findElement(referredDrpDwn).click();
			driver.findElement(saveBtnForPrescribeMedicine).click();
		}
		waitSometime();
		scrollTillElementVisible(driver.findElement(prescriptionInformationHeader));
		takeScreenShot(driver, "ConductTestInformation", testCaseID, sheet);
		if(driver.findElement(prescribedMedicineDetails).getText().trim().toUpperCase().contains(referredLab.toUpperCase())) {
			exrNumber = driver.findElement(prescribedMedicineDetails).getText().substring(4, driver.findElement(prescribedMedicineDetails).getText().indexOf("\n")).trim();
			System.out.println("EXR Number: "+ exrNumber);
		}
		Assert.assertTrue(driver.findElement(prescribedMedicineDetails).isDisplayed(), "Details are not present");
		return exrNumber;
	}
	
	public String verifyPrescribeMedicine(String problemType, String prescriptionType, String totalUsage, String productName,
			String duration, String instruction, String referredPharmacy, String testCaseID, String sheet) throws Exception {	
		explicitWait(askDrSalamtekScreenIdentifier);
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		driver.findElement(viewBtn).click();
		waitForSpecificTime(3000);
		driver.findElement(prescribeMedicineLnk).click();
		waitSometime();
		if(driver.findElement(prescribeMedicineHeader).getText().trim().equalsIgnoreCase("E-Prescription")) {
			driver.findElement(prescriptionTypeTxt).sendKeys(prescriptionType);
			driver.findElement(totalUsageTxt).sendKeys(totalUsage);
			driver.findElement(searchProductTxt).sendKeys(productName);
			waitSometime();
			driver.findElement(searchProductDrpDwn).click();
			driver.findElement(durationTxt).sendKeys(duration);
			driver.findElement(instructionTxt).sendKeys(instruction);
			scrollTillEnd();
			waitSometime();
			driver.findElement(referredPharmacyTxt).sendKeys(referredPharmacy);
			waitSometime();
			driver.findElement(referredPharmacyDrpDwn).click();
			driver.findElement(saveBtnForPrescribeMedicine).click();
		}
		waitSometime();
		scrollTillElementVisible(driver.findElement(prescriptionInformationHeader));
		takeScreenShot(driver, "PrescriptionInformation", testCaseID, sheet);
		if(driver.findElement(prescribedMedicineDetails).getText().trim().toUpperCase().contains(referredPharmacy.toUpperCase()))
			exrNumber = driver.findElement(prescribedMedicineDetails).getText().substring(4, driver.findElement(prescribedMedicineDetails).getText().indexOf("\n")).trim();
		Assert.assertTrue(driver.findElement(prescribedMedicineDetails).isDisplayed(),"Details are not present");
		return exrNumber;
	}
	
	
	public boolean verifyComplete(String problemType, String testCaseID, String sheet) throws Exception {	
		explicitWait(askDrSalamtekScreenIdentifier);
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		driver.findElement(viewBtn).click();
		waitForSpecificTime(3000);
		driver.findElement(completeBtn).click();
		waitSometime();
		driver.findElement(completeSectionFromLeftPanel).click();
		waitSometime();
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		takeScreenShot(driver, "completeRequest", testCaseID, sheet);
		System.out.println("++++++++++++++++     "+driver.findElement(requestPresence).getText().toUpperCase().trim());
		Assert.assertEquals(driver.findElement(requestPresence).getText().toUpperCase().trim(), problemType.toUpperCase(),"Request is not present");
		return true;
	}
	
	public boolean verifyNoAnswer(String problemType, String testCaseID, String sheet) throws Exception {	
		explicitWait(askDrSalamtekScreenIdentifier);
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		driver.findElement(viewBtn).click();
		waitForSpecificTime(3000);
		driver.findElement(noAnswerBtn).click();
		waitSometime();
		driver.findElement(noAnswerFromLeftPanel).click();
		waitSometime();
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		takeScreenShot(driver, "noAnswerRequest", testCaseID, sheet);
		System.out.println("++++++++++++++++     "+driver.findElement(requestPresence).getText().toUpperCase().trim());
		Assert.assertEquals(driver.findElement(requestPresence).getText().toUpperCase().trim(), problemType.toUpperCase(),"Request is not present");
		return true;
	}
	
	public boolean verifySendRequestToCustomerService(String problemType, String testCaseID, String sheet) throws Exception {	
		explicitWait(askDrSalamtekScreenIdentifier);
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		driver.findElement(viewBtn).click();
		waitForSpecificTime(3000);
		driver.findElement(sendRequestToCustomerServiceBtn).click();
		waitSometime();
		driver.findElement(sendRequestToCustomerServiceFromLeftPanel).click();
		waitSometime();
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		takeScreenShot(driver, "sendRequestToCustomerService", testCaseID, sheet);
		System.out.println("++++++++++++++++     "+driver.findElement(requestPresence).getText().toUpperCase().trim());
		Assert.assertEquals(driver.findElement(requestPresence).getText().toUpperCase().trim(), problemType.toUpperCase(),"Request is not present");
		return true;
	}
	
	public String verifyDelete(String problemType, String testCaseID, String sheet) throws Exception {	
		explicitWait(askDrSalamtekScreenIdentifier);
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		driver.findElement(viewBtn).click();
		waitForSpecificTime(3000);
		driver.findElement(deleterBtn).click();
		waitSometime();
		driver.findElement(pendingFromLeftPanel).click();
		waitSometime();
		driver.findElement(problemTypeTxt).sendKeys(problemType);
		waitSometime();
		takeScreenShot(driver, "DeleteRequest", testCaseID, sheet);
		System.out.println("++++++++++++++++     "+driver.findElement(noDataMessage).getText().toUpperCase().trim());
		Assert.assertEquals(driver.findElement(noDataMessage).getText().toUpperCase().trim(), "NO DATA FOUND","Request is not present");
		return driver.findElement(noDataMessage).getText().trim();
	}
}
