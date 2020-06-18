package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {

	public WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[id='billing:street1']")
	private WebElement enterAddress;
	
	public WebElement enterAddress() {
		return enterAddress;
	}
	
	@FindBy(css="input[id='billing:city']")
	private WebElement enterCity;
	
	public WebElement enterCity() {
		return enterCity;
	}
	
	@FindBy(css="select[id='billing:region_id']")
	private WebElement chooseStateOrProvince;
	
	public Select chooseStateOrProvince() {
		Select selectStateOrProvince = new Select(chooseStateOrProvince);
		return selectStateOrProvince;
	}
	
	@FindBy(css="input[id='billing:postcode']")
	private WebElement enterZip;
	
	public WebElement enterZip() {
		return enterZip;
	}
	
	@FindBy(css="select[id='billing:country_id']")
	private WebElement chooseCountry;
	
	public Select chooseCountry() {
		Select selectCountry = new Select(chooseCountry);
		return selectCountry;
	}
	
	@FindBy(css="input[id='billing:telephone']")
	private WebElement enterTelephone;
	
	public WebElement enterTelephone() {
		return enterTelephone;
	}
	
	@FindBy(xpath="//div[@id='billing-buttons-container'] //button[@title='Continue']")
	private WebElement billingInfoContinue;
	
	public WebElement billingInfoContinue() {
		return billingInfoContinue;
	}
	
	@FindBy(css="label[for='s_method_flatrate_flatrate'] span[class='price']")
	private WebElement shippingRate;
	
	public WebElement shippingRate() {
		return shippingRate;
	}
	
	@FindBy(xpath="//div[@id='shipping-method-buttons-container'] //button")
	private WebElement shippingMethodContinue;
	
	public WebElement shippingMethodContinue() {
		return shippingMethodContinue;
	}
	
	@FindBy(css="input#p_method_checkmo")
	private WebElement checkPayment;
	
	public WebElement checkPayment() {
		return checkPayment;
	}
	
	@FindBy(xpath="//div[@id='payment-buttons-container'] //button")
	private WebElement paymentInfoContinue;
	
	public WebElement paymentInfoContinue() {
		return paymentInfoContinue;
	}
	
	@FindBy(xpath="//table[@id='checkout-review-table'] //tr[3] //td[2]/strong/span")
	private WebElement grandTotal;
	
	public WebElement grandTotal() {
		return grandTotal;
	}
	
	@FindBy(css="button[title='Place Order']")
	private WebElement placeOrder;
	
	public PlaceOrderPage placeOrder() {
		placeOrder.click();
		return new PlaceOrderPage(driver);
	}
	
	@FindBy(xpath="//iframe [@id='flow_close_btn_iframe']")
	private List<WebElement> checkingPopUp;
	
	public List<WebElement> checkingPopUp() {
		return checkingPopUp;
	}
	
	@FindBy(xpath="//iframe [@id='flow_close_btn_iframe']")
	private WebElement popUp;
	
	public WebElement popUp() {
		return popUp;
	}
	
	@FindBy(xpath="//div[@id='closeBtn']")
	private WebElement closePopUp;
	
	public WebElement closePopUp() {
		return closePopUp;
	}
}
