import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;
import java.util.List;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }

    });



    view.getCategoryFilter().addActionListener(e -> {
      String filterField = view.getFilterField();
      if(InputValidation.isValidCategory(filterField)){
        List<Transaction> res = controller.applyFilter(1, model.getTransactions(), 0, filterField);

      };
      
    });

    view.getAmountFilter().addActionListener(e -> {
      String filterField = view.getFilterField();
      double filterAmount = Double.parseDouble(filterField);
      if (InputValidation.isValidAmount(filterAmount)){
          List<Transaction> res = controller.applyFilter(0, model.getTransactions(), filterAmount, "");


      };
      
    });

  }

}