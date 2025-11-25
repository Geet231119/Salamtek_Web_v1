package Pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.BaseTest;

public class BookAppointmentPage extends BaseTest {

	// Find A Doctor elements

	By bookAnAppointmentBtn = By.xpath("//button[contains(text(),'BOOK AN APPOINTMENT')]");
	By inPersonBtn = By.xpath("//button[contains(text(),'In Person Consultation')]");
	By videoConsultationBtn = By.xpath("//button[contains(text(),'Video Consultation')]");
	By medicalProcedureBtn = By.xpath("//button[contains(text(),'Medical Procedures')]");
	By homeServiceBtn = By.xpath("//button[contains(text(),'Home Service')]");
	By notAvailable = By.xpath("//p[contains(text(),'No slots available for today')]");
	By bookAnAppointmentNowBtn = By.xpath("//button[2]/span/span[contains(text(),'BOOK AN APPOINTMENT NOW')]");
	By timeScheduleTxt = By.xpath("//p[contains(text(),'Time Schedule')]");
	By selectATime = By.xpath("//span[contains(text(),'Select Your Appointment Time')]");

	// Medical service elements
	By bookMedicalServicesLnk = By
			.xpath("//app-sub-menu/div[1]/div/div/nav/div/div[2]/ul/li[3]/a[contains(text(),'BOOK MEDICAL SERVICES')]");
	By whatAreYouLookingLbl = By.xpath("//app-medical-home/section/div[1]/div[2]/div/div/div/div/div[1]/div/h2/span");
	By mostUsedServicesHeader = By
			.xpath("//app-medical-home/section/div[2]/div[1]/div/div/div/div/h2[contains(text(),'MOST USED')]");
	By showMeMoreBtn = By.xpath(
			"//app-medical-home/section/div[2]/div[1]/div/div/div/div/div[2]/div/div/div/button[contains(text(),'SHOW ME MORE')]");
	By numberOfTestsHeader = By.xpath(
			"//app-lab-listing/section/div/div[3]/div/div[2]/div[2]/div[1]/div[1]/p[4]/span[contains(text(),'NUMBER OF TESTS')]");
	By checkOutOurServiceHeader = By.xpath("//app-medical-home/section/div[2]/div[2]/div/div[1]/h2");
	By dontMissHeader = By.xpath("//span[contains(text(),'MISS IT')]");
	By numberOfCentersHeader = By
			.xpath("//app-lab-listing/section/div/div[3]/div/div[2]/div[2]/div[1]/div[1]/p[2]/span");
	By checkAllServicesBtn = By.id("ALLTEST-tab");
	By showMeAllProvidersBtn = By.id("ALLLABORATORY-tab");
	By categoryFilterBtn = By.xpath(
			"//app-lab-listing/section/div/div[3]/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/div/div/h2/button[contains(text(),'Categories')]");
	By profileServiceLnk = By.id("profileTestTab");
	By individualServiceLnk = By.id("individualTestTab");
	By profileServiceElement = By.xpath("//*[@id=\"profileTest\"]/div/div/div/div[1]/div");
	By outerDivEle = By.xpath("//*[@id=\"profileTest\"]/div/div/div/div[1]/div");
	By profileTestElement = By.xpath("//div[@aria-labelledby=\"panelsStayOpen-headingOne\"]/div");
	By profile_noOfSelectedServiceTxt = By.xpath("//h3[contains(text(),'NUMBER OF SELECTED SERVICES:')]");
	By individual_noOfSelectedServiceTxt = By.xpath("//h3[contains(text(),'NUMBER OF SELECTED SERVICES:')]");
	By profileBookAnAppointmentNow = By.xpath("//button[contains(text(),'BOOK AN APPOINTMENT NOW')]");
	By individualBookAnAppointmentNow = By.xpath("//button[contains(text(),'BOOK AN APPOINTMENT NOW')]");
	By chooseServiceTxt = By.xpath("//p[contains(text(),'Choose the Service that fits you')]");
	By msBookAnAppointmentNowBtn = By.xpath("//button/span/span[contains(text(),'BOOK AN APPOINTMENT NOW')]");
	By bookingStatementHeader = By.xpath("//h4[contains(text(),'BOOKING STATEMENT')]");
	By walkInHeader = By.xpath(
			"//app-lab-book/section[1]/div/div/div[5]/div/div[1]/div/h4[contains(text(),'DO YOU WANT TO WALK IN?')]");
	By walkInChkBx = By.xpath("//app-lab-book/section[1]/div/div/div[5]/div/div[3]/div/span/input");
	By buyNowBtn = By.xpath("//app-lab-book/section[1]/div/div/div[6]/div[2]/span/button/span/span[3]");

