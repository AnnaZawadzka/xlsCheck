package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature file
        glue = "cucumber",                     // Package where your step definitions are
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner {
}