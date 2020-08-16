package steps;

import Utilities.BDDTestBase;

import static org.testng.Assert.fail;

import Pages.GoogleHome;
import Pages.GoogleWiki;
import Pages.WebPageBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Search extends BDDTestBase
{
	String WikipediaTitle = "Wikipedia";
	
	@Given("^The user is in Google page$")
	public void the_user_is_in_google_page() {
	    WebPageBase.PageFactoryObject();
	    Driver.get("https://www.google.com.eg/");
	}

	@When("^User type Wikipedia word$")
	public void user_type_wekipedia_word() {
	    GoogleHome.SearchBox.sendKeys("Wikipedia");
	}

	@When("^Press Search$")
	public void press_search() {
	    GoogleHome.SearchButton.click();
	}

	@When("^Choose Wikipedia website$")
	public void choose_wikipedia_website() {
	    GoogleWiki.FirstOption.click();
	}

	@Then("^Wikipedia website is opened$")
	public void wikipedia_website_is_opened() {
		WikipediaTitle = Driver.getTitle();
		assert(WikipediaTitle.equals("Wikipedia"));
	}
	
	@Given("^The user navigate to Google page$")
	public void the_user_navigate_to_google_page() {
	    Driver.navigate().to("https://www.google.com.eg/");
	}

	@When("^User type the \"(.*)\"$")
	public void user_type_the(String word) {
		GoogleHome.SearchBox.sendKeys(word);
	}
	
	@When("^Press Search again$")
	public void Press_search_again() throws InterruptedException {
	    GoogleHome.SearchButton.click();
	    Thread.sleep(500);
	}
/*	
	@Then("^Close Browser$")
	public void close_browser()
	{
		Driver.quit();
	}
	*/
}
