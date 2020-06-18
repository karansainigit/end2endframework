package framework.e2eproject;

import java.io.IOException;

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
import pageobjects.SonyXperiaPage;
import resources.Base;

public class ValidateCostofProduct extends Base {
	
	public WebDriver driver;
	public static Logger log = (Logger) LogManager.getLogger(ValidateCostofProduct.class.getName());

	@BeforeTest
	public void initializeBrowser() throws IOException {
		driver=initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
	}
	
	@Test
	public void validateCostofProduct() throws IOException {
		LandingPage lp = new LandingPage(driver);
		
		MobilePage mp = lp.mobile();
		log.info("Navigated to mobile page");
		
		Select s = new Select(mp.sortBy());
		s.selectByIndex(1);
		log.info("Sorting by name");
		
		String costOnListPage = mp.costOfProduct1().getText();
		log.info("Cost of Sony Xperia on List Page: " + mp.costOfProduct1().getText());
		
		SonyXperiaPage sXp = mp.sonyXperia();
		log.info("Navigated to Sony Xperia detail page");
		
		String costOnDetailPage = sXp.costofSonyXperia().getText();
		log.info("Cost of Sony Xperia on Detail Page: " + sXp.costofSonyXperia().getText());
		
		Assert.assertEquals(costOnListPage, costOnDetailPage);
		log.info("Cost is same on both page");
	}
	
	@AfterTest
	public void quittingBrowser() {
		driver.quit();
	}

}
