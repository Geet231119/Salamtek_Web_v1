package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class NotificationListPage extends BaseTest{
	By notificationLnk = By.xpath("//*[@id=\"navbarNav\"]/div[1]/ul/li[3]/a[contains(text(),'NOTIFICATION')]");
	By firstNotificationInList = By.xpath("//app-notifications/section/div/div/div[3]/div[1]/div/div/div[1]/a/div/div/span[1]");
	By firstNotificationInListForInsurance = By.xpath("//app-notifications/section/div/div/div[3]/div[1]/div/div/div[1]/a/div/div/div/span");
	By firstNotificationInListForUserPrescription = By.xpath("//app-notifications/section/div/div/div[3]/div[1]/div/div/div[1]/a/div/div/span[1]");
	
	
	public NotificationListPage(WebDriver driver) {
		super();
	}
	
	//Validate notification for Reschedule doctor appointment from doctor side
	public String verifyNotificationPresenceForReschedule(String bookingID,String doctorName, String tcID, String sheet) throws Exception{
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		if(doctorName.substring(0, 2).equalsIgnoreCase("Dr")) {
			doctorName = doctorName.substring(3).trim().toUpperCase();
		}
		takeScreenShot(driver, "RescheduleInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		System.out.println("Your appointment "+bookingID+" with "+doctorName+" has been rescheduled successfully");
		Assert.assertEquals(firstNotificationTxt, "YOUR APPOINTMENT "+bookingID+" WITH "+doctorName.toUpperCase()+" HAS BEEN RESCHEDULED SUCCESSFULLY", "Notification not present");
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for Reschedule doctor appointment from user side
	public String verifyNotificationPresenceForPatientReschedule(String bookingID,String doctorName, String tcID, String sheet) throws Exception{
		waitSometime();
		driver.get("https://dev.salamtek.com/en/notification-list");
		waitForPageLoad();
		explicitWait_60(notificationLnk);
		moveToElement(driver.findElement(notificationLnk));
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		if(doctorName.substring(0, 2).equalsIgnoreCase("Dr")) {
			doctorName = doctorName.substring(3).trim().toUpperCase();
		}
		takeScreenShot(driver, "RescheduleInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		System.out.println("YOU HAVE SUCCESSFULLY RESCHEDULED A DOCTOR APPOINTMENT "+bookingID+" WITH "+doctorName);
		Assert.assertEquals(firstNotificationTxt, "YOU HAVE SUCCESSFULLY RESCHEDULED A DOCTOR APPOINTMENT "+bookingID+" WITH "+doctorName);
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for Reschedule medical appointment from Lab side
	public String verifyNotificationPresenceForRescheduleMedicalService(String bookingID,String medicalServiceName, String tcID, String sheet) throws Exception{
		explicitWait_60(notificationLnk);
		driver.get("https://dev.salamtek.com/en/notification-list");
		waitForPageLoad();
		explicitWait_60(notificationLnk);
		moveToElement(driver.findElement(notificationLnk));
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		takeScreenShot(driver, "RescheduleInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		System.out.println("YOU HAVE RESCHEDULED YOUR APPOINTMENT "+bookingID+" AT "+medicalServiceName);
		Assert.assertEquals(firstNotificationTxt, "YOU HAVE RESCHEDULED YOUR APPOINTMENT "+bookingID+" AT "+medicalServiceName.toUpperCase());
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for doctor appointment completion
	public String verifyNotificationPresenceForComplete(String bookingID,String doctorName, String tcID, String sheet) throws Exception{
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		if(doctorName.substring(0, 2).equalsIgnoreCase("Dr")) {
			doctorName = doctorName.substring(3).trim().toUpperCase();
		}
		takeScreenShot(driver, "CompletedInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		System.out.println("YOU HAVE COMPLETED A DOCTOR APPOINTMENT "+bookingID+" WITH "+doctorName.toUpperCase()+". PLEASE SHARE YOUR VALUABLE EXPERIENCE WITH US.");
		Assert.assertEquals(firstNotificationTxt, "YOU HAVE COMPLETED A DOCTOR APPOINTMENT "+bookingID+" WITH "+doctorName.toUpperCase()+". PLEASE SHARE YOUR VALUABLE EXPERIENCE WITH US.","Notification not present");
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for diagnostic notes
	public String verifyNotificationPresenceForDiagnosticNotes(String doctorName, String tcID, String sheet) throws Exception{
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		if(doctorName.substring(0, 2).equalsIgnoreCase("Dr")) {
			doctorName = doctorName.substring(3).trim().toUpperCase();
		}
		takeScreenShot(driver, "NotesInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		System.out.println(doctorName.toUpperCase()+" HAS ADDED A NEW NOTE FOR YOUR REQUEST");
		Assert.assertEquals(firstNotificationTxt, doctorName.toUpperCase()+" HAS ADDED A NEW NOTE FOR YOUR REQUEST","Notification not present");
		driver.findElement(firstNotificationInList).click();
		return firstNotificationTxt.toLowerCase();
	}
	
	//Validate notification for appointment cancellation by doctor
	public String verifyNotificationPresenceForCancel(String bookingID,String doctorName, String tcID, String sheet) throws Exception{
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		if(doctorName.substring(0, 2).equalsIgnoreCase("Dr")) {
			doctorName = doctorName.substring(3).trim().toUpperCase();
		}
		takeScreenShot(driver, "CancelledInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		//System.out.println("DOCTOR, "+doctorName+" HAS CANCELLED YOUR APPOINTMENT "+bookingID);
		String expectedNotiFromProd = "YOUR APPOINTMENT "+bookingID+" HAS BEEN CANCELLED BY "+doctorName.toUpperCase()+" DUE TO UNAVAILABILITY. YOUR REFUND WILL BE PROCESSED SHORTLY.";
		String expectedNotiFromStg = "DOCTOR, "+doctorName.toUpperCase()+" HAS CANCELLED YOUR APPOINTMENT "+bookingID;
		if(firstNotificationTxt.equalsIgnoreCase(expectedNotiFromStg)) {
			Assert.assertEquals(firstNotificationTxt, expectedNotiFromStg,"Notification not present");
		}else {
			Assert.assertEquals(firstNotificationTxt, expectedNotiFromProd,"Notification not present");
		}
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for follow up
	public String verifyNotificationPresenceForFollowUpAppointment(String bookingID,String doctorName, String tcID, String sheet) throws Exception{
		int nextBookingID = Integer.parseInt(bookingID.substring(1)) + 1;
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().substring(0, driver.findElement(firstNotificationInList).getText().length()-9).trim().toUpperCase();
		if(doctorName.substring(0, 2).equalsIgnoreCase("Dr")) {
			doctorName = doctorName.substring(3).trim().toUpperCase();
		}
		takeScreenShot(driver, "FollowUpInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		String expectedNotification = "You have a follow up appointment #"+nextBookingID+" with "+doctorName+" on "+getTomorrowDateInYYYYMMDD();
		System.out.println(expectedNotification);
		Assert.assertEquals(firstNotificationTxt, expectedNotification.toUpperCase(), "Notification not present");
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for prescribe medicine and conduct test
	public String verifyNotificationPresenceForPrescribeMedicineConductTest(String bookingID,String doctorName, String tcID, String sheet) throws Exception{
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		if(doctorName.substring(0, 2).equalsIgnoreCase("Dr")) {
			doctorName = doctorName.substring(3).trim().toUpperCase();
		}
		takeScreenShot(driver, "PrescribedMedicineInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		System.out.println(doctorName+" HAS ADDED A NEW PRESCRIPTION IN DOCTOR APPOINTMENT "+bookingID);
		Assert.assertEquals(firstNotificationTxt, doctorName.toUpperCase()+" HAS ADDED A NEW PRESCRIPTION IN DOCTOR APPOINTMENT "+bookingID,"Notification not present");
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for EHR permission
	public String verifyNotificationPresenceForEHRPermissions(String bookingID,String doctorName, String tcID, String sheet) throws Exception{
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		if(doctorName.substring(0, 2).equalsIgnoreCase("Dr")) {
			doctorName = doctorName.substring(3).trim().toUpperCase();
		}
		takeScreenShot(driver, "EHRRequestInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		System.out.println(doctorName+" HAS SENT YOU A REQUEST TO ACCESS YOUR REPORTS");
		Assert.assertEquals(firstNotificationTxt, doctorName.toUpperCase()+" HAS SENT YOU A REQUEST TO ACCESS YOUR REPORTS", "Notification not present");
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	
	//All Notifications Related To Ask Salamtek
	public String verifyNotificationPresenceForSalamtekRequest(String tcID, String sheet) throws Exception{
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		Assert.assertEquals(firstNotificationTxt, "THANK YOU FOR REACHING OUT TO SALAMTEK TEAM! WE HAVE RECEIVED YOUR MESSAGE AND OUR TEAM WILL BE IN TOUCH WITH YOU SHORTLY.","Notification not present");
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for Added notes by Salamtek doctor
	public String verifyNotificationPresenceForAddNotesSalamtek(String tcID, String sheet) throws Exception{
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		Assert.assertEquals(firstNotificationTxt, "SALAMTEK HAS ADDED A NEW NOTE FOR YOUR REQUEST","Notification not present");
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for prescribe medicine and conduct test by Salamtek doctor
	public String verifyNotificationPresenceForPrescribeConductSalamtek(String tcID, String sheet) throws Exception{
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		Assert.assertEquals(firstNotificationTxt, "SALAMTEK HAS ADDED A NEW PRESCRIPTION","Notification not present");
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for status change in order
	public String verifyNotificationPresenceForStatusChange(String orderID, String status, String tcID, String sheet) throws Exception{
		String expectedNotification;
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		takeScreenShot(driver, "OrderStatusInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		System.out.println("your order "+orderID+" is "+status+" ");
		if(status.trim().equalsIgnoreCase("Ready for driver pickup") || status.trim().equalsIgnoreCase("Ready for customer pickup"))
			expectedNotification = "YOUR ORDER "+orderID+" IS READY FOR PICKUP";
		else
			expectedNotification = "YOUR ORDER "+orderID+" IS "+status.toUpperCase().trim();
		Assert.assertEquals(firstNotificationTxt, expectedNotification, "Notification not present");
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for insurance approval/rejection for order
	public String verifyNotificationPresenceForInsurance_Order(String approveOrReject, String orderID, String tcID, String sheet) throws Exception{
		String expectedNotification="";
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInListForInsurance).getText().trim().toUpperCase();
		takeScreenShot(driver, "InsuranceInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		if(approveOrReject.equalsIgnoreCase("Approve")) {
			System.out.println("Salamtek: Order "+orderID+" has been approved by Insurance. "+getTodayDateInSlash());
			expectedNotification = "Salamtek: Order "+orderID+" has been approved by Insurance. "+getTodayDateInSlash();
		}
		else {
			System.out.println("Salamtek: Order "+orderID+" has been declined. "+getTodayDateInSlash());
			expectedNotification = "Salamtek: Order "+orderID+" has been declined. "+getTodayDateInSlash();
		}
		Assert.assertEquals(firstNotificationTxt, expectedNotification.trim().toUpperCase(), "Notification not present");
		driver.findElement(firstNotificationInListForInsurance).click();
		return expectedNotification.trim();
	}
	
	//Validate notification for adding item to an order
	public String verifyNotificationPresenceForAddItems(String newOrderID, String pharmacyName, String status, String tcID, String sheet) throws Exception{
		String expectedNotification;
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String orderID = newOrderID.substring(8, newOrderID.length()-1).trim();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		takeScreenShot(driver, "NewOrderInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		System.out.println("You have placed an order#"+orderID+" at "+pharmacyName+" ");
		expectedNotification = "You have placed an order#"+orderID+" at "+pharmacyName+" ";
		Assert.assertEquals(firstNotificationTxt, expectedNotification.trim().toUpperCase(), "Notification not present");
		return driver.findElement(firstNotificationInList).getText().trim();
	}
	
	//Validate notification for insurance approval/rejection for Lab appointment
	public String verifyNotificationPresenceForInsurance_Lab(String approveOrReject, String bookingID, String tcID, String sheet) throws Exception{
		String expectedNotification="";
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInListForInsurance).getText().trim().toUpperCase();
		takeScreenShot(driver, "InsuranceInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		if(approveOrReject.equalsIgnoreCase("Approve")) {
			System.out.println("Salamtek: Lab Appointment "+bookingID+" has been approved by Insurance. "+getTodayDateInSlash());
			expectedNotification = "Salamtek: Lab Appointment "+bookingID+" has been approved by Insurance. "+getTodayDateInSlash();
		}
		else {
			System.out.println("Salamtek: Lab Appointment "+bookingID+" has been declined. "+getTodayDateInSlash());
			expectedNotification = "Salamtek: Lab Appointment "+bookingID+" has been declined. "+getTodayDateInSlash();
		}
		Assert.assertEquals(firstNotificationTxt, expectedNotification.trim().toUpperCase(), "Notification not present");
		clickUsingJS(firstNotificationInListForInsurance);
		return expectedNotification.trim();
	}
	
	//Validate notification for User Prescription
	public String verifyNotificationPresenceForUserPrescription(String tcID, String sheet) throws Exception{
		explicitWait(notificationLnk);
		driver.findElement(notificationLnk).click();
		waitSometime();
		String firstNotificationTxt = driver.findElement(firstNotificationInList).getText().trim().toUpperCase();
		takeScreenShot(driver, "UserPrescriptionAcceptedInNotificationList", tcID, sheet);
		System.out.println("++++++++++++++++     "+firstNotificationTxt);
		Assert.assertEquals(firstNotificationTxt, "SALAMTEK:YOUR PRESCRIPTION HAS BEEN ACCEPTED AND PROCCESSED.","Notification not present");
		String expectedNotification = "SALAMTEK:YOUR PRESCRIPTION HAS BEEN ACCEPTED AND PROCCESSED.";
		clickUsingJS(firstNotificationInListForUserPrescription);
		return expectedNotification.trim();
	}
}
