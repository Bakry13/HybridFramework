package Pages;

import Utilities.BDDTestBase;

public class WebPageBase extends BDDTestBase
{
	public static void PageFactoryObject()
	{
		GoogleHome GoogleHomeElementObject = new GoogleHome(Driver);
		GoogleWiki GoogleWikiElementObject = new GoogleWiki(Driver);
	}
}
