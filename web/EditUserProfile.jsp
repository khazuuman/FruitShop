<%-- 
    Document   : EditUserProfile
    Created on : Jan 17, 2024, 3:20:06 PM
    Author     : Thanh Hai
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Change Password</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="./css/Home/HomeCss.css"/>
        <link rel="stylesheet" href="./css/Home/bass.css"/>
        <link rel="stylesheet" href="./css/Home/grid.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <script>
            function showMessage() {
                alert("You cannot change the email");
            }


            function validateForm() {
                var fileInput = document.getElementById('imageInput');
                var fileName = fileInput.value.trim().toLowerCase();

                // Check if the file input is empty or the file format is not '.jpg'
                if (fileName !== '' && !fileName.endsWith('.jpg')) {
                    alert('Invalid file format. Please choose a JPG image file.');
                    return false; // Prevent form submission
                }

                return true; // Allow form submission
            }

            function checkFileExtension() {
                var fileInput = document.getElementById('imageInput');
                var fileName = fileInput.value.trim().toLowerCase();

                // Check if the file input is not empty and the file format is not '.jpg'
                if (fileName !== '' && !fileName.endsWith('.jpg')) {
                    alert('Invalid file format. Please choose a JPG image file.');
                    // Clear the file input if the file format is invalid
                    fileInput.value = '';
                }
            }
            document.addEventListener("DOMContentLoaded", function() {
    // Get the input element
    var usernameInput = document.querySelector('input[name="name"]');

    // Add an event listener for input changes
    usernameInput.addEventListener('input', function() {
        // Get the current value of the input
        var username = usernameInput.value;

        // Validate the username using a regular expression
        var isValidUsername = /^[a-zA-Z\s]+$/.test(username);

        // Display an error message if the username is not valid
        var errorMessage = document.getElementById('error-message');
        if (!isValidUsername) {
            errorMessage.textContent = 'Username can only contain letters and spaces';
            usernameInput.setCustomValidity('Invalid username');
        } else {
            errorMessage.textContent = '';
            usernameInput.setCustomValidity('');
        }
    });

    // Add an event listener for form submission to perform a final validation
    var form = document.querySelector('form');
    form.addEventListener('submit', function(event) {
        var username = usernameInput.value;
        if (!/^[a-zA-Z\s]+$/.test(username)) {
            alert('Please enter a valid username containing only letters and spaces.');
            event.preventDefault(); // Prevent form submission
        }
    });
});

        </script>
    </head>
    <body>
        <div class="container">
            <div class="main-body">
                <div class="grid wide menu">
                    <nav class="navbar">
                        <ul class="navbar-list">
                            <li class="navbar-list-item">
                                <a href="home">Home</a>
                            </li>
                            <li class="navbar-list-item header__navbar-user">
                                <a href="ProductListController">Shop</a>
                                <ul class="navbar-blog-list">
                                    <li class="navbar-blog-list-item">
                                        <a class="navbar-blog-item-link" href="ProductListController">Products</a>
                                    </li>

                                    <li class="navbar-blog-list-item">
                                        <a class="navbar-blog-item-link" href="url">Cart</a>
                                    </li>
                                    <li class="navbar-blog-list-item">
                                        <a class="navbar-blog-item-link" href="url">Checkout</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <a href="home" class="navbar_logo">
                            <img src="./img/logo-img.png" alt="anh logo"/>
                        </a>
                        <ul class="navbar-list">
                            <li class="navbar-list-item header__navbar-user">
                                <a href="#">Blog</a>
                                <ul class="navbar-blog-list">
                                    <li class="navbar-blog-list-item">
                                        <a class="navbar-blog-item-link" href="Blog">Blog List</a>
                                    </li>
                                    <li class="navbar-blog-list-item">
                                        <a class="navbar-blog-item-link" href="url">Blog Single</a>
                                    </li>
                                </ul>
                            </li>
                            <c:if test="${sessionScope.acc==null}">
                                <li class="navbar-list-item">
                                    <a href="Login.jsp">Login</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.acc!=null}">
                                <li class="navbar-list-item header__navbar-user">
        <!--                            <img src="./assest/img/${sessionScope.acc.accImg}" class="account-logo" alt="123"/>-->
                                    <a href="#" class="account-name">${sessionScope.acc.username}</a>
                                    <ul class="header__navbar-user-menu">
                                        <li class="header__navbar-user-item">
                                            <a href="userprofile?id=${sessionScope.acc.getAccID()}" class="header__navbar-user-item-link">Profile</a>
                                        </li>
                                        <li class="header__navbar-user-item header__navbar-user-item--separate">
                                            <a href="logout" class="header__navbar-user-item-link">Logout</a>
                                        </li>
                                    </ul>
                                </li>
                            </c:if>
                        </ul>

                    </nav>
                </div>

                <!-- Breadcrumb -->

                <!-- /Breadcrumb -->
                <form action="edit-profile" method="post"   onsubmit="return validateForm()" >

                    <div class="row gutters-md">
                        <div class="col-md-4 mb-3">
                            <div class="card">
                                <div class="card-body ">
                                    <div class="d-flex flex-column align-items-center text-center">
                                        <c:choose>
                                            <c:when test="${not empty requestScope.acc.accImg}">
                                                <img src="img/${requestScope.acc.accImg}" alt="User" class="rounded-circle" width="150" accept=".jpg">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
                                            </c:otherwise>
                                        </c:choose>
                                        <div class="mt-3">
                                            <h4>${requestScope.acc.username}</h4>


                                            <div class="btn btn-outline-success">
                                                <input type="file"  name ="img"  id="imageInput" style="display: none;" />
                                                <label for="imageInput">Change Image</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card mt-3">

                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Full Name</h6>
                                        </div>

                                        <div class="col-sm-9 text-secondary ">
                                            <input type="text" name="name" placeholder="${requestScope.acc.username}"  value="${requestScope.acc.username}"required />
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Email</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" name="email" value="${requestScope.acc.email}" readonly onclick="showMessage()" />

                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Phone</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" name="phone" placeholder="${requestScope.acc.phone}" value="${requestScope.acc.phone}"required pattern="\d{10}" title="Vui lòng nhập đúng 10 số"/>


                                        </div>
                                    </div>
                                    <hr>

                                    <hr>




                                    <div class="row">
                                        <div class="col-sm-12 d-flex align-items-center">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Address</h6>
                                            </div>
                                            <div class="col-sm-6 text-break">
                                                <input type="text" value="${requestScope.acc.address}" name="address"class="form-control"  /> 
                                            </div>

                                        </div>
                                    </div>
                                    <br>




                                    <hr>



                                    <div class="row">

                                        <div class="col-sm-12">

                                            <input type="radio" name="gender" value="1" checked />Male
                                            <input type="radio" name="gender" value="0" />Female
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input  class="btn btn-outline-success " type="submit" value="Save Change" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            </form>  
                        </div> 
                    </div> 
            </div> 

        </div> 
        <footer class="footer" style ="    margin-top: 173px;">

            <div class="grid wide">
                <div class="row">

                    <div class="col l-5">
                        <div class="footer-intro">
                            <img src="./img/logo-footer.png" alt="alt"/>
                            <p class="footer-intro-des">Maecenas mi justo, interdum at consectetur vel, tristique et arcu. Ut quis eros blandit, ultrices diam in, elementum ex. Suspendisse quis faucibus urna. Suspendisse pellentesque.</p>
                        </div>
                    </div>
                    <div class="col l-3">
                        <div class="footer-link">
                            <h1 class="footer-link-heading">Quick Links</h1>
                            <ul class="footer-link-list">
                                <li class="footer-link-item">
                                    <a href="#">About</a>
                                </li> 
                                <li class="footer-link-item">
                                    <a href="#">About</a>
                                </li> 
                                <li class="footer-link-item">
                                    <a href="#">About</a>
                                </li> 
                                <li class="footer-link-item">
                                    <a href="#">About</a>
                                </li> 
                                <li class="footer-link-item">
                                    <a href="#">About</a>
                                </li> 
                            </ul>

                        </div>
                    </div>
                    <div class="col l-4">
                        <div class="footer-download">
                            <h1 class="footer-download-heading">Download Our Mobile App</h1>
                            <p class="footer-download-des">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec aliquam gravida sollicitudin. Praesent porta enim mi, non tincidunt libero interdum sit amet.</p>
                        </div>
                    </div>

                </div>
            </div>
            <div class="footer-copyright">
                <div class="grid wide">
                    <h1 class="copyright-text">Copyright © 2024 | Organic Store</h1>
                </div>

            </div>
        </footer>




        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
    </body>
</html>