/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class BlogCateDAO extends MyDAO {

    public List getAllBlogCate() {
        List blog = new ArrayList();
        String sql = "select * from blogcategory";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                blog.add(new BlogCategory(
                        rs.getInt("BlogCateID"),
                        rs.getString("BlogCateName")
                ));
            }
        } catch (Exception e) {
        }
        return blog;
    }
}
