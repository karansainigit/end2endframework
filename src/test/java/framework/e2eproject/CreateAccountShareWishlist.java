package framework.e2eproject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.CheckoutPage;
import pageobjects.CreateAccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import pageobjects.MyOrdersPage;
import pageobjects.MyWishListPage;
import pageobjects.OrderDetailsPage;
import pageobjects.PlaceOrderPage;
import pageobjects.ShoppingCartPage;
import pageobjects.TVPage;
import pageobjects.WishListSharingPage;
import resources.Base;

public class CreateAccountShareWishlist extends Base {

	public WebDriver driver;
	public static Logger log = (Logger) LogManager.getLogger(CreateAccountShareWishlist.class.getName());
	String fName = "Karan";
	String lName = "Saini";
	String email = "emailaddress" + (int)(Math.random()*((10000-1)+1))+1 + "@gmail.com";
	String password = "password" + Math.random() * (100 - 1) + 5;
	String wlemail = "emailaddress" + (int)(Math.random()*((10000-1)+1))+1 + "@yahoo.com";
	String orderNumber;
	LandingPage lp;
	LoginPage logp;
	CreateAccountPage cap;
	MyAccountPage ap;
	TVPage tv;
	MyWishListPage wl;
	WishListSharingPage wlsp;
	ShoppingCartPage sc;
	CheckoutPage co;
	PlaceOrderPage po;
	MyOrdersPage op;
	OrderDetailsPage od;
	
	@BeforeTest
	public void initializeBrowser() throws IOException {
		driver=initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
	}
	
	@Test (priority = 1)
	public void creatingAccountAndSharingWishList() throws InterruptedException {
		lp = new LandingPage(driver);
		
		lp.accountLink().click();
		log.info("Account is clicked");
		
		logp = lp.myAccount();
		log.info("MyAccount is clicked and Login Page is opened");
		
		cap = logp.createAccount();
		log.info("Create Account Page is opened");
		
		cap.firstName().sendKeys(fName);
		log.info("First Name entered");
		
		cap.lastname().sendKeys(lName);
		log.info("Last Name entered");
		
		cap.email().sendKeys(email);
		log.info("Email entered");
		
		cap.password().sendKeys(password);
		log.info("Password entered");
		
		cap.confirmation().sendKeys(password);
		log.info("Confirm Password entered");
		
		ap = cap.register();
		log.info("Successfully Registered");
		
		if (ap.successMessage().getText().contains("Thank you for registering")) {
			Assert.assertTrue(true);
			log.info("Registration verified");
		} else {
			Assert.assertFalse(true);
			log.error("Registration failed");
		}
		
		tv = ap.tv();
		log.info("TV Page opened");
		
		wl = tv.addToWishList();
		log.info("My Wish List Page opened");
		
		if (wl.successMsg().getText().contains("added to your wishlist")) {
			Assert.assertTrue(true);
			log.info("Product added to Wish List");
		} else {
			Assert.assertFalse(true);
			log.error("Error in adding product to Wish List");
		}
		
		wlsp = wl.shareWishList();
		log.info("Share Wish List page opened");
		
		wlsp.emails().sendKeys(wlemail);
		log.info("Email entered");
		
		wlsp.message().sendKeys("Check this out!");
		log.info("Message entered");
		
		wl = wlsp.shareWishList();
		log.info("Shared WishList button clicked");
		
		if (wl.wishListSharedSuccessMsg().getText().contains("Wishlist has been shared")) {
			Assert.assertTrue(true);
			log.info("Wish List shared successfully");
		} else {
			Assert.assertFalse(true);
			log.error("Error in sharing Wish List");
		}
	}
	
