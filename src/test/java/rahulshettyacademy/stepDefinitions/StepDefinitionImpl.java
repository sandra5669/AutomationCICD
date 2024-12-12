package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CheckOut;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.PlaceOrder;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.VerificationPage;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public VerificationPage page;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		landingpage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_with_username_and_password(String username, String password) {
		
		productCatalogue=landingpage.loginApplication(username,password);
		
	}
	
	@When("^I add product (.+) to the Cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException {
		
		CheckOut checkout = productCatalogue.goToCart();
		boolean match = checkout.itemsAdded(productName);
		Assert.assertTrue(match);
		PlaceOrder placeOrder = checkout.checkOut();
		placeOrder.enterCountry();
		placeOrder.scrollDown();
		placeOrder.clickCountry();
		page = placeOrder.submitOrder();
	}
	
	@Then("{string} message is displayed on Confirmation Page")
	public void message_displayed_confirmation_page(String string) {
		
		String text = page.verifyMessage();
		Assert.assertTrue(text.equalsIgnoreCase(string));
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string) {
		
		Assert.assertEquals(string,landingpage.getErrorMsg());
	}	
}
