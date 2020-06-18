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

public class ValidateHeader extends Base {
	public WebDriver driver;
	public static Logger log = (Logger) LogManager.getLogger(ValidateHeader.class.getName());
	
	@BeforeTest
	public void initializeBrowser() throws IOException {
		driver=initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
	}
	
	@Test
	public void validateHeader() throws IOException {
		LandingPage lp = new LandingPage(driver);
		
		Assert.assertTrue(lp.header().isDisplayed());
		log.info("Successfully validated Header section");
	}
	
	@AfterTest
	public void quittingBrowser() {
		driver.quit();
	}

}
