/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thanh Hai
 */
public class CartOrder {
    private String image;
    private String productName;
    private int quantity;
    private double sellPrice;

    public CartOrder() {
    }

    public CartOrder(String image, String productName, int quantity, double sellPrice) {
        this.image = image;
        this.productName = productName;
        this.quantity = quantity;
        this.sellPrice = sellPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public String toString() {
        return "CartOrder{" + "image=" + image + ", productName=" + productName + ", quantity=" + quantity + ", sellPrice=" + sellPrice + '}';
    }
    
}
