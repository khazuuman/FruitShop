/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

public class Setting {
    private int settingID;
    private int accID;
    private Type type;
    private int order;
    private boolean isActive;
    private String name;
    private String value;
    private String description;

    public Setting() {
    }

    public Setting(int settingID, Type type, int order, boolean isActive, String name, String value, String description) {
        this.settingID = settingID;
        this.type = type;
        this.order = order;
        this.isActive = isActive;
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public int getSettingID() {
        return settingID;
    }

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }
    
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descrpition) {
        this.description = descrpition;
    }

    @Override
    public String toString() {
        return "Setting{" + "settingID=" + settingID + ", type=" + type + ", order=" + order + ", isActive=" + isActive + ", name=" + name + ", value=" + value + ", description=" + description + '}';
    }
    
    
    
}
