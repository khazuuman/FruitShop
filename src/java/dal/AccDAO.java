/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.security.MessageDigest;
import model.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import org.apache.tomcat.util.codec.binary.Base64;

public class AccDAO extends MyDAO {

    private int nrpp;

    public void setNrpp(int nrpp) {
        this.nrpp = nrpp;
    }

    public int getNrpp() {
        return nrpp;
    }

    List<Role> roleList = new RoleDAO().getAllRole();
    List<Status> sttList = new StatusDAO().getAllStatus();
    
    public Account accParse(ResultSet rs) {
        
        Account acc = new Account();
        ignoringExc(() -> acc.setAccID(rs.getInt("AccID")));
        ignoringExc(() -> acc.setGender(rs.getBoolean("Gender")));
        ignoringExc(() -> acc.setRole(roleList.get(rs.getInt("RoleID") - 1)));
        ignoringExc(() -> acc.setStatus(sttList.get(rs.getInt("Status") - 1)));
        ignoringExc(() -> acc.setEmail(rs.getString("Email")));
        ignoringExc(() -> acc.setUsername(rs.getString("Username")));
        ignoringExc(() -> acc.setPassword(rs.getString("Password")));
        ignoringExc(() -> acc.setAccImg(rs.getString("AccImg")));
        ignoringExc(() -> acc.setPhone(rs.getString("Phone")));
        ignoringExc(() -> acc.setAddress(rs.getString("Address")));
        ignoringExc(() -> acc.setTime(rs.getTimestamp("Time").toLocalDateTime()));
        return acc;
    }

    public List getAllAcc() {
        long time = System.nanoTime();
        List acc = new ArrayList();
        String sql = "select * from account ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                acc.add(accParse(rs));
            }
        } catch (Exception e) {
            
        }
        System.out.println(System.nanoTime()-time);
        return acc;
    }

    public int CountAcc(String sql) {
        int count = 0;
        sql = sql.substring(8);
        sql = "select count(AccID) " + sql;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    public List getAllAcc(int index) {
        List acc = new ArrayList();
        String sql = "select * from account limit ? offset ?";
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            ps.setInt(1, nrpp);
            ps.setInt(2, ((index - 1) * nrpp));
            rs = ps.executeQuery();
            while (rs.next()) {
                acc.add(accParse(rs));
            }
        } catch (Exception e) {
        }
        return acc;
    }

    public int insertAcc(int roleID, String email, String pass, String name, String img, String phone, int stt, boolean gender, String time) {
        String sql = """
                    INSERT INTO mydb.account
                    (RoleID, Email, Password, Username, AccImg, Phone, Status, Gender, Time) VALUES
                    (?,?,?,?,?,?,?,?,?);
                    SELECT last_insert_id() AccID
                    """;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, roleID);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setString(4, name);
            ps.setString(5, img);
            ps.setString(6, phone);
            ps.setInt(7, stt);
            ps.setBoolean(8, gender);
            ps.setString(9, time);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("AccID");
        } catch (Exception e) {    
        }
        return 0;
    }

    public void updateAcc(int roleID, int stt, int accID) {
        String sql = """
                    UPDATE mydb.account 
                    SET RoleID = ?, Status = ? 
                    WHERE AccID = ? 
                     """;
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            ps.setInt(1, roleID);
            ps.setInt(2, stt);
            ps.setInt(3, accID);
            ps.execute();
        } catch (Exception e) {
        }
    }

    public Account getAccByID(int id) {
        String sql = "select * from account where AccID = " +id;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return accParse(rs);
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public boolean checkExist(String email) {
        String sql = "select AccID from account where Email = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }
    public List searchAcc(String sql, String txt, int size, int index) {
        List<Account> user = new ArrayList<>();
        sql += " limit ? offset ? ";
        try {
            ps = con.prepareStatement(sql);
            for (int i = 1; i <= size; i++) {
                ps.setString(i, txt);
            }
            ps.setInt(size + 1, nrpp);
            ps.setInt(size + 2, ((index - 1) * nrpp));
            rs = ps.executeQuery();
            while (rs.next()) {
                user.add(accParse(rs));
            }
        } catch (Exception e) {
        }
        return user;
    }
    
    public String passGenerator() {
        String pass="";
        String lowercase = "gywjoxahcqbmpziuelsnvdfktr";
        String uppercase = "SHOQEKYFZNGRVCJDLPMTAIUBWX";
        String numbers = "9064138752";
        for (int i = 0; i < 2; i++) {
            int a = (int)(Math.random()*1000)%26;
            pass+=lowercase.charAt(a) +""+ uppercase.charAt(26-1-a);
            a = (int)(Math.random()*1000)%10;
            pass+=numbers.charAt(a);
        }
        return pass;
    }
    
    public String toSHA1(String str) {
		String salt = "asjrlkmcoewgsdfgiogoidfjsstwj@tjle;oxqskjhdjksjf1jurVn";// Làm cho mật khẩu phức tap
		String result = null;
		
		str = str + salt;
		try {
			byte[] dataBytes = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
    
    public static void main(String[] args) {
        AccDAO acc = new AccDAO();
        System.out.println(acc.toSHA1("password"));
    }
}
