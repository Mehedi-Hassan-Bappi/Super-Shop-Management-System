package bd.edu.seu.supershopmanagementsystem.Model;

public class Owner {
    private String name;
    private String number;
    private String password;
    private String email;
    private String nid;
    private String gender;
    private String nationality;
    private String age;
    private String image;

    public Owner(String name, String number, String password, String email, String nid, String gender, String nationality,String age,String image) {
        this.name = name;
        this.number = number;
        this.password = password;
        this.email = email;
        this.nid = nid;
        this.gender = gender;
        this.nationality = nationality;
        this.age = age;
        this.image = image;
    }

    public Owner(String name, String number, String nid, String gender,String image) {
        this.name = name;
        this.number = number;
        this.nid = nid;
        this.gender = gender;
        this.image = image;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String mame) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
