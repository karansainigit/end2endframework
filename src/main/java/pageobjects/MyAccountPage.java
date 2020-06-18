package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
	
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//li[@class='success-msg'] //span")
	private WebElement successMessage;
	
	public WebElement successMessage() {
		return successMessage;
	}
	
	@FindBy(xpath="//a[text()='TV']")
	private WebElement tv;
	
	public TVPage tv() {
		tv.click();
		return new TVPage(driver);
	}
	
	@FindBy(xpath="//a[text()='My Orders']")
	private WebElement myOrders;
	
	public MyOrdersPage myOrders() {
		myOrders.click();
		return new MyOrdersPage(driver);
	}
	
}
