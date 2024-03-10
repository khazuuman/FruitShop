/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.MyDAO.con;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CartOrder;
import model.Order;
import model.OrderDetail;
import model.OrderInfo;
import model.Product;

/**
 *
 * @author Thanh Hai
 */
public class OrderDAO extends MyDAO {

    public List<OrderInfo> getPaginatedOrders(int pageNumber, int pageSize, String sortBy,
            String orderDateFrom, String orderDateTo,
            String saleName, int status, String searchQuery) {
        List<OrderInfo> orderList = new ArrayList<>();
        try {
            String sql = " SELECT o.OrderID, o.Date, a.Username AS CustomerName, (SELECT p.ProductName FROM cart c join product p on c.ProductID  = p.ProductID where c.OrderID = o.OrderID limit 1 ) AS FirstProductName, (SELECT COUNT(*) \n"
                    + " FROM cart c WHERE c.OrderID = o.OrderID) AS OtherProductsCount,\n"
                    + " o.TotalPrice, s.SttName AS Status FROM `order` o JOIN account a ON o.AccID = a.AccID \n"
                    + " JOIN status s ON o.Status = s.SttID "
                    + "WHERE ( o.Date >= '" + orderDateFrom + "') "
                    + "AND ( o.Date <= '" + orderDateTo + "') "
                    + "AND ( a.Username LIKE '%" + saleName + "%') "
                    + "AND (? = -1 OR o.Status = ?) "
                    + "AND (o.OrderID LIKE '%" + searchQuery + "%' OR a.Username LIKE '%" + searchQuery + "%') "
                    + "ORDER BY " + sortBy + " DESC "
                    + "LIMIT " + pageSize + " OFFSET " + (pageNumber - 1) * pageSize;

            ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, status);

            System.out.println(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderInfo orderInfo = new OrderInfo(
                        rs.getInt("OrderID"),
                        rs.getString("Date"),
                        rs.getString("CustomerName"),
                        rs.getString("FirstProductName"),
                        rs.getInt("OtherProductsCount"),
                        rs.getFloat("TotalPrice"),
                        rs.getString("Status")
                );
                orderList.add(orderInfo);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception according to your application's needs
        }
        return orderList;
    }

    public List<CartOrder> getOrderByOrderId(int orderId) {
        List<CartOrder> list = new ArrayList<>();
        try {
            String sql = "select pro.Image,pro.ProductName,c.Quantity,p.SellPrice from mydb.order o\n"
                    + "join cart c on o.OrderID =c.OrderID\n"
                    + "join price p on p.ProductID = c.ProductID\n"
                    + "join Account a on o.AccID = a.AccID\n"
                    + "join product pro on pro.ProductID= c.ProductID\n"
                    + "where o.OrderID=?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {

                CartOrder cart = new CartOrder();
                cart.setImage(rs.getString(1));
                cart.setProductName(rs.getString(2));
                cart.setQuantity(rs.getInt(3));
                cart.setSellPrice(rs.getDouble(4));

                list.add(cart);

            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getSaleNameByOrderID(int orderId) {
        try {
            String sql = "SELECT ac.Username FROM mydb.order o "
                    + "JOIN mydb.account ac ON ac.AccID = o.SaleID "
                    + "WHERE o.OrderID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            String saleName = null; // Initialize with a default value

            if (rs.next()) {
                saleName = rs.getString("Username");
                // If there are multiple matching records, this will return the first one
            }

            return saleName;
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception appropriately (e.g., log it or rethrow as a custom exception)
        }

        return null; // Return a default value or throw an exception based on your requirements
    }

    public List<String> getStatus() {
        List<String> statusList = new ArrayList<>();

        try {
            String sql = "SELECT DISTINCT s.SttName FROM mydb.order o "
                    + "JOIN mydb.status s ON o.Status = s.SttID";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String status = rs.getString("SttName");
                statusList.add(status);
            }

            return statusList;
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception appropriately (e.g., log it or rethrow as a custom exception)
        }

        return statusList;
    }

    public OrderDetail getInforOrderDetail(int orderId) {
        OrderDetail orderDetail = new OrderDetail();

        try {
            String sql = "SELECT ac.Username, ac.Address, ac.Email, ac.Phone, o.Note, o.Date FROM mydb.account ac "
                    + "JOIN mydb.order o ON o.AccID = ac.AccID "
                    + "WHERE o.OrderID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            if (rs.next()) {
                orderDetail.setUsername(rs.getString("Username"));
                orderDetail.setAddress(rs.getString("Address"));
                orderDetail.setEmail(rs.getString("Email"));
                orderDetail.setPhone(rs.getString("Phone"));
                orderDetail.setNote(rs.getString("Note"));
                orderDetail.setOrderDate(rs.getString("Date"));
            }

            return orderDetail;
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception appropriately (e.g., log it or rethrow as a custom exception)
        }

        return orderDetail;
    }

    public void updateSaleIdAndStatus(int orderId, int saleID, int StatusID) {
        try {

            String sql = "UPDATE mydb.order c\n"
                    + "SET c.SaleID = ? ,\n"
                    + "  c.Status = ?\n"
                    + "WHERE c.OrderID = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, saleID);
            ps.setInt(2, StatusID);
            ps.setInt(3, orderId);

            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception appropriately (e.g., log it or rethrow as a custom exception)

        }
    }

