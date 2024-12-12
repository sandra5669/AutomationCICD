package rahulshettyacademy.pageobjects;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class PlaceOrder extends AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement input;
	
	@FindBy(xpath="(//button[contains(@class,'ng-star-inserted')])[2]")
	WebElement inputCountry;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderBtn;
	
	By dropdown = By.cssSelector(".ta-results");
	
	public PlaceOrder(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void enterCountry() throws InterruptedException {
		
		Actions a = new Actions(driver);
		waitForElementToDisappear();
		a.sendKeys(input,"india").build().perform();
		
	}
	
	
	
	public void clickCountry() throws InterruptedException {
		
		scrollDown();
		waitForElementToAppear(dropdown);
		inputCountry.click();
	}
	
	public VerificationPage submitOrder() {
		
		placeOrderBtn.click();
		return new VerificationPage(driver);
	}
}
