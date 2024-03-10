/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccDAO;
import dal.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import model.Admin;



/**
 *
 * @author MM
 */
public class AdminDashboardController extends HttpServlet {

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
        AccDAO acc = new AccDAO();
        AdminDAO a = new AdminDAO();
        int shipping = a.countOrder(5);
        int shipped = a.countOrder(4);
        int Cancelled = a.countOrder(9);
        int totalcus = a.totalCus();
        double totalIncom1 = a.totalIncome();
        double avg1 = a.getAVG();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String acc1 = acc.getAccByID(a.getIdAccNew()).getUsername();
        String acc2 = acc.getAccByID(a.getIdOrderNew()).getUsername();
        String totalIncom = String.format("%.2f", totalIncom1);
        String avg = String.format("%.2f", avg1);
        List<Admin> admin = a.BestProduct();
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

        Map<String, Integer> result = a.countTrendCustomer2(startDate, endDate);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("result", result);
        request.setAttribute("admin", admin);
        request.setAttribute("shipping", shipping);
        request.setAttribute("shipped", shipped);
        request.setAttribute("Cancelled", Cancelled);
        request.setAttribute("totalcus", totalcus);
        request.setAttribute("totalIncom", totalIncom);
        request.setAttribute("avg", avg);
        request.setAttribute("acc1", acc1);
        request.setAttribute("acc2", acc2);
        request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
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
        AccDAO acc = new AccDAO();
        AdminDAO a = new AdminDAO();
        int shipping = a.countOrder(5);
        int shipped = a.countOrder(4);
        int Cancelled = a.countOrder(12);
        int totalcus = a.totalCus();
        double totalIncom1 = a.totalIncome();
        double avg1 = a.getAVG();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String acc1 = acc.getAccByID(a.getIdAccNew()).getUsername();
        String acc2 = acc.getAccByID(a.getIdOrderNew()).getUsername();
        String totalIncom = String.format("%.2f", totalIncom1);
        String avg = String.format("%.2f", avg1);
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

        Map<String, Integer> result = a.countTrendCustomer2(startDate, endDate);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("result", result);
        request.setAttribute("shipping", shipping);
        request.setAttribute("shipped", shipped);
        request.setAttribute("Cancelled", Cancelled);
        request.setAttribute("totalcus", totalcus);
        request.setAttribute("totalIncom", totalIncom);
        request.setAttribute("avg", avg);
        request.setAttribute("acc1", acc1);
        request.setAttribute("acc2", acc2);
        request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
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
