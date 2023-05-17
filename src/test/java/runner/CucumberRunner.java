package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        features = "classpath:features/",
        glue = {"com.muradov.analytics.cucumberglue"},
        plugin = {"pretty", "html:target/cucumber-report.html"})

public class CucumberRunner {
}
