package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public OrderPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyOrderDisplay(String productName) {
		
		Boolean result = productNames.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		return result;
	}

}
