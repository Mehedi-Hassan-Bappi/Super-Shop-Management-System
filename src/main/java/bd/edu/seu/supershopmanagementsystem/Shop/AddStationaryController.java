package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.Model.Stationary;
import bd.edu.seu.supershopmanagementsystem.service.StationaryService;
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

public class AddStationaryController implements Initializable {
    @FXML
    public ImageView imageView;

    @FXML
    public TextField nameField;

    @FXML
    public TextField priceFiled;

    @FXML
    public TextField profitField;

    @FXML
    public Spinner<Integer> quantityField;

    @FXML
    public ChoiceBox<String> typeBox;
    @FXML
    public Label saveInfo;
    String filename;
    String destLocation = "E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\";
    @FXML
    public void save(ActionEvent event) {
        String name = nameField.getText();
        String type = typeBox.getValue();
        int quantity = quantityField.getValue();
        Double price = Double.parseDouble(priceFiled.getText());
        Double profit = Double.parseDouble(profitField.getText());
        String image = imageView.getImage().getUrl();
        Stationary stationary = new Stationary(type,name, price, profit, quantity, filename);
        StationaryService stationaryService = new StationaryService();
        boolean isAdded = stationaryService.addStationary(stationary);
        if(isAdded){
            saveInfo.setText("Product Added Successfully");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to save");
            alert.showAndWait();
        }
    }

    @FXML
    public void uploadPhoto(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeBox.setItems(FXCollections.observableArrayList("Pencil","Pen","Marker","Duster","Note Book","Eraser","Sharpener"));
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        spinnerValueFactory.setValue(1);
        quantityField.setValueFactory(spinnerValueFactory);
    }
}
