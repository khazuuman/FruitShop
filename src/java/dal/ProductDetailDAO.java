/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.ProductList;

/**
 *
 * @author ADMIN
 */
public class ProductDetailDAO extends MyDAO {

    public ProductList getProductDetail(int ProductId) {
        try {
            String sql = "select p.ProductID, p.ProductName, c.CateName, p.Quantity, p.IsActive, p.Rating, p.Image, pr.RootPrice, pr.SellPrice, p.Description\n"
                    + "from mydb.product p left join mydb.category c on p.CateID = c.CateID\n"
                    + "left join mydb.price pr on p.ProductID = pr.ProductID\n"
                    + "where p.ProductID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ProductId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductList p = new ProductList(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getString(7), rs.getFloat(8), rs.getFloat(9), rs.getString(10));
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }

        public int getOrderId(int AccID) {
        try {
            String sql = "select OrderID\n"
                    + "from mydb.`order`\n"
                    + "where `Status` = 2 and AccID = ?";
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

    public int createOrderId() {
        try {
            String sql = "select max(OrderID)\n"
                    + "from mydb.`order`";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void insertToOrder(int accId, int orderId) {
        try {
            String sql = "insert into mydb.`order` (OrderID, AccID, `Status`, `Date`)\n"
                    + "values (?, ?, 2, NOW())";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, accId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertToCart(int orderId, int productId, int quantity, float price) {
        try {
            String sql = "insert into cart (ProductID, OrderID, Quantity, Price)\n"
                    + "values (?, ?, ?, ?)";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, orderId);
            ps.setInt(3, quantity);
            ps.setFloat(4, price);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<ProductList> getProductCartList(int orderId) {
        try {
            String sql = "select ProductID, Quantity\n"
                    + "from mydb.cart \n"
                    + "where OrderID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            List<ProductList> list = new ArrayList<>();
            while (rs.next()) {
                ProductList p = new ProductList(rs.getInt(1), rs.getInt(2));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void updateProductCartQuantity(int orderId, int productId, int quantity) {
        try {
            String sql = "update mydb.cart set Quantity = ?\n"
                    + "where ProductID = ? and OrderID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.setInt(3, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int getProductQtyCart(int productID, int AccID) {
        try {
            String sql = "select c.Quantity\n"
                    + "from mydb.cart c join mydb.`order` o on c.OrderID = o.OrderID\n"
                    + "where o.AccID = ? and c.ProductID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, AccID);
            ps.setInt(2, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public static void main(String[] args) {
        try {
            ProductDetailDAO dao = new ProductDetailDAO();
            System.out.println(dao.getOrderId(4));
        } catch (Exception e) {
        }
    }
}
