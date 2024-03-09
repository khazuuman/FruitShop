
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://kit.fontawesome.com/33f9434037.js" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link
      href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@500&family=Poppins:wght@300;600&display=swap"
      rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@500&family=Poppins:wght@600&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="css/BlogDetailcss/body.css">
    <link rel="stylesheet" href="css/BlogDetailcss/left_content.css">
    <link rel="stylesheet" href="css/BlogDetailcss/right_content.css">
    <link rel="stylesheet" href="css/BlogDetailcss/footer.css">
    <link rel="stylesheet" href="css/BlogDetailcss/grid.css">
    <link rel="stylesheet" href="css/BlogDetailcss/header.css">
  </head>
  <body>
    <section class="header">
      <div class="grid wide menu">
        <nav class="navbar">
          <ul class="navbar-list">
            <li class="navbar-list-item">
              <a href="home">Home</a>
            </li>
            <li class="navbar-list-item header__navbar-user">
              <a href="BecomeSeller.jsp">Shop</a>
              <ul class="navbar-blog-list">
                <li class="navbar-blog-list-item">
                  <a class="navbar-blog-item-link" href="url">Products</a>
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
            <img src="img/logo-img.png" alt="anh logo"/>
          </a>
          <ul class="navbar-list">
            <li class="navbar-list-item header__navbar-user">
              <a href="Blog">Blog</a>
              <ul class="navbar-blog-list">
                <li class="navbar-blog-list-item">
                  <a class="navbar-blog-item-link" href="url">Blog List</a>
                </li>
                <li class="navbar-blog-list-item">
                  <a class="navbar-blog-item-link" href="url">Blog Single</a>
                </li>
              </ul>
            </li>
            <c:if test="${sessionScope.acc==null}">
              <li class="navbar-list-item">
                <a href="LoginController">Login</a>
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
    </section>
    <section class="content">
      <div class="left-content">
        <div class="img">
          <img src="img/${blog.img}"
               alt="">
        </div>
        <div class="author_cmt">
          <span class="user_main"><i class="fa-solid fa-circle-user"></i> ${blog.account.username}</span>
          <span class="comment_main"><i class="fa-solid fa-comments"></i> ${fn:length(blogcmt)} Comments</span>
        </div>
        <div class="text-content">
          <h1>${blog.title}</h1>
          <c:forEach items="${blogContent}" var="line">
            <p>${line}</p>
          </c:forEach>
        </div>
        <div class="related-blog">
          <h2>Related Blog</h2>
          <div class="related-blog-list">
            <c:forEach items="${relateBlog}" var="rl">
              <div class="related-single-blog">
                <img src="img/${rl.img}"
                     alt="">
                <div class="related-single-blog-content">
                  <span><i class="fa-solid fa-calendar-days"></i>${DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss").format(rl.time)}</span>
                  <h3><a style="text-decoration: none; color: #000000" href="blogdetail?id=${rl.blogID}">${rl.title}</a></h3>
                  <div class="author_cmt_related-blog">
                    <span><i class="fa-regular fa-user"></i> ${rl.account.username}</span>
                    <span><i class="fa-regular fa-message"></i> <fmt:formatNumber type="number" minIntegerDigits="2" value="${(cmtcount[rl.blogID]==null)?0:cmtcount[rl.blogID]}" /> Comments</span>
                    <i class="fa-solid fa-arrow-right"></i>
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
        <div class="comment">
          <h2 class="title-comment">2 Comments</h2>
          <div class="user-comment">
            <div class="avatar">
              <img src="https://html.ditsolution.net/drtheme/dreamhub/organic/assets/images/main-thumb/blog-details-profile.png"
                   alt="">
            </div>
            <div class="comment-content">
              <div class="userName-repbtn">
                <span>Kevin Martin</span>
                <button class="reply-btn"><i class="fa-solid fa-reply"></i> Reply</button>
              </div>
              <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Expedita sit architecto, maxime
                accusantium ratione sapiente? Tempora, sit? Earum optio, consequatur, repellat magnam
                doloribus nemo amet deleniti, a exercitationem commodi laborum!</p>
            </div>
          </div>
          <div class="user-comment">
            <div class="avatar">
              <img src="https://html.ditsolution.net/drtheme/dreamhub/organic/assets/images/main-thumb/blog-details-profile.png"
                   alt="">
            </div>
            <div class="comment-content">
              <div class="userName-repbtn">
                <span>Kevin Martin</span>
                <button class="reply-btn"><i class="fa-solid fa-reply"></i> Reply</button>
              </div>
              <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Expedita sit architecto, maxime
                accusantium ratione sapiente? Tempora, sit? Earum optio, consequatur, repellat magnam
                doloribus nemo amet deleniti, a exercitationem commodi laborum!</p>
            </div>
          </div>
        </div>
        <div class="leave-cmt">
          <h1>Leave A Comment</h1>
          <textarea class="cmt-input" name="" id="" cols="30" rows="8"
                    placeholder="Write Your Comments*"></textarea>
          <button class="submit-cmt">Submit Request</button>
        </div>
      </div>

      <div class="right-content">
        <div class="recent-post-box">
          <h3>Recent Post</h3>
          <c:forEach items="${recentBlog}" var="rc">
            <div class="single-recent-post">
              <div class="recent-img">
                <img src="img/${rc.img}"
                     alt="">
              </div>
              <div class="single-recent-post-content">
                <a style="text-decoration: none; color: #000000" href="blogdetail?id=${rc.blogID}"><p class="single-recent-text">${rc.title}</p></a>
                <p class="single-recent-date">${DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss").format(rc.time)}</p>
              </div>
            </div>
          </c:forEach>

        </div>
        <div class="blog-categories">
          <h3>Categories</h3>
          <div class="cate-list">
            <form action="Blog" id="FilForm" onclick="submitForm()">
              <c:forEach items="${blogCate}" var="ca">
                <input type="radio" id="radio-btn${ca.blogCateID+1}" name="searchBlog" ${ca.blogCateID==blog.category.blogCateID?"checked":""} value="${ca.blogCateName}">
                <label for="radio-btn${ca.blogCateID+1}">${ca.blogCateName}</label>
              </c:forEach>
            </form>

          </div>
        </div>
        <div class="contact-poster">
          <div class="img-background">
            <img src="https://merriam-webster.com/assets/mw/images/gallery/gal-wap-slideshow-slide/assorted%20fruit%20photo-6825-8b8e196d9d5fd4470911d69ad25fa5e0@1x.jpg"
                 alt="">
          </div>
          <div class="contact-poster-content">
            <div class="logo">
              <img src="" alt="">
            </div>
            <div class="contact-poster-text">
              <span>Need Service?</span>
              <span>Contact Us</span>
            </div>
            <button class="contact-btn">Contact Us</button>
          </div>
        </div>
      </div>
    </section>
    <footer class="footer">

      <div class="grid wide">
        <div class="row">

          <div class="col l-5">
            <div class="footer-intro">
              <img src="img/logo-footer.png" alt="alt"/>
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
  </body>
  <script>
    function submitForm() {
      document.getElementById("FilForm").submit();
    }
  </script>
</html>