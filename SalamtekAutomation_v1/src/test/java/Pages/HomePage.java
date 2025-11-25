package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Common.BaseTest;

public class HomePage extends BaseTest {
	By homeLnk = By.xpath("//app-header/header/div[2]/div/div/nav/div/div[1]/ul/li[1]/a[contains(text(),'HOME ')]");
	By findADoctorLnk = By
			.xpath("//app-sub-menu/div[1]/div/div/nav/div/div[2]/ul/li[1]/a[contains(text(),'FIND A DOCTOR')]");
	By hospitalsAndClinicsLnk = By.xpath("//app-sub-menu/div[1]/div/div/nav/div/div[2]/ul/li[2]/a[contains(text(),'HOSPITALS & CLINICS')]");
	By bookMedicalServicesLnk = By
			.xpath("//app-sub-menu/div[1]/div/div/nav/div/div[2]/ul/li[3]/a[contains(text(),'BOOK MEDICAL SERVICES')]");
	By shopOnlineLnk = By
			.xpath("//app-sub-menu/div[1]/div/div/nav/div/div[2]/ul/li[4]/a[contains(text(),'SHOP ONLINE')]");
	By doctorCornerLnk = By.linkText("DOCTOR CORNER");
	By offerLnk = By
			.xpath("//app-sub-menu/div[1]/div/div/nav/div/div[2]/ul/li[6]/a[1]/span[contains(text(),'OFFERS')]");
	By uploadPrescriptionLnk = By.xpath("//app-header/header/div[2]/div/div/nav/div/div[2]/p");
	By needHelpBtn = By.xpath("//*[@id=\"topBtn\"]/img");
	By askSalamtekDoctorLnk = By
			.xpath("//app-footer/footer/div[1]/div/div/div[2]/div/div/span/div/span/div/div[1]/div[3]/a/img");
	By advancedSearchLnk = By
			.xpath("//app-header/header/div[2]/div/div/nav/div/div[1]/ul/li[5]/a[contains(text(),'SEARCH')]");
	By myAccountLnk = By.xpath("//*[@id=\"navbarNav\"]/div[1]/ul/li[2]/a[contains(text(),'MY ACCOUNT')]");
	By tellMeSearchTxtDoctor = By.xpath("//app-sub-menu/div[2]/div/div/div[1]/div[2]/div/div/input");
	By tellMeSearchTxtMedicalService = By.xpath("//app-sub-menu/div[2]/div/div/div[1]/div[2]/div/div/input");
	By tellMeSearchTxtShopOnline = By.xpath("//app-sub-menu/div[2]/div/div/div[1]/div[2]/div/div/input");
	By searchNameLnk = By.xpath("//*[@id=\"five-block\"]/div[2]/div[2]/div[2]/div/div/ul/li[1]/a");
	By pharmacyHeader = By.xpath("//div[@class='DOCTER-PROFILE-NAME']/h2");
	By chooseServiceThatFitsYouLbl = By.xpath("//app-doctor-details/section[1]/div[2]/div/div[1]/div/div[1]/p[contains(text(),'Choose the Service that fits you')]");
	By bookAnAppointmentBtn = By.xpath("//button[contains(text(),'BOOK AN APPOINTMENT')]");
	By profileServiceLnk = By.id("profileTestTab");

	// Salamtek Has It All elements
	By salamtekHasItAllLbl = By
			.xpath("//app-general-home/div[1]/span/section[2]/div/div/div/div/h2[contains(text(),'HAS IT ALL')]");
	By pharmacyProductsLnk = By.id("SELLING-tab");
	By showMeMoreProductsBtn = By.name("store");
	By productListingHeader = By.xpath(
			"//app-product-listing/section/div/div[2]/div/div[3]/div/div[3]/div/div[3]/h2/span[contains(text(),'PRODUCTS LISTING')]");

