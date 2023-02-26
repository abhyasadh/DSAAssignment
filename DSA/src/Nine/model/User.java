package Nine.model;

public class User {
    private int userId;
    private String fName;
    private String lName;
    private String phone;
    private String password;

    public User() {}

    public User(String fName, String lName, String phone, String password) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
