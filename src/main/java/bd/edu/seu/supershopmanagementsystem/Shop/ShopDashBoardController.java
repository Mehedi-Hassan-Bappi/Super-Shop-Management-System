package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.Model.Employee;
import bd.edu.seu.supershopmanagementsystem.Model.Profit;
import bd.edu.seu.supershopmanagementsystem.Model.Transaction;
import bd.edu.seu.supershopmanagementsystem.service.*;
import bd.edu.seu.supershopmanagementsystem.utility.ConnectionSingleton;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class ShopDashBoardController implements Initializable {
    @FXML
    public TableColumn<Employee, String> emailColumn;

    @FXML
    public TableView<Employee> employeeTable;
    @FXML
    public TableColumn<Employee, String> nameColumn;

    @FXML
    public TableColumn<Employee,String > nationalityColumn;

    @FXML
    public TableColumn<Employee, String> nidColumn;

    @FXML
    public TableColumn<Employee, String> numberColumn;

    @FXML
    public TableColumn<Employee, Number> salaryColumn;
    @FXML
    public TextField numberField;
    @FXML
    public TextField salaryField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField nidField;
    @FXML
    public Label balanceLabel;
    @FXML
    public TextField nidField1;
    @FXML
    public TextField amountField;

    public void backToHome(){
        HelloApplication.changeScene("owner-page");
    }
    ObservableList<Employee> employeeObservableList;
    double profit;
//    double newProfit;
//    double sentAmount;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employeeList = employeeService.getEmployeeList();

        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        nidColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNid()));
        numberColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNumber()));
        emailColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));
        nationalityColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNationality()));
        salaryColumn.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getSalary()));
        employeeObservableList = FXCollections.observableArrayList();
        employeeTable.setItems(employeeObservableList);
        employeeObservableList.addAll(employeeList);
        profit = SellClothesService.clothesProfit() + SellGoodsService.groceryProfit() + SellStationaryService.stationaryProfit();
        Profit profit1 = new Profit(profit);
        ProfitService profitService = new ProfitService();
        profitService.collectProfit(profit1);
        balanceLabel.setText(profit1.getProfit()+"");
    }
    Employee selectedEmployee;
    public void selectTableEmployee(){
        selectedEmployee =  employeeTable.getSelectionModel().getSelectedItem();
        numberField.setText(selectedEmployee.getNumber());
        nameField.setText(selectedEmployee.getName());
        emailField.setText(selectedEmployee.getEmail());
        salaryField.setText(selectedEmployee.getSalary()+"");
        nidField.setText(selectedEmployee.getNid());
    }
    public void delete(){
        if(selectedEmployee != null){
            EmployeeService employeeService = new EmployeeService();
            employeeService.delete(selectedEmployee);
            employeeObservableList.clear();
            employeeObservableList.addAll(employeeService.getEmployeeList());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete successful");
            alert.setContentText("Employee Deleted");
            alert.showAndWait();
        } else{

        }
    }
    public void update(){
        String name = nameField.getText();
        String number = numberField.getText();
        String email = emailField.getText();
        String nid = nidField.getText();
        double salary = Double.parseDouble(salaryField.getText());
        Employee employee = new Employee(name,number,email,salary,nid);
        EmployeeService employeeService = new EmployeeService();
        boolean updated = employeeService.update(employee);
        if(updated){
            employeeObservableList.clear();
            employeeObservableList.addAll(employeeService.getEmployeeList());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update successful");
            alert.setContentText("Employee Deleted");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("X");
            alert.showAndWait();
        }
    }
    double newProfit;
    public void paySalary(){
        String nid = nidField1.getText();
        double amount = Double.parseDouble(amountField.getText());
        Transaction transaction = new Transaction(nid,amount);
        TransactionService transactionService = new TransactionService();
        boolean isSend = transactionService.insert(transaction);
        if(isSend){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setContentText("Amount sent successfully");
            alert.showAndWait();
            newProfit = profit - amount;
            Profit updatedProfit = new Profit(newProfit);
            ProfitService profitService = new ProfitService();
            profitService.updateProfit(updatedProfit);
        }
    }
}
