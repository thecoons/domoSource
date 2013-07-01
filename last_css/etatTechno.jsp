<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- est un ensemble de span2 qui montrent l'état des attributs -->

<h2>
	<c:out value="${objet.getNomObjet()}" />
</h2>

<c:forEach items="${objet.recupListAttributs()}" var="att">
	<div class="row-fluid">
		<c:import url="mod/get${att.value}.jsp" />
	</div>
</c:forEach>