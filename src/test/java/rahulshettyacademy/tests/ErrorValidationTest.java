package rahulshettyacademy.tests;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CheckOut;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import java.io.IOException;
import java.util.List;

public class ErrorValidationTest extends BaseTest{

	@Test(retryAnalyzer=rahulshettyacademy.TestComponents.RetryTest.class)
	public void loginError() {
		
		landingpage.loginApplication("sandramekkadan@gmail.com", "rtyg");
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMsg());
	}
	
	@Test(groups={"ErrorValidation"})
	public void productErrorValidation() throws InterruptedException {
		
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue=landingpage.loginApplication("sandra@gmail.com", "Sandra1234");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CheckOut checkout = productCatalogue.goToCart();
		boolean match = checkout.itemsAdded("ADIDAS DUPE");
		Assert.assertFalse(match);
	}

}
