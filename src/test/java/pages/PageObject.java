package pages;

import org.testng.annotations.BeforeTest;

import utilities.GlobalParams;
import utilities.TestBase;

public class PageObject extends TestBase
{
	//--------------------------------Objects of Page Factory--------------------------------------------------
	@BeforeTest
	public static void PageFactoryObject()
	{
//		GlobalParams GlobalPageObject = new GlobalParams(driver);
//		Menu menuPageObject = new Menu(driver);
//		Home homePageObject = new Home(driver);
//		Logout logoutPageObject = new Logout(driver);
		Login loginPageObject = new Login(driver);
	}
}
