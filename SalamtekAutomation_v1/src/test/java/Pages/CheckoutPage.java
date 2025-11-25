package Pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Common.BaseTest;
import Utils.ExcelUtils;

public class CheckoutPage extends BaseTest {

	ExcelUtils excelUtils = new ExcelUtils();

	By screenIdentifierCheckout = By.xpath("//h4[contains(text(),'CHECKOUT')]");
	By promotionCodeTxtForDoctor = By.xpath("//input[@placeholder='PROMOTION CODE.']");
	By promotionCodeTxtForLab = By.id("promoCode");
	By promotionCodeTxtForPharmacy = By.xpath("//input[@placeholder='PROMOTION CODE']");
	By submitBtn = By.xpath("//button[contains(text(),'SUBMIT')]");
	By consultationFeeLbl = By
			.xpath("//app-doctor-checkout/section/div[1]/div/div[3]/div/div[2]/div[2]/div[3]/ul[1]/li[2]/span");
	// By discountLblForDoctor =
	// By.xpath("//app-doctor-checkout/section/div[1]/div/div[3]/div/div[2]/div[2]/div[3]/ul[3]/li[1]");
	By discountLblForDoctor = By.xpath("//li[contains(text(),'Discount')]");
	By discountedAmountLblForDoctor = By
			.xpath("//app-doctor-checkout/section/div[1]/div/div[3]/div/div[2]/div[2]/div[3]/ul[3]/li[2]/span");
	By discoutLblForLab = By
			.xpath("//app-lab-checkout-v2/section/div[1]/div/div[3]/div/div[2]/div[2]/div[3]/ul[2]/li[1]");
	By discountedAmountLblForLab = By
			.xpath("//app-lab-checkout-v2/section/div[1]/div/div[3]/div/div[2]/div[2]/div[3]/ul[2]/li[2]/span");
	By discountLblForPharmacy = By
			.xpath("//app-product-checkout/section[1]/div/div/div[3]/div/div[2]/div[2]/div[3]/ul[3]/li[1]");
	By totalAmountForPharmacy = By
			.xpath("//app-product-checkout/section[1]/div/div/div[3]/div/div[2]/div[2]/div[3]/ul[4]/li[2]/span");
	By discountedAmountLblForPharmacy = By
			.xpath("//app-product-checkout/section[1]/div/div/div[3]/div/div[2]/div[2]/div[3]/ul[3]/li[2]/span");
	By subTotalAmountForLab = By
			.xpath("//app-lab-checkout-v2/section/div[1]/div/div[3]/div/div[2]/div[2]/div[3]/ul[1]/li[2]/span");
	By totalAmountForLab = By
			.xpath("//app-lab-checkout-v2/section/div[1]/div/div[3]/div/div[2]/div[2]/div[3]/ul[3]/li[2]/span");
	By subTotalAmountForPharmacy = By
			.xpath("//app-product-checkout/section[1]/div/div/div[3]/div/div[2]/div[2]/div[3]/ul[1]/li[2]/span");
	By walletChkbx = By.xpath("//section/div[1]/div/div[3]/div/div[2]/div[2]/div[2]/div[2]/div/div/div[1]/div/label");
	By walletBalanceTxt = By.xpath("//h4[contains(text(),'WALLET BALANCE')]");
	By payOnlyBookingFeeChkbx = By.id("reservationAmount");
	By cashModeBtn = By.xpath("//label/input[@id='pcC' or @id='payC' and @type='radio']/following-sibling::span");
	By kentModeBtn = By.xpath("//label/input[@id='pcK' or @id='payK' and @type='radio']/following-sibling::span");
	By creditCardModeBtn = By
			.xpath("//label/input[@id='pcCC' or @id='payCC' and @type='radio']/following-sibling::span");
	By selectPaymentMethod = By.xpath("//h3[contains(text(),'Select Payment Method')]");
	By proceedBtn = By.xpath("//button[contains(text(),'PROCEED')]");
	By paymentPageForKNET = By.id("paypage");
	By paymentPageForVisa = By.id("payContainer");
	By bookingID = By
			.xpath("//h2[contains(@class, 'booking-id-text') or contains(@class, 'booking-id-text mt-5 mb-5')]/span");
	By printReceiptTxt = By.xpath("//span[contains(text(),'PRINT RECEIPT')]");
	By labAmountPaidTxt = By
			.xpath("//app-lab-booking/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div[2]/ul/li[2]/span");
	By labGrandTotal = By.xpath("//app-lab-booking/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div[3]/ul/li[2]/span");
	By labGrandTotallbl = By.xpath("//app-lab-booking/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div[1]/ul/li[2]");
	By doctorConsultationFee = By
			.xpath("//app-doctors-booking/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[7]/ul/li[2]");
	By doctorGrandTotalLbl = By.xpath(
			"//app-booking-confirmation/div/app-doctors-booking/div/div/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/ul/li[2]");
	By doctorBookingFeeLbl = By
			.xpath("//app-doctors-booking/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[7]/ul/li[2]");
	By shopOnlineTotalPrice = By.xpath("//li[contains(text(),'Grand Total')]/following-sibling::li/span");
	By shopOnlineSubTotalPrice = By.xpath("//li[contains(text(),'Subtotal')]/following-sibling::li/span");
	By shopOnlineDeliveryFee = By.xpath("//li[contains(text(),'Delivery Fee')]/following-sibling::li/span");
	By shopOnlineAmountPaid = By
			.xpath("//app-product-booking/div/div/div/div[3]/div[2]/div[3]/div[1]/div[2]/div[2]/div[2]/ul/li[2]/span");
	By paymentFailed = By.xpath("//h3[@class='title Order-cancled']");
	By transactionFailedError = By.xpath("//div[contains(text(),'Transaction Failed')]");
	By futureAppointmentPopup = By
			.xpath("//h5[contains(text(),'You Already have an Appointment book for Future date')]");
	By pharmacyClosedPopup = By.xpath(
			"//*[@id=\"isStoreOnline\"]/div/div/div[2]/div[1]/div/p[contains(text(),'Any placed orders will be delivered during the next available working hours.')]");
	By futureProceed = By.xpath(
			"//*[@id=\"hasOtherAppointment\"]/div/div/div[3]/button[contains(text(),'PROCEED') or contains(text(),'Proceed')]");
	By pharmacyClosedProceed = By.xpath(
			"//*[@id=\"isStoreOnline\"]/div/div/div[2]/div[2]/button[contains(text(),'PROCEED') or contains(text(),'Proceed')]");
	By manageBookingBtn = By.xpath("//button[contains(text(),'MANAGE BOOKING')]");
	By rescheduleBtn = By.xpath("//button[contains(text(),'RESCHEDULE')]");
	By rescheduleNowBtn = By.xpath("//button[contains(text(),'RESCHEDULE NOW') or contains(text(),'Reschedule Now')]");

