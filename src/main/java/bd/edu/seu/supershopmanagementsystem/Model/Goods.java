package bd.edu.seu.supershopmanagementsystem.Model;

public class Goods {
    private String productType;
    private String name;
    private double price;
    private double profit;
    private int quantity;
    private String image;
    private String date;
    public Goods(String productType, String name, double price, double profit, int quantity, String image, String date) {
        this.productType = productType;
        this.name = name;
        this.price = price;
        this.profit = profit;
        this.quantity = quantity;
        this.image = image;
        this.date = date;
    }

    public Goods(String productType, String name, double price, int quantity, String image) {
        this.productType = productType;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public Goods(String productType, String name, double price, double profit, int quantity) {
        this.productType = productType;
        this.name = name;
        this.price = price;
        this.profit = profit;
        this.quantity = quantity;
    }

    public Goods(String productType, int quantity) {
        this.productType = productType;
        this.quantity = quantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
