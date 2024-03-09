
var role, stt, gen, txt, column, order;
const path = window.location.pathname;

let getFil = function () {
    role = document.getElementById("role-filter").value;
    stt = document.getElementById("status-filter").value;
    gen = document.getElementById("gender-filter").value;
    txt = document.getElementById("search").value;
    console.log(role, stt, gen, txt);
    $.ajax({
        url: "/Fruitshop/userlist",
        type: "post",
        data: {
            role: role,
            stt: stt,
            gen: gen,
            txt: txt
        },
        success: function (resp) {
            $("tbody").html(resp.split("divi")[0]);
            $(".paging").html(resp.split("divi")[1]);
        }
    });
};

$(document).on("click", ".onclick", function () {
    var id = $(this).children("p").attr('index');
    console.log(id);
    $.ajax({
        url: "/Fruitshop/userlist",
        type: "post",
        data: {
            role: role,
            stt: stt,
            gen: gen,
            txt: txt,
            col: column,
            or: order,
            index: id
        },
        success: function (resp) {
            $("tbody").html(resp.split("divi")[0]);
            $(".paging").html(resp.split("divi")[1]);
        }
    });
});

$(document).on("click", "#so", function () {
    column = $(this).data('column');
    order = $(this).data('order');
    console.log("column: ", column, order);
    if (order === 'desc') {
        $(this).data('order', "asc");
    } else {
        $(this).data('order', "desc");
    }
    $.ajax({
        url: "/Fruitshop/userlist",
        type: "post",
        data: {
            role: role,
            stt: stt,
            gen: gen,
            txt: txt,
            col: column,
            or: order
        },
        success: function (resp) {
            $("tbody").html(resp.split("divi")[0]);
            $(".paging").html(resp.split("divi")[1]);
        }
    });
});