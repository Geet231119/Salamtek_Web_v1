package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.BaseTest;

public class CartMainPage extends BaseTest{
	By cartHeader = By.xpath("//h4[contains(text(),'MY CART')]");
	By cartListElement = By.xpath("//div[contains(@class,'SIGNAL-CART')]");
	By paymentSummaryHeader = By.xpath("//app-cart-details/section/div[1]/div/div[3]/div/div[2]/div[1]/h4[contains(text(),'PAYMENT SUMMARY')]");
	
	public CartMainPage(WebDriver driver) 
	{
		super();
	}
	
	//Validate product correctly added in the cart
	public boolean verifyProductListInCart(String pharmacyName) throws Exception{
	    waitSometime();	
	    explicitWait(cartHeader);
	    moveToElement(driver.findElement(cartHeader));
	    waitSometime();
	    List<WebElement> cartList = driver.findElements(cartListElement);
		int cartListCnt = cartList.size();
		if(cartListCnt == 0) {
			waitSometime();
			String cartItem = driver.findElement(By.xpath("//div[contains(@class,'SIGNAL-CART')]/div/div/div[2]/div/h4")).getText();
			System.out.println(cartItem);
			waitSometime();
			if(cartItem.trim().toUpperCase().equals(pharmacyName.toUpperCase())) {
				driver.findElement(By.xpath("//div[contains(@class,'SIGNAL-CART')]/div/div/div[5]/div/div[1]/div/button")).click();
			}
		}
		else {
			for(int i=1;i<=cartListCnt;i++) {
				waitSometime();
				String cartItem = driver.findElement(By.xpath("//div[contains(@class,'SIGNAL-CART')]["+i+"]/div/div/div[2]/div/h4")).getText();
				System.out.println(cartItem);
				waitSometime();
				if(cartItem.trim().toUpperCase().equals(pharmacyName.toUpperCase())) {
					driver.findElement(By.xpath("//div[contains(@class,'SIGNAL-CART')]["+i+"]/div/div/div[6]/div/div[1]/div/button")).click();
					break;
				}
			}
		}
		waitForSpecificTime(5000);
		explicitWait(paymentSummaryHeader);
		System.out.println("++++++++++++++++     "+driver.findElement(paymentSummaryHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(paymentSummaryHeader).getText().trim().toUpperCase(), "PAYMENT SUMMARY");
		return true;
	}
}
