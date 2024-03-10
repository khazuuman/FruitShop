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
public class FeedbackDAO extends MyDAO {

    public List<Feedback> listBestFeedback() {
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
                        rs.getInt("Rating"), rs.getString("Content"), rs.getBoolean("IsActive"));
                list.add(f);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int countFeedback(String sql) {
        sql = sql.substring(8);
        sql = "SELECT count(FeedbackID) " + sql;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List searchFeeback(String sql, String txt, int size, int index) {
        AccountNavbarDAO acc = new AccountNavbarDAO();
        ProductDAO p = new ProductDAO();
        List<Feedback> list = new ArrayList<>();
        sql += " limit ? offset ? ";
        try {
            ps = con.prepareStatement(sql);
            for (int i = 1; i <= size; i++) {
                ps.setString(i, txt);
            }
            ps.setInt(size + 1, 3);
            ps.setInt(size + 2, ((index - 1) * 3));
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(rs.getInt("FeedbackID"),
                        acc.getAccountById(rs.getInt("AccID")),
                        p.getProductById(rs.getInt("ProductID")),
                        rs.getInt("Rating"), rs.getString("Content"), rs.getBoolean("IsActive"));
                list.add(f);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Feedback getFeedbackByID(int id) {
        AccountNavbarDAO acc = new AccountNavbarDAO();
        ProductDAO p = new ProductDAO();
        String sql = "select * from feedback where FeedbackID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Feedback(rs.getInt("FeedbackID"),
                        acc.getAccountById(rs.getInt("AccID")),
                        p.getProductById(rs.getInt("ProductID")),
                        rs.getInt("Rating"), rs.getString("Content"), rs.getBoolean("IsActive"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Feedback> getAllFeedback(int index) {
        AccountNavbarDAO acc = new AccountNavbarDAO();
        ProductDAO p = new ProductDAO();
        List<Feedback> list = new ArrayList<>();
        String sql = "select * from feedback limit ? offset ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, 3);
            ps.setInt(2, ((index - 1) * 3));
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(rs.getInt("FeedbackID"),
                        acc.getAccountById(rs.getInt("AccID")),
                        p.getProductById(rs.getInt("ProductID")),
                        rs.getInt("Rating"), rs.getString("Content"), rs.getBoolean("IsActive"));
                list.add(f);
            }

        } catch (SQLException e) {
        }
        return list;
    }

    public void insertFeedback(int aid, int pid, int rating, String content) {
        String sql = "INSERT INTO `mydb`.`feedback`\n"
                + "(\n"
                + "`AccID`,\n"
                + "`ProductID`,\n"
                + "`Rating`,\n"
                + "`IsActive`,\n"
                + "`Content`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, aid);
            ps.setInt(2, pid);
            ps.setInt(3, rating);
            ps.setInt(4, 1);
            ps.setString(5, content);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void updateFeedback(int id, int status) {
        String sql = "UPDATE `mydb`.`feedback`\n"
                + "SET\n"
                + "\n"
                + "`IsActive` = ?\n"
                + "\n"
                + "WHERE `FeedbackID` = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        FeedbackDAO s = new FeedbackDAO();
        
    }
}
