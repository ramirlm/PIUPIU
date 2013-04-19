
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Piu-Piu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <style type="text/css">

      /* Sticky footer styles
      -------------------------------------------------- */

      html,
      body {
        height: 100%;
        /* The html and body elements cannot have any padding or margin. */
      }

      /* Wrapper for page content to push down footer */
      #wrap {
        min-height: 100%;
        height: auto !important;
        height: 100%;
        /* Negative indent footer by it's height */
        margin: 0 auto -60px;
      }

      /* Set the fixed height of the footer here */
      #push,
      #footer {
        height: 60px;
      }
      #footer {
        background-color: #f5f5f5;
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 767px) {
        #footer {
          margin-left: -20px;
          margin-right: -20px;
          padding-left: 20px;
          padding-right: 20px;
        }
      }



      /* Custom page CSS
      -------------------------------------------------- */
      /* Not required for template or sticky footer method. */

      #wrap > .container {
        padding-top: 60px;
      }
      .container .credit {
        margin: 20px 0;
      }

      code {
        font-size: 80%;
      }

    </style>
    <link href="../css/bootstrap-responsive.css" rel="stylesheet">
	<link href="../css/typeahead.js-bootstrap.css" rel="stylesheet">

	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
  </head>

  <body>


    <!-- Part 1: Wrap all page content here -->
    <div id="wrap">

      <!-- Fixed navbar -->
      <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="brand" href="/piupiu/profiles/show">Piu-Piu</a>
            <div class="nav-collapse collapse">
            	<ul class="nav">
                	<li class="active"><a href="/piupiu/profiles/show">Home</a></li>
                	<li><a href="#sobre">Sobre</a></li>
              	</ul>
            	<form class="navbar-search pull-left" action="/piupiu/users/show" method="get">
		          <input type="text" class="search-query" placeholder="Pesquisar" id="search" autocomplete="off">
		          <input type="hidden" name="id" id="idUser">
		        </form>
              	<p class="navbar-text pull-right">
              		Logado como <a href="#" class="navbar-link">${userSession.user.email}</a>
            	</p>
            </div><!--/.nav-collapse -->
          </div>
        </div>
      </div>

      <!-- Begin page content -->
      <div class="container">
        <div class="span3 well">
          <div class="row">
            <div class="span1">
              <a href="" class="thumbnail"><img src="http://critterapp.pagodabox.com/img/user.jpg" alt="photo_profile"></a>
              <p>${user.email }</p>
            </div>
            <div class="span3">
	   			<p><strong>${user.name }</strong></p>
			    <p>
				    <span class=" badge badge-follow">10 Seguindo</span>
  				    <span class=" badge badge-info">15 Seguidores</span>
			    </p>
		    </div>
          </div>
        </div>
        <div class="span6 well">
  	      <div id="wall">
          </div>
        </div>

      </div> <!-- container -->

      <div id="push"></div>

      <div id="footer">
        <div class="container">
          <p class="muted credit">&copy; Piu-Piu Company 2013</p>
        </div>
      </div>


    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../js/jquery-1.8.3.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script src="../js/bootstrap-typeahead.js"></script>
    <script src="../js/peeps.js"></script>
    <script>
    $(function() {
    	loadPeeps("${user.email}");
    	
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
        
        $('#search').typeahead({
        	minLength : 3,
            source: function (query, process) {
                return $.get('../users/find', { query: query }, function (data) {
                	 names = new Array();
                     ids    = new Array();
             
                     for (var i= 0; i < data.list.length; i++) {
                        var user = data.list[i];
                        names[i] = user.name;
                        ids[i]    = user.id;
                     }
                    return process(names);
                });
            },
            updater: function(item) {
				for ( var i = 0; i < names.length; i++) {
					if(names[i] === item) {
						$('#idUser').val(ids[i]);
						break;
					}
				}
            	this.$element[0].form.submit();
                return item;
            }
        });
    });
    
    </script>

  </body>
</html>