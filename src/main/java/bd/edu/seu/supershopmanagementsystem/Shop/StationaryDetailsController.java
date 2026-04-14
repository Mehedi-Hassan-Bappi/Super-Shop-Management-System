package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.Model.Stationary;
import bd.edu.seu.supershopmanagementsystem.service.SellStationaryService;
import bd.edu.seu.supershopmanagementsystem.service.StationaryService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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

public class StationaryDetailsController implements Initializable {
    @FXML
    public ImageView imageView;

    @FXML
    public Label nameLabel;

    @FXML
    public Label priceLabel;
    @FXML
    public Label typeLabel;

    @FXML
    public Label quantityLabel;

    @FXML
    public Spinner<Integer> setQuantity;

    @FXML
    public Label totalAmountLabel;
    Stationary selectedStationary;
    int totalAmount;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        spinnerValueFactory.setValue(1);
        setQuantity.setValueFactory(spinnerValueFactory);
        selectedStationary = StationaryController.selectedStationary;
        if(selectedStationary != null){
            nameLabel.setText(selectedStationary.getName());
            typeLabel.setText(selectedStationary.getType());
            quantityLabel.setText(selectedStationary.getQuantity()+"");
            priceLabel.setText(selectedStationary.getPrice()+"");
            imageView.setImage(new Image("file:///E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\"+selectedStationary.getImage()));
        }
        totalAmount = (int) (spinnerValueFactory.getValue() * selectedStationary.getPrice());
        totalAmountLabel.setText(Double.toString(totalAmount));
        spinnerValueFactory.valueProperty().addListener(new ChangeListener<Integer>(){
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                totalAmount = (int) (spinnerValueFactory.getValue() * selectedStationary.getPrice());
                totalAmountLabel.setText(Double.toString(totalAmount));
            }
        });
    }
    @FXML
    public void buy(ActionEvent event) {
        String type = typeLabel.getText();
        String name = nameLabel.getText();
        double price = Double.parseDouble(totalAmountLabel.getText());
        int quantity = setQuantity.getValue();
        int profit = (int) (selectedStationary.getProfit() * quantity);
        System.out.println(type+" "+name+" "+price+" "+quantity+" "+profit);
        Stationary stationary = new Stationary(type,name,price,profit,quantity);
        SellStationaryService sellStationaryService = new SellStationaryService();
        boolean isSold = sellStationaryService.buy(stationary);
        if(isSold){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setContentText("Purchase SuccessFul");
            alert.showAndWait();
            String updatedType =typeLabel.getText();
            int updatedQuantity = selectedStationary.getQuantity() - setQuantity.getValue() ;
            Stationary stationary1 = new Stationary(updatedType,updatedQuantity);
            StationaryService stationaryService = new StationaryService();
            stationaryService.sold(stationary1);
        }
    }


}
