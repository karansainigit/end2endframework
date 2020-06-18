package framework.e2eproject;

import java.io.IOException;

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
import pageobjects.MyWishListPage;
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
	
	@BeforeTest
	public void initializeBrowser() throws IOException {
		driver=initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
	}
	
	@Test
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
	
	@Test
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
	}
	
	@AfterTest
	public void quittingBrowser() {
		driver.quit();
	}
}
