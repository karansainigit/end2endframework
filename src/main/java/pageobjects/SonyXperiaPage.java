package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SonyXperiaPage {
	
	public WebDriver driver;
	
	public SonyXperiaPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="span[class='price']")
	private WebElement costofSonyXperia;
	
	public WebElement costofSonyXperia() {
		return costofSonyXperia;
	}

}
