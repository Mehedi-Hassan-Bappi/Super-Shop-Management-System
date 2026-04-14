package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.Model.Goods;
import bd.edu.seu.supershopmanagementsystem.service.ClothesService;
import bd.edu.seu.supershopmanagementsystem.service.GoodService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UpdateClothesController implements Initializable {
    @FXML
    public TableView<Clothes> clotheTable;

    @FXML
    public TableColumn<Clothes, String> nameColumn;

    @FXML
    public TableColumn<Clothes, Number> priceColumn;

    @FXML
    public TableColumn<Clothes, Number> quantityColumn;

    @FXML
    public TableColumn<Clothes, String> sizeColumn;

    @FXML
    public TableColumn<Clothes, String> typeColumn;
    @FXML
    public TextField nameField;
    @FXML
    public TextField typeField;
    @FXML
    public TextField sizeField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField quantityField;
    @FXML
    public ChoiceBox<String> typeChoiceBox;
    @FXML
    public Label nameLabel;
    @FXML
    public Label typeLabel;
    @FXML
    public Label priceLabel;
    @FXML
    public Label profitLabel;
    @FXML
    public Label sizeLabel;
    @FXML
    public Label quantityLabel;
    @FXML
    public ImageView showImage;
    @FXML
    public ImageView updateImage;
    ObservableList<String> typeObservableList;

    ObservableList<Clothes> clothesObservableList;
    public void selectedChoiceBox(ActionEvent event){
        String selectedType = typeChoiceBox.getValue();
        ClothesService clothesService = new ClothesService();
        List<Clothes> goodsList = clothesService.geClothes();
        List<Clothes> filterList = goodsList.stream().filter(cell -> cell.getType().contentEquals(selectedType)).collect(Collectors.toList());
        clothesObservableList.clear();
        clothesObservableList.addAll(filterList);
    }
    public Clothes selectedClothes;
    public void onTableClicked(MouseEvent event){
        selectedClothes = clotheTable.getSelectionModel().getSelectedItem();
        nameField.setText(selectedClothes.getName());
        typeField.setText(selectedClothes.getType());
        sizeField.setText(selectedClothes.getSize());
        quantityField.setText(selectedClothes.getQuantity()+"");
        priceField.setText(selectedClothes.getPrice()+"");
        updateImage.setImage(new Image("file:///E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\"+selectedClothes.getImage()));


        nameLabel.setText(selectedClothes.getName());
        typeLabel.setText(selectedClothes.getType());
        priceLabel.setText(selectedClothes.getPrice()+"");
        profitLabel.setText(selectedClothes.getProfit()+"");
        sizeLabel.setText(selectedClothes.getSize());
        quantityLabel.setText(selectedClothes.getQuantity()+"");
        showImage.setImage(new Image("file:///E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\"+selectedClothes.getImage()));
    }
    String destLocation = "E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\";
    String filename;
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
            updateImage.setImage(image);
        } catch (MalformedURLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //table view
        ClothesService clothesService = new ClothesService();
        List<Clothes> clothesList = clothesService.geClothes();
        typeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getType()));
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        sizeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSize()));
        quantityColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()));
        priceColumn.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getPrice()));
        clothesObservableList = FXCollections.observableArrayList();
        clotheTable.setItems(clothesObservableList);
        clothesObservableList.addAll(clothesList);
        //choice box
        typeObservableList = FXCollections.observableArrayList();
        for(Clothes clothes : clothesList){
            typeObservableList.addAll(clothes.getType());
        }
        typeChoiceBox.setItems(typeObservableList);
        typeChoiceBox.setOnAction(this::selectedChoiceBox);
    }

    public void update(){
        String name = nameField.getText();
        String type = typeField.getText();
        String size = sizeField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        String image = updateImage.getImage().getUrl();

        Clothes clothes = new Clothes(type, name, size, quantity, price,filename);
        ClothesService clothesService = new ClothesService();
        boolean updated = clothesService.updateClothes(clothes);
        if(updated){
            clothesObservableList.clear();
            clothesObservableList.addAll(clothesService.geClothes());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setContentText("Information updated Successfully");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Can't update information");
            alert.showAndWait();
        }
    }
    public void delete(){
        if(selectedClothes != null){
            ClothesService clothesService = new ClothesService();
            clothesService.delete(selectedClothes);
            clothesObservableList.clear();
            clothesObservableList.addAll(clothesService.geClothes());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DELETE");
            alert.setContentText("Delete Successfully");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("select a product to delete");
            alert.showAndWait();
        }
    }
    public void backToFirstScene(){
        HelloApplication.changeScene("first-scene");
    }
}
