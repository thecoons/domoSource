<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="./W3C.jsp" />
<body>
	<c:import url="./navbar.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">

			<c:import url="./pannel.jsp" />


			<!-- Import de la barre de nav et du pannel fini 
					 Loading du span de configuration objets
				-->
			<div class="well span9">
				<c:if test="${piece.getListObjets().isEmpty() }">
					<div id="module">
						<center>
							<h2>Votre pièce est vide&hellip;</h2>
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
			<c:forEach items="${piece.getListObjets() }" var="techno">
				<c:set var="objet" scope="request" value="${techno}" />
				<c:import url="blockTechno.jsp" />


			</c:forEach>
		</div>


	</div>


	</div>
	<!-- /container -->

	<!-- Importation des scripts js pour le dynamisme des dropdowns -->

	<c:import url="scripts.jsp" />
</body>
</html>

