package bd.edu.seu.supershopmanagementsystem.Model;

import javafx.scene.image.ImageView;

public class Employee {
    private String name;
    private String number;
    private String email;
    private String nid;
    private double salary;
    private String gender;
    private String nationality;
    private String password;
    private String image;

    public Employee(String name, String number, String email, String nid, double salary, String gender, String nationality, String password,String image) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.nid = nid;
        this.salary = salary;
        this.gender = gender;
        this.nationality = nationality;
        this.password = password;
        this.image = image;
    }

    public Employee(String name, String number, String email, String nid, Double salary, String nationality) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.nid = nid;
        this.salary = salary;
        this.nationality = nationality;

    }

    public Employee(String name, String number, String email, String nid, double salary, String nationality,String image) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.nid = nid;
        this.salary = salary;
        this.nationality = nationality;
        this.image = image;
    }

    public Employee(String name, String number, String email, double salary,String nid) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.salary = salary;
        this.nid = nid;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
