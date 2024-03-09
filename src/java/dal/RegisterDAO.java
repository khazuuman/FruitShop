/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import model.Role;
import model.Status;

/**
 *
 * @author lemti
 */
public class RegisterDAO extends MyDAO{
    public void signUp(String email, String pass,String username) {
        String sql = "INSERT INTO mydb.account (RoleID, Status, Gender, Email, Password, Username, AccImg,Phone,Address,Time) VALUES\n" +
"(2,7,1, ?, ?, ?,'user1_image.jpg','null','null',NOW())";
        try {
            con = new DBContext().getConnection();//mo ket noi voi sql
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            ps.setString(3, username);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public Account checkAccountExist(String email) {
        String sql = "SELECT * FROM mydb.account\n"
                + "WHERE Email = ?\n";

        try {
            con = new DBContext().getConnection();//mo ket noi voi sql
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt(2));  
                Status status = new Status(rs.getInt(3));
                return new Account(rs.getInt(1),
                        role,
                        status,
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getTimestamp(11).toLocalDateTime()
                        
                );
            }
        } catch (Exception e) {
        }
        return null;
    }
    public static void main(String[] args) {
        RegisterDAO dao = new RegisterDAO();
        try {
            dao.signUp("dd@fpt.edu.vn", "hai123","jkasdf");
            System.out.println("success");
        } catch (Exception e) {
        }
    }
}
