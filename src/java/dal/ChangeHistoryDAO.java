/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.*;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.*;

public class ChangeHistoryDAO extends MyDAO {

    public void insertChange(int row, String tableName, String colName, String value, String changedBy, String time) {
        String sql = """
                    INSERT INTO mydb.changehistory 
                    (RowID, TableName, ColName, Value, ChangedBy, ChangeTime) 
                    values (?, ?, ?, ?, ?, ?)
                    """;
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            ps.setInt(1, row);
            ps.setString(2, tableName);
            ps.setString(3, colName);
            ps.setString(4, value);
            ps.setString(5, changedBy);
            ps.setString(6, time);
            ps.execute();
        } catch (Exception e) {
        }
    }

    public List accHistory(Account acc) {
        String sql = "SELECT * FROM mydb.changehistory where ChangeTime=? and RowID=?";
        List<AccountChangeHistory> list = new ArrayList<>();
        List<String> timeList = getDateList(acc.getAccID());
        for (String string : timeList) {
            try {
                System.out.println(acc);
                AccountChangeHistory ach = new AccountChangeHistory((Account)acc.clone(), string.split("_")[1], string.split("_")[0]);
                list.add(ach);
                ps = new DBContext().getConnection().prepareStatement(sql);
                ps.setString(1, string);
                ps.setInt(2, acc.getAccID());
                rs = ps.executeQuery();
                while (rs.next()) {
                    acc = getAcc(rs, acc);
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public Account getAcc(ResultSet rs, Account acc) {
        RoleDAO dao = new RoleDAO();
        List<Role> roleList = dao.getAllRole();
        StatusDAO sttDAO = new StatusDAO();
        List<Status> sttList = sttDAO.getAllStatus();
        try {
            String col = rs.getString("ColName");
            switch (col) {
                case "RoleID" -> acc.setRole(roleList.get(rs.getInt("Value")-1));
                case "Username" -> acc.setUsername(rs.getString("Value"));
                case "AccImg" -> acc.setAccImg(rs.getString("Value"));
                case "Phone" -> acc.setPhone(rs.getString("Value"));
                case "Status" -> acc.setStatus(sttList.get(rs.getInt("Value")-1));
                case "Gender" -> acc.setGender(rs.getString("Value").equals("1"));
                case "Address" -> acc.setAddress(rs.getString("Value"));
                default -> {
                }
            }
        }
        catch (Exception e) {
        }
        return acc;
    }

    public List<String> getDateList(int id) {
        String sql = "SELECT distinct ChangeTime, ChangedBy FROM mydb.changehistory where RowID=? order by ChangeTime desc";
        List<String> time = new ArrayList<>();
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                time.add(rs.getString("ChangeTime")+"_"+rs.getString("ChangedBy"));
            }
        } catch (Exception e) {
        }
        return time;
    }

    public HashMap<String, Object> compareAcc(Account acc, int roleID, String adr, String name, String img, String phone, int stt, boolean gender) {
        HashMap<String, Object> map = new HashMap<>();
        if (acc.getRole().getRoleID() != roleID) {
            map.put("RoleID", acc.getRole().getRoleID());
        }
        if (!acc.getAddress().equals(adr)) {
            map.put("Address", acc.getAddress());
        }
        if (!acc.getUsername().equals(name)) {
            map.put("Username", acc.getUsername());
        }
        if (!acc.getAccImg().equals(img)) {
            map.put("AccImg", acc.getAccImg());
        }
        if (!acc.getPhone().equals(phone)) {
            map.put("Phone", acc.getPhone());
        }
        if (acc.getStatus().getSttID() != stt) {
            map.put("Status", acc.getStatus().getSttID());
        }
        if (acc.isGender() != gender) {
            map.put("Gender", acc.isGender() ? "1" : "0");
        }
        return map;
    }

    public void insertHistory(HashMap<String, Object> map, int accID, String changedBy, String time) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            insertChange(accID, "account", key, value.toString(), changedBy, time);
        }
    }

    //main
    public void changeHistory(Account acc, int roleID, String adr, String name, String img, String phone, int stt, boolean gender, String changedBy) {
        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        insertHistory(compareAcc(acc, roleID, adr, name, img, phone, stt, gender), acc.getAccID(), changedBy, time);
    }

    public void createAcc(int id, String changedBy, String time) {
        insertChange(id, "account", "all", "create", changedBy, time);
    }

    public static void main(String[] args) {
        ChangeHistoryDAO chd = new ChangeHistoryDAO();
        Account acc = (Account)new AccDAO().getAllAcc().get(1);
//        AccountChangeHistory ach = new AccountChangeHistory(acc, "asdad", "asdad");
//        System.out.println(ach);
        List aa = chd.accHistory(acc);
        System.out.println("");
        for (Object object : aa) {
            System.out.println(object);
        }
    }

}
