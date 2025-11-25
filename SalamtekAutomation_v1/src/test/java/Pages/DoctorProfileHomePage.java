package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.BaseTest;

public class DoctorProfileHomePage extends BaseTest{
	By appointmentSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/a/span");
	By doctorAppointmentSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li[1]/a/span");
	By allSectionInDoctorAppointment = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li[1]/ul/li[2]/a/span");
	By upcomingInDoctorAppointment = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li[1]/ul/li[3]/a/span");
	
	public DoctorProfileHomePage(WebDriver driver) 
	{
		super();
	}
	
	//Validate the selection of left panel operation
	public boolean selectOperationFromLeftPanel(String operationName) {
		//waitSometime();
		switch(operationName) {
		case "appointments": 
			explicitWait(appointmentSection);
			waitSometime();
			clickUsingJS(appointmentSection);
			break;
		}
		return true;
	}
	
	//Validate the selection of left panel sub operation
	public boolean selectSuboperationFromLeftPanel(String subOperationName) {
		//waitSometime();
		switch(subOperationName) {
		case "doctorAppointment": 
			explicitWait(doctorAppointmentSection);
			driver.findElement(doctorAppointmentSection).click();
			break;
		}
		return true;
	}
	
	//Validate the selection of left panel sub operation (Lab)
	public boolean selectSuboperationOfLab(String subOperationName) {
		//waitSometime();
		switch(subOperationName) {
		case "allInDoctor": 
			explicitWait(allSectionInDoctorAppointment);
			driver.findElement(allSectionInDoctorAppointment).click();
			break;
		case "upcomingInDoctor": 
			explicitWait(upcomingInDoctorAppointment);
			driver.findElement(upcomingInDoctorAppointment).click();
			waitSometime();
			driver.findElement(upcomingInDoctorAppointment).click();
			break;
		}
		return true;
	}
	
	//Validate closing of opened tabs
	public boolean closeOpenedTabs(String tabName) {
		waitSometime();
		switch(tabName) {
		case "appointments": 
			driver.findElement(appointmentSection).click();
			break;
		}
		return true;
	}
}
