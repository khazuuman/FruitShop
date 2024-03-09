/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function addCart(ID) {
                var noti = document.getElementById("noti-ajax-box");
                console.log(ID)
                $.ajax({
                    url: "/Fruitshop/addCartFromProductList",
                    type: "get",
                    data: {
                        productID: ID
                    },
                    success: function (result) {
                        var data = JSON.parse(result);
                        var popup = data.popuphtml;
                        noti.innerHTML = popup;
                    },
                    error: function (xhr) {

                    }
                });
            }
            document.addEventListener('DOMContentLoaded', function () {
                function closeNotification() {
                    document.querySelector('.notification-container').style.display = 'none';
                }
                document.addEventListener('click', function (event) {
                    if (event.target && event.target.classList.contains('noti-close')) {
                        closeNotification();
                    }
                });
            });