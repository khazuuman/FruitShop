/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Slider;

/**
 *
 * @author MM
 */
public class SliderList extends HttpServlet {

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
            out.println("<title>Servlet SliderList</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SliderList at " + request.getContextPath() + "</h1>");
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
        SliderDAO s = new SliderDAO();
        String status = request.getParameter("status");
        int indexPage;
        if(request.getParameter("index") == null){
            indexPage = 1;
        }else{
            indexPage = Integer.parseInt(request.getParameter("index"));
        }  
        String key = request.getParameter("searchSlider");
        int count = s.getTotalSlider(key,status);
        int endPage = count / 2;
        if (count % 2 != 0) {   
            endPage++;
        }
        request.setAttribute("addsuccess1", request.getParameter("add"));
        List<Slider> bpSlider = s.pagingSlider(indexPage,key,status);
        request.setAttribute("slider", bpSlider);
        request.setAttribute("endP", endPage);
        request.setAttribute("key", key);
        request.setAttribute("index", indexPage);
        request.setAttribute("sid", status);
        
        request.getRequestDispatcher("SliderList.jsp").forward(request, response);
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
        SliderDAO s = new SliderDAO();
        String img = request.getParameter("img");
        String content = request.getParameter("content");
        int status = Integer.parseInt(request.getParameter("status"));
        int pid = Integer.parseInt(request.getParameter("pid"));
        s.addSlider(3, pid, status, img, content);
        response.sendRedirect("sliderlist?add=Add new slider successful");
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
