package framework.e2eproject;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.ForgotPasswordPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class HomePage extends Base {
	public WebDriver driver;
	public static Logger log = (Logger) LogManager.getLogger(HomePage.class.getName());
	
	@BeforeTest
	public void initializeBrowser() throws IOException {
		driver=initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
	}
	
	@Test (dataProvider = "getData")
	public void basePageNavigation(String email, String password) throws IOException {
		driver.get(prop.getProperty("url"));
		LandingPage lp = new LandingPage(driver);
		
		lp.accountLink().click();
		log.info("Account link clicked");
		
		LoginPage logp = lp.logInLink();
		log.info("Login link clicked");
		
		//LoginPage logp = new LoginPage(driver);
		
		logp.enterEmail().sendKeys(email);
		log.info("Email address entered");
		
		logp.enterPassword().sendKeys(password);
		log.info("Password entered");
		
		logp.loginButton().click();
		log.info("Login button clicked");
		
		ForgotPasswordPage fp = logp.forgotPassword();
		
		fp.email().sendKeys(email);
		fp.submit().click();
	}
	
	@AfterTest
	public void quittingBrowser() {
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][2];
		data[0][0] = "email@gmail.com";
		data[0][1] = "password123";
		
		data[1][0] = "email@yahoo.com";
		data[1][1] = "password456";
		
		return data;
	}

}
