package bd.edu.seu.supershopmanagementsystem.Model;

public class Transaction {
    private String nid;
    private double amount;

    public Transaction(String nid, double amount) {
        this.nid = nid;
        this.amount = amount;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