	// ProfileService elements (Med White Lab)
	By p_laboratoryLnk = By.xpath("//*[@id=\"profileTest\"]/div/div/div/div[1]/div[1]/div/h3/button");
	By p_biochemistryLnk = By.xpath("//*[@id=\"profileTest\"]/div/div/div/div[1]/div[2]/div/h3/button");
	By p_haematologyLnk = By.xpath("//*[@id=\"profileTest\"]/div/div/div/div[1]/div[3]/div/h3/button");
	By p_radiologyLnk = By.xpath("//*[@id=\"profileTest\"]/div/div/div/div[1]/div[2]/div/h3/button");
	By p_laboratoryOuterDiv = By.xpath("//*[@id=\"collapse0\"]/div");
	By p_biochemistryOuterDiv = By.xpath("//*[@id=\"collapse1\"]/div");
	By p_haematologyOuterDiv = By.xpath("//*[@id=\"collapse2\"]/div");
	By p_radiologyOuterDiv = By.xpath("//*[@id=\"collapse1\"]/div");

	// individualService elements (Med White Lab)
	By laboratoryLnk = By.xpath(
			"//*[@id=\"individualTest\"]/div/div/div/div/div/div[@id=\"accordionPanelsStayOpenExample\"][1]/div/h3/button");
	By biochemistryLnk = By.xpath(
			"//*[@id=\"individualTest\"]/div/div/div/div/div/div[@id=\"accordionPanelsStayOpenExample\"][2]/div/h3/button");
	By haematologyLnk = By.xpath(
			"//*[@id=\"individualTest\"]/div/div/div/div/div/div[@id=\"accordionPanelsStayOpenExample\"][3]/div/h3/button");
	By laboratoryOuterDiv = By.xpath("//*[@id=\"collapse0\"]/div/div/div");
	By biochemistryOuterDiv = By.xpath("//*[@id=\"collapse1\"]/div/div/div");
	By haematologyOuterDiv = By.xpath("//*[@id=\"collapse2\"]/div/div/div");

	int profileCount;

	public BookAppointmentPage(WebDriver driver) {
		super();
	}

	// Validate selection of service from Book Medical Services -> Most Used
	// Services
	public boolean selectServiceFromMainPage(String sectionName, String service) {
		waitSometime();
		if (sectionName.equalsIgnoreCase("MostUsedServices")) {
			explicitWait(mostUsedServicesHeader);
			waitSometime();
			moveToElement(driver.findElement(showMeMoreBtn));
			moveToElement(driver.findElement(checkOutOurServiceHeader));
			driver.findElement(showMeMoreBtn).click();
			waitSometime();
			System.out.println("++++++++++++++++     " + driver.findElement(numberOfTestsHeader).getText());
			Assert.assertEquals(driver.findElement(numberOfTestsHeader).getText(), "NUMBER OF TESTS");
			return true;
		} else {
			waitSometime();
			moveToElement(driver.findElement(checkOutOurServiceHeader));
			waitSometime();
			moveToElement(driver.findElement(dontMissHeader));
			if (driver.findElement(By.xpath("//h2[contains(text(),'" + service + "')]")).getText().trim()
					.equalsIgnoreCase(service)) {
				driver.findElement(By.xpath("//h2[contains(text(),'" + service + "')]")).click();
			}
			waitSometime();
			System.out.println("++++++++++++++++     " + driver.findElement(numberOfCentersHeader).getText());
			Assert.assertEquals(driver.findElement(numberOfCentersHeader).getText(), "NUMBER OF CENTERS");
			return true;
		}
	}

	// Validate filtering of services (Book Medical Services)
	public boolean filterServices(String sectionName, String filterType, String filterValue, String filterResult,
			String pageName, String tcID, String scenario) {
		boolean result = false;
		waitSometime();
		if (sectionName.equalsIgnoreCase("checkOutOurServices")) {
			explicitWait(checkAllServicesBtn);
			driver.findElement(checkAllServicesBtn).click();
			waitSometime();
		}
		moveToElement(driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")));
		if (driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).getText().trim()
				.equalsIgnoreCase(filterType)) {
			driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).click();
		}
		waitSometime();
		moveToElement(driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")));
		if (driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")).getText().trim()
				.equalsIgnoreCase(filterValue)) {
			driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")).click();
		}
		waitSometime();
		takeScreenShot(getDriver(), pageName + "_" + filterType, tcID, scenario);
		if (driver.findElement(By.xpath("//h2[contains(text(),'" + filterResult + "')]")).getText().trim()
				.equalsIgnoreCase(filterResult)) {
			result = true;
		}
		return result;
	}

