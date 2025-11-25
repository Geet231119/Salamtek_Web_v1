package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.BaseTest;

public class LabProfileHomePage extends BaseTest {
	By appointmentSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/a/span");
	By labAppointmentSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li/a/span");
	By allSectionInLabAppointment = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li/ul/li[1]/a/span");
	By insuranceInLabAppointment = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li/ul/li[9]/a/span");
	
	//Elements for walk in
	By walkInSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li/ul/li[10]/a/span");
	By createWalkInSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li/ul/li[11]/a/span");
	By upcomingSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li/ul/li[10]/ul/li[1]/a/span");
	
	public LabProfileHomePage(WebDriver driver) {
		super();
	}

	// Validate the selection of left panel operation
	public boolean selectOperationFromLeftPanel(String operationName) {
		waitSometime();
		switch (operationName) {
		case "appointments":
			waitForSpecificTime(20000);
			explicitWait(appointmentSection);
			driver.findElement(appointmentSection).click();
			break;
		}
		return true;
	}

	// Validate the selection of left panel sub operation
	public boolean selectSuboperationFromLeftPanel(String subOperationName) {
		waitSometime();
		switch (subOperationName) {
		case "labAppointment":
			explicitWait(labAppointmentSection);
			driver.findElement(labAppointmentSection).click();
			break;
		}
		return true;
	}

	// Validate the selection of left panel sub operation (Lab)
	public boolean selectSuboperationOfLab(String subOperationName) {
		waitSometime();
		switch (subOperationName) {
		case "allInLab":
			explicitWait(allSectionInLabAppointment);
			driver.findElement(allSectionInLabAppointment).click();
			break;
		case "insurance":
			explicitWait(insuranceInLabAppointment);
			driver.findElement(insuranceInLabAppointment).click();
			break;
		case "create":
			moveToElement(driver.findElement(createWalkInSection));
			explicitWait(createWalkInSection);
			driver.findElement(createWalkInSection).click();
			break;
		case "WalkIn":
			explicitWait(walkInSection);
			driver.findElement(walkInSection).click();
			break;
		}
		return true;
	}

	// Validate the selection of left panel sub status (Lab)
	public boolean selectSubStatusOfLab(String subStatusName) {
		waitSometime();
		switch (subStatusName) {
		case "upcoming":
			explicitWait(upcomingSection);
			driver.findElement(upcomingSection).click();
			break;
		}
		return true;
	}

	// Validate closing of opened tabs
	public boolean closeOpenedTabs(String tabName) {
		waitSometime();
		switch (tabName) {
		case "appointments":
			driver.findElement(appointmentSection).click();
			break;
		}
		return true;
	}
}
