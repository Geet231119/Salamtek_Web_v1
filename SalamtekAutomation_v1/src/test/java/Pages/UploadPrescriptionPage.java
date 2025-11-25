package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.BaseTest;

public class UploadPrescriptionPage extends BaseTest {
	By uploadPrescriptionHeader = By.xpath("//app-upload-prescription/div/div/div[1]/div[2]/h5/span[contains(text(),'PRESCRIPTION')]");
	By documentUploadTxt = By.xpath("//app-upload-prescription/div/div/div[2]/div[1]/div[1]/div/div/div/span[2]/span[contains(text(),'UPLOAD')]");
	By uploadBtn = By.xpath("//app-upload-prescription/div/div/div[2]/div[2]/button");
	

	public UploadPrescriptionPage(WebDriver driver) {
		super();
	}
	
	// Select document from system and upload as prescription
		public boolean uploadDocumentForUploadPrescription(String pageName, String tcID,
				String sheetName) throws Exception {
			waitSometime();
			explicitWait(uploadPrescriptionHeader);
			driver.findElement(documentUploadTxt).click();
			waitSometime();
			uploadPDF();
			waitSometime();
			takeScreenShot(getDriver(), pageName, tcID, sheetName);
			clickUsingJS(uploadBtn);
			waitSometime();
			return true;
		}
}
