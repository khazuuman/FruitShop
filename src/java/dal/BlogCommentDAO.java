/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import model.*;
import java.util.*;

public class BlogCommentDAO extends MyDAO{
    public List get5RecentBlogCmt () {
        AccDAO accDAO = new AccDAO();
        List<Account> accList = accDAO.getAllAcc();
        List<BlogComment> blogCmtList = new ArrayList<>();
        String sql = "select * from blogcomment order by CmtID Desc limit 5";
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            
            rs = ps.executeQuery();
            while (rs.next()) {
               blogCmtList.add(new BlogComment(
                        rs.getInt("CmtID"),
                        accList.get(rs.getInt("AccID")-1),
                        null,
                        rs.getInt("ReCmtID"),
                        rs.getString("Content"),
                        rs.getTimestamp("Time").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
        }
        return blogCmtList;
    }
    
    public List getBlogCmt (int id) {
        List<BlogComment> blogCmtRep = getReplies(id);
        List<BlogComment> blogCmtRoot = getRoot(id);
        List<LinkedHashMap<Integer, BlogComment>> blogCmtList = new ArrayList<>();
        for(BlogComment cmt : blogCmtRoot) {
            LinkedHashMap<Integer, BlogComment> cmt2 = new LinkedHashMap<>();
            cmt2.put(cmt.getCmtID(), cmt);
            for (BlogComment blogComment : blogCmtRep) {
                if (cmt2.containsKey(blogComment.getReCmt())) {
                    cmt2.put(blogComment.getCmtID(), blogComment);
                }
            }
            blogCmtList.add(cmt2);
        }
        return blogCmtList;
    }
    
    public List getRoot(int bid) {
        AccDAO accDAO = new AccDAO();
        List<Account> accList = accDAO.getAllAcc();
        List<BlogComment> blogCmtList = new ArrayList<>();
        String sql = "select * from blogcomment where BlogID = ? and ReCmtID is null";
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            ps.setInt(1, bid);
            rs = ps.executeQuery();
            while (rs.next()) {
               blogCmtList.add(new BlogComment(
                        rs.getInt("CmtID"),
                        accList.get(rs.getInt("AccID")-1),
                        null,
                        0,
                        rs.getString("Content"),
                        rs.getTimestamp("Time").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
        }
        return blogCmtList;
    }
    
    public List getReplies(int bid) {
        AccDAO accDAO = new AccDAO();
        List<Account> accList = accDAO.getAllAcc();
        List<BlogComment> blogCmtList = new ArrayList<>();
        String sql = "select * from blogcomment where BlogID = ? and ReCmtID = ?";
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            ps.setInt(1, bid);
            rs = ps.executeQuery();
            while (rs.next()) {
               blogCmtList.add(new BlogComment(
                        rs.getInt("CmtID"),
                        accList.get(rs.getInt("AccID")-1),
                        null,
                        rs.getInt("ReCmtID"),
                        rs.getString("Content"),
                        rs.getTimestamp("Time").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
        }
        return blogCmtList;
    }
    
    public void insertCmt(int bid, int aid, String cmt, int cid) {
        String sql ="""
                    INSERT INTO mydb.blogcomment (Content, AccID, BlogID, ReCmtID, Time) VALUES
                    (?,?,?,?,?)
                    """;
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            ps.setString(1, cmt);
            ps.setInt(2, aid);
            ps.setInt(3, bid);
            if (cid==0) {ps.setInt(4, cid);}else{ps.setNull(4, java.sql.Types.NULL);}
            ps.setString(5, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(java.time.LocalDateTime.now()));
        } catch (Exception e) {
        }
    }
    
    public HashMap getBlogCmtCount () {
        String sql = "select count(BlogID) num, BlogID from blogcomment group by BlogID";
        HashMap<Integer, Integer> cmtblog = new HashMap<>();
        try {
            ps = new DBContext().getConnection().prepareStatement(sql);
            rs =ps.executeQuery();
            while (rs.next()) {                
                cmtblog.put(rs.getInt("BlogID"), rs.getInt("num"));
            }
        } catch (Exception e){
            
        }
        return cmtblog;
    }
    
    
    public static void main(String[] args) {
        BlogCommentDAO blogCommentDAO = new BlogCommentDAO();
        List<BlogComment> blogComments = blogCommentDAO.get5RecentBlogCmt();
        for (BlogComment blogComment : blogComments) {
            System.out.println(blogComment);
        }
    }
}

