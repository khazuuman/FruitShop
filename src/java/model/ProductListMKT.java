/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MM
 */
public class ProductListMKT {
    private int productID;
    private CategoryMKT categoryMKT;
    private int quantity;
    private boolean isActive;
    private double rating;
    private String productName;
    private String image;
    private String description;
    private Price price;
    public ProductListMKT() {
    }

    public ProductListMKT(int productID, CategoryMKT categoryMKT, int quantity, boolean isActive, double rating, String productName, String image, String description, Price price) {
        this.productID = productID;
        this.categoryMKT = categoryMKT;
        this.quantity = quantity;
        this.isActive = isActive;
        this.rating = rating;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public CategoryMKT getCategoryMKT() {
        return categoryMKT;
    }

    public void setCategoryMKT(CategoryMKT categoryMKT) {
        this.categoryMKT = categoryMKT;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductListMKT{" + "productID=" + productID + ", categoryMKT=" + categoryMKT + ", quantity=" + quantity + ", isActive=" + isActive + ", rating=" + rating + ", productName=" + productName + ", image=" + image + ", description=" + description + ", price=" + price + '}';
    }

    

    
}
