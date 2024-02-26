package xyz.selenide.userInterface;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class LoginPage {
    private final SelenideElement btnCustomerLogin;
    private final SelenideElement btnBankManagerLogin;

    public LoginPage() {
        this.btnCustomerLogin = $(byText("Customer Login"));
        this.btnBankManagerLogin = $(byText("Bank Manager Login"));
    }
}
