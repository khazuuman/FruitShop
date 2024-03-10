/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.*;
import java.util.List;
import model.*;

/**
 *
 * @author Thanh Hai
 */
@WebServlet(name = "OrderList", urlPatterns = {"/OrderList"})
public class OrderList extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.setAttribute("ac", new AccDAO().getAllAcc());
        int pageNumber = Integer.parseInt(request.getParameter("index") == null ? "1" : request.getParameter("index"));
        int pageSize = 3;
        String sortBy = request.getParameter("sortBy") == null || request.getParameter("sortBy") == ""? " o.OrderID " : request.getParameter("sortBy");
        String orderDateFrom = request.getParameter("orderDateFrom") == null || request.getParameter("orderDateFrom") == "" ? "1990-01-01" : request.getParameter("orderDateFrom");
        String orderDateTo = request.getParameter("orderDateTo") == null || request.getParameter("orderDateTo") == "" ? "2099-09-09" : request.getParameter("orderDateTo");
        String saleName = request.getParameter("saleName") == null ? "" : request.getParameter("saleName");
        int status = Integer.parseInt(request.getParameter("status") == null || request.getParameter("status") == "" ? "-1" : request.getParameter("status"));
        String searchQuery = request.getParameter("searchQuery") == null ? "" : request.getParameter("searchQuery");

        OrderDAO orderDAO = new OrderDAO();

        int totalRecords = orderDAO.getPaginatedOrders(
                1, 999999, sortBy, orderDateFrom, orderDateTo, saleName, status, searchQuery).size();
        int totalPage = 0;
        if (totalRecords % 3 == 0) {
            totalPage = totalRecords / 3;
        } else {
            totalPage = totalRecords / 3 + 1;
        }
        List<OrderInfo> orderList = orderDAO.getPaginatedOrders(
                pageNumber, pageSize, sortBy, orderDateFrom, orderDateTo, saleName, status, searchQuery);

        request.setAttribute("orderList", orderList);
        request.setAttribute("totalPage", totalPage);

        request.getRequestDispatcher("orderlist.jsp").forward(request, response);
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
