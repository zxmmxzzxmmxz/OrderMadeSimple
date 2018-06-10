$(function() {$('#submit').on('click',function (){
    $.ajax({
        type: 'POST',
        url: '/rest/auth/login',
        crossDomain: true,
        data:$("#login_form").serialize(),
        success: function(token){
            alert('got token '+token);
            sessionStorage.token = token.token;
            window.location.href = "index.html";
        },
        error:function(jqXHR, textStatus, errorThrown){
            var error_element =  $("#error_message")
            error_element.append(jqXHR.responseText);
            error_element.attr("hidden",false)
        }
    });
})});