	// Quick Services Elements
	By quickServicesHeader = By
			.xpath("//app-general-home/div[1]/div[2]/div/div/div/div[1]/div/h2[contains(text(),'QUICK SERVICES')]");
	By medicineBtn = By.name("product1");
	By equipmentBtn = By.name("product2");
	By checkAllServicesBtn = By.name("serviceAll");
	By mostUsedServicesHeader = By.xpath("//app-medical-home/section/div[2]/div[1]/div/div/div/div/h2[contains(text(),'MOST USED')]");
	By doctorPicksBtn = By.name("doctor1");
	By doctorShelvesBtn = By.name("doctor2");
	By doctorPicksHeader = By.xpath("//app-doctors-corner/section/div[2]/div/div/div[4]/div[2]/div/div/div/button[1]/h2/span[contains(text(),'DOCTOR PICKS')]");
	By doctorShelvesHeader = By.xpath("//app-doctors-corner/section/div[2]/div/div/div[4]/div[2]/div/div/div/button[3]/h2/span[contains(text(),'DOCTOR SHELVES')]");


	// Need More Help elements
	By needMoreHelpHeader = By
			.xpath("//app-general-home/div[1]/span/section[1]/div[1]/div/h2/span[contains(text(),'NEED MORE')]");
	By checkBySymptomBtn = By.xpath(
			"//app-general-home/div[1]/span/section[1]/div[2]/div/app-need-more-section/div[1]/ul/li[1]/button[contains(text(),'CHECK BY SYMPTOM')]");
	By lookForOtherSymptomBtn = By.xpath("//button[contains(text(),'LOOKING FOR OTHER SYMPTOMS?')]");
	By defineOtherSymptomsHeader = By
			.xpath("//app-symptoms-popup/div/div/div/div/div/div/div[1]/h2[contains(text(),'Define Other')]");
	By findDoctorBtn = By
			.xpath("//app-symptoms-popup/div/div/div/div/div/div/div[4]/button[contains(text(),'Find Doctor')]");
	By inNetworkDoctorsLbl = By.xpath(
			"//app-doctor-listing/section/div/div[3]/div/div/div[4]/div[1]/p[contains(text(),'IN-NETWORK DOCTORS')]");
	
	//Review pop up elements
	By reviewPopupTitle = By.xpath("//*[@id=\"MY-ORDER-REVIEW\"]/app-user-reviews/div/div/div/p");
	By fifthStar = By.xpath("//*[@id=\"MY-ORDER-REVIEW\"]/app-user-reviews/div/div/form/div/div[1]/div[1]/ul/li[5]/i");
	By reviewSubmitBtn = By.xpath("//*[@id=\"MY-ORDER-REVIEW\"]/app-user-reviews/div/div/form/div/div[2]/button[contains(text(),'SUBMIT')]");
	By reviewCloseBtn = By.xpath("//*[@id=\"MY-ORDER-REVIEW\"]/app-user-reviews/div/div/span");
	
	public HomePage(WebDriver driver) {
		super();
	}

	//Validate menu selection from home page
	public boolean selectOperation(String operationName) {
		waitSometime();
		verifyReviewClose();
		switch (operationName) {
		case "findADoctor":
			explicitWait(findADoctorLnk);
			driver.findElement(findADoctorLnk).click();
			break;
		case "hospitalsAndClinics":
			explicitWait(hospitalsAndClinicsLnk);
			driver.findElement(hospitalsAndClinicsLnk).click();
			break;
		case "bookMedicalServices":
			explicitWait(bookMedicalServicesLnk);
			driver.findElement(bookMedicalServicesLnk).click();
			break;
		case "shopOnline":
			explicitWait(shopOnlineLnk);
			driver.findElement(shopOnlineLnk).click();
			break;
		case "doctorCorner":
			explicitWait(doctorCornerLnk);
			driver.findElement(doctorCornerLnk).click();
			break;
		case "offers":
			explicitWait(offerLnk);
			driver.findElement(offerLnk).click();
			break;
		case "askSalamtekDoctor":
			waitSometime();
			scrollTillASpecificPoint(1000);
			waitSometime();
			driver.findElement(needHelpBtn).click();
			waitSometime();
			driver.findElement(askSalamtekDoctorLnk).click();
			// clickUsingJS(askSalamtekDoctorLnk);
			break;
		case "advancedSearch":
			explicitWait(advancedSearchLnk);
			driver.findElement(advancedSearchLnk).click();
			break;
		case "myAccount":
			explicitWait(myAccountLnk);
			driver.findElement(myAccountLnk).sendKeys(Keys.ENTER);
			waitSometime();
			break;
		case "uploadPrescription":
			driver.navigate().refresh();
			waitForSpecificTime(5000);
			verifyReviewPopUp();
			explicitWait(uploadPrescriptionLnk);
			driver.findElement(uploadPrescriptionLnk).click();
			break;
		}
		return true;
	}

