<%-- 
    Document   : newPassword
    Created on : Jan 16, 2024, 9:11:23 PM
    Author     : Thanh Hai
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./css/ChangePasscss/bass.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/ChangePasscss//changepass.css"/>
        <style>
            .back-button{
                height: 40px;
                display: flex;
                justify-content: center;
                align-items: center;
                width: 40px;
                background-color: #8bc34a;
                text-decoration: none;
                position: absolute;
                left: 36%;
                top: 20%;
                color: background;
            }
        </style>
    </head>
    <body>
        <div class="newpass-container">
            <form action="change-pass" method="POST" class="newpass-card">
                
                <a href="userprofile" class="back-button">
                    <i class="fa-solid fa-left-long"></i>
                </a>
                <h1>Change Password</h1>
                <h1 name ="${requestScope.acc.email}" >${requestScope.acc.email}</h1>
                <h3 class="error">${requestScope.msg}</h3>
                <div class="new-pass">
                    <span><i class="fa-solid fa-lock"></i></span>
                    <input id="new-pass" placeholder="Enter current password" required type="password" name="passolder" />
                </div>  
                <div class="new-pass">
                    <span><i class="fa-solid fa-lock"></i></span>
                    <input id="new-pass" placeholder="Enter new password" required type="password" name="pass1" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d]{6,}$" title="The password must be at least 6 characters long and include at least one uppercase letter, one lowercase letter, and one digit."




 />
                </div>  
                <div class="new-pass">
                    <span><i class="fa-solid fa-lock"></i></span>
                    <input id="new-pass" placeholder="Enter new password" required type="password" name="pass2" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d]{6,}$" title="The password must be at least 6 characters long and include at least one uppercase letter, one lowercase letter, and one digit."




/>
                </div>

                <button type="submit">Reset password</button>
            </form>
        </div>
    </body>
</html>
