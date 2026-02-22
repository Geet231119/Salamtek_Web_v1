package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Common.BaseTest;

public class AdminProfileHomeServiceRequestPage extends BaseTest {
	By homeServiceRequestHeader = By
			.xpath("//app-home-service-request-index/nb-card/nb-card-header[contains(text(),'Home Service Request')]");
	By requestSearchTxt = By.xpath("//app-home-service-request-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[2]/ng2-smart-table-filter/div/default-table-filter/input-filter/input");
	By firstRequesterName = By.xpath("//app-home-service-request-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[2]/ng2-smart-table-cell/table-cell-view-mode/div/div");
	By firstViewBtn = By.xpath(
			"//app-home-service-request-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[1]/ng2-st-tbody-custom/a[1]/i");
	By resquestTable = By.xpath("//app-home-service-request-view/nb-card/nb-card-body/table");
	By deleteBtn = By.xpath("//app-home-service-request-view/nb-card/nb-card-header/p/a[contains(text(),'Delete')]");
	
	public AdminProfileHomeServiceRequestPage(WebDriver driver) {
		super();
	}

	// Validate home service request
	public String verifyHomeServiceRequest(String requesterName,String requestComment,
			String pageName, String tcID, String scenario) {
		String res = "";
		waitSometime();
		explicitWait(homeServiceRequestHeader);
		driver.findElement(requestSearchTxt).sendKeys(requesterName);
		waitSometime();
		if (driver.findElement(firstRequesterName).getText().trim().equalsIgnoreCase(requesterName)) {
			driver.findElement(firstViewBtn).click();
			waitForSpecificTime(3000);
			explicitWait(resquestTable);
			WebElement table = driver.findElement(resquestTable);
			List<WebElement> value = table.findElements(By.tagName("td"));
			for(WebElement v: value) {
				if(v.getText().equalsIgnoreCase(requestComment))
					res = v.getText();
			}
		}
		driver.findElement(deleteBtn).click();
		driver.switchTo().alert().accept();
		waitSometime();
		waitForPageLoad();
		return res;
	}
}
