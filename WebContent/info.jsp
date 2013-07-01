<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="./W3C.jsp" />

<body>

	<c:import url="/navbar.jsp" />


	<div class="container-fluid">
		<div class="row-fluid">
			<c:import url="./pannel.jsp" />


			<div class="well span9">
				<c:if test="${listePiece.isEmpty() }">
					<div id="module">
						<center>
							<h2>
								<!--<img alt="" src="./inc/triste.png"/> -->
								Votre maison est vide&hellip;
							</h2>
						</center>
						<div class="row-fluid">
							<form method="get" action="servletVue">
								<input type="hidden" name="go" value="retour"> <input
									type="submit" value="Configurer la pièce"
									class="bn btn-primary">
							</form>
						</div>
					</div>
			</div>
			</c:if>
			<c:forEach items="${listePiece}" var="piece">
				<div id="module">
					<form action="servletPiece" method="get" id="fermer">
						<input type="submit" class="btn btn-danger" value="X" /> <input
							type="hidden" name="Piece" value="remove"> <input
							type="hidden" name="idPiece" value="${piece.value.getIdPiece() }">
					</form>
					<h1>
						<c:out value="${piece.value.getNomPiece() }" />
					</h1>


					<c:forEach items="${piece.value.getListObjets()}" var="object">
						<div class="row-fluid">
							<div class="span3">
								<p><c:out value="${object.getNomObjet()}" /></p>
							</div>
							<div class="span3 on">
								<input type="button" value="ON"
									class="btn btn-primary btn-block <c:if test="${object.getEtatObjet()==0}">disabled</c:if>">
							</div>
							<div class="span3 off">
								<input type="button" value="OFF"
									class="btn btn-danger btn-block <c:if test="${object.getEtatObjet()!=0}">disabled</c:if>">
							</div>
						</div>

			</c:forEach>
			<div class="row-fluid">
				<form method="get" action="servletVue">
					<p id="validation">
						<input type="hidden" name="go" value="edition"> <input
							type="hidden" name="Edit" value="${piece.key}"> <input
							type="submit" value="Paramétrer" class="btn btn-primary">
					</p>
				</form>
			</div>
		</div>
		</c:forEach>
	</div>

	</div>

	</div>


	<!-- /container -->

	<c:import url="./scripts.jsp" />

</body>
</html>

