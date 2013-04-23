<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${peep.likers}" var="liker">
	<div class="row">
	  <div class="span8">
	    <div class="row">
	      <div class="span1">
	        <a href="#" class="thumbnail">
	            <img src="http://www.gravatar.com/avatar/${liker.hash}?s=200" alt="">
	        </a>
	      </div>
	      <div class="span4">
	        <p> ${liker.userEmail}
	        </p>
	      </div>
	    </div>
	  </div>
	</div>
	<hr>
</c:forEach>