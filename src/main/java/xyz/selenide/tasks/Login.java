package xyz.selenide.tasks;

import xyz.selenide.userInterface.LoginPage;

public class Login {

    public static void asManager(){
        LoginPage loginPage = new LoginPage();
        loginPage.getBtnBankManagerLogin().click();
    }
}
