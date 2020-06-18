package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	public WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="ACCOUNT")
	private WebElement accountLink;
	
	public WebElement accountLink() {
		return accountLink;
	}
	
	@FindBy(xpath="//a[@title='Log In']")
	private WebElement logInLink;
	
	public LoginPage logInLink() {
		logInLink.click();
		return new LoginPage(driver);
	}
	
	@FindBy(xpath="//p[@class='welcome-msg']")
	private WebElement welcomeMsg;
	
	public WebElement welcomeMsg() {
		return welcomeMsg;
	}
	
	@FindBy(css="#header")
	private WebElement header;
	
	public WebElement header() {
		return header;
	}
	
	@FindBy(linkText="MOBILE")
	private WebElement mobile;

	public MobilePage mobile() {
		mobile.click();
		return new MobilePage(driver);
	}
	
	@FindBy(css="img[title*='Additional']")
	private WebElement payPal;
	
	public WebElement payPal() {
		return payPal;
	}
	
	@FindBy(xpath="//a[@class='logo']/img[1]")
	private WebElement icon;
	
	public WebElement icon() {
		return icon;
	}
	
	@FindBy(xpath="(//a[@title='My Account'])[1]")
	private WebElement myAccount;
	
	public LoginPage myAccount() {
		myAccount.click();
		return new LoginPage(driver);
	}
}
