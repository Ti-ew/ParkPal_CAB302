package com.example.parkpal;

public class CustomerModel {

    private int customerId;
    private String email;
    private String password;

    //Constructor
    public CustomerModel(int customerId, String email, String password) {
        this.customerId = customerId;
        this.email = email;
        this.password = password;
    }


    //Getters & Setters

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //toString()
    @Override
    public String toString() {
        return "CustomerModel{" +
                "customerId=" + customerId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
