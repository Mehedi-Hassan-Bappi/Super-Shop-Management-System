package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;
import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.Model.Employee;
import bd.edu.seu.supershopmanagementsystem.service.ClothesService;
import bd.edu.seu.supershopmanagementsystem.service.EmployeeService;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ClothesController implements Initializable {
    @FXML
    public  TableView<Clothes> clotheTable;

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
    public TextField searchField;
    @FXML
    public ChoiceBox<String> typeChoiceBox;
    ObservableList<Clothes> clothesObservableList;
    ObservableList<String> typeobservableList;

    public static  Clothes clothes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        typeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getType()));
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        sizeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSize()));
        quantityColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()));
        priceColumn.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getPrice()));
        clothesObservableList = FXCollections.observableArrayList();
        ClothesService clothesService = new ClothesService();
        List<Clothes> clothesList = clothesService.geClothes();
        clotheTable.setItems(clothesObservableList);
        clothesObservableList.addAll(clothesList);

        typeobservableList = FXCollections.observableArrayList();
        for(Clothes clothes : clothesList){
            typeobservableList.addAll(clothes.getType());
        }
        typeChoiceBox.setItems(typeobservableList);
        typeChoiceBox.setOnAction(this::choiceBoxSelected);
    }
    @FXML
    void choiceBoxSelected(ActionEvent event) {
        String selectedType = typeChoiceBox.getValue();
        ClothesService clothesService = new ClothesService();
        List<Clothes> clothesList = clothesService.geClothes();
        List<Clothes> filterList = clothesList.stream().filter(cell -> cell.getType().contains(selectedType)).collect(Collectors.toList());
        clothesObservableList.clear();
        clothesObservableList.addAll(filterList);
    }
    @FXML
    public void startSearching(KeyEvent event) {
        String search = searchField.getText();
        ClothesService clothesService = new ClothesService();
        List<Clothes> clothesList = clothesService.geClothes();

        List<Clothes> filterList = clothesList.stream().filter(cell -> cell.getName().toLowerCase().contains(search)||
                cell.getSize().toLowerCase().contains(search)).collect(Collectors.toList());
        clothesObservableList.clear();
        clothesObservableList.addAll(filterList);
    }
    public static Clothes selectedClothes;
    @FXML
    public void selectedItem(MouseEvent event) {
        selectedClothes = clotheTable.getSelectionModel().getSelectedItem();
        HelloApplication.openNewWindow("clothes-details");
    }
    public void backToShop(){
        HelloApplication.changeScene("shop");
    }
}
