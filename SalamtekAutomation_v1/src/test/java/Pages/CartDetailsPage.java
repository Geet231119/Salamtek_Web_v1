package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Common.BaseTest;

public class CartDetailsPage extends BaseTest{
	By addYourDocumentLnk = By.xpath("//app-cart-details/section/div[1]/div/div[3]/div/div[1]/div[3]/div/div[3]/div/h5");
	By paymentSummaryHeader = By.xpath("//h4[contains(text(),'PAYMENT SUMMARY')]");
	By proceedCheckoutBtn = By.xpath("//button[contains(text(),'PROCEED CHECKOUT')]");
	By proceedToCheckoutBtn = By.xpath("//app-eprescriptions-listing/section/div/div/div[4]/div[2]/div[2]/div[2]/button[contains(text(),'PROCEED TO CHECKOUT')]");
	By checkoutHeader = By.xpath("//div[@class='CART-DETAILS-PAYMENT-SUMMARY']/h4[contains(text(),'CHECKOUT')]");
	By chooseServiceTxt = By.xpath("//p[contains(text(),'Choose the Service that fits you')]");
	By totalAmount = By.xpath("//app-cart-details/section/div[1]/div/div[3]/div/div[2]/div[2]/div[1]/ul[4]/li[2]");
	By totalAmountAfterInsurance = By.xpath("//app-cart-details/section/div[1]/div/div[3]/div/div[2]/div[2]/div[1]/ul[5]/li[2]");
	
	public CartDetailsPage(WebDriver driver) 
	{
		super();
	}
	
	//Validate proceed to checkout
	public boolean verifyProceedCheckout(String uploadNeeded) throws Exception{
	    waitSometime();
	    if(driver.findElement(proceedCheckoutBtn).getText().trim().equals("PROCEED CHECKOUT")) {
				if(uploadNeeded.equalsIgnoreCase("Yes")) {
					driver.findElement(addYourDocumentLnk).click();
					waitSometime();
					uploadPDF();
					waitSometime();
					moveToElement(driver.findElement(proceedCheckoutBtn));
					driver.findElement(proceedCheckoutBtn).click();
				}
				else {
					moveToElement(driver.findElement(proceedCheckoutBtn));
				    driver.findElement(proceedCheckoutBtn).click();
				}
		}
	    waitForSpecificTime(5000);
		System.out.println("++++++++++++++++     "+driver.findElement(checkoutHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(checkoutHeader).getText().trim().toUpperCase(), "CHECKOUT");
		return true;
	}
	
	public boolean verifyProceedToCheckout(String uploadNeeded) throws Exception{
	    waitSometime();
	    explicitWait(proceedToCheckoutBtn);
	    if(driver.findElement(proceedToCheckoutBtn).getText().trim().contains("PROCEED TO CHECKOUT")) {
				if(uploadNeeded.equalsIgnoreCase("Yes")) {
					driver.findElement(addYourDocumentLnk).click();
					waitSometime();
					uploadPDF();
					waitSometime();
					driver.findElement(proceedToCheckoutBtn).click();
				}
				else {
					driver.findElement(proceedToCheckoutBtn).click();
				}
		}
	    waitForSpecificTime(5000);
		System.out.println("++++++++++++++++     "+driver.findElement(checkoutHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(checkoutHeader).getText().trim().toUpperCase(), "CHECKOUT");
		return true;
	}
	
	public boolean verifyProceedToCheckoutForConductTest(String uploadNeeded) throws Exception{
	    waitSometime();
	    if(driver.findElement(proceedToCheckoutBtn).getText().trim().equals("PROCEED TO CHECKOUT")) {
				if(uploadNeeded.equalsIgnoreCase("Yes")) {
					driver.findElement(addYourDocumentLnk).click();
					waitSometime();
					uploadPDF();
					waitSometime();
					driver.findElement(proceedToCheckoutBtn).click();
				}
				else {
				    driver.findElement(proceedToCheckoutBtn).click();
				}
		}
	    waitForSpecificTime(5000);
		System.out.println("++++++++++++++++     "+driver.findElement(chooseServiceTxt).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(chooseServiceTxt).getText().trim().toUpperCase(), "CHOOSE THE SERVICE THAT FITS YOU");
		return true;
	}
	
	public boolean verifyInsuranceDeduction(String coveredByInsuranceAmount) throws Exception{
	    waitSometime();
	    boolean status;
	    double total, amountAfterInsurance = 0, expectedPatientShare = 0;
	    if(driver.findElement(proceedCheckoutBtn).getText().trim().equals("PROCEED CHECKOUT")) {
			total = Double.parseDouble(driver.findElement(totalAmount).getText().substring(3));
			amountAfterInsurance = Double.parseDouble(driver.findElement(totalAmountAfterInsurance).getText().substring(3));
			expectedPatientShare = total - Double.parseDouble(coveredByInsuranceAmount);			
		}
	    if(amountAfterInsurance == expectedPatientShare)
	    	status = true;
	    else
	    	status = false;
		return status;
	}
	
}
