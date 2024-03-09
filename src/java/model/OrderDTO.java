/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.Date;

public class OrderDTO {
    
    int oid;
    Date orderDate;
    float totalPrice;
    String note;
    String status;
    private Account account;

    public OrderDTO(int oid, Date orderDate, float totalPrice, String note) {
        this.oid = oid;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "oid=" + oid + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice + ", note=" + note + ", status=" + status + ", account=" + account + '}';
    }
  
}
