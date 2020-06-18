package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ForgotPasswordPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class StepDefinition extends Base {
	
		public WebDriver driver;
		LandingPage lp;
		LoginPage logp;
		ForgotPasswordPage fp;

		@Given("Initialize browser with chrome")
		public void initialize_browser_with_chrome() throws IOException {
		    // Write code here that turns the phrase above into concrete actions
			driver=initializeDriver();
		}

		@Given("Navigate to {string} site")
		public void navigate_to_site(String url) {
		    // Write code here that turns the phrase above into concrete actions
			driver.get(url);
		}
		
		@Given("Click on Login link in Home page to land on Sign In page")
		public void click_on_Login_link_in_Home_page_to_land_on_Sign_In_page() {
		    // Write code here that turns the phrase above into concrete actions
			lp = new LandingPage(driver);
			lp.accountLink().click();
			
			logp = lp.logInLink();
		}
		
		
		@When("^User enters (.+) and (.+) and click on Login$")
	    public void user_enters_and_and_click_on_login(String username, String password) throws Throwable {
			logp.enterEmail().sendKeys(username);
			logp.enterPassword().sendKeys(password);
			logp.loginButton().click();
	    }
		
		
		/*@When("User enters {string} and {string} and click on Login")
		public void user_enters_and_and_click_on_Login(String email, String password) {
		    // Write code here that turns the phrase above into concrete actions
			logp.enterEmail().sendKeys(email);
			logp.enterPassword().sendKeys(password);
			logp.loginButton().click();
		}*/
		
		@Then("Verify User is successfully logged in")
		public void verify_User_is_successfully_logged_in() {
		    // Write code here that turns the phrase above into concrete actions
			fp = logp.forgotPassword();
			
			fp.email().sendKeys("test99@gmail.com");
			fp.submit().click();
		}
		
		@Then("Close browsers")
		public void close_browsers() {
		    // Write code here that turns the phrase above into concrete actions
			driver.quit();
		}
    }