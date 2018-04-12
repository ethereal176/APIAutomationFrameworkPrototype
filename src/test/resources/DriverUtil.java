package test.resources;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtil {
	// Instatiates the files for the WebDriver Executables
	private File chromeDriver = new File(System.getProperty("webdriver.chrome.driver"));
	private File edgeDriver = new File(System.getProperty("webdriver.edge.driver"));
	private File fireFoxDriver = new File(System.getProperty("webdriver.firefox.driver"));
	private File ieDriver = new File(System.getProperty("webdriver.ie.driver"));

	// Captures the System Property for the "browser"
	private String browser = System.getProperty("browser");

	public WebDriver createWebDriver() {
		setDriverSystemProperty();
		return initializeDriver();
	}

	private WebDriver initializeDriver() {
		switch (browser.toLowerCase()) {
		case "chrome":
			return new ChromeDriver();
		case "edge":
			return new EdgeDriver();
		// case "ff":
		// return new FirefoxDriver();
		case "ie":
			return new InternetExplorerDriver();
		default:
			return new InternetExplorerDriver();
		}
	}

	private void setDriverSystemProperty() {
		if(browser == null) browser = "edge";
		
		switch (browser.toLowerCase()) {
		case "chrome":
		case "gc":
			browser = "chrome";
			System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
			break;
		case "edge":
			browser = "edge";
			System.setProperty("webdriver.edge.driver", edgeDriver.getAbsolutePath());
			break;
		case "firefox":
		case "ff":
			browser = "ff";
			System.setProperty("webdriver.firefox.driver", fireFoxDriver.getAbsolutePath());
			break;
		case "ie":
		case "internet explorer":
			browser = "ie";
			System.setProperty("webdriver.ie.driver", ieDriver.getAbsolutePath());
			break;
		default:
			browser = "ff";
			System.setProperty("webdriver.firefox.driver", fireFoxDriver.getAbsolutePath());
			break;
		}
	}
}
