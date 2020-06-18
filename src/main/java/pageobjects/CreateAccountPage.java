package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {
	
	WebDriver driver;
	
	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input#firstname")
	private WebElement firstName;
	
	public WebElement firstName() {
		return firstName;
	}
	
	@FindBy(css="input#lastname")
	private WebElement lastname;
	
	public WebElement lastname() {
		return lastname;
	}
	
	@FindBy(css="input#email_address")
	private WebElement email;
	
	public WebElement email() {
		return email;
	}
	
	@FindBy(css="input#password")
	private WebElement password;
	
	public WebElement password() {
		return password;
	}
	
	@FindBy(css="input#confirmation")
	private WebElement confirmation;
	
	public WebElement confirmation() {
		return confirmation;
	}
	
	@FindBy(css="button[title='Register']")
	private WebElement register;
	
	public MyAccountPage register() {
		register.click();
		return new MyAccountPage(driver);
	}
}
