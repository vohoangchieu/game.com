function showWarning(msg) {
    $.ambiance({
        message: msg,
        type: "error",
        permanent: false,
        timeout: 1,
        fade: true
    });
}
function showInfo(msg) {
    $.ambiance({
        message: msg,
        type: "success",
        permanent: false,
        timeout: 1,
        fade: true
    });
}
$(function() {
    $("#key").keydown(function(e) {
        var keyCode = e.keyCode || e.which;
        if (keyCode == 13) {
            var val = $("#key").val().replace(/[!@#$%^&*()'"<>,./?:;]/g, '').trim();//replace(/[^a-zA-Z 0-9]+/g, '').trim();
            if (val != "") {
                window.location = "/search?keyword=" + val;
                return false;
            }
        }
    })
})