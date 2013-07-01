<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Domo Project | Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="inc/bootstrap.min.css" rel="stylesheet">
<link href="inc/style.css" rel="stylesheet">
<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
	background-image: url('./inc/background.jpg');
}
</style>
</head>

<body background="./inc/background.jpg">
	<div class="container">

		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<a class="brand" href="getEtat?accueil=true">Domo Project</a>
					<ul class="nav">
						<li class="active"><a href="getEtat?accueil=true">Accueil</a></li>
						<li class="active"><a href="getEtat?tache=retour">Configuration</a></li>
					</ul>
				</div>
			</div>
		</div>


		<div class="container">
			<div class="row">
				<aside class="span4" style="background-color: transparent;">
					<div class="row">
						<div class="span4">
							<img src="inc/plan.gif" class="img">
							<blockquote>
								<p>Plan de la maison</p>
								<small>Voyants témoins</small>
							</blockquote>
						</div>

						<div class="span4">
							<h5>
								Liste des protocoles : <br> (Tâches automatisées)
							</h5>
							<br />
							<ul class="tableau">
								<li><a href="#"> Partir de la maison</a></li>
								<li><a href="#"> Préparer cuisine</a></li>
								<li><a href="#"> Se coucher</a></li>
								<li><a href="#"> </a></li>
								<li><a href="#"> Ajouter une tâche</a></li>
							</ul>

						</div>
					</div>
				</aside>
				<div class="span8">

					<h3>
						<strong> ?? PIECE ??</strong>
					</h3>
					<div id="module">
						<div class="container">
							<h4>Lumière :</h4>

							<div class="span2">
								<p>Etat :</p>
							</div>
							<div class="span2">
								<input value="ON" class="btn btn-primary btn-block"
									type="button">
							</div>

						</div>

						<p>Editer :</p>

						<form action="getEtat" method="get">

							<select name="setLum">
								<option value="ON">Allumer</option>
								<option value="OFF">Eteindre</option>
							</select> <select name="action">
								<option value="">Action</option>
								<option value="protocole">Ajouter au protocole</option>

							</select> <input type="submit" class="btn">

						</form>




					</div>
					<div id="module">
						<div class="container">
							<h4>Télé</h4>

							<div class="span2">
								<p>Etat :</p>
							</div>
							<div class="span2">
								<input value="ON" class="btn btn-primary btn-block"
									type="button">
							</div>

						</div>

						<p>Editer :</p>

						<form action="getEtat" method="get">
							<select name="setTele">
								<option value="ON">Allumer</option>
								<option value="OFF">Eteindre</option>

							</select> <select name="setChaine" id="chiffre">
								<c:forEach begin="1" end="12" var="chaine">
									<option>
										<c:out value="${chaine}" />
									</option>
								</c:forEach>
							</select> <select name="setVolume" id="chiffre">
								<c:forEach begin="0" end="100" step="10" var="vol">
									<option value="${vol}">
										<c:out value="${vol}%" />
									</option>
								</c:forEach>
							</select> <select name="action">
								<option value="">Action</option>
								<option value="protocole">Ajouter au protocole</option>

							</select> <input type="submit" class="btn">

						</form>





					</div>
					<div id="module">
						<div class="container">
							<h4>Chauffage</h4>
							<div class="span2">
								<p>Etat :</p>
							</div>
							<div class="span2">
								<input value="ON" class="btn btn-primary btn-block"
									type="button">
							</div>

						</div>
						<p>Editer :</p>
						<form action="getEtat" method="get">

							<select name="setTemp">
								<option value="ON">Allumer</option>
								<option value="OFF">Eteindre</option>
							</select> <select name="temp">
								<c:forEach begin="18" end="30" var="temperature">
									<option value="${temperature}">${temperature}°C</option>
								</c:forEach>
							</select> <select name="action">
								<option value="">Action</option>
								<option value="protocole">Ajouter au protocole</option>

							</select> <input type="submit" class="btn">

						</form>

					</div>
					<div id="module">
						<div class="container">
							<h4>Bain</h4>

							<div class="span2">
								<p>Etat :</p>
							</div>
							<div class="span2">
								<input value="ON" class="btn btn-primary btn-block"
									type="button">
							</div>
						</div>
						<p>Editer :</p>

						<form action="getEtat" method="get">
							<select name="setBain">
								<option value="ON">Allumer</option>
								<option value="OFF">Eteindre</option>

							</select> <select name="temp" id="chiffre">
								<c:forEach begin="20" end="45" var="temperature">
									<option>
										<c:out value="${temperature}" />
									</option>
								</c:forEach>
							</select> <select name="action">
								<option value="">Action</option>
								<option value="protocole">Ajouter au protocole</option>

							</select> <input type="submit" class="btn">

						</form>
					</div>
					<div id="module">
						<div class="container">
							<h4>Four</h4>

							<div class="span2">
								<p>Etat :</p>
							</div>
							<div class="span2">
								<input value="ON" class="btn btn-primary btn-block"
									type="button">
							</div>
						</div>
						<p>Editer :</p>

						<form action="getEtat" method="get">
							<select name="setFour">
								<option value="ON">Allumer</option>
								<option value="OFF">Eteindre</option>

							</select> <select name="temp" id="chiffre">
								<c:forEach begin="100" end="280" step="20" var="temperature">
									<option>
										<c:out value="${temperature}" />
									</option>
								</c:forEach>
							</select> <select name="action">
								<option value="">Action</option>
								<option value="protocole">Ajouter au protocole</option>

							</select> <input type="submit" class="btn">

						</form>
					</div>
					<div id="module">
						<div class="container">
							<h4>Volets</h4>

							<div class="span2">
								<p>Etat :</p>
							</div>
							<div class="span2">
								<input value="Ouvert" class="btn btn-primary btn-block"
									type="button">
							</div>
						</div>
						<p>Editer :</p>

						<form action="getEtat" method="get">
							<select name="setVolets">
								<option value="ouvert">Ouvrir</option>
								<option value="ferme">Fermer</option>

							</select> 
							 <select name="action">
								<option value="">Action</option>
								<option value="protocole">Ajouter au protocole</option>

							</select> <input type="submit" class="btn">

						</form>
					</div>
				</div>




			</div>


		</div>
		<!-- /container -->
	</div>


	<script src="./inc/bootstrap/js/jquery-2.0.0.min.js"></script>
	<script src="./inc/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>

