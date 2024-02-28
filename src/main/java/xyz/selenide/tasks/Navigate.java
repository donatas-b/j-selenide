package xyz.selenide.tasks;

import xyz.selenide.userInterface.ManagerHomePage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class Navigate {
    public static void toBankPage() {
        open(baseUrl);
    }

    public static void toAddCustomer() {
        ManagerHomePage managerHomePage = new ManagerHomePage();
        managerHomePage.getBtnAddCustomer().click();
    }

    public static void toOpenAccount() {
        ManagerHomePage managerHomePage = new ManagerHomePage();
        managerHomePage.getBtnOpenAccount().click();
    }

    public static void toCustomers() {
        ManagerHomePage managerHomePage = new ManagerHomePage();
        managerHomePage.getBtnCustomers().click();
    }
}
