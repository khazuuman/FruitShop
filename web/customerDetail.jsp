
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css"> 
<!--     Ionicons -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css"> 
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="css/userlist.css" />
    <link rel="stylesheet" href="css/bass.css"/>   
    <link rel="stylesheet" href="css/customerdetail.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/33f9434037.js" crossorigin="anonymous"></script>
    <script>
      $(document).ready(function () {
        $('#add').click(function () {
          window.location.href = "userlistprofile?id=0";
        });
      });
      $(document).ready(function () {
        $('#cancel').click(function () {
          window.history.back();
        });
      });
    </script>
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <header class="main-header">
      <!-- Logo -->
      <a href="index2.html" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>A</b>LT</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>Admin</b>LTE</span>
      </a>

      <!-- Header Navbar: style can be found in header.less -->
      <nav class="navbar navbar-static-top">
        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
          <ul class="nav navbar-nav">

            <li class="dropdown user user-menu">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="align-items: center;display: flex;margin-right: 60px"> 
                <img src="./img/user1_image.jpg" class="user-image" alt="User Image" style="object-fit: cover;">
                <span class="hidden-xs">Alexander Pierce</span>
              </a>
              <ul class="dropdown-menu" style="margin-right: 40px">
                <!-- User image -->
                <li class="user-header" style="height: inherit;">
                  <img src="./img/user1_image.jpg" class="img-circle" alt="User Image" style="object-fit: cover;width: 40px;height: 40px;">
                  <p>
                    Alexander Pierce
                  </p>
                </li>
                <!-- Menu Footer-->
                <li class="user-footer">
                  <div class="pull-left">
                    <a href="#" class="btn btn-default btn-flat">Profile</a>
                  </div>
                  <div class="pull-right">
                    <a href="#" class="btn btn-default btn-flat">Sign out</a>
                  </div>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </nav>
    </header>

    <div class="row">

      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="./img/user1_image.jpg" class="img-circle" alt="User Image" style="object-fit: cover;width: 40px;height: 40px;">
            </div>
            <div class="pull-left info">
              <p style="color: white">Alexander Pierce</p>
              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div>

          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
              </a>
            </li>

            <li>
                            <a href="#">
                                <i class="fa fa-th"></i> <span>Manage Post</span>
                                <span class="pull-right-container">
                                    <small class="label pull-right bg-green">new</small>
                                </span>
                            </a>
                        </li>

                        <li>
                            <a href="sliderlist">
                                <i class="fa fa-picture-o" aria-hidden="true"></i> <span>Manage Sliders</span>
                                <span class="pull-right-container">
                                    <small class="label pull-right bg-green">new</small>
                                </span>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="fa fa-comment" aria-hidden="true"></i> <span>Manage Feedbacks</span>
                                <span class="pull-right-container">
                                    <small class="label pull-right bg-green">new</small>
                                </span>
                            </a>
                        </li>

                        <li>
                          <a href="customerlist" class="active treeview">
                                <i class="fa fa-th"></i> <span>Manage Customers</span>
                                <span class="pull-right-container">
                                    <small class="label pull-right bg-green">new</small>
                                </span>
                            </a>
                        </li>

          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>
      <div class="col-lg-10 main-content">
      
      <div class="function-area">
        <h1 class="title">Customer Detail</h1>
        <div>
<!--          <button id="add" ${hd}><i class="fa-solid fa-plus"></i></button>-->
        <button id="cancel" ><i class="fa-solid fa-xmark"></i></button>
        </div>
      </div>
      <form class="form-area">
        <div class="first-col">              
          <label class="form-label">User Name</label>
          <input name="name" id="name" type="text" class="form-control" placeholder="Username :" value="${requestScope.acc.username}" ${d} required>
        </div><!--end col-->

        <div class="second-col">              
          <label class="form-label">Gender</label><br>
          <input type="radio" name="gen" id="" value="1" ${(requestScope.acc.gender)?"checked":""} ${d}>Male
          <input type="radio" name="gen" id="" value="0" ${(requestScope.acc.gender)?"":"checked"} ${d}>Female
        </div><!--end col-->

        <div class="first-col">              
          <label class="form-label">Your Email</label>
          <input name="email" id="email" type="email" pattern="[a-z0-9._%+\-]+@[a-z0-9.\-]+\.[a-z]{2,}$"
                 class="form-control" placeholder="Your email :" value="${requestScope.acc.email}" required ${d}>
        </div><!--end col-->
        
        <div class="second-col address-textarea">              
          <label class="form-label">Address</label>
          <textarea name="address" rows="3" class="form-control" id="address"
                    placeholder="Address" ${d}>${requestScope.acc.address}</textarea>
        </div><!--end col-->

        <div class="first-col">              
          <label class="form-label">Mobile</label>
          <input name="number" id="number" type="text" pattern="0[0-9]{9}"
                 class="form-control" placeholder="Phone no. :" value="${requestScope.acc.phone}" ${d}>
        </div><!--end col-->

        <div class="first-col">              
          <label class="form-label">Status</label>
          <select class="form-control" ${d}>
            <c:forEach items="${sttList}" var="sl">
              <option value="${sl.sttID}" ${(sl.sttID==requestScope.acc.status.sttID) ? "selected" :""}>
                ${sl.sttName}</option>
              </c:forEach>
          </select>
        </div><!--end col-->

        <!-- <button type="submit" id="submit" id="${id}" class="btn">Submit</button> -->
      </form>
      <hr>
      <h1>Change History</h1>
      <table>
        <table class="table-list">
          <thead>
            <tr>
              <th data-column="Username" data-order="desc">Name</th>
              <th data-column="Gender" data-order="desc">Gender</th>
              <th data-column="Email" data-order="desc">Email</th>
              <th data-column="Phone" data-order="desc">Mobile</th>
              <th data-column="Address" data-order="desc">Address</th>
              <th >Changed By</th>
              <th>Changed Time</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${chgHis}" var="chl">
              <tr>
                <td>${chl.acc.username}</td>
                <td>${(chl.acc.gender)?"Male":"Female"}</td>
                <td>${chl.acc.email}</td>
                <td>${chl.acc.phone}</td>
                <td>${chl.acc.address}</td>
                <td>${chl.changedBy}</td>
                <td>${chl.time}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </table>
    </div>

</body>

</html>