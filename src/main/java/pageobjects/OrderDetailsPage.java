package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderDetailsPage {
	
	WebDriver driver;
	
	public OrderDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class='page-title title-buttons'] h1")
	private WebElement orderStatus;
	
	public WebElement orderStatus() {
		return orderStatus;
	}
	
	@FindBy(xpath="//a[text()='Print Order']")
	private WebElement printOrder;
	
	public WebElement printOrder() {
		return printOrder;
	}
}
