/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AccDAO;
import dal.AccountDAO;
import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CartOrder;
import model.OrderDetail;

/**
 *
 * @author Thanh Hai
 */
public class OrderDetailSaleController extends HttpServlet {
   
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
            out.println("<title>Servlet OrderDetailSaleController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderDetailSaleController at " + request.getContextPath () + "</h1>");
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
        String orderId = request.getParameter("orderId");
        String status =request.getParameter("status");
        int orderID = Integer.parseInt(orderId);
        OrderDAO dao = new OrderDAO();
        AccDAO accdao = new AccDAO();
        List<CartOrder> list = dao.getOrderByOrderId(orderID);
         double cartTotals =0;
             for (CartOrder cartOrder : list) {
              cartTotals += cartOrder.getQuantity()*cartOrder.getSellPrice();
                      
           }
             List arry = accdao.getAccSale();
             String saleName = dao.getSaleNameByOrderID(orderID);
             List<String> statusList= dao.getStatus();
             OrderDetail orderDetail = dao.getInforOrderDetail(orderID);
        request.setAttribute("listOrder", list);
        request.setAttribute("cartTotals", cartTotals);
        request.setAttribute("saleName", saleName);
        request.setAttribute("saleList", arry);
        request.setAttribute("statusList", statusList);
        request.setAttribute("status", status);
        request.setAttribute("orderDetail", orderDetail);
        request.setAttribute("orderId", orderId);
        request.getRequestDispatcher("OrderDetail.jsp").forward(request, response);
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
        String status = request.getParameter("status");
       int statusID = Integer.parseInt(status);
       String orderId = request.getParameter("orderId");
       int orderID = Integer.parseInt(orderId);
       String salerName = request.getParameter("salerName");
        OrderDAO dao = new OrderDAO();
        int saleId = dao.getsaleIdbyName(salerName);
        dao.updateSaleIdAndStatus(orderID, saleId, statusID);
        response.sendRedirect("OrderList");
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
