/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.Categories;
import java.util.ArrayList;
import model.ProductList;

/**
 *
 * @author nguye
 */
public class ProductListDAO extends MyDAO {

    public List<Categories> getAllCate() {
        try {
            String sql = "select c.CateID, c.IsActive, c.CateName, COALESCE(count(p.CateID), 0) as count\n"
                    + "from mydb.product p right join mydb.category c on p.CateID = c.CateID\n"
                    + "group by c.CateName, c.CateID";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Categories> list = new ArrayList<>();
            while (rs.next()) {
                Categories c = new Categories(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getInt(4));
                list.add(c);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ProductList> getAllProduct(String search, String categories, String rating, String minPrice, String maxPrice, String sort, int index) {
        String whereSql = "";
        String sortSql = "";
        if (search != null) {
            whereSql = "where p.ProductName like '%" + search + "%'";
        }
        if (categories != null) {
            whereSql = "where c.CateName like '" + categories + "'";
        }
        if (rating != null) {
            whereSql = "where p.Rating >= " + rating;
        }
        if (minPrice != null && maxPrice == null) {
            whereSql = "where pr.RootPrice >= " + minPrice + " or pr.SellPrice >= " + minPrice;
        } else if (minPrice == null && maxPrice != null) {
            whereSql = "where pr.RootPrice <= " + maxPrice + " or pr.SellPrice <= " + maxPrice;
        } else if (minPrice != null && maxPrice != null) {
            whereSql = "where (pr.SellPrice IS NOT NULL AND pr.SellPrice BETWEEN " + minPrice + " AND " + maxPrice + ") OR (pr.SellPrice IS NULL AND pr.RootPrice BETWEEN " + minPrice + " AND " + maxPrice + ")";
        }
        if (sort != null) {
            sortSql = "order by COALESCE(pr.SellPrice, pr.RootPrice) " + sort;
        }
        index = (index - 1) * 9;
        try {
            String sql = "select p.ProductID, p.ProductName, c.CateName, p.Quantity, p.IsActive, p.Rating, p.Image, pr.RootPrice, pr.SellPrice, p.Description\n"
                    + "from product p left join category c on p.CateID = c.CateID\n"
                    + "left join price pr on p.ProductID = pr.ProductID\n"
                    + whereSql + "\n"
                    + sortSql + "\n"
                    + "limit " + index + ", 9";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            List<ProductList> list = new ArrayList<>();
            while (rs.next()) {
                ProductList p = new ProductList(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getString(7), rs.getFloat(8), rs.getFloat(9), rs.getString(10));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public int countProduct(String search, String categories, String rating, String minPrice, String maxPrice) {
        String whereSql = "";
        if (search != null) {
            whereSql = "where p.ProductName like '%" + search + "%'";
        }
        if (categories != null) {
            whereSql = "where c.CateName like '" + categories + "'";
        }
        if (rating != null) {
            whereSql = "where p.Rating >=" + rating;
        }
        if (minPrice != null && maxPrice == null) {
            whereSql = "where pr.RootPrice >= " + minPrice + " or pr.SellPrice >= " + minPrice;
        } else if (minPrice == null && maxPrice != null) {
            whereSql = "where pr.RootPrice <= " + maxPrice + " or pr.SellPrice <= " + maxPrice;
        } else if (minPrice != null && maxPrice != null) {
            whereSql = "where (pr.SellPrice IS NOT NULL AND pr.SellPrice BETWEEN " + minPrice + " AND " + maxPrice + ") OR (pr.SellPrice IS NULL AND pr.RootPrice BETWEEN " + minPrice + " AND " + maxPrice + ")";
        }
        try {
            String sql = "select count(p.ProductID) as count\n"
                    + "from product p left join category c on p.CateID = c.CateID\n"
                    + "left join price pr on p.ProductID = pr.ProductID\n"
                    + whereSql;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<ProductList> getNewest3Product() {
        try {
            String sql = "select p.ProductID, p.ProductName, c.CateName, p.Quantity, p.IsActive, p.Rating, p.Image, pr.RootPrice, pr.SellPrice, p.Description\n"
                    + "from product p inner join category c on p.CateID = c.CateID\n"
                    + "inner join price pr on p.ProductID = pr.ProductID\n"
                    + "order by pr.ChangeDate Desc\n"
                    + "limit 0, 3";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            List<ProductList> list = new ArrayList<>();
            while (rs.next()) {
                ProductList p = new ProductList(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getString(7), rs.getFloat(8), rs.getFloat(9), rs.getString(10));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public int getProductQuantity(int productID) {
        try {
            String sql = "select Quantity\n"
                    + "from mydb.product\n"
                    + "where ProductID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public boolean checkProductExist(int accID, int productID) {
        try {
            String sql = "select o.OrderID\n"
                    + "from mydb.`order` o join mydb.cart c on o.OrderID = c.OrderID\n"
                    + "where o.AccID = ? and c.ProductID = ? and o.`Status` = 2";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accID);
            ps.setInt(2, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) != 0) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            ProductListDAO dao = new ProductListDAO();
//            System.out.println(dao.getAllProduct(null, null, null, null, null, null, 1));
            System.out.println(dao.checkProductExist(4, 1));
        } catch (Exception e) {
        }
    }
}
