$(function(){
	// unsured url, replce later
	var url = 'http://localhost:8080/rest/restaurant/';
	var getUrl = 'info/'+sessionStorage.restaurant_id;
	var postUrl = 'reserve'; 

	var $hours = $('#hours');
	var $addr = $('#addr');
	var $phone = $("#tel");

	console.log(sessionStorage.restaurant_name);

	$('#restaurant_name').html(sessionStorage.restaurant_name);

	$('.info').click(function () {
		console.log('info click');
        //var position = $('.info').position();
        //$('#currentRes').css({top: position.top, left: position.left});
        $('#resInfo .name').html(sessionStorage.restaurant_name);
        $('#currentRes').toggle();
    });

	$('#currentRes .close').click(function () {
        console.log("close");
        $('#currentRes').hide();
    });

	$('.close').on('click', function(){
        $('#currentRes').removeClass('visible');
		$('#helper').removeClass('visible');
	});

	$('#overlay').click(function(){
		$('#booking').removeClass('visible');
		$('#overlay').removeClass('visible');
		$('#postForReserve').removeClass('visible');
	});

	$.ajax({
		type: 'get',
		url: url + getUrl,
		success: function(response){
			var business = response.hours;
			var contact = response.contact;

			console.log('finish loading restaurant');

            $.each(business, function(i, info){
				$row = $('<tr>' + 
				'<td>' + info.Day + ':</td>' +
				'<td>' + info.OpenHr +' - ' + info.CloseHr +'</td>' +
				'<tr/>');
				$hours.append($row);
			});

			$addr.append('<p>' + contact.Address + '</p');
			$phone.append('<p>' + contact.Phone + '</p');
		}

	});

	$('.res').click(function(){
		if($('#booking').hasClass('visible')){
			$('#booking').removeClass('visible');
        }else{
			$('#booking').addClass('visible');
			$('#postForReserve').addClass('visible');
			$('#overlay').addClass('visible');
            //$('#booking').css({top:'10px',left:'10px'});
		}
	});

	$('#helper').click(function(){
		$('#currentRes').removeClass('visible');
		$('#helper').removeClass('visible');
		$('#booking').removeClass('visible');
		$('#overlay').removeClass('visible');
	});


	$('#booking span').on('click', function(){
		$('#booking').removeClass('visible');
		$('#overlay').removeClass('visible');
		$('#returnMsg').removeClass('visible');
		$('#postForReserve').removeClass('visible');
	});

	$('#sub input[type=button]').click(function(){
		$('#booking').removeClass('visible');
		$('#overlay').removeClass('visible');
		$('#postForReserve').removeClass('visible');
	});

	$('#sub input[type=submit]').click(function(){
		var msg = null;

		if($('#fname').val() === null || $('#fname').val().length === 0){
			$('#fname-err').html("Please fill out first name");
			document.getElementById("fname").focus();
			return;
		}else{
			$('#fname-err').html("");
		}

		if($('#lname').val() === null || $('#lname').val().length === 0){
			$('#lname-err').html("Please fill out last name");
			document.getElementById("lname").focus();
			return;
		}else{
			$('#lname-err').html("");
		}

		if($('#pnumber').val() === null || $('#pnumber').val().length === 0){
			$('#pnumber-err').html("Please fill out phone number");
			document.getElementById("tel").focus();
			return;
		}else{
			$('#pnumber-err').html("");
		}

		var reservation = {
			fname: $('#fname').val(),
			lname: $('#lname').val(),
			phone: $('#pnumber').val(),
			time: $('#time').val(),
			seats: $('#seats').val()
		}
		$.ajax({
			type: 'post',
			url: url + postUrl,
			data: JSON.stringify(
				reservation
			),
			contentType: 'application/json',
			dataType: 'text',
			success: function(data){
				msg = data;
			},
			error: function(xhr, res, text){
				console.log(res);
				msg = res;
			}
		})
		.done(function(){
			$('#postForReserve.visible').removeClass('visible');
			$('#returnMsg').append('<img src="../resource/check.png">');
			$('#returnMsg p').html(msg);
			$('#returnMsg').addClass('visible');
		})
		.fail(function(){
			$('#postForReserve.visible').removeClass('visible');
			$('#returnMsg').append('<img src="../resource/error.png">');
			$('#returnMsg p').html(msg + ': reservation is failed.');
			$('#returnMsg').addClass('visible');
		});
	})

});