<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="./W3C.jsp" />

<body>

	<c:import url="./navbar.jsp" />

	<div class="container-fluid">
		<div class="row-fluid">

			<c:import url="./pannel.jsp" />
			<!--/span-->

			<div class="well span9">

				<div id="module">
					<div class="row-fluid">
						<h2><strong> ${piece.getNomPiece() }</strong></h2>

						<div class="span2">
							<p>Etat :</p>
						</div>
						<div class="span2" id="On">
							<input value="ON" class="btn btn-primary btn-block disabled"
								type="button">
						</div>
						<div class="span2" id="Off">
							<input value="OFF" class="btn btn-danger btn-block disabled"
								type="button">
						</div>

					</div>
					<div class="row-fluid">
						<p>Editer :</p>

						<form action="getEtat" method="get">
							<div class="styled">
								<select name="setLum">
									<option value="ON">Allumer</option>
									<option value="OFF">Eteindre</option>
								</select> <select name="action">
									<option value="">Action</option>
									<option value="protocole">Ajouter au protocole</option>

								</select>

							</div>
							<p id="validation">
								<input type="submit" class="btn btn-success">
							</p>
						</form>



					</div>
				</div>
				<div id="module">
					<div class="row-fluid">
						<h2>Lumière :</h2>

						<div class="span2">
							<p>Etat :</p>
						</div>
						<div class="span2" id="On">
							<input value="ON" class="btn btn-primary btn-block disabled"
								type="button">
						</div>
						<div class="span2" id="Off">
							<input value="OFF" class="btn btn-danger btn-block disabled"
								type="button">
						</div>

					</div>
					<div class="row-fluid">
						<p>Editer :</p>

						<form action="getEtat" method="get">
							<div class="styled">
								<select name="setLum">
									<option value="ON">Allumer</option>
									<option value="OFF">Eteindre</option>
								</select> <select name="action">
									<option value="">Action</option>
									<option value="protocole">Ajouter au protocole</option>

								</select>

							</div>
							<p id="validation">
								<input type="submit" class="btn btn-success">
							</p>
						</form>



					</div>
				</div>
			</div>
		</div>
		<!--/row-->



	</div>
	<!--/.fluid-container-->




</body>
</html>