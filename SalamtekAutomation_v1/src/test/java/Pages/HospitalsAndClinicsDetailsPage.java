package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Common.BaseTest;

public class HospitalsAndClinicsDetailsPage extends BaseTest{
	By bookAnAppointmentBtn = By.xpath("//app-clinic-profile/div[1]/div[2]/div[3]/div/div[3]/button[contains(text(),'BOOK AN APPOINTMENT')]");
	By specialtiesDrpDwn = By.xpath("//app-clinic-details/section/div[5]/div/div/div/div/div[1]/div/select");
	By doctorBookAnAppointmentBtn = By.xpath("//app-doctor-profile/div/div[2]/div/div[3]/div/div[3]/button[contains(text(),'BOOK AN APPOINTMENT')]");
	
	//Elements in Filter
	By manufacturesSearchTxt = By.xpath("//app-product-listing/section/div/div[2]/div/div[3]/div/div[1]/div[2]/div[2]/div[4]/div/div/div/div/div[1]/input");
	
	public HospitalsAndClinicsDetailsPage(WebDriver driver) 
	{
		super();
	}
	
	//Validate selection of doctor from Clinic
	public boolean selectDoctorFromClinic(String serviceProviderName, String specialties, String doctorName, String tcID, String scenario) {
		waitForSpecificTime(5000);
		By clinicHeader = By.xpath("//app-clinic-profile/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div[1]/h2[contains(text(),'" + serviceProviderName + "')]");
		explicitWait(clinicHeader);
		scrollTillASpecificPoint(200);
		waitSometime();
		WebElement bookAnAppointmentBtnEle = driver.findElement(bookAnAppointmentBtn); 
		scrollTillElementVisible(bookAnAppointmentBtnEle);
		waitSometime();
		Select specialtiesList = new Select(driver.findElement(specialtiesDrpDwn));
		specialtiesList.selectByVisibleText(specialties);
		waitSometime();
		if(driver.findElement(By.xpath("//app-clinic-details/section/div[5]/div/div/div/div/div[2]/div[1]/h3")).getText().toUpperCase().trim().contains(specialties.toUpperCase().trim())) {
			List<WebElement> doctors = driver.findElements(By.xpath(
					"//app-clinic-details/section/div[5]/div/div/div/div/div[2]/div"));
			for (int i = 3; i <= doctors.size(); i++) {
				if (driver.findElement(By.xpath(
						"//app-clinic-details/section/div[5]/div/div/div/div/div[2]/div["
								+ i + "]/span/app-new-doc-card/div/div/div[3]/div/div/div[1]/div[1]/div/h2"))
						.getText().trim().equalsIgnoreCase(doctorName)) {
					moveToElement(driver.findElement(By.xpath(
							"//app-clinic-details/section/div[5]/div/div/div/div/div[2]/div["
									+ i + "]/span/app-new-doc-card/div/div/div[3]/div/div/div[3]/div/button[1]")));
					driver.findElement(By.xpath(
							"//app-clinic-details/section/div[5]/div/div/div/div/div[2]/div["
									+ i + "]/span/app-new-doc-card/div/div/div[3]/div/div/div[3]/div/button[1]"))
							.click();
					waitSometime();
					takeScreenShot(getDriver(), "Selected_" + doctorName, tcID, scenario);
					break;
				}
				if (i % 3 == 0) {
					scrollTillASpecificPoint(100);
					waitSometime();
				}
			}
		}
		waitSometime();
		System.out.println(
				"++++++++++++++++     " + driver.findElement(doctorBookAnAppointmentBtn).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(doctorBookAnAppointmentBtn).getText().trim().toUpperCase(),
				"BOOK AN APPOINTMENT", "BOOK AN APPOINTMENT is not present");
		return true;
	}
	
	
	
}
