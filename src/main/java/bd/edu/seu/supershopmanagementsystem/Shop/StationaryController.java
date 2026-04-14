package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.Model.Stationary;
import bd.edu.seu.supershopmanagementsystem.service.ClothesService;
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

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StationaryController implements Initializable {
    @FXML
    public TableColumn<Stationary, String> nameColumn;

    @FXML
    public TableColumn<Stationary, Number> priceColumn;

    @FXML
    public TableColumn<Stationary, Number> quantityColumn;

    @FXML
    public TableView<Stationary> stationaryTable;

    @FXML
    public TableColumn<Stationary, String> typeColumn;
    ObservableList<Stationary> stationaryObservableList;
    @FXML
    public TextField searchField;
    @FXML
    public ChoiceBox<String> typeChoiceBox;
    ObservableList<String> typeObservableList;

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
        typeObservableList = FXCollections.observableArrayList();
        for(Stationary stationary : stationaryList){
            typeObservableList.addAll(stationary.getType());
        }
        typeChoiceBox.setItems(typeObservableList);
        typeChoiceBox.setOnAction(this::choiceBoxSelected);
    }
    @FXML
    public void choiceBoxSelected(ActionEvent event) {
        String selectedType = typeChoiceBox.getValue();
        StationaryService stationaryService = new StationaryService();
        List<Stationary> stationaryList = stationaryService.getStationary();
        List<Stationary> filterList = stationaryList.stream().filter(cell -> cell.getType().contentEquals(selectedType)).collect(Collectors.toList());
        stationaryObservableList.clear();
        stationaryObservableList.addAll(filterList);
    }
    @FXML
    public void searchStationary(KeyEvent event) {
        String search = searchField.getText();
        StationaryService stationaryService = new StationaryService();
        List<Stationary> stationaryList = stationaryService.getStationary();
        List<Stationary> filterList = stationaryList.stream().filter(cell -> cell.getName().toLowerCase().contains(search)).collect(Collectors.toList());
        stationaryObservableList.clear();
        stationaryObservableList.addAll(filterList);
    }
    public static Stationary selectedStationary;
    public void onTableClicked(){
        selectedStationary = stationaryTable.getSelectionModel().getSelectedItem();
        HelloApplication.openNewWindow("stationary-details");
    }
    public void backToShop(){
        HelloApplication.changeScene("shop");
    }

}