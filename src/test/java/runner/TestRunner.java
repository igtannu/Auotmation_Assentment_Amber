package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/java/features/" }, glue = { "stepDefinations", "hooks" }, plugin = {
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, tags = "@regression", dryRun = false

)

public class TestRunner extends AbstractTestNGCucumberTests {

}