package expenseandincome_tracker_with_database;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionDAO {

    // Method to retrieve all transactions from the database
    public static List<Transaction> getAllTransaction() {
        // Create a list to store Transaction objects
        List<Transaction> transactions = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.out.println("Failed to establish a database connection.");
            return transactions;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM `expenditure`");
            rs = ps.executeQuery();

            // Iterate through the result set obtained from the SQL query
            while (rs.next()) {
                // Extract transaction details from the result set
                int id = rs.getInt("id");
                String transaction_type = rs.getString("transaction_type");
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");

                // Create a Transaction object with the retrieved details
                Transaction transaction = new Transaction(id,transaction_type, description, amount);
                // Add the Transaction object to the list
                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Clean up resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Return the list of transactions
        return transactions;
    }

    static void removeTransaction(int transactionId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static int getLastInsertedTransactionId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
