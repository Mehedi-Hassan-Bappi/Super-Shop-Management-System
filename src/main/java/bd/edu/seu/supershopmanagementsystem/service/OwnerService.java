package bd.edu.seu.supershopmanagementsystem.service;

import bd.edu.seu.supershopmanagementsystem.Model.Owner;
import bd.edu.seu.supershopmanagementsystem.utility.ConnectionSingleton;

import java.sql.*;

public class OwnerService {
    public boolean register(Owner owner) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO owner VALUES ('"+ owner.getName() +"','" + owner.getNumber() +"','" + owner.getNid() +
                    "','" + owner.getGender() +"','" + owner.getPassword() +"','"+owner.getImage()+"');";
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed To save file");
        }
        return false;
    }

    public Owner login(String name, String password) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM owner WHERE name = '"+ name +"' AND password = '" + password + "';";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String OwnerName = resultSet.getString("name");
                String number = resultSet.getString("number");
                String nid = resultSet.getString("nid");
                String gender = resultSet.getString("gender");
                String image = resultSet.getString("image");


                return new Owner(OwnerName, number, nid, gender,image);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed To save file");
        }
        return null;
    }
}
