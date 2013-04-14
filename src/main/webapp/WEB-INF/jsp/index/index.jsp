<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title> Bem-vindo ao Piu-Piu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/jquery.ui.dialog.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
  	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="index">Piu-Piu</a>
          <div class="nav-collapse collapse">
            <form class="navbar-form pull-right">
              <input class="span2" type="text" placeholder="E-mail">
              <input class="span2" type="password" placeholder="Senha">
              <button type="submit" class="btn">Entrar</button>
            </form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

       <div class="container-fluid">
      <div class="row-fluid">
        <div class="span9" style="background: url('img/background.png') no-repeat; 
        	background-size: contain; height: 300px;">
          <div class="hero-unit">
            <h1>Bem-vindo ao Piu-Piu!</h1>
            <p>Site para você piar para os seus amigos Galos e ciscar com as Galinhas :)</p>
          </div>
        </div><!--/span-->
        <div class="span3">
         <form class="form-sigup" action="/piupiu/users" name="registerForm" method="post" accept-charset="utf-8">
	  	      <h2 style="margin-bottom: 1px;">Cadastre-se</h2>
	  	      <span>e veja como &eacute; divertido piar.</span>
	  	      <br/><br/>
	  	      <input type="text" class="input-block-level" placeholder="Nome" id="userName" 
	  	      	name="user.name" value="${user.name}" maxlength="45">
	  	      <input type="text" class="input-block-level" placeholder="Endereço de E-mail" id="userEmail" 
	  	      	name="user.email" value="${user.email}" maxlength="45">
	          <input type="password" class="input-block-level" placeholder="Senha" id="userPassword" 
	          	name="user.password" value="${user.password}" maxlength="45">
	          <input type="password" class="input-block-level" placeholder="Confirmação de Senha" 
	          	id="userPasswordConfirmation" name="user.passwordConfirmation" value="${user.passwordConfirmation}"
	          	maxlength="45">
	  	      <button class="btn btn-large btn-success" type="submit">Cadastre-se</button>
  	      </form>
        </div><!--/span-->
        
      </div><!--/row-->
      
      <hr>
	  <div id="push"></div>
      <footer>
        <p class="muted credit" style="text-align: center;">&copy; Piu-Piu Company 2013</p>
      </footer>
      
      <div id="messages" title="Atenção" style="display: none;">
      	<p></p>
      </div>
      
	<c:if test="${not empty errors}">
		<div id="errors">
			<c:forEach items="${errors}" var="error">
				<c:if test="${error.category eq 'name'}">
					<script type="text/javascript">
						var elem = document.getElementById("userName");
			    	    elem.style.borderColor="#CC0000";
					</script>
				</c:if>
				<c:if test="${error.category eq 'email'}">
					<script type="text/javascript">
						var elem = document.getElementById("userEmail");
			    	    elem.style.borderColor="#CC0000";
					</script>
				</c:if>
				<c:if test="${error.category eq 'password'}">
					<script type="text/javascript">
						var elem = document.getElementById("userPassword");
			    	    elem.style.borderColor="#CC0000";
					</script>
				</c:if>
				<c:if test="${error.category eq 'passwordConfirmation'}">
					<script type="text/javascript">
						var elem = document.getElementById("userPasswordConfirmation");
			    	    elem.style.borderColor="#CC0000";
					</script>
				</c:if>
			</c:forEach>
			
		</div>
	</c:if>

    </div><!--/.fluid-container-->
    
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery-1.9.1.js"></script>
    <script src="js/jquery.ui.dialog.js"></script>
    <script src="js/bootstrap.js"></script>
    
  </body>
</html>
