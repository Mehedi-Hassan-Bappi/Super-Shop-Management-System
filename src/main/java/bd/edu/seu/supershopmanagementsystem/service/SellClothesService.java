package bd.edu.seu.supershopmanagementsystem.service;

import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.utility.ConnectionSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SellClothesService {
    public boolean buy(Clothes clothes) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO sellclothes VALUES('"+clothes.getType()+"','"+clothes.getName()+"','"+clothes.getPrice()+"','"+clothes.getQuantity()+"','"+clothes.getProfit()+"');";
            statement.execute(query);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    public static double clothesProfit(){
        double profit = 0.0;
        try{
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM sellclothes";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                double profitValue = resultSet.getDouble("profit");
                profit+=profitValue;
            }
            //profit = Double.parseDouble(0+query);
//            System.out.println(profit);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return profit;
    }
}
