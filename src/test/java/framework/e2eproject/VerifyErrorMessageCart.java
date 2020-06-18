package framework.e2eproject;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import pageobjects.MobilePage;
import pageobjects.ShoppingCartPage;
import resources.Base;

public class VerifyErrorMessageCart extends Base {

	public WebDriver driver;
	public static Logger log = (Logger) LogManager.getLogger(VerifyErrorMessageCart.class.getName());
	LandingPage lp;
	
	@BeforeTest
	public void initializeBrowser() throws IOException {
		driver=initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
	}
	
	@Test
	public void verifyErrorMsgCart() {
		lp = new LandingPage(driver);
		
		MobilePage mp = lp.mobile();
		log.info("Navigated to Mobile page");
		
		ShoppingCartPage sc = mp.addToCartSony();
		log.info("Navigated to Shopping Cart page");
		
		sc.quantity().clear();
		log.info("Quantity cleared");
		
		sc.quantity().sendKeys("1000");
		log.info("Quantity updated to 1000");
		
		sc.updateQty().click();
		log.info("Update button clicked");
		
		Assert.assertEquals(sc.errorMsg().getText(), "Some of the products cannot be ordered in requested quantity.");
		log.info("Error message successfully validated");
		
		sc.emptyCart().click();
		log.info("Cart is empty now");
		
		Assert.assertEquals(sc.cartEmptyMsg().getText(),"SHOPPING CART IS EMPTY");
		log.info("Cart Empty message successfully validated");
	}
	
	@AfterTest
	public void quittingBrowser() {
		driver.quit();
	}
}
