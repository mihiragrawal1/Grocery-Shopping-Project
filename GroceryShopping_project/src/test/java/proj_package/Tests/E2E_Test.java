package proj_package.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import proj_package.BaseComponent.BaseTest;
import proj_package.Pages.CartPage;
import proj_package.Pages.CheckoutPage;
import proj_package.Pages.HomePage;
import proj_package.Pages.OrderConfirmationPage;

public class E2E_Test extends  BaseTest{
	
	String Country = "India";
	String[] productToBuy = { "Walnuts", "Banana", "beetroot", "cucumber" };
	
	
	public String[] convertArrayToLowercase(String[] arr) {
		String[] lowercaseArray = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			lowercaseArray[i] = arr[i].toLowerCase();
		}
		return lowercaseArray;
	}

	@Test(description = "validate e2e process to buy a product")
	public void allModuleIntegrationTest_E2E() throws InterruptedException {
		
		HomePage homeObj = new HomePage(driver);
		String[] lowerCaseArray = convertArrayToLowercase(productToBuy);
		homeObj.addProductToCart(lowerCaseArray);
		CartPage cartObj = new CartPage(driver);
		cartObj.goToCart();
		cartObj.clickProceedToCheckout();
		Thread.sleep(2000);
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains("/cart"));
		cartObj.clickPlaceOrderBtn();
		CheckoutPage checkoutObj = new CheckoutPage(driver);
		checkoutObj.selectCountry(Country);
		checkoutObj.checkTermAndConditions();
		checkoutObj.clickProceed();
		OrderConfirmationPage orderPageObj = new OrderConfirmationPage(driver);

		String msg = orderPageObj.getMsg();
		System.out.print(msg);
		Assert.assertTrue(msg.contains("Thank you"));
		
	}

}
