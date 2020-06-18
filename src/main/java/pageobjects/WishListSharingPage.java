package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishListSharingPage {

	public WebDriver driver;
	
	public WishListSharingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="textarea[name='emails']")
	private WebElement emails;
	
	public WebElement emails() {
		return emails;
	}
	
	@FindBy(css="textarea#message")
	private WebElement message;
	
	public WebElement message() {
		return message;
	}
	
	@FindBy(css="button[title='Share Wishlist']")
	private WebElement shareWishList;
	
	public MyWishListPage shareWishList() {
		shareWishList.click();
		return new MyWishListPage(driver);
	}
}
