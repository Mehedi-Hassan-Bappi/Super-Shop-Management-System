package bd.edu.seu.supershopmanagementsystem.Model;

public class Stationary {
    private String type;
    private String name;
    private double price;
    private double profit;
    private int quantity;
    private String image;

    public Stationary(String type, String name, double price, double profit, int quantity, String image) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.profit = profit;
        this.quantity = quantity;
        this.image = image;
    }

    public Stationary(String type, String name, double price, int quantity,String image) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public Stationary(String type, String name, double price, double profit, int quantity) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.profit = profit;
        this.quantity = quantity;
    }

    public Stationary(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