    public int getsaleIdbyName(String name) {
        try {
            String sql = "SELECT AccID FROM mydb.account "
                    + "WHERE Username LIKE ? "
                    + "AND (RoleID = 4 OR RoleID = 5)";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();

            int accId = -1; // Initialize with a default value

            while (rs.next()) {
                accId = rs.getInt(1);
                // If there are multiple matching records, this will only return the last one
            }

            return accId;
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception appropriately (e.g., log it or rethrow as a custom exception)
        }

        return -1; // Return a default value or throw an exception based on your requirements
    }

    
    public List<Order> getAllOrder(){
        String sql = "SELECT * FROM mydb.order where ";
        List<Order> list = new ArrayList<>();
        AccDAO a = new AccDAO();
        try {
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) { 
                Order o = new Order(rs.getInt("OrderID"), getProductbyOrderID(rs.getInt("OrderID")),a.getAccByID(rs.getInt("AccID")) ,rs.getInt("status"), quantity(rs.getInt("OrderID")),Price(rs.getInt("OrderID")),rs.getDouble("TotalPrice"),rs.getTimestamp("Date").toLocalDateTime());
                list.add(o);
            }
        } catch (Exception e) {
        }
        return list;
        
    }
    
    public Order getOrderbyProductID(int pid,int oid){
        String sql = "Select o.*,c.ProductID from mydb.order o join cart c on o.OrderID = c.OrderID where ProductID = ? and Status!=2 and o.OrderID=? order by o.Date desc";
        AccDAO a = new AccDAO();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pid);
            ps.setInt(2, oid);
            rs = ps.executeQuery();
            while (rs.next()) { 
                Order o = new Order(rs.getInt("OrderID"), get1ProductbyOrderID(oid,pid),a.getAccByID(rs.getInt("AccID")) ,rs.getInt("status"), quantity1(oid,pid),Price1(oid,pid),rs.getDouble("TotalPrice"),rs.getTimestamp("Date").toLocalDateTime());
                return o;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Order> OrderPaging(int index,int id){
        String sql = "SELECT * FROM mydb.order where Status != 2 and AccID = ? order by Date desc limit ? offset ?";
        List<Order> list = new ArrayList<>();
        AccDAO a = new AccDAO();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setInt(2, 3);
            ps.setInt(3, ((index - 1) * 3));
            rs = ps.executeQuery();
            while (rs.next()) { 
                Order o = new Order(rs.getInt("OrderID"), getProductbyOrderID(rs.getInt("OrderID")),a.getAccByID(rs.getInt("AccID")) ,rs.getInt("status"), quantity(rs.getInt("OrderID")),Price(rs.getInt("OrderID")),rs.getDouble("TotalPrice"),rs.getTimestamp("Date").toLocalDateTime());
                list.add(o);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Product> getProductbyOrderID(int id){
        String sql = "SELECT * FROM mydb.cart where OrderID = ?;";
        List<Product> listP = new ArrayList<>();
        ProductDAO p = new ProductDAO();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product p1 = p.getProductById(rs.getInt("ProductID"));
                listP.add(p1);
            }
            
        } catch (Exception e) {
        }
        return listP;
    }
    public List<Product> get1ProductbyOrderID(int oid,int pid){
        String sql = "SELECT * FROM mydb.cart where OrderID = ? and ProductID= ?;";
        List<Product> listP = new ArrayList<>();
        ProductDAO p = new ProductDAO();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, oid);
            ps.setInt(2, pid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product p1 = p.getProductById(rs.getInt("ProductID"));
                listP.add(p1);
            }
            
        } catch (Exception e) {
        }
        return listP;
    }
    
    
    public List quantity(int id){
        String sql = "SELECT * FROM mydb.cart where OrderID = ?;";
        List q = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                q.add(rs.getInt("Quantity"));
            }
            
        } catch (Exception e) {
        }
        return q;
    }
    public List quantity1(int oid,int pid){
        String sql = "SELECT * FROM mydb.cart where OrderID = ? and ProductID= ?;";
        List q = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, oid);
            ps.setInt(2, pid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                q.add(rs.getInt("Quantity"));
            }
            
        } catch (Exception e) {
        }
        return q;
    }
    public List Price(int id){
        String sql = "SELECT * FROM mydb.cart where OrderID = ?;";
        List q = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                q.add(rs.getDouble("Price"));
            }
            
        } catch (Exception e) {
        }
        return q;
    }
    public List Price1(int oid,int pid){
        String sql = "SELECT * FROM mydb.cart where OrderID = ? and ProductID= ?;";
        List q = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, oid);
            ps.setInt(2, pid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                q.add(rs.getInt("Price"));
            }
            
        } catch (Exception e) {
        }
        return q;
    }
    
    public int countOrder(String sql){
        sql = sql.substring(8);
        sql = "SELECT count(OrderID) "+sql;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    
    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        
        System.out.println(dao.getOrderbyProductID(1, 6));

    }
}
