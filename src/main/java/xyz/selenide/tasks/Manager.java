package xyz.selenide.tasks;

import xyz.selenide.model.CustomerInformation;
import xyz.selenide.userInterface.AddCustomerPage;

public class Manager {

    public static void enterCustomerInformation(CustomerInformation customer) {
        AddCustomerPage addCustomerPage = new AddCustomerPage();
        addCustomerPage.getInputFirstName().setValue(customer.getFirstName());
        addCustomerPage.getInputLastName().setValue(customer.getLastName());
        addCustomerPage.getInputPostCode().setValue(customer.getPostCode());
    }

}
