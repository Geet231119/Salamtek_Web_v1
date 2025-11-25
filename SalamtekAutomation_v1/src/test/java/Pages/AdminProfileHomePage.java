package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.BaseTest;

public class AdminProfileHomePage extends BaseTest{
	By appointmentSection = By.xpath("//span[contains(text(),'Appointments')]");
	By doctorAppointmentSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li[1]/a/span");
	By labAppointmentSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li[2]/a/span");
	By allSectionInLabAppointment = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li[2]/ul/li[1]/a/span");
	By allSectionInDoctorAppointment = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li[1]/ul/li[2]/a/span");
	By upcomingInDoctorAppointment = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li[1]/ul/li[3]/a/span");
	By reportSection = By.xpath("//span[contains(text(),'Reports')]");
	By accountStatementSection = By.xpath("//span[contains(text(),'Account Statement')]");
	By referralCalculationSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[20]/ul/li[14]/a/span[contains(text(),'Referral Calculation')]");
	By settingsSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[13]/a/span[contains(text(),'Settings')]");
	By referralSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[13]/ul/li[9]/a/span[contains(text(),'Referrals')]");
	By ordersSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[3]/a/span[contains(text(),'Orders')]");
	By allSectionInOrders = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[3]/ul/li[1]/a/span[contains(text(),'All Orders')]");
	By askDrSalatekSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[15]/a/span[contains(text(),'Ask Dr. Salamtek')]");
	By pendingInAskSalamtek = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[15]/ul/li[1]/a/span[contains(text(),'Pending')]");
	By storeManagementSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[8]/a/span[contains(text(),'Store Management')]");
	By userPrescriptionSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[8]/ul/li[4]/a/span[contains(text(),'User Prescription')]");
	By marketingSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[10]/a/span[contains(text(),'Marketing')]");
	By promotionSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[10]/ul/li[3]/a/span[contains(text(),'Promotions')]");
	By voucherSection = By.xpath("//span[contains(text(),'Vouchers')]");
	By usersSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[14]/a/span[contains(text(),'Users')]");
	By customerSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[14]/ul/li[1]/a/span[contains(text(),'Customers')]");
	
	public AdminProfileHomePage(WebDriver driver) 
	{
		super();
	}
	
	//Validate the selection of left panel operation
	public boolean selectOperationFromLeftPanel(String operationName) {
		//waitForSpecificTime(15000);
		switch(operationName) {
		case "appointments": 
			waitForSpecificTime(5000);
			explicitWait(appointmentSection);
			waitSometime();
			moveToElement(driver.findElement(appointmentSection));
			clickUsingJS(appointmentSection);
			break;
		case "reports": 
			waitForSpecificTime(20000);
			moveToElement(driver.findElement(reportSection));
			waitElementToBeClickable(reportSection);
			driver.findElement(reportSection).click();
			break;
		case "settings":
			waitForSpecificTime(5000);
			moveToElement(driver.findElement(settingsSection));
			waitElementToBeClickable(settingsSection);
			waitForSpecificTime(15000);
			driver.findElement(settingsSection).click();
			break;
		case "orders":
			driver.findElement(ordersSection).click();
			break;
		case "askDrSalamtek":
			driver.findElement(askDrSalatekSection).click();
			break;
		case "storeManagement":
			waitForSpecificTime(20000);
			waitElementToBeClickable(storeManagementSection);
			driver.findElement(storeManagementSection).click();
			break;
		case "marketing":
			waitForSpecificTime(20000);
			waitElementToBeClickable(marketingSection);
			driver.findElement(marketingSection).click();
			break;
		case "users":
			waitForSpecificTime(15000);
			waitElementToBeClickable(usersSection);
			driver.findElement(usersSection).click();
			break;
		}
		return true;
	}
	
	//Validate the selection of left panel sub operation
	public boolean selectSuboperationFromLeftPanel(String subOperationName) {
		waitSometime();
		switch(subOperationName) {
		case "doctorAppointment": 
			explicitWait(doctorAppointmentSection);
			waitForSpecificTime(3000);
			driver.findElement(doctorAppointmentSection).click();
			break;
		case "labAppointment": 
			explicitWait(labAppointmentSection);
			waitForSpecificTime(2000);
			driver.findElement(labAppointmentSection).click();
			break;
		case "referralCalculation":
			scrollTillElementVisible(driver.findElement(referralCalculationSection));
			driver.findElement(referralCalculationSection).click();
			break;
		case "referrals":
			moveToElement(driver.findElement(referralSection));
			driver.findElement(referralSection).click();
			break;
		case "allInOrders":
			driver.findElement(allSectionInOrders).click();
			break;
		case "pendingInAskSalamtek":
			driver.findElement(pendingInAskSalamtek).click();
			break;
		case "userPrescription":
			driver.findElement(userPrescriptionSection).click();
			break;
		case "accountStatement":
			moveToElement(driver.findElement(accountStatementSection));
			driver.findElement(accountStatementSection).click();
			break;
		case "promotion":
			driver.findElement(promotionSection).click();
			break;
		case "vouchers":
			driver.findElement(voucherSection).click();
			break;
		case "customers":
			driver.findElement(customerSection).click();
			break;
		}
		return true;
	}
	
	//Validate the selection of left panel sub operation (Lab)
	public boolean selectSuboperationOfLab(String subOperationName) {
		waitSometime();
		switch(subOperationName) {
		case "allInLab": 
			explicitWait_60(allSectionInLabAppointment);
			driver.findElement(allSectionInLabAppointment).click();
			break;
		case "allInDoctor": 
			explicitWait_60(allSectionInDoctorAppointment);
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
		case "doctorAppointment":
			driver.findElement(doctorAppointmentSection).click();
			break;
		case "labAppointment": 
			driver.findElement(labAppointmentSection).click();
			break;
		case "referrals": 
			driver.findElement(referralSection).click();
			break;
		case "allInOrders": 
			driver.findElement(allSectionInOrders).click();
			break;
		case "settings": 
			driver.findElement(settingsSection).click();
			break;
		case "orders": 
			driver.findElement(ordersSection).click();
			break;
		}
		return true;
	}
}
