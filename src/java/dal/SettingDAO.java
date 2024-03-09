/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.*;
import java.sql.ResultSet;

public class SettingDAO extends MyDAO {
    
    public final List<Type> listType = getAllType();
    public int nrpp;

    public List getAllType() {
        String sql = "SELECT * FROM mydb.type;";
        List<Type> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Type(rs.getInt("TypeID"), rs.getString("TypeName")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Setting settingParse(ResultSet rs) {
        Setting setting = new Setting();
        ignoringExc(() -> setting.setSettingID(rs.getInt("SettingID")));
        ignoringExc(() -> setting.setType(listType.get(rs.getInt("TypeID") - 1)));
        ignoringExc(() -> setting.setOrder(rs.getInt("Order")));
        ignoringExc(() -> setting.setIsActive(rs.getBoolean("IsActive")));
        ignoringExc(() -> setting.setName(rs.getString("Name")));
        ignoringExc(() -> setting.setValue(rs.getString("Value")));
        ignoringExc(() -> setting.setDescription(rs.getString("Description")));
        return setting;
    }

    public List getAllSetting(int index) {
        List list = new ArrayList();

        String sql = "Select * from `mydb`.`setting` limit ? offset ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, nrpp);
            ps.setInt(2, ((index - 1) * nrpp));
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(settingParse(rs));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insertSetting(int type, int or, int act, String name, String val, String des) {
        String sql = """
                     INSERT INTO `mydb`.`setting`
                     (`TypeID`,`Order`,`IsActive`,`Name`,`Value`,`Description`)
                     VALUES (?,?,?,?,?,?);
                     """;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, type);
            ps.setInt(2, or);
            ps.setInt(3, act);
            ps.setString(4, name);
            ps.setString(5, val);
            ps.setString(6, des);
            ps.execute();
        } catch (Exception e) {
        }
    }

    public void updateSetting(int id, int or, int act, String name, String val, String des) {
        String sql = """
                     UPDATE `mydb`.`setting` SET
                     `Order` = ?, `IsActive` = ?, 
                     `Name` = ?, `Value` = ?, `Description` = ?
                     WHERE `SettingID` = ?;
                     """;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(6, id);
            ps.setInt(1, or);
            ps.setInt(2, act);
            ps.setString(3, name);
            ps.setString(4, val);
            ps.setString(5, des);
            ps.execute();
        } catch (Exception e) {
            
        }
    }

    public void changeSetting(String type, boolean act, String val) {
        String sql = String.format("""
                     UPDATE `mydb`.`%s` SET `IsActive` = %s
                     WHERE `%sID` = %s;
                     """, type.toLowerCase(),act?"1":"0", type.equals("Category")?"Cate":type, val);
        System.out.println(sql);
        try {
            ps=con.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
        }
    }

    public int countSetting(String sql) {
        sql = sql.substring(8);
        sql = "select count(SettingID) " + sql;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List searchSetting(String sql, int index) {
        List list = new ArrayList();
        sql += "\n limit ? offset ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, nrpp);
            ps.setInt(2, ((index - 1) * nrpp));
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(settingParse(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void changeStt(int id, boolean stt) {
        String sql = """
                     UPDATE `mydb`.`setting` SET
                     `IsActive` = ?
                     WHERE `SettingID` = ?;
                     """;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, stt?1:0);
            ps.setInt(2, id);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public void changeSttAt(int id, boolean stt, String type) {
        String sql = "update setting natural join type set IsActive = ? where TypeName = ? and `Value` = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, stt?1:0);
            ps.setString(2, type);
            ps.setInt(3, id);
            ps.execute();
        }
        catch (Exception e){
        }
    }
    
    public Setting getSettingByID(int id) {
        String sql = "select * from setting where SettingID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return settingParse(rs);
            }
        } catch (Exception ex) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        SettingDAO setdao = new SettingDAO();
        System.out.println(setdao.getSettingByID(2));
    }
}
