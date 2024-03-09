package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.SaleData;
import java.util.LinkedHashMap;


public class SaleDashboardDAO extends MyDAO{
    public LinkedHashMap<String, Integer> getOrderStats() {
        LinkedHashMap<String, Integer> orderData = new LinkedHashMap<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            DBContext dbContext = new DBContext();
            conn = dbContext.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT DATE(Date) AS OrderDate, COUNT(*) AS TotalOrders, SUM(TotalPrice) AS TotalRevenue \n" +
"FROM `order` \n" +
"GROUP BY OrderDate  \n" +
"ORDER BY OrderDate;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String orderDate = rs.getString("OrderDate");
                int totalOrders = rs.getInt("TotalOrders");
                orderData.put(orderDate, totalOrders);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return orderData;
    }
    public LinkedHashMap<String, Float> getRevenue() {
        LinkedHashMap<String, Float> revenue = new LinkedHashMap<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            DBContext dbContext = new DBContext();
            conn = dbContext.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT DATE(Date) AS OrderDate, COUNT(*) AS TotalOrders, SUM(TotalPrice) AS TotalRevenue " +
                           "FROM `Order` " +
                           "GROUP BY OrderDate ORDER BY OrderDate;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String orderDate = rs.getString("OrderDate");
                int totalOrders = rs.getInt("TotalOrders");
                                float totalRevenue = rs.getFloat("TotalRevenue");

                revenue.put(orderDate, totalRevenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return revenue;
    }
    public List<String> getAllDate(){
        List<String> allDates = new ArrayList<>();
        String sql = "SELECT Date FROM mydb.order";
        try {
            con = new DBContext().getConnection(); // Open a connection to MySQL
            System.out.println("Connected to MySQL!"); // Add this line for logging

            ps = con.prepareStatement(sql);
           
            rs = ps.executeQuery();

            while (rs.next()) {
                String date = rs.getString("Date"); // Thay date_column bằng tên cột chứa ngày trong bảng của bạn
                allDates.add(date);
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
        return allDates;
    }
    
    public static void main(String[] args) {
        SaleDashboardDAO sd = new  SaleDashboardDAO();
    
        LinkedHashMap<String, Integer> date = sd.getOrderStats();
        System.out.println(date);
    }
}
