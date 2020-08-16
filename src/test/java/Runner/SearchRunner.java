package Runner;

import Utilities.BDDTestBase;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/features"
, glue= {"steps"}
, plugin= {"pretty","html:Reports/CucumberReport.html"})
public class SearchRunner extends BDDTestBase
{

}
