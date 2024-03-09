/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.*;

/**
 *
 * @author vinhp
 */
@WebServlet(name = "UserListProfileController", urlPatterns = {"/userdetail"})
public class UserListProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
        HttpSession ses = request.getSession();
        AccDAO accDAO = new AccDAO();
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            id = 0;
        }
        request.setAttribute("id", id);
        int a = accDAO.CountAcc("select * from account where RoleID = 1");
        if (a == 1) {
            request.setAttribute("ad", "disabled");
        }
        if (id == 1) {
            request.setAttribute("v", "disabled");
        }
        //add
        if (id == 0) {
            request.setAttribute("roleList", new RoleDAO().getAllRole());
            request.setAttribute("sttList", new StatusDAO().getAccStatus());
            request.setAttribute("hd", "hidden");
            request.getRequestDispatcher("userlistprofile.jsp").forward(request, response);
        }
        //view
        request.setAttribute("d", "disabled");
        String sql = "select * from account where AccID = " + id;
        accDAO.setNrpp(1);
        Account acc = (Account) accDAO.searchAcc(sql, "", 0, 1).get(0);
        request.setAttribute("acc", acc);
        request.setAttribute("roleList", new RoleDAO().getAllRole());
        request.setAttribute("sttList", new StatusDAO().getAccStatus());
        request.getRequestDispatcher("userlistprofile.jsp").forward(request, response);
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
        AccDAO accDAO = new AccDAO();
        ChangeHistoryDAO chd = new ChangeHistoryDAO();
        int id, role, stt;

        try {
            id = Integer.parseInt(request.getParameter("id") + "");
        } catch (Exception e) {
            id = 0;
        }
        if (id == 0) {
            role = Integer.parseInt(request.getParameter("role"));
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            stt = Integer.parseInt(request.getParameter("stt") + "");
            boolean gender = Boolean.parseBoolean(request.getParameter("gen"));
            String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

            if (accDAO.checkExist(email)) {
                Account newacc = new Account(0, new Role(role), new Status(stt), gender, "", name, "", "", phone,address, null);
                request.setAttribute("hd", "hidden");
                request.setAttribute("acc", newacc);
                request.setAttribute("roleList", new RoleDAO().getAllRole());
                request.setAttribute("sttList", new StatusDAO().getAccStatus());
                request.setAttribute("msg", "Email has already exist");
                request.getRequestDispatcher("userlistprofile.jsp").forward(request, response);
            }
            String pass = accDAO.toSHA1(accDAO.passGenerator());
            int newid = accDAO.insertAcc(role, email, pass, name, null, phone, stt, gender, time);
            chd.createAcc(newid, "vinhnthe172280@fpt.edu.vn", time);

        } else {
            Account acc = accDAO.getAccByID(id);
            role = Integer.parseInt(request.getParameter("role"));
            stt = Integer.parseInt(request.getParameter("stt"));
            accDAO.updateAcc(role, stt, id);
            chd.changeHistory(acc, role,acc.getAddress(), acc.getUsername(), acc.getAccImg(), acc.getPhone(), stt, acc.isGender(), "vinhnthe172280@fpt.edu.vn");
        }
        response.sendRedirect("userlist");
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
