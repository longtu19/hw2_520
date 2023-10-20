package controller;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class CategoryFilter implements TransactionFilter {
    private String userCategory;

    public CategoryFilter(String inputCategory) {
        this.userCategory = inputCategory;

    }

    @Override
    public List<Transaction> filter(List<Transaction> lst) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : lst) {
            if (t.getCategory().equals(this.userCategory)) {
                result.add(t);
            }
        }
        return result;

    }

}
