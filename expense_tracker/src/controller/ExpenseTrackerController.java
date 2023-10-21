package controller;

import view.ExpenseTrackerView;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import model.ExpenseTrackerModel;
import model.Transaction;

public class ExpenseTrackerController {

  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }

    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[] { t.getAmount(), t.getCategory(), t.getTimestamp() });
    refresh();
    return true;
  }

  // Other controller methods
  public List<Transaction> applyFilter(int strategy, List<Transaction> ogLst, double filterAmount,
      String filterCategory) {
    List<Transaction> res = new ArrayList<>();
    List<Transaction> t = model.getTransactions();
    if (strategy == 0) {
      AmountFilter amountFilter = new AmountFilter(filterAmount);
      res = amountFilter.filter(ogLst);
    } else if (strategy == 1) {
      System.out.println("HERE");
      System.out.println(filterCategory);
      CategoryFilter categoryFilter = new CategoryFilter(filterCategory);
      res = categoryFilter.filter(ogLst);
    }
    final List<Transaction> finalRes = res;
    JTable transactionsTable = view.getTransactionsTable();
    transactionsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
      @Override 
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                    boolean hasFocus, int row, int column) {
          boolean inFilter = false;
          Component c = super.getTableCellRendererComponent(table, value, isSelected, true, row, column);
          if (row < t.size()) {
            for (Transaction r: finalRes) {
              if (r.getAmount() ==  t.get(row).getAmount() && r.getCategory() == t.get(row).getCategory()) {
                inFilter = true;
                System.out.println(row);
                break;
              }
            }
          }
          if (inFilter) {
            c.setBackground(new Color(173, 255, 168)); // Light green
          } else {
            c.setBackground(table.getBackground());
          }
          return c;
      }
    });
    System.out.println(res);
    return res;

  }


}