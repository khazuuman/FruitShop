/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Hai
 */
public class MyDAO extends DBContext {

    static Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public MyDAO() {
        try {
            con = new DBContext().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(MyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ignoringExc(RunnableExc r) {
        try {
            r.run();
        } catch (Exception e) {
        }
    }

    public interface RunnableExc {

        void run()
                throws Exception;
    }
}