	// Validate filtering of centers (Book Medical Services)
	public boolean filterCenters(String sectionName, String filterType, String filterValue, String filterResult,
			String pageName, String tcID, String scenario) {
		boolean result = false;
		waitSometime();
		if (sectionName.equalsIgnoreCase("MostUsedServices")) {
			explicitWait(showMeAllProvidersBtn);
			driver.findElement(showMeAllProvidersBtn).click();
			waitSometime();
		}
		
		//selecting filter type from the filter left pane 
		moveToElement(driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")));
		if (driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).getText().trim()
				.equalsIgnoreCase(filterType)) {
			driver.findElement(By.xpath("//button[contains(text(),'" + filterType + "')]")).click();
		}
		waitSometime();
		if (filterType.equalsIgnoreCase("Services")||filterType.equalsIgnoreCase("Insurances")||filterType.equalsIgnoreCase("Location")) {
			moveToElement(driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")));
			if (driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")).getText().trim()
					.equalsIgnoreCase(filterValue)) {
				driver.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]")).click();
			}
		} else if (filterType.equalsIgnoreCase("Ratings")) {
			moveToElement(driver.findElement(By.xpath("//input[@value='"+ filterValue + "']")));
			driver.findElement(By.xpath("//input[@value='"+ filterValue + "']"))
					.click();
		}
		scrollTillASpecificPoint(300);
		waitSometime();
		takeScreenShot(getDriver(), sectionName + "_" + pageName + "_" + filterType, tcID, scenario);
		List<WebElement> filterResults = driver.findElements(By.xpath(
				"//app-lab-listing/section/div/div[3]/div/div[2]/div[2]/div[3]/div/div[3]/div[1]/div[2]/div/div[1]/div/div/div"));
		for (int i = 1; i <= filterResults.size(); i++) {
			if (driver.findElement(By.xpath(
					"//app-lab-listing/section/div/div[3]/div/div[2]/div[2]/div[3]/div/div[3]/div[1]/div[2]/div/div[1]/div/div/div["
							+ i + "]/app-new-lab-single/div/div/div[4]/h3"))
					.getText().trim().equalsIgnoreCase(filterResult)) {
				result = true;
				break;
			}
		}
		return result;
	}

	// Validate service selection for Doctor/Lab
	public boolean selectAndBookDoctorAppointment(String serviceName) {
		explicitWait(bookAnAppointmentBtn);
		driver.findElement(bookAnAppointmentBtn).click();
		explicitWait(timeScheduleTxt);
		switch (serviceName) {
		case "In Person Consultation":
			explicitWait(inPersonBtn);
			driver.findElement(inPersonBtn).click();
			break;
		case "Video Consultation":
			explicitWait(videoConsultationBtn);
			driver.findElement(videoConsultationBtn).click();
			break;
		case "Medical Procedure":
			explicitWait(medicalProcedureBtn);
			driver.findElement(medicalProcedureBtn).click();
			break;
		case "Home Service":
			explicitWait(homeServiceBtn);
			driver.findElement(homeServiceBtn).click();
			break;
		}
		moveToElement(driver.findElement(By.xpath("//*[@id=\"collapseOne\"]/div/div[2]/form/div/div/div")));
		List<WebElement> availableTimeSlots = driver
				.findElements(By.xpath("//*[@id=\"collapseOne\"]/div/div[2]/form/div/div/div"));
		System.out.println("Count of available time slots: " + availableTimeSlots.size());
		for (int i = 2; i <= availableTimeSlots.size(); i++) {
			By time = By.xpath("//*[@id=\"collapseOne\"]/div/div[2]/form/div/div/div[" + i + "]/label/span");
			String strTimeSlot = driver.findElement(time).getText();
			if (strTimeSlot.equalsIgnoreCase("BOOKED")) {
				continue;
			} else {
				moveToElement(driver.findElement(time));
				driver.findElement(time).click();
				break;
			}
		}
		moveToElement(driver.findElement(bookAnAppointmentNowBtn));
		driver.findElement(bookAnAppointmentNowBtn).click();
		return true;
	}

