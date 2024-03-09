
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="./css/sliderList.css">
        <link rel="stylesheet" href="./css/Home/BlogList.css"/>
        <link rel="stylesheet" href="./css/bass.css"/>
        <!--        <link rel="stylesheet" href="./css/Home/grid.css"/>-->
        <!--<link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">-->
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">

        <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <header class="main-header">
            <!-- Logo -->
            <a href="index2.html" class="logo">
                <!-- mini logo for sidebar mini 50x50 pixels -->
                <span class="logo-mini"><b>A</b>LT</span>
                <!-- logo for regular state and mobile devices -->
                <span class="logo-lg"><b>Sale</b></span>
            </a>

            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top">
                <!-- Navbar Right Menu -->
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">

                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="align-items: center;display: flex;margin-right: 60px"> 
                                <img src="./img/user1_image.jpg" class="user-image" alt="User Image" style="object-fit: cover;">
                                <span class="hidden-xs"> ${sessionScope.acc.username}</span>
                            </a>
                            <ul class="dropdown-menu" style="margin-right: 40px">
                                <!-- User image -->
                                <li class="user-header" style="height: inherit;">
                                    <img src="./img/user1_image.jpg" class="img-circle" alt="User Image" style="object-fit: cover;width: 40px;height: 40px;">
                                        <p>${sessionScope.acc.username}</p>
                                   
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
<!--                                    <div class="pull-left">
                                        <a href="userprofile?id=${sessionScope.acc.getAccID()}" class="btn btn-default btn-flat">Profile</a>
                                    </div>-->
                                    <div class="pull-right">
                                        <a href="logout" class="btn btn-default btn-flat">Sign out</a>
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
                            <p style="color: white"> ${sessionScope.acc.username}</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>

                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li class="header">MAIN NAVIGATION</li>
                        <li class="active treeview">
                            <a href="#">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>

                            </a>

                        </li>

                     

                        <li class="">
                            <a href="sliderlist">
                                <i class="fa fa-picture-o" aria-hidden="true"></i> <span>Manage Orders</span>
                                <span class="pull-right-container">
                                    <small class="label pull-right bg-green">new</small>
                                </span>
                            </a>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>
            <div class="col-lg-10" style="padding-left: 360px">
                 <head>
          <meta charset="utf-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge">
          <title>Order Stats</title>
          <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
      </head>
      <body>
          <label for="startDate">Choose a start date:</label>
          <input type="date" id="startDate" name="startDate">
          <button id="submitBtn">Submit</button>
          <canvas id="orderChart" width="400" height="200"></canvas>
      
          <script>
             $(document).ready(function(){
          $("#submitBtn").click(function(){
              var startDate = $("#startDate").val();
              var endDate = new Date(startDate);
              endDate.setDate(endDate.getDate() + 7); // L?y ngày k?t thúc, 7 ngày sau ngày b?t ??u
              
              // Chuy?n ??i ngày k?t thúc sang chu?i yyyy-mm-dd
              var endDateStr = endDate.toISOString().split('T')[0];
              
              $.ajax({
                  url: "SalesTrendServlet?startDate=" + startDate + "&endDate=" + endDateStr,
                  type: "GET",
                  dataType: "json",
                  success: function(data){
                      var labels = [];
                      var orderCounts = [];
                      var revenue = [];
                      
                      // L?y danh sách ngày t? JSON và x? lý
                      for (var i = 0; i < 7; i++) {
                          var currentDate = new Date(startDate);
                          currentDate.setDate(currentDate.getDate() + i); // T?ng ngày b?t ??u lên i ?? l?y 7 ngày sau
                          var currentDateStr = currentDate.toISOString().split('T')[0];
                          labels.push(currentDateStr);
                          orderCounts.push(data.orderData[currentDateStr] || 0); // S? l??ng ??n hàng
                          revenue.push(data.revenue[currentDateStr] || 0); // Doanh thu
                      }
      
                      var ctx = document.getElementById('orderChart').getContext('2d');
                      var chart = new Chart(ctx, {
                          type: 'line',
                          data: {
                              labels: labels,
                              datasets: [{
                                  label: 'Order Counts',
                                  data: orderCounts,
                                  fill: false,
                                  borderColor: 'rgba(54, 162, 235, 1)',
                                  borderWidth: 1
                              },
                              {
                                  label: 'Revenue',
                                  data: revenue,
                                  fill: false,
                                  borderColor: 'rgba(255, 99, 132, 1)',
                                  borderWidth: 1
                              }]
                          },
                          options: {
                              scales: {
                                  y: {
                                      beginAtZero: true
                                  }
                              }
                          }
                      });
                  }
              });
          });
      });
      
          </script>
      </body>
            </div>


        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-U9Yi14bGYPMjYfJAZGuh6o3JODvw/+T2BE6ZU7izjKT1VaVZfU9R8IBqweNkOF1d" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



        <script>
                                function submitForm() {
                                    document.getElementById("fStatus").submit();
                                }
        </script>
    </body>
</html>
