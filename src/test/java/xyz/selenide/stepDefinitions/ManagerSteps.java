package xyz.selenide.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import xyz.selenide.model.*;
import xyz.selenide.tasks.Login;
import xyz.selenide.tasks.Manager;
import xyz.selenide.tasks.Navigate;

import java.util.List;

import static org.testng.Assert.*;

@Slf4j
public class ManagerSteps {

    private CustomerInformation currentCustomer;
    private String createdCustomerAccountNumber;

    @Given("Manager has logged in")
    public void managerHasLoggedIn() {
        Navigate.toBankPage();
        Login.asManager();
    }

    @When("he enters new Customer data")
    public void heEntersNewCustomerData() {
        Navigate.toAddCustomer();
        currentCustomer = CustomerInformation.random();
        Manager.enterCustomerInformation(currentCustomer);
    }

    @And("he tries to save it")
    public void heTriesToSaveIt() {
        Manager.addCustomer();
    }

    @Then("Customer fields should be cleared")
    public void customerFieldsShouldBeCleared() {
        assertTrue(Manager.areCustomerFieldsCleared(), "Customer fields were not cleared");
    }

    @And("Customer appears in Customer List")
    @And("Customer should appear in Customer List")
    public void customerShouldAppearInCustomerList() {
        Navigate.toCustomers();
        boolean isFound = Manager.isCustomerInTheList(currentCustomer);
        assertTrue(isFound, String.format("Customer '%s' is not in the list", currentCustomer.toStringShort()));
    }

    @Given("there is a Customer")
    public void thereIsACustomer() {
        Navigate.toAddCustomer();
        currentCustomer = CustomerInformation.random();
        Manager.enterCustomerInformation(currentCustomer);
        Manager.addCustomer();
    }

    @When("Manager opens {string} Account for Customer")
    public void managerOpensAccountForCustomer(String currency) {
        Navigate.toOpenAccount();
        String alertMessage = Manager.openCustomerAccount(currentCustomer, Currency.byValue(currency));
        createdCustomerAccountNumber = alertMessage.substring(alertMessage.indexOf(":") + 1);
        currentCustomer.addAccount(createdCustomerAccountNumber);
        assertTrue(alertMessage.contains("Account created successfully with account Number"));
    }

    @Then("Customer Account should appear in Customer List")
    public void customerAccountShouldAppearInCustomerList() {
        Navigate.toCustomers();
        assertTrue(Manager.isCustomerWithAccountInTheList(currentCustomer, createdCustomerAccountNumber), String.format("Customer '%s' is not in the list or it does not has account '%s'", currentCustomer.toStringShort(), createdCustomerAccountNumber));
    }

    @When("Manager does Search for Customer")
    public void managerDoesSearchForCustomer() {
        Navigate.toCustomers();
        Manager.searchCustomers(currentCustomer);
    }

    @And("Customer List should contain {int} Customer")
    public void customerListShouldContainCustomer(int expectedCustomerCount) {
        int actualCustomerCount = Manager.customerCount();
        log.info("Actual Customer count: {}", actualCustomerCount);
        assertEquals(actualCustomerCount, expectedCustomerCount, String.format("Expected Customer count %s but was %s", expectedCustomerCount, actualCustomerCount));
    }

    @When("Manager Sorts Customer List by {string} in {string} order")
    public void managerSortsCustomerListByInOrder(String columnName, String sortOrder) {
        Navigate.toCustomers();
        Manager.sortCustomers(CustomerSortColumn.byValue(columnName), SortOrder.byValue(sortOrder));
    }

    @Then("Customer list should be sorted by {string} in {string} order")
    public void customerListShouldBeSortedByInOrder(String columnName, String sortOrder) {
        Customers customers = Manager.getCustomerList();
        log.info("before sort: {}", customers);
        customers.sort(CustomerSortColumn.byValue(columnName), SortOrder.byValue(sortOrder));
        log.info("after sort: {}", customers);

        List<String> actualCustomerListStrings = Manager.getCustomerList().stream().map(CustomerInformation::toString).toList();
        log.info("actualCustomerListStrings: {}", actualCustomerListStrings);
        List<String> expectedCustomerListStrings = customers.stream().map(CustomerInformation::toString).toList();
        log.info("expectedCustomerListStrings: {}", expectedCustomerListStrings);

        assertEquals(actualCustomerListStrings, expectedCustomerListStrings, "Customer List is not sorted as expected");
    }

    @When("Manager deletes the Customer")
    public void managerDeletesTheCustomer() {
        Navigate.toCustomers();
        Manager.deleteCustomer(currentCustomer);
    }

    @Then("Customer should no longer appear in Customer List")
    public void customerShouldNoLongerAppearInCustomerList() {
        Manager.clearCustomerSearch();
        assertFalse(Manager.isCustomerNotInTheList(currentCustomer), String.format("Customer '%s' was not deleted", currentCustomer.toStringShort()));
    }
}
