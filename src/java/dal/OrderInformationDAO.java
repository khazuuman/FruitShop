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
import model.*;

public class OrderInformationDAO extends MyDAO {

    private AccDAO accDao = new AccDAO();
    private ProductDAO pDao = new ProductDAO();

    public List<OrderDetailDTO> listOrderDetailByOid(String oid) {
        List<OrderDetailDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM mydb.cart c join product p on c.ProductID = p.ProductID\n"
                + "where c.OrderID = " + oid;
        try {
            connection = new DBContext().getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetailDTO p = new OrderDetailDTO(rs.getInt("Quantity"), rs.getFloat("Price"), pDao.getProductById(rs.getInt("ProductID")));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static void main(String[] args) {
        System.out.println(new OrderInformationDAO().listOrderDetailByOid("1"));
    }

    public OrderDTO getOrderById(String id) {
        String sql = "SELECT * FROM mydb.order o join mydb.status s on o.Status = s.SttID where OrderID = " + id;
        try {
            connection = new DBContext().getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderDTO p = new OrderDTO(rs.getInt(1), rs.getDate("Date"), rs.getFloat(7), rs.getString("Note"));
                p.setAccount(accDao.getAccByID(rs.getInt("AccID")));
                p.setStatus(rs.getString("SttName"));
                return p;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
      public OrderDTO CancelOrder(String id) {
        String sql = "update mydb.order set Status = 9 where OrderID = " + id;
        try {
            connection = new DBContext().getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
      public OrderDTO FeedbackedOrder(String id) {
        String sql = "update mydb.order set Status = 11 where OrderID = " + id;
        try {
            connection = new DBContext().getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
