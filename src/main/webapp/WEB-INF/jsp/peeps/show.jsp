<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${peeps}" var="peep">
	<div class="row">
	  <div class="span8">
	    <div class="row">
	      <div class="span1">
	        <a href="#" class="thumbnail">
	            <img src="http://www.gravatar.com/avatar/${peep.hash}?s=200" alt="">
	        </a>
	      </div>
	      <div class="span4">
	        <p> ${peep.text}
	        </p>
	      </div>
	    </div>
	    <div class="row">
	      <div class="span8">
	        <p></p>
	        <p>
	          <i class="icon-user"></i> by <a href="/piupiu/profiles/${peep.author}">${peep.author}</a>
	          | <i class="icon-calendar"></i> ${peep.date}
	          | <a title="RePiar" class="icon-random" href="javascript: showRepeepDialog('${peep.author}','${peep.text}')"></a>
	          | <a title="RePiar" class="icon-random" href="javascript: showLikers('${peep.id}')"></a>
	        </p>
	      </div>
	    </div>
	  </div>
	</div>
	<hr>
</c:forEach>