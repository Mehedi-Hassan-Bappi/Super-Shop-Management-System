package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.Model.Goods;
import bd.edu.seu.supershopmanagementsystem.service.GoodService;
import bd.edu.seu.supershopmanagementsystem.service.SellGoodsService;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GoodsDetailsController implements Initializable {
    @FXML
    public Label nameLabel;

    @FXML
    public Label priceLabel;

    @FXML
    public Label quantityLabel;

    @FXML
    public Spinner<Integer> setQuantity;

    @FXML
    public ImageView showImage;

    @FXML
    public Label totalAmountLabel;

    @FXML
    public Label typeLabel;
    @FXML
    public Label dateLabel;

    Goods selectedGoods;
    int totalAmount;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        spinnerValueFactory.setValue(1);
        setQuantity.setValueFactory(spinnerValueFactory);
        selectedGoods = GoodsController.selectedGoods;
        if(selectedGoods != null){
            typeLabel.setText(selectedGoods.getProductType());
            nameLabel.setText(selectedGoods.getName());
            priceLabel.setText(selectedGoods.getPrice()+"");
            quantityLabel.setText(selectedGoods.getQuantity()+"");
            dateLabel.setText(selectedGoods.getDate());
            showImage.setImage(new Image("file:///E:\\java281.10 premium\\Super-Shop-Management-System\\shopImage\\"+selectedGoods.getImage()));
        }
        totalAmount =(int)(spinnerValueFactory.getValue() * selectedGoods.getPrice());
        totalAmountLabel.setText(Double.toString(totalAmount));
        spinnerValueFactory.valueProperty().addListener(new ChangeListener<Integer>(){
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                totalAmount = (int) (spinnerValueFactory.getValue() * selectedGoods.getPrice());
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
        int profit = (int) (selectedGoods.getProfit() * quantity);
        System.out.println(type+" "+name+" "+price+" "+quantity+" "+profit);
        Goods goods = new Goods(type, name, price, profit, quantity);
        SellGoodsService sellGoodsService = new SellGoodsService();
        boolean isSold = sellGoodsService.buy(goods);
        if(isSold){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setContentText("Purchase SuccessFul");
            alert.showAndWait();
            String updatedType = typeLabel.getText();
            int updatedQuantity = selectedGoods.getQuantity() - setQuantity.getValue();
            Goods goods1 = new Goods(updatedType,updatedQuantity);
            GoodService goodService = new GoodService();
            goodService.sold(goods1);
        }
    }

}
