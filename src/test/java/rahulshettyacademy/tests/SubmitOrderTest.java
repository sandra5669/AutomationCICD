package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CheckOut;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.PlaceOrder;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.VerificationPage;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
	
		
		ProductCatalogue productCatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CheckOut checkout = productCatalogue.goToCart();
		
		boolean match = checkout.itemsAdded(input.get("product"));
		Assert.assertTrue(match);
		
		PlaceOrder placeOrder = checkout.checkOut();
		
		placeOrder.enterCountry();
		placeOrder.scrollDown();
		placeOrder.clickCountry();
		VerificationPage page = placeOrder.submitOrder();
		
		String text = page.verifyMessage();
		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void goToOrderHistory() {
		
		ProductCatalogue productCatalogue=landingpage.loginApplication("sandramekkadan@gmail.com", "Sandra");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		//HashMap<String,String> map = new HashMap<String,String>();
		//map.put("email", "sandramekkadan@gmail.com");
		//map.put("password", "Sandra");
		//map.put("product", "ADIDAS ORIGINAL");
		
		//HashMap<String,String> map1 = new HashMap<String,String>();
		//map1.put("email", "sandra@gmail.com");
		//map1.put("password", "Sandra1234");
		//map1.put("product", "ZARA COAT 3");
		List <HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
