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
import resources.Base;

public class ValidateWelcomeMsg extends Base {
	public WebDriver driver;
	public static Logger log = (Logger) LogManager.getLogger(ValidateWelcomeMsg.class.getName());
	LandingPage lp;
	
	@BeforeTest
	public void initializeBrowser() throws IOException {
		driver=initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
	}
	
	@Test
	public void validateWelcomeMsg() throws IOException {
		lp = new LandingPage(driver);
		
		Assert.assertEquals(lp.welcomeMsg().getText(), "DEFAULT WELCOME MSG!");
		log.info("Successfully validated Welcome Message");
	}
	
	@Test (dependsOnMethods= {"validateWelcomeMsg"})
	public void validatePayPal() throws IOException {
		Assert.assertTrue(lp.payPal().isDisplayed());
		log.info("PayPal is displayed");
	}
	
	@Test (dependsOnMethods= {"validateWelcomeMsg"})
	public void validateIconOnPage() throws IOException {
		Assert.assertTrue(lp.icon().isDisplayed());
		log.info("Icon is displayed");
	}
	
	@AfterTest
	public void quittingBrowser() {
		driver.quit();
	}

}
