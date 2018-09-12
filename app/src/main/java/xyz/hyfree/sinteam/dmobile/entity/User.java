package xyz.hyfree.sinteam.dmobile.entity;

/**
 * Created by Administrator on 2018/8/22.
 */
public class User {
    private String userName;
    private String userPass;
    private String userSex;
    private int userAge;
    private String userPhone;
    private String userAdress;

    public User() {
    }

    public User(String userName, String userPass, String userSex, int userAge, String userPhone, String userAdress) {
        this.userName = userName;
        this.userPass = userPass;
        this.userSex = userSex;
        this.userAge = userAge;
        this.userPhone = userPhone;
        this.userAdress = userAdress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(String userAdress) {
        this.userAdress = userAdress;
    }
}
