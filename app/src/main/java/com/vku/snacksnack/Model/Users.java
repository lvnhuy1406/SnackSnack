package com.vku.snacksnack.Model;

public class Users {
    private String Name;
    private String Password;
    private String Phone;
    private String IsStaff;

    public Users() {
    }

    public Users(String name, String password) {
        Name = name;
        Password = password;
        IsStaff = "false";
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }
}
