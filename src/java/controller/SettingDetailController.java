/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.*;
import model.*;

/**
 *
 * @author vinhp
 */
public class SettingDetailController extends HttpServlet {
   
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
            out.println("<title>Servlet SettingDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingDetailController at " + request.getContextPath () + "</h1>");
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
        SettingDAO setdao = new SettingDAO();
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            id = 0;
        }
        request.setAttribute("typeList", setdao.listType);
        request.setAttribute("id", id);
        if (id == 0) {
            request.setAttribute("hd", "hidden");
            request.getRequestDispatcher("SettingDetail.jsp").forward(request, response);
        }
        //view
        request.setAttribute("d", "disabled");
        Setting set = setdao.getSettingByID(id);
        request.setAttribute("set", set);
        request.getRequestDispatcher("SettingDetail.jsp").forward(request, response);
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
        SettingDAO setdao = new SettingDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");       
        String value = request.getParameter("value");
        String des = request.getParameter("dec");
        int or = Integer.parseInt(request.getParameter("order"));
        int stt = Integer.parseInt(request.getParameter("stt")==null?"0":"1");
        
        if (id == 0) {
            int type = Integer.parseInt(request.getParameter("type"));
            setdao.insertSetting(type, or, stt, name, value, des);
        } else {
            setdao.updateSetting(id, or, stt, name, value, des);
        }
        response.sendRedirect("adsettinglist");
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
