package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
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

public class UpdateGoodsController implements Initializable {
    @FXML
    public TableColumn<Goods, String> dateColumn;

    @FXML
    public TableView<Goods> goodTable;

    @FXML
    public TableColumn<Goods, String> nameColumn;

    @FXML
    public TableColumn<Goods, Number> priceColumn;

    @FXML
    public TableColumn<Goods, Number> quantityColumn;

    @FXML
    public TableColumn<Goods, String> typeColumn;
    ObservableList<Goods> goodsObservableList;
    @FXML
    public TextField searchField;
    @FXML
    public Label typeLabel;
    @FXML
    public Label nameLabel;
    @FXML
    public Label quantityLabel;
    @FXML
    public Label dateLabel;
    @FXML
    public Label priceLabel;
    @FXML
    public Label profitLabel;
    @FXML
    public ImageView showImage;

    @FXML
    public ChoiceBox<String> choiceField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField quantityField;
    @FXML
    public TextField typeField;
    @FXML
    public ImageView imageView;

    ObservableList<String> typeobservableList;
public Goods selectedGoods;
    public void choiceBoxSelected(ActionEvent event){
        String selectedType = choiceField.getValue();
        GoodService goodService = new GoodService();
        List<Goods> goodsList = goodService.getGoods();
        List<Goods> filterList = goodsList.stream().filter(cell -> cell.getProductType().contentEquals(selectedType)).collect(Collectors.toList());
        goodsObservableList.clear();
        goodsObservableList.addAll(filterList);
    }
    public void searching(){
        String search = searchField.getText();
        GoodService goodService = new GoodService();
        List<Goods> goodsList = goodService.getGoods();
        List<Goods> filterList = goodsList.stream().filter(cell-> cell.getName().toLowerCase().contains(search)).collect(Collectors.toList());
        goodsObservableList.clear();
        goodsObservableList.addAll(filterList);
//        System.out.println(search);
    }
    public void onTableClicked(){
        selectedGoods = goodTable.getSelectionModel().getSelectedItem();
        nameLabel.setText(selectedGoods.getName());
        typeLabel.setText(selectedGoods.getProductType());
        priceLabel.setText((selectedGoods.getPrice())+"");
        profitLabel.setText(selectedGoods.getProfit()+"");
        quantityLabel.setText(selectedGoods.getQuantity()+"");
        dateLabel.setText(selectedGoods.getDate());
        showImage.setImage(new Image("file:///E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\"+selectedGoods.getImage()));

        typeField.setText(selectedGoods.getProductType());
        nameField.setText(selectedGoods.getName());
        priceField.setText(selectedGoods.getPrice()+"");
        quantityField.setText(selectedGoods.getQuantity()+"");
        imageView.setImage(new Image("file:///E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\"+selectedGoods.getImage()));
    }
    String destLocation = "E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\";
    String filename;
    public void newImage(){
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
        GoodService goodService = new GoodService();
        List<Goods> goodsList = goodService.getGoods();
        typeColumn.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getProductType()));
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        quantityColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()));
        priceColumn.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getPrice()));
        dateColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDate()));
        goodsObservableList = FXCollections.observableArrayList();
        goodTable.setItems(goodsObservableList);
        goodsObservableList.addAll(goodsList);
        typeobservableList = FXCollections.observableArrayList();
        for(Goods goods : goodsList){
            typeobservableList.addAll(goods.getProductType());
        }
        choiceField.setItems(typeobservableList);
        choiceField.setOnAction(this::choiceBoxSelected);
    }
    public void update(){
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        String type = typeField.getText();
        String image = imageView.getImage().getUrl();
        Goods goods = new Goods(type, name, price, quantity, filename);
        GoodService goodService = new GoodService();
        boolean updated =  goodService.updateGoods(goods);
        if(updated){
            goodsObservableList.clear();
            goodsObservableList.addAll(goodService.getGoods());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setContentText("Information updated Successfully");
            alert.showAndWait();
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Can't update information");
            alert.showAndWait();
        }
    }
    public void delete(){
        if(selectedGoods != null){
            GoodService goodService = new GoodService();
            goodService.delete(selectedGoods);
            goodsObservableList.clear();
            goodsObservableList.addAll(goodService.getGoods());
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
