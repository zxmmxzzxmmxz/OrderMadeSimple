// NEED_PROJECT = "/project";
NEED_PROJECT = "";
$(function () {

    $("#message").html(sessionStorage.message);
    if (sessionStorage.message) {
        $("#messages").attr("hidden", false);
    }
    sessionStorage.message = "";
});

$(function() {
    $("#restaurant_name").append(sessionStorage.restaurant_name);
});