package steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GoogleHome;
import pages.GoogleWiki;
import pages.WebPageBase;
import utilities.BDDTestBase;

public class Search extends BDDTestBase
{
	String WikipediaTitle = "Wikipedia";
	Scenario scenario;
	
	@Given("^The user is in Google page$")
	public void the_user_is_in_google_page() {
	    WebPageBase.pageFactoryObject();
	    driver.get("https://www.google.com.eg/");
	}

	@When("^User type Wikipedia word$")
	public void user_type_wekipedia_word() {
	    GoogleHome.searchBox.sendKeys("Wikipedia");
	}

	@When("^Press Search$")
	public void press_search() {
	    GoogleHome.searchButton.click();
	}

	@When("^Choose Wikipedia website$")
	public void choose_wikipedia_website() {
	    GoogleWiki.FirstOption.click();
	}

	@Then("^Wikipedia website is opened$")
	public void wikipedia_website_is_opened() {
		WikipediaTitle = driver.getTitle();
		try {
			assert(WikipediaTitle.equals("Wikipedi"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        System.out.println(scenario.getName());
    }
	
	@Given("^The user navigate to Google page$")
	public void the_user_navigate_to_google_page() {
	    driver.navigate().to("https://www.google.com.eg/");
	}

	@When("^User type the \"(.*)\"$")
	public void user_type_the(String word) {
		GoogleHome.searchBox.sendKeys(word);
	}
	
	@When("^Press Search again$")
	public void Press_search_again() throws InterruptedException {
	    GoogleHome.searchButton.click();
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
