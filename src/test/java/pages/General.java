package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.TestBase;

public class General extends TestBase
{
    //----------------------------------Ana Vodafone App---------------------------------------
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Ana Vodafone,Unlocked\"]/android.view.View")
	public static WebElement recentAnaVodafone_Xiaomi; //Log out element in side menu
	
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Ana Vodafone\"]/android.view.View[1]")
	public static WebElement recentAnaVodafone_Emulator; //Log out element in side menu
	
	//---------------------------------------App Language---------------------------------------
	public static int languageID = 0; //0 -> for En, 1 -> for Ar
	public static String[] language = {"En", "Ar"};
	//------------------------------------------------------------------------------------------
	
	public static WebElement scrollVertical(String visibleText) //Find element by visible text using scroll
	{
		WebElement Element = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))");
		return Element;
	}
	
	public static WebElement scrollHorizontal(String ResourceId, String ClassName, String visibleText)
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
