/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductListDAO;
import dal.ProductListMKTDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Paging;
import model.ProductListMKT;

/**
 *
 * @author lemti
 */
public class ProductListMKTController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        response.getWriter().write(request.getAttribute("SQL")+"");
        List<ProductListMKT> list = (List<ProductListMKT>) request.getAttribute("listProduct");
        if (list.isEmpty()) {
            response.getWriter().print("No matching records found divi");
            return;
        }
        Paging p = (Paging) request.getAttribute("p");
        for (ProductListMKT product : list) {
            response.getWriter().println(String.format(
                    """
                    <tr>
                        <td>%s</td>
                        <td><img src='./img/%s' style= 'width:20%%'</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>
                            <div class="action-btn">
                                <button class="btn-edit" onclick="window.location.href='/Fruitshop/ProductDetailMKT?id=%s'"><i class="fa-solid fa-info"></i></button>
                            </div>
                        </td>
                    </tr>""",
                    product.getProductID(), product.getImage(), product.getProductName(),
                    product.getCategoryMKT().getCateName(), product.getPrice().getImportPrice(),product.getPrice().getRootPrice(), product.getPrice().getSellPrice(), product.getQuantity(),
                    product.getDescription(),
                    product.isIsActive() ? "Displayed":"Hided",product.getProductID()));
        }
        response.getWriter().write(p.toString());
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
        String sql = "SELECT * FROM product ;";
        ProductListMKTDAO productDAO = new ProductListMKTDAO();
        productDAO.setNrpp(3);
        int size = productDAO.CountProduct(sql);
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
            index = 1;
        }
        Paging p = new Paging(productDAO.getNrpp(), index, size);
        p.calc();
        request.setAttribute("p", p);
        List<ProductListMKT> Plist = productDAO.getAllProduct(index);
        request.setAttribute("listProduct", Plist);
        request.setAttribute("category", new ProductListMKTDAO().getAllCateMKT());
        request.getRequestDispatcher("ProductListMKT.jsp").forward(request, response);
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
        ProductListMKTDAO productDAO = new ProductListMKTDAO();
        productDAO.setNrpp(3);
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
            index = 1;
        }
        String sql = "SELECT * FROM product \n" +
"JOIN price ON product.ProductID = price.ProductID \n" +
"JOIN category ON product.CateID = category.CateID\n" +
"where ";
        try {
            int cateID = Integer.parseInt(request.getParameter("cate"));
            sql += "product.CateID = " + cateID + " and ";
        } catch (Exception e) {
        }

        try {
            int isActive = Integer.parseInt(request.getParameter("status"));
            sql += "product.IsActive = " + isActive + " and ";
            request.setAttribute("isActive", isActive);
        } catch (Exception e) {
        }
        String txt = request.getParameter("txt") + "";
        if (request.getParameter("txt") == null || txt.isEmpty()) {
            sql += "1=1 ";
        } else {
            String[] field = {"ProductName", "Description"};
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
        int size = productDAO.CountProduct(sql);

        Paging p = new Paging(productDAO.getNrpp(), index, size);
        p.calc();
        request.setAttribute("p", p);
        List<ProductListMKT> Plist = productDAO.searchProduct(sql, "", 0, index);
        request.setAttribute("sql", sql);
        request.setAttribute("listProduct", Plist);
        request.setAttribute("SQL", sql);
        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
