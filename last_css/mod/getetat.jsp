<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Maybe problemes d'alignement a cause du manque de id on/off css -->
<div class="span2">
	<p>Etat :</p>
</div>
<c:choose>
<c:when test="${objet.getEtatObjet()==1 }">
<input value="ON" class="btn btn-primary btn-block" type="button">
</c:when>
<c:otherwise>
<input value="OFF" class="btn btn-danger btn-block" type="button">
</c:otherwise>
</c:choose>

