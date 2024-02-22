package proj_package.BaseComponent;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {

	public WebDriver driver;

	@Test
	public WebDriver browserConfig() throws IOException {

//		String url=null;
		Properties props = new Properties();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//proj_package//resources//Base_resource.properties");

		props.load(fis);

		String browsername = System.getProperty("browser") != null ? System.getProperty("browser")
				: props.getProperty("browser");

//		url=props.getProperty(url);
		switch (browsername.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;
		}

		return driver;

	}

	@BeforeMethod
	public void launchBrowser() throws IOException {
		driver = browserConfig();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
