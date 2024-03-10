<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title></title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="bootstrap/css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="./css/Home/bass.css"/>
        <link rel="stylesheet" href="./css/Home/grid.css"/>
        <link rel="stylesheet" href="./css/Home/HomeCss.css"/>
        <link rel="stylesheet" href="./css/ProductList/side-bar.css"/>
        <link rel="stylesheet" href="./css/FeedbackForm.css"/>
    </head>

    <body>
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


        <div class="grid wide" style="margin-top: 120px">
            <div class="row">
                <div class="col l-3">
                    <div class="side-bar-area" style="margin: 0">
                        <div class="side-bar">
                            <div class="search-box">
                                <form class="search-form" action="ProductListController">
                                    <input class="text-input" type="text" placeholder="Search product..." value="${search}" name="search">
                                    <button type="submit" class="search-btn"><i class="fa-solid fa-chevron-up fa-rotate-90"
                                                                                style="color: #ffffff;"></i></button>
                                </form>
                            </div>
                            <div class="category">
                                <h3>Categories</h3>
                                <c:forEach var="c" items="${cateList}">
                                    <span><a href="ProductListController?cate=${c.getCateName()}">${c.getCateName()}</a> (${c.getQuantity()})</span>
                                </c:forEach>
                            </div>
                            <div class="star-rating-sider">
                                <h3>Rating</h3>
                                <div class="rating-list">
                                    <a href="ProductListController?rating=5">
                                        <div class="five-stars">
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                        </div>
                                    </a>
                                    <a href="ProductListController?rating=4">
                                        <div class="four-stars">
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-regular fa-star"></i><span>or more</span>
                                        </div>
                                    </a>
                                    <a href="ProductListController?rating=3">
                                        <div class="three-stars">
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-regular fa-star"></i>
                                            <i class="fa-regular fa-star"></i><span>or more</span>
                                        </div>
                                    </a>
                                    <a href="ProductListController?rating=2">
                                        <div class="two-stars">
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-regular fa-star"></i>
                                            <i class="fa-regular fa-star"></i>
                                            <i class="fa-regular fa-star"></i><span>or more</span>
                                        </div>
                                    </a>
                                    <a href="ProductListController?rating=1">
                                        <div class="one-star">
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-regular fa-star"></i>
                                            <i class="fa-regular fa-star"></i>
                                            <i class="fa-regular fa-star"></i>
                                            <i class="fa-regular fa-star"></i><span>or more</span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <form class="price-form" action="ProductListController">
                                <div class="pricing-sider">
                                    <h3>Pricing</h3>
                                    <div class="price-input">
                                        <span>From</span> <input type="number" value="${minPrice}" name="minPrice"> <span>To</span> <input type="number" value="${maxPrice}" name="maxPrice"> 
                                    </div>
                                </div>
                                <div class="search-btn-box"><button type="submit" class="price-search">Search</button>
                                </div>
                            </form>
                            <div class="reset-box"><button onclick="window.location.href = 'ProductListController'" class="reset-side-bar">Reset</button> </div>  
                            <div class="side-bar-products">
                                <h3>Lasted Products</h3>
                                <c:forEach var="p3" items="${newest3products}">
                                    <div class="side-product">
                                        <div class="side-product-img">
                                            <a href="ProductDetailController?productId=${p3.getProductID()}">
                                                <img src="./ProductListImg/${p3.getImage()}"
                                                     alt="">
                                            </a>
                                        </div>
                                        <div class="side-product-content">
                                            <a style="text-decoration: none; font-family: 'Open Sans', sans-serif; font-size: 1.2rem; color: black;" href="ProductDetailController?productId=${p3.getProductID()}"><h2 style="margin: 0">${p3.getProductName()}</h2></a>
                                            <span style="margin-bottom: 5px" class="cate-title">${p3.getCateName()}</span>
                                            <div style="margin-bottom: 5px" class="star-rating">
                                                <c:forEach begin="1" end="${p3.getRating()}" step="1">
                                                    <i style="color: #9fe252;" class="fa-solid fa-star"></i>
                                                </c:forEach>
                                                <c:forEach begin="${p3.getRating()}" end="4" step="1">
                                                    <i class="fa-sharp fa-regular fa-star" style="color: #000000;"></i>
                                                </c:forEach>
                                            </div>
                                            <div class="side-bar-product-price">
                                                <span class="oldprice">$${p3.getMainPrice()}</span>
                                                <span class="onprice">$${p3.getSalePrice()}</span>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>                        
                            </div>
                        </div>
                    </div>

                </div>
                <div class="col l-9">
                    <div class="container" style="position: sticky;top: 20px;">
                        <div class="row justify-content-center">
                            <div class="col-md-7">
                                <div class="bg-light p-30" style="font-size: 14px;">
                                    <h2 class="mb-4 text-center text-uppercase">Feedback</h2>

                                    <div>
                                        
                                        <div class="order-item">
                                            <h3 class="order-item-id">${requestScope.listo.getOrderID()}</h3>
                                            <div class="img-hover">
                                                <img class="item-product-img" src="./ProductListImg/${requestScope.listo.getProducts().get(0).getImage()}" alt="alt"/>
                                            </div>
                                            <div class="order-item-product">
                                                <div class="item-product-content">
                                                    <h1 class="item-product-name">${requestScope.listo.getProducts().get(0).getProductName()}</h1>
                                                    <p class="item-product-cate">${requestScope.listo.getProducts().get(0).getCategory().getCateName()}</p>
                                                </div>

                                                <span class="item-product-price">${requestScope.listo.getPrice().get(0)}$</span>
                                                <span class="item-product-quantity">x${requestScope.listo.getQuantity().get(0)}</span>
                                            </div>
                                        </div>

                                        <div>

                                            <div class="Feedback-info">
                                                <div class="info-box-feedback">
                                                    <label class="">Full Name</label>
                                                    <input  type="text" value="${sessionScope.acc.username}" readonly=""/>
                                                </div>
                                                <div class="info-box-feedback">
                                                    <label class="">Gender</label>
                                                    <input  type="text" value="${sessionScope.acc.gender == true ? 'Male' : 'Female' }" readonly=""/>
                                                </div>
                                                <div class="info-box-feedback">
                                                    <label class="">Phone</label>
                                                    <input  type="text" value="${sessionScope.acc.phone}" readonly=""/>
                                                </div>
                                                <div class="info-box-feedback">
                                                    <label class="">Email</label>
                                                    <input  type="text" value="${sessionScope.acc.email}" readonly=""/>
                                                </div>


                                            </div>



                                            <div class="d-flex my-4" style="margin-bottom: 20px">
                                                <p class="mb-0" style="flex: 1">Your Rating: </p>
                                                <div class="text-primary" style="flex: 3">
                                                    <i class="far fa-star changeStar"></i>
                                                    <i class="far fa-star changeStar"></i>
                                                    <i class="far fa-star changeStar"></i>
                                                    <i class="far fa-star changeStar"></i>
                                                    <i class="far fa-star changeStar"></i>
                                                </div>
                                            </div>
                                            <p style="color: black;margin-left: 120px" id="rating-text"></p>
                                            <form  onsubmit="return validateReview()" action="feedbackform" method="POST">
                                                <div class="form-group">
                                                    <label for="message">Your Review:</label>
                                                    <textarea name="text" id="message" cols="30" rows="5" class="form-control"></textarea>
                                                    <p id="characterMessage" style="color: red;"></p>
                                                </div>
                                                <input type="hidden" id="rating-input" name="rating" value="0">
                                                
                                                <input hidden name="pid" value="${requestScope.listo.getProducts().get(0).getProductID()}"/>
                                                <input hidden name="aid" value="${sessionScope.acc.getAccID()}"/> 

                                                <div class="form-group text-center" style="margin-top: 30px">
                                                    <input type="submit"  value="Leave Your Feedback" class="btn btn-primary px-6 py-3" style="font-size: 12px;border: none;background-color: var(--primary-color);color: white !important">
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>


                    </div>


                </div>
            </div>
