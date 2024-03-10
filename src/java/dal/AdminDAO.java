/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.MyDAO.con;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;

/**
 *
 * @author MM
 */
public class AdminDAO extends MyDAO{
    public int countOrder(int status){
        String sql = "SELECT count(*) as count FROM mydb.order where status = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("count");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public int totalCus(){
        String sql = "SELECT count(AccID) as count FROM mydb.account;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("count");
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public double totalIncome(){
        String sql = "SELECT sum(TotalPrice) as sum FROM mydb.order where Status = 3;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getDouble("sum");
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    
    public int getIdAccNew(){
        String sql = "SELECT AccID FROM mydb.account order by AccID desc limit 1;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("AccID");
            }
        } catch (Exception e) {
        }
        return 0;
    }
    public int getIdOrderNew(){
        String sql = "SELECT AccID FROM mydb.order order by AccID desc limit 1;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("AccID");
            }
        } catch (Exception e) {
        }
        return 0;
    }
    public double getAVG(){
        String sql = "SELECT avg(Rating) as avg FROM mydb.feedback;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getDouble("avg");
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    
    public Map<String, Integer> countTrendCustomer2(String startDate, String endDate) {
        Map<String, Integer> map = new LinkedHashMap<>();
        try {
            // Create temporary table
            String createTableSQL = "CREATE TEMPORARY TABLE DateSeries (DateValue DATE);";
            ps = con.prepareStatement(createTableSQL);
            ps.execute();

            // Insert values into the temporary table
            String insertValuesSQL = "INSERT INTO DateSeries (DateValue) "
                    + "SELECT DATE(?) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY "
                    + "FROM (SELECT 0 AS a UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS a "
                    + "CROSS JOIN (SELECT 0 AS a UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS b "
                    + "CROSS JOIN (SELECT 0 AS a UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS c "
                    + "WHERE DATE(?) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY < ?;";
            ps = con.prepareStatement(insertValuesSQL);
            ps.setString(1, startDate);
            ps.setString(2, startDate);
            ps.setString(3, endDate);
            ps.execute();

            // Select data from the temporary table
            String selectDataSQL = "SELECT DateSeries.DateValue, COUNT(order.Date) AS AccountCount "
                    + "FROM DateSeries "
                    + "LEFT JOIN mydb.order ON DATE(DateSeries.DateValue) = DATE(order.Date) AND order.Status != 2 "
                    + "WHERE DateSeries.DateValue >= ? AND DateSeries.DateValue < ? "
                    + "GROUP BY DateSeries.DateValue "
                    + "ORDER BY DateSeries.DateValue;";
            ps = con.prepareStatement(selectDataSQL);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();

            // Process the result set and put the counts into the map
            while (rs.next()) {
                String dateValue = rs.getString("DateValue");
                int accountCount = rs.getInt("AccountCount");
                map.put(dateValue, accountCount);
            }

            // Drop the temporary table
            String dropTableSQL = "DROP TEMPORARY TABLE IF EXISTS DateSeries;";
            ps = con.prepareStatement(dropTableSQL);
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(MarketingDashboradDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MarketingDashboradDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources (result sets, statements, and connection) in a 'finally' block
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return map;
    }
    
    public List<Admin> BestProduct(){
        List<Admin> list = new ArrayList<>();
        ProductDAO p = new ProductDAO();
        String sql = "select count(ProductID) as Number,sum(Quantity) as Quantity,c.ProductID,sum(Price) as Total from cart c join mydb.order o on c.OrderId = o.OrderID where Status = 3 group by ProductID order by Quantity desc limit 5; ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Admin a = new Admin(rs.getInt("Number"), rs.getInt("Quantity"),p.getProductById(rs.getInt("ProductID")) , rs.getDouble("Total"));
                list.add(a);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    
    public static void main(String[] args) {
        AdminDAO a = new AdminDAO();
        System.out.println(a.countTrendCustomer2("2024-01-15 08:00:00", "2024-01-20 08:00:00"));
    }
}
