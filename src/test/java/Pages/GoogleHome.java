package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BDDTestBase;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GoogleHome extends BDDTestBase
{
	@FindBy(name = "q")
	public static WebElement SearchBox; //Google Search box
	
	@FindBy(name = "btnK")
	public static WebElement SearchButton; //Google Search button
	
	public GoogleHome(WebDriver Driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(Driver), this);
    }
}