	// Elements related to insurance for order
	By enableInsuranceChkbx = By
			.xpath("//app-product-checkout/section/div/div/div[3]/div/div[1]/div[2]/div/div[2]/div/span/label");
	By claimDocumentUpload = By.xpath("//*[@id=\"insuranceSelectedProd\"]/div/div/div[2]/div/div[2]/div/h5/span");
	By insuranceMessage = By.xpath("//p[contains(text(),'approval')]");
	By claimProceedBtn = By
			.xpath("//*[@id=\"insuranceSelectedProd\"]/div/div/div[2]/div/div[3]/div/div/div/div[2]/button");

	// Elements related to insurance for lab
	By l_enableInsuranceChkbx = By
			.xpath("//app-lab-checkout-v2/section/div[1]/div/div[3]/div/div[1]/div[6]/div/div[2]/div/span/label");
	By originalAmount = By
			.xpath("//app-lab-checkout-v2/section/div[1]/div/div[3]/div/div[2]/div[2]/div[3]/ul[2]/li[2]");
	By amountToBePaid = By
			.xpath("//app-lab-checkout-v2/section/div[1]/div/div[3]/div/div[2]/div[2]/div[3]/ul[4]/li[2]");

	// Elements related to VISA
	// Billing Info
	By firstNameTxt = By.id("bill_to_forename");
	By lastNameTxt = By.id("bill_to_surname");
	By addressTxt = By.id("bill_to_address_line1");
	By cityTxt = By.id("bill_to_address_city");
	By countryTxt = By.id("bill_to_address_country");

	By cardType_visa = By.id("card_type_001");
	By cardType_mastercard = By.id("card_type_002");
	By visaHeader1 = By.xpath("//*[@id=\"payment_details_upper\"]/h2[contains(text(),'Payment Details')]");
	// By visaHeader1 =
	// By.xpath("//*[@id=\"billing_details\"]/h2[contains(text(),'Billing
	// Information')]");
	By visaCardNumber1 = By.id("card_number");
	By visaExpiryMonth1 = By.id("card_expiry_month");
	By visaExpiryYear1 = By.id("card_expiry_year");
	By visaCVV1 = By.id("card_cvn");
	By nextBtn = By.xpath("//*[@id=\"payment_details_lower\"]/input[@value='Next']");
	By payBtn1 = By.xpath("//*[@id=\"payment_details_lower\"]/input[@value='Pay']");
	By reviewOrderHeader = By.xpath("//*[@id=\"main\"]/div/div/h2[contains(text(),'Review your Order')]");
	By payBtn = By.xpath("//*[@id=\"main\"]/div/div/div[4]/form[2]/input[@value='Pay']");
	By purchaseAuthHeader = By.xpath("//*[@id=\"content\"]/div[1]/h2[contains(text(),'Purchase Authentication')]");
	By codeTxt = By.name("challengeDataEntry");
	By otp_submitBtn = By.xpath("//*[@id=\"content\"]/div[2]/form[1]/input[@value='SUBMIT']");
	
