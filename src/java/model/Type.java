/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lemti
 */
public class Type {
    private int typeId;
    private String typeName;

    public Type() {
    }
    

    public Type(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Type(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Type{" + "typeId=" + typeId + ", typeName=" + typeName + '}';
    }
    
}
