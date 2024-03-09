/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feedback;
import model.Product;

/**
 *
 * @author MM
 */
public class FeedbackDAO extends MyDAO{
    public List<Feedback> listBestFeedback(){
        AccountNavbarDAO acc = new AccountNavbarDAO();
        ProductDAO p = new ProductDAO();
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM mydb.feedback where Rating = 5 limit 3;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(rs.getInt("FeedbackID"),
                        acc.getAccountById(rs.getInt("AccID")),
                        p.getProductById(rs.getInt("ProductID")), 
                        rs.getInt("Rating"), rs.getString("Content"));
                list.add(f);
            }
        
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static void main(String[] args) {
        FeedbackDAO s = new FeedbackDAO();
        System.out.println(s.listBestFeedback().get(1).getAccount().getUsername());
    }
}
