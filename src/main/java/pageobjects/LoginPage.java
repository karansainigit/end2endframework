package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input#email")
	private WebElement enterEmail;
	
	public WebElement enterEmail() {
		return enterEmail;
	}
	
	@FindBy(css="input#pass")
	private WebElement enterPassword;
	
	public WebElement enterPassword() {
		return enterPassword;
	}
	
	@FindBy(xpath="//button[@title='Login']")
	private WebElement loginButton;
	
	public WebElement loginButton() {
		return loginButton;
	}
	
	@FindBy(linkText="Forgot Your Password?")
	private WebElement forgotPassword;
	
	public ForgotPasswordPage forgotPassword() {
		forgotPassword.click();
		return new ForgotPasswordPage(driver);
	}
	
	@FindBy(css="a[title='Create an Account']")
	private WebElement createAccount;
	
	public CreateAccountPage createAccount() {
		createAccount.click();
		return new CreateAccountPage(driver);
	}
	

}
