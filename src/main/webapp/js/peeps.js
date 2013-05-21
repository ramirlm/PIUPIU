function peep(){
	var message = $('#new_message').val();
	var photo = $('#file').val();
	if(photo){
		$('#photo_message').val(message);
		$('#form_photo').submit();
	}else{
		$.ajax({
			url: "/piupiu/peeps",
			type: "POST",
		    data: '{ "peep":{ "text":  "' + message + '" }}',
		    contentType: "application/json",
		    async: true,
		    success: function(html){
	    	  $('#new_message').val('');
	    	  $('#wall').prepend(html);
		    },
		    error: function(data, status, e) {
	    	  //Show Error Div 
		    }
		});	
	}
}
    
function loadPeeps() {
	    $('#totalPeeps').val(0);
	    $('#wall').empty();
	
    	$.ajax({
	      url: "/piupiu/peeps/show",
	      type: "GET",
	      async: true,
	      success: function(html){
	    	  $('#wall').append(html);
	      },
	      error: function(data, status, e) {
	    	  //Show Error Div 
		  }
	   });
}
    
function showRepeepDialog(author, peep){
	$('#new_message').val('RP @' + get_username(author) +': '+ peep);
	$('html,body').animate({scrollTop: 0},'slow');
}
    
function get_username(email) {
    var m = email.match('([^@]+)');
    return m ? m[0] : null;
}

$("#divListAllFollowers").dialog({
	  height: 300,
	  width: 450,
	  autoOpen: false,
    modal: true,
    maxWidth : 450,
    resizable: false,
    close: function(ev, ui) {
    	$(this).empty();
    }
  });

$("#divListAllFollowing").dialog({
	  height: 300,
	  width: 450,
	  autoOpen: false,
  modal: true,
  maxWidth : 450,
  resizable: false,
  close: function(ev, ui) {
  	$(this).empty();
  }
});


$("#idShowLikes").dialog({
	  height: 300,
	  width: 450,
	  autoOpen: false,
    modal: true,
    resizable: true,
    close: function(ev, ui) {
    	$(this).empty();
    }
  });

function showLikers(id){
	$.ajax({
		url: "/piupiu/peeps/showLikers", 
		type: "POST",
	    data: '{ "peep":{ "id":  "' + id + '" }}',
	    contentType: "application/json",
	    async: true,
	    success: function(html){
    	  $('#idShowLikes').append(html);
    	  $("#idShowLikes").dialog("open");
	    },
	    error: function(data, status, e) {
    	  //Show Error Div 
	    }
	});
}


function listAll(email,followersOrFollowing,divToOpen){
	$.ajax({
		url: "/piupiu/profiles/listAll/"+email+"/"+followersOrFollowing, 
		type: "GET",
	    async: true,
	    success: function(html){
    	  $('#'+divToOpen).append(html);
    	  $('#'+divToOpen).dialog("open");
	    },
	    error: function(data, status, e) {
    	  //Show Error Div 
	    }
	});
}

function list(id){
	$.ajax({
		url: "/piupiu/peeps/showLikers", 
		type: "POST",
	    data: '{ "peep":{ "id":  "' + id + '" }}',
	    contentType: "application/json",
	    async: true,
	    success: function(html){
    	  $('#idShowLikes').append(html);
    	  $("#idShowLikes").dialog("open");
	    },
	    error: function(data, status, e) {
    	  //Show Error Div 
	    }
	});
}

function like(id){
	$.ajax({
		url: "/piupiu/peeps/like", 
		type: "POST",
	    data: '{ "peep":{ "id":  "' + id + '" }}',
	    contentType: "application/json",
	    async: true,
	    success: function(html){
	    	$("#idShowLikes").empty();
	    	$("#idShowLikes").dialog("close");
	    	location.reload();
	    },
	    error: function(data, status, e) {
    	  //Show Error Div 
	    }
	});
}

function dislike(id){
	$.ajax({
		url: "/piupiu/peeps/dislike", 
		type: "POST",
	    data: '{ "peep":{ "id":  "' + id + '" }}',
	    contentType: "application/json",
	    async: true,
	    success: function(html){
	    	$("#idShowLikes").empty();
	    	$("#idShowLikes").dialog("close");
	    	location.reload();
	    },
	    error: function(data, status, e) {
    	  //Show Error Div 
	    }
	});
}

function updateTotalPeeps(){
	var total = $('#totalPeeps').val();
	var count = eval(total) + 1;
    $('#totalPeeps').val(count);	
}

function loadMorePeeps(){
	var skip = $('#totalPeeps').val();
	
	$.ajax({
	      url: "/piupiu/peeps/"+skip,
	      type: "GET",
	      async: true,
	      success: function(html){
	    	  $('#wall').append(html);
	      },
	      error: function(data, status, e) {
	    	  //Show Error Div 
		  }
	   });
}

$( "#confirmationDialog" ).dialog({
	autoOpen: false,
    modal: true,
    resizable: true,
    buttons: {
      "Sim": function() {
        deletePeep($("#peepId").val());
      },
      "Não": function() {
        $( this ).dialog( "close" );
      }
    }
  });

function deletePeep(peepId) {
	$.ajax({
	      url: "/piupiu/peeps/delete",
	      type: "POST",
	      data: '{ "peep":{ "id":  "' + peepId + '" }}',
		  contentType: "application/json",
	      async: true,
	      success: function(){
	    	  location.reload();
	      }
	   });
}

function showConfirmationDialog(peepId){
	$("#confirmationDialog").dialog("open");
	$("#peepId").val(peepId);
}

function openDialogShortUrl() {
	$("#idShortUrl").dialog({
		  height: 200,
		  width: 450,
		  autoOpen: false,
	      modal: true,
	      resizable: true
	    });
	$("#url").val('');
	$("#idShortUrl").dialog("open");
}

function bit_url(url) { 
	var username="o_1a40gtdkg";
	var key="R_ba6345da8469187f7481a4b5d0958646";
	$.ajax({
		url:"http://api.bit.ly/v3/shorten",
		data:{longUrl:url,apiKey:key,login:username},
		dataType:"jsonp",
		success:function(v) {
			var bit_url = v.data.url;
			$("#new_message").val($('#new_message').val() + bit_url);
		},
		error: function(v) {
			alert('Erro ao encurtar URL');
		}
	});
}

function shortUrl() {
	var valor = $("#url").val();
	var urlRegex = /(\b(https?|ftp|file):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/ig;
	var urltest = urlRegex.test(valor);
	if(urltest) {
		bit_url(valor);
		$("#idShortUrl").dialog("close");
	} else {
		alert("Verifique se a URL possui http ou https");
	}

}