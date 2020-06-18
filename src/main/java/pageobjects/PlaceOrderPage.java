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
}
