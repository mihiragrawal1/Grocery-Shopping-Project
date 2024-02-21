package proj_package.Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import proj_package.BaseComponent.BaseTest;
import proj_package.Pages.CartPage;
import proj_package.Pages.HomePage;

public class Test1 extends BaseTest {

	String productToSearch = "banana";
	String wrongProductToSearch = "Mihir";
	String noProductAvailableerrorMsg = "Sorry, no products matched your search!";
	String[] productToBuy = {  "walnuts","banana","beetroot" };

	@Test(enabled = false, groups = "smoke", description = "verify if user search for a product in searchbar, user gets the appropiate "
			+ "results(product displayed after search should match/contains the string searched by user)")
	public void test1() {
		HomePage homeObj = new HomePage(driver);
		homeObj.searchItem(productToSearch);
		List<WebElement> productList = homeObj.getListOfProducts();

		for (WebElement pro : productList) {
			String[] product = pro.getText().split("-");
			String productName = product[0].trim();
			System.out.println(productName);
			Assert.assertTrue(product[0].trim().toLowerCase().contains(productToSearch));
		}

	}

	@Test(enabled = false, description = "Verify that if user search for wrong product or product which is "
			+ "not available he/she should get message[Sorry,no products matched your search!] stating "
			+ "that the searched product is not available.")
	public void test2() {
		HomePage homeObj = new HomePage(driver);
		homeObj.searchItem(wrongProductToSearch);
		List<WebElement> productList = homeObj.getListOfProducts();
		System.out.println(productList);
		Assert.assertTrue(productList.isEmpty());
		String error = homeObj.noProductAvailableErrorMsg();
		System.out.println(error);
		Assert.assertTrue(error.equalsIgnoreCase(noProductAvailableerrorMsg));

	}

	@Test(description = "Validate if user is able to add a product of choice to cart from the list "
			+ "of available products on dashboard/homepage and that product must be available/visible in cart.")
	public void test3() {
		HomePage homeObj = new HomePage(driver);
		homeObj.addProductToCart(productToBuy);
		CartPage cartObj = new CartPage(driver);
		cartObj.goToCart();
		List<WebElement> productInCart = cartObj.getProductsInCart();
		
		List<String> productName=new ArrayList<String>();
		for(int i=0;i<productInCart.size();i++)
		{
			String[] FullProductName=productInCart.get(i).getText().split("-");
			productName.add(FullProductName[0].trim().toLowerCase());
		}
		
		
//		System.out.println(productName);
		Collections.sort(productName);
//		System.out.println(productName);
		List<String> list=new ArrayList<>(Arrays.asList(productToBuy));
		Collections.sort(list);
//		System.out.println(list);
		Assert.assertEqualsNoOrder(productName, list);

	}

//	testcase : description="Validate when user click on add to cart button to add product to "
//			+ "cart the button text should get changed to added stating that product has been added to cart"

}
