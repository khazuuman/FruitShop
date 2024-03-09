/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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

/**
 *
 * @author nguye
 */
public class ProductListController extends HttpServlet {

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
        try {
            ProductListDAO daop = new ProductListDAO();
            String indexStr = request.getParameter("index");
            String cate = request.getParameter("cate");
            String sort = request.getParameter("sort");
            String minPrice = request.getParameter("minPrice");
            String maxPrice = request.getParameter("maxPrice");
            String search = request.getParameter("search");
            String rating = request.getParameter("rating");

            search = (search != null && search.isEmpty()) ? null : search;
            cate = (cate != null && cate.isEmpty()) ? null : cate;
            minPrice = (minPrice != null && minPrice.isEmpty()) ? null : minPrice;
            maxPrice = (maxPrice != null && maxPrice.isEmpty()) ? null : maxPrice;
            rating = (rating != null && rating.isEmpty()) ? null : rating;
            sort = (sort != null && sort.isEmpty()) ? null : sort;

//            out.print(indexStr);
//            out.print(cate);
//            out.print(sort);
//            out.print(maxPrice);
//            out.print(minPrice);
//            out.print(search);
//            out.print(rating);

            int index = 1;
            if (indexStr != null) {
                index = Integer.parseInt(indexStr);
            }

            List<Categories> listc = (ArrayList<Categories>) daop.getAllCate();
            List<ProductList> list3p = (ArrayList<ProductList>) daop.getNewest3Product();
            List<ProductList> listp = (ArrayList<ProductList>) daop.getAllProduct(search, cate, rating, minPrice, maxPrice, sort, index);
            int productCount = daop.countProduct(search, cate, rating, minPrice, maxPrice);

            int pageCount = 0;
            if (productCount <= 9) {
                pageCount = 1;
            } else if (productCount % 9 != 0) {
                pageCount = (productCount / 9) + 1;
            } else {
                pageCount = productCount / 9;
            }

            request.setAttribute("productCount", productCount);
            request.setAttribute("page", pageCount);
            request.setAttribute("index", index);
            request.setAttribute("cate", cate);
            request.setAttribute("sort", sort);
            request.setAttribute("minPrice", minPrice);
            request.setAttribute("maxPrice", maxPrice);
            request.setAttribute("rating", rating);
            request.setAttribute("search", search);
            request.setAttribute("newest3products", list3p);
            request.setAttribute("products", listp);
            request.setAttribute("cateList", listc);
            request.getRequestDispatcher("ProductList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
