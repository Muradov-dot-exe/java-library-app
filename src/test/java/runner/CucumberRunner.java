package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@CucumberOptions(monochrome = true,
        features = "classpath:features",
        glue = {"com.muradov.analytics.cucumberglue"},
        plugin = {"pretty", "json:build/cucumber-reports/cucumber.json"})

public class CucumberRunner {
}
