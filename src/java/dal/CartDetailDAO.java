/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import java.util.ArrayList;
import model.ProductList;

/**
 *
 * @author ADMIN
 */
public class CartDetailDAO extends MyDAO {

    public List<ProductList> getProductListCart(int AccID) {
        try {
            String sql = "select p.ProductID, p.Image, p.ProductName, c.Price, c.Quantity\n"
                    + "from mydb.cart c join mydb.`order` o on c.OrderID=o.OrderID\n"
                    + "join mydb.product p on c.ProductID=p.ProductID\n"
                    + "where o.AccID=? and o.`Status`=2";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, AccID);
            rs = ps.executeQuery();
            List<ProductList> list = new ArrayList<>();
            while (rs.next()) {
                ProductList p = new ProductList(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int orderIdStatus2(int AccID) {
        try {
            String sql = "select OrderID\n"
                    + "from mydb.`Order` \n"
                    + "where Status = 2 and AccID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, AccID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countProductinOrder(int AccID) {
        try {
            String sql = "select count(c.ProductID)\n"
                    + "from mydb.cart c join mydb.`order` o on c.OrderID=o.OrderID\n"
                    + "join mydb.product p on c.ProductID=p.ProductID\n"
                    + "where o.AccID=? and o.`Status`=2";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, AccID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void deleteOrder(int OrderID) {
        try {
            String sql = "delete from mydb.`order` o\n"
                    + "where o.OrderID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, OrderID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteCartProduct(int productID, int AccID) {
        try {
            int orderID = orderIdStatus2(AccID);
            String sql = "delete from mydb.cart c\n"
                    + "where c.OrderID = ? and c.ProductID=?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderID);
            ps.setInt(2, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateCartProductQuantity(int productID, int quantity, int AccID) {
        try {
            int orderID = orderIdStatus2(AccID);
            String sql = "update mydb.cart c\n"
                    + "set c.Quantity = ?\n"
                    + "where c.OrderID = ? and c.ProductID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, orderID);
            ps.setInt(3, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        try {
            CartDetailDAO dao = new CartDetailDAO();
            dao.updateCartProductQuantity(2, 100, 2);
            System.out.println(dao.orderIdStatus2(2));
        } catch (Exception e) {
        }
    }
}
