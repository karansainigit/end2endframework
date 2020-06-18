package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MobilePage {

	public WebDriver driver;
	
	public MobilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//select[@title='Sort By'])[1]")
	private WebElement sortBy;
	
	public WebElement sortBy() {
		return sortBy;
	}
	
	@FindBy(xpath="//h2[@class='product-name']/a")
	private List<WebElement> productNames;
	
	public List<WebElement> productNames() {
		return productNames;
	}
	
	@FindBy(css="span[id='product-price-1'] span")
	private WebElement costOfProduct1;
	
	public WebElement costOfProduct1() {
		return costOfProduct1;
	}
	
	@FindBy(xpath="//a[text()='Sony Xperia']")
	private WebElement sonyXperia;
	
	public SonyXperiaPage sonyXperia() {
		sonyXperia.click();
		return new SonyXperiaPage(driver);
	}
	
	@FindBy(xpath="//a[@title='Sony Xperia']/parent::h2/following-sibling::div[3]/button")
	private WebElement addToCartSony;
	
	public ShoppingCartPage addToCartSony() {
		addToCartSony.click();
		return new ShoppingCartPage(driver);
	}
	
	@FindBy(xpath="(//a[text()='Add to Compare'])[1]")
	private WebElement addToCompareSony;
	
	public WebElement addToCompareSony() {
		return addToCompareSony;
	}
	
	@FindBy(xpath="(//a[text()='Add to Compare'])[2]")
	private WebElement addToCompareIphone;
	
	public WebElement addToCompareIphone() {
		return addToCompareIphone;
	}
	
	@FindBy(css="button[title='Compare']")
	private WebElement compareButton;
	
	public ComparePopUpPage compareButton() {
		compareButton.click();
		return new ComparePopUpPage(driver);
	}
}
