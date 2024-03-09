/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.ProductList;
import org.json.JSONObject;

/**
 *
 * @author ADMIN
 */
public class updateQuantityAjax extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            CartDetailDAO daoc = new CartDetailDAO();
            HttpSession ss = request.getSession();
            Account acc = (Account) ss.getAttribute("acc");
            int productID = Integer.parseInt(request.getParameter("productID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            daoc.updateCartProductQuantity(productID, quantity, acc.getAccID());
            ProductList sp = new ProductList();
            List<ProductList> loadDelete = daoc.getProductListCart(acc.getAccID());
            float totalCart = 0;
            float productPrice = 0;
            for (ProductList pl : loadDelete) {
                if (pl.getProductID() == productID){
                    productPrice = pl.getQuantity() * pl.getMainPrice();
                }
                totalCart += pl.getMainPrice() * pl.getQuantity();
            }
            
//            String productCartResultString = "";
//            String totalCartResultString = "";
//            int index = 0;
//            for (ProductList lD : loadDelete) {
//                productCartResultString += "<tr>\n"
//                        + "                                <td class=\"td-id\">" + (index + 1) + "</td>\n"
//                        + "                                <td class=\"td-img\">\n"
//                        + "                                    <div class=\"pro-img\">\n"
//                        + "                                        <img src=\"./ProductListImg/" + lD.getImage() + "\"\n"
//                        + "                                             alt=\"\">\n"
//                        + "                                    </div>\n"
//                        + "                                </td>\n"
//                        + "                                <td class=\"td-name\">" + lD.getProductName() + "</td>\n"
//                        + "                                <td class=\"td-price\">$" + lD.getMainPrice() + "</td>\n"
//                        + "                                <td class=\"td-quantity\">\n"
//                        + "                                    <input class='inputs-quantity' type=\"number\" value=\"" + lD.getQuantity() + "\" name=\"cartQty\" id=\"\" max=\"\" data-id='" + lD.getProductID() + "'>\n"
//                        + "                                    <input type=\"hidden\" id=\"productID\" name=\"productId\" value=\"" + lD.getProductID() + "\">\n"
//                        + "                                </td>\n"
//                        + "                                <td class=\"td-total\">$" + lD.getMainPrice() * lD.getQuantity() + "</td>                              \n"
//                        + "                                <td class=\"td-removebtn\">\n"
//                        + "                                    <button onclick=\"loadAfterDelete(" + lD.getProductID() + ")\"\"><i class=\"fa-solid fa-xmark\"></i></button>\n"
//                        + "                                </td>\n"
//                        + "                            </tr>";
//            }
//            totalCartResultString = "<tr>\n"
//                    + "                                    <th>Subtotal</th>\n"
//                    + "                                    <th>$" + totalCart + "</th>\n"
//                    + "                                </tr>\n"
//                    + "                                <tr>\n"
//                    + "                                    <th>Delivery</th>\n"
//                    + "                                    <th>$0.00</th>\n"
//                    + "                                </tr>\n"
//                    + "                                <tr>\n"
//                    + "                                    <th style=\"font-weight: 600;\">TOTAL</th>\n"
//                    + "                                    <th class=\"th-total\">$" + totalCart + "</th>\n"
//                    + "                                </tr>";
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("productPrice", productPrice);
            jsonResponse.put("totalCart", totalCart);
            out.print(jsonResponse);
            out.flush();
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
            // Trả về một thông báo lỗi phù hợp nếu cần
            // out.print("{ \"error\": \"An error occurred\" }");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
