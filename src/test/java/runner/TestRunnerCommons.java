package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", 
    glue = "steps",  
    plugin = {"json:target/cucumber.json", "html:target/cucumber-report-html-2.html"},
    tags = "@filterAtoZ or @filterZtoA" 
)
public class TestRunnerCommons {
    
}
