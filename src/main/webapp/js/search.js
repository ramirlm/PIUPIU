function charactersCount() {
	$(".maxlength").keyup(function(event){
	    var target    = $("#content-countdown");
	    var max        = 140;
	    var len     = $(this).val().length;
	    var remain    = max - len;
	    if(len > max) {
	        var val = $(this).val();
	        $(this).val(val.substr(0, max));
	        remain = 0;
	    }
	    target.html(remain + ' caracteres restantes');
	});
}

function userSearchAutoComplete() {
	$('#search').typeahead({
		minLength : 3,
	    source: function (query, process) {
	    	return $.get('/piupiu/users/find', { query: query }, function (data) {
		    	 names = new Array();
		         emails    = new Array();
		 
		         for (var i= 0; i < data.list.length; i++) {
		            var user = data.list[i];
		            names[i] = user.name;
		            emails[i] = user.email;
		         }
		        return process(names);
	        });
	    },
	  
	    updater: function(item) {
			for ( var i = 0; i < names.length; i++) {
				if(names[i] === item) {
					$('#emailUser').val(emails[i]);
					var email = emails[i];
					break;
			}
		}
		var url = "/piupiu/profiles/" + email ;
		window.location = url;
		return item;
	    }
	});
}