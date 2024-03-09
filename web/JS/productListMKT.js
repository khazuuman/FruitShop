let getFil = function () {
    var cate = document.getElementById("cate-filter").value;
//    var stt = document.getElementById("status-filter").value;
    var status = document.getElementById("status-filter").value;
    var txt = document.getElementById("search").value;
//    console.log(role, stt, gen, txt);
    const path = window.location.pathname;
    const params = new URLSearchParams(window.location.search);
    params.set("cate", cate);
//    params.set("stt", stt);
    params.set("status", status);
    params.set("txt", txt);
    console.log(params.toString());
    $.ajax({
        url: "/Fruitshop/ProductListMKT",
        type: "post",
        data: {
            cate: cate,
//            stt: stt,
            status: status,
            txt: txt
        },
        success: function (resp) {
            //console.log(resp.split("divi")[1]);
            $("tbody").html(resp.split("divi")[0]);
            window.history.pushState({}, null, path + "?" + params.toString());
            $(".paging").html(resp.split("divi")[1]);
        }
    });
};


$(document).on("click", ".onclick", function () {
    var id = $(this).children("p").attr('index');
    console.log(id);
    const path = window.location.pathname;
    const params = new URLSearchParams(window.location.search);
    params.set("index", id);
    console.log(params.toString());
    $.ajax({
        url: path + "?" + params.toString(),
        type: "post",
        success: function (resp) {
            //console.log(resp.split("divi")[1]);
            $("tbody").html(resp.split("divi")[0]);
            window.history.pushState({}, null, path + "?" + params.toString());
            $(".paging").html(resp.split("divi")[1]);
        }
    });
});

$(document).on("click", "#so", function () {
    const path = window.location.pathname;
    const params = new URLSearchParams(window.location.search);
    var column = $(this).data('column');
    var order = $(this).data('order');
    params.set("col", column);
    params.set("or", order);
    console.log("column: ", column, order);
    if (order === 'desc') {
        $(this).data('order', "asc");
    } else {
        $(this).data('order', "desc");
    }
    $.ajax({
        url: path + "?" + params.toString(),
        type: "post",
        success: function (resp) {
            //console.log(resp.split("divi")[1]);
            $("tbody").html(resp.split("divi")[0]);
            window.history.pushState({}, null, path + "?" + params.toString());
            $(".paging").html(resp.split("divi")[1]);
        }
    });
});