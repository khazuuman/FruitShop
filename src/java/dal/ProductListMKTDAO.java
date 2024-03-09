/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dal;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CategoryMKT;
import model.Price;
import model.ProductListMKT;

public class ProductListMKTDAO extends MyDAO{
    
    private int nrpp;

    public void setNrpp(int nrpp) {
        this.nrpp = nrpp;
    }

    public int getNrpp() {
        return nrpp;
    }
    
    public ProductListMKT productParse(ResultSet rs) throws SQLException {
         CategoryMKT cate = new CategoryMKT(rs.getString("CateName"));     
         Price price = new Price(rs.getDouble("ImportPrice"),rs.getDouble("RootPrice"),rs.getDouble("SellPrice"));
//        List<CategoryMKT> cate = pl.getAllCateMKT();
        ProductListMKT product = new ProductListMKT();
        ignoringExc(() -> product.setProductID(rs.getInt("ProductID")));
        ignoringExc(() -> product.setImage(rs.getString("Image")));
        ignoringExc(() -> product.setProductName(rs.getString("ProductName"))) ;
        ignoringExc(() -> product.setCategoryMKT(cate)) ;
        ignoringExc(() -> product.setQuantity(rs.getInt("Quantity"))) ;    
        ignoringExc(() -> product.setPrice(price));
//        ignoringExc(() -> product.setQuantity(rs.getInt("Quantity")));
        ignoringExc(() -> product.setDescription(rs.getString("Description")));
        ignoringExc(() -> product.setIsActive(rs.getBoolean("IsActive")));
       
        
        return product;
    }
    
    public List getAllProductMKT() {
        List product = new ArrayList();
        String sql = "SELECT * FROM product join price on product.ProductID = price.ProductID\n" +
                    "join category on product.CateID = category.CateID;";
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                product.add(productParse(rs));
            }
        } catch (Exception e) {
            
        }
        return product;
    }
    public List getProductByIdMKT(int id) {
        List product = new ArrayList();
        String sql = "SELECT * FROM product join price on product.ProductID = price.ProductID\n" +
                    "join category on product.CateID = category.CateID where product.ProductID = ?;";
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                product.add(productParse(rs));
            }
        } catch (Exception e) {
            
        }
        return product;
    }
    
    
    public int CountProduct(String sql) {
        int count = 0;
        sql = sql.substring(8);
        sql = "select count(product.ProductID) " + sql;
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }
    
    public List getAllProduct(int index) {
        List acc = new ArrayList();
        String sql = "SELECT * FROM product join price on product.ProductID = price.ProductID\n" +
"join category on product.CateID = category.CateID limit ? offset ?;";
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            ps.setInt(1, nrpp);
            ps.setInt(2, ((index - 1) * nrpp));
            rs = ps.executeQuery();
            while (rs.next()) {
                acc.add(productParse(rs));
            }
        } catch (Exception e) {
        }
        return acc;
    }
    
    public List searchProduct(String sql, String txt, int size, int index) {
        List<ProductListMKT> plist = new ArrayList<>();
        sql += " limit ? offset ? ";
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            for (int i = 1; i <= size; i++) {
                ps.setString(i, txt);
            }
            ps.setInt(size + 1, nrpp);
            ps.setInt(size + 2, ((index - 1) * nrpp));
            rs = ps.executeQuery();
            while (rs.next()) {
                plist.add(productParse(rs));
            }
        } catch (Exception e) {
        }
        return plist;
    }
    public List getAllCateMKT() {
        List cate = new ArrayList();
        String sql = "select * from category";
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cate.add(new CategoryMKT(
                        rs.getInt("CateID"),
                        rs.getBoolean("IsActive"),
                        rs.getString("cateName")
                ));
            }
        } catch (Exception e) {
        }
        return cate;
    }
   

    public List getCategory(int cateId) {
        List<CategoryMKT> stt = new ArrayList<>();
        String sql = "select * from category";
        if (cateId > 0) {
            sql += " where CateID = " + cateId;
        }
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                stt.add(new CategoryMKT(
                        rs.getInt("CateID"),
                        rs.getBoolean("IsActive"),
                        rs.getString("CateName")
                ));
            }
        } catch (Exception e) {
        }
        return stt;
    }
