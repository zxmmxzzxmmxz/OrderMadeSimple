$(function(){
	// unsured url, replce later
	let url = 'http://localhost:1234/rest/info/2';

	var $hours = $('#hours');
	var $addr = $('#addr');
	var $phone = $("#tel");

	$('.info').on('click', function(){
		if($('#currentRes').hasClass('visible')){
			$('#currentRes').removeClass('visible');
		}else{
			$('#currentRes').addClass('visible');
			$('#helper').addClass('visible');
		}	
	});

	$('.close').on('click', function(){
		$('#currentRes').removeClass('visible');
		$('#helper').removeClass('visible');
	});

	$('#overlay').click(function(){
		$('#booking').removeClass('visible');
		$('#overlay').removeClass('visible');
	});

	$.ajax({
		type: 'get',
		url: url,
		success: function(response){
			var business = response.hours;
			var contact = response.contact;
			
			$.each(business, function(i, info){
				$row = $('<tr>' + 
				'<td>' + info.Day + ':</td>' +
				'<td>' + info.OpenHr +' - ' + info.CloseHr +'</td>' +
				'<tr/>');
				$hours.append($row);
			});

			$addr.append('<p>' + contact.Address + '</p');
			$phone.append('<p>' + contact.Phone + '</p')
		}

	});

	$('.res').click(function(){
		if($('#booking').hasClass('visible')){
			$('#booking').removeClass('visible');
		}else{
			$('#booking').addClass('visible');
			$('#overlay').addClass('visible');
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
	});

	$('#sub input[type=button]').click(function(){
		$('#booking').removeClass('visible');
		$('#overlay').removeClass('visible');
	});

});