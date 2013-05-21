<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${listAll}" var="follower">
	<div class="row">
	  <div class="span8">
	    <div class="row">
	      <div class="span1">
	        <a href="/piupiu/profiles/${follower.userEmail}" class="thumbnail">
	            <img src="http://www.gravatar.com/avatar/${follower.hash}?s=200" alt="">
	        </a>
	      </div>
	      <div class="span4">
	        <p> ${follower.userEmail}
	        </p>
	      </div>
	    </div>
	  </div>
	</div>
	<hr>
</c:forEach>