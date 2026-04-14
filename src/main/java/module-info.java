module bd.edu.seu.supershopmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens bd.edu.seu.supershopmanagementsystem to javafx.fxml;
    exports bd.edu.seu.supershopmanagementsystem;
    opens bd.edu.seu.supershopmanagementsystem.owner to javafx.fxml;
    opens bd.edu.seu.supershopmanagementsystem.Employee to javafx.fxml;
    exports bd.edu.seu.supershopmanagementsystem.Shop;
    opens bd.edu.seu.supershopmanagementsystem.Shop to javafx.fxml;
    exports bd.edu.seu.supershopmanagementsystem.Employee;
    exports bd.edu.seu.supershopmanagementsystem.owner;
    exports bd.edu.seu.supershopmanagementsystem.Model;
    opens bd.edu.seu.supershopmanagementsystem.Model to javafx.fxml;
    exports bd.edu.seu.supershopmanagementsystem.service;
    opens bd.edu.seu.supershopmanagementsystem.service to javafx.fxml;
    exports bd.edu.seu.supershopmanagementsystem.utility;
    opens bd.edu.seu.supershopmanagementsystem.utility to javafx.fxml;
}