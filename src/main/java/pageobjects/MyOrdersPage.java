package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyOrdersPage {
	
	WebDriver driver;
	
	public MyOrdersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='View Order']")
	private WebElement viewOrder;
	
	public OrderDetailsPage viewOrder() {
		viewOrder.click();
		return new OrderDetailsPage(driver);
	}
	
}
