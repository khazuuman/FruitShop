/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.*;

/**
 *
 * @author vinhp
 */
public class CustomerListController extends HttpServlet {
   
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
        List<Account> list = (List<Account>) request.getAttribute("listUser");
        if (list.isEmpty()){
            response.getWriter().print("No matching records found divi");
            return;
        }
        Paging p = (Paging) request.getAttribute("p");
        for (Account acc : list) {
            response.getWriter().println(String.format(
                    """
                    <tr>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>
                            <div class="action-btn">
                                <button class="btn-edit" onclick="window.location.href='/Fruitshop/customerdetail?id=%s'"><i class="fa-solid fa-info"></i></button>
                            </div>
                        </td>
                    </tr>""",
                    acc.getAccID(), acc.getUsername(), acc.isGender() ? "Male" : "Female",
                    acc.getEmail(), acc.getPhone(),
                    acc.getStatus().getSttName(), acc.getAccID()));
        }
        String s = "divi\n<ul class=\"page-list\">\n";
        if (p.getIndex()>1){
            s+=String.format("""
               <li class="onclick">
                      <p index="%s"><i class="fa-solid fa-angles-left"></i></p>
                    </li>
                    <li class="onclick">
                      <p index="%s"><i class="fa-solid fa-arrow-right fa-rotate-180"></i></p>
                    </li>
               """, 1, p.getIndex()-1);
        }
        for (int i = p.getPageStart(); i <= p.getPageEnd(); i++) {
            s+="<li class='"+((p.getIndex() == i)?"notclick":"onclick")+"'>\n" +
"                      <p index='"+i+"'>"+i+"</p>\n" +
"                    </li>";
        }
        if (p.getIndex()<p.getPageEnd()){
            s+=String.format("""
               <li class="onclick">
                      <p index="%s"><i class="fa-solid fa-arrow-right"></i></p>
                    </li>
                    <li class="onclick">
                      <p index="%s"><i class="fa-solid fa-angles-right"></i></p>
                    </li>
               """, p.getIndex()+1, p.getTotalPage());
        }
        s+="<ul>";
        response.getWriter().write(s);
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
        String sql = "select * from account where RoleID=2";
        AccDAO accDAO = new AccDAO();
        accDAO.setNrpp(3);
        int size = accDAO.CountAcc(sql);
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
            index = 1;
        }
        Paging p = new Paging(accDAO.getNrpp(), index, size);
        p.calc();
        request.setAttribute("p", p);
        List<Account> list = accDAO.searchAcc(sql, "", 0, index);
        request.setAttribute("listUser", list);
        request.setAttribute("sttList", new StatusDAO().getAccStatus());
        request.getRequestDispatcher("customerList.jsp").forward(request, response);
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
        AccDAO accDAO = new AccDAO();
        accDAO.setNrpp(3);
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
            index = 1;
        }
        String sql = "select * from account where RoleID=2 and ";
        try {
            int stt = Integer.parseInt(request.getParameter("stt"));
            sql += "Status = " + stt + " and ";
        } catch (Exception e) {
        }
        String txt = request.getParameter("txt") + "";
        if (request.getParameter("txt") == null || txt.isEmpty()) {
            sql += "1=1 ";
        } else {
            String[] field = {"Username", "Email", "Phone"};
            sql += " (";
            for (String string : field) {
                sql += string + " like CONCAT('%','" + txt + "','%') or ";
            }
            sql = sql.substring(0, sql.length() - 4) + ")";
        }
        
        String col = request.getParameter("col")+"";
        String or = request.getParameter("or")+"";
        if (!col.equals("null")){
            sql += String.format(" order by %s %s ", col, or);
        }
        int size = accDAO.CountAcc(sql);
        Paging p = new Paging(accDAO.getNrpp(), index, size);
        p.calc();
        request.setAttribute("p", p);
        List<Account> list = accDAO.searchAcc(sql, "", 0, index);
        request.setAttribute("sql", sql);
        request.setAttribute("listUser", list);
        processRequest(request, response);
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
