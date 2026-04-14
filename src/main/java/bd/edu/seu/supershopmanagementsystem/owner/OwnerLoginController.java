package bd.edu.seu.supershopmanagementsystem.owner;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import bd.edu.seu.supershopmanagementsystem.Model.Owner;
import bd.edu.seu.supershopmanagementsystem.service.OwnerService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class OwnerLoginController {
    @FXML
    public TextField nameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Label emptyName;
    @FXML
    public Label emptyPassword;

    public void login(){
        if(nameField.getText().trim().isEmpty() && passwordField.getText().trim().isEmpty()){
            emptyName.setText("Name Required");
            emptyPassword.setText("Password required");
        } else if (nameField.getText().trim().isEmpty()) {
            emptyName.setText("Name Required");
        } else if (passwordField.getText().trim().isEmpty()) {
            emptyPassword.setText("Password required");
        } else {
            String name = nameField.getText();
            String password = passwordField.getText();

            OwnerService ownerService = new OwnerService();
            Owner owner = ownerService.login(name, password);
            if (owner != null) {
                HelloApplication.loggedOwner= owner;
                HelloApplication.changeScene("owner-page");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(" User name or password incorrect ");
                alert.showAndWait();
            }
        }

    }
    public void backToFirstScene(){
        HelloApplication.changeScene("first-scene");
    }
    public void goToRegistration(){
        HelloApplication.changeScene("owner-registration");
    }

}
