package controller;

public interface TransactionFilter {
    List<Transaction> filter(List<Transaction> lst);
  
}