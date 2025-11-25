package Common;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected static WebDriver driver = null;
	static String path = System.getProperty("user.dir");
	protected static String prod_url = "https://salamtek.com/en";
	protected static String stg_url = "https://stg.salamtek.com/en";
	protected static String dev_url = "https://dev.salamtek.com/en";

	static String pageLoadStatus = null;

	// initialization of web driver and navigating to URL
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		// System.setProperty("webdriver.chrome.driver", path +
		// "\\Drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		options.addArguments("--remote-allow-origins=*");
		// Instantiate the chrome driver
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// URL for staging
		// driver.get(stg_url);

		// URL for DEV
		driver.get(dev_url);

		// URL for PROD
		// driver.get(prod_url);

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(80));

//		 Dimension initial_size = driver.manage().window().getSize();
//		 System.out.println("initial_size : " + initial_size);
//		 driver.manage().window().setSize(new Dimension(1936, 1056));

	}

	// Retrieves webdriver
	public WebDriver getDriver() {
		return driver;
	}

	// Retrieves excel sheet
	public String getExcelSheetName() {

		// Test Data for staging
		// String excelPath = path + "\\TestData\\TestData_V2.xlsx";

		// Test Data for Dev
		String excelPath = path + "\\TestData\\TestData_DEV.xlsx";

		// Test Data for PROD
		// String excelPath = path + "\\TestData\\TestData_PROD.xlsx";
		return excelPath;
	}

	// Explicitly waits a locator to be visible for a specific time
	public void explicitWait(By locatorName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorName));
	}

	// Explicitly waits a locator to be visible for a specific time
	public void explicitWait_60(By locatorName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorName));
	}

	// Explicitly waits a locator to be visible for a specific time
	public void waitElementToBeClickable(By locatorName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(locatorName));
	}

	// Explicitly waits a locator to be visible for a specific time
	public void waitElementToBeVisible(By locatorName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorName));
	}

	// Clicking element using javascript executor
	public void clickUsingJS(By locatorName) {
		WebElement ele = driver.findElement(locatorName);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	// To scroll down the webpage till the end vertically
	public void scrollTillEnd() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)", "");
	}

	// To scroll up the webpage till the top vertically
	public void scrollTillTop() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		waitSometime();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		waitSometime();
		js.executeScript("window.scrollTo(0,0)");
	}

	// To scroll down the web page to a specific point
	public void scrollTillASpecificPoint(int y_cord) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + y_cord + ")", "");
	}

	// To scroll down the webpage to a specific point
	public void scrollUpToASpecificPoint(int x_cord) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x_cord + ",0)", "");
	}

	// To scroll until the element is visible
	public void scrollTillElementVisible(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	// To wait for sometime
	public static void waitSometime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// To wait a second
	public static void waitASecond() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// To wait for sometime
	public void waitForSpecificTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// To move away the mouse pointer to an element
	public void moveMousePointerAndMoveToAnElement(By element) {
		Actions a = new Actions(driver);
		a.moveByOffset(10, 20).perform();
		a.click().perform();
		// move to an element
		WebElement l = driver.findElement(element);
		a.moveToElement(l).perform();
	}

	// To scroll untill we find the element
	public void moveMousePointerToAnElement(By element) {
		Actions a = new Actions(driver);
		a.moveByOffset(10, 20).perform();
		a.click().perform();
		// move to an element
		WebElement l = driver.findElement(element);
		a.moveToElement(l).perform();
	}

	// To move away the mouse pointer away from a selected object
	public void moveMousePointerAway() {
		Actions action = new Actions(driver);
		action.moveByOffset(0, 0).click().build().perform();
	}

	// To upload PDF document
	public void uploadPDF() {
		try {
			System.out.println(path + "\\Files\\FileUpload.exe");
			Runtime.getRuntime().exec(path + "\\Files\\FileUpload.exe");
			System.out.println("File is Uploaded Successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// To get today's date
	public Date getTodayDate() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		return today;
	}

	// To get any date from today
	public Date getAnyDateFromToday(int dayCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, dayCount);
		Date tomorrow = calendar.getTime();
		return tomorrow;
	}

	// To get today's date in yyyy-MM-dd format
	public static String getTodayDateInYYYYMMDD() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(today);
	}

	// To get today's date in MM/dd/yyyy format
	public String getTodayDateInSlash() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(today);
	}

	// To get day from today's date
	public int getDayFromDate() {
		LocalDate currentDate = LocalDate.now();
		int day = currentDate.getDayOfMonth();
		return day;
	}
	
	//To get Day from tomorrow's date
	public int getDayFromTomorrow() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		int day = tomorrow.getDayOfMonth();
		return day;
	}

	// To get tomorrow's date in yyyy-MM-dd format
	public String getTomorrowDateInYYYYMMDD() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(tomorrow);
	}

	// To get month from today's date
	public String getMonthFromToday(Date date) {
		return new SimpleDateFormat("MMMM").format(date);
	}

	// To get current year
	public int getCurrentYear(String date) {
		LocalDate currentDate = LocalDate.parse(date);
		return currentDate.getYear();
	}

	// To get month from today's date
	public String getWeekDayValueFromDate(Date date) {
		DateFormat formatter = new SimpleDateFormat("EEEE");
		return formatter.format(date);
	}

	// To get day value as single digit or double
	public String getDayValue(String date) {
		String dayValue;
		if (date.substring(date.length() - 2, date.length()).substring(0).charAt(0) == '0')
			dayValue = date.substring(date.length() - 1, date.length());
		else
			dayValue = date.substring(date.length() - 2, date.length());
		return dayValue;
	}

	// To get date suffix such as st, nd, rd
	public String getDateSuffix(String day) {
		String daySuffix = "th";
		switch (day) {
		case "1":
		case "21":
		case "31":
			daySuffix = "st";
			break;
		case "2":
		case "22":
			daySuffix = "nd";
			break;
		case "3":
		case "23":
			daySuffix = "rd";
			break;
		}
		return daySuffix;
	}

	// Validates error pop up, if any
	public boolean errorCheck(By element) {
		Boolean status = false;
		List<WebElement> targetElement = driver.findElements(element);
		try {
			System.out.println("Size of elements: " + targetElement.size());
			if (targetElement.size() >= 1) {
				for (int i = 0; i < targetElement.size(); i++) {
					if (targetElement.get(i).isDisplayed()) {
						status = true;
						break;
					} else {
						status = false;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Exception in finding the element:" + e.getMessage());
		}
		return status;
	}

	// Round a decimal number to 3 digits after decimal
	public static double roundNumberTo3Decimals(double d) {
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.UNNECESSARY);
		return Double.parseDouble(df.format(d));
	}

	// Round a decimal number to 2 digits after decimal
	public static double roundNumberTo2Decimals(double d) {
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(d));
	}

	// Round a decimal number to 1 digits after decimal
	public static double roundNumberTo1Decimal(double d) {
		DecimalFormat df = new DecimalFormat("#.#");
		return Double.parseDouble(df.format(d));
	}

	// Generate random number with a specific length
	public static String GenerateRandomNumber(int charLength) {
		return String.valueOf(charLength < 1 ? 0
				: new Random().nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
						+ (int) Math.pow(10, charLength - 1));
	}

	// To take screenshot and stores in Screenshots folder
	public static void takeScreenShot(WebDriver driver, String screenShotName, String tcID, String sheetname) {
		String fileSeparator = "\\";
		try {
			String filePath = path + fileSeparator + "Screenshots" + fileSeparator + "Results" + fileSeparator
					+ sheetname + fileSeparator + tcID;
			File file = new File(filePath);
			System.out.println("File Name:   " + filePath);
			if (!file.exists()) {
				System.out.println("Create File");
				file.mkdir();
			}
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File(path + fileSeparator + "Screenshots" + fileSeparator + "Results" + fileSeparator
					+ sheetname + fileSeparator + tcID + fileSeparator + screenShotName + ".jpg");
			FileUtils.copyFile(screenshotFile, targetFile);
		} catch (Exception e) {
			System.out.println("An exception occured while taking screenshot " + e.getCause());
		}
	}

	public boolean validateToast(String expectedMessage, String tcID, String sheetname) {
		By toastMessage = By.id("toast-container");
		boolean status = false;
		explicitWait_60(toastMessage);
		String actualString = driver.findElement(toastMessage).getText().trim();
		if (actualString.equalsIgnoreCase(expectedMessage)) {
			takeScreenShot(driver, "ToastMessage", tcID, sheetname);
			status = true;
		} else {
			status = false;
		}
		return status;
	}

	// check until pageload is completed
	public static void waitForPageLoad() {
		do {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			pageLoadStatus = (String) js.executeScript("return document.readyState");
		} while (!pageLoadStatus.equals("complete"));
		System.out.println("Page Loaded.");
	}
	
	//Stop page load
	public void stopPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return window.stop");
    }

	//Move and highlight an element in the page
	public static void moveToElement(WebElement ele) {
		Actions ac = new Actions(driver);
		ac.moveToElement(ele).perform();
		waitSometime();
	}

	//Getting value using javascript executor
	public String getValueUsingJS(WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return arguments[0].value;", webElement);
    }
	// Closes the browser after completing all the tests
	@AfterTest
	public void tearDown() {
		try {
			driver.quit();
		} catch (Exception e) {

		}
	}
}
