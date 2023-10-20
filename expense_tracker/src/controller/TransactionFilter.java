package controller;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public interface TransactionFilter {
    List<Transaction> filter(List<Transaction> lst);
  
}