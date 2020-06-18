package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrderPage {

	public WebDriver driver;
	
	public PlaceOrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h2[@class='sub-title']")
	private WebElement orderSuccessMsg;
	
	public WebElement orderSuccessMsg() {
		return orderSuccessMsg;
	}
	
	@FindBy(xpath="(//a[contains(@href,'order_id')])[1]")
	private WebElement orderNumber;
	
	public WebElement orderNumber() {
		return orderNumber;
	}
	
	@FindBy(xpath="(//span[text()='Account'])[1]")
	private WebElement accountLink;
	
	public WebElement accountLink() {
		return accountLink;
	}
	
	@FindBy(css="div[id='header-account'] a[title='My Account']")
	private WebElement myAccountlink;
	
	public MyAccountPage myAccountlink() {
		myAccountlink.click();
		return new MyAccountPage(driver);
	}
}
