package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.BDDTestBase;

public class Wikipedia extends BDDTestBase
{
	@FindBy(xpath = "//span[@class='central-textlogo__image sprite svg-Wikipedia_wordmark']")
	public static WebElement pageTitle; //Google wikipedia first option
	
	public Wikipedia(WebDriver driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}