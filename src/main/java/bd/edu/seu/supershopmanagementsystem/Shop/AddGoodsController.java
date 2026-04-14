package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import bd.edu.seu.supershopmanagementsystem.Model.Goods;
import bd.edu.seu.supershopmanagementsystem.service.GoodService;
import javafx.collections.FXCollections;
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

public class AddGoodsController implements Initializable {
    @FXML
    public ChoiceBox<String> productChoiceBox;
    @FXML
    public TextField nameField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField profitField;
    @FXML
    public Spinner<Integer> quantityField;
    @FXML
    public DatePicker dateField;
    @FXML
    public ImageView imageView;
    @FXML Label infoLabel;

    String filename;
    String destLocation = "E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\";
    @FXML
    public void save(){
        String name = nameField.getText();
        Double price = Double.parseDouble(priceField.getText());
        Double profit = Double.parseDouble(profitField.getText());
        String productType = productChoiceBox.getValue();
        int quantity = quantityField.getValue();
        String image = imageView.getImage().getUrl();
        String date = ""+dateField.getValue();
        Goods goods = new Goods(productType, name, price, profit, quantity, filename, date);
        GoodService goodService = new GoodService();
        boolean isAdded =  goodService.addGoods(goods);
        if(isAdded){
            infoLabel.setText("Product Added Successfully");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed To add");
            alert.showAndWait();
        }
    }
    public void goToHome(){
        HelloApplication.changeScene("first-scene");
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
            imageView.setImage(image);
        } catch (MalformedURLException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productChoiceBox.setItems(FXCollections.observableArrayList("Chips","Beverage","Cake","Biscuit","Chocolates"));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueFactory.setValue(1);
        quantityField.setValueFactory(valueFactory);
    }
}