//    public void insertProduct(String name, String description, int cateId, String image, boolean status, float ip, float rp, float sp, int quantity, String time) throws Exception {
//        String sql = "INSERT INTO product (CateID, Quantity, IsActive, ProductName, Image, Description) VALUES (?,?,?,?,?,?);";
//    sql += "SET @last_product_id = LAST_INSERT_ID();";
//    sql += "INSERT INTO price (ChangeDate, ProductID, ImportPrice, RootPrice, SellPrice) VALUES (?, @last_product_id, ?, ?, ?);";
//        try {
//            ps = new DBContext().getConnection().prepareStatement(sql);
//            ps.setInt(1, cateId);
//            ps.setInt(2, quantity);
//            ps.setBoolean(3, status);
//            ps.setString(4, name);
//            ps.setString(5, image);
//            ps.setString(6, description);
//            ps.setString(7, time);
//            ps.setFloat(8, ip);
//            ps.setFloat(9, rp);
//            ps.setFloat(10, sp);
//            ps.executeUpdate();
//            System.out.println("Thêm thành công");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Thêm thất bại");
//        } finally {
//            // Đảm bảo giải phóng tài nguyên
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
    public void insertProduct(String name, String description, int cateId, String image, boolean status, float ip, float rp, float sp, int quantity, String time) throws Exception {
    String insertProductSql = "INSERT INTO product (CateID, Quantity, IsActive, ProductName, Image, Description) VALUES (?,?,?,?,?,?)";
    String getLastProductIdSql = "SELECT LAST_INSERT_ID() AS LastProductID";
    String insertPriceSql = "INSERT INTO price (ChangeDate, ProductID, ImportPrice, RootPrice, SellPrice) VALUES (?, ?, ?, ?, ?)";

    
    
    try {
        con = new DBContext().getConnection();
        con.setAutoCommit(false); // Tắt tự động commit

        // Insert into product table
        ps = con.prepareStatement(insertProductSql);
        ps.setInt(1, cateId);
        ps.setInt(2, quantity);
        ps.setBoolean(3, status);
        ps.setString(4, name);
        ps.setString(5, image);
        ps.setString(6, description);
        ps.executeUpdate();

        // Get the last inserted ProductID
        ps = con.prepareStatement(getLastProductIdSql);
        rs = ps.executeQuery();
        int lastProductId = 0;
        if (rs.next()) {
            lastProductId = rs.getInt("LastProductID");
        }

        // Insert into price table
        ps = con.prepareStatement(insertPriceSql);
        ps.setString(1, time);
        ps.setInt(2, lastProductId);
        ps.setFloat(3, ip);
        ps.setFloat(4, rp);
        ps.setFloat(5, sp);
        ps.executeUpdate();

        con.commit(); // Commit thay đổi vào cơ sở dữ liệu
        System.out.println("Thêm thành công");
    } catch (SQLException e) {
        con.rollback(); // Rollback thay đổi nếu có lỗi
        e.printStackTrace();
        System.out.println("Thêm thất bại");
    } finally {
        // Đảm bảo giải phóng tài nguyên
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.setAutoCommit(true); // Bật tự động commit trở lại
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
    
    public void updateProduct(int id, String name, String description, int cateId, String image, boolean status, float ip, float rp, float sp, int quantity, String time) throws Exception {
    String updateProductSql = "UPDATE product SET ProductName=?, Description=?, CateID=?, Image=?, IsActive=?, Quantity=? WHERE ProductID=?";
    String updatePriceSql = "UPDATE price SET ChangeDate=?, ImportPrice=?, RootPrice=?, SellPrice=? WHERE ProductID=?";

    try {
        
        

        con = new DBContext().getConnection();
        con.setAutoCommit(false); // Tắt tự động commit

        // Update product table
        ps = con.prepareStatement(updateProductSql);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setInt(3, cateId);
        ps.setString(4, image);
        ps.setBoolean(5, status);
        ps.setInt(6, quantity);
        ps.setInt(7, id);
        ps.executeUpdate();

        // Update price table
        ps = con.prepareStatement(updatePriceSql);
        ps.setString(1, time);
        ps.setFloat(2, ip);
        ps.setFloat(3, rp);
        ps.setFloat(4, sp);
        ps.setInt(5, id);
        ps.executeUpdate();

        con.commit(); // Commit thay đổi vào cơ sở dữ liệu
        System.out.println("Cập nhật sản phẩm và giá thành công");
    } catch (SQLException e) {
        con.rollback(); // Rollback thay đổi nếu có lỗi
        e.printStackTrace();
        System.out.println("Cập nhật sản phẩm và giá thất bại");
    } finally {
        // Đảm bảo giải phóng tài nguyên
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.setAutoCommit(true); // Bật tự động commit trở lại
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


 
    
    
    public static void main(String[] args) {
    ProductListMKTDAO pdao = new ProductListMKTDAO();
        System.out.println(pdao.getProductByIdMKT(2));
       
      

    }
}