</div>


            <footer class="footer">

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

            <script>
                // Lấy phần tử chứa dòng chữ phí
                const ratingText = document.getElementById('rating-text');

                // Lấy danh sách các ngôi sao
                const stars = document.querySelectorAll('.changeStar');

                // Biến để lưu số lượng sao được chọn
                let rating = 0;

                // Lặp qua từng ngôi sao và thêm sự kiện click
                stars.forEach((star, index) => {
                    star.addEventListener('click', () => {
                        rating = index + 1; // Lấy số lượng sao được chọn


                        for (let i = 0; i <= index; i++) {
                            stars[i].classList.add('fas', 'text-primary');
                            stars[i].classList.remove('far');
                        }


                        for (let i = index + 1; i < stars.length; i++) {
                            stars[i].classList.remove('fas', 'text-primary');
                            stars[i].classList.add('far');
                        }


                        document.getElementById('rating-input').value = rating;



                    });
                });
            </script>
            <script>
                function validateReview() {
                    var textareaValue = document.getElementById("message").value;
                    var forbiddenCharacters = ['@', '#', '$', '&', '!', '^'];
                    var rating = document.getElementById('rating-input').value;


                    if (rating === "0") {
                        document.getElementById("characterMessage").textContent = "Please select the number of stars before submitting a review!";
                        return false;
                    }


                    if (textareaValue.trim() === "") {
                        document.getElementById("characterMessage").textContent = "Please enter review text!";
                        return false;
                    }


                    if (textareaValue.length > 200) {
                        document.getElementById("characterMessage").textContent = "Review must not exceed 200 characters!";
                        return false;
                    }


                    for (var i = 0; i < forbiddenCharacters.length; i++) {
                        if (textareaValue.includes(forbiddenCharacters[i])) {

                            document.getElementById("characterMessage").textContent = "You violated the special character rule. Please civilize!";
                            return false;
                        }
                    }

                    document.getElementById("characterMessage").textContent = "";
                    return true;
                }
            </script>


    </body>

</html>


