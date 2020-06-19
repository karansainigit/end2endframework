package cucumberoptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
//import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features", glue="stepdefinitions")

/*public class TestRunner extends AbstractTestNGCucumberTests{

}*/

public class TestRunner {

}
