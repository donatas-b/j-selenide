package xyz.selenide;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags="", features={"src/test/resources/features/manager.feature"}, glue={"xyz.selenide.stepDefinitions"}, plugin="html")
public class Tests extends AbstractTestNGCucumberTests {
}
