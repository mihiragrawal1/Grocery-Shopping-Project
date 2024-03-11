package proj_package.Tests;

import org.testng.annotations.Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
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

	String productToSearch = "Banana";
	String wrongProductToSearch = "Mihir";
	String noProductAvailableerrorMsg = "Sorry, no products matched your search!";
	String[] productToBuy = { "Walnuts", "Banana" ,"beetroot","cucumber"};
	String buySingleProduct = "Carrot";
	String addToCartBtnTextAfterClick = "ADDED";
	String[] removeProductFromCart= {"beetroot"};
	String[] finalItemList= { "Walnuts", "Banana","cucumber"};
	public String[] convertArrayToLowercase(String[] arr) {
		String[] lowercaseArray = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			lowercaseArray[i] = arr[i].toLowerCase();
		}
		return lowercaseArray;
	}

	@Test(groups = "smoke", description = "verify if user search for a product in searchbar, user gets the appropiate "
			+ "results(product displayed after search should match/contains the string searched by user)")
	public void test1() {
		HomePage homeObj = new HomePage(driver);
		homeObj.searchItem(productToSearch);

		List<WebElement> productList = homeObj.getListOfProducts();

		System.out.println(productList.size());
		for (WebElement pro : productList) {
			String[] product = pro.getText().split("-");
			String productName = product[0].trim();
			System.out.println(productName);

			Assert.assertTrue(productName.toLowerCase().contains(productToSearch.toLowerCase()));
		}

	}

	@Test(description = "Verify that if user search for wrong product or product which is "
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
		String[] lowerCaseArray = convertArrayToLowercase(productToBuy);

		homeObj.addProductToCart(lowerCaseArray);
		CartPage cartObj = new CartPage(driver);
		cartObj.goToCart();
		List<WebElement> productInCart = cartObj.getProductsInCart();

		List<String> productName = new ArrayList<String>();
		for (int i = 0; i < productInCart.size(); i++) {
			String[] FullProductName = productInCart.get(i).getText().split("-");
			productName.add(FullProductName[0].trim().toLowerCase());
		}

//		System.out.println(productName);
		Collections.sort(productName);
//		System.out.println(productName);
		List<String> list = new ArrayList<>(Arrays.asList(lowerCaseArray));
		Collections.sort(list);
//		System.out.println(list);
		Assert.assertEqualsNoOrder(productName, list);

	}

	@Test(description = "validate if user can search for a specific product and add it to cart "
			+ "also verify that searched product added to cart are actually available/visible in cart")
	public void test4() {
		HomePage homeObj = new HomePage(driver);
		homeObj.searchItem(productToSearch);

		List<WebElement> visibleProduct = homeObj.getListOfProducts();

		System.out.println(visibleProduct);
		for (WebElement ele : visibleProduct) {
			String[] productFullName = ele.getText().split("-");
			if (productFullName[0].toLowerCase().contains(productToSearch)) {
				String[] searchedProductToBuy = { productFullName[0].toLowerCase() };
				String[] lowerCaseArray = convertArrayToLowercase(searchedProductToBuy);
				homeObj.addProductToCart(lowerCaseArray);

				CartPage cartObj = new CartPage(driver);
				cartObj.goToCart();
				List<WebElement> productInCart = cartObj.getProductsInCart(); // validating that searched product added
																				// to cart
																				// are actually available/visible in
																				// cart
				List<String> productName = new ArrayList<String>();
				for (int i = 0; i < productInCart.size(); i++) {
					String[] FullProductName = productInCart.get(i).getText().split("-");
					productName.add(FullProductName[0].trim().toLowerCase());
				}
//				System.out.println(productName);
				Collections.sort(productName);
//				System.out.println(productName);
				List<String> list = new ArrayList<>(Arrays.asList(lowerCaseArray));
				Collections.sort(list);
//				System.out.println(list);
				Assert.assertEqualsNoOrder(productName, list);
				Assert.assertEquals(productName.size(), list.size());
			}

		}

	}

	@Test(description = "Validate when user click on add to cart button to add product to "
			+ "cart the button text should get changed to added stating that product has been succesfully added to cart")
	public void test5() {
		HomePage homeObj = new HomePage(driver);
	
		String[] productToBuy = { buySingleProduct };
		String[] lowerCaseArray = convertArrayToLowercase(productToBuy);

		String btnText = homeObj.addProductToCart(lowerCaseArray);
		System.out.println(btnText + "full btn text===");
		String[] fullButtonText = btnText.split(" ");
		System.out.println(fullButtonText[1].toLowerCase());
		Assert.assertEquals(fullButtonText[1].toLowerCase(), addToCartBtnTextAfterClick.toLowerCase());
	}
	
	
	
	//user should be able to remove product from cart if added by mistake
	@Test(description="Validate if user can remove the product from cart after adding")
	public void test6() throws InterruptedException {
		HomePage homeObj = new HomePage(driver);
		String[] lowerCaseArray = convertArrayToLowercase(productToBuy);
		homeObj.addProductToCart(lowerCaseArray);
		CartPage cartObj=new CartPage(driver);
		cartObj.goToCart();
		List<WebElement> itemInCart=cartObj.getProductsInCart();
		System.out.print(itemInCart.size());
		cartObj.removeItemFromCart(itemInCart,removeProductFromCart);
		Thread.sleep(2000);
		List<WebElement> finalItemInCart=cartObj.getProductsInCart();
		List<String> finalProducts=new ArrayList<>();
		for(int i=0;i<finalItemInCart.size();i++)
		{
			String[] itemName=finalItemInCart.get(i).getText().split(" ");
			finalProducts.add(itemName[0].trim().toLowerCase());
			
			
		}
		System.out.println(finalProducts);
		Collections.sort(finalProducts);
		List<String> itemsFinal=new ArrayList<>(Arrays.asList(convertArrayToLowercase(finalItemList)));
		Collections.sort(itemsFinal);
		Assert.assertEqualsNoOrder(finalProducts,itemsFinal);
		System.out.println(finalProducts.size());
		System.out.println(itemsFinal.size());
		Assert.assertEquals(finalProducts.size(), itemsFinal.size());
		
		
	}
	//e2e product buy

}
