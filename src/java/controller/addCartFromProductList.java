/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDetailDAO;
import dal.ProductListDAO;
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
public class addCartFromProductList extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            HttpSession ss = request.getSession();
            Account acc = (Account) ss.getAttribute("acc");
            ProductListDAO dao = new ProductListDAO();
            int productID = Integer.parseInt(request.getParameter("productID"));

            String notifiHTML = "";
            if (acc == null) {
                notifiHTML = "<div style=\"display: block; pointer-events: auto;\" class=\"notification-container\">\n"
                        + "                <div class=\"child-container-noti\">\n"
                        + "                    <span class=\"noti-close\">&times;</span>\n"
                        + "                    <div class=\"noti-icon-fail\"><i class=\"fa-solid fa-xmark\" style=\"color: #ff2424;\"></i></div>\n"
                        + "                    <h2>You need to log in to use the service</h2>\n"
                        + "                    <div class=\"two-btn\">\n"
                        + "                        <button onclick=\"window.location.href = 'Login.jsp'\">Login</button>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>";
            } else {
                int quantity = dao.getProductQuantity(productID);
                if (quantity == 0) {
                    notifiHTML = "<div style=\"display: block; pointer-events: auto;\" class=\"notification-container\">\n"
                            + "                <div class=\"child-container-noti\">\n"
                            + "                    <span class=\"noti-close\">&times;</span>\n"
                            + "                    <div class=\"noti-icon-fail\"><i class=\"fa-solid fa-xmark\" style=\"color: #ff2424;\"></i></div>\n"
                            + "                    <h2>Add to cart failed because the product was out of stock</h2>\n"
                            + "                    <div class=\"two-btn\">\n"
                            + "                        <button onclick=\"window.location.href = 'ProductListController'\">Continue Shopping</button>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "            </div>";
                } else if (dao.checkProductExist(acc.getAccID(), productID) == true) {
                    notifiHTML = "<div style=\"display: block; pointer-events: auto;\" class=\"notification-container\">\n"
                            + "                <div class=\"child-container-noti\">\n"
                            + "                    <span class=\"noti-close\">&times;</span>\n"
                            + "                    <div class=\"noti-icon\"><i class=\"fa-solid fa-check\" style=\"color: #4cd930;\"></i></div>\n"
                            + "                    <h2>Products already in the cart</h2>\n"
                            + "                    <div class=\"two-btn\">\n"
                            + "                        <button onclick=\"window.location.href = 'ProductListController'\">Continue Shopping</button>\n"
                            + "                        <button onclick=\"window.location.href = 'CartDetailController'\">View Cart</button>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "            </div>";
                } else {
                    ProductDetailDAO daoPd = new ProductDetailDAO();
                    ProductList p = daoPd.getProductDetail(productID);
                    float salePrice = p.getSalePrice();
                    float rootprice = p.getMainPrice();
                    int OrderID = daoPd.getOrderId(acc.getAccID());
                    if (OrderID == 0) {
                        OrderID = daoPd.createOrderId();
                        daoPd.insertToOrder(acc.getAccID(), OrderID);
                    }
                    if (salePrice != 0) {
                        daoPd.insertToCart(OrderID, productID, 1, salePrice);
                    } else {
                        daoPd.insertToCart(OrderID, productID, 1, rootprice);
                    }
                    notifiHTML = "<div style=\"display: block; pointer-events: auto;\" class=\"notification-container\">\n"
                            + "                <div class=\"child-container-noti\">\n"
                            + "                    <span class=\"noti-close\">&times;</span>\n"
                            + "                    <div class=\"noti-icon\"><i class=\"fa-solid fa-check\" style=\"color: #4cd930;\"></i></div>\n"
                            + "                    <h2>Add to cart successfully!</h2>\n"
                            + "                    <div class=\"two-btn\">\n"
                            + "                        <button onclick=\"window.location.href = 'ProductListController'\">Continue Shopping</button>\n"
                            + "                        <button onclick=\"window.location.href = 'CartDetailController'\">View Cart</button>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "            </div>";
                }

            }

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("popuphtml", notifiHTML);
            out.print(jsonResponse.toString());
            out.flush();
        } catch (Exception e) {
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
