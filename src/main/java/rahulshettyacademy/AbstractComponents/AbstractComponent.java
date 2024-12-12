package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CheckOut;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orders;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
	}

	public void waitForElementToAppear(By findby) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForWebElementToAppear(WebElement findby) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public void waitForElementToDisappear() throws InterruptedException {
		
		Thread.sleep(3000);
	}
	
	public CheckOut goToCart() {
		
		cart.click();
		CheckOut checkout = new CheckOut(driver);
		return checkout;
	}
	
	public OrderPage goToOrderPage() {
		
		orders.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	public void scrollDown() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(1000);
}
}
