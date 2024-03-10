/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.OrderDAO;
import dal.ProductListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Categories;

import model.Order;
import model.Paging;
import model.ProductList;

/**
 *
 * @author MM
 */
public class MyOrderController extends HttpServlet {

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
        Paging p = (Paging) request.getAttribute("p");
        List<Order> oList = (List<Order>) request.getAttribute("listo");
        if (oList.isEmpty()) {
            response.getWriter().print("No matching records found divi");
            return;
        }
        String a = "<ul class=\"order-list\">";
        for (int i = 0; i < oList.size(); i++) {
            a += String.format(
                    """
                    <li class="order-item-view item-main">
                                                    <div class="order-item">
                                                        <h3 class="order-item-id">%s</h3>
                                                        <div class="img-hover">
                                                            <img class="item-product-img" src="./ProductListImg/%s" alt="alt"/>
                                                        </div>
                    
                                                        <div class="order-item-product">
                                                            <div class="item-product-content">
                    
                                                                <h1 class="item-product-name">%s</h1>
                                                                <p class="item-product-cate">%s</p>
                                                            </div>
                    
                                                            <span class="item-product-price">%s$</span>
                                                            <span class="item-product-quantity">x%s</span>
                                                        </div>
                                                        
                    """, oList.get(i).getOrderID(),
                    oList.get(i).getProducts().get(0).getImage(),
                    oList.get(i).getProducts().get(0).getProductName(),
                    oList.get(i).getProducts().get(0).getCategory().getCateName(),
                    oList.get(i).getPrice().get(0),
                    oList.get(i).getQuantity().get(0)
            );
            if (oList.get(i).getStatus() == 3) {
                a += String.format(
                        """
                    <span class="order-item-status span-success">Completed</span>
                    """
                );
            } else if (oList.get(i).getStatus() == 4) {
                a += String.format(
                        """
                    <span class="order-item-status span-primary">Shipped</span>
                    """
                );
            } else if (oList.get(i).getStatus() == 5) {
                a += String.format(
                        """
                    <span class="order-item-status span-warning">Shipping</span>
                    """
                );
            }

            a += String.format("""
                               </div>
                               """);
            for (int b = 1; b < oList.get(i).getProducts().size(); b++) {
                a += String.format("""
                              <ul class="order-list-treeview">
                                                                  <li class="order-item-view">
                                                                      <div class="order-item">
                                                                          <h3 class="order-item-id">%s</h3>
                                                                          <div class="img-hover">
                                                                              <img class="item-product-img" src="./ProductListImg/%s" alt="alt"/>
                                                                          </div>
                              
                                                                          <div class="order-item-product">
                                                                              <div class="item-product-content">
                                                                                  <h1 class="item-product-name">%s</h1>
                                                                                  <p class="item-product-cate">%s</p>
                                                                              </div>
                                                                              <span class="item-product-price">%s$</span>
                                                                              <span class="item-product-quantity">x%s</span>
                                                                          </div>
                              
                              """, oList.get(i).getOrderID(),
                        oList.get(i).getProducts().get(b).getImage(),
                        oList.get(i).getProducts().get(b).getProductName(),
                        oList.get(i).getProducts().get(b).getCategory().getCateName(),
                        oList.get(i).getPrice().get(b),
                        oList.get(i).getQuantity().get(b)
                );
                if (oList.get(i).getStatus() == 3) {
                    a += String.format(
                            """
                    <span class="order-item-status span-success">
                                                            Completed
                                                        </span>
                    """
                    );
                }
                if (oList.get(i).getStatus() == 4) {
                    a += String.format(
                            """
                    <span class="order-item-status span-primary">
                                                                                                Shipped
                                                                                            </span>
                    """
                    );
                }
                if (oList.get(i).getStatus() == 5) {
                    a += String.format(
                            """
                    <span class="order-item-status span-warning">
                                                                                                Shipping
                                                                                            </span>
                    """
                    );
                }
                a += String.format("""
                             </div>
                                                                 </li>
                                                             </ul>
                             <a class="see-more" onclick="toggleTreeView(this)">See More</a>
                             """);
            }
            a += String.format("""
                             </li>
                             """);
        }
        a += String.format("""
                             </ul>
                             """);
        response.getWriter().write(a);

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
        AccountDAO dao = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc1 = (Account) session.getAttribute("acc");
        if (acc1 == null) {
            response.sendRedirect("LoginController");
            return;
        } else {

            Account acc = dao.getAccountById(acc1.getAccID());

            OrderDAO o = new OrderDAO();
            ProductListDAO daop = new ProductListDAO();
            List<Categories> listc = (ArrayList<Categories>) daop.getAllCate();
            List<ProductList> list3p = (ArrayList<ProductList>) daop.getNewest3Product();

            String sql = "select * from mydb.order where Status != 2 and AccID = " + acc1.getAccID();
            int size = o.countOrder(sql);
            int index;
            try {
                index = Integer.parseInt(request.getParameter("index"));
            } catch (Exception e) {
                index = 1;
            }

            Paging p = new Paging(3, index, size);
            p.calc();
            List<Order> listo = o.OrderPaging(index, acc.getAccID());
            request.setAttribute("newest3products", list3p);
            request.setAttribute("cateList", listc);
            request.setAttribute("p", p);
            request.setAttribute("listo", listo);
            request.getRequestDispatcher("MyOrder.jsp").forward(request, response);
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
        OrderDAO o = new OrderDAO();
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
            index = 1;
        }
        String sql = "SELECT * FROM mydb.order";
        int size = o.countOrder(sql);
        Paging p = new Paging(3, index, size);
        p.calc();
        //List<Order> listPaging = o.OrderPaging(index);
        request.setAttribute("p", p);
        request.setAttribute("index", index);
        //request.setAttribute("listo", listPaging);
        //PrintWriter out = response.getWriter();
        //out.println(listPaging);
        //processRequest(request, response);
        response.sendRedirect("myorder?index=" + index);
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
