/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function attachChangeEvents() {
    $('.inputs-quantity').on('change', function () {
        var quantity = $(this).val();
        var productId = this.getAttribute('data-id');
        console.log(quantity)
        console.log(productId)
        var tdtotal = document.getElementById("th-total");
        var subtotal = document.getElementById("subtotal");
        var tdprice = this.closest('tr').getElementsByClassName("td-total")[0]; // Tìm thẻ Price trong cùng hàng
        console.log(tdtotal)
        $.ajax({
            url: '/Fruitshop/updateQuantityAjax',
            method: 'POST',
            data: {
                productID: productId,
                quantity: quantity
            },
            success: function (response) {
                var price = response.productPrice;
                var total = response.totalCart;

                tdprice.textContent = "$" + price; // Cập nhật tổng giá trị
                tdtotal.textContent = "$" + total; // Cập nhật giá của sản phẩm
                subtotal.textContent = "$" + total;
            }
        });
    });
}

attachChangeEvents()

function loadAfterDelete(id) {
    console.log(id)
    var productCartContent = document.getElementById("productCartContentTable");
    var totalCartContent = document.getElementById("totalCarttable");
    $.ajax({
        url: "/Fruitshop/deleteAjax",
        type: "get",
        data: {
            productID: id
        },
        success: function (result) {
            var data = JSON.parse(result);
            var productCartResult = data.productCartResult;
            var totalCartResult = data.totalCartResult;
            productCartContent.innerHTML = productCartResult;
            totalCartContent.innerHTML = totalCartResult;
            attachChangeEvents();
        },
        error: function (xhr) {
            console.log(xhr.responseText)
        }
    });
}

