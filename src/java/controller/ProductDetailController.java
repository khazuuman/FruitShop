/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDetailDAO;
import dal.ProductListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Categories;
import model.ProductList;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author ADMIN
 */
public class ProductDetailController extends HttpServlet {

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
        ProductDetailDAO daoPd = new ProductDetailDAO();
        int ProductId = Integer.parseInt(request.getParameter("productId"));

        if (acc != null) {
            int QtyLimit = daoPd.getProductQtyCart(ProductId, acc.getAccID());
            request.setAttribute("QtyLimit", QtyLimit);
        }
        try {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String action = request.getParameter("action");
            String SalepriceStr = request.getParameter("Saleprice");
            String RootpriceStr = request.getParameter("Rootprice");
            if (action.equals("addCart")) {
                int OrderID = daoPd.getOrderId(acc.getAccID());
                if (OrderID == 0) {
                    OrderID = daoPd.createOrderId();
                    daoPd.insertToOrder(acc.getAccID(), OrderID);
                }
                boolean exist = false;
                int totalQtyInput = 0;
                List<ProductList> cartList = daoPd.getProductCartList(OrderID);
                for (ProductList pl : cartList) {
                    if (ProductId == pl.getProductID()) {
                        exist = true;
                        totalQtyInput = quantity + pl.getQuantity();
                    }
                }
                if (exist) {
                    daoPd.updateProductCartQuantity(OrderID, ProductId, totalQtyInput);
                } else {
                    if (SalepriceStr != null) {
                        float Saleprice = Float.parseFloat(SalepriceStr);
                        daoPd.insertToCart(OrderID, ProductId, quantity, Saleprice);
                    } else {
                        float Rootprice = Float.parseFloat(RootpriceStr);
                        daoPd.insertToCart(OrderID, ProductId, quantity, Rootprice);
                    }
                }
            }
            if (action.equals("buyNow")) {
                response.sendRedirect("#");
            }
            response.sendRedirect("ProductDetailController?productId="+ProductId);
            return;
        } catch (Exception e) {
        }

        ProductList p = daoPd.getProductDetail(ProductId);
        List<Categories> listc = (ArrayList<Categories>) daop.getAllCate();
        List<ProductList> list3p = (ArrayList<ProductList>) daop.getNewest3Product();

        request.setAttribute("newest3products", list3p);
        request.setAttribute("cateList", listc);
        request.setAttribute("productDetail", p);
        request.getRequestDispatcher("ProductDetail.jsp").forward(request, response);
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
