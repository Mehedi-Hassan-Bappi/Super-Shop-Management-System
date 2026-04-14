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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.xml.transform.dom.DOMResult;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GoodsController implements Initializable {
    @FXML
    public TableColumn<Goods, String> dateColumn;

    @FXML
    public TableView<Goods> goodTable;

    @FXML
    public TableColumn<Goods, String> nameColumn;

    @FXML
    public TableColumn<Goods,Number> priceColumn;

    @FXML
    public TableColumn<Goods,Number> quantityColumn;

    @FXML
    public TableColumn<Goods,String> typeColumn;
    ObservableList<Goods> goodsObservableList;
    @FXML
    public ChoiceBox<String> typeChoiceBox;
    ObservableList<String> typeobservableList;
    @FXML
    public TextField searchField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GoodService goodService = new GoodService();
        List<Goods> goodsList = goodService.getGoods();
        typeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getProductType()));
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
        typeChoiceBox.setItems(typeobservableList);
        typeChoiceBox.setOnAction(this::selectedItem);
    }
    @FXML
    public void selectedItem(ActionEvent event) {
        String selectedType = typeChoiceBox.getValue();
        GoodService goodService = new GoodService();
        List<Goods> goodsList = goodService.getGoods();
        List<Goods> filterList = goodsList.stream().filter(cell -> cell.getProductType().contentEquals(selectedType)).collect(Collectors.toList());
        goodsObservableList.clear();
        goodsObservableList.addAll(filterList);
    }
    @FXML
    void searchOnKeyType(KeyEvent event) {
        String search = searchField.getText();
        GoodService goodService = new GoodService();
        List<Goods> stationaryList = goodService.getGoods();
        List<Goods> filterList = stationaryList.stream().filter(cell -> cell.getName().toLowerCase().contains(search)).collect(Collectors.toList());
        goodsObservableList.clear();
        goodsObservableList.addAll(filterList);
    }
    public static Goods selectedGoods;
    public void onTableClicked(){
        selectedGoods = goodTable.getSelectionModel().getSelectedItem();
        HelloApplication.openNewWindow("goods-details");
    }
    public void backToShop(){
        HelloApplication.changeScene("shop");
    }

}
