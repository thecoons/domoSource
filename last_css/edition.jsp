<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="./W3C.jsp"/>
<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="./navbar.jsp"/>
				
				<!-- Import de la barre de nav et du pannel fini 
					 Loading du span de configuration objets
				-->
				<div class="well span9">
						
					<c:forEach items="${piece.getListObjets() }" var="techno">
						<c:set var="objet" scope="request" value="${techno}" />
						<h4><c:out value="${techno.getNomObjet() }" /></h4>
						<c:import url="blockTechno.jsp" />


					</c:forEach>
				</div>


			</div>


		</div>
		<!-- /container -->
		
		<!-- Importation des scripts js pour le dynamisme des dropdowns -->

	<c:import url="scripts.jsp"/>
</body>
</html>