	// Validate the selection of profile/individual service link
	public boolean selectMedicalService(String serviceName) {
//		waitSometime();
		moveToElement(driver.findElement(profileServiceLnk));
		waitSometime();
//		explicitWait(profileServiceLnk);
		switch (serviceName) {
		case "PROFILE SERVICE":
			explicitWait(profileServiceLnk);
			driver.findElement(profileServiceLnk).click();
			scrollTillASpecificPoint(400);
			break;
		case "INDIVIDUAL SERVICE":
			explicitWait(individualServiceLnk);
			driver.findElement(individualServiceLnk).click();
			break;
		}
		return true;
	}

	// Validate the selection of services under Profile Service / Individual Service
	public boolean selectProfileService(String serviceName, String profileIndividualServiceSelection,
			String profileIndividualTestSelection) {
		int countForDisplayedProfile = 0;
		switch (serviceName) {
		case "PROFILE SERVICE":
			waitSometime();
			List<WebElement> profileServiceList = driver.findElements(
					By.xpath("//app-lab-details/section/div[5]/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div"));
			System.out.println("List of profile Tests available: " + profileServiceList.size());
			for (int j = 1; j <= profileServiceList.size(); j++) {
				String profileTest = driver.findElement(By
						.xpath("//app-lab-details/section/div[5]/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div["
								+ j + "]/div/span"))
						.getText();
				System.out.println("Profile Test Value: " + profileTest);
				if (profileTest.trim().equalsIgnoreCase(profileIndividualTestSelection)) {
					moveToElement(driver.findElement(By
						.xpath("//app-lab-details/section/div[5]/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div["
								+ j + "]/div/span")));
					moveToElement(driver.findElement(By.xpath(
							"//app-lab-details/section/div[5]/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[" + j
							+ "]/div/div/button")));
					driver.findElement(By.xpath(
							"//app-lab-details/section/div[5]/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[" + j
									+ "]/div/div/button"))
							.click();
					break;
				}
				if (j % 5 == 0) {
					scrollTillASpecificPoint(300);
					waitSometime();
				}
			}
			break;
		case "INDIVIDUAL SERVICE":
			waitSometime();
			List<WebElement> individualServiceList = driver.findElements(By.xpath(
					"//app-lab-details/section/div[@class='mt-5']/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div"));
			System.out.println("List of profile Tests available: " + individualServiceList.size());
			for (int i = 1; i <= individualServiceList.size(); i++) {
				if (driver.findElement(By.xpath(
						"//app-lab-details/section/div[@class='mt-5']/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div["
								+ i + "]"))
						.getText().equalsIgnoreCase("")) {
					continue;
				}
				List<WebElement> targetElement = driver.findElements(By.xpath(
						"//app-lab-details/section/div[@class='mt-5']/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div["
								+ i + "]/div/h3/button"));
				try {
					System.out.println("Size of elements: " + targetElement.size());
					if (targetElement.size() >= 1) {
						for (int i1 = 0; i1 < targetElement.size(); i1++) {
							if (targetElement.get(i1).isDisplayed()) {
								countForDisplayedProfile++;
								if (countForDisplayedProfile % 5 == 0) {
									scrollTillASpecificPoint(300);
									waitSometime();
								}
								System.out.println("Profile Name: " + driver.findElement(By.xpath(
										"//app-lab-details/section/div[@class='mt-5']/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div["
												+ i + "]/div/h3/button"))
										.getText().trim().toUpperCase());
								if (driver.findElement(By.xpath(
										"//app-lab-details/section/div[@class='mt-5']/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div["
												+ i + "]/div/h3/button"))
										.getText().trim().toUpperCase()
										.contains(profileIndividualServiceSelection.toUpperCase())) {
									driver.findElement(By.xpath(
											"//app-lab-details/section/div[@class='mt-5']/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div["
													+ i + "]/div/h3/button"))
											.click();
									waitSometime();
									List<WebElement> individualTestList = driver.findElements(By.xpath(
											"//app-lab-details/section/div[@class='mt-5']/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div["
													+ i + "]/div/div/div/div/div"));
									System.out.println("List of profile Tests available: " + individualTestList.size());
									for (int j = 1; j <= individualTestList.size(); j++) {
										String profileTest = driver.findElement(By.xpath(
												"//app-lab-details/section/div[@class='mt-5']/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div["
														+ i + "]/div/div/div/div/div[" + j
														+ "]/app-used-service/div/div/div/div[1]/h2"))
												.getText();
										System.out.println("Individual Test Value: " + profileTest);
										if (profileTest.trim().equalsIgnoreCase(profileIndividualTestSelection)) {
											moveToElement(driver.findElement(By.xpath(
													"//app-lab-details/section/div[@class='mt-5']/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div["
															+ i + "]/div/div/div/div/div[" + j
															+ "]/app-used-service/div/div/div/div[4]/ul/li/button")));
											driver.findElement(By.xpath(
													"//app-lab-details/section/div[@class='mt-5']/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div["
															+ i + "]/div/div/div/div/div[" + j
															+ "]/app-used-service/div/div/div/div[4]/ul/li/button"))
													.click();
											j = individualTestList.size();
											i1 = targetElement.size();
											i = individualServiceList.size();
											break;
										}
									}
								} else
									continue;
							} else
								continue;
						}
					} else
						continue;
				} catch (NoSuchElementException e) {
					System.out.println("Exception in finding the element:" + e.getMessage());
				}
			}
			break;
		}
		waitSometime();
		return true;
	}

