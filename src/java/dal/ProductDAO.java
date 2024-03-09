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
import model.Blog;
import model.Categories;
import model.Price;
import model.Product;

/**
 *
 * @author MM
 */
public class ProductDAO extends MyDAO {

    public Product productParse(ResultSet rs) {

        Product product = new Product();
        ignoringExc(() -> product.setProductID(rs.getInt("ProductID")));
        ignoringExc(() -> product.setCategory(getCategoryById(rs.getInt("CateID"))));
        ignoringExc(() -> product.setQuantity(rs.getInt("Quantity")));
        ignoringExc(() -> product.setIsActive(rs.getBoolean("IsActive")));
        ignoringExc(() -> product.setRating(rs.getDouble("Rating")));
        ignoringExc(() -> product.setProductName(rs.getString("ProductName")));
        ignoringExc(() -> product.setImage(rs.getString("Image")));
        ignoringExc(() -> product.setDescription(rs.getString("Description")));

        return product;
    }

    public List<Product> listBestProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, t.bestSell\n"
                + "FROM mydb.product p\n"
                + "JOIN (\n"
                + "SELECT ProductID, COUNT(*) AS bestSell\n"
                + "FROM mydb.cart\n"
                + "GROUP BY ProductID\n"
                + "ORDER BY bestSell DESC\n"
                + "LIMIT 4\n"
                + ") t ON p.ProductID = t.ProductID;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(productParse(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Product getProductById(int id) {
        String sql = "select * from product where ProductID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return productParse(rs);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Categories> listHomeCategory() {
        List<Categories> list = new ArrayList<>();
        String sql = "select * from category";
        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categories c = new Categories(rs.getInt("CateID"), rs.getBoolean("IsActive"), rs.getString("CateName"), 0);
                list.add(c);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Categories getCategoryById(int id) {
        String sql = "select * from category where CateID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categories c = new Categories(rs.getInt("CateID"), rs.getBoolean("IsActive"), rs.getString("CateName"), 0);
                return c;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Price getPriceByPiD(int id) {
        String sql = "select * from mydb.price where ProductID = ?";
        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Price p = new Price(rs.getDate("ChangeDate"), getProductById(rs.getInt("ProductID")), rs.getInt("Quantity"), rs.getDouble("ImportPrice"), rs.getDouble("RootPrice"), rs.getDouble("SellPrice"));
                return p;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> getRecentProduct() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM mydb.product\n"
                    + "ORDER BY ProductID DESC\n"
                    + "LIMIT 5;";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(productParse(rs));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDAO s = new ProductDAO();
        System.out.println(s.getProductById(1).getProductName());
        System.out.println(s.listBestProduct().size());
    }

}
