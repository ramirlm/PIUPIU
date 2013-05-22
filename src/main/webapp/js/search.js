function charactersCount() {
	$(".maxlength").keyup(function(event) {
		var target = $("#content-countdown");
		var max = 140;
		var len = $(this).val().length;
		var remain = max - len;
		if (len > max) {
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
		source : function(query, process) {
			return $.get('/piupiu/users/find', {
				query : query
			}, function(data) {
				names = new Array();
				emails = new Array();

				for ( var i = 0; i < data.list.length; i++) {
					var user = data.list[i];
					names[i] = user.name;
					emails[i] = user.email;
				}
				return process(names);
			});
		},

		updater : function(item) {
			for ( var i = 0; i < names.length; i++) {
				if (names[i] === item) {
					$('#emailUser').val(emails[i]);
					var email = emails[i];
					break;
				}
			}
			var url = "/piupiu/profiles/" + email;
			window.location = url;
			return item;
		}
	});
}

function autocompleteUser() {
	var activeSearch = false;
	var searchTerm = "";
	var beginFrom = 0;
	$('#new_message').typeahead(
			{
				minLength : 3,
				source : function(query, process) {
					if (activeSearch) {
						searchTerm = query.substring(beginFrom);
						if (query.substring(beginFrom - 1, beginFrom) != "@") {
							activeSearch = false;
							beginFrom = 0;
							searchTerm = "";
						}
						if (searchTerm != "") {
							return $.get('/piupiu/users/find', {
								query : searchTerm
							}, function(data) {
								names = new Array();
								for ( var i = 0; i < data.list.length; i++) {
									var user = data.list[i];
									names[i] = '@' + user.name;
								}
								return process(names);
							});
						}
					}
				},
				highlighter : function(item) {
					var query = this.query.replace(	/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&');
					return item.replace(new RegExp('(' + query + ')', 'ig'),
							function($1, match) {
								return '<strong>' + match.replace('@', '')	+ '</strong>';
							});
				},
				updater : function(item) {
					return $('#new_message').val().substring(0, ($('#new_message').val().indexOf('@'))) + ' ' + item.replace('@','');
				},
				matcher : function(item) {
					return ~item.toLowerCase().indexOf(searchTerm.toLowerCase());
				}
			}).live('keypress', function(e) {
				// ativando no @
				if (e.which == 64 && !activeSearch) {
					activeSearch = true;
					beginFrom = e.target.selectionStart + 1;
				}
				// desativando no space
				if (e.which == 32 && activeSearch) {
					activeSearch = false;
				}
	});
}
