package bd.edu.seu.supershopmanagementsystem;

public class FirstSceneController {
    public void goToOwnerLogin(){
        HelloApplication.changeScene("owner-login");
    }
    public void changeToEmployeeLogin(){
        HelloApplication.changeScene("employee-login");
    }
    public void goToShop(){
            HelloApplication.changeScene("shop");
    }
}
