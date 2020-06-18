package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {

	WebDriver driver;
	
	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='email_address']")
	private WebElement email;
	
	public WebElement email() {
		return email;
	}
	
	@FindBy(css="div[class='buttons-set'] button[type='submit']")
	private WebElement submit;
	
	public WebElement submit() {
		return submit;
	}
}
