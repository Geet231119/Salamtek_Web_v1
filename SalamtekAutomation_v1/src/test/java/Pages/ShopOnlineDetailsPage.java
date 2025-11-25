package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class ShopOnlineDetailsPage extends BaseTest{
	
	By pharmacyHeader = By.xpath("//div[@class='DOCTER-PROFILE-NAME']/h2");
	By addToCartBtn = By.xpath("//div[@class='DOCTER-PROFILE-BUTTON']/div[3]/button");
	By cartLnk = By.xpath("//*[@id=\"navbarNav\"]/div[1]/ul/li[4]/a");
	By goToMyCartBtn = By.xpath("//button[contains(text(),'GO TO MY CART')]");
	By cartHeader = By.xpath("//h4[contains(text(),'MY CART')]");

	
	public ShopOnlineDetailsPage(WebDriver driver) 
	{
		super();
	}
	
	//Validate addition of product to cart
	public boolean addProductToCart(String productName, String colorPresent) throws Exception{
	    waitSometime();	
	    explicitWait(pharmacyHeader);
	    String productHeader = driver.findElement(pharmacyHeader).getText().trim().toUpperCase();
		System.out.println("pdt====="+productHeader);
		System.out.println("pdt====="+productName.toUpperCase());
		if(productHeader.equals(productName.trim().toUpperCase())) {
			if(colorPresent.equalsIgnoreCase("Yes")) {
				By color = By.xpath("//div[3]/div/div[@class='PRODUCT-SIZE']/ul/li/label");
				driver.findElement(color).click();
				moveToElement(driver.findElement(addToCartBtn));
				driver.findElement(addToCartBtn).click();
			}
			else {
			moveToElement(driver.findElement(addToCartBtn));
			driver.findElement(addToCartBtn).click();
			}
		}
		waitSometime();
		driver.findElement(cartLnk).click();
		waitSometime();
		driver.findElement(goToMyCartBtn).click();
		waitSometime();
		explicitWait(cartHeader);
		System.out.println("++++++++++++++++     "+driver.findElement(cartHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(cartHeader).getText().trim().toUpperCase(), "MY CART", "Cart header is not present");
		return true;
	}
	
	//Validating Go to cart section
	public boolean goToCart() {
		waitSometime();
		driver.findElement(cartLnk).click();
		waitSometime();
		driver.findElement(goToMyCartBtn).click();
		waitSometime();
		explicitWait(cartHeader);
		System.out.println("++++++++++++++++     "+driver.findElement(cartHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(cartHeader).getText().trim().toUpperCase(), "MY CART", "Cart header is not present");
		return true;
	}
}
