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
//
//    public static void toOpenAccount(Page page) {
//        ManagerHomePage managerHomePage = new ManagerHomePage(page);
//        managerHomePage.getBtnOpenAccount().click();
//    }
//
//    public static void toCustomers(Page page) {
//        ManagerHomePage managerHomePage = new ManagerHomePage(page);
//        managerHomePage.getBtnCustomers().click();
//    }
}
