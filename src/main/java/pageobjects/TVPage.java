package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TVPage {

	public WebDriver driver;
	
	public TVPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//a[@class='link-wishlist'])[2]")
	private WebElement addToWishList;
	
	public MyWishListPage addToWishList() {
		addToWishList.click();
		return new MyWishListPage(driver);
	}
}
