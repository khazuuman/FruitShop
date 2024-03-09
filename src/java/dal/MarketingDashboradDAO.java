/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Author: Thanh Hai
 */
public class MarketingDashboradDAO extends MyDAO {

    public int[] countData() {
        int[] fetchDataDashboard = new int[5]; // Initialize the variable

        try {
            String sql = "SELECT \n"
                    + "    (SELECT COUNT(*) FROM mydb.blog) AS BlogCount,\n"
                    + "    (SELECT COUNT(*) FROM mydb.feedback) AS FeedbackCount,\n"
                    + "    (select count(*) from mydb.product) AS ProductCount ,\n"
                    + "    (select count(*) from mydb.blogcomment) AS BlogComment,\n"
                    + "    (select count(distinct AccID) from mydb.account ) as CustomerCount;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // Process the result set and get the count
            if (rs.next()) {
                fetchDataDashboard[0] = rs.getInt(1);
                fetchDataDashboard[1] = rs.getInt(2);
                fetchDataDashboard[2] = rs.getInt(3);
                fetchDataDashboard[3] = rs.getInt(4);
                fetchDataDashboard[4] = rs.getInt(5);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MarketingDashboradDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MarketingDashboradDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fetchDataDashboard;
    }

    public Map<String, Integer> countTrendCustomer2(String startDate, String endDate) {
        Map<String, Integer> map = new LinkedHashMap<>();

        try {
            con = new DBContext().getConnection();

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
            String selectDataSQL = "SELECT DateSeries.DateValue, COUNT(account.Time) AS AccountCount "
                    + "FROM DateSeries "
                    + "LEFT JOIN mydb.account ON DATE(DateSeries.DateValue) = DATE(account.Time) AND account.RoleID = 2 "
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

}
