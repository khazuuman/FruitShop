/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thanh Hai
 */
public class Status {
    private int sttID;
    private Type type;
    private String sttName;

    public Status() {
    }

    public Status(int sttId, Type type, String sttName) {
        this.sttID = sttId;
        this.type = type;
        this.sttName = sttName;
    }

    public int getSttID() {
        return sttID;
    }

    public void setSttID(int sttId) {
        this.sttID = sttId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    

    public String getSttName() {
        return sttName;
    }

    public void setSttName(String sttName) {
        this.sttName = sttName;
    }

    public Status(int sttId) {
        this.sttID = sttId;
    }

    @Override
    public String toString() {
        return "Status{" + "sttId=" + sttID + ", typeId=" + type + ", sttName=" + sttName + '}';
    }

  

   
    
}
