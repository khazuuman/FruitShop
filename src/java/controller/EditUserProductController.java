/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;


/**
 *
 * @author Thanh Hai
 */
public class EditUserProductController extends HttpServlet {

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
            out.println("<title>Servlet EditUserProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUserProductController at " + request.getContextPath() + "</h1>");
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
        AccountDAO dao = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc1 = (Account) session.getAttribute("acc");
        Account acc = dao.getAccountById(acc1.getAccID());
        request.setAttribute("acc", acc);
        request.getRequestDispatcher("EditUserProfile.jsp").forward(request, response);
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
        ChangeHistoryDAO chd = new ChangeHistoryDAO();
        String name = request.getParameter("name");
        String img = request.getParameter("img");
        String phone = request.getParameter("phone");
        Boolean gender = Boolean.valueOf(request.getParameter("gender"));
        String address = request.getParameter("address");
        AccountDAO dao = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc1 = (Account) session.getAttribute("acc");
        Account acc = dao.getAccountById(acc1.getAccID());
        
        if (img == null || img.isEmpty()) {
            img = acc.getAccImg();
        }
        
        dao.UpdateAccount(acc, name, img, gender, phone,address);
        chd.changeHistory(acc, acc.getRole().getRoleID(), acc.getAddress(), name, img, phone, acc.getStatus().getSttID(), acc.isGender(), acc.getEmail());
        
      
        response.sendRedirect("userprofile");
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
