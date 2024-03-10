/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author MM
 */
public class MailFeedback extends HttpServlet {

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
            out.println("<title>Servlet MailFeedback</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MailFeedback at " + request.getContextPath() + "</h1>");
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
                    msg.setSubject("Feedback Mail: ");
                    //Quy dinh ngay gui
                    msg.setSentDate(new Date());
                    //Quy dinh email nhan phan hoi

                    msg.setText("Your OTP for email verification is: ");

                    //gui email
                    Transport.send(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
