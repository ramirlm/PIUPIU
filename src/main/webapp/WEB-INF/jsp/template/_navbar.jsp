<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="brand" href="/piupiu/profiles">Piu-Piu</a>
      <div class="nav-collapse collapse">
      	<ul class="nav">
          	<li class="active"><a href="/piupiu/profiles">Home</a></li>
          	<li><a href="#sobre">Sobre</a></li>
        	</ul>
      	<form class="navbar-search pull-left" action="/piupiu/profiles/" method="get">
      <input type="text" class="search-query" placeholder="Pesquisar" id="search" autocomplete="off">
      <span class="icon-search"></span>
      <input type="hidden" name="email" id="emailUser">
    </form>
        	<p class="navbar-text pull-right">
        		Logado como ${userSession.user.email}
        		<a class="icon-white icon-off" style="margin-left: 20px;" href="${ctx}/logout" title="Sair"></a>
      	</p>
      </div><!--/.nav-collapse -->
    </div>
  </div>
</div>