	@Test(priority = 2)
	public void verifyPurchaseUsingRegisteredEmail() {
		sc = wl.addToCart();
		log.info("Product added to cart and Shopping Cart page opened");
		
		co = sc.proceedCheckout();
		log.info("Proceeded to Check Out Page");
		
		co.enterAddress().sendKeys("ABC");
		log.info("Address Entered");
		
		co.enterCity().sendKeys("New York");
		log.info("City Entered");
		
		co.chooseStateOrProvince().selectByVisibleText("New York");
		log.info("State selected");
		
		co.enterZip().sendKeys("542896");
		log.info("Zip Entered");
		
		co.chooseCountry().selectByVisibleText("United States");
		log.info("Country selected");
		
		co.enterTelephone().sendKeys("12345678");
		log.info("Telephone Entered");
		
		if (co.checkingPopUp().size() > 0) {
			driver.switchTo().frame(co.popUp());
			co.closePopUp().click();
			driver.switchTo().defaultContent();
		}
		
		co.billingInfoContinue().click();
		log.info("Billing Information Entered");
		
		Assert.assertEquals(co.shippingRate().getText(), "$5.00");
		log.info("Shipping Rate Verified");
		
		co.shippingMethodContinue().click();
		log.info("Shipping Method selected");
		
		co.checkPayment().click();
		log.info("Check/Money Order selected");
		
		co.paymentInfoContinue().click();
		log.info("Payment Information Selected");
		
		Assert.assertEquals(co.grandTotal().getText(), "$705.00");
		log.info("Grand Total Verified");
		
		po = co.placeOrder();
		log.info("Order Placed");
		
		Assert.assertTrue(po.orderSuccessMsg().getText().contains("THANK YOU"));
		log.info("Order Successfully Placed");
		
		Assert.assertTrue(po.orderNumber().isDisplayed());
		log.info("Order Number: " + po.orderNumber().getText());
		orderNumber = po.orderNumber().getText();
	}
	
	@Test(priority = 3)
	public void verifySavePreviouslyPlacedOrder() {
		po.accountLink().click();
		log.info("Account link clicked");
		
		ap = po.myAccountlink();
		log.info("My Account link clicked and navigated to My Account page");
		
		op = ap.myOrders();
		log.info("My Orders link clicked and navigated to My Orders page");
		
		od = op.viewOrder();
		log.info("View Order clicked and navigated to Order Details page");
		
		Assert.assertTrue(od.orderStatus().getText().contains(orderNumber));
		Assert.assertTrue(od.orderStatus().getText().contains("PENDING"));
		log.info("Order Number Verified and Status is verified as Pending");
		
		od.printOrder().click();
		log.info("Print Order clicked");
		
		Set<String> wh = driver.getWindowHandles();
		Iterator<String> ids = wh.iterator();
		String pWindows = ids.next();
		
		driver.switchTo().window(ids.next());
		Assert.assertTrue(driver.getTitle().contains("Print"));
		log.info("Print page title verified");
		
		driver.close();
		log.info("Print Page closed");
		
		driver.switchTo().window(pWindows);
	}
	
	@Test(priority = 4)
	public void verifyReOrderProduct() {
		sc = od.reOrder();
		log.info("Reorder clicked and shopping cart page opened");
		
		sc.quantity().clear();
		log.info("Clearing Quantity");
		sc.quantity().sendKeys("10");
		log.info("Updating Quantity number");
		
		sc.updateQty().click();
		log.info("Updating Quantity");
		
		Assert.assertEquals(sc.grandTotal().getText(), "$7,000.00");
		log.info("Grand Total verified");
		
		co = sc.proceedCheckout();
		log.info("Proceeded to Check Out Page");
		
		if (co.checkingPopUp().size() > 0) {
			driver.switchTo().frame(co.popUp());
			co.closePopUp().click();
			driver.switchTo().defaultContent();
		}
		
		co.billingInfoContinue().click();
		log.info("Billing Information Entered");
		
		co.shippingMethodContinue().click();
		log.info("Shipping Method selected");
		
		co.checkPayment().click();
		log.info("Check/Money Order selected");
		
		co.paymentInfoContinue().click();
		log.info("Payment Information Selected");
		
		Assert.assertEquals(co.grandTotal().getText(), "$7,050.00");
		log.info("Grand Total Verified");
		
		po = co.placeOrder();
		log.info("Order Placed");
		
		Assert.assertTrue(po.orderSuccessMsg().getText().contains("THANK YOU"));
		log.info("Order Successfully Placed");
		
		Assert.assertTrue(po.orderNumber().isDisplayed());
		log.info("Order Number: " + po.orderNumber().getText());
		orderNumber = po.orderNumber().getText();
	}
	
	@AfterTest
	public void quittingBrowser() {
		driver.quit();
	}
}
