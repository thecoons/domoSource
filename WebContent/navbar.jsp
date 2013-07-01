<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<button type="button" class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="brand" href="#">Domo Project</a>
			<div class="nav-collapse collapse">
				<p class="navbar-text pull-right">
					Connecté en tant que : <a href="#" class="navbar-link">
						${utilisateur} </a> - <a href="servletLogin?deconnexion=1">déconnexion</a>
				</p>
				<ul class="nav">
					<li class="active"><a href="./servletVue?go=info">Accueil</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> Maison <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<c:forEach items="${listePiece}" var="piece">
								<li><a href="./servletVue?go=edition&Edit=${piece.key}"><c:out
											value="${piece.value.getNomPiece()}" /></a></li>
							</c:forEach>
							<li class="divider"></li>
							<li class="nav-header"></li>
							<li><a href="./servletVue?go=retour">Configuration</a></li>
						</ul></li>
					<c:if test="${warning!=null && !warning.isEmpty() }">
						<li class="dropdown btn-danger" id="radiusDanger"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown" id="caret">
								Warning </a>
							<ul class="dropdown-menu">
								<c:forEach items="${warning}" var="w">
									<li><a href="#" id="erreur">Erreur <c:out
												value="${w.getElement() }" /> <c:out
												value="${w.getParametre() }" /></a></li>
								</c:forEach>
							</ul>
						</li>
					</c:if>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>