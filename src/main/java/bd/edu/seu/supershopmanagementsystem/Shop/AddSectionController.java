package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;

public class AddSectionController {
    public void goToAddGood(){
        HelloApplication.changeScene("add-goods");
    }
    public void goToAddCloth(){
        HelloApplication.changeScene("add-clothes");
    }
    public void goToAddStationary(){
        HelloApplication.changeScene("add-stationary");
    }
    public void goToAddGrocery(){
        HelloApplication.changeScene("add-grocery");
    }
}
