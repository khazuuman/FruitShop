/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AccountDAO;
import dal.CartContactDAO;
import dal.ProductListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.CartOrder;
import model.Categories;
import model.ProductList;


/**
 *
 * @author Thanh Hai
 */
public class CartContactController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartContactController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartContactController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc1 = (Account) session.getAttribute("acc");
       if (acc1 == null){
           response.sendRedirect("LoginController");
           return;
       }else{
           
            Account acc = dao.getAccountById(acc1.getAccID());
            CartContactDAO daoCart = new CartContactDAO();
             List<CartOrder> listCart = daoCart.getOrderByAccId(2);
             double cartTotals =0;
             for (CartOrder cartOrder : listCart) {
              cartTotals += cartOrder.getQuantity()*cartOrder.getSellPrice();
                      
           }
       
       ProductListDAO daop = new ProductListDAO();
       List<ProductList> list3p = (ArrayList<ProductList>) daop.getNewest3Product();
        List<Categories> listc = (ArrayList<Categories>) daop.getAllCate();
         request.setAttribute("newest3products", list3p);
           request.setAttribute("cateList", listc);
           request.setAttribute("ListCart", listCart);
           request.setAttribute("cartTotals", cartTotals);
   
        request.setAttribute("acc", acc);
       }
       request.getRequestDispatcher("cartContact1.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
