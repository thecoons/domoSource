<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<aside class="span3	">
	<div class="row" id="modulePannel">
		<div class="hero-unit" id="pannel">
			<h1>${piece.getNomPiece() }</h1>
			<p>
				<small>Page de configuration</small><br> <a
					href="./servletVue?go=info"> Retour à l'accueil </a>
			</p>
		</div>

	</div>
	<div class="row" id="modulePannel">

		<div class="well sidebar-nav" id="pannel">
			<ul class="nav nav-list">
			
				<li class="nav-header">Listes des protocoles</li>
				<c:forEach items="${listeProtocoles}" var="protocole">
					<li><a href="servletProtocole?execute=1&nomProtocole=${protocole.key }"><c:out value="${protocole.key }" /></a></li>
				</c:forEach>
				<li class="nav-header"><a href="./servletProtocole">
						Gérer les protocoles </a></li>



			</ul>
		</div>

	</div>


</aside>
<!--/.well -->
