/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MM
 */
public class Slider {
    private int SliderID;
    private int AccID;
    private Product product;
    private int IsActive;
    private String SliderImg;   
    private String Content;

    public Slider() {
    }

    public Slider(int SliderID, int AccID, Product product, int IsActive, String SliderImg, String Content) {
        this.SliderID = SliderID;
        this.AccID = AccID;
        this.product = product;
        this.IsActive = IsActive;
        this.SliderImg = SliderImg;
        this.Content = Content;
    }

    public int getSliderID() {
        return SliderID;
    }

    public void setSliderID(int SliderID) {
        this.SliderID = SliderID;
    }

    public int getAccID() {
        return AccID;
    }

    public void setAccID(int AccID) {
        this.AccID = AccID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int IsActive) {
        this.IsActive = IsActive;
    }

    public String getSliderImg() {
        return SliderImg;
    }

    public void setSliderImg(String SliderImg) {
        this.SliderImg = SliderImg;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    
    
}
