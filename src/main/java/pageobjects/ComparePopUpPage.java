package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComparePopUpPage {

	WebDriver driver;
	
	public ComparePopUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Sony Xperia']")
	private WebElement sonyXperia;
	
	public WebElement sonyXperia() {
		return sonyXperia;
	}
	
	@FindBy(xpath="//a[text()='IPhone']")
	private WebElement iPhone;
	
	public WebElement iPhone() {
		return iPhone;
	}
	
	@FindBy(css="button[title='Close Window']")
	private WebElement closePopUp;
	
	public WebElement closePopUp() {
		return closePopUp;
	}
}
