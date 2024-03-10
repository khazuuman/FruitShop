/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.MyDAO.con;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import model.CartOrder;

/**
 *
 * @author Thanh Hai
 */
public class CartContactDAO extends MyDAO {

    public List<CartOrder> getOrderByAccId(int accountId) {
        List<CartOrder> list = new ArrayList<>();
        try {
            String sql = "select pro.Image,pro.ProductName,c.Quantity,p.SellPrice from mydb.order o\n"
                    + "join cart c on o.OrderID =c.OrderID\n"
                    + "join price p on p.ProductID = c.ProductID\n"
                    + "join Account a on o.AccID = a.AccID\n"
                    + "join product pro on pro.ProductID= c.ProductID\n"
                    + "where o.Status=2 and o.AccID=?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {

                CartOrder cart = new CartOrder();
                cart.setImage(rs.getString(1));
                cart.setProductName(rs.getString(2));
                cart.setQuantity(rs.getInt(3));
                cart.setSellPrice(rs.getDouble(4));

                list.add(cart);

            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getOrderIDbyAccID(int accountId) {
        try {
            String sql = "SELECT OrderID FROM mydb.order\n"
                    + "WHERE AccID = ? AND Status = 2;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();

            int orderId = -1; // Initialize with a default value

            while (rs.next()) {
                orderId = rs.getInt(1);
                // If there are multiple matching records, this will only return the last one
            }

            return orderId;
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception appropriately (e.g., log it or rethrow as a custom exception)
        }

        return -1; // Return a default value or throw an exception based on your requirements
    }

    public void updateCartNote(int orderId, String note, double cartTotals , int saleId) {
        try {

            String sql = "UPDATE mydb.order c\n"
                    + "SET c.Note = ? ,\n"
                    + "  c.TotalPrice = ? ,\n"
                    + "  c.SaleID = ?\n"
                    + "WHERE c.OrderID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, note);
            ps.setDouble(2, cartTotals);
            ps.setInt(3, saleId);
            ps.setInt(4, orderId);

            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception appropriately (e.g., log it or rethrow as a custom exception)

        }
    }
  

    

    public static void main(String[] args) {
        CartContactDAO dao = new CartContactDAO();
        try {
            dao.updateCartNote(6, "giao tan la so 1", 123.0,4);
            System.out.println("update succes");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
