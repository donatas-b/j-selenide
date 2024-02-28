package xyz.selenide.model;

import com.codeborne.selenide.ElementsCollection;

import java.util.ArrayList;
import java.util.Comparator;

public class Customers extends ArrayList<CustomerInformation> {

    public Customers(ElementsCollection rows) {
        rows.texts().subList(1, rows.size()).forEach(row -> this.add(new CustomerInformation(row)));
    }

    public void sort(CustomerSortColumn column, SortOrder sortOrder) {
        switch (sortOrder) {
            case ASC -> {
                switch (column) {
                    case FIRST_NAME -> this.sort(Comparator.comparing(CustomerInformation::getFirstName));
                    case LAST_NAME -> this.sort(Comparator.comparing(CustomerInformation::getLastName));
                    case POST_CODE -> this.sort(Comparator.comparing(CustomerInformation::getPostCode));
                }
            }
            case DESC -> {
                switch (column) {
                    case FIRST_NAME -> this.sort(Comparator.comparing(CustomerInformation::getFirstName).reversed());
                    case LAST_NAME -> this.sort(Comparator.comparing(CustomerInformation::getLastName).reversed());
                    case POST_CODE -> this.sort(Comparator.comparing(CustomerInformation::getPostCode).reversed());
                }
            }
        }
    }
}
