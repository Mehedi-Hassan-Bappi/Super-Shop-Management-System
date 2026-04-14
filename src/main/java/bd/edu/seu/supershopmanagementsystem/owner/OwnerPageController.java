package bd.edu.seu.supershopmanagementsystem.owner;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OwnerPageController implements Initializable {
    @FXML
    public Label nameLabel;
    @FXML
    public Label nidLabel;
    @FXML
    public Label numberLabel;
    @FXML
    public ImageView imageView;

    public void backToHome(){
        HelloApplication.changeScene("first-scene");
    }
    public void goToShop(){
        HelloApplication.changeScene("update-goods");
    }
    public void updateStationary(){
        HelloApplication.changeScene("update-stationary");
    }
    public void updateClothes(){
        HelloApplication.changeScene("update-clothes");
    }
    public void goToAddProductPage(){
        HelloApplication.changeScene("section");
    }
    public void goToEmployeeInformation(){
        HelloApplication.changeScene("employee-information");
    }
    public void goToDashBoard(){
        HelloApplication.changeScene("shop-dashboard");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(HelloApplication.loggedOwner.getName());
        numberLabel.setText(HelloApplication.loggedOwner.getNumber());
        nidLabel.setText(HelloApplication.loggedOwner.getNid());
        try{
            FileInputStream inputstream = new FileInputStream("E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\"+HelloApplication.loggedOwner.getImage());
            Image img = new Image(inputstream);
            imageView.setImage(img);
        } catch (IOException ex){

        }

    }
}
