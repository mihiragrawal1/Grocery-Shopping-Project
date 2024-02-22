package proj_package.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import proj_package.Utilities.WaitUtility;

public class CartPage extends WaitUtility{
	
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	
	@FindBy(css="a[class='cart-icon']")
	WebElement cartButton;
	
	@FindBy(xpath="//button[text()='PROCEED TO CHECKOUT']")
	WebElement proceedToCheckoutButton;
	
	@FindBy(xpath="//div[@class='cart-preview active'] //p[@class='product-name']")  
	List<WebElement> productsListInCart;
	
	
	
	public void goToCart()
	{
		waitForElement(cartButton);
		cartButton.click();
	}
	
	public List<WebElement> getProductsInCart()
	{
	
		
		try {
			waitForListElements(productsListInCart);
		
		return productsListInCart;
		}
		catch(Exception e)
		{
			return new ArrayList<>();
		}
	}
	
	
	
}
