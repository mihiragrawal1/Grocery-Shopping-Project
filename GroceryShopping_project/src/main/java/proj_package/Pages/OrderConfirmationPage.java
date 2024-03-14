package proj_package.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import proj_package.Utilities.WaitUtility;

public class OrderConfirmationPage extends WaitUtility{
	
	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(className="wrapperTwo")
	WebElement thankYouMsg;
	
	
	public String getMsg()
	{
		waitForElement(thankYouMsg);
		String msg=thankYouMsg.getText();
		return msg;
		
	}
}

