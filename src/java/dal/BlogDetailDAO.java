/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Blog;

import java.sql.ResultSet;
import java.util.*;
import model.*;

public class BlogDetailDAO extends MyDAO {

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
        return blog;
    }

    public Blog getBlog(int blogID) {

        String sql = "select * from blog where BlogID = ?;";
        Blog blog = new Blog();
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            ps.setInt(1, blogID);
            rs = ps.executeQuery();
            while (rs.next()) {
                blog = blogParse(rs);
            }
        } catch (Exception e) {
        }
        return blog;
    }

    public List searchBlog(String sql, String txt, int size) {
        List<Blog> blog = new ArrayList<>();
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            for (int i = 1; i <= size; i++) {
                ps.setString(i, txt);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                blog.add(blogParse(rs));
            }
        } catch (Exception e) {
        }
        return blog;
    }

    public List relatedBlog(int num, int CateID, int id) {
        String sql = "select * from blog where CateID = " + CateID + " and not BlogID = " + id
                + " order by BlogID desc ";
        if (num >= 0) {
            sql += "limit " + num;
        }
        return searchBlog(sql, "", 0);
    }

    public List recentBlog(int num, int id) {
        String sql = "select * from blog where not BlogID = " + id + " order by BlogID desc ";
        if (num >= 0) {
            sql += " limit " + num;
        }
        return searchBlog(sql, "", 0);
    }

    public static void main(String[] args) {

    }
}
