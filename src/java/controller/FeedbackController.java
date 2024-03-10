/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Account;
import model.Feedback;
import model.Paging;

/**
 *
 * @author MM
 */
public class FeedbackController extends HttpServlet {

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
        List<Feedback> list = (List<Feedback>) request.getAttribute("listFeedback");
        if (list.isEmpty()) {
            response.getWriter().print("No matching records found divi");
            return;
        }
        Paging p = (Paging) request.getAttribute("p");

        for (Feedback feedback : list) {
            String a = "<tr>";
            a += String.format(
                    """
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                    
                        """,
                    feedback.getFeedbackID(), feedback.getAccount().getUsername(), feedback.getAccount().getEmail(),
                    feedback.getAccount().getPhone(), feedback.getProduct().getProductName(), feedback.getContent()
            );

            if (feedback.getRating() == 5) {
                a += String.format(
                        """
                <td>
                <i class="fa-solid fa-star primary-color"></i>
                <i class="fa-solid fa-star primary-color"></i>
                <i class="fa-solid fa-star primary-color"></i>
                <i class="fa-solid fa-star primary-color"></i>
                <i class="fa-solid fa-star primary-color"></i>
                </td>
                """);
            }
            if (feedback.getRating() == 4) {
                a += String.format(
                        """
                <td>
                <i class="fa-solid fa-star primary-color"></i>
                                                                    <i class="fa-solid fa-star primary-color"></i>
                                                                    <i class="fa-solid fa-star primary-color"></i>
                                                                    <i class="fa-solid fa-star primary-color"></i>
                                                                    <i class="fa-regular fa-star primary-color"></i>
                </td>
                """);
            }
            if (feedback.getRating() == 3) {
                a += String.format(
                        """
                <td>
                <i class="fa-solid fa-star primary-color"></i>
                                                                    <i class="fa-solid fa-star primary-color"></i>
                                                                    <i class="fa-solid fa-star primary-color"></i>
                                                                    <i class="fa-regular fa-star primary-color"></i>
                                                                    <i class="fa-regular fa-star primary-color"></i>
                </td>
                """);
            }
            if (feedback.getRating() == 2) {
                a += String.format(
                        """
                <td>
                <i class="fa-solid fa-star primary-color"></i>
                                                                    <i class="fa-solid fa-star primary-color"></i>
                                                                    <i class="fa-regular fa-star primary-color"></i>
                                                                    <i class="fa-regular fa-star primary-color"></i>
                                                                    <i class="fa-regular fa-star primary-color"></i>
                </td>
                """);
            }
            if (feedback.getRating() == 1) {
                a += String.format(
                        """
                <td>
                <i class="fa-solid fa-star primary-color"></i>
                                                                    <i class="fa-regular fa-star primary-color"></i>
                                                                    <i class="fa-regular fa-star primary-color"></i>
                                                                    <i class="fa-regular fa-star primary-color"></i>
                                                                    <i class="fa-regular fa-star primary-color"></i>
                </td>
                """);
            }
            a += String.format("""
                               <td>
                               """);
            if (feedback.isIsActive()) {
                a += String.format("""
                                   <a class="" id="so1"><span class="label label-success">Show</span></a>
                                   <input type="hidden" name="status" id="statusInput" value="%s0"/>
                                   """,feedback.getFeedbackID());
                
            } else {
                a += String.format("""
                                   <a class="" id="so1"><span class="label label-danger">Hide</span></a>
                                   <input type="hidden" name="status" id="statusInput" value="%s1"/>
                                   """,feedback.getFeedbackID());
                
            }
            a += String.format("""
                               </td>
                               """);

            a += String.format("""
                              
                                                      <td>
                                                          <div class="action-btn">
                                                              <button class="btn-edit" onclick="window.location.href='/Fruitshop/feedbackdetail?id=%s'"><i class="fa-solid fa-info"></i></button>
                                                          </div>
                                                      </td>
                                                  </tr>
                              """,
                    feedback.getFeedbackID());

            response.getWriter().write(a);

        }

