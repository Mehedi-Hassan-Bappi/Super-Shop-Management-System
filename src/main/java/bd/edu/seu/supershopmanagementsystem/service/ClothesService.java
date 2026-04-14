package bd.edu.seu.supershopmanagementsystem.service;

import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.utility.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClothesService {
    public boolean addClothes(Clothes clothes) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO clothes VALUES('" + clothes.getType() + "','" + clothes.getName() + "','" + clothes.getSize() + "','"
                    + clothes.getGender() + "','" + clothes.getQuantity() + "','" + clothes.getPrice() + "','" + clothes.getProfit() + "','" + clothes.getImage() + "');";
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Clothes> geClothes() {
        List<Clothes> clothesList = new ArrayList<>();
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM clothes";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                String size = resultSet.getString("size");
                String gender = resultSet.getString("gender");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                double profit = resultSet.getDouble("profit");
                String image = resultSet.getString("image");
                Clothes clothes = new Clothes(type, name, size, gender, image, quantity, price, profit);
                clothesList.add(clothes);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clothesList;
    }
    public boolean delete(Clothes clothes) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM clothes WHERE name = '"+clothes.getName()+"';";
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean updateClothes(Clothes clothes) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE clothes SET type ='" + clothes.getType() + "',size ='" + clothes.getSize()
                    + "',quantity = '" + clothes.getQuantity() + "',price = '" + clothes.getPrice() + "',image='"+clothes.getImage()+"'WHERE name = '" + clothes.getName() + "';";
            System.out.println(query);
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean sold(Clothes clothes) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE clothes SET quantity='"+clothes.getQuantity()+"'WHERE type ='"+clothes.getType()+"';";
            System.out.println(query);
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
