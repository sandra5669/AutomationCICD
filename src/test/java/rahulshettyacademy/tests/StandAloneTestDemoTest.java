package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandAloneTestDemoTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		LandingPage landingpage = new LandingPage(driver);
		
		//login
		driver.findElement(By.id("userEmail")).sendKeys("sandramekkadan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sandra");
		driver.findElement(By.id("login")).click();
		
		//Add the item to Cart
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("card-body")));
		List<WebElement> products =driver.findElements(By.className("card-body"));
		WebElement name = products.stream().filter(product->product.findElement
		(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		name.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//wait until Cart is clickable
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(4000);
		//Click on Cart
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//Check if all the items added are present in the cart
		List<WebElement> items = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean result = items.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(result);
		
		//Checkout the items
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		
		//Enter the country and select it from dropdown
		Actions a = new Actions(driver);
		Thread.sleep(3000);
		WebElement input = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		a.sendKeys(input,"india").build().perform();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ng-star-inserted')])[2]")).click();
		
		//Place the order
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//Verify confirm message
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

}