	// Elements related to VISANew
	By visaHeaderLbl = By.xpath(
			"//app-payment-detail-form/div/div/div/div/div[1]/div[2]/form/app-email-address/div/div/h5[contains(text(),'Customer information')]");
	By visaEmailId = By.id("email");
	By visaCardHolderNameTxt = By.id("nameOnCard");
	By visaCardNumberTxt = By.id("number");
	By visaExpiryMonth = By.id("expiryMonth");
	By visaExpiryYear = By.id("expiryYear");
	By visaSecurityCode = By.id("securityCode");
	By visaPayBtn = By.id("pay-button");

	By acsEmulatorHeader = By
			.xpath("//*[@id=\"ContainerHeader\"]/table/tbody/tr/td/center/h1[contains(text(),'ACS Emulator')]");
	By visaSubmitBtn = By
			.xpath("//*[@id=\"ContainerContent\"]/center/form/table/tbody/tr[13]/td/input[@value='Submit']");

	// Elements related to KNET
	By knetHeader = By.id("PayPageEntry");
	By knetBankName = By.xpath("//*[@id=\"FCUseDebitEnable\"]/div[1]/div[2]/select");
	By knetDebitNumber = By.id("debitNumber");
	By knetExpiryMonth = By.xpath("//*[@id=\"cardExpdate\"]/div[2]/select[1]");
	By knetExpiryYear = By.xpath("//*[@id=\"cardExpdate\"]/div[2]/select[2]");
	By knetPin = By.id("cardPin");
	By knetSubmitBtn = By.xpath("//input[@value='Submit']");
	By knetConfirmBtn = By.id("proceedConfirm");
	By paymentError = By.xpath("//html/body/div/text()[1]");
	By paymentInvalidLoginError = By.xpath("//html/body/pre[contains(text(),'Error: Invalid login')]");

	// Variables for storing amounts
	Double actualConsultationFee = 0.0;
	Double actualTotalAmountForLab = 0.0;
	Double actualTotalAmountForPharmacy = 0.0;

	public CheckoutPage(WebDriver driver) {
		super();
	}

	// Verify applying of promotion for Doctor Service
	public String verifyPromotionForDoctor(String promoCode, String discountType, String discount, String tcID,
			String scenario) throws Exception {
		double result = 0.0;
		double actualDiscount = 0.0;
		double expectedDiscount = 0.0;
		scrollTillTop();
		waitForSpecificTime(5000);
		explicitWait(screenIdentifierCheckout);
		driver.findElement(promotionCodeTxtForDoctor).sendKeys(promoCode);
		driver.findElement(submitBtn).click();
		try {
			moveToElement(driver.findElement(discountLblForDoctor));
			if (driver.findElement(discountLblForDoctor).getText().trim().equalsIgnoreCase("Discount")) {
				if (discountType.equalsIgnoreCase("Flat")) {
					actualDiscount = roundNumberTo2Decimals(
							Double.parseDouble(driver.findElement(discountedAmountLblForDoctor).getText().trim()));
					expectedDiscount = roundNumberTo2Decimals(Double.parseDouble(discount));
				} else {
					actualConsultationFee = Double.parseDouble(driver.findElement(consultationFeeLbl).getText().trim());
					double promoDiscount = Double.parseDouble(discount);
					actualDiscount = roundNumberTo2Decimals(
							Double.parseDouble(driver.findElement(discountedAmountLblForDoctor).getText().trim()));
					expectedDiscount = roundNumberTo2Decimals(actualConsultationFee * (promoDiscount / 100));
				}
				if (actualDiscount == expectedDiscount) {
					result = actualDiscount;
					takeScreenShot(getDriver(), "DiscountWithPromotion", tcID, scenario);
				} else {
					result = 0.0;
				}
			}
		} catch (ArithmeticException ae) {
			System.out.println("Exception in finding the element:" + ae.getMessage());
		}
		return Double.toString(result);
	}

