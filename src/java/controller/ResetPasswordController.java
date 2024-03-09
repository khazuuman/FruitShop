/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import static controller.ChangePasswordController.toSHA1;
import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author lemti
 */
public class ResetPasswordController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
   
        String email = request.getParameter("email");
        String token = request.getParameter("token");

       request.getRequestDispatcher("ResetPassword.jsp?email=" + email + "&token=" + token).forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String pass1 =request.getParameter("pass");
        String pass2 =request.getParameter("repass");
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        
        AccountDAO dao = new AccountDAO(); 

        String msg="";
   if (pass1.equals(pass2)) {
       String passVip1=toSHA1(pass1);
    dao.updatePassword(passVip1, email);
    msg = "Password change success";
   
}
     else {
      msg = "Passwords don't match each other";
}
         request.setAttribute("msg", msg);
//         request.getRequestDispatcher("ResetPassword.jsp?email=" + email + "&token=" + token).forward(request, response);
       request.getRequestDispatcher("ResetPassword.jsp?email=" + email + "&token=" + token).forward(request, response);
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
