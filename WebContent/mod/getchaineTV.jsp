<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${param.valeur != null }">
		<span class="getProtocole">Chaine</span> : Canal <c:out value="${param.valeur}" />
	</c:when>
	<c:when test="${objet != null }">
		<p>Chaine :</p>
		<div class="span2">
			<p>
				Canal
				<c:out value="${objet.getChaine() }" />
			</p>
		</div>
	</c:when>
</c:choose>