package bd.edu.seu.supershopmanagementsystem.service;

import bd.edu.seu.supershopmanagementsystem.Model.Transaction;
import bd.edu.seu.supershopmanagementsystem.utility.ConnectionSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionService {
    public boolean insert(Transaction transaction) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO transaction VALUES('"+transaction.getNid()+"','"+transaction.getAmount()+"');";
            statement.execute(query);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
