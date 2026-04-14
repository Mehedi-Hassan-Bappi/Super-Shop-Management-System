package bd.edu.seu.supershopmanagementsystem.owner;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import bd.edu.seu.supershopmanagementsystem.Model.Owner;
import bd.edu.seu.supershopmanagementsystem.service.OwnerService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class OwnerRegistrationController {
    @FXML
    public TextField ageField;

    @FXML
    public TextField emailField;

    @FXML
    public ToggleGroup genderSelection;

    @FXML
    public TextField nameField;

    @FXML
    public TextField nationalityField;

    @FXML
    public TextField nidField;

    @FXML
    public TextField numberField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Label saveLabel;
    @FXML
    public Label nameEmpty;
    @FXML
    public Label numberEmpty;
    @FXML
    public ImageView imageView;
    String filename;
    String destLocation = "E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\";
    @FXML
    public void save(){

        String name = nameField.getText();
       String number = numberField.getText();
       String nid  = nidField.getText();
       String age = ageField.getText();
       String password = passwordField.getText();
       String nationality = nationalityField.getText();
       String email = emailField.getText();
        String image = imageView.getImage().getUrl();

       RadioButton genderRadioButton = (RadioButton) genderSelection.getSelectedToggle();
       String gender = genderRadioButton.getText();

       System.out.println(name + " " + number  + " " + nid + " " + age + " " + password + " " + nationality + " " + email + " " + gender);
       Owner owner  = new Owner(name, number, password, email, nid, gender, nationality, age,filename);
       OwnerService ownerService = new OwnerService();
       boolean isRegistered = ownerService.register(owner);
       if(isRegistered){
           saveLabel.setText("Registration Successful");
       }
       else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setContentText("Failed to register");
           alert.showAndWait();
       }
       }
       public void uploadImage(){
           //Rename
           Random random = new Random();
           filename = random.nextInt(9999)+".jpg";
           //Move
           String fileLocation = destLocation+filename;
           Path destPath = Paths.get(fileLocation);

           FileChooser fileChooser = new FileChooser();
           File file = fileChooser.showOpenDialog(new Stage());
           Path imagePath = Paths.get(file.toURI());
           try {
               Files.copy(imagePath,destPath);
           } catch (IOException ex){
               ex.printStackTrace();
           }
           try{
               Image image = new Image(file.toURI().toURL().toExternalForm());
               imageView.setImage(image);
           } catch (MalformedURLException ex){
               ex.printStackTrace();
           }
       }
    public void goBackToOwnerLogin() {
        HelloApplication.changeScene("owner-login");
    }

}


