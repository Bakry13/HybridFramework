package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BDDTestBase;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GoogleWiki extends BDDTestBase
{
	@FindBy(xpath = "//div[@id='rso']//div[1]//div[1]//div[1]//a[1]//h3[1]//span[1]")
	public static WebElement FirstOption; //Google wikipedia first option
	
	public GoogleWiki(WebDriver Driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(Driver), this);
    }
}