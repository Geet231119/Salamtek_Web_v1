package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.BaseTest;

public class AdminProfileUserCustomerPage extends BaseTest{
	By usersHeader = By.xpath("//app-user-index/nb-card/nb-card-header[contains(text(),'Users')]");
	By emailTxt = By.xpath("//app-user-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[4]/ng2-smart-table-filter/div/default-table-filter/input-filter/input");
	By emailLbl = By.xpath("//app-user-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[4]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	By viewBtn = By.xpath("//app-user-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[1]/ng2-st-tbody-custom/a[1]/i");
	By walletAmount = By.xpath("//app-user-view/nb-card/nb-card-body[1]/table/tr[10]/td");
	By addBalanceBtn = By.xpath("//app-user-view/nb-card/nb-card-header/p/a[contains(text(),'Add Balance')]");
	By addWalletBalanceHeader = By.xpath("//app-user-wallet/nb-card/nb-card-header[contains(text(),'Add Wallet Balance')]");
	By amountTxt = By.name("amount");
	By saveBtn = By.xpath("//app-user-wallet/nb-card/nb-card-body/form/div[2]/div/button[contains(text(),'Save')]");
	
	public AdminProfileUserCustomerPage(WebDriver driver) 
	{
		super();
	}
	
	//Validate reports under Reports -> Account Statement
	public String verifyAddingWalletMoney(String username, String walletRechargeAmount, String tcID, String scenario) {
		String result = "";
		double firstWalletAmount = 0.0;
		double lastWalletAmount = 0.0;
		double expectedWalletAmount = 0.0;
		waitSometime();
		explicitWait(usersHeader);
		waitSometime();
		driver.findElement(emailTxt).sendKeys(username);
		waitSometime();
		if(driver.findElement(emailLbl).getText().trim().equalsIgnoreCase(username.trim())) {
			driver.findElement(viewBtn).click();
			waitForSpecificTime(5000);
			String[] firstAmount = driver.findElement(walletAmount).getText().trim().split(" ");
			firstWalletAmount = Double.parseDouble(firstAmount[0]);
			driver.findElement(addBalanceBtn).click();
			explicitWait(addWalletBalanceHeader);
			driver.findElement(amountTxt).sendKeys(walletRechargeAmount);
			driver.findElement(saveBtn).click();
			waitForSpecificTime(5000);
			String[] lastAmount = driver.findElement(walletAmount).getText().trim().split(" ");
			lastWalletAmount = Double.parseDouble(lastAmount[0]);
			expectedWalletAmount = firstWalletAmount + Double.parseDouble(walletRechargeAmount);
			if(expectedWalletAmount == lastWalletAmount) {
				result = firstAmount[0]+" "+lastAmount[0];
				takeScreenShot(driver, "Reports", tcID, scenario);
			}
			else {
				result = "false";
			}
		}
		else {
			result = "false";
		}
		return result;
	}
}
