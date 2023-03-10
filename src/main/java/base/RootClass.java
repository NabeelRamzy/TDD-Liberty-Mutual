package base;

import static utils.IConstant.BROWSER;
import static utils.IConstant.CHROME;
import static utils.IConstant.EDGE;
import static utils.IConstant.EXPLICIT_WAIT;
import static utils.IConstant.FIREFOX;
import static utils.IConstant.IMPLECIT_WAIT;
import static utils.IConstant.PAGELOAD_WAIT;
import static utils.IConstant.SAFARI;
import static utils.IConstant.URL;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.AddressPage;
import pageObject.LandingPage;
import pageObject.PersonalInfoPage;
import utils.ConfigProperties;

public class RootClass {

	protected WebDriver driver;
	protected LandingPage landingPage;
	protected AddressPage addressPage;
	protected PersonalInfoPage personalInfoPage;
	ConfigProperties configProperties;

	@BeforeSuite
	public void setUpSuite() {
		configProperties = new ConfigProperties();

	}

	@BeforeMethod
	public void setUpDriver() {
		String browser = configProperties.getProperty(BROWSER);
		String url = configProperties.getProperty(URL);
		long pageLoadWait = configProperties.getNumProperty(PAGELOAD_WAIT);
		long implicitlyWait = configProperties.getNumProperty(IMPLECIT_WAIT);
		long explicitWait = configProperties.getNumProperty(EXPLICIT_WAIT);

		initDriver(BROWSER);
		initClass(driver);

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadWait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(implicitlyWait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(explicitWait));

	}

	private void initClass(WebDriver driver) {
		landingPage = new LandingPage(driver);
		addressPage = new AddressPage(driver);
		personalInfoPage = new PersonalInfoPage(driver);
	}

	private void initDriver(String driverName) {
		switch (driverName) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case SAFARI:
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		}
	}

	@AfterMethod
	public void tearUp() {
		driver.quit();

	}

}
