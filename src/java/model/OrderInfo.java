/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thanh Hai
 */
public class OrderInfo {
    private int orderId;
    private String orderedDate;
    private String customerName;
    private String firstProductName;
    private int otherProductsCount;
    private float totalPrice;
    private String status;

    public OrderInfo(int orderId, String orderedDate, String customerName,
                     String firstProductName, int otherProductsCount,
                     float totalPrice, String status) {
        this.orderId = orderId;
        this.orderedDate = orderedDate;
        this.customerName = customerName;
        this.firstProductName = firstProductName;
        this.otherProductsCount = otherProductsCount;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFirstProductName() {
        return firstProductName;
    }

    public void setFirstProductName(String firstProductName) {
        this.firstProductName = firstProductName;
    }

    public int getOtherProductsCount() {
        return otherProductsCount;
    }

    public void setOtherProductsCount(int otherProductsCount) {
        this.otherProductsCount = otherProductsCount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
