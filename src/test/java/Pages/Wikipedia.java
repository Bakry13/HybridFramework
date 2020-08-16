package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BDDTestBase;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Wikipedia extends BDDTestBase
{
	@FindBy(xpath = "//span[@class='central-textlogo__image sprite svg-Wikipedia_wordmark']")
	public static WebElement PageTitle; //Google wikipedia first option
	
	public Wikipedia(WebDriver Driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(Driver), this);
    }
}