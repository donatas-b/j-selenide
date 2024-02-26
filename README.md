# j-selenide

This is an example of using Selenide with Java, TestNG and Cucumber for web UI automated tests. The tests are utilizing [XYZ Bank](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login) application.

Project is using Java with Selenide, Cucumber, TestNG.

Project architecture was somewhat influenced by Screenplay pattern (page objects provide only UI elements, Tasks are used to perform actions and get resuts from UI).

## Executing the tests
To run the sample project, you can either just run the `Tests` test runner class, use maven from the command line or run feature/ scenario individually from e.g. IntelliJ.

By default, the tests will run using Chrome.
```
$ mvn clean verify
```
## Useful links

### Web pages
https://selenide.org/index.html

https://www.baeldung.com/selenide

https://qaautomation.expert/2023/11/08/integration-of-cucumber-with-selenium-and-testng/


### GitHub repositories
https://github.com/selenide

https://github.com/selenide-examples

https://github.com/Pavelya/Selenide-cucumber-allure-reports-java



