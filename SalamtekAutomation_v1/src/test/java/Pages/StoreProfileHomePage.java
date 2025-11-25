package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.BaseTest;

public class StoreProfileHomePage extends BaseTest{
	By ordersSection = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/a/span");
	By allSectionInOrders = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li[1]/a/span");
	By insurance = By.xpath("//nb-sidebar/div/div/nb-menu/ul/li[2]/ul/li[10]/a/span");
	
	public StoreProfileHomePage(WebDriver driver) 
	{
		super();
	}
	
	//Validate the selection of left panel operation
	public boolean selectOperationFromLeftPanel(String operationName) {
		waitSometime();
		switch(operationName) {
		case "orders": 
			explicitWait(ordersSection);
			driver.findElement(ordersSection).click();
			break;
		}
		return true;
	}
	
	//Validate the selection of left panel sub operation
	public boolean selectSuboperationOfOrder(String subOperationName) {
		waitSometime();
		switch(subOperationName) {
		case "allInOrders": 
			explicitWait(allSectionInOrders);
			driver.findElement(allSectionInOrders).click();
			break;
		case "insurance": 
			explicitWait(insurance);
			driver.findElement(insurance).click();
			break;
		}
		return true;
	}
	
	//Validate closing of opened tabs
	public boolean closeOpenedTabs(String tabName) {
		waitSometime();
		switch(tabName) {
		case "orders": 
			driver.findElement(ordersSection).click();
			break;
		}
		return true;
	}
}
