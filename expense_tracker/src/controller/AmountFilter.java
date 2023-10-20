package controller;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class AmountFilter implements TransactionFilter {
    private double filterAmount;
    public AmountFilter (double amount){
        this.filterAmount = amount;



    }

    @Override
    public List<Transaction> filter(List<Transaction> lst){
        List<Transaction> resultLst = new ArrayList<>();
        for (Transaction t: lst){
            if (t.getAmount() == this.filterAmount){
                resultLst.add(t);
            }

        }
        return resultLst;

    }

    
}
