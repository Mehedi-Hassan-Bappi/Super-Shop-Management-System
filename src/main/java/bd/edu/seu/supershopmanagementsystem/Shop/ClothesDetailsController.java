package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.service.ClothesService;
import bd.edu.seu.supershopmanagementsystem.service.SellClothesService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClothesDetailsController implements Initializable {
    @FXML
    public Label nameLabel;
    @FXML
    public Label priceLabel;
    @FXML
    public Spinner<Integer> quantityField;
    @FXML Label typeLabel;
    @FXML
    public ImageView productImage;
    @FXML
    public Label totalAmountLabel;
    Clothes selectedClothes;
    @FXML
    public Label quantityLabel;
    int totalAmount;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, ClothesController.selectedClothes.getQuantity());
        spinnerValueFactory.setValue(1);
        quantityField.setValueFactory(spinnerValueFactory);
        selectedClothes = ClothesController.selectedClothes;
        if (selectedClothes != null) {
            nameLabel.setText(selectedClothes.getName());
            priceLabel.setText(selectedClothes.getPrice()+"");
            typeLabel.setText(selectedClothes.getType());
            quantityLabel.setText(selectedClothes.getQuantity()+"");
            productImage.setImage(new Image("file:///E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\"+selectedClothes.getImage()));

        }
        totalAmount = (int) (spinnerValueFactory.getValue() * selectedClothes.getPrice());
        totalAmountLabel.setText(Double.toString(totalAmount));
        spinnerValueFactory.valueProperty().addListener(new ChangeListener<Integer>(){
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                totalAmount = (int) (spinnerValueFactory.getValue() * selectedClothes.getPrice());
                totalAmountLabel.setText(Double.toString(totalAmount));
            }
        });
    }
    public void buy(){
        String type = typeLabel.getText();
        String name = nameLabel.getText();
        double price = Double.parseDouble(totalAmountLabel.getText());
        int quantity = quantityField.getValue();
        int profit = (int) (selectedClothes.getProfit() * quantity);
        System.out.println(price+" "+profit+" "+type+" "+name);
        Clothes clothes = new Clothes(type, name, quantity, price, profit);
        SellClothesService sellClothesService = new SellClothesService();
        boolean isSold = sellClothesService.buy(clothes);
        if(isSold){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setContentText("Purchase SuccessFul");
            alert.showAndWait();
            String updatedType =typeLabel.getText();
            int updatedQuantity = selectedClothes.getQuantity() - quantityField.getValue() ;
            Clothes clothes1 = new Clothes(updatedQuantity,updatedType);
            ClothesService clothesService = new ClothesService();
            clothesService.sold(clothes1);
        }
    }

}
