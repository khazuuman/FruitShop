/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ProductListMKTDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductListMKT;

/**
 *
 * @author lemti
 */
public class ProductListMKTAdd extends HttpServlet {
   
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
            out.println("<title>Servlet ProductListMKTAdd</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductListMKTAdd at " + request.getContextPath () + "</h1>");
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
                ProductListMKTDAO pdao = new ProductListMKTDAO();

        request.setAttribute("category", pdao.getAllCateMKT());
        request.getRequestDispatcher("ProductListMKTAdd.jsp").forward(request, response);


    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ProductListMKTDAO pdao = new ProductListMKTDAO();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int cateId = Integer.parseInt(request.getParameter("category"));
        String image = request.getParameter("img");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Float importPrice = Float.parseFloat(request.getParameter("ip"));
        Float rootPrice = Float.parseFloat(request.getParameter("rp"));
        Float sellPrice = Float.parseFloat(request.getParameter("sp"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        try {
            pdao.insertProduct(name, description, cateId, image, status, importPrice, rootPrice, sellPrice, quantity, time);
        } catch (Exception ex) {
            Logger.getLogger(ProductListMKTAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("ProductListMKT");
        
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

