function peep(){
	var message = $('#new_message').val();
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
    
function loadPeeps() {
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

$("#idShowLikes").dialog({
	  height: 200,
	  width: 350,
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
	    },
	    error: function(data, status, e) {
    	  //Show Error Div 
	    }
	});
}