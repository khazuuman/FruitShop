<%-- 
    Document   : success
    Created on : Oct 30, 2023, 11:44:53 PM
    Author     : MM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Redirect Page</title>
    <link rel="stylesheet" href="./assets/css/bass.css"/>
    <link rel="stylesheet" href="./assets/css/grid.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        * {
            font-family: 'Montserrat', sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            background: rgb(156, 224, 149);
        color: #def0bd;
        background-size: cover;
        background-repeat: no-repeat;
        background-image: url("https://chichchoedesign.com/wp-content/uploads/2023/04/thiet-ke-cua-hang-hoa-qua-tron-goi-min.jpeg");
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .countdown-card {
            background-color: #f5f5f5; /* Màu nền trắng */
            box-shadow: 0 3px 10px 0 rgba(0,0,0,.14);
            border-radius: 24px;
            overflow: hidden;
            width: 500px;
            min-height: 275px;
            padding: 20px;
            text-align: center;
        }
        h1 {
            color: #218838; /* Màu xanh lá cây */
            font-weight: 600;
            font-size: 2.2rem;
            text-transform: uppercase;
            margin-bottom: 20px;
        }
        p {
            font-size: 1.6rem;
            margin-bottom: 30px;
            color: #218838; /* Màu xanh lá cây */
        }
        #countdown {
            font-size: 1.4rem;
            margin-bottom: 30px;
            opacity: 0.7;
            color: #218838; /* Màu xanh lá cây */
        }
        a {
            color: #ff6600; /* Màu cam */
            text-decoration: none;
            font-weight: 600;
            text-transform: uppercase;
        }
        button {
            cursor: pointer;
            font-size: 1.6rem;
            text-transform: uppercase;
            border: none;
            background: #ff6600; /* Màu cam */
            color: #ffffff; /* Màu trắng */
            width: 70%;
            margin: auto;
            display: block;
            height: 50px;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #ff8533; /* Màu cam nhạt khi hover */
        }
        .icon {
            font-size: 48px;
            margin-bottom: 20px;
            color: #218838; /* Màu xanh lá cây */
        }
    </style>
</head>
<body>
    <div class="countdown-card">
        <i class="icon fas fa-apple-alt"></i> <!-- Icon táo -->
        <h1>Successfully</h1>
        <p id="countdown">Automatically redirect to the page <a href='Login.jsp'>Login</a> after <span id="count"></span> seconds</p>
        <button onclick="window.location.href = 'Login.jsp'">OK</button>
    </div>
    <script>
        function countDown() {
            var count = 10;
            var countdownElement = document.getElementById("count");
            function updateCountdown() {
                countdownElement.textContent = count;
            }
            function excuteCountdown() {
                updateCountdown();
                var countdownInterval = setInterval(
                    function () {
                        count--;
                        updateCountdown();
                        if (count <= 0) {
                            clearInterval(countdownInterval);
                            window.location.href = "Login.jsp";
                        }
                    }, 1000);
            }
            excuteCountdown();
        }
        window.onload = countDown;
    </script>
</body>
</html>
