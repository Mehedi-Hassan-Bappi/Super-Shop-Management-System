package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import bd.edu.seu.supershopmanagementsystem.Model.Goods;
import bd.edu.seu.supershopmanagementsystem.Model.Stationary;
import bd.edu.seu.supershopmanagementsystem.service.GoodService;
import bd.edu.seu.supershopmanagementsystem.service.StationaryService;
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
import javafx.scene.input.InputMethodHighlight;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.FillRule;
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

public class UpdateStationaryController implements Initializable {
    @FXML
    public TableColumn<Stationary, String> nameColumn;
    @FXML
    public TableColumn<Stationary, Number> priceColumn;
    @FXML
    public TableColumn<Stationary, Number> quantityColumn;
    @FXML
    public TableColumn<Stationary, String> typeColumn;
    @FXML
    public TableView<Stationary> stationaryTable;
    ObservableList<Stationary> stationaryObservableList;

    @FXML
    public TextField nameField;

    @FXML
    public Label nameLabel;

    @FXML
    public TextField priceField;

    @FXML
    public Label priceLabel;

    @FXML
    public Label profitLabel;


    @FXML
    public TextField quantityField;

    @FXML
    public Label quantityLabel;

    @FXML
    public ImageView showImage;

    @FXML
    public Label typeLabel;

    @FXML
    public TextField typefield;

    @FXML
    public ImageView updateImage;
    @FXML
    public TextField searchField;
    @FXML
    public void delete(ActionEvent event) {
        if(selectedStationary != null){
            StationaryService stationaryService = new StationaryService();
            stationaryService.delete(selectedStationary);
            stationaryObservableList.clear();
            stationaryObservableList.addAll(stationaryService.getStationary());
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
    Stationary selectedStationary;
    @FXML
    public void onTableClicked(MouseEvent event) {
        selectedStationary = stationaryTable.getSelectionModel().getSelectedItem();
        nameLabel.setText(selectedStationary.getName());
        typeLabel.setText(selectedStationary.getType());
        profitLabel.setText(selectedStationary.getProfit()+"");
        priceLabel.setText(selectedStationary.getPrice()+"");
        quantityLabel.setText(selectedStationary.getQuantity()+"");
        showImage.setImage(new Image("file:///E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\"+selectedStationary.getImage()));
        if(selectedStationary.getQuantity() < 10){
            quantityLabel.setTextFill(Paint.valueOf("red"));
        }else{
            quantityLabel.setTextFill(Paint.valueOf("black"));
        }
        typefield.setText(selectedStationary.getType());
        nameField.setText(selectedStationary.getName());
        quantityField.setText(selectedStationary.getQuantity()+"");
        priceField.setText(selectedStationary.getPrice()+"");
        updateImage.setImage(new Image("file:///E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\"+selectedStationary.getImage()));
    }
    String destLocation = "E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\";
    String filename;
    @FXML
    public void setImage(ActionEvent event) {
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

    @FXML
    public void update(ActionEvent event) {
        String type = typefield.getText();
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        String image = updateImage.getImage().getUrl();

        Stationary stationary = new Stationary(type,name,price,quantity,filename);
        StationaryService stationaryService = new StationaryService();
        boolean updated = stationaryService.update(stationary);
        if(updated){
            stationaryObservableList.clear();
            stationaryObservableList.addAll(stationaryService.getStationary());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setContentText("Information updated Successfully");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Can't update information");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stationaryObservableList = FXCollections.observableArrayList();
        StationaryService stationaryService = new StationaryService();
        typeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getType()));
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        quantityColumn.setCellValueFactory((cell -> new SimpleIntegerProperty(cell.getValue().getQuantity())));
        priceColumn.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getPrice()));
        List<Stationary> stationaryList = stationaryService.getStationary();
        stationaryTable.setItems(stationaryObservableList);
        stationaryObservableList.addAll(stationaryList);
    }
    public void searching(){
        String search = searchField.getText();
        StationaryService stationaryService = new StationaryService();
        List<Stationary> stationaryList = stationaryService.getStationary();
        List<Stationary> filterList = stationaryList.stream().filter(cell-> cell.getName().toLowerCase().contains(search)).collect(Collectors.toList());
        stationaryObservableList.clear();
        stationaryObservableList.addAll(filterList);
        System.out.println(search);
    }
    public void backToFirstScene(){
        HelloApplication.changeScene("first-scene");
    }
}
