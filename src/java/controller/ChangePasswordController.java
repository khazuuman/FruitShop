/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;


import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import model.Account;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author Thanh Hai
 */
public class ChangePasswordController extends HttpServlet {
   
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
            out.println("<title>Servlet ChangePasswordController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordController at " + request.getContextPath () + "</h1>");
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
        AccountDAO dao = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
//        Account acc = dao.getAccountById(1);
        request.setAttribute("acc", acc);
        
        request.getRequestDispatcher("newPassword.jsp").forward(request, response);
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
        String passOld = request.getParameter("passolder");
        String pass1 =request.getParameter("pass1");
        String pass2 =request.getParameter("pass2");
        String msg="";
        AccountDAO dao = new AccountDAO(); 
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        
//                Account acc = dao.getAccountById(1);
        String passVip =toSHA1(passOld);
   if (!passVip.equals(dao.CheckPassWord(acc.getEmail()))) {
    msg = "Please enter the correct old password!";
} else if (!pass1.equals(pass2)) {
    msg = "Passwords don't match each other";
}
    else if (pass1.equals(passOld)) {
    msg = " New Passwords duplicated old Password";
} else {
     String passVip1=toSHA1(pass1);
    dao.updatePassword(passVip1, acc.getEmail());
    msg = "Password change success";
}
         request.setAttribute("msg", msg);
        request.getRequestDispatcher("newPassword.jsp").forward(request, response);
    
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
     public static String toSHA1(String str) {
		String salt = "asjrlkmcoewgsdfgiogoidfjsstwj@tjle;oxqskjhdjksjf1jurVn";// Làm cho mật khẩu phức tap
		String result = null;
		
		str = str + salt;
		try {
			byte[] dataBytes = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
