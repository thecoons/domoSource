<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="./W3C.jsp" />

<body>

	<c:import url="/navbar.jsp" />


	<div class="container-fluid">
		<div class="row-fluid">
			<c:import url="./pannel.jsp" />
			<div class="well span9">
				<div id="module">
					<h2>Protocole en cours d'édition</h2>
					<c:forEach items="${protocoleCourant}" var="action">
						<h3>
							<c:out value="${action.getNomTechno()}" />
							<i> ( <c:out value="${action.getNomPiece()}" /> )
							</i>
						</h3>

						<c:set var="i" value="0" />
						<c:forEach items="${action.getListAttribut() }" var="object">
							<c:if test="${i>0}">
								<span style="margin-left: 20px; margin-right: 20px;"> - </span>
							</c:if>
							<c:set var="i" value="${i+1}" />
							<c:import url="mod/get${object.key }.jsp">
								<c:param name="valeur" value="${object.value }" />
							</c:import>
							</span>
						</c:forEach>
					</c:forEach>
					<div class="row-fluid" style="padding-bottom: 10px;">
						<p id="validation">
						<form action="servletProtocole" type="get"
							style="display: inline;">
							Nom du protocole : <input type="text" name="nomProtocole"
								value=""><input type="hidden" name="submit" value="1">
							<input type="submit" value="Envoyer" class="btn btn-success">
						</form>
						<form action="servletProtocole" type="get"
							style="display: inline;">
							<input type="hidden" name="submit" value="0"> <input
								type="submit" value="Annuler" class="btn btn-danger">
						</form>
						</p>
					</div>
				</div>
			</div>

			<div class="well span9">
				<div id="module">
					<h2>Gestion des protocoles</h2>
					<c:forEach items="${listeProtocoles}" var="protocole">
							<center>
								<hr
									style="color: black; border-color: black; width: 80%; margin-top: 50px; margin-bottom: 50px;">
							</center>
						
						<h2>
							<c:out value="${protocole.key }" />
						</h2>
						<c:forEach items="${protocole.value}" var="action">
							<h3>
								<c:out value="${action.getNomTechno()}" />
								<i> ( <c:out value="${action.getNomPiece()}" /> )
								</i>
							</h3>

							<div class="row-fluid">
								<c:set var="i" value="0" />
								<c:forEach items="${action.getListAttribut() }" var="object">

									<c:if test="${i>0}">
										<span style="margin-left: 20px; margin-right: 20px;"> -
										</span>
									</c:if>
									<c:set var="i" value="${i+1}" />
									<c:import url="mod/get${object.key }.jsp">
										<c:param name="valeur" value="${object.value }" />
									</c:import>
								</c:forEach>
							</div>
						</c:forEach>

						<div class="row-fluid" style="margin-top:15px;">
							<p id="validation">
							<form method="get" action="servletProtocole" style="display: inline;">
								<input type="hidden" name="execute" value="1"><input
									type="hidden" name="nomProtocole" value="${protocole.key }">
									<input type="submit" value="Executer le protocole"
									class="bn btn-success">
							</form>
							<form action="servletProtocole" method="get" id="fermer" style="display: inline;">
						 <input
							type="hidden" name="delete" value="protocole"> <input
							type="hidden" name="nomProtocole" value="${protocole.key }">
							<input type="submit" class="btn btn-danger" value="Supprimer le protocole" />
					</form>
							</p>
						</div>

					</c:forEach>
				</div>
			</div>

		</div>

	</div>


	<!-- /container -->

	<c:import url="./scripts.jsp" />

</body>
</html>

