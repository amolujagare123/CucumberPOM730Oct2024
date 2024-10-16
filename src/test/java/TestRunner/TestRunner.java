package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features"
        , glue ="stepdefinitions"
        , tags = "@starverify"
       // , dryRun = true
        ,plugin = {"pretty","html:target/report.html"}
)
public class TestRunner {
}
