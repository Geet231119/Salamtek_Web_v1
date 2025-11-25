package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.JavascriptExecutor;

import Common.BaseTest;

public class AskSalamtekDoctorPage extends BaseTest{
	By askSalamtekDoctorHeader = By.xpath("//*[@id=\"askDoctorModal\"]/span[contains(text(),'ASK DR. SALAMTEK')]");
	By preferenceDrpDwn = By.xpath("//app-ask-doctor-footer/div/div/div[3]/div[1]/form/div/div[1]/div[1]/select");
	By problemTypeDrpDwn = By.xpath("//app-ask-doctor-footer/div/div/div[3]/div[1]/form/div/div[1]/div[2]/select");
	By describeYourCaseTxt = By.xpath("//app-ask-doctor-footer/div/div/div[3]/div[1]/form/div/div[1]/div[3]/textarea");
	By preferredContactMethodDrpDwn = By.xpath("//app-ask-doctor-footer/div/div/div[3]/div[1]/form/div/div[1]/div[4]/div[1]/select");
	By callDateTxt = By.xpath("//app-ask-doctor-footer/div/div/div[3]/div[1]/form/div/div[1]/div[4]/div[2]/input");
	By preferredTimeDrpDwn = By.xpath("//app-ask-doctor-footer/div/div/div[3]/div[1]/form/div/div[1]/div[4]/div[3]/div[2]/select");
	By additionalDetailsBtn = By.xpath("//app-ask-doctor-footer/div/div/div[3]/div[1]/form/div/div[2]/div/ul/li[2]/div/label[2]");
	By enterHeightTxt = By.xpath("//input[@formcontrolname='height']");
	By enterWeightTxt = By.xpath("//input[@formcontrolname='weight']");
	By prevDiagnosedYes = By.xpath("//label[@for='rady']");
	By prevDisgnosedDesc = By.xpath("//input[@formcontrolname='previouslyDiagnosedDescription']");
	By anyMedicationYes = By.xpath("//label[@for='radm']");
	By medicationDesc = By.xpath("//input[@formcontrolname='medicationDescription']");
	By anyAllergyYes = By.xpath("//label[@for='rada3']");
	By allergyDesc = By.xpath("//input[@formcontrolname='allergiesDescription']");
	By addDocumentLnk = By.xpath("//app-ask-doctor-footer/div/div/div[3]/div[1]/form/div/div[3]/div/ul/li[2]/span");
	By sendMyRequestBtn = By.xpath("/html/body/cdk-virtual-scroll-viewport/app-root/app-footer/div[1]/app-ask-doctor-footer/div/div/div[3]/div[3]/div/div/div/button[2]");
	By notificationLnk = By.xpath("//*[@id=\"navbarNav\"]/div[1]/ul/li[3]/a");
	By firstNotificationInList = By.xpath("//div[1]/div/div/div[1]/a/div/div/div/span");
	
	public AskSalamtekDoctorPage(WebDriver driver) {
		super();
	}
	
	public Boolean verifyAskSalamtekRequestSent(String preference, String problemType, String describeYourCase, String preferredContactMethod, String preferredTime, String needAdditionalDetails, String enterHeight, String enterWeight, String ques1, String ques1Desc, String ques2, String ques2Desc, String ques3, String ques3Desc) throws Exception{	
		explicitWait(preferenceDrpDwn);
		Select preferenceList = new Select(driver.findElement(preferenceDrpDwn));
		preferenceList.selectByVisibleText(preference);
		explicitWait(problemTypeDrpDwn);
		Select problemTypeList = new Select(driver.findElement(problemTypeDrpDwn));
		problemTypeList.selectByValue(problemType);
		driver.findElement(describeYourCaseTxt).sendKeys(describeYourCase);
		explicitWait(preferredContactMethodDrpDwn);
		Select preferredContactMethodList = new Select(driver.findElement(preferredContactMethodDrpDwn));
		preferredContactMethodList.selectByVisibleText(preferredContactMethod);
		driver.findElement(callDateTxt).sendKeys(getTodayDateInSlash());
		Select preferredTimeMethodList = new Select(driver.findElement(preferredTimeDrpDwn));
		preferredTimeMethodList.selectByVisibleText(preferredTime);
		waitSometime();
		if(needAdditionalDetails.equalsIgnoreCase("Yes")) {
			driver.findElement(additionalDetailsBtn).click();
			waitSometime();
			driver.findElement(enterHeightTxt).sendKeys(enterHeight);
			driver.findElement(enterWeightTxt).sendKeys(enterWeight);
			if(ques1.equalsIgnoreCase("Yes")) {
				driver.findElement(prevDiagnosedYes).click();
				driver.findElement(prevDisgnosedDesc).sendKeys(ques1Desc);
			}
			if(ques2.equalsIgnoreCase("Yes")) {
				driver.findElement(anyMedicationYes).click();
				driver.findElement(medicationDesc).sendKeys(ques2Desc);
			}
			if(ques3.equalsIgnoreCase("Yes")) {
				driver.findElement(anyAllergyYes).click();
				driver.findElement(allergyDesc).sendKeys(ques3Desc);
			}
		}
		scrollTillElementVisible(driver.findElement(sendMyRequestBtn));
		driver.findElement(addDocumentLnk).click();
		waitSometime();
		uploadPDF();
		waitSometime();
		problemTypeList.selectByValue(problemType);
		((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", driver.findElement(sendMyRequestBtn));
		waitElementToBeVisible(sendMyRequestBtn);
		WebElement element= driver.findElement(sendMyRequestBtn);
		element.click();
		return true;
	}
}
