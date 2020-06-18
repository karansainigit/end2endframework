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

import pageobjects.ComparePopUpPage;
import pageobjects.LandingPage;
import pageobjects.MobilePage;
import resources.Base;

public class CompareMobile extends Base {

	public WebDriver driver;
	public static Logger log = (Logger) LogManager.getLogger(CompareMobile.class.getName());
	LandingPage lp;
	
	@BeforeTest
	public void initializeBrowser() throws IOException {
		driver=initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
	}
	
	@Test
	public void compareMobile() throws InterruptedException {
		lp = new LandingPage(driver);
		
		MobilePage mp = lp.mobile();
		log.info("Navigated to Mobile page");
		
		mp.addToCompareSony().click();
		log.info("Sony Xperia added to compare");
		
		mp.addToCompareIphone().click();
		log.info("Iphone added to compare");
		
		ComparePopUpPage cpp = mp.compareButton();
		log.info("Compare button clicked");
		
		Set<String> w = driver.getWindowHandles();
		Iterator<String> wn = w.iterator();
		
		wn.next();
		driver.switchTo().window(wn.next());
		log.info("Switched to pop up window");
		
		String PopUpTitle = driver.getTitle();
		if (PopUpTitle.contains("Products Comparison List")) {
			Assert.assertTrue(true);
			log.info("Pop Up window title validated");
		} else {
			Assert.assertFalse(true);
			log.error("Pop Up window title not matched");
		}
		
		Assert.assertEquals(cpp.sonyXperia().getText(), "SONY XPERIA");
		log.info("Sony Xperia present in compare pop up page");
		
		Assert.assertEquals(cpp.iPhone().getText(), "IPHONE");
		log.info("iPhone present in compare pop up page");
		
		cpp.closePopUp().click();;
		log.info("Compare Pop Up closed");
	}
	
	@AfterTest
	public void quittingBrowser() {
		driver.quit();
	}
}
