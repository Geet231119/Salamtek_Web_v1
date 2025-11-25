package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Common.BaseTest;

public class DoctorListingPage extends BaseTest {
	By findADoctorLnk = By
			.xpath("//app-sub-menu/div[1]/div/div/nav/div/div[2]/ul/li[1]/a[contains(text(),'FIND A DOCTOR')]");
	By inNetworkDoctorsLbl = By.xpath(
			"//app-doctor-listing/section/div/div[3]/div/div/div[4]/div[1]/p[contains(text(),'IN-NETWORK DOCTORS')]");
	By bookAnAppointmentBtn = By.xpath(
			"//app-doctor-profile/div/div[2]/div/div[3]/div/div[3]/button[contains(text(),'BOOK AN APPOINTMENT')]");
	By scopeOfClinicalPracticeHeader = By
			.xpath("//app-doctor-profile/div/div[2]/div/div[4]/div/h4[contains(text(),'Scope Of Clinical Practice')]");
	By priceSlider = By.id("myinput");
	By findDoctorLbl = By.xpath("//*[@id=\"nav-tabContent\"]/div[2]/div/div[1]/h2/span[contains(text(),'DOCTOR')]");

	public DoctorListingPage(WebDriver driver) {
		super();
	}

	// Select doctor from Doctor's Listing Page
	public boolean selectDoctorFromDoctorListing(String doctor, String tcID, String sheetName) throws Exception {
		explicitWait(inNetworkDoctorsLbl);
		List<WebElement> doctorList = driver.findElements(By.xpath("//app-doctor/div/div"));
		int doctorListCnt = doctorList.size();
		System.out.println("Count of the doctors in the list: " + doctorListCnt);
		for (int i = 1; i <= doctorListCnt; i++) {
			By doctorName = By.xpath("//app-doctor/div/div[" + i + "]/div/div/div[4]/div/div/div[1]/div[1]/div[1]/h2");
			String doctorNameTxt = driver.findElement(doctorName).getText().trim();
			if (doctorNameTxt.contains(doctor.trim())) {
				takeScreenShot(getDriver(), "DoctorInDoctorListing", tcID, sheetName);
				driver.findElement(By.xpath("//app-doctor/div/div[" + i + "]/div/div/div[2]/img")).click();
				break;
			}
			if (i % 3 == 0) {
				scrollTillASpecificPoint(300);
				waitSometime();
			}
		}
		waitForSpecificTime(4000);
		explicitWait(scopeOfClinicalPracticeHeader);
		System.out.println("++++++++++++++++     "
				+ driver.findElement(scopeOfClinicalPracticeHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(scopeOfClinicalPracticeHeader).getText().trim().toUpperCase(),
				"SCOPE OF CLINICAL PRACTICE", "SCOPE OF CLINICAL PRACTICE is not present");
		return true;
	}

	// Select doctor from Doctor's Listing Page through filters
	public boolean filterDoctors(String filterType, String filterValue, String filterResult, String tcID,
			String sheetName) throws Exception {
		boolean result = false;
		driver.navigate().refresh();
		waitForPageLoad();
		explicitWait(inNetworkDoctorsLbl);
		waitSometime();
		if (filterType.equalsIgnoreCase("Price")) {
			scrollTillASpecificPoint(400);
			waitSometime();
			try {
				WebElement priceSliderEle = driver.findElement(priceSlider);
				Actions builder = new Actions(driver);
				builder.moveToElement(priceSliderEle).click().dragAndDropBy(priceSliderEle, 5, 0).build().perform();
				builder.moveToElement(priceSliderEle).click().dragAndDropBy(priceSliderEle, -20, 0).build().perform();
			} catch (JavascriptException e) {
				System.out.println("Exception in finding the element:" + e.getMessage());
			}
			waitSometime();
			takeScreenShot(getDriver(), "Selected_" + filterType, tcID, sheetName);
		} else {
			if (filterType.equalsIgnoreCase("Gender") || filterType.equalsIgnoreCase("Availability")
					|| filterType.equalsIgnoreCase("Time Slot") || filterType.equalsIgnoreCase("Location")
					|| filterType.equalsIgnoreCase("Ratings")) {
				moveToElement(driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")));
			}
			if (driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).getText().trim()
					.equalsIgnoreCase(filterType)) {
				moveToElement(driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")));
				driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).click();
			}
		}
		waitSometime();
		if (filterType.equalsIgnoreCase("Symptoms")) {
			getFilterTypeOptions("//*[@id=\"panelsStayOpen-collapseOne21\"]/div/div/div", filterValue, filterType, tcID,
					sheetName);
		} else if (filterType.equalsIgnoreCase("Services")) {
			getFilterTypeOptions("//*[@id=\"panelsStayOpen-collapseOne22\"]/div/div/div", filterValue, filterType, tcID,
					sheetName);
		} else if (filterType.equalsIgnoreCase("Insurances")) {
			getFilterTypeOptions("//*[@id=\"panelsStayOpen-collapseOne23\"]/div/div/div", filterValue, filterType, tcID,
					sheetName);
		} else if (filterType.equalsIgnoreCase("Languages")) {
			getFilterTypeOptions("//*[@id=\"panelsStayOpen-collapseOne24\"]/div/div/div", filterValue, filterType, tcID,
					sheetName);
		} else if (filterType.equalsIgnoreCase("Gender")) {
			moveToElement(
					driver.findElement(By.xpath("//*[@id=\"panelsStayOpen-collapseOne25\"]/div/div/div[2]/div/label")));
			waitSometime();
			if (driver.findElement(By.xpath("//*[@id=\"panelsStayOpen-collapseOne25\"]/div/div/div[1]/div/label"))
					.getText().trim().equalsIgnoreCase(filterValue.trim())) {
				driver.findElement(By.xpath("//*[@id=\"panelsStayOpen-collapseOne25\"]/div/div/div[1]/div/label"))
						.click();
			} else {
				driver.findElement(By.xpath("//*[@id=\"panelsStayOpen-collapseOne25\"]/div/div/div[2]/div/label"))
						.click();
			}
		} else if (filterType.equalsIgnoreCase("Availability")) {
			getFilterTypeOptions("//*[@id=\"panelsStayOpen-collapseOne26\"]/div/div/div", filterValue, filterType, tcID,
					sheetName);
		} else if (filterType.equalsIgnoreCase("Time Slot")) {
			getFilterTypeOptions("//*[@id=\"panelsStayOpen-collapseOne27\"]/div/div/div", filterValue, filterType, tcID,
					sheetName);
		}

		else if (filterType.equalsIgnoreCase("Location")) {
			getFilterTypeOptions("//*[@id=\"panelsStayOpen-collapseOne28\"]/div/div/div", filterValue, filterType, tcID,
					sheetName);
		}

		else if (filterType.equalsIgnoreCase("Ratings")) {
			waitSometime();
			moveToElement(driver.findElement(By.xpath("//input[@value='" + filterValue + "']")));
			driver.findElement(By.xpath("//input[@value='" + filterValue + "']")).click();
			takeScreenShot(getDriver(), "Selected_" + filterType, tcID, sheetName);
		}
		waitForSpecificTime(7000);
		// scrollTillASpecificPoint(-500);
		moveToElement(driver.findElement(findDoctorLbl));
		waitSometime();
		if (filterType.equalsIgnoreCase("Price")) {
			int counter = 0;
			for (int i = 1; i <= 5; i++) {
				Double actualPrice = Double
						.parseDouble(driver
								.findElement(By.xpath("//app-doctor/div/div[" + i
										+ "]/div/div/div[4]/div/div/div[3]/div/div[1]/button/span[2]"))
								.getText().trim());
				Double expectedPrice = Double.parseDouble(filterResult);
				if (actualPrice <= expectedPrice) {
					counter++;
				}
				if (i % 3 == 0) {
					scrollTillASpecificPoint(700);
					waitSometime();
				}
			}
			if (counter == 5) {
				takeScreenShot(getDriver(), "FilterResult_" + filterResult, tcID, sheetName);
				result = true;
			} else {
				result = false;
			}
		} else {
			moveToElement(driver.findElement(By.xpath("//h2[contains(text(),'" + filterResult + "')]")));
			String doctorNameTxt = driver.findElement(By.xpath("//h2[contains(text(),'" + filterResult + "')]"))
					.getText().trim();
			System.out.println("Doctor Name: "+ doctorNameTxt);
			System.out.println("Doctor Name: "+ filterResult.trim());
			if (doctorNameTxt.contains(filterResult.trim())) {
				takeScreenShot(getDriver(), "Filter_" + filterType + "_" + filterResult, tcID, sheetName);
				result = true;
			}
		}
		waitSometime();
		return result;
	}

	public void getFilterTypeOptions(String path, String filterValue, String filterType, String tcID,
			String sheetName) {
		List<WebElement> options = driver.findElements(By.xpath(path));
		for (int i = 1; i <= options.size(); i++) {
			moveToElement(driver.findElement(By.xpath(path + "[" + i + "]/div/label")));
			if (driver.findElement(By.xpath(path + "[" + i + "]/div/label")).getText().trim()
					.equalsIgnoreCase(filterValue)) {
				driver.findElement(By.xpath(path + "[" + i + "]/div/label")).click();
				takeScreenShot(getDriver(), "Selected_" + filterType, tcID, sheetName);
				break;
			}
			if (i % 4 == 0) {
				scrollTillASpecificPoint(300);
				waitSometime();
			}
		}
	}
}
