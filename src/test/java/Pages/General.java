package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utilities.TestBase;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class General extends TestBase
{
    //----------------------------------Ana Vodafone App---------------------------------------
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Ana Vodafone,Unlocked\"]/android.view.View")
	public static WebElement RecentAnaVodafone_Xiaomi; //Log out element in side menu
	
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Ana Vodafone\"]/android.view.View[1]")
	public static WebElement RecentAnaVodafone_Emulator; //Log out element in side menu
	
	//---------------------------------------App Language---------------------------------------
	public static int LanguageID = 0; //0 -> for En, 1 -> for Ar
	public static String[] Language = {"En", "Ar"};
	//------------------------------------------------------------------------------------------
	
	public static WebElement ScrollAndFind(String visibleText) //Find element by visible text using scroll
	{
		WebElement Element = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))");
		return Element;
	}
	
	public static WebElement ScrollHorizontal(String ResourceId, String ClassName, String visibleText)
	{
		WebElement Element = driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()."
					  + "resourceId(\""+ResourceId+"\")).setAsHorizontalList().getChildByText("
                      + "new UiSelector().className(\""+ClassName+"\"), \""+visibleText+"\")"));
		
		return Element;

	}

	public General(AndroidDriver driver)
	{
        //this.driver = driver;
        //PageFactory.initElements(driver, this); //This initElements method will create all WebElements
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
	
	//--------------------------------Objects of Page Factory--------------------------------------------------
	public static void PageFactoryObject()
	{
		General GeneralElementObject = new General(driver);
		Login LoginElementObject = new Login(driver);
	}
}
