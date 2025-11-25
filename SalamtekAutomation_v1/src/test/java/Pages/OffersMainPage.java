package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class OffersMainPage extends BaseTest{
	By offerHeader = By.xpath("//app-offers-home/div[2]/div[1]/div/div/div/div/h2[contains(text(),'TODAY EXCLUSIVE DEALS')]");
	By viewAllTodayOfferBtn = By.xpath("//app-offers-home/div[2]/div[1]/div/div/div/div/div[1]/div/div/div/button[contains(text(),'VIEW ALL TODAY OFFERS')]");
	By offerDetailsPageHeader = By.xpath("//app-offer-listing/section/section[2]/div/div/div[2]/div[3]/div/div/div[1]/p[contains(text(),'Find Every Deal')]");
	By whatAreYouLookingForHeader = By.xpath("//app-offers-home/div[1]/div/div/div/div/div[1]/div/h2/span[contains(text(),'WHAT ARE YOU LOOKING FOR')]");
	By searchOfferNameTxt = By.xpath("//app-offers-home/div[1]/div/div/div/div/div[2]/div/div[2]/div/input[@type='search']");
	By searchBtn = By.xpath("//app-offers-home/div[1]/div/div/div/div/div[2]/div/div[3]/div/button[contains(text(),'SEARCH')]");
	
	
	public OffersMainPage(WebDriver driver) 
	{
		super();
	}
	
	//Validate all the offers
	public boolean selectAllOffers() throws Exception{
	    waitSometime();	
		if(driver.findElement(offerHeader).isDisplayed()) {
			try {
				moveToElement(driver.findElement(viewAllTodayOfferBtn));
				clickUsingJS(viewAllTodayOfferBtn);
			}
			catch(Exception e) {
				System.out.println("Element not found: " + e.getMessage());
			}
		}
		waitSometime();
		explicitWait(offerDetailsPageHeader);
		System.out.println("++++++++++++++++     "+driver.findElement(offerDetailsPageHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(offerDetailsPageHeader).getText().trim().toUpperCase(), "FIND EVERY DEAL, OFFER , OR EVEN HOT PACKAGES FROM MEDICINE & MEDICAL DEVICES", "Product is not present");
		return true;
	}
	
	//Validate search offers
	public boolean searchOffer(String searchName, String tcID, String scenario) throws Exception{
		waitSometime();
		explicitWait(whatAreYouLookingForHeader);
		moveToElement(driver.findElement(searchOfferNameTxt));
		driver.findElement(searchOfferNameTxt).sendKeys(searchName);
		waitSometime();
		takeScreenShot(driver, "SearchInOfferMainPage", tcID, scenario);
		moveToElement(driver.findElement(searchBtn));
		driver.findElement(searchBtn).click();
		waitSometime();
		explicitWait(offerDetailsPageHeader);
		System.out.println("++++++++++++++++     "+driver.findElement(offerDetailsPageHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(offerDetailsPageHeader).getText().trim().toUpperCase(), "FIND EVERY DEAL, OFFER , OR EVEN HOT PACKAGES FROM MEDICINE & MEDICAL DEVICES", "Product is not present");
		return true;
	}
}
