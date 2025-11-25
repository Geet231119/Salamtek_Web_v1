package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class AdminProfileLogoutPage extends BaseTest{
	By userProfile = By.xpath("//nb-user/div/div[2]/div[@class='user-name ng-star-inserted']");
	By logoutBtn = By.xpath("//*[@class='cdk-overlay-pane']/nb-context-menu/nb-menu/ul/li[2]/a/span");
	By loginHeader = By.id("title");
	public AdminProfileLogoutPage(WebDriver driver) {
		super();
	}
	
	//Validate admin logout
	public boolean verifyLogout() throws Exception{
		explicitWait_60(userProfile);
		clickUsingJS(userProfile);
		waitSometime();
		driver.findElement(logoutBtn).click();
		waitSometime();
		System.out.println("++++++++++++++++     "+driver.findElement(loginHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(loginHeader).getText().trim().toUpperCase(), "LOGIN");
		return true;
	}
}
