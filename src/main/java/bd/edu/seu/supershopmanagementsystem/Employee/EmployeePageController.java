package bd.edu.seu.supershopmanagementsystem.Employee;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeePageController implements Initializable {
    public void back(){
        HelloApplication.changeScene("first-scene");
    }
    public void goToAddProduct(){
        HelloApplication.changeScene("section");
    }
    public void goToShop(){
        HelloApplication.changeScene("update-goods");
    }
    @FXML
    public void updateClothes(MouseEvent event) {
        HelloApplication.changeScene("update-clothes");
    }

    @FXML
    void updateStationary(MouseEvent event) {
        HelloApplication.changeScene("update-stationary");
    }
    @FXML
    public Label nameLabel;
    @FXML
    public Label salaryLabel;
    @FXML
    public Label numberLabel;
    @FXML
    public Label nidLabel;
    @FXML
    public ImageView imageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(HelloApplication.loggedEmployee.getName());
        salaryLabel.setText(HelloApplication.loggedEmployee.getSalary()+"");
        numberLabel.setText(HelloApplication.loggedEmployee.getNumber());
        nidLabel.setText(HelloApplication.loggedEmployee.getNid());
        try{
            FileInputStream inputstream = new FileInputStream("E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\"+HelloApplication.loggedEmployee.getImage());
            Image img = new Image(inputstream);
            imageView.setImage(img);
        } catch (IOException ex){

        }
    }
}
