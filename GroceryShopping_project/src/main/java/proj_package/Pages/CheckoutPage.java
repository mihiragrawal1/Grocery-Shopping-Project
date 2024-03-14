package proj_package.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import proj_package.Utilities.WaitUtility;

public class CheckoutPage extends WaitUtility{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='wrapperTwo'] //select")
	WebElement countryDropdownLocator;
	
	@FindBy(css="input[class='chkAgree']")
	WebElement TermAndConditionheckbox;
	
	@FindBy(xpath="//button[text()='Proceed']")
	WebElement proceedBtn;
	
	

	
	public void selectCountry(String country)
	{
		Select dropdown=new Select(countryDropdownLocator);
		dropdown.selectByVisibleText(country);
	}
	
	public void checkTermAndConditions()
	{
		waitForElement(TermAndConditionheckbox);
		TermAndConditionheckbox.click();
	}
	public void clickProceed()
	{
		waitForElement(proceedBtn);
		proceedBtn.click();
	}
}
