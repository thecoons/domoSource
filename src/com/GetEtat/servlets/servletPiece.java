package com.GetEtat.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servletPiece extends HttpServlet {

	private static final long serialVersionUID = 6910010010118082417L;
	private ConnexionDB con = null;
	private Statement st;
	private ResultSet rs;
	private Connection conn;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String Piece=request.getParameter("Piece");
		String idTypePiece = request.getParameter("idtypePiece");
		String idElemPiece = request.getParameter("idPiece");
		String nomPiece=request.getParameter("nomPiece");
		String allPiece=request.getParameter("allPiece");


		CreateSession();
		try{
			if(Piece.equals("add")){
				int idtypePiece= Integer.parseInt(idTypePiece);
				createPiece(1,idtypePiece,nomPiece);
			}else{				
				if(Piece.equals("remove")){
					int idPiece= Integer.parseInt(idElemPiece);
					try{
						if(!allPiece.equals(null)){
							deleteAllPiece();
						}
					}catch(NullPointerException e) {}
					deletePiece(idPiece);
				}else if(Piece.equals("edit")){
					int idPiece= Integer.parseInt(idElemPiece);
					editPiece(nomPiece, idPiece);
				}
			}	
		}catch(NullPointerException e) {}
		this.getServletContext().getRequestDispatcher("/servletVue").forward(request, response);
		Destroy();
	}

	/**
	 * Creation d'une connexion avec la bdd
	 */
	public void CreateSession() {
		try {
			con = new ConnexionDB();
			Connection conn = con.connect;
			st = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * * Destruction de la Connection et la session une fois qu'on a termine de
	 * communiquer avec la bdd
	 */
	public void Destroy() {
		try {
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
		}
	}

	/**
	 * definir le prochain id a affecter pour un nouveau objet
	 * 
	 * @param table
	 * @return id
	 */
	public int selectNewId(String table) {
		int id = 0;
		try {
			rs = st.executeQuery("select max(id) from " + table);
			while (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * inserer les donnees d'une piece dans la bdd
	 * 
	 * @param table
	 * @param id
	 * @param idMaison
	 * @param idP
	 * @param nom
	 */
	public void insertPieceIntoBdd(String table, int id, int idMaison, int idP,
			String nom) {
		try {
			st.executeUpdate("insert into " + table
					+ " (ID,IDmaison,type,nom)values (" + id + "," + idMaison
					+ "," + idP + ",'" + nom + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cree une piece dans la bdd
	 * 
	 * @param idMaison
	 *            id de la maison actuelle
	 * @param idP
	 *            id de la piece
	 */
	public void createPiece(int idMaison, int idP, String nom) {
		int id = 0;
		id = selectNewId("piece");
		insertPieceIntoBdd("piece", id, idMaison, idP, nom);
	}

	/**
	 * supprime une piece donnee
	 * 
	 * @param idPiece
	 *            id de la piece a supprimer
	 */
	public void deletePiece(int idPiece) {
		try {
			st.executeUpdate("Delete FROM piece WHERE ID = " + idPiece);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * suppression de toutes les pieces
	 */
	public void deleteAllPiece() {
		try {
			st.executeUpdate("Delete * FROM piece");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editPiece(String nom, int id) {
		try {
			st.executeUpdate("Update piece set nom " + nom + " where ID =" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
