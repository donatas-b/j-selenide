package xyz.selenide.stepDefinitions;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;

public class Hooks {

    @After
    public void After() {
        WebDriverRunner.closeWebDriver();
    }

}