	// Verify applying of promotion for Lab Service
	public String verifyPromotionForLab(String promoCode, String discount, String tcID, String scenario)
			throws Exception {
		double result = 0.0;
		scrollTillTop();
		waitForSpecificTime(5000);
		explicitWait(screenIdentifierCheckout);
		driver.findElement(promotionCodeTxtForLab).sendKeys(promoCode);
		driver.findElement(submitBtn).click();
		moveToElement(driver.findElement(discoutLblForLab));
		if (driver.findElement(discoutLblForLab).getText().trim().equalsIgnoreCase("Discount")) {
			actualTotalAmountForLab = Double.parseDouble(driver.findElement(totalAmountForLab).getText().trim());
			double actualSubTotalAmount = Double.parseDouble(driver.findElement(subTotalAmountForLab).getText().trim());
			double promoDiscount = Double.parseDouble(discount);
			double actualDiscount = Double.parseDouble(driver.findElement(discountedAmountLblForLab).getText().trim());
			double expectedDiscount = actualSubTotalAmount * (promoDiscount / 100);
			if (actualDiscount == expectedDiscount) {
				result = actualDiscount;
				takeScreenShot(getDriver(), "DiscountWithPromotion", tcID, scenario);
			} else {
				result = 0.0;
			}
		}

		return Double.toString(result);
	}

	// Verify applying of promotion for pharmacy product
	public String verifyPromotionForPharmacy(String promoCode, String discount, String tcID, String scenario)
			throws Exception {
		double result = 0.0;
		scrollTillTop();
		waitForSpecificTime(5000);
		explicitWait(screenIdentifierCheckout);
		driver.findElement(promotionCodeTxtForPharmacy).sendKeys(promoCode);
		driver.findElement(submitBtn).click();
		scrollTillASpecificPoint(200);
		waitSometime();
		if (driver.findElement(discountLblForPharmacy).getText().trim().equalsIgnoreCase("Discount")) {
			actualTotalAmountForPharmacy = Double
					.parseDouble(driver.findElement(totalAmountForPharmacy).getText().trim());
			double actualSubTotalAmount = Double
					.parseDouble(driver.findElement(subTotalAmountForPharmacy).getText().trim());
			double promoDiscount = Double.parseDouble(discount);
			double actualDiscount = Double
					.parseDouble(driver.findElement(discountedAmountLblForPharmacy).getText().trim());
			double expectedDiscount = actualSubTotalAmount * (promoDiscount / 100);
			if (actualDiscount == expectedDiscount) {
				result = actualDiscount;
				takeScreenShot(getDriver(), "DiscountWithPromotion", tcID, scenario);
				scrollTillASpecificPoint(-300);
			} else {
				result = 0.0;
			}
		}
		return Double.toString(result);
	}

	// Verify promo Not applied error for Lab
	public boolean verifyPromoNotApplied_lab(String promoCode, String expectedError, String tcID, String scenario)
			throws Exception {
		boolean result = false;
		scrollTillTop();
		waitForSpecificTime(5000);
		explicitWait(screenIdentifierCheckout);
		driver.findElement(promotionCodeTxtForLab).sendKeys(promoCode);
		driver.findElement(submitBtn).click();
		result = validateToast(expectedError, tcID, scenario);
		return result;
	}

	// To get the total amount from checkout after discount
	public String getTotalAmountAfterDiscount() {
		return Double.toString(actualTotalAmountForPharmacy);
	}

