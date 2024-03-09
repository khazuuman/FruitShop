/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.*;

/**
 *
 * @author vinhp
 */
public class SettingListController extends HttpServlet {
   
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
        //response.getWriter().write(request.getAttribute("sql")+"");
        List<Setting> list = (List<Setting>) request.getAttribute("setList");
        if (list.isEmpty()){
            response.getWriter().print("No matching records found divi");
            return;
        }
        Paging p = (Paging) request.getAttribute("p");
        for (Setting set : list) {
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
                              <button class="%s btn-stt" data-id="%s" data-act="chg" data-stt="%s">
                                <i class="%s"></i></button>
                              <button class="btn-edit"
                                      onclick="window.location.href = '/Fruitshop/adsettingdetail?id=%s'"><i
                                  class="fa-solid fa-info"></i></button>
                            </div>
                        </td>
                    </tr>
                    """,set.getSettingID(), set.getType().getTypeName(), set.getName(),
                    set.getValue(), set.getOrder(), (set.isIsActive()?"Active":"Inactive"),
                    set.isIsActive()?"off":"on",set.getSettingID(),set.isIsActive()?"1":"0",
                    set.isIsActive()?"fa-solid fa-ban":"fa-solid fa-power-off",set.getSettingID()
                    ));
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sql = "select * from setting";
        SettingDAO setdao = new SettingDAO();
        setdao.nrpp=3;
        int size = setdao.countSetting(sql);
        Paging p = new Paging(setdao.nrpp, 1, size);
        p.calc();
        request.setAttribute("p", p);
        List<Account> list = setdao.getAllSetting(1);
        request.setAttribute("setList", list);
        request.setAttribute("typeList", setdao.listType);
        request.getRequestDispatcher("SettingList.jsp").forward(request, response);
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
        SettingDAO setdao = new SettingDAO();
        setdao.nrpp=3;
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
            index = 1;
        }
        if ((request.getParameter("act")+"").equals("chg")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean stt = !(request.getParameter("st").equals("1"));
                setdao.changeStt(id, stt);
                Setting set = setdao.getSettingByID(id);
                setdao.changeSetting(set.getType().getTypeName(), stt, set.getValue());
            } catch (Exception e) {}
        }
        
        String sql = "select * from setting where ";
        try {sql += "TypeID = " + Integer.valueOf(request.getParameter("type")) + " and ";} catch (Exception e) {}
        try {sql += "IsActive = " + Integer.valueOf(request.getParameter("stt")) + " and ";} catch (Exception e) {}
        String txt = request.getParameter("txt") + "";
        if (request.getParameter("txt") == null || txt.isEmpty()) sql += " 1=1 ";
        else sql += "(`Value` like CONCAT('%','" + txt + "','%'))";     
        String col = request.getParameter("col")+"";
        String or = request.getParameter("or")+"";
        if (!col.equals("null")){
            sql += String.format(" order by %s %s ", col, or);
        }
        int size = setdao.countSetting(sql);
        Paging p = new Paging(setdao.nrpp, index, size);
        p.calc();
        request.setAttribute("p", p);
        List<Setting> list = setdao.searchSetting(sql, index);
        request.setAttribute("sql", sql);
        request.setAttribute("setList", list);
        processRequest(request, response);
    }

}
