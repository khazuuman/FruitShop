/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Role;
import model.Status;
import model.Type;


/**
 *
 * @author MM
 */
public class AccountNavbarDAO extends MyDAO{
    public Account getAccountById(int id) {

        String sql = "select * from account where AccID = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getInt("AccID"), getRoleById(rs.getInt("RoleID")), getStatusById(rs.getInt("Status")),rs.getBoolean("Gender"),rs.getString("Email"),rs.getString("Username"),rs.getString("Password"),rs.getString("AccImg"),rs.getString("Phone"),rs.getString("Address"),rs.getTimestamp("Time").toLocalDateTime());
                return a;
            }
        } catch(SQLException e){
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(AccountNavbarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public Role getRoleById(int id) {

        String sql = "SELECT * FROM mydb.role";
        try {
            connection = new DBContext().getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Role r = new Role(rs.getInt("RoleID"), rs.getString("RoleName"));
                return r;   
            }
        
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) { 
            Logger.getLogger(AccountNavbarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Status getStatusById(int id) {

        String sql = "SELECT * FROM mydb.status";
        try {
            connection = new DBContext().getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Status s = new Status(rs.getInt("SttID"), getTypeById(rs.getInt("TypeID")), rs.getString("SttName"));
                return s;
            }
        }catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) { 
            Logger.getLogger(AccountNavbarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Type getTypeById(int id) {

        String sql = "SELECT * FROM mydb.type";
        try {
            connection = new DBContext().getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Type t = new Type(rs.getInt("typeID"), rs.getString("typeName"));
                return t;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) { 
            Logger.getLogger(AccountNavbarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        AccountNavbarDAO a = new AccountNavbarDAO();
        System.out.println(a.getRoleById(1));
    }
    
}
