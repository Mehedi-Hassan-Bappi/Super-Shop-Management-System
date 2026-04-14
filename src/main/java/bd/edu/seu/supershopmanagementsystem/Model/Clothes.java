package bd.edu.seu.supershopmanagementsystem.Model;

public class Clothes {
    private String type;
    private String Name;
    private String Size;
    private String gender;
    private String image;
    private int quantity;
    private double price;
    private double profit;

    public Clothes(String type, String name, String size, String gender, String image, int quantity, double price, double profit) {
        this.type = type;
        Name = name;
        Size = size;
        this.gender = gender;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.profit = profit;
    }

    public Clothes(String type, String name, String size, int quantity, double price,String image) {
        this.type = type;
        Name = name;
        Size = size;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public Clothes(String type, String name, int quantity, double price, double profit) {
        this.type = type;
        Name = name;
        this.quantity = quantity;
        this.price = price;
        this.profit = profit;
    }

    public Clothes(int quantity,String type) {
        this.quantity = quantity;
        this.type = type;
    }

    public Clothes(double profit) {
        this.profit = profit;
    }

    public Clothes(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}
