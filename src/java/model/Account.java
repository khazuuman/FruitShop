/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author Thanh Hai
 */
public class Account implements Cloneable{

    private int accID;
    private Role role;
    private Status status;
    private boolean gender;
    private String email;
    private String username;
    private String password;
    private String accImg;
    private String phone;
    private String address;
    private LocalDateTime time;
    

    public Account() {
    }

    public Account(int accID, Role role, Status status, boolean gender, String email, String username, String password, String accImg, String phone, String address, LocalDateTime time) {
        this.accID = accID;
        this.role = role;
        this.status = status;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
        this.accImg = accImg;
        this.phone = phone;
        this.address = address;
        this.time = time;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccImg() {
        return accImg;
    }

    public void setAccImg(String accImg) {
        this.accImg = accImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    
     @Override
    public Object clone()
            throws CloneNotSupportedException {
        return (Account)super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String toString() {
        return "Account{" + "accID=" + accID + ", role=" + role + ", status=" + status + ", gender=" + gender + ", email=" + email + ", username=" + username + ", password=" + password + ", accImg=" + accImg + ", phone=" + phone + ", address=" + address + ", time=" + time + '}';
    }

  

}
