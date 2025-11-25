package Pages;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Common.BaseTest;

public class OffersDetailsPage extends BaseTest{
	By offerDetailsPageHeader = By.xpath("//app-offer-listing/section/section[2]/div/div/div[2]/div[3]/div/div/div[1]/p[contains(text(),'Find Every Deal')]");
	By filterByCategoryLbl = By.xpath("//app-offer-listing/section/section[2]/div/div/div[2]/div[1]/div[2]/div[2]/div/div/div/h2/button[contains(text(),'Category')]");
	
	public OffersDetailsPage(WebDriver driver) 
	{
		super();
	}
	
	//Validate filtering by medical service
	public boolean selectOfferCategory(String offerCategory) throws Exception{
	    waitSometime();	
	    explicitWait(offerDetailsPageHeader);
	    driver.findElement(filterByCategoryLbl).click();
	    waitSometime();
	    By filterByMedicalServiceLbl = By.xpath("//app-offer-listing/section/section[2]/div/div/div[2]/div[1]/div[2]/div[2]/div/div/div/div/div/div/div[3]/div/label[contains(text(),'"+offerCategory+"')]");
	    moveToElement(driver.findElement(filterByMedicalServiceLbl));
	    driver.findElement(filterByMedicalServiceLbl).click();
	    waitSometime();
		return true;
	}
	
	//Validate selection of offer from selected offer category
	public boolean selectOfferFromCategory(String offerImageName) throws Exception{
	    List<WebElement> offerList = driver.findElements(By.xpath("//app-offer-listing/section/section[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div"));
		int offerListCnt = offerList.size();
		System.out.println("Count of the offers in the list: "+ offerListCnt);
		for(int i=1;i<=offerListCnt;i++) {
			WebElement offerImageEle = driver.findElement(By.xpath("//app-new-single-offer/div/div/div/div[1]/img[1]"));
			moveToElement(offerImageEle);
			if(offerImageEle.isDisplayed()) {
				WebElement buyNowBtn = driver.findElement(with(By.xpath("//span[contains(text(),'BUY NOW')]")).below(offerImageEle));
				moveToElement(buyNowBtn);
				buyNowBtn.click();
				break;
			}
		}
		waitSometime();
		return true;
	}
	
	//Validate offers from offer list
	public boolean verifyOfferFromOfferList(String offerImageName, String tcID, String sheet) throws Exception{
	    boolean status = false;
	    moveToElement(driver.findElement(By.xpath("//app-offer-listing/section/section[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div")));
	    List<WebElement> offerList = driver.findElements(By.xpath("//app-offer-listing/section/section[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div"));
		int offerListCnt = offerList.size();
		System.out.println("Count of the offers in the list: "+ offerListCnt);
		for (int i = 1; i <= offerListCnt; i++) {
			String actualSrcValue = driver.findElement(By.xpath("//app-offer-listing/section/section[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[" +i+ "]/app-new-single-offer/div/div/div/div[1]/img")).getAttribute("src");
			if (actualSrcValue.equalsIgnoreCase(offerImageName)) {
				waitSometime();
				status = true;
				takeScreenShot(driver, "OfferSearchResult", tcID, sheet);
				break;
			}
			if (i % 2 == 0) {
				scrollTillASpecificPoint(200);
				waitSometime();
			}
		}
		waitSometime();
		return status;
	}
}
