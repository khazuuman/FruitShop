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
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Account;

/**
 *
 * @author lemti
 */
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String rePass = request.getParameter("repass");

        RegisterDAO regDao = new RegisterDAO();
        Account a = regDao.checkAccountExist(email);
        if (a == null) {
            if (password.equals(rePass)) {
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.setAttribute("pass", password);
                String maskedEmail = email.substring(0, 3) + "**********@" + email.split("@")[1];
                request.setAttribute("userEmail", maskedEmail);
                String otp = generateOTP();

                final String from = "quaithugaoru@gmail.com";
//        final String password = "abcd auht ?ids jicx";
                final String passwordv = "oqtt qcwz fqsx lnug";

                //properties khai bao thuoc tinh
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");//smtp host
                props.put("mail.smtp.port", "587");//TLS 587 SSL 465
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                //create Authenticator
                Authenticator auth = new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, passwordv);
                    }

                };
                //phien lam viec 
                Session session = Session.getInstance(props, auth);

                //gui email
                final String to = request.getParameter("email");
                //tao 1 tin nhan
                MimeMessage msg = new MimeMessage(session);
                try {
                    //kieu noi dung
                    msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                    //nguoi gui
                    msg.setFrom(from);
                    //nguoi nhan
                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
                    //tieu de email
                    msg.setSubject("test mail");
                    //Quy dinh ngay gui
                    msg.setSentDate(new Date());
                    //Quy dinh email nhan phan hoi

                    msg.setText("Your OTP for email verification is: " + otp);

                    //gui email
                    Transport.send(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                HttpSession session1 = request.getSession();
                session1.setAttribute("otpActual", otp);
                long currentTimeMillis = System.currentTimeMillis();
                session1.setAttribute("otpTime", currentTimeMillis);

                request.getRequestDispatcher("RegisterVerified.jsp").forward(request, response);
            } else {
                request.setAttribute("mess1", "wrong password. Enter again");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("mess2", "email has been existed");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
    }

// Method to generate a 6-digit random OTP
    private String generateOTP() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
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
