package com.GetEtat.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class servletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ConnexionDB con = null; 
	private Statement st; 
	private ResultSet rs;
	private Connection conn;

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		String deconnexion =request.getParameter("deconnexion");
		String identifiant =request.getParameter("identifiant");
		String password=request.getParameter("password");

		CreateSession();
		HttpSession session = request.getSession();

		Hashtable tab = new Hashtable();

		if(deconnexion!=null){
			session.invalidate();
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}else if(authentification(identifiant, password)){
			session.setAttribute("utilisateur", identifiant);
			session.setAttribute("protocoleCourant", tab);
			this.getServletContext().getRequestDispatcher("/servletProtocole").forward(request, response);
		}else{
			session.setAttribute("utilisateur", null);
			session.setAttribute("protocoleCourant", null);
			session.setAttribute("listeProtocole", null);
			request.setAttribute("warningConnexion", "L'identifiant ou le mot de passe est invalide.");
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		 

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
	 * * Destruction de la Connection et la session une fois qu'on a termine de<br>
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
	 * @param identifiant
	 * @param mdp
	 * @return vrai si l'authentification est correct
	 */
	public boolean authentification(String identifiant,String mdp){
		String ident =null;
		String motDePasse =null;
		boolean success=false;
		try {
			ResultSet rs = st.executeQuery("SELECT identifiant,password FROM user ");
			while(rs.next()){	
				ident = rs.getString("identifiant");
				motDePasse= rs.getString("password");
				success = (ident.equals(identifiant) && motDePasse.equals(mdp));
			}	
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return success;
	}
}
