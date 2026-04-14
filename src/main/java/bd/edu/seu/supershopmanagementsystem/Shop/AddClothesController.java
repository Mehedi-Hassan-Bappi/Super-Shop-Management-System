package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.service.ClothesService;
import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.ResourceBundle;

public class AddClothesController implements Initializable {
    @FXML
    public ToggleGroup genderToggleGroup;



    @FXML
    public TextField nameField;

    @FXML
    public TextField priceField;

    @FXML
    public TextField profitFiled;

    @FXML
    public Spinner<Integer> quantityField;

    @FXML
    public ChoiceBox<String> sizeBox;

    @FXML
    public ChoiceBox<String> typeBox;
    @FXML
    public Label saveInfo;
    @FXML
    public ImageView addImage;
    String filename;
    String destLocation = "E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\";
    @FXML
    public void save() {
        String type = typeBox.getValue();
        String name = nameField.getText();
        String size = sizeBox.getValue();
        double price = Double.parseDouble(priceField.getText());
        double profit = Double.parseDouble(profitFiled.getText());
        int quantity = quantityField.getValue();
        RadioButton genderRadioButton =(RadioButton) genderToggleGroup.getSelectedToggle();
        String gender = genderRadioButton.getText();
        String image = addImage.getImage().getUrl();
        Clothes clothes = new Clothes(type,name, size, gender, filename, quantity, price, profit);
        ClothesService clothesService = new ClothesService();
        boolean isAdded = clothesService.addClothes(clothes);
        if(isAdded){
            saveInfo.setText("Product Added Successfully");
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to save");
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeBox.setItems(FXCollections.observableArrayList("Sweater","Hoodie","Pant","T-Shirt"));
        sizeBox.setItems(FXCollections.observableArrayList("Small","Medium","Large"));
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        spinnerValueFactory.setValue(1);
        quantityField.setValueFactory(spinnerValueFactory);
    }
    public void uploadPhoto(){
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
            addImage.setImage(image);
        } catch (MalformedURLException ex){
            ex.printStackTrace();
        }
    }
}
