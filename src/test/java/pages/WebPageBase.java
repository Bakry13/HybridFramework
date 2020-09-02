package pages;

import utilities.BDDTestBase;

public class WebPageBase extends BDDTestBase
{
	public static void pageFactoryObject()
	{
		GoogleHome googleHomeElementObject = new GoogleHome(driver);
		GoogleWiki googleWikiElementObject = new GoogleWiki(driver);
	}
}
