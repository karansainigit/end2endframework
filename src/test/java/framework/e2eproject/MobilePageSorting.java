package framework.e2eproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import pageobjects.MobilePage;
import resources.Base;

public class MobilePageSorting extends Base {
	
	public WebDriver driver;
	public static Logger log = (Logger) LogManager.getLogger(MobilePageSorting.class.getName());

	@BeforeTest
	public void initializeBrowser() throws IOException {
		driver=initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
	}
	
	@Test
	public void validateMobileSorting() throws IOException {
		LandingPage lp = new LandingPage(driver);
		
		MobilePage mp = lp.mobile();
		log.info("Navigated to mobile page");
		
		Assert.assertEquals("Mobile", driver.getTitle());
		log.info("Mobile Page title matches");
		
		Select s = new Select(mp.sortBy());
		s.selectByIndex(1);
		log.info("Sorting by name selected");
		
		ArrayList<String> productNames = new ArrayList<String>();
		
		for(int i=0; i<mp.productNames().size(); i++) {
			productNames.add(mp.productNames().get(i).getText());
		}
		
		ArrayList<String> copiedList = new ArrayList<String>();
		
		for(int i=0; i<productNames.size(); i++) {
			copiedList.add(productNames.get(i));
		}
		
		Collections.sort(copiedList);
		
		Assert.assertTrue(productNames.equals(copiedList));
		log.info("Products are sorted correctly");
	}
	
	@AfterTest
	public void quittingBrowser() {
		driver.quit();
	}
}
