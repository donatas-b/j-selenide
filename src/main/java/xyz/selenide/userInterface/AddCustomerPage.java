package xyz.selenide.userInterface;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class AddCustomerPage {
    private final SelenideElement inputFirstName;
    private final SelenideElement inputLastName;
    private final SelenideElement inputPostCode;
    private final SelenideElement buttonAddCustomer;

    public AddCustomerPage() {
        this.inputFirstName = $(by("placeholder", "First Name"));
        this.inputLastName = $(by("placeholder", "Last Name"));
        this.inputPostCode = $(by("placeholder", "Post Code"));
        this.buttonAddCustomer = $(byXpath("//button[contains(@class, 'btn-default')]"));
    }
}
