/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.MyDAO.con;
import java.util.ArrayList;
import java.util.List;
import model.ProductList;

/**
 *
 * @author ADMIN
 */
public class CartCompletionDAO extends MyDAO {

    public void updateOrderStatus(int AccId) {
        try {
            String sql = "update mydb.`order`\n"
                    + "set `Status` = 1\n"
                    + "where AccID = ? and `Status` = 2";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, AccId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateProductQuantity(int productId, int quantity) {
        try {
            String sql = "update mydb.product\n"
                    + "set Quantity = ?\n"
                    + "where ProductID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<ProductList> getProductOrder(int OrderID) {
        try {
            String sql = "select p.ProductID, p.Image, p.ProductName, c.Price, c.Quantity\n"
                    + "from mydb.cart c join mydb.`order` o on c.OrderID=o.OrderID\n"
                    + "join mydb.product p on c.ProductID=p.ProductID\n"
                    + "where o.OrderID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, OrderID);
            rs = ps.executeQuery();
            List<ProductList> list = new ArrayList<>();
            while (rs.next()) {
                ProductList p = new ProductList(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

//    public void updateUserAddress(int AccId, String address) {
//        try {
//            String sql = "update mydb.account\n"
//                    + "set `Address` = ?\n"
//                    + "where AccID = ?";
//            con = new DBContext().getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, AccId);
//            ps.setString(2, address);
//            ps.executeUpdate();
//        } catch (Exception e) {
//        }
//    }
    public int getProductQty(int productId) {
        try {
            String sql = "select Quantity\n"
                    + "from mydb.product\n"
                    + "where ProductID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, productId);

            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
