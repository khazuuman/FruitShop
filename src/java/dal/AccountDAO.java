/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.PSource;

import model.Role;
import model.Status;
import model.Type;

/**
 *
 * @author Thanh Hai
 */
public class AccountDAO extends MyDAO {

    public Account getAccountById(int accountId) {
        try {
            String sql = " SELECT *\n"
                    + "FROM account as acc\n"
                    + "  JOIN role on acc.RoleID = role.RoleID\n"
                    + "  join status on acc.Status= status.SttID\n"
                    + "  where acc.AccId like ? ;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {

                Account account = new Account();
                account.setAccID(rs.getInt(1));
                account.setRole(new Role(rs.getInt(2), rs.getString(11)));
                account.setStatus(new Status());
                account.setGender(rs.getBoolean(4));
                account.setEmail(rs.getString(5));
                account.setPassword(rs.getString(7));
                account.setUsername(rs.getString(6));
                account.setAccImg(rs.getString(8));

                account.setPhone(rs.getString(9));
                account.setAddress(rs.getString(10));

                return account;
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updatePassword(String pass, String email) {
        try {
            String sql = "UPDATE account\n"
                    + "SET Password = ?\n"
                    + "WHERE Email = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String CheckPassWord(String email) {
        String pass = null; // Initialize pass to avoid compilation errors

        try {
            String sql = "SELECT Password FROM account WHERE Email = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                pass = rs.getString("Password");
            }

            rs.close(); // Close the ResultSet
            ps.close(); // Close the PreparedStatement

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pass;
    }

    public void UpdateAccount(Account acc, String name, String img, Boolean gender, String phone, String address) {
        try {
            String sql = "UPDATE account\n"
                    + "   SET Username = ?,\n"
                    + "       AccImg = ?,\n"
                    + "       Gender = ?,\n"
                    + "       Phone = ?,\n"
                    + "  Address = ?\n"
                    + " WHERE Email = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, img);
            ps.setBoolean(3, gender);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, acc.getEmail());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        Account acc = dao.getAccountById(6);

        try {
            dao.UpdateAccount(acc, "jh", "ngu", true, "0912320855", "giao tan");
            System.out.println("Account updated successfully");
        } catch (Exception e) {
            System.out.println("Error updating account: " + e.getMessage());
        }
    }

}
