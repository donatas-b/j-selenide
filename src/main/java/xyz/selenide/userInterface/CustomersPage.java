package xyz.selenide.userInterface;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import xyz.selenide.model.CustomerInformation;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class CustomersPage {
    private final SelenideElement inputSearchCustomers;
    private final SelenideElement tableCustomers;
    private final ElementsCollection tableCustomersRows;
    private final SelenideElement buttonDelete;
    private final SelenideElement linkFirstName;
    private final SelenideElement linkLastName;
    private final SelenideElement linkPostCode;

    public CustomersPage() {
        this.inputSearchCustomers = $(byTagName("input"));
        this.tableCustomers = $(byTagName("table"));
        this.tableCustomersRows = $$(byTagName("tr"));
        this.buttonDelete = $(byText("Delete"));
        this.linkFirstName = $(byText("First Name"));
        this.linkLastName = $(byText("Last Name"));
        this.linkPostCode = $(byText("Post Code"));
    }

    public boolean isCustomerNotInTheList(CustomerInformation customerInformation) {
        SelenideElement notIn = $(byText(customerInformation.getFirstName())).shouldNot(Condition.exist);
        return notIn.exists();
    }
}
