package bd.edu.seu.supershopmanagementsystem;
import bd.edu.seu.supershopmanagementsystem.Model.Clothes;
import bd.edu.seu.supershopmanagementsystem.Model.Employee;
import bd.edu.seu.supershopmanagementsystem.Model.Owner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Owner loggedOwner;
    public static Clothes enteredClothes;
    public static Employee loggedEmployee;
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("first-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static void changeScene(String fileName){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 700);
            stage.setScene(scene);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static void openNewWindow(String fileName){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName +".fxml"));
            Parent root1 =fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("second-window");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}