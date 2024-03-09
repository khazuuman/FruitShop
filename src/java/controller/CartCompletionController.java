/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartCompletionDAO;
import dal.CartDetailDAO;
import dal.ProductListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.ProductList;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Categories;

/**
 *
 * @author ADMIN
 */
public class CartCompletionController extends HttpServlet {

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
        PrintWriter out = response.getWriter();

        HttpSession ss = request.getSession();
        Account acc = (Account) ss.getAttribute("acc");
        ProductListDAO daop = new ProductListDAO();
        CartDetailDAO daoc = new CartDetailDAO();
        CartCompletionDAO daocc = new CartCompletionDAO();
        CartDetailDAO daocd = new CartDetailDAO();

        List<Categories> listc = (ArrayList<Categories>) daop.getAllCate();
        List<ProductList> list3p = (ArrayList<ProductList>) daop.getNewest3Product();


        int OrderID = daocd.orderIdStatus2(acc.getAccID());
        List<ProductList> listp = (ArrayList<ProductList>) daoc.getProductListCart(acc.getAccID());
        for (ProductList l : listp) {
            int inventory = daocc.getProductQty(l.getProductID());
            daocc.updateProductQuantity(l.getProductID(), inventory - l.getQuantity());
        }
        daocc.updateOrderStatus(acc.getAccID());
        List<ProductList> listo = (ArrayList<ProductList>) daocc.getProductOrder(OrderID);

        String productHtml = "";
        float totalAmount = 0;
        for (ProductList lo : listo) {
            totalAmount += lo.getMainPrice() * lo.getQuantity();
            productHtml += "        <tr>\n"
                    + "            <td>" + lo.getProductName() + "</td>\n"
                    + "            <td>" + lo.getQuantity() + "</td>\n"
                    + "        </tr>\n";
        }
        String htmlContent = "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <style>\n"
                + "        table {\n"
                + "            font-family: arial, sans-serif;\n"
                + "            border-collapse: collapse;\n"
                + "            width: 100%;\n"
                + "        }\n"
                + "\n"
                + "        td,\n"
                + "        th {\n"
                + "            border: 1px solid #dddddd;\n"
                + "            text-align: left;\n"
                + "            padding: 8px;\n"
                + "        }\n"
                + "\n"
                + "        tr:nth-child(even) {\n"
                + "            background-color: #dddddd;\n"
                + "        }\n"
                + "    </style>\n"
                + "    <span style=\"font-size: 20px;\">We are <a href=\"\">Organic Store</a>. Thank you for trusting and purchasing our products.</span>\n"
                + "    <h2>Customer information:</h2>\n"
                + "    <table>\n"
                + "        <tr>\n"
                + "            <th>Name</th>\n"
                + "            <th>Phone number</th>\n"
                + "            <th>Delivery address</th>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>" + acc.getUsername() + "</td>\n"
                + "            <td>" + acc.getPhone() + "</td>\n"
                + "            <td>" + acc.getAddress() + "</td>\n"
                + "        </tr>\n"
                + "    </table>\n"
                + "    <h2>Payment method: payment upon receipt</h2>\n"
                + "    <h2>Information line:</h2>\n"
                + "    <table>\n"
                + "        <tr>\n"
                + "            <th>Product</th>\n"
                + "            <th>Quantity</th>\n"
                + "        </tr>\n"
                + productHtml
                + "    </table>\n"
                + "    <h3>Total amout: " + totalAmount + "</h3>\n"
                + "    <h4>Staff will call you back soon to confirm your order. If you want more details, call 0335982700 for advice.</h4>\n"
                + "    <h4>If you are interested in our products, please click <a href=\"http://localhost:8080/Fruitshop/\">here</a></h4>\n"
                + "</body>\n"
                + "\n"
                + "</html>";
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
        final String to = acc.getEmail();
        //tao 1 tin nhan
        MimeMessage msg = new MimeMessage(session);
        try {
            //nguoi gui
            msg.setFrom(from);
            //nguoi nhan
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            //tieu de email
            msg.setSubject("Order confirmation notification");
            //Quy dinh ngay gui
            msg.setSentDate(new Date());
            //Quy dinh email nhan phan hoi
            msg.setContent(htmlContent, "text/html; charset=utf-8");

            //gui email
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("new3Product", list3p);
        request.setAttribute("cateList", listc);
        request.getRequestDispatcher("CartCompletion.jsp").forward(request, response);
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
