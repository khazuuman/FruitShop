/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import java.sql.Connection;
import java.sql.DriverManager;


public class DBContext {
    protected Connection connection;

    public DBContext() {
    }

    public Connection getConnection()throws Exception  {
            long time = System.nanoTime();
            // Change the username, password, and URL to connect to your own database
            String username = "root";
            String password = "vinh0205";
            String url = "jdbc:mysql://localhost:3306/mydb";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.printf("connect: %d\n",System.nanoTime()-time);
            return connection;
        
    }
     public static void main(String[] args) {
        try {
            DBContext dbContext = new DBContext();
            Connection connection = dbContext.getConnection();

            if (connection != null) {
                System.out.println("Connected to the database");
                // Thực hiện các thao tác khác tại đây nếu cần
            } else {
                System.out.println("Failed to connect to the database");
            }

            // Đóng kết nối sau khi sử dụng
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
