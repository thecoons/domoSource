<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Maybe problemes d'alignement a cause du manque de id on/off css -->
<c:choose>
	<c:when test="${param.valeur!=null}">
			<span class="getProtocole">Etat : </span><c:choose>
			<c:when test="${param.valeur==1}">
				<input value="ON" class="btn btn-primary btn-block" type="button" style="width:100px; display:inline;">
			</c:when>
			<c:otherwise>
				<input value="OFF" class="btn btn-danger btn-block" type="button" style="width:100px; display:inline;">
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${objet.getEtatObjet()!=null}">
		<div class="span2">
			<p>Etat :</p>
		</div>
		<div class="span2">
			<c:choose>
				<c:when test="${objet.getEtatObjet()==1}">
					<input value="ON" class="btn btn-primary btn-block" type="button">
				</c:when>
				<c:otherwise>
					<input value="OFF" class="btn btn-danger btn-block" type="button">
				</c:otherwise>
			</c:choose>
		</div>
	</c:when>
</c:choose>