/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.MyDAO.ignoringExc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Slider;

/**
 *
 * @author MM
 */
public class SliderDAO extends MyDAO {
    private final static ProductDAO p = new ProductDAO();
    public Slider sliderParse(ResultSet rs) {
        Slider slider = new Slider();
        ignoringExc(() -> slider.setSliderID(rs.getInt("SliderID")));
        ignoringExc(() -> slider.setAccID(rs.getInt("AccID")));
        ignoringExc(() -> slider.setProduct(p.getProductById(rs.getInt("productID"))));
        ignoringExc(() -> slider.setIsActive(rs.getInt("IsActive")));
        ignoringExc(() -> slider.setSliderImg(rs.getString("SliderImg")));
        ignoringExc(() -> slider.setContent(rs.getString("Content")));
        return slider;
    }
    
    public List<Slider> getListSlider() {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT * FROM mydb.slider where IsActive = 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(sliderParse(rs));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Slider getSliderById(int id) {
        String sql = "SELECT * FROM mydb.slider where SliderID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return sliderParse(rs);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateSlider(int action, int id) {
        String sql = "UPDATE `mydb`.`slider`\n"
                + "SET\n"
                + "`IsActive` = ?\n"
                + "WHERE `SliderID` = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, action);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteSlider(int id) {
        String sql = "DELETE FROM `mydb`.`slider`\n"
                + "WHERE SliderID = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Slider> searchSlider(String key, String status) {
        List<Slider> list = new ArrayList();
        String sql = "SELECT * FROM mydb.slider where 1=1";
        if (key != null && !key.equals("")) {
            sql += " and Content like '% " + key + "%'";
        }
        if (status != null && !status.equals("") && !status.equals("2")) {
            sql += " and IsActive = " + status;
        }
        if (status != null && !status.equals("") && status.equals("2")) {
            sql += "";
        }
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(sliderParse(rs));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public int getTotalSlider(String key, String status) {
        List<Slider> list = searchSlider(key, status);
        return (int) (list.size());
    }

    public List<Slider> pagingSlider(int index, String key, String status) {
        List<Slider> list = new ArrayList();
        String sql = "SELECT * FROM mydb.slider where 1=1 ";
        if (key != null && !key.equals("")) {
            sql += " and Content like '% " + key + "%'";
        }
        if (status != null && !status.equals("") && !status.equals("2")) {
            sql += " and IsActive = " + status;
        }
        if (status != null && !status.equals("") && status.equals("2")) {
            sql += "";
        }
        sql += " LIMIT ?,2";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 2);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(sliderParse(rs));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void updateSliderFull(int id, String img, String content, int status, int pid) {
        String sql = "UPDATE `mydb`.`slider`\n"
                + "SET\n"
                + "`SliderImg` = ?,\n"
                + "`IsActive` = ?,\n"
                + "`Content` = ?,\n"
                + "`productID` = ?\n"
                + "WHERE `SliderID` = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, img);
            ps.setInt(2, status);
            ps.setString(3, content);
            ps.setInt(4, pid);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addSlider(int accid, int pid, int status, String img, String content) {
        String sql = "INSERT INTO `mydb`.`slider`\n"
                + "(`AccID`,\n"
                + "`productID`,\n"
                + "`IsActive`,\n"
                + "`SliderImg`,\n"
                + "`Content`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, accid);
            ps.setInt(2, pid);
            ps.setInt(3, status);
            ps.setString(4, img);
            ps.setString(5, content);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        SliderDAO s = new SliderDAO();
        //s.updateSliderFull(1, "slider_image2.jpg", "slider_image2.jpg", 0, 5);
//        List<Slider> slider = s.pagingSlider(2, null, "2");
//        List<Slider> slider1 = s.searchSlider(null, "2");
        //Slider sli = s.getSliderById(2);
        System.out.println(s.getListSlider().size());
        //System.out.println(sli.getContent());

    }
}
