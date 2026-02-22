package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class AdminOrderPage extends BaseTest{
	By ordersScreenIdentifier = By.xpath("//app-orders-index/nb-card/nb-card-header[contains(text(),'Orders')]");
	By orderNumberTxt = By.xpath("//app-orders-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[3]/ng2-smart-table-filter/div/default-table-filter/input-filter/input");
	By viewBtn = By.xpath("//app-orders-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr[1]/td[2]/ng2-st-tbody-custom/a/i");
	By itemOrderedHeader = By.xpath("//app-orders-view/nb-card[2]/nb-card-body/div/div[5]/table/tr[1]/th[contains(text(),'ITEMS ORDERED')]");
	By calculateReferralBtn = By.xpath("//app-orders-view/div/div/div/button[contains(text(),'Calculate Referral')]");
	By pharmacyTxt = By.xpath("//app-orders-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr[1]/td[3]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	By usernameTxt = By.xpath("//app-orders-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr[1]/td[4]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	By bellIcon = By.xpath("//app-orders-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr[1]/td[1]/ng2-st-tbody-custom/a[2]/i");
	By titleTxt = By.name("title");
	By messageTxt = By.name("message");
	By saveBtn = By.xpath("//app-push-popup/nb-card/nb-card-body/form/div[2]/div/button[contains(text(),'Save')]");
	
	public AdminOrderPage(WebDriver driver) {
		super();
	}
	//Validate the order is created or not
	public boolean verifyOrderPresent(String orderID) throws Exception{	
		explicitWait(ordersScreenIdentifier);
		driver.findElement(orderNumberTxt).sendKeys(orderID.substring(1));
		waitSometime();
		driver.findElement(viewBtn).click();
		waitForSpecificTime(3000);
		System.out.println("++++++++++++++++     "+driver.findElement(itemOrderedHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(itemOrderedHeader).getText().trim().toUpperCase(), "ITEMS ORDERED");
		return true;
	}
	
	//Validate clicking on Calculate Referral button calculates referral for order
	public boolean verifyCalculateReferralFromOrders(String orderID, String tcID, String sheet) throws Exception{	
		explicitWait(itemOrderedHeader);
		waitSometime();
		driver.findElement(calculateReferralBtn).click();
		driver.switchTo().alert().accept();
		waitForSpecificTime(1000);
		takeScreenShot(driver, "CalculateReferralFromOrders", tcID, sheet);
		Assert.assertTrue(driver.findElement(itemOrderedHeader).isDisplayed());
		return true;
	}
	
	//Validate the abandoned order is created or not
		public boolean verifyAbandonedOrder(String pharmacy, String orderPlacedBy, String title, String message, String tcID, String sheet) throws Exception{	
			explicitWait(ordersScreenIdentifier);
			if(driver.findElement(pharmacyTxt).getText().trim().equalsIgnoreCase(pharmacy) && driver.findElement(usernameTxt).getText().trim().equalsIgnoreCase(orderPlacedBy)) {
				driver.findElement(bellIcon).click();
				driver.findElement(titleTxt).clear();
				driver.findElement(titleTxt).sendKeys(title);
				driver.findElement(messageTxt).clear();
				driver.findElement(messageTxt).sendKeys(message);
				takeScreenShot(driver, "AbandonedCartReminder", tcID, sheet);
				driver.findElement(saveBtn).click();
			}
			waitForSpecificTime(3000);
			return true;
		}
}
