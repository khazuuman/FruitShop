/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BlogDAO;
import dal.FeedbackDAO;
import dal.ProductDAO;
import dal.ProductListDAO;
import dal.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
import model.Categories;
import model.Feedback;
import model.Price;
import model.Product;
import model.Slider;

/**
 *
 * @author MM
 */
public class HomeController extends HttpServlet {

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
            out.println("<title>Servlet HomeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeController at " + request.getContextPath() + "</h1>");
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

        try {

            ProductListDAO daop = new ProductListDAO();
            SliderDAO slider = new SliderDAO();
            BlogDAO blog = new BlogDAO();
            ProductDAO product = new ProductDAO();
            FeedbackDAO feedback = new FeedbackDAO();
            List<Categories> listc = (ArrayList<Categories>) daop.getAllCate();
            List<Price> priceList = new ArrayList<>();
            List<Categories> cate = product.listHomeCategory();
            List<Product> pList = product.listBestProduct();
            for (int i = 0; i < pList.size(); i++) {
                priceList.add(product.getPriceByPiD(pList.get(i).getProductID()));
            }
            List<Blog> bList = blog.listNewBlog();
            List<Slider> sList = slider.getListSlider();
            List<Feedback> fList = feedback.listBestFeedback();
            request.setAttribute("cate", cate);
            request.setAttribute("Price", priceList);
            request.setAttribute("ListP", pList);
            request.setAttribute("bList", bList);
            request.setAttribute("sList", sList);
            request.setAttribute("fList", fList);
            request.setAttribute("cateList", listc);
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        } catch (IOException e) {
            System.out.println(e);
        }

        //out.println("123123");
        //request.getRequestDispatcher("Blog.jsp").forward(request, response);
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
