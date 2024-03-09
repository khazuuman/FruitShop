/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;
import com.google.gson.Gson;

import dal.SaleDashboardDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SaleData;

/**
 *
 * @author lemti
 */
public class SalesTrendServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Khởi tạo SaleDataDAO
        SaleDashboardDAO saleDataDAO = new SaleDashboardDAO();

        // Lấy dữ liệu đơn hàng từ DAO
        LinkedHashMap<String, Integer> orderData = saleDataDAO.getOrderStats();
        LinkedHashMap<String, Float> revenue = saleDataDAO.getRevenue();
        
//         List<String> allDates = saleDataDAO.getAllDate(); 
//        for (String date : allDates) {
//           
//                orderData.putIfAbsent(date, 0);
//            
//        }
        
        // Tạo một đối tượng chứa cả hai dữ liệu order và revenue
        Map<String, Object> data = new HashMap<>();
        data.put("orderData", orderData);
        data.put("revenue", revenue);    
        // Serialize data to JSON
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
                out.print(new Gson().toJson(data));

        
        out.flush();
    }
    public static void main(String[] args) {
                SaleDashboardDAO sd = new SaleDashboardDAO();
                LinkedHashMap<String, Integer> date = sd.getOrderStats();
                System.out.println(date);

    }
    
}
