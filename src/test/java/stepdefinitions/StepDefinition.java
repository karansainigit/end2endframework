package stepdefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.ForgotPasswordPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class StepDefinition extends Base {
	
		public WebDriver driver;
		LandingPage lp;
		LoginPage logp;
		ForgotPasswordPage fp;
		
		@Given("^Initialize browser with chrome$")
	    public void initialize_browser_with_chrome() throws Throwable {
			driver=initializeDriver();
	    }

	    @When("^User enters (.+) and (.+) and click on Login$")
	    public void user_enters_and_and_click_on_login(String username, String password) throws Throwable {
	    	logp.enterEmail().sendKeys(username);
			logp.enterPassword().sendKeys(password);
			logp.loginButton().click();
	    }

	    @Then("^Verify User is successfully logged in$")
	    public void verify_user_is_successfully_logged_in() throws Throwable {
	    	fp = logp.forgotPassword();
			
			fp.email().sendKeys("test99@gmail.com");
			fp.submit().click();
	    }

	    @And("^Navigate to \"([^\"]*)\" site$")
	    public void navigate_to_something_site(String url) throws Throwable {
	    	driver.get(url);
	    }

	    @And("^Click on Login link in Home page to land on Sign In page$")
	    public void click_on_login_link_in_home_page_to_land_on_sign_in_page() throws Throwable {
	    	lp = new LandingPage(driver);
			lp.accountLink().click();
			
			logp = lp.logInLink();
	    }

	    @And("^Close browsers$")
	    public void close_browsers() throws Throwable {
	    	driver.quit();
	    }
		
		/*@When("User enters {string} and {string} and click on Login")
		public void user_enters_and_and_click_on_Login(String email, String password) {
		    // Write code here that turns the phrase above into concrete actions
			logp.enterEmail().sendKeys(email);
			logp.enterPassword().sendKeys(password);
			logp.loginButton().click();
		}*/
    }