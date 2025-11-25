package Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.BaseTest;

public class StoreProfileOrdersPage extends BaseTest {
	By ordersScreenIdentifier = By.xpath("//ngx-shop-order-index/nb-card/nb-card-header[contains(text(),'Orders')]");
	By orderNumberTxt = By.xpath(
			"//ngx-shop-order-index/nb-card/nb-card-body/ng2-smart-table/table/thead/tr[2]/th[3]/ng2-smart-table-filter/div/default-table-filter/input-filter/input");
	By viewBtn = By.xpath(
			"//ngx-shop-order-index/nb-card/nb-card-body/ng2-smart-table/table/tbody/tr/td[2]/ng2-st-tbody-custom/a/i");
	By orderInformationHeader = By.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[1]/table/tr[1]/th");
	By addItemsBtn = By.xpath("//button[contains(text(),'Add Items')]");
	By selectProductTxt = By.xpath(
			"//app-add-item-popup/nb-card/nb-card-body/form/div[1]/nb-card-body/div[2]/div/div/ng-select/div/div/div[2]/input");
	By selectProductDrpDwn = By.xpath(
			"//app-add-item-popup/nb-card/nb-card-body/form/div[1]/nb-card-body/div[2]/div/div/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By addItemsSaveBtn = By
			.xpath("//app-add-item-popup/nb-card/nb-card-body/form/div[4]/div/button[contains(text(),'Save')]");
	By newOrderIDLbl = By.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[1]/table/tr[1]/th");
	By itemOrderedHeader = By.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[5]/table/tr[1]/th");
	By addedProductInItemList = By.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[5]/table/tr[3]/td[1]");
	By expectedStatusLbl = By.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[1]/table/tr[3]/td");
	By grandTotalLbl = By.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[4]/table/tr[8]/td");
	By statusHistoryHeader = By.xpath(
			"//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[6]/table/tr[1]/th[contains(text(),'STATUS HISTORY')]");
	By selectStatusTxt = By.xpath(
			"//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[6]/table/tr[2]/td/form/div[1]/ng-select/div/div/div[2]/input");
	By selectStatusDrpDwn = By.xpath(
			"//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[6]/table/tr[2]/td/form/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By selectRefyndTypeTxt = By.xpath(
			"//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[6]/table/tr[2]/td/form/div[2]/ng-select/div/div/div[3]/input");
	By selectRefundTypeDrpDwn = By.xpath(
			"//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[6]/table/tr[2]/td/form/div[2]/ng-select/ng-dropdown-panel/div/div[2]/div/span");
	By saveBtn = By.xpath("//button[contains(text(),'Save')]");
	By notifyCustomerChkBx = By.name("notifyCustomer");
	By statusAddedToHistory = By.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[6]/table/tr[3]/td/p[1]");
	By lastStatusAddedToHistory = By
			.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[6]/table/tr[3]/td/p[1]");
	By claimImageLnk = By.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[1]/table/tr[7]/td/li/a");
	By civilIDImageLnk = By.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[5]/div/div/table/tr[6]/td/a");
	By cashReceivedTxt = By.xpath(
			"//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[4]/table/tr[14]/th[contains(text(),'Cash Received?')]");
	By cashReceivedChkBx = By
			.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[4]/table/tr[14]/td/div/label");
	By resultLbl = By.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[4]/table/tr[9]/td");

	// Elements related to insurance
	By approveOrderBtn = By.xpath("//ngx-shop-order-view/div/div/div/button[contains(text(),'Approve Order?')]");
	By rejectOrderBtn = By.xpath("//ngx-shop-order-view/div/div/div/button[contains(text(),'Reject Order?')]");
	By approveOrderHeader = By
			.xpath("//*[@class='cdk-overlay-pane']/nb-dialog-container/app-approval/nb-card/nb-card-header");
	By coveredByInsuranceTxt = By.xpath("//input[@formcontrolname='discount']");
	By insuranceDocumentBtn = By.xpath("//input[@formcontrolname='insuranceApprovalDocument']");
	By insuranceSaveBtn = By.xpath(
			"//*[@class=\"cdk-overlay-pane\"]/nb-dialog-container/app-approval/nb-card/nb-card-body/form/div[3]/div/button");
	By insuranceCardDetailsHeader = By.xpath(
			"//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[5]/div/div/table/tr[1]/th[contains(text(),'Insurance Card Details')]");
	By insuranceImageLnk = By
			.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[5]/div/div/table/tr[8]/td/a");

	// Elements in Payment Information section
	By paymentMethodLbl = By.xpath("//th[contains(text(),'Payment Method')]");
	By paymentMethodVal = By.xpath("//th[contains(text(),'Payment Method')]/following-sibling::td");
	By currencyLbl = By.xpath("//th[contains(text(),'Currency')]");
	By currencyVal = By.xpath("//th[contains(text(),'Currency')]/following-sibling::td");
	By orderAmountLbl = By.xpath("//th[contains(text(),'Order Amount')]");
	By orderAmountVal = By.xpath("//th[contains(text(),'Order Amount')]/following-sibling::td");
	By subTotalLbl = By.xpath("//th[contains(text(),'Subtotal')]");
	By subTotalVal = By.xpath("//th[contains(text(),'Subtotal')]/following-sibling::td");
	By deliveryFeeLbl = By.xpath("//th[contains(text(),'Delivery Fee')]");
	By deliveryFeeVal = By.xpath("//th[contains(text(),'Delivery Fee')]/following-sibling::td");
	By paidByKnetLbl = By.xpath("//th[contains(text(),'Paid By')]");
	By paidByKnetVal = By.xpath("//th[contains(text(),'Paid By')]/following-sibling::td");
	By grandLbl = By.xpath("//th[contains(text(),'Grand Total')]");
	By grandVal = By.xpath("//th[contains(text(),'Grand Total')]/following-sibling::td");

	// Elements in Order Summary section
	By orderSummaryHeader = By.xpath("//th[contains(text(),'ORDER SUMMARY')]");
	By orderAmountLbl_OS = By.xpath("//strong[contains(text(),'Order Amount')]");
	By orderAmountVal_OS = By.xpath("//strong[contains(text(),'Order Amount')]/parent::div/following-sibling::div");
	By subTotalLbl_OS = By.xpath("//strong[contains(text(),'Subtotal')]");
	By subTotalVal_OS = By.xpath("//strong[contains(text(),'Subtotal')]/parent::div/following-sibling::div");
	By deliveryFeeLbl_OS = By.xpath("//strong[contains(text(),'Delivery Fee')]");
	By deliveryFeeVal_OS = By.xpath("//strong[contains(text(),'Delivery Fee')]/parent::div/following-sibling::div");
	By grandLbl_OS = By.xpath("//strong[contains(text(),'Grand Total')]");
	By grandVal_OS = By.xpath("//strong[contains(text(),'Grand Total')]/parent::div/following-sibling::div");
	By paymentMethodLbl_OS = By.xpath("//strong[contains(text(),'Payment Method')]");
	By paymentMethodVal_OS = By.xpath("//strong[contains(text(),'Payment Method')]/parent::div/following-sibling::div");
	By paidByKnetLbl_OS = By.xpath("//strong[contains(text(),'Paid By')]");
	By paidByKnetVal_OS = By.xpath("//strong[contains(text(),'Paid By')]/parent::div/following-sibling::div");

	public StoreProfileOrdersPage(WebDriver driver) {
		super();
	}

	// Validate whether order is created or not
	public boolean verifyOrderPresent(String orderID) throws Exception {
		waitForSpecificTime(10000);
		explicitWait(ordersScreenIdentifier);
		waitSometime();
		driver.findElement(orderNumberTxt).clear();
		driver.findElement(orderNumberTxt).sendKeys(orderID.substring(1));
		waitSometime();
		driver.findElement(viewBtn).click();
		waitForSpecificTime(3000);
		System.out.println(
				"++++++++++++++++     " + driver.findElement(orderInformationHeader).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(orderInformationHeader).getText().trim().toUpperCase(),
				"ORDER # " + orderID.substring(1) + "A");
		return true;
	}

	// Validate claim, insurance, civil ID images for insurance order
	public boolean verifyClaim_Insurance_CivilID(String orderID, String tcID, String scenario) {
		int counter = 0;
		boolean result = false;
		explicitWait(orderInformationHeader);
		if (driver.findElement(orderInformationHeader).getText().trim().toUpperCase()
				.equalsIgnoreCase("ORDER # " + orderID.substring(1) + "A")) {
			if (driver.findElement(claimImageLnk).isDisplayed()) {
				driver.findElement(claimImageLnk).click();
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(2));
				waitSometime();
				takeScreenShot(driver, "ClaimImage", tcID, scenario);
				driver.close();
				counter += 1;
				driver.switchTo().window(tabs.get(1));
				WebElement insuranceCardDetailsHeaderEle = driver.findElement(insuranceCardDetailsHeader);
				scrollTillElementVisible(insuranceCardDetailsHeaderEle);
				waitSometime();
				scrollTillASpecificPoint(-200);
				waitSometime();
			}
			if (driver.findElement(civilIDImageLnk).isDisplayed()) {
				driver.findElement(civilIDImageLnk).click();
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(2));
				waitSometime();
				takeScreenShot(driver, "CivilIDImage", tcID, scenario);
				driver.close();
				counter += 1;
				driver.switchTo().window(tabs.get(1));
				waitSometime();
			}
			if (driver.findElement(insuranceImageLnk).isDisplayed()) {
				driver.findElement(insuranceImageLnk).click();
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(2));
				waitSometime();
				takeScreenShot(driver, "InsuranceImage", tcID, scenario);
				driver.close();
				counter += 1;
				ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs1.get(1));
				waitSometime();
				driver.navigate().refresh();
				waitSometime();
			}
		}
		if (counter == 3) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	// Validate Approval/Rejection of insurance orders
	public boolean verifyApproveOrRejectInsurance(String coveredByInsuranceAmount, String approveOrReject)
			throws Exception {
		explicitWait(orderInformationHeader);
		if (approveOrReject.equalsIgnoreCase("Approve")) {
			driver.findElement(approveOrderBtn).click();
			waitSometime();
			explicitWait(approveOrderHeader);
			driver.findElement(coveredByInsuranceTxt).sendKeys(coveredByInsuranceAmount);
			waitSometime();
			clickUsingJS(insuranceDocumentBtn);
			waitSometime();
			uploadPDF();
			waitSometime();
			driver.findElement(insuranceSaveBtn).click();
		} else {
			driver.findElement(rejectOrderBtn).click();
		}
		return true;
	}

	// Validate order status change
	public boolean verifyOrderStatusChange(String status, String tcID, String sheet) throws Exception {
		String expectedStatus = "";
		explicitWait(orderInformationHeader);
		scrollTillElementVisible(driver.findElement(statusHistoryHeader));
		waitSometime();
		driver.findElement(selectStatusTxt).sendKeys(status);
		driver.findElement(selectStatusDrpDwn).click();
		waitSometime();
		driver.findElement(saveBtn).click();
		waitSometime();
		takeScreenShot(driver, "Order " + status, tcID, sheet);
		List<WebElement> targetElement = driver
				.findElements(By.xpath("//ngx-shop-order-view/nb-card[2]/nb-card-body/div/div[6]/table/tr[2]/td/p[1]"));
		try {
			System.out.println("Size of elements: " + targetElement.size());
			if (targetElement.size() >= 1) {
				for (int i = 0; i < targetElement.size(); i++) {
					if (targetElement.get(i).isDisplayed()) {
						expectedStatus = targetElement.get(i).getText();
						break;
					}
				}
			} else
				expectedStatus = driver.findElement(statusAddedToHistory).getText();
		} catch (NoSuchElementException e) {
			expectedStatus = driver.findElement(statusAddedToHistory).getText();
		}

		int startOfdelimiter = expectedStatus.indexOf('|');
		System.out.println("++++++++++++++++     "
				+ expectedStatus.substring(startOfdelimiter + 1, expectedStatus.length()).trim().toUpperCase());
		Assert.assertEquals(
				expectedStatus.substring(startOfdelimiter + 1, expectedStatus.length()).trim().toUpperCase(),
				status.trim().toUpperCase());
		scrollTillTop();
		return true;
	}

	// Cancelling an order and refunding to Wallet
	public String verifyCancelOrder(String status, String refundType, String tcID, String sheet) throws Exception {
		explicitWait(orderInformationHeader);
		scrollTillElementVisible(driver.findElement(statusHistoryHeader));
		waitSometime();
		driver.findElement(selectStatusTxt).sendKeys(status);
		driver.findElement(selectStatusDrpDwn).click();
		waitSometime();
		driver.findElement(selectRefyndTypeTxt).sendKeys(refundType);
		driver.findElement(selectRefundTypeDrpDwn).click();
		waitSometime();
		driver.findElement(notifyCustomerChkBx).click();
		waitSometime();
		takeScreenShot(driver, "CancellationFromVendor", tcID, sheet);
		driver.findElement(saveBtn).click();
		waitSometime();
		driver.navigate().refresh();
		waitSometime();
		System.out.println("++++++++++++++++     " + driver.findElement(expectedStatusLbl).getText().trim());
		Assert.assertEquals(driver.findElement(expectedStatusLbl).getText().trim(), status.trim(),
				"Status is NOT as expected");
		System.out.println("Grand Total: " + driver.findElement(grandTotalLbl).getText().trim());
		return driver.findElement(grandTotalLbl).getText().trim();
	}

	// Validate add item to existing order
	public String verifyAddItemToOrder(String productToBeAdded, String tcID, String sheet) throws Exception {
		String newOrderID = "";
		explicitWait(orderInformationHeader);
		driver.findElement(addItemsBtn).click();
		waitSometime();
		driver.findElement(selectProductTxt).sendKeys(productToBeAdded);
		waitSometime();
		driver.findElement(selectProductTxt).sendKeys(Keys.DOWN);
		driver.findElement(selectProductTxt).sendKeys(Keys.RETURN);
		waitSometime();
		takeScreenShot(driver, "AddedItemToAnOrder", tcID, sheet);
		driver.findElement(addItemsSaveBtn).click();
		waitForSpecificTime(5000);
		takeScreenShot(driver, "NewOrderDetails", tcID, sheet);
		newOrderID = driver.findElement(newOrderIDLbl).getText().trim();
		scrollTillElementVisible(driver.findElement(itemOrderedHeader));
		System.out.println("++++++++++++++++     " + driver.findElement(addedProductInItemList).getText().trim());
		Assert.assertEquals(driver.findElement(addedProductInItemList).getText().trim(), productToBeAdded.trim(),
				"Product in the list is NOT same as added through the add items pop up ");
		return newOrderID;
	}

	// Validate Cash Received
	public String verifyCashReceivedAndComplete(String tcID, String sheet) throws Exception {
		driver.navigate().refresh();
		waitSometime();
		scrollTillElementVisible(driver.findElement(cashReceivedTxt));
		waitSometime();
		driver.findElement(cashReceivedChkBx).click();
		waitSometime();
		takeScreenShot(driver, "CashReceivedChecked", tcID, sheet);
		System.out.println("++++++++++++++++     " + driver.findElement(resultLbl).getText().trim());
		Assert.assertEquals(driver.findElement(resultLbl).getText().trim(), "PAID", "Still result is NOT PAID");
		return driver.findElement(resultLbl).getText().trim();
	}

	// Verify payment information in order details page
	public boolean verifyPaymentInfo(String paymentMode, String subTotal, String deliveryFee, String grandTotal,
			String tcID, String sheetName) {
		boolean status = false;
		int counter = 0;
		if (paymentMode.equalsIgnoreCase("KNET")) {
			paymentMode = "K-Net";
		}
		else if(paymentMode.equalsIgnoreCase("VISA")) {
			paymentMode = "Credit Card";
		}
		if ((driver.findElement(paymentMethodLbl).getText().trim().equalsIgnoreCase("Payment Method"))
				&& (driver.findElement(paymentMethodVal).getText().trim().equalsIgnoreCase(paymentMode))) {
			counter++;
		}
		if ((driver.findElement(currencyLbl).getText().trim().equalsIgnoreCase("Currency"))
				&& (driver.findElement(currencyVal).getText().trim().equalsIgnoreCase("KWD"))) {
			counter++;
		}
		if ((driver.findElement(orderAmountLbl).getText().trim().equalsIgnoreCase("Order Amount"))
				&& (driver.findElement(orderAmountVal).getText().trim().equalsIgnoreCase("KWD " + subTotal))) {
			counter++;
		}
		if ((driver.findElement(subTotalLbl).getText().trim().equalsIgnoreCase("Subtotal"))
				&& (driver.findElement(subTotalVal).getText().trim().equalsIgnoreCase("KWD " + subTotal))) {
			counter++;
		}
		if ((driver.findElement(deliveryFeeLbl).getText().trim().equalsIgnoreCase("Delivery Fee"))
				&& (driver.findElement(deliveryFeeVal).getText().trim().equalsIgnoreCase("KWD " + deliveryFee))) {
			counter++;
		}
		if (paymentMode.equalsIgnoreCase("K-Net") || paymentMode.equalsIgnoreCase("Credit Card")) {
			if ((driver.findElement(paidByKnetLbl).getText().trim().contains("Paid By"))
					&& (driver.findElement(paidByKnetVal).getText().trim().equalsIgnoreCase("KWD " + grandTotal))) {
				counter++;
			}
		}
		if ((driver.findElement(grandLbl).getText().trim().equalsIgnoreCase("Grand Total"))
				&& (driver.findElement(grandVal).getText().trim().equalsIgnoreCase("KWD " + grandTotal))) {
			counter++;
		}
		if (counter == 7) {
			status = true;
			takeScreenShot(driver, "PaymentInfo", tcID, sheetName);
		} else {
			status = false;
		}
		return status;
	}

	// Verify order summary in order details page
	public boolean verifyOrderSummary(String paymentMode, String subTotal, String deliveryFee, String grandTotal,
			String tcID, String sheetName) {
		boolean status = false;
		int counter = 0;
		scrollTillElementVisible(driver.findElement(orderSummaryHeader));
		explicitWait(orderSummaryHeader);
		if (paymentMode.equalsIgnoreCase("KNET")) {
			paymentMode = "K-Net";
		}
		else if(paymentMode.equalsIgnoreCase("VISA")) {
			paymentMode = "Credit Card";
		}
		if (driver.findElement(orderSummaryHeader).getText().trim().equalsIgnoreCase("ORDER SUMMARY")) {
			if ((driver.findElement(orderAmountLbl_OS).getText().trim().equalsIgnoreCase("Order Amount"))
					&& (driver.findElement(orderAmountVal_OS).getText().trim().equalsIgnoreCase("KWD " + subTotal))) {
				counter++;
			}
			if ((driver.findElement(subTotalLbl_OS).getText().trim().equalsIgnoreCase("Subtotal"))
					&& (driver.findElement(subTotalVal_OS).getText().trim().equalsIgnoreCase("KWD " + subTotal))) {
				counter++;
			}
			if ((driver.findElement(deliveryFeeLbl_OS).getText().trim().equalsIgnoreCase("Delivery Fee"))
					&& (driver.findElement(deliveryFeeVal_OS).getText().trim().equalsIgnoreCase("KWD " + deliveryFee))) {
				counter++;
			}
			if ((driver.findElement(grandLbl_OS).getText().trim().equalsIgnoreCase("Grand Total"))
					&& (driver.findElement(grandVal_OS).getText().trim().equalsIgnoreCase("KWD " + grandTotal))) {
				counter++;
			}
			if ((driver.findElement(paymentMethodLbl_OS).getText().trim().equalsIgnoreCase("Payment Method"))
					&& (driver.findElement(paymentMethodVal_OS).getText().trim().equalsIgnoreCase(paymentMode))) {
				counter++;
			}
			if (paymentMode.equalsIgnoreCase("K-Net") || paymentMode.equalsIgnoreCase("Credit Card")) {
				if ((driver.findElement(paidByKnetLbl_OS).getText().trim().contains("Paid By"))
						&& (driver.findElement(paidByKnetVal_OS).getText().trim().equalsIgnoreCase("KWD " + grandTotal))) {
					counter++;
				}
			}
		}
		if (counter == 6) {
			status = true;
			takeScreenShot(driver, "OrderSummary", tcID, sheetName);
		} else {
			status = false;
		}
		return status;
	}
}
