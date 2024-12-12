package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOut extends AbstractComponent {
	
	WebDriver driver;
	public CheckOut(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> items;
	
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkoutBtn;
	
	
	public boolean itemsAdded(String productName) {
		
		Boolean result = items.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		return result;
	}

	public PlaceOrder checkOut() throws InterruptedException {
		
		waitForElementToDisappear();
		scrollDown();
		checkoutBtn.click();
		PlaceOrder placeOrder = new PlaceOrder(driver);
		return placeOrder;
	}
}
