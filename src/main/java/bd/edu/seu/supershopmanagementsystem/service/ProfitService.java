package bd.edu.seu.supershopmanagementsystem.service;

import bd.edu.seu.supershopmanagementsystem.Model.Profit;
import bd.edu.seu.supershopmanagementsystem.utility.ConnectionSingleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfitService {
    public boolean collectProfit(Profit profit) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO profit VALUES('"+profit.getProfit()+"');";
            statement.execute(query);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    public boolean updateProfit(Profit profit){
        try{
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE profit SET profitAmount ='"+profit.getProfit()+"';";
            statement.execute(query);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
