package xyz.selenide.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import xyz.selenide.model.CustomerInformation;
import xyz.selenide.tasks.Login;
import xyz.selenide.tasks.Manager;
import xyz.selenide.tasks.Navigate;

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

}
