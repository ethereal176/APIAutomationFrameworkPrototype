package test.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import test.resources.DriverUtil;

public class OverstockSearchTest {
	@Test
	public void searchForSomethingTest() {
		WebDriver driver = new DriverUtil().createWebDriver();
		driver.get("www.overstock.com");
		driver.quit();
	}
}
