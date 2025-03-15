package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", 
    glue = "steps",  
    plugin = {"json:target/cucumber.json", "html:target/cucumber-report-html-3.html"},
    tags = "@addToCartSuccess or @removeFromCartSuccess or @checkoutProduct or @checkoutProductwithoutFirstName or @checkoutProductwithoutLastName or @checkoutProductwithoutPostalCode" 
)
public class TestRunnerAddtoCart {
    
}
