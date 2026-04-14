package bd.edu.seu.supershopmanagementsystem.service;

import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.Model.Stationary;
import bd.edu.seu.supershopmanagementsystem.utility.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationaryService {
    public boolean addStationary(Stationary stationary){
        try{
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO stationary VALUES ('"+stationary.getType()+"','"+stationary.getName()+"','"
                    +stationary.getPrice()+"','"+stationary.getProfit()+"','"+stationary.getQuantity()+"','"+stationary.getImage()+"');";
            statement.execute(query);
            return true;
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    public List<Stationary> getStationary(){
        List<Stationary> stationaryList = new ArrayList<>();
        try{
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM stationary";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                double profit = resultSet.getDouble("profit");
                int quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                Stationary stationary = new Stationary(type,name,price,profit, quantity, image);
                stationaryList.add(stationary);
            }
    } catch (SQLException ex){
        ex.printStackTrace();
        }
        return stationaryList;
    }
    public boolean update(Stationary stationary){
        try{
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE stationary SET type ='"+stationary.getType()+"', price='"
                    +stationary.getPrice()+"',quantity='"+stationary.getQuantity()+"',image='"+stationary.getImage()+"'WHERE name='"+stationary.getName()+"';";
            statement.execute(query);
            return true;
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    public void delete(Stationary stationary){
        try{
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM stationary WHERE name='"+stationary.getName()+"';";
            statement.execute(query);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public boolean sold(Stationary stationary) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE stationary SET quantity='"+stationary.getQuantity()+"'WHERE type ='"+stationary.getType()+"';";
            System.out.println(query);
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
