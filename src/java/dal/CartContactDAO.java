/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import model.CartOrder;

/**
 *
 * @author Thanh Hai
 */
public class CartContactDAO extends MyDAO{
    public List<CartOrder> getOrderByAccId(int accountId) {
        List<CartOrder> list = new ArrayList<>();
        try {
            String sql = "select pro.Image,pro.ProductName,c.Quantity,p.SellPrice from mydb.order o\n" +
"join cart c on o.OrderID =c.OrderID\n" +
"join price p on p.ProductID = c.ProductID\n" +
"join Account a on o.AccID = a.AccID\n" +
"join product pro on pro.ProductID= c.ProductID\n" +
"where o.Status=2 and o.AccID=?;";
            con=new DBContext().getConnection();
             ps = con.prepareStatement(sql);
              ps.setInt(1, accountId);
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
    public static void main(String[] args) {
        CartContactDAO dao = new CartContactDAO();
        List<CartOrder> list = dao.getOrderByAccId(2);
         double cartTotals =0;
             for (CartOrder cartOrder : list) {
              cartTotals += cartOrder.getQuantity()*cartOrder.getSellPrice();
                      
           }
        System.out.println(cartTotals);
    }
            
}
