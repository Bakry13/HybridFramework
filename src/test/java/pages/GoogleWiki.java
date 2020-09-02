package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.BDDTestBase;

public class GoogleWiki extends BDDTestBase
{
	@FindBy(xpath = "//div[@id='rso']//div[1]//div[1]//div[1]//a[1]//h3[1]//span[1]")
	public static WebElement FirstOption; //Google wikipedia first option
	
	public GoogleWiki(WebDriver driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}