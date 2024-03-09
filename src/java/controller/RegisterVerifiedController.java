/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.RegisterDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author lemti
 */
public class RegisterVerifiedController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String enteredOtp = request.getParameter("otp");
        HttpSession session1 = request.getSession();
        String actualOtp = (String) session1.getAttribute("otpActual");
        long otpTime = (Long) session1.getAttribute("otpTime");
        long currentTime = System.currentTimeMillis();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        if (!enteredOtp.equals(actualOtp)) {
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("pass", pass);
            request.setAttribute("userEmail", request.getParameter("userEmail"));
            // Set an error message and forward back to the OTP verification page
            request.setAttribute("error", "Incorrect OTP . Please try again.");
            request.getRequestDispatcher("RegisterVerified.jsp").forward(request, response);
        } else if (enteredOtp.equals(actualOtp) && (currentTime - otpTime > 60000)) {
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("pass", pass);
            request.setAttribute("userEmail", request.getParameter("userEmail"));
            // Set an error message and forward back to the OTP verification page
            request.setAttribute("error", "OTP expired. Please try again.");
            request.getRequestDispatcher("RegisterVerified.jsp").forward(request, response);
        } else if (enteredOtp.equals(actualOtp) && (currentTime - otpTime <= 60000)) {

            String passVip = toSHA1(pass);
            RegisterDAO regDao = new RegisterDAO();
            regDao.signUp(email, passVip, username);
            response.sendRedirect("success.jsp");

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
        processRequest(request, response);
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
