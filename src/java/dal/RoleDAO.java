/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.*;
import model.*;
import java.sql.SQLException;

public class RoleDAO extends MyDAO {

    public List getAllRole() {
        List role = new ArrayList();
        String sql = "select * from role";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                role.add(new Role(
                        rs.getInt("RoleID"),
                        rs.getString("RoleName")
                ));
            }
        } catch (Exception e) {
        }
        return role;
    }
}
