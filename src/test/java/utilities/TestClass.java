package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

public class TestClass 
{
	@Test
	public void testMethod()
	{
		WebDriver driver = new HtmlUnitDriver();
		driver.get("https://www.google.com.eg/");
		System.out.println(driver.getCurrentUrl());
	}
}
