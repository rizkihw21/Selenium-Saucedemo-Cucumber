package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", 
    glue = "steps",  
    plugin = {"json:target/cucumber.json", "html:target/cucumber-report-html-1.html"},
    tags = "@loginValid or @loginInvalid or @loginwithoutInputUsername or @loginwithoutInputPassword" 
)
public class TestRunnerLogin {
    
}