	//Validate filter from the menu selected
	public boolean selectFilterFromOperation(String filterFromOperation) {
		waitSometime();
		switch (filterFromOperation) {
		case "Medical Center":
			explicitWait(findADoctorLnk);
			driver.findElement(findADoctorLnk).click();
			break;
		}
		return true;
	}

	//Validate selection of doctor from Find A Doctor
	public boolean selectDoctorFromLink(String doctorSearchName, String doctorName) throws Exception {
		driver.findElement(tellMeSearchTxtDoctor).sendKeys(doctorSearchName);
		driver.findElement(tellMeSearchTxtDoctor).sendKeys(Keys.ENTER);
		By doctorNameLnk = By.xpath("//a[contains(text(),'" + doctorName + "')]");
		explicitWait(doctorNameLnk);
		driver.findElement(doctorNameLnk).click();
		waitSometime();
		System.out.println(
				"++++++++++++++++     " + driver.findElement(chooseServiceThatFitsYouLbl).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(chooseServiceThatFitsYouLbl).getText().trim().toUpperCase(),
				"CHOOSE THE SERVICE THAT FITS YOU", "CHOOSE THE SERVICE THAT FITS YOU is not present");
		return true;
	}

	//Validate book doctor appointment
	public boolean bookDoctor(String doctorName) {
		driver.navigate().refresh();
		waitSometime();
		WebElement bookAnAppointmentBtn = driver
				.findElement(By.xpath("//button[contains(text(),'BOOK AN APPOINTMENT')]"));
		Actions action = new Actions(driver);
		action.moveToElement(bookAnAppointmentBtn).perform();
		waitSometime();
		System.out.println("++++++++++++++++     " + driver
				.findElement(By.xpath("//app-doctor-profile/div/div[1]/div/p/a[3]")).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(By.xpath("//app-doctor-profile/div/div[1]/div/p/a[3]")).getText().trim()
				.toUpperCase(), doctorName.toUpperCase(), "Doctor is not present");
		return true;
	}

	//Validate selection of Lab from Book Medical Services
	public boolean selectMedicalService(String serviceSearchName, String serviceName) throws Exception {
		driver.findElement(tellMeSearchTxtMedicalService).sendKeys(serviceSearchName);
		driver.findElement(tellMeSearchTxtMedicalService).sendKeys(Keys.ENTER);
		By serviceNameLnk = By.xpath("//a[contains(text(),'" + serviceName + "')]");
		explicitWait(serviceNameLnk);
		driver.findElement(serviceNameLnk).click();
		waitSometime();
		scrollTillTop();
		waitSometime();
		explicitWait(bookAnAppointmentBtn);
		moveToElement(driver.findElement(bookAnAppointmentBtn));
		driver.findElement(bookAnAppointmentBtn).click();
		waitSometime();
		System.out.println("++++++++++++++++     " + driver.findElement(profileServiceLnk).getText().trim());
		Assert.assertEquals(driver.findElement(profileServiceLnk).getText().trim(), "PROFILE SERVICE",
				"Profile service link is not present");
		return true;
	}

