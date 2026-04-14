package bd.edu.seu.supershopmanagementsystem.Shop;

import bd.edu.seu.supershopmanagementsystem.HelloApplication;

public class ShopController {
    public void goToGrocery(){
        HelloApplication.changeScene("goods");
    }
    public void goToStationary(){
        HelloApplication.changeScene("stationary");
    }
    public void goToClothes(){
        HelloApplication.changeScene("clothes");
    }
    public void goBackToHome(){
        HelloApplication.changeScene("first-scene");
    }
}