	// Validate selection of payment method and proceeding to confirmation screen
	public boolean selectPaymentAndProceed(String paymentMode, String payOnlyBookingFee) {
		explicitWait_60(screenIdentifierCheckout);
		waitSometime();
		if (paymentMode.equalsIgnoreCase("WALLET")) {
			moveToElement(driver.findElement(walletBalanceTxt));
			driver.findElement(walletChkbx).click();
		}
		if (payOnlyBookingFee.equalsIgnoreCase("Yes")) {
			waitSometime();
			moveToElement(driver.findElement(selectPaymentMethod));
			driver.findElement(payOnlyBookingFeeChkbx).click();
			explicitWait(screenIdentifierCheckout);
		}
		switch (paymentMode) {
		case "CASH":
			explicitWait(cashModeBtn);
			moveToElement(driver.findElement(selectPaymentMethod));
			moveToElement(driver.findElement(cashModeBtn));
			driver.findElement(cashModeBtn).click();
			moveToElement(driver.findElement(proceedBtn));
			driver.findElement(proceedBtn).click();
			break;
		case "KNET":
			explicitWait(kentModeBtn);
			moveToElement(driver.findElement(selectPaymentMethod));
			moveToElement(driver.findElement(kentModeBtn));
			driver.findElement(kentModeBtn).click();
			moveToElement(driver.findElement(proceedBtn));
			driver.findElement(proceedBtn).click();
			break;
		case "VISA":
			explicitWait(creditCardModeBtn);
			moveToElement(driver.findElement(selectPaymentMethod));
			moveToElement(driver.findElement(creditCardModeBtn));
			driver.findElement(creditCardModeBtn).click();
			moveToElement(driver.findElement(proceedBtn));
			driver.findElement(proceedBtn).click();
			break;
		case "WALLET":
			moveToElement(driver.findElement(proceedBtn));
			driver.findElement(proceedBtn).click();
			break;
		}
		waitSometime();
		List<WebElement> targetElement = driver.findElements(futureAppointmentPopup);
		try {
			System.out.println("Size of elements: " + targetElement.size());
			if (targetElement.size() >= 1) {
				for (int i = 0; i < targetElement.size(); i++) {
					if (targetElement.get(i).isDisplayed()) {
						System.out.println("++++++caught popup++++++");
						driver.findElement(futureProceed).click();
						break;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Exception in finding the element:" + e.getMessage());
		}
		List<WebElement> targetElement1 = driver.findElements(pharmacyClosedPopup);
		try {
			System.out.println("Size of elements: " + targetElement1.size());
			if (targetElement1.size() >= 1) {
				for (int i = 0; i < targetElement1.size(); i++) {
					if (targetElement1.get(i).isDisplayed()) {
						System.out.println("++++++caught popup++++++");
						driver.findElement(pharmacyClosedProceed).click();
						break;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Exception in finding the element:" + e.getMessage());
		}
		return true;
	}

	// Validate booking confirmation page
	public boolean verifyBookingSuccess() {
		Boolean bookingIDPresent = false;
		moveToElement(driver.findElement(By.xpath(
				"//h2[contains(@class, 'booking-id-text') or contains(@class, 'booking-id-text mt-5 mb-5')]/span")));
		List<WebElement> targetElement = driver.findElements(By.xpath(
				"//h2[contains(@class, 'booking-id-text') or contains(@class, 'booking-id-text mt-5 mb-5')]/span"));
		try {
			System.out.println("Size of elements: " + targetElement.size());
			if (targetElement.size() >= 1) {
				for (int i = 0; i < targetElement.size(); i++) {
					if (targetElement.get(i).isDisplayed()) {
						System.out.println("Booking success!!!!!!!!!!");
						bookingIDPresent = true;
						break;
					} else {
						bookingIDPresent = false;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Exception in finding the element:" + e.getMessage());
		}
		return bookingIDPresent;
	}

	// validate visa details entry after proceeding with VISA payment method
	public boolean enterVISADetails(String firstName, String lastName, String address, String city, String country,
			String cardNumber, String expiryMonth, String expiryYear, String cardHolderName, String CVV,
			String username) throws Exception {
		boolean status = true;
		if (errorCheck(transactionFailedError) == true)
			return false;
		try {
			explicitWait(visaHeader1);
			// driver.findElement(firstNameTxt).sendKeys(firstName); waitSometime();
			// driver.findElement(lastNameTxt).sendKeys(lastName); waitSometime();

//		driver.findElement(addressTxt).sendKeys(address);
//		waitSometime();
//		driver.findElement(cityTxt).sendKeys(city);
//		waitSometime();
//		Select country1 = new Select(driver.findElement(countryTxt));
//		country1.selectByVisibleText(country);
//		waitSometime();

			driver.findElement(cardType_visa).click();
			waitSometime();
			driver.findElement(visaCardNumber1).sendKeys(cardNumber);
			waitSometime();
			Select expiryMonth1 = new Select(driver.findElement(visaExpiryMonth1));
			expiryMonth1.selectByValue(expiryMonth);
			waitSometime();
			Select expiryYear1 = new Select(driver.findElement(visaExpiryYear1));
			expiryYear1.selectByValue(expiryYear);
			waitSometime();
			driver.findElement(visaCVV1).sendKeys(CVV);
			waitSometime();
			// driver.findElement(payBtn1).click();
			driver.findElement(nextBtn).click();
			waitSometime();
			waitSometime();
			explicitWait(reviewOrderHeader);
			driver.findElement(payBtn).click();
			waitSometime();
			waitSometime();
			driver.switchTo().frame(driver.findElement(By.id("Cardinal-CCA-IFrame")));
			explicitWait(purchaseAuthHeader);
			driver.findElement(codeTxt).sendKeys("1234");
			driver.findElement(otp_submitBtn).click();
			driver.switchTo().defaultContent();

//		explicitWait(visaHeaderLbl);
//		driver.findElement(visaEmailId).sendKeys(username);
//		waitSometime();
//		driver.switchTo().frame(0);
//		driver.findElement(visaCardHolderNameTxt).sendKeys(cardHolderName); 
//		driver.switchTo().defaultContent();
//		waitSometime();
//		driver.switchTo().frame(1);
//		driver.findElement(visaCardNumberTxt).sendKeys(cardNumber);
//		driver.switchTo().defaultContent();
//		waitSometime();
//		driver.switchTo().frame(2);
//		driver.findElement(visaExpiryMonth).sendKeys(expiryMonth); 
//		driver.switchTo().defaultContent();
//		waitSometime();
//		driver.switchTo().frame(3);
//		driver.findElement(visaExpiryYear).sendKeys(expiryYear); 
//		driver.switchTo().defaultContent();
//		waitSometime();
//		driver.switchTo().frame(4);
//		driver.findElement(visaSecurityCode).sendKeys(CVV); 
//		driver.switchTo().defaultContent();
//		waitSometime();
//		driver.findElement(visaPayBtn).click();
//		waitSometime();
//		waitSometime();
//		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@name='redirectTo3ds1Frame']")));
//		explicitWait(acsEmulatorHeader);
//		driver.findElement(visaSubmitBtn).click();
//		driver.switchTo().defaultContent();
			waitForSpecificTime(10000);
			// explicitWait_60(printReceiptTxt);
			Assert.assertTrue(driver.findElement(printReceiptTxt).isDisplayed(),
					"Booking is unsuccessful due to some payment error");
			status = true;
		} catch (Exception e) {
			System.out.println("Exception in finding the element:" + e.getMessage());
			tearDown();
			status = false;
		}
		return status;
	}

	// validate knet details entry after proceeding with KNET payment method
	public boolean enterKNETDetails(String bankName, String cardNumber, String expiryMonth, String expiryYear,
			String pin) throws Exception {
		boolean status = true;
		try {
		explicitWait(knetHeader);
		Select banks = new Select(driver.findElement(knetBankName));
		banks.selectByValue(bankName);
		waitSometime();
		driver.findElement(knetDebitNumber).sendKeys(cardNumber);
		waitSometime();
		Select expiryMonth1 = new Select(driver.findElement(knetExpiryMonth));
		expiryMonth1.selectByValue(expiryMonth);
		waitSometime();
		Select expiryYear1 = new Select(driver.findElement(knetExpiryYear));
		expiryYear1.selectByValue(expiryYear);
		waitSometime();
		driver.findElement(knetPin).sendKeys(pin);
		waitSometime();
		driver.findElement(knetSubmitBtn).click();
		waitSometime();
		explicitWait(knetConfirmBtn);
		driver.findElement(knetConfirmBtn).click();
		waitForSpecificTime(10000);
		explicitWait_60(printReceiptTxt);
		Assert.assertTrue(driver.findElement(printReceiptTxt).isDisplayed(),
				"Booking is unsuccessful due to some payment error");
		}catch(Exception e) {
			tearDown();
			System.out.println("Exception in finding the element:" + e.getMessage());
			status = false;
		}
		return status;
	}

	// Validate retrieving booking ID from booking confirmation screen
	public String getBookingID() {
		System.out.println("Booking ID : " + driver.findElement(bookingID).getText());
		return driver.findElement(bookingID).getText();
	}

	// Validate retrieving lab grand total amount from booking confirmation screen
	public String getLabGrandTotalAmount() {
		System.out.println("Grand Total: " + driver.findElement(labGrandTotallbl).getText().trim().substring(2));
		return driver.findElement(labGrandTotallbl).getText().trim().substring(2);
	}

	// Validate retrieving doctor consultation fee from booking confirmation screen
	public String getDoctorConsultationFee() {
		return driver.findElement(doctorConsultationFee).getText().trim().substring(2);
	}

	// Validate retrieving doctor grand total amount from booking confirmation
	// screen
	public String getDoctorGrandTotalAmount(String payOnlyBookingFee) {
		if (payOnlyBookingFee.equalsIgnoreCase("Yes"))
			return driver.findElement(doctorBookingFeeLbl).getText().trim().substring(2);
		else
			return driver.findElement(doctorGrandTotalLbl).getText().trim().substring(3);
	}

	// Validate retrieving shop online delivery fee from order confirmation screen
	public String getShopOnineDeliveryFee() {
		scrollTillASpecificPoint(300);
		System.out.println("price: " + driver.findElement(shopOnlineDeliveryFee).getText().trim());
		return driver.findElement(shopOnlineDeliveryFee).getText().trim();
	}

	// Validate retrieving shop online total price from order confirmation screen
	public String getShopOnineTotalPrice() {
		scrollTillASpecificPoint(300);
		System.out.println("price: " + driver.findElement(shopOnlineTotalPrice).getText().trim());
		return driver.findElement(shopOnlineTotalPrice).getText().trim();
	}

	// Validate retrieving shop online sub total price from order confirmation
	// screen
	public String getShopOnineSubTotalPrice() {
		scrollTillASpecificPoint(300);
		System.out.println("price: " + driver.findElement(shopOnlineSubTotalPrice).getText().trim());
		return driver.findElement(shopOnlineSubTotalPrice).getText().trim();
	}

	// Validate insurance for order checkout page
	public boolean enterInsuranceDetailsForOrder(String enableInsurance, String insuranceCardNumber) {
		explicitWait(screenIdentifierCheckout);
		scrollTillASpecificPoint(500);
		waitSometime();
		if (enableInsurance.equalsIgnoreCase("Yes")) {
			driver.findElement(enableInsuranceChkbx).click();
			driver.findElement(By.xpath("//p[contains(text(),'" + insuranceCardNumber + "')]//preceding::span[1]"))
					.click();
			driver.findElement(proceedBtn).click();
			waitSometime();
			List<WebElement> targetElement1 = driver.findElements(pharmacyClosedPopup);
			try {
				System.out.println("Size of elements: " + targetElement1.size());
				if (targetElement1.size() >= 1) {
					for (int i = 0; i < targetElement1.size(); i++) {
						if (targetElement1.get(i).isDisplayed()) {
							System.out.println("++++++caught popup++++++");
							driver.findElement(pharmacyClosedProceed).click();
							break;
						}
					}
				}
			} catch (NoSuchElementException e) {
				System.out.println("Exception in finding the element:" + e.getMessage());
			}
			driver.findElement(claimDocumentUpload).click();
			waitSometime();
			uploadPDF();
			waitSometime();
			driver.findElement(claimProceedBtn).click();
			waitForSpecificTime(5000);
			System.out.println(
					"++++++++++++++++     " + driver.findElement(insuranceMessage).getText().trim().toUpperCase());
			Assert.assertEquals(driver.findElement(insuranceMessage).getText().trim().toUpperCase(),
					"YOUR ORDER HAS BEEN SUBMITTED FOR INSURANCE APPROVAL. WE WILL GET BACK TO YOU AT THE EARLIEST");
		}
		return true;
	}

	// Validate insurance for lab checkout page
	public boolean enterInsuranceDetailsForLab(String enableInsurance, String insuranceCardNumber) {
		explicitWait(screenIdentifierCheckout);
		scrollTillASpecificPoint(500);
		waitSometime();
		if (enableInsurance.equalsIgnoreCase("Yes")) {
			moveToElement(driver.findElement(l_enableInsuranceChkbx));
			driver.findElement(l_enableInsuranceChkbx).click();
			moveToElement(driver.findElement(By.xpath("//p[contains(text(),'" + insuranceCardNumber + "')]//preceding::span[1]")));
			driver.findElement(By.xpath("//p[contains(text(),'" + insuranceCardNumber + "')]//preceding::span[1]"))
					.click();
			moveToElement(driver.findElement(proceedBtn));
			driver.findElement(proceedBtn).click();
			waitSometime();
			List<WebElement> targetElement = driver.findElements(futureAppointmentPopup);
			try {
				System.out.println("Size of elements: " + targetElement.size());
				if (targetElement.size() >= 1) {
					for (int i = 0; i < targetElement.size(); i++) {
						if (targetElement.get(i).isDisplayed()) {
							System.out.println("++++++caught popup++++++");
							driver.findElement(futureProceed).click();
							break;
						}
					}
				}
			} catch (NoSuchElementException e) {
				System.out.println("Exception in finding the element:" + e.getMessage());
			}
			waitSometime();
			driver.findElement(claimDocumentUpload).click();
			waitSometime();
			uploadPDF();
			waitSometime();
			driver.findElement(claimProceedBtn).click();
			waitForSpecificTime(5000);
			System.out.println(
					"++++++++++++++++     " + driver.findElement(insuranceMessage).getText().trim().toUpperCase());
			Assert.assertEquals(driver.findElement(insuranceMessage).getText().trim().toUpperCase(),
					"YOUR REQUEST HAS BEEN SHARED FOR APPROVAL. YOU WILL BE NOTIFIED ONCE YOUR REQUEST IS PROCESSED");
		}
		return true;
	}

	// Validate insurance deduction in checkout page
	public boolean verifyInsuranceDeduction(String coveredByInsuranceAmount) throws Exception {
		waitSometime();
		boolean status;
		double total, amountAfterInsurance = 0, expectedPatientShare = 0;
		if (driver.findElement(screenIdentifierCheckout).getText().trim().equals("CHECKOUT")) {
			total = Double.parseDouble(driver.findElement(originalAmount).getText().substring(3));
			amountAfterInsurance = Double.parseDouble(driver.findElement(amountToBePaid).getText().substring(3));
			expectedPatientShare = total - Double.parseDouble(coveredByInsuranceAmount);
		}
		if (amountAfterInsurance == expectedPatientShare)
			status = true;
		else
			status = false;
		return status;
	}

	// Validate reschedule of doctor appointment from Manage Booking
	public void verifyReschedule() {
		explicitWait(manageBookingBtn);
		waitElementToBeClickable(manageBookingBtn);
		clickUsingJS(manageBookingBtn);
		waitElementToBeClickable(rescheduleBtn);
		driver.findElement(rescheduleBtn).click();
		explicitWait(rescheduleNowBtn);
		waitSometime();
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
		moveToElement(driver.findElement(rescheduleNowBtn));
		driver.findElement(rescheduleNowBtn).click();
	}

	// Validate reschedule of lab appointment from Manage Booking
	public void verifyRescheduleMS() {
		explicitWait(manageBookingBtn);
		driver.findElement(manageBookingBtn).click();
		waitForSpecificTime(1000);
		waitElementToBeVisible(rescheduleBtn);
		driver.findElement(rescheduleBtn).click();
		explicitWait(rescheduleNowBtn);
		waitSometime();
		List<WebElement> timeSlotCount = driver.findElements(By.xpath("//div[@class='row']/div"));
		for (int i = 2; i < timeSlotCount.size(); i++) {
			By time = By.xpath("//div[@class='row']/div[" + i + "]/label/span");
			String strTimeSlot = driver.findElement(time).getText();
			if (strTimeSlot.equalsIgnoreCase("BOOKED")) {
				continue;
			} else {
				driver.findElement(time).click();
				break;
			}
		}
		driver.findElement(rescheduleNowBtn).click();
	}

	// Validate original and discount price after insurance approval in Confirmation
	// screen
	public String verifyOriginalAndDiscountPrice_Lab(String discount, String tcID, String scenario) {
		double result1 = 0.0;
		double result2 = 0.0;
		Double expectedOriginalAmount = Double.parseDouble(discount) + actualTotalAmountForLab;
		Double expectedDiscountAmount = actualTotalAmountForLab;
		moveToElement(driver.findElement(labGrandTotal));
		double actualOriginalAmount = Double.parseDouble(driver.findElement(labGrandTotal).getText().trim());
		double actualDiscountedAmount = Double.parseDouble(driver.findElement(labAmountPaidTxt).getText().trim());
		if (expectedOriginalAmount == actualOriginalAmount && expectedDiscountAmount == actualDiscountedAmount) {
			result1 = actualOriginalAmount;
			result2 = actualDiscountedAmount;
			takeScreenShot(getDriver(), "OriginalAndDiscountAmount", tcID, scenario);
		} else {
			result1 = 0.0;
			result2 = 0.0;
		}
		return Double.toString(result1) + " " + Double.toString(result2);
	}

	// Validate original and discount price after promo discount in Confirmation
	// screen
	public String verifyOriginalAndDiscountPrice_Pharmacy(String discount, String tcID, String scenario) {
		double result1 = 0.0;
		double result2 = 0.0;
		Double expectedOriginalAmount = Double.parseDouble(discount) + actualTotalAmountForPharmacy;
		Double expectedDiscountAmount = actualTotalAmountForPharmacy;
		scrollTillASpecificPoint(300);
		double actualOriginalAmount = Double.parseDouble(driver.findElement(shopOnlineTotalPrice).getText().trim());
		double actualDiscountedAmount = Double.parseDouble(driver.findElement(shopOnlineAmountPaid).getText().trim());
		if (expectedOriginalAmount == actualOriginalAmount && expectedDiscountAmount == actualDiscountedAmount) {
			result1 = actualOriginalAmount;
			result2 = actualDiscountedAmount;
			takeScreenShot(getDriver(), "OriginalAndDiscountAmount", tcID, scenario);
			scrollTillASpecificPoint(-300);
		} else {
			result1 = 0.0;
			result2 = 0.0;
		}
		return Double.toString(result1) + " " + Double.toString(result2);
	}
}
