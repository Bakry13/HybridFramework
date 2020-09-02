package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.BDDTestBase;

public class GoogleHome extends BDDTestBase
{
	@FindBy(name = "q")
	public static WebElement searchBox; //Google Search box
	
	@FindBy(name = "btnK")
	public static WebElement searchButton; //Google Search button
	
	public GoogleHome(WebDriver driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
