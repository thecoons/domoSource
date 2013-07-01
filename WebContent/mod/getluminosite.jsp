<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- est un string qui renvoie l'état de la luminosite -->


<c:choose>
	<c:when test="${param.valeur != null }">
		<span class="getProtocole">Luminosité : </span><c:out value="${param.valeur}" /> %
	</c:when>
	<c:otherwise>
		<p>Luminosité :</p>
		<div class="span2">
			<p>
				<c:out value="${objet.getLuminosite() }" />
				%
			</p>
		</div>
	</c:otherwise>
</c:choose>

