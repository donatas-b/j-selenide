package xyz.selenide.userInterface;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class ManagerHomePage {
    private final SelenideElement btnAddCustomer;
    private final SelenideElement btnOpenAccount;
    private final SelenideElement btnCustomers;

    public ManagerHomePage() {
        this.btnAddCustomer = $(byText("Add Customer"));
        this.btnOpenAccount = $(byText("Open Account"));
        this.btnCustomers = $(byText("Customers"));
    }
}
