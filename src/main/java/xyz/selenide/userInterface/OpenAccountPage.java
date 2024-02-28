package xyz.selenide.userInterface;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class OpenAccountPage {
    private final SelenideElement drpCustomer;
    private final SelenideElement drpCurrency;
    private final SelenideElement buttonProcess;

    public OpenAccountPage() {
        this.drpCustomer = $(byXpath("//select[@id='userSelect']"));
        this.drpCurrency = $(byXpath("//select[@id='currency']"));
        this.buttonProcess = $(byText("Process"));
    }

}
