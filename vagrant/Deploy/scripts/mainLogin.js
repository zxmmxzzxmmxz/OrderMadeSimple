$(function() {$('#submit').on('click',function (e){
    $.ajax({
        type: 'POST',
        url: NEED_PROJECT+'/rest/auth/login',
        crossDomain: true,
        data:$("#login_form").serialize(),
        success: function(token){
            sessionStorage.token = token.token;
            sessionStorage.restaurant_name = token.restaurant_name;
            sessionStorage.restaurant_id = token.restaurant_id;
            window.location.href = "index.html";
        },
        error:function(jqXHR, textStatus, errorThrown){
            var error_element =  $("#error_message")
            error_element.html(jqXHR.responseText);
            error_element.attr("hidden",false)
        }
    });
    e.preventDefault();
})});
