/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

public class AccountChangeHistory {
    private Account acc;
    private String changedBy;
    private String time;

    public Account getAcc() {
        return acc;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public String getTime() {
        return time;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public AccountChangeHistory(Account acc, String changedBy, String time) {
        this.acc = acc;
        this.changedBy = changedBy;
        this.time = time;
    }

    @Override
    public String toString() {
        return "AccountChangeHistory{" + "acc=" + acc + ", changedBy=" + changedBy + ", time=" + time + '}';
    }
    
    
}
