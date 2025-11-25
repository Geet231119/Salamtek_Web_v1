package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Common.BaseTest;
import Utils.ExcelUtils;

public class AdminReferralReportPage extends BaseTest{
	ExcelUtils excelUtils = new ExcelUtils();
	
	
	By nameFilter = By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[3]/ng2-smart-table-filter/div/default-table-filter/input-filter/input");
	By referralCalculationHeader = By.xpath("//app-report-referral-calculation/nb-card/nb-card-header[contains(text(),'Referral Calculation Report')]");
	By startDate = By.name("startDate");
	By endDate = By.name("endDate");
	By searchBtn = By.xpath("//app-report-referral-calculation/nb-card/nb-card-body[1]/form/input[@value='search']");
	By referralsHeader = By.xpath("//app-referral-index/nb-card/nb-card-header[contains(text(),'Referrals')]");
	By referralFrom = By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[2]/ng2-smart-table-filter/div/default-table-filter/select-filter/select");
	By referralTo = By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[4]/ng2-smart-table-filter/div/default-table-filter/select-filter/select");
	Double expectedAmount;
	Double actualAmount;
	
	public AdminReferralReportPage(WebDriver driver) {
		super();
	}
	
	//Validate referral calculation in Referral Calculation report
	public String verifyReferralCalculationInReport(String orderID, String doctorName, String referredLab, String referralCommission, String totalAmount, String tcID, String sheetName) throws Exception{	
		String amounts="";
		waitForSpecificTime(5000);
		explicitWait(referralCalculationHeader);
		driver.findElement(startDate).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3)+" "+getDayValue(getTodayDateInYYYYMMDD())+", "+getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(endDate).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3)+" "+getDayValue(getTodayDateInYYYYMMDD())+", "+getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(searchBtn).click();
		waitSometime();
		expectedAmount = roundNumberTo1Decimal(Double.parseDouble(totalAmount) * (Double.parseDouble(referralCommission)/100));
		System.out.println("Expected Referral Commission: "+ expectedAmount);
		 List<WebElement> referralList = driver.findElements(By.xpath("//app-report-referral-calculation/nb-card/nb-card-body[2]/div[2]/div/ng2-smart-table/table/tbody/tr"));
		 System.out.println("Size of the report: "+ referralList.size());
		 for(int i=1;i<=referralList.size();i++) {
			 if(driver.findElement(By.xpath("//app-report-referral-calculation/nb-card/nb-card-body[2]/div[2]/div/ng2-smart-table/table/tbody/tr["+i+"]/td[3]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText().contains(referredLab) && driver.findElement(By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/app-report-referral-calculation/nb-card/nb-card-body[2]/div[2]/div/ng2-smart-table/table/tbody/tr["+i+"]/td[6]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText().equalsIgnoreCase(doctorName.substring(3).trim())) {							 
				 Assert.assertTrue(driver.findElement(By.xpath("//app-report-referral-calculation/nb-card/nb-card-body[2]/div[2]/div/ng2-smart-table/table/tbody/tr["+i+"]/td[2]/ng2-smart-table-cell/table-cell-view-mode/div/div")).isDisplayed());
				 actualAmount = roundNumberTo1Decimal(Double.parseDouble(driver.findElement(By.xpath("//app-report-referral-calculation/nb-card/nb-card-body[2]/div[2]/div/ng2-smart-table/table/tbody/tr["+i+"]/td[4]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText()));
				 System.out.println("Actual Referral Amount: "+actualAmount);
				 break;
			 }
		 }
		 takeScreenShot(getDriver(), "ReferralCommissionInReport", tcID,sheetName);
		 if(Double.compare(expectedAmount,actualAmount) == 0) {
			amounts = actualAmount.toString();
		 }
		return amounts;
	}
	
	//Validate referral calculation in Referral Calculation report (For Salamtek)
	public String verifyReferralCalculationInReportForSalamtek(String orderID, String referred, String referralCommission, String totalAmount, String tcID, String sheetName) throws Exception{	
		String amounts="";
		waitForSpecificTime(5000);
		explicitWait(referralCalculationHeader);
		driver.findElement(startDate).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3)+" "+getDayValue(getTodayDateInYYYYMMDD())+", "+getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(endDate).sendKeys(getMonthFromToday(getTodayDate()).substring(0, 3)+" "+getDayValue(getTodayDateInYYYYMMDD())+", "+getCurrentYear(getTodayDateInYYYYMMDD()));
		driver.findElement(searchBtn).click();
		waitSometime();
		expectedAmount = roundNumberTo1Decimal(Double.parseDouble(totalAmount) * (Double.parseDouble(referralCommission)/100));
		System.out.println("Expected Referral Commission: "+ expectedAmount);
		 List<WebElement> referralList = driver.findElements(By.xpath("//app-report-referral-calculation/nb-card/nb-card-body[2]/div[2]/div/ng2-smart-table/table/tbody/tr"));
		 System.out.println("Size of the report: "+ referralList.size());
		 for(int i=1;i<=referralList.size();i++) {
			 if(driver.findElement(By.xpath("//app-report-referral-calculation/nb-card/nb-card-body[2]/div[2]/div/ng2-smart-table/table/tbody/tr["+i+"]/td[3]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText().contains(referred) && driver.findElement(By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/app-report-referral-calculation/nb-card/nb-card-body[2]/div[2]/div/ng2-smart-table/table/tbody/tr["+i+"]/td[5]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText().equalsIgnoreCase("Salamtek")) {							 
				 Assert.assertTrue(driver.findElement(By.xpath("//app-report-referral-calculation/nb-card/nb-card-body[2]/div[2]/div/ng2-smart-table/table/tbody/tr["+i+"]/td[2]/ng2-smart-table-cell/table-cell-view-mode/div/div")).isDisplayed());
				 actualAmount = roundNumberTo1Decimal(Double.parseDouble(driver.findElement(By.xpath("//app-report-referral-calculation/nb-card/nb-card-body[2]/div[2]/div/ng2-smart-table/table/tbody/tr["+i+"]/td[4]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText()));
				 System.out.println("Actual Referral Amount: "+actualAmount);
				 break;
			 }
		 }
		 takeScreenShot(getDriver(), "ReferralCommissionInReport", tcID,sheetName);
		 if(Double.compare(expectedAmount,actualAmount) == 0) {
			amounts = actualAmount.toString();
		 }
		return amounts;
	}
	
	//Validate referralComission under Setting -> Referrals
	public String verifyReferralCommission(String doctorName, String doctorSearchName, String referredPharmacy, String referredLab, String tcID, String sheetName) throws Exception{
		waitSometime();
		String referralCommission="";
		String referred = "";
		explicitWait(referralsHeader);
		driver.findElement(nameFilter).sendKeys(doctorSearchName);
		waitSometime();
		waitSometime();
		Select referralTo1 = new Select(driver.findElement(referralTo));
		if(!referredPharmacy.equals("")) {
			referralTo1.selectByValue("S");
			referred = referredPharmacy;
		}
		else {
			referralTo1.selectByValue("L");
			referred = referredLab;
		}
		waitSometime();
		List<WebElement> reportList1 = driver.findElements(By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr"));
		System.out.println("Size of the report: "+ reportList1.size());
		for(int i=1;i<=reportList1.size();i++) {
			if(driver.findElement(By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr["+i+"]/td[3]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText().contains(doctorName.substring(3).trim()) && driver.findElement(By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr["+i+"]/td[5]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText().contains(referred)) {
				Assert.assertTrue(driver.findElement(By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr["+i+"]/td[3]/ng2-smart-table-cell/table-cell-view-mode/div/div")).isDisplayed());
				referralCommission = driver.findElement(By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr["+i+"]/td[8]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText();
				System.out.println("Referral Commission is: "+ referralCommission);
				break;
			}
			if(i%5 == 0) {
				scrollTillASpecificPoint(400);
				waitSometime();
			}
			
		}
		 takeScreenShot(getDriver(), "ReferralCommissionFromSettings", tcID,sheetName);
		 return referralCommission;
	}
	
	//Validate referralComission under Setting -> Referrals (For Salamtek)
	public String verifyReferralCommissionForSalamtek(String referredDoctor, String referredPharmacy, String referredLab, String tcID, String sheetName) throws Exception{
		waitSometime();
		String referred = "";
		String referralCommission="";
		explicitWait(referralsHeader);
		Select referralFrom1 = new Select(driver.findElement(referralFrom));
		referralFrom1.selectByValue("A");
		waitSometime();
		Select referralTo1 = new Select(driver.findElement(referralTo));
		if(!referredPharmacy.equals("")) {
			referralTo1.selectByValue("S");
			referred = referredPharmacy;
		}
		else if(!referredDoctor.equals("")) {
			referralTo1.selectByValue("D");
			referred = referredDoctor;
		}
		else {
			referralTo1.selectByValue("L");
			referred = referredLab;
		}
		waitSometime();
		List<WebElement> reportList1 = driver.findElements(By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr"));
		System.out.println("Size of the report: "+ reportList1.size());
		for(int i=1;i<=reportList1.size();i++) {
			if(driver.findElement(By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr["+i+"]/td[5]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText().contains(referred)) {
				Assert.assertTrue(driver.findElement(By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr["+i+"]/td[3]/ng2-smart-table-cell/table-cell-view-mode/div/div")).isDisplayed());
				referralCommission = driver.findElement(By.xpath("//app-referral-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr["+i+"]/td[8]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText();
				System.out.println("Referral Commission is: "+ referralCommission);
				break;
			}
		}
		 takeScreenShot(getDriver(), "ReferralCommissionFromSettings", tcID,sheetName);
		 return referralCommission;
	}
}
