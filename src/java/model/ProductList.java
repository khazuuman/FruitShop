/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class ProductList {
    private int ProductID;
    private String ProductName;
    private String CateName;
    private int Quantity;
    private int IsActice;
    private float Rating;
    private String Image;
    private float MainPrice;
    private float SalePrice;
    private String discription;

    public ProductList() {
    }

    public ProductList(int ProductID, String ProductName, String CateName, int Quantity, int IsActice, float Rating, String Image, float MainPrice, float SalePrice, String discription) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.CateName = CateName;
        this.Quantity = Quantity;
        this.IsActice = IsActice;
        this.Rating = Rating;
        this.Image = Image;
        this.MainPrice = MainPrice;
        this.SalePrice = SalePrice;
        this.discription = discription;
    }

    public ProductList(int ProductID, String Image, String ProductName, float MainPrice, int Quantity) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Quantity = Quantity;
        this.Image = Image;
        this.MainPrice = MainPrice;
    }

    public ProductList(int ProductID, int Quantity) {
        this.ProductID = ProductID;
        this.Quantity = Quantity;
    }
    
    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String CateName) {
        this.CateName = CateName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getIsActice() {
        return IsActice;
    }

    public void setIsActice(int IsActice) {
        this.IsActice = IsActice;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float Rating) {
        this.Rating = Rating;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public float getMainPrice() {
        return MainPrice;
    }

    public void setMainPrice(float MainPrice) {
        this.MainPrice = MainPrice;
    }

    public float getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(float SalePrice) {
        this.SalePrice = SalePrice;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    
}
