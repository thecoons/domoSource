<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- est une balise form contenue par le 2nd row-fluid -->


<form method="get" action="servletRedirection">
	<div id="styled">
		<c:forEach items="${objet.recupListAttributs()}" var="att">
			<select name="${att.value}">
				<c:import url="mod/${att.value}.jsp" />

			</select>

		</c:forEach>
		<select name="action">
			<option value="action">Action</option>
			<option value="protocole">Ajouter au protocole</option>

		</select>
	</div>
	<input type="hidden" name="Techno" value="edit" /> <input type="hidden"
		name="idtypeTechno" value="${objet.getIdTypeObjet() }" /> <input
		type="hidden" name="idTechno" value="${objet.getIdObjet() }" /> <input
		type="hidden" name="go" value="edition" /> <input type="hidden"
		name="Edit" value="${objet.getIdPiece() }" />
	<p id="validation">
		<input type="submit" class="btn btn-success" />
	</p>
</form>
