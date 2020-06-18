package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

	public WebDriver driver;
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[title='Qty']")
	private WebElement quantity;
	
	public WebElement quantity() {
		return quantity;
	}
	
	@FindBy(css="button[title='Update']")
	private WebElement updateQty;
	
	public WebElement updateQty() {
		return updateQty;
	}
	
	@FindBy(css="li[class='error-msg'] ul li span")
	private WebElement errorMsg;
	
	public WebElement errorMsg() {
		return errorMsg;
	}
	
	@FindBy(xpath="//span[text()='Empty Cart']")
	private WebElement emptyCart;
	
	public WebElement emptyCart() {
		return emptyCart;
	}
	
	@FindBy(css="div[class='page-title'] h1")
	private WebElement cartEmptyMsg;
	
	public WebElement cartEmptyMsg() {
		return cartEmptyMsg;
	}
	
	@FindBy(xpath="(//button[contains(@title,'Proceed')])[1]")
	private WebElement proceedCheckout;
	
	public CheckoutPage proceedCheckout() {
		proceedCheckout.click();
		return new CheckoutPage(driver);
	}
}
