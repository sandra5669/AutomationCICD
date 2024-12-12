package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerificationPage {
	
	
		WebDriver driver;
		
		public VerificationPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
			
		}
	@FindBy(css=".hero-primary")
	WebElement message;

	public String verifyMessage() {
		
		String ConfirmMsg= message.getText();
		return ConfirmMsg;
	}
}