        String s = "divi\n<ul class=\"page-list\">\n";
        if (p.getIndex() > 1) {
            s += String.format("""
               <li class="onclick">
                      <p index="%s"><i class="fa-solid fa-angles-left"></i></p>
                    </li>
                    <li class="onclick">
                      <p index="%s"><i class="fa-solid fa-arrow-right fa-rotate-180"></i></p>
                    </li>
               """, 1, p.getIndex() - 1);
        }
        for (int i = p.getPageStart(); i <= p.getPageEnd(); i++) {
            s += "<li class='" + ((p.getIndex() == i) ? "notclick" : "onclick") + "'>\n"
                    + "                      <p index='" + i + "'>" + i + "</p>\n"
                    + "                    </li>";
        }
        if (p.getIndex() < p.getPageEnd()) {
            s += String.format("""
               <li class="onclick">
                      <p index="%s"><i class="fa-solid fa-arrow-right"></i></p>
                    </li>
                    <li class="onclick">
                      <p index="%s"><i class="fa-solid fa-angles-right"></i></p>
                    </li>
               """, p.getIndex() + 1, p.getTotalPage());
        }
        s += "<ul>";
        response.getWriter().write(s);
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
        String sql = "select * from feedback";
        FeedbackDAO f = new FeedbackDAO();
        int size = f.countFeedback(sql);
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
            index = 1;
        }
        Paging p = new Paging(3, index, size);
        p.calc();
        request.setAttribute("p", p);
        List<Feedback> list = f.getAllFeedback(index);
        request.setAttribute("listFeedback", list);
        request.getRequestDispatcher("FeedbackList.jsp").forward(request, response);
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
        
        
        FeedbackDAO f = new FeedbackDAO();
        
        try {
            int status = Integer.parseInt(request.getParameter("status").substring(request.getParameter("status").length()-1));
            int id = Integer.parseInt(request.getParameter("status").substring(0,request.getParameter("status").length()-1));
            if(status == 0){
                f.updateFeedback(id, 0);
            }
            if(status == 1){
                f.updateFeedback(id, 1);
            }
            
        } catch (Exception e) {
        }
        
        
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
            index = 1;
        }
        String sql = "Select * from (SELECT f.FeedbackID,f.AccID,f.ProductID,f.Rating,f.Content,p.ProductName,f.IsActive FROM mydb.feedback f join product p on f.ProductID = p.ProductID) a \n"
                + "join account b on a.AccID = b.AccID\n"
                + "where ";
        try {
            int star = Integer.parseInt(request.getParameter("star"));
            sql += "Rating = " + star + " and ";
        } catch (Exception e) {
        }
        try {
            int stt = Integer.parseInt(request.getParameter("stt"));
            sql += "IsActive = " + stt + " and ";
        } catch (Exception e) {
        }
        
        String txt = request.getParameter("txt") + "";
        if (request.getParameter("txt") == null || txt.isEmpty()) {
            sql += "1=1 ";
        } else {
            String[] field = {"Username", "Email", "Phone", "Content", "ProductName"};
            sql += " (";
            for (String string : field) {
                sql += string + " like CONCAT('%','" + txt + "','%') or ";
            }
            sql = sql.substring(0, sql.length() - 4) + ")";
        }
        String col = request.getParameter("col") + "";
        String or = request.getParameter("or") + "";
        if (!col.equals("null")) {
            sql += String.format(" order by %s %s ", col, or);
        }
        int size = f.countFeedback(sql);
        Paging p = new Paging(3, index, size);
        p.calc();
        request.setAttribute("p", p);
        List<Feedback> list = f.searchFeeback(sql, "", 0, index);
        request.setAttribute("sql", sql);
        request.setAttribute("listFeedback", list);
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
