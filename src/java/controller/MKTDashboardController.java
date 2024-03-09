/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;


import dal.BlogCommentDAO;
import dal.MarketingDashboradDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import model.BlogComment;
import model.Product;

/**
 *
 * @author Thanh Hai
 */
public class MKTDashboardController extends HttpServlet {
   
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
        MarketingDashboradDAO dao = new MarketingDashboradDAO();
        BlogCommentDAO blogCommentDAO = new BlogCommentDAO();
        List<BlogComment> blogComments = blogCommentDAO.get5RecentBlogCmt();
        ProductDAO pdao = new ProductDAO();
            int[] data = dao.countData();
            List<Product>listProduct=pdao.getRecentProduct();
            String startDate = request.getParameter("startDate");
    String endDate = request.getParameter("endDate");

    if (startDate == null && endDate == null) {
        // If startDate and endDate are not provided, set default values
        LocalDate today = LocalDate.now();
        startDate = today.toString();

        // Set endDate to 6 days after startDate
        LocalDate endDateObj = today.plusDays(6);
        endDate = endDateObj.toString();
    } else {
        // If startDate and endDate are provided, convert them to LocalDate objects
        LocalDate startDateObj = LocalDate.parse(startDate);
        LocalDate endDateObj = startDateObj.plusDays(6);
        endDate = endDateObj.toString();
    }

    Map<String, Integer> result = dao.countTrendCustomer2(startDate, endDate);
    request.setAttribute("startDate", startDate);
    request.setAttribute("endDate", endDate);
    request.setAttribute("result", result);
            
            request.setAttribute("data", data);
            request.setAttribute("Products", listProduct);
            request.setAttribute("blogComments", blogComments);
       request.getRequestDispatcher("MKTDashboard.jsp").forward(request, response);
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
       MarketingDashboradDAO dao = new MarketingDashboradDAO();
    BlogCommentDAO blogCommentDAO = new BlogCommentDAO();
    List<BlogComment> blogComments = blogCommentDAO.get5RecentBlogCmt();
    ProductDAO pdao = new ProductDAO();
    int[] data = dao.countData();
    List<Product> listProduct = pdao.getRecentProduct();

    request.setAttribute("data", data);
    request.setAttribute("Products", listProduct);
    request.setAttribute("blogComments", blogComments);

    String startDate = request.getParameter("startDate");
    String endDate = request.getParameter("endDate");

    if (startDate == null && endDate == null) {
        LocalDate today = LocalDate.now();
        startDate = today.toString();
        LocalDate endDateObj = today.plusDays(6);
        endDate = endDateObj.toString();
    } else {
        LocalDate startDateObj = LocalDate.parse(startDate);
        LocalDate endDateObj = startDateObj.plusDays(6);
        endDate = endDateObj.toString();
    }

    Map<String, Integer> result = dao.countTrendCustomer2(startDate, endDate);
    request.setAttribute("startDate", startDate);
    request.setAttribute("endDate", endDate);
    request.setAttribute("result", result);

    request.getRequestDispatcher("MKTDashboard.jsp").forward(request, response);


       
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
