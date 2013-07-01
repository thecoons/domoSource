<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:choose>
	<c:when test="${param.valeur != null }">
		<span class="getProtocole">Volume : </span><c:out value="${param.valeur }" /> %
	</c:when>
	<c:otherwise>
		<p>Volume :</p>
		<div class="span2">
			<p>
				<c:out value="${objet.getVolume() }" /> %
			</p>
		</div>
	</c:otherwise>
</c:choose>