	//Validate selection of pharmacy from Shop Online
	public boolean selectPharmacy(String pharmacySearchName, String pharmacyName) throws Exception {
		driver.findElement(tellMeSearchTxtShopOnline).sendKeys(pharmacySearchName);
		driver.findElement(tellMeSearchTxtShopOnline).sendKeys(Keys.ENTER);
		explicitWait(searchNameLnk);
		String pName = driver.findElement(searchNameLnk).getText().trim();
		System.out.println("Pharmacy link name: " + pName);
		if (pName.contains(pharmacyName)) {
			driver.findElement(searchNameLnk).click();
		}
		waitSometime();
		explicitWait(pharmacyHeader);
		System.out.println("++++++++++++++++     " + driver.findElement(pharmacyHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(pharmacyHeader).getText().trim().toUpperCase(),
				pharmacyName.toUpperCase(), "Pharmacy name is not present");
		return true;
	}

	// Purchase product from Home page -> Salamtek Has It All
	public boolean selectProductFromSalamtekHasItAll(String pageName, String tcID, String sheetName) throws Exception {
		driver.findElement(homeLnk).click();
		waitForSpecificTime(5000);
		WebElement salamtekHasItAllLblEle = driver.findElement(salamtekHasItAllLbl);
		moveToElement(salamtekHasItAllLblEle);
		waitSometime();
		moveToElement(driver.findElement(pharmacyProductsLnk));
		driver.findElement(pharmacyProductsLnk).click();
		takeScreenShot(getDriver(), pageName, tcID, sheetName);
		moveToElement(driver.findElement(showMeMoreProductsBtn));
		driver.findElement(showMeMoreProductsBtn).click();
		waitSometime();
		waitSometime();
		explicitWait(productListingHeader);
		System.out.println(
				"++++++++++++++++     " + driver.findElement(productListingHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(productListingHeader).getText().trim().toUpperCase(), "PRODUCTS LISTING",
				"Product is not present");
		return true;
	}

	//Book Medical Services from Home page -> Quick Services
	public boolean selectServicesFromQuickServices(String pageName, String tcID,
			String sheetName) throws Exception {
		driver.findElement(homeLnk).click();
		waitForSpecificTime(5000);
		verifyReviewClose();
		WebElement quickServicesEle = driver.findElement(quickServicesHeader);
		//scrollTillElementVisible(quickServicesEle);
		moveToElement(quickServicesEle);
		waitSometime();
		//scrollTillASpecificPoint(-400);
		moveToElement(driver.findElement(checkAllServicesBtn));
		waitSometime();
		takeScreenShot(getDriver(), pageName, tcID, sheetName);
		driver.findElement(checkAllServicesBtn).click();
		waitSometime();
		waitSometime();
		explicitWait(mostUsedServicesHeader);
		System.out.println("++++++++++++++++     " + driver.findElement(mostUsedServicesHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(mostUsedServicesHeader).getText().trim().toUpperCase(), "MOST USED SERVICES",
				"Most Used Services header is not present");
		return true;
	}

	// Purchase product from Home page -> Quick Services
	public boolean selectProductFromQuickServices(String productCategory, String pageName, String tcID,
			String sheetName) throws Exception {
		driver.findElement(homeLnk).click();
		waitForSpecificTime(5000);
		verifyReviewClose();
		WebElement quickServicesEle = driver.findElement(quickServicesHeader);
		moveToElement(quickServicesEle);
		waitSometime();
		moveToElement(driver.findElement(medicineBtn));
		takeScreenShot(getDriver(), pageName, tcID, sheetName);
		if (productCategory.equalsIgnoreCase("MEDICINE")) {
			driver.findElement(medicineBtn).click();
		} else {
			driver.findElement(equipmentBtn).click();
		}
		waitSometime();
		waitSometime();
		explicitWait(productListingHeader);
		System.out.println(
				"++++++++++++++++     " + driver.findElement(productListingHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(productListingHeader).getText().trim().toUpperCase(), "PRODUCTS LISTING",
				"Product is not present");
		return true;
	}

	// Select doctor from Home page -> Need More Help -> Check By Symptom
	public boolean selectDoctorFromNeedMoreHelp_Symptom(String symptom, String tcID, String sheetName)
			throws Exception {
		driver.findElement(homeLnk).click();
		waitForSpecificTime(5000);
		WebElement needMoreHelpHeaderEle = driver.findElement(needMoreHelpHeader);
		moveToElement(needMoreHelpHeaderEle);
		waitSometime();
		moveToElement(driver.findElement(checkBySymptomBtn));
		driver.findElement(checkBySymptomBtn).click();
		takeScreenShot(getDriver(), "SymptomsListUnderNeedMoreHelp", tcID, sheetName);
		moveToElement(driver.findElement(lookForOtherSymptomBtn));
		String symptomNameTxt = driver.findElement(By.xpath("//h2[contains(text(),'"+symptom+"')]")).getText().trim();
		if (symptomNameTxt.equalsIgnoreCase(symptom.trim())) {
			driver.findElement(By.xpath("//h2[contains(text(),'"+symptom+"')]")).click();
		}
		waitSometime();
		explicitWait(defineOtherSymptomsHeader);
		takeScreenShot(getDriver(), "OtherSymptomsSelectionPopUp", tcID, sheetName);
		driver.findElement(findDoctorBtn).click();
		waitSometime();
		waitSometime();
		explicitWait(inNetworkDoctorsLbl);
		System.out.println(
				"++++++++++++++++     " + driver.findElement(inNetworkDoctorsLbl).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(inNetworkDoctorsLbl).getText().trim().toUpperCase(),
				"IN-NETWORK DOCTORS", "IN-NETWORK DOCTORS is not present");
		return true;
	}
	
	
	// Select Doctor recommendations from Home page -> Quick Services -> DR.S PICKS/DR.S SHELF
		public boolean selectDoctorRecommendationsFromQuickServices(String filterBy, String pageName, String tcID,
				String sheetName) throws Exception {
			driver.findElement(homeLnk).click();
			explicitWait_60(quickServicesHeader);
			WebElement quickServicesEle = driver.findElement(quickServicesHeader);
			moveToElement(quickServicesEle);
			if (filterBy.equalsIgnoreCase("doctorPicks")) {
				explicitWait_60(doctorPicksBtn);
				takeScreenShot(getDriver(), pageName, tcID, sheetName);
				moveToElement(driver.findElement(doctorPicksBtn));
				driver.findElement(doctorPicksBtn).click();
				explicitWait(doctorPicksHeader);
				System.out.println(
						"++++++++++++++++     " + driver.findElement(doctorPicksHeader).getText().trim().toUpperCase());
				Assert.assertEquals(driver.findElement(doctorPicksHeader).getText().trim().toUpperCase(), "DOCTOR PICKS",
						"DOCTOR PICKS header is not present");
			} else {
				explicitWait_60(doctorShelvesBtn);
				takeScreenShot(getDriver(), pageName, tcID, sheetName);
				moveToElement(driver.findElement(doctorShelvesBtn));
				driver.findElement(doctorShelvesBtn).click();
				explicitWait(doctorShelvesHeader);
				System.out.println(
						"++++++++++++++++     " + driver.findElement(doctorShelvesHeader).getText().trim().toUpperCase());
				Assert.assertEquals(driver.findElement(doctorShelvesHeader).getText().trim().toUpperCase(), "DOCTOR SHELVES",
						"DOCTOR SHELVES header is not present");
			}
			waitSometime();
			return true;
		}
		
		public boolean verifyReviewPopUp() {
			boolean result = false;
			waitSometime();
			if(driver.findElement(reviewPopupTitle).isDisplayed()) {
				driver.findElement(fifthStar).click();
				driver.findElement(reviewSubmitBtn).click();
				waitSometime();
				result = true;
			}
			else {
				result = true;
			}
			return result;
		}
		
		public boolean verifyReviewClose() {
			boolean result = false;
			waitSometime();
			if(driver.findElement(reviewPopupTitle).isDisplayed()) {
				driver.findElement(reviewCloseBtn).click();
				waitSometime();
				result = true;
			}
			else {
				result = true;
			}
			return result;
		}
}