	// Validate the selected service for Profile/Individual
	public boolean verifySelectedService(String serviceName) throws Exception {
		switch (serviceName) {
		case "PROFILE SERVICE":
			String strNoOfSelectedService = driver.findElement(profile_noOfSelectedServiceTxt).getText();
			System.out.println("noOfSelectedServiceTxt: " + strNoOfSelectedService.trim());
			if (strNoOfSelectedService.trim().contains("NUMBER OF SELECTED SERVICES")) {
				driver.findElement(profileBookAnAppointmentNow).click();
				waitSometime();
			}
			break;
		case "INDIVIDUAL SERVICE":
			String strNoOfSelectedService1 = driver.findElement(individual_noOfSelectedServiceTxt).getText();
			System.out.println("noOfSelectedServiceTxt: " + strNoOfSelectedService1.trim());
			if (strNoOfSelectedService1.trim().contains("NUMBER OF SELECTED SERVICES")) {
				driver.findElement(individualBookAnAppointmentNow).click();
				waitSometime();
			}
			break;
		}
		waitForSpecificTime(5000);
		System.out.println("++++++++++++++++     " + driver.findElement(chooseServiceTxt).getText());
		Assert.assertEquals(driver.findElement(chooseServiceTxt).getText(), "Choose the Service that fits you");
		return true;
	}

	// Validate time slot selection for appointment booking
	public boolean selectTimeSlotAndBook() throws Exception {
		waitSometime();
		scrollTillASpecificPoint(300);
		waitSometime();
		List<WebElement> timeSlotCount = driver
				.findElements(By.xpath("//div[contains(@class, 'row') or contains(@class, 'lab_book_date')]/div"));
		for (int i = 2; i < timeSlotCount.size(); i++) {
			By time = By.xpath(
					"//div[contains(@class, 'row') or contains(@class, 'lab_book_date')]/div[" + i + "]/label/span");
			String strTimeSlot = driver.findElement(time).getText();
			if (strTimeSlot.equalsIgnoreCase("BOOKED")) {
				continue;
			} else {
				driver.findElement(time).click();
				break;
			}
		}
		waitSometime();
		scrollTillASpecificPoint(400);
		waitSometime();
		driver.findElement(msBookAnAppointmentNowBtn).click();
		waitSometime();
		explicitWait(bookingStatementHeader);
		System.out.println("++++++++++++++++     " + driver.findElement(bookingStatementHeader).getText().trim());
		Assert.assertEquals(driver.findElement(bookingStatementHeader).getText().trim(), "BOOKING STATEMENT");
		return true;
	}

	// Validate walk in appointment
	public boolean selectWalkInAndBuy() throws Exception {
		waitSometime();
		explicitWait(walkInHeader);
		clickUsingJS(walkInChkBx);
		waitSometime();
		driver.findElement(By.xpath("//app-lab-book/section[1]/div/div/div[5]/div/div[3]/div/span")).click();
		waitSometime();
		moveToElement(driver.findElement(buyNowBtn));
		driver.findElement(buyNowBtn).click();
		waitSometime();
		System.out.println("++++++++++++++++     " + driver.findElement(bookingStatementHeader).getText().trim());
		Assert.assertEquals(driver.findElement(bookingStatementHeader).getText().trim(), "BOOKING STATEMENT");
		return true;
	}
}
