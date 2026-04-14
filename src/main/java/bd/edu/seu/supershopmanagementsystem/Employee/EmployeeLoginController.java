package bd.edu.seu.supershopmanagementsystem.Employee;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import bd.edu.seu.supershopmanagementsystem.Model.Employee;
import bd.edu.seu.supershopmanagementsystem.service.EmployeeService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EmployeeLoginController {
    @FXML
    public TextField nameField;
    @FXML
    public PasswordField passwordField;


    public void login(){
        String name = nameField.getText();
        String password = passwordField.getText();
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.login(name,password);
        if(employee != null){
            HelloApplication.loggedEmployee = employee;
            HelloApplication.changeScene("employee-page");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("User name or password Incorrect");
            alert.showAndWait();
        }
    }
    public void goToEmployeeRegistration(){
        HelloApplication.changeScene("employee-registration");
    }
    public void backToFirstScene(){HelloApplication.changeScene("first-scene");}
}
