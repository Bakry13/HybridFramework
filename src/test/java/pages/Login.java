package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.TestBase;

public class Login extends TestBase
{
    //----------------------------------Login Page Elements---------------------------------------
	public static String[] allowTxt = {"ALLOW","سماح"};
	//@AndroidFindBy(xpath = "//android.widget.Button[@text='ALLOW']")
	@AndroidFindBy(id = "android:id/button1")
	public static WebElement allowButton; //Log out element in side menu
	
	@AndroidFindBy(id = "com.emeint.android.myservices:id/tvOnboardingHeader")
	public static WebElement Hi; //Hi text in login page
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Type a mobile number']")
	public static WebElement msisdn; //Log out element in side menu
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='ادخل رقم الموبيل']")
	public static WebElement msisdn_Ar; //Log out element in side menu
	
	@AndroidFindBy(id = "com.emeint.android.myservices:id/btnLogin")
	//@AndroidFindBy(xpath = "//android.widget.Button[@text='Proceed']")
	public static WebElement proceedBtn; //NT user name text box
	
	
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Password']")
	public static WebElement password; //Log out element in side menu
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='الرقم السري']")
	public static WebElement password_Ar; //Log out element in side menu
	
	@AndroidFindBy(id = "com.emeint.android.myservices:id/imgLanguage")
	public static WebElement xhangeLanguage; //Change language from En to Ar and vice versa
	
	public Login(AndroidDriver driver)
	{
        //this.driver = driver;
        //PageFactory.initElements(driver, this); //This initElements method will create all WebElements
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		if (General.languageID == 1)
		{
			//Updating Elements with Arabic values====
			msisdn = msisdn_Ar;
			password = password_Ar;
		}
    }
}


