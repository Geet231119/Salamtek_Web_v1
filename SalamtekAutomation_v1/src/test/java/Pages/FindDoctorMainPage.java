package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class FindDoctorMainPage extends BaseTest{
	By showMeAllDoctorListBtn = By.xpath("//app-doctor-home/section/div[1]/div/div/div/div/div[2]/div[4]/div/button[contains(text(),'SHOW ME ALL DOCTOR LIST')]");
	By inNetworkDoctorsLbl = By.xpath("//app-doctor-listing/section/div/div[3]/div/div/div[4]/div[1]/p[contains(text(),'IN-NETWORK DOCTORS')]");
	
	public FindDoctorMainPage(WebDriver driver) 
	{
		super();
	}
	
	//select Show All Doctors List from Find doctor Main page
		public boolean selectShowAllDoctorsList() throws Exception{
			waitSometime();
			moveToElement(driver.findElement(showMeAllDoctorListBtn));
			explicitWait(showMeAllDoctorListBtn);
			driver.findElement(showMeAllDoctorListBtn).click();
			waitSometime();
			System.out.println("++++++++++++++++     "+driver.findElement(inNetworkDoctorsLbl).getText().trim().toUpperCase());
			Assert.assertEquals(driver.findElement(inNetworkDoctorsLbl).getText().trim().toUpperCase(), "IN-NETWORK DOCTORS", "IN-NETWORK DOCTORS is not present");
			return true;
		}
}
