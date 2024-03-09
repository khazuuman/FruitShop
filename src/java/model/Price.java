/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author MM
 */
public class Price {

    private Date changeDate;
    private Product product;
    private int quantity;
    private double importPrice;
    private double rootPrice;
    private double sellPrice;

    public Price() {
    }

    public Price(Date changeDate, Product product, int quantity, double importPrice, double rootPrice, double sellPrice) {
        this.changeDate = changeDate;
        this.product = product;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.rootPrice = rootPrice;
        this.sellPrice = sellPrice;
    }

    public Price(double importPrice, double rootPrice, double sellPrice) {
        this.importPrice = importPrice;
        this.rootPrice = rootPrice;
        this.sellPrice = sellPrice;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getRootPrice() {
        return rootPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRootPrice(double rootPrice) {
        this.rootPrice = rootPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public String toString() {
        return "Price{" + "changeDate=" + changeDate + ", product=" + product + ", quantity=" + quantity + ", importPrice=" + importPrice + ", rootPrice=" + rootPrice + ", sellPrice=" + sellPrice + '}';
    }

}
