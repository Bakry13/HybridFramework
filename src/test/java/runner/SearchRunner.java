package runner;

import io.cucumber.testng.CucumberOptions;
import utilities.BDDTestBase;

@CucumberOptions(features="src/test/java/features"
, glue= {"steps"}
, plugin= {"pretty","html:Reports/CucumberReport.html"})
public class SearchRunner extends BDDTestBase
{

}
