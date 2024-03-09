
var type, stt, txt, column, order, index;
const path = window.location.pathname;

let getFil = function () {
    type = document.getElementById("type-filter").value;
    stt = document.getElementById("status-filter").value;
    txt = document.getElementById("search").value;
    console.log(type, stt, txt);
    $.ajax({
        url: path,
        type: "post",
        data: {
            type: type,
            stt: stt,
            txt: txt
        },
        success: function (resp) {
            $("tbody").html(resp.split("divi")[0]);
            $(".paging").html(resp.split("divi")[1]);
        }
    });
};

$(document).on("click", ".onclick", function () {
    index = $(this).children("p").attr('index');
    console.log(index);
    $.ajax({
        url: path,
        type: "post",
        data: {
            type: type,
            stt: stt,
            txt: txt,
            col: column,
            or: order,
            index: index
        },
        success: function (resp) {
            console.log(resp);
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
        url: path,
        type: "post",
        data: {
            type: type,
            stt: stt,
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

$(document).on("click", ".btn-stt", function () {
    var id = $(this).data('id');
    var act = $(this).data('act');
    var st = $(this).data('stt');
    
    console.log(id, act, st);
    $.ajax({
        url: path,
        type: "post",
        data: {
            type: type,
            stt: stt,
            txt: txt,
            col: column,
            or: order,
            index: index,
            id: id,
            act: act,
            st: st
        },
        success: function (resp) {
            $("tbody").html(resp.split("divi")[0]);
            $(".paging").html(resp.split("divi")[1]);
        }
    });
    
});
    


