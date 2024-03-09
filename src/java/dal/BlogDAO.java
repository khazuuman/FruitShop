/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.MyDAO.ignoringExc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Blog;
import model.BlogCategory;
import model.BlogCmtCount;
import model.Categories;
import model.Slider;

/**
 *
 * @author MM
 */
public class BlogDAO extends MyDAO {
    
    private final static List<BlogCategory> blogCateList = new BlogCateDAO().getAllBlogCate();
    private final static List<Account> accList = new AccDAO().getAllAcc();

    public Blog blogParse(ResultSet rs) {

        Blog blog = new Blog();
        ignoringExc(() -> blog.setBlogID(rs.getInt("BlogID")));
        ignoringExc(() -> blog.setAccount(accList.get(rs.getInt("AccID") - 1)));
        ignoringExc(() -> blog.setCategory(blogCateList.get(rs.getInt("CateID") - 1)));
        ignoringExc(() -> blog.setIsActive(rs.getBoolean("IsActive")));
        ignoringExc(() -> blog.setTitle(rs.getString("Title")));
        ignoringExc(() -> blog.setImg(rs.getString("Img")));
        ignoringExc(() -> blog.setContent(rs.getString("Content")));
        ignoringExc(() -> blog.setTime(rs.getTimestamp("Time").toLocalDateTime()));
        ignoringExc(() -> blog.setBcount(getBcountById(rs.getInt("BlogID"))));
        return blog;
    }

    AccountNavbarDAO a = new AccountNavbarDAO();

    public List<Blog> listNewBlog() {
        long time = System.nanoTime();
        List<Blog> list = new ArrayList<>();
        String sql = "Select * from blog order by Time desc limit 2";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println(rs.findColumn("IsActive"));
            while (rs.next()) {
                list.add(blogParse(rs));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(System.nanoTime()-time);
        return list;

    }

    public List<Blog> listBlog() {
        List<Blog> list = new ArrayList<>();
        String sql = "Select * from blog";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(blogParse(rs));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public BlogCategory getBlogCategoryById(int id) {
        String sql = "SELECT * FROM mydb.blogcategory where BlogCateID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                BlogCategory b = new BlogCategory(rs.getInt("BlogCateID"), rs.getString("BlogCateName"));
                return b;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<BlogCategory> getAllBlogCategory() {
        List<BlogCategory> list = new ArrayList<>();

        String sql = "SELECT * FROM mydb.blogcategory";
        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BlogCategory b = new BlogCategory(rs.getInt("BlogCateID"), rs.getString("BlogCateName"));
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<Blog> searchBlog(String key) {

        List<Blog> list = new ArrayList();
        String sql = "SELECT * FROM mydb.blog b  join mydb.blogcategory c on b.CateID = c.BlogCateID where 1=1 ";
        if (key != null && !key.equals("")) {
            sql += " and b.Title like '% " + key + "%'or b.Content like '%" + key + "%'" + "or c.BlogCateName like '%" + key + "%'";
        }
        sql += "order by Time desc";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(blogParse(rs));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public int getTotalBlog(String key) {
        List<Blog> list = searchBlog(key);
        return (int) (list.size());
    }

    public List<Blog> pagingBlog(int index, String key) {
        List<Blog> list = new ArrayList();
        String sql = "SELECT * FROM mydb.blog b  join mydb.blogcategory c on b.CateID = c.BlogCateID where 1=1 ";
        if (key != null && !key.equals("")) {
            sql += " and b.Title like '% " + key + "%'or b.Content like '%" + key + "%'" + "or c.BlogCateName like '%" + key + "%'";
        }
        sql += "LIMIT ?, 2;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 2);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(blogParse(rs));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public BlogCmtCount getBcountById(int id) {
        String sql = "SELECT b.blogid, IFNULL(COUNT(c.blogid), 0) AS Count\n"
                + "FROM mydb.blog b\n"
                + "LEFT JOIN blogcomment c ON b.blogid = c.blogid where b.BlogID = ?\n"
                + "GROUP BY b.blogid ;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BlogCmtCount c = new BlogCmtCount(rs.getInt("blogid"), rs.getInt("Count"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public static void main(String[] args) {

        long time = System.nanoTime();
        BlogDAO b = new BlogDAO();
        System.out.println(b.pagingBlog(1, null));
        System.out.println(System.nanoTime()-time);
    }
}
