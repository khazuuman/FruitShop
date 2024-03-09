<%-- 
    Document   : InvalidToken
    Created on : Feb 16, 2024, 3:21:23 PM
    Author     : lemti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Expired Link</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
         background-size: cover;
        background-repeat: no-repeat;
        background-image: url("https://chichchoedesign.com/wp-content/uploads/2023/04/thiet-ke-cua-hang-hoa-qua-tron-goi-min.jpeg");
    }
    
    .message {
        background-color: #f8d7da; /* Màu đỏ nhạt */
        border: 1px solid #dc3545; /* Màu viền */
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
    }
    
    .message h2 {
        margin-top: 0;
        color: #dc3545; /* Màu chữ đỏ */
    }
    
    .message p {
        margin-bottom: 20px;
    }
    
    .btn {
        background-color: #dc3545; /* Màu đỏ */
        color: #fff;
        border: none;
        padding: 10px 20px;
        text-transform: uppercase;
        border-radius: 3px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    
    .btn:hover {
        background-color: #c82333; /* Màu đỏ đậm khi hover */
    }

    .icon {
        font-size: 36px;
        margin-bottom: 20px;
        color: #dc3545; /* Màu icon đỏ */
    }
</style>
</head>
<body>

<div class="message">
    <i class="icon fas fa-exclamation-triangle"></i> <!-- Icon cảnh báo -->
    <h2>Link has been expired</h2>
    <p>Please return to the forgot password page to get a new link.</p>
    <button class="btn" onclick="window.location.href='ForgotPassword.jsp'">Return to Forgot Password</button>
</div>

</body>
</html>
