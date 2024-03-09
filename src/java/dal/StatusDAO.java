/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import model.*;
import java.util.*;

public class StatusDAO extends MyDAO {

    public List getAllStatus() {
        return getStatus(0);
    }

    public List getAccStatus() {
        return getStatus(3);
    }

    public List getStatus(int type) {
        List<Status> stt = new ArrayList<>();
        String sql = "select * from status";
        if (type > 0) {
            sql += " where TypeID = " + type;
        }
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                stt.add(new Status(
                        rs.getInt("SttID"),
                        null,
                        rs.getString("SttName")
                ));
            }
        } catch (Exception e) {
        }
        return stt;
    }
}
