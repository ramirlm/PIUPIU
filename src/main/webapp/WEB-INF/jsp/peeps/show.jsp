<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${peeps}" var="peep">
	<div class="row">
	  <div class="span8">
	    <div class="row">
	      <div class="span1">
	        <a href="#" class="thumbnail">
	            <img src="http://placehold.it/140x100" alt="">
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
	          <i class="icon-user"></i> by <a href="#">${peep.author}</a>
	          | <i class="icon-calendar"></i> ${peep.date}
	        </p>
	      </div>
	    </div>
	  </div>
	</div>
	<hr>
</c:forEach>