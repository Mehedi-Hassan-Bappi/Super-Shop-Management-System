package bd.edu.seu.supershopmanagementsystem.service;

import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.Model.Employee;
import bd.edu.seu.supershopmanagementsystem.Model.Goods;
import bd.edu.seu.supershopmanagementsystem.utility.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodService {
    public boolean addGoods(Goods goods){
        try{
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO goods VALUES ('"+goods.getProductType()+"','"+goods.getName()+"','"+goods.getPrice()
                    +"','"+goods.getProfit()+"','"+goods.getQuantity()+"','"+goods.getDate()+"','"+goods.getImage()+"');";
            statement.execute(query);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    public List<Goods> getGoods(){
        List<Goods> goodsList = new ArrayList<>();
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM goods";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                double profit = resultSet.getDouble("profit");
                int quantity = resultSet.getInt("quantity");
                String date = resultSet.getString("date");
                String image = resultSet.getString("image");
                Goods goods = new Goods(type,name, price, profit, quantity, image, date);
                goodsList.add(goods);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return goodsList;
    }
    public boolean updateGoods(Goods goods) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE goods SET type ='" + goods.getProductType() + "',quantity = '" + goods.getQuantity() +
                    "',price = '" + goods.getPrice() + "',image='"+goods.getImage()+"'WHERE name = '" + goods.getName() + "';";
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean delete(Goods goods) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM goods WHERE name = '"+goods.getName()+"';";
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean sold(Goods goods) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE goods SET quantity='"+goods.getQuantity()+"'WHERE type ='"+goods.getProductType()+"';";
            System.out.println(query);
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
