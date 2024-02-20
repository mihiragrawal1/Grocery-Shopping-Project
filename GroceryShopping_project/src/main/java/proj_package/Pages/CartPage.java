package proj_package.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	
	@FindBy(css="a[class='cart-icon']")
	WebElement cartButton;
	
	@FindBy(xpath="//button[text()='PROCEED TO CHECKOUT']")
	WebElement proceedToCheckoutButton;
	
	
	
}
