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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
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
public class ForgotPasswordController extends HttpServlet {

    // Lưu trữ mã OTP và thời gian hết hạn của nó
     private static final Map<String, Long> resetTokenMap = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        RegisterDAO regDao = new RegisterDAO();
        Account a = regDao.checkAccountExist(email);
        // Tạo mã token ngẫu nhiên
        if(a!=null){
        String token = UUID.randomUUID().toString();

        // Thiết lập thời gian hết hạn cho token (ví dụ: 30 phút)
        long expirationTime = System.currentTimeMillis() + (5 * 60 * 1000); // Thời gian hiện tại + 30 phút
        resetTokenMap.put(token, expirationTime);

        // Gửi  email
        sendOTPEmail(email, token);

        // Chuyển hướng người dùng đến trang thông báo
        response.sendRedirect("success.jsp");
        } else {
            request.setAttribute("error", "Email has not been existed");
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
        }
    }

   

    private void sendOTPEmail(String email, String token) {
        // Gửi email chứa mã OTP
//        String otp = generateOTP();

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
        //tao 1 tin nhan
        MimeMessage msg = new MimeMessage(session);
        try {
            //kieu noi dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            //nguoi gui
            msg.setFrom(from);
            //nguoi nhan
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //tieu de email
            msg.setSubject("Password Reset Instructions");
            //Quy dinh ngay gui
            msg.setSentDate(new Date());
            // Nội dung email chứa liên kết đặt lại mật khẩu với mã token
            String encodedEmail = java.net.URLEncoder.encode(email, "UTF-8");

            String resetLink = "http://localhost:9999/Fruitshop/ResetPasswordController?email=" + encodedEmail + "&token=" + token;
            msg.setText("Please click on the following link to reset your password: " + resetLink);

            //gui email
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   // Phương thức để kiểm tra xem mã token có hợp lệ không
    public static boolean isTokenValid(String token) {
        Long expirationTime = resetTokenMap.get(token);
        return expirationTime != null && System.currentTimeMillis() < expirationTime;
    }

}
