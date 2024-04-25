package xyz.selenide.tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;
import xyz.selenide.model.*;
import xyz.selenide.userInterface.AddCustomerPage;
import xyz.selenide.userInterface.CustomersPage;
import xyz.selenide.userInterface.OpenAccountPage;

import static com.codeborne.selenide.Selenide.switchTo;

public class Manager {

    public static void enterCustomerInformation(CustomerInformation customer) {
        AddCustomerPage addCustomerPage = new AddCustomerPage();
        addCustomerPage.getInputFirstName().setValue(customer.getFirstName());
        addCustomerPage.getInputLastName().setValue(customer.getLastName());
        addCustomerPage.getInputPostCode().setValue(customer.getPostCode());
    }

    public static void addCustomer() {
        AddCustomerPage addCustomerPage = new AddCustomerPage();
        addCustomerPage.getButtonAddCustomer().click();

        Alert info = switchTo().alert();
        info.accept();
    }

    public static boolean areCustomerFieldsCleared() {
        AddCustomerPage addCustomerPage = new AddCustomerPage();
        boolean firstName = addCustomerPage.getInputFirstName().has(Condition.empty);
        boolean lastName = addCustomerPage.getInputLastName().has(Condition.empty);
        boolean postCode = addCustomerPage.getInputPostCode().has(Condition.empty);
        return firstName && lastName && postCode;
    }

    public static boolean isCustomerInTheList(CustomerInformation customerInformation) {
        CustomersPage customersPage = new CustomersPage();
        ElementsCollection rows = customersPage.getTableCustomersRows();
        String customerRowText = rows.findBy(Condition.partialText(customerInformation.getFirstName())).getText();
        boolean isFirstNameVisible = customerRowText.contains(customerInformation.getFirstName());
        boolean isLastNameVisible = customerRowText.contains(customerInformation.getLastName());
        boolean isPostCodeVisible = customerRowText.contains(customerInformation.getPostCode());
        return isFirstNameVisible && isLastNameVisible && isPostCodeVisible;
    }

    public static boolean isCustomerNotInTheList(CustomerInformation customerInformation) {
        CustomersPage customersPage = new CustomersPage();
        return customersPage.isCustomerNotInTheList(customerInformation);
    }

    public static String openCustomerAccount(CustomerInformation customerInformation, Currency currency) {
        OpenAccountPage openAccountPage = new OpenAccountPage();
        openAccountPage.getDrpCustomer().selectOption(customerInformation.toStringShort());
        openAccountPage.getDrpCurrency().selectOption(currency.getCurrency());
        openAccountPage.getButtonProcess().click();

        Alert info = switchTo().alert();
        return info.getText();
    }

    public static boolean isCustomerWithAccountInTheList(CustomerInformation customerInformation, String accountNumber) {
        boolean isCustomerInTheList = isCustomerInTheList(customerInformation);
        CustomersPage customersPage = new CustomersPage();
        ElementsCollection rows = customersPage.getTableCustomersRows();
        String customerRowText = rows.findBy(Condition.partialText(customerInformation.getFirstName())).getText();
        boolean isAccountVisible = customerRowText.contains(accountNumber);

        return isCustomerInTheList && isAccountVisible;
    }

    public static void searchCustomers(CustomerInformation customerInformation) {
        CustomersPage customersPage = new CustomersPage();
        //needed to trick the test into waiting for input to be ready
        customersPage.getInputSearchCustomers().shouldBe(Condition.attribute("ng-model", "searchCustomer"));
        customersPage.getInputSearchCustomers().setValue(customerInformation.getFirstName());
    }

    public static int customerCount() {
        CustomersPage customersPage = new CustomersPage();
        return customersPage.getTableCustomersRows().size() - 1;
    }

    public static void sortCustomers(CustomerSortColumn sortColumn, SortOrder sortOrder) {
        CustomersPage customersPage = new CustomersPage();
        SelenideElement columnTarget = customersPage.getLinkFirstName();
        switch (sortColumn) {
            case LAST_NAME -> columnTarget = customersPage.getLinkLastName();
            case POST_CODE -> columnTarget = customersPage.getLinkPostCode();
        }
        switch (sortOrder) {
            case DESC -> columnTarget.click();
            case ASC -> {
                columnTarget.click();
                columnTarget.click();
            }
        }
    }

    public static Customers getCustomerList() {
        CustomersPage customersPage = new CustomersPage();
        return new Customers(customersPage.getTableCustomersRows());
    }

    public static void deleteCustomer(CustomerInformation customerInformation) {
        searchCustomers(customerInformation);
        CustomersPage customersPage = new CustomersPage();
        customersPage.getButtonDelete().click();
    }

    public static void clearCustomerSearch() {
        CustomersPage customersPage = new CustomersPage();
        customersPage.getInputSearchCustomers().clear();
    }
}
