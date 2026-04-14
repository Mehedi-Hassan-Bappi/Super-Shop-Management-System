package bd.edu.seu.supershopmanagementsystem.service;

import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.Model.Stationary;
import bd.edu.seu.supershopmanagementsystem.utility.ConnectionSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SellStationaryService {
    public boolean buy(Stationary stationary) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO sellstationary VALUES('"+stationary.getType()+"','"+stationary.getName()+"','"+stationary.getPrice()+"','"+stationary.getProfit()+"','"+stationary.getQuantity()+"');";
            statement.execute(query);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    public static double stationaryProfit(){
        double profit = 0.0;
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM sellstationary";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                double profitValue = resultSet.getDouble("profit");
                profit+=profitValue;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return profit;
    }

}
