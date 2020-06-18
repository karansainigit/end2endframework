package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyWishListPage {

	public WebDriver driver;
	
	public MyWishListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//li[@class='success-msg'] //span")
	private WebElement successMsg;
	
	public WebElement successMsg() {
		return successMsg;
	}
	
	@FindBy(css="button[title='Share Wishlist']")
	private WebElement shareWishList;
	
	public WishListSharingPage shareWishList() {
		shareWishList.click();
		return new WishListSharingPage(driver);
	}
	
	@FindBy(xpath="//li[@class='success-msg'] //span")
	private WebElement wishListSharedSuccessMsg;
	
	public WebElement wishListSharedSuccessMsg() {
		return wishListSharedSuccessMsg;
	}
	
	@FindBy(css="button[title='Add to Cart']")
	private WebElement addToCart;
	
	public ShoppingCartPage addToCart() {
		addToCart.click();
		return new ShoppingCartPage(driver);
	}
	
}
