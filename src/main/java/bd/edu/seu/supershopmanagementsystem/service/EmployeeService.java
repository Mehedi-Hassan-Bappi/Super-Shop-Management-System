package bd.edu.seu.supershopmanagementsystem.service;

import bd.edu.seu.supershopmanagementsystem.Model.Employee;
import bd.edu.seu.supershopmanagementsystem.utility.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeService {
    public boolean register (Employee employee){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/super_shop","root","samid999+");
            Statement statement = connection.createStatement();
            String query = "INSERT INTO employee VALUE('"+ employee.getNid() +"','"+ employee.getName() +"','"+ employee.getNumber() +"','"+ employee.getEmail() +
                    "','" + employee.getNationality() + "','"+ employee.getSalary()+"','" + employee.getPassword() +"','"+employee.getImage()+"' );";
            statement.execute(query);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Failed to save");
        }
        return false;
    }

    public Employee login(String name, String password){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/super_shop","root","samid999+");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM employee WHERE name = '"+ name +"' AND password = '" + password + "';";
            ResultSet resultSet = statement.executeQuery(query);
          while(resultSet.next()){
              String nid = resultSet.getString("nid");//nid,number,email,nationality,salary
              String employeeName = resultSet.getString("name");
              String number = resultSet.getString("number");
              String email = resultSet.getString("email");
              String nationality = resultSet.getString("nationality");
              Double salary = resultSet.getDouble("salary");
              String image = resultSet.getString("image");
              return new Employee(employeeName, number, email, nid, salary, nationality,image);
          }
        } catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Failed To save File");
        }
        return null;
    }
    public List<Employee> getEmployeeList(){
        List<Employee> employeeList = new ArrayList<>();
        try{
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM employee";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String nid =resultSet.getString("nid");
                String UserName =resultSet.getString("name");
                String number =resultSet.getString("number");
                String email =resultSet.getString("email");
                String nationality =resultSet.getString("nationality");
                double salary =resultSet.getDouble("salary");
                Employee employee = new Employee(UserName, number, email, nid, salary, nationality);
                employeeList.add(employee);

            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return employeeList;
    }
    public boolean delete (Employee employee){
        try{
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM employee WHERE nid = '"+employee.getNid()+"';";
            statement.execute(query);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Failed to save");
        }
        return false;
    }
    public boolean update (Employee employee){
        try{
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE employee SET name ='"+ employee.getName()+"',number ='"+employee.getNumber()+"',salary ='"+employee.getSalary()
                    +"',email ='"+employee.getEmail()+"'WHERE nid = '"+employee.getNid()+"';";

            statement.execute(query);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Failed to save");
        }
        return false;
    }
}
