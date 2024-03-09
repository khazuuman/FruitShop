/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import model.Role;
import java.sql.SQLException;
import model.Status;

/**
 *
 * @author lemti
 */
public class LoginDAO extends MyDAO{

    public Account login(String email, String pass) {
        String sql = "SELECT * FROM mydb.account\n"
                + "WHERE Email = ? AND Password = ?";
        try {
            con = new DBContext().getConnection(); // Open a connection to MySQL
            System.out.println("Connected to MySQL!"); // Add this line for logging

            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
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
            e.printStackTrace(); // Print the exception details for debugging
        } finally {
            try {
                if (con != null) {
                    con.close(); // Close the connection in the finally block
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
   
}
