/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author MM
 */
public class Order {
    private int orderID;
    private List<Product> products;
    private Account account;
    private int status;
    private List quantity;
    private List price;
    private double totalPrice;
    private LocalDateTime time;

    public Order() {
    }

    public Order(int orderID, List<Product> products, Account account, int status, List quantity, List price, double totalPrice, LocalDateTime time) {
        this.orderID = orderID;
        this.products = products;
        this.account = account;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.time = time;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List getQuantity() {
        return quantity;
    }

    public void setQuantity(List quantity) {
        this.quantity = quantity;
    }

    public List getPrice() {
        return price;
    }

    public void setPrice(List price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    

    

    

    

    

    
    
    
}
