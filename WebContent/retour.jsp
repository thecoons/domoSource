<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="./W3C.jsp" />

<body>
	<c:import url="./navbar.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">

		<c:import url="./pannel.jsp"/>

			<div class="well span9">
				<div id="module">
					<h1 style="margin-top:0px;">
						<strong> Actions </strong>
					</h1>
					<form method="get" action="servletPiece">

						<label for="piece"> Nouvelle Pièce :</label> <select
							name="idtypePiece">
							<c:forEach items="${modelePiece }" var="piece">
								<option value="${piece.key}">
									<c:out value="${piece.value}" />
							</c:forEach>

						</select> <input type="text" name="nomPiece" id="nomPiece" placeholder="Nom de la pièce..." required /> <input
							class="btn" type="submit" value="Créer nouvelle pièce"> <input
							type="hidden" name="Piece" value="add">
					</form>

					<form method="get" action="servletTechno">


						<label for="objet"> Nouvel objet :</label>
						<select name="idPiece">
								
							<c:forEach items="${listePiece}" var="piece">
								<option value="${piece.key}">
									<c:out value="${piece.value.getNomPiece() }" />

								</option>
							</c:forEach>
						</select> <select name="idtypeTechno">
							<c:forEach items="${modeleTechno}" var="techno">
								<option value="${techno.key}">
									<c:out value="${techno.value}" />
								</option>
							</c:forEach>
						</select> <input type="text" name="nomTechno" id="nomTechno" placeholder="Nom de l'objet..." required /> <input
							class="btn" type="submit" value="Créer un nouvel objet">
						<input type="hidden" name="Techno" value="add">

					</form>


				</div>


			</div>
			<!-- /container -->
		</div>
	</div>

	<c:import url="./scripts.jsp"/>

</body>
</html>

