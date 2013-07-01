<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="module">
	<form action="servletTechno" method="get" id="fermer">
		<input type="submit" class="btn btn-danger" value="X" /> <input
			type="hidden" name="Techno" value="remove"> <input
			type="hidden" name="go" value="edition"> <input type="hidden"
			name="Edit" value="${objet.getIdPiece() }"> <input
			type="hidden" name="idTechno" value="${objet.getIdObjet() }">
	</form>
	<div class="row-fluid">
		<c:import url="etatTechno.jsp"></c:import>
	</div>
	<!--  importe directement des form -->
	<div class="row-fluid">
		<c:import url="editTechno.jsp"></c:import>
	</div>
</div>