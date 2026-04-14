package bd.edu.seu.supershopmanagementsystem.service;

import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.Model.Goods;
import bd.edu.seu.supershopmanagementsystem.utility.ConnectionSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SellGoodsService {
    public boolean buy(Goods goods) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO sellgoods VALUES ('"+goods.getProductType()+"','"+goods.getName()+"','"+goods.getPrice()+"','"+goods.getProfit()+"','"+goods.getQuantity()+"');";
            statement.execute(query);
            System.out.println(query);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    public static double groceryProfit(){
        double profit = 0.0;
        try{
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM sellgoods";
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
