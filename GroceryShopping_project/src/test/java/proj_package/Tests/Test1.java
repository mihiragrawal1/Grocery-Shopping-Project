package proj_package.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import proj_package.BaseComponent.BaseTest;
import proj_package.Pages.HomePage;

public class Test1 extends BaseTest {

	String productToSearch = "banana";
	String wrongProductToSearch = "Mihir";
	String noProductAvailableerrorMsg = "Sorry, no products matched your search!";

	@Test(groups = "smoke", description = "verify if user search for a product in searchbar, user gets the appropiate "
			+ "results(product displayed after search should match/contains the string searched by user)")
	public void test1()
	{
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

}
