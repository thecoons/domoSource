package com.GetEtat.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.GetEtat.beans.ObjetBain;
import com.GetEtat.beans.ObjetChauffage;
import com.GetEtat.beans.ObjetFour;
import com.GetEtat.beans.ObjetLumiere;
import com.GetEtat.beans.ObjetTele;
import com.GetEtat.beans.ObjetVolet;
import com.GetEtat.beans.Piece;
import com.GetEtat.beans.struct;
import com.GetEtat.beans.warning;

public class servletVue extends HttpServlet{

	private ConnexionDB con = null; 
	private Statement st;
	private ResultSet rs;
	private Connection conn;

	Hashtable<Integer,Piece> listPiece=new Hashtable<Integer,Piece>();
	Hashtable<Integer,String> modelePiece =new Hashtable<Integer,String>(); 
	Hashtable<Integer, String> modeleTechno =new Hashtable<Integer, String>();



	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String direction=request.getParameter("go");
		String action=request.getParameter("action");
		CreateSession();

		HttpSession session = request.getSession();
		request.setAttribute("utilisateur", session.getAttribute("utilisateur"));


		listPiece.clear();
		if(initialisPiece(1))
			initi(initialisTechno(1));

		request.setAttribute("listePiece", listPiece);
		session.setAttribute("listePiece", listPiece);

		request.setAttribute("listeProtocoles", session.getAttribute("listeProtocole"));// jht.protocoles(idMaison).toHashtable());


		try{
			if(!direction.equals(null)){
				if(direction.equals("retour")){
					modelePiece=getModelePiece();
					modeleTechno=getModeleTechno();
					request.setAttribute("modelePiece", modelePiece);
					request.setAttribute("modeleTechno", modeleTechno);

				}

				if(direction.equals("edition")){
					int id = Integer.parseInt(request.getParameter("Edit"));
					request.setAttribute("piece", listPiece.get(id));

				}
				
				this.getServletContext().getRequestDispatcher("/"+direction+".jsp").forward(request, response);

			}
		}catch(NullPointerException e){
			System.err.println("Erreur NULLPOINTEUR SERVLETVUE");
			this.getServletContext().getRequestDispatcher("/info.jsp").forward(request, response);
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
	 * recupere les attributs relatives a la tele
	 * @param idObjet
	 * @param idPiece
	 * @param nom
	 */
	public void recupAttTV(int idObjet,int idPiece,String nom){
		try{
			ResultSet rs = st.executeQuery("SELECT etat,chaineTV,volume FROM listAttributs WHERE idObjet= "+idObjet);
			while (rs.next()) {
				int etatObjet = rs.getInt("etat");
				int chaine = rs.getInt("chaineTV");
				int volume = rs.getInt("volume");
				Piece p = listPiece.get(idPiece);
				p.ajoutObjet(new ObjetTele(idObjet, idPiece,nom, etatObjet, chaine, volume));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param idObjet
	 * @param idPiece
	 * @param nom
	 */
	public void recupAttBain(int idObjet,int idPiece,String nom){
		try{
			ResultSet rs = st.executeQuery("SELECT etat,temperatureBain,ecoulementBain FROM listAttributs WHERE idObjet= "+idObjet);
			while (rs.next()) {
				int etatObjet = rs.getInt("etat");
				int temperature = rs.getInt("temperatureBain");
				int ecoulementBain = rs.getInt("ecoulementBain");				
				Piece p = listPiece.get(idPiece);
				p.ajoutObjet(new ObjetBain(idObjet, idPiece, nom, etatObjet, temperature, ecoulementBain));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param idObjet
	 * @param idPiece
	 * @param nom
	 */
	public void recupAttFour(int idObjet,int idPiece,String nom){
		try{
			ResultSet rs = st.executeQuery("SELECT etat,temperatureFour FROM listAttributs WHERE idObjet= "+idObjet);
			while (rs.next()) {
				int etatObjet = rs.getInt("etat");
				int temperature = rs.getInt("temperatureFour");				
				Piece p = listPiece.get(idPiece);
				p.ajoutObjet(new ObjetFour(idObjet, idPiece, nom, etatObjet, temperature));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param idObjet
	 * @param idPiece
	 * @param nom
	 */
	public void recupAttLumiere(int idObjet,int idPiece,String nom){
		try{
			ResultSet rs = st.executeQuery("SELECT etat,luminosite FROM listAttributs WHERE idObjet= "+idObjet);
			while (rs.next()) {
				int etatObjet = rs.getInt("etat");
				int  luminosite= rs.getInt("luminosite");				
				Piece p = listPiece.get(idPiece);
				p.ajoutObjet(new ObjetLumiere(idObjet, idPiece, nom, etatObjet, luminosite));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param idObjet
	 * @param idPiece
	 * @param nom
	 */
	public void recupAttChauffage(int idObjet,int idPiece,String nom){
		try{
			ResultSet rs = st.executeQuery("SELECT etat,temperaturePiece FROM listAttributs WHERE idObjet= "+idObjet);
			while (rs.next()) {
				int etatObjet = rs.getInt("etat");
				int temperature = rs.getInt("temperaturePiece");				
				Piece p = listPiece.get(idPiece);
				p.ajoutObjet(new ObjetChauffage(idObjet, idPiece, nom, etatObjet, temperature));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void recupAttVolet(int idObjet,int idPiece,String nom){
		try{
			ResultSet rs = st.executeQuery("SELECT etat FROM listAttributs WHERE idObjet= "+idObjet);
			while (rs.next()) {
				int etatObjet = rs.getInt("etat");				
				Piece p = listPiece.get(idPiece);
				p.ajoutObjet(new ObjetVolet(idObjet, idPiece, nom, etatObjet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * initialise la table de hachage des pieces a partir de la bdd 
	 * @param idMaison de la maison
	 * @return vrai si l'initialisation a ete correctement faite
	 */
	public boolean initialisPiece(int idMaison){
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM piece where IDmaison=" + idMaison);
			while(rs.next()){	
				int id = rs.getInt("ID");				
				String nom = rs.getString("nom");
				int tempMin = rs.getInt("temperatureMin");
				int tempMax = rs.getInt("temperatureMax");
				Piece p = new Piece(id,nom,tempMin,tempMax);			
				listPiece.put(id,p);
			}
		} catch (SQLException e) {
			System.err.println("ERR BDD");
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * recuperation de toutes les technos de la piece  
	 * @param idPiece id de la piece 
	 * @return 
	 */
	private Vector<struct> initialisTechno(int idMaison){
		Vector<struct> s= new Vector<struct>();
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM objet, piece WHERE piece.ID=idPiece AND IDmaison=" + idMaison);
			while(rs.next()){
				String idObjet = rs.getString("idObjet");
				String idPiece = rs.getString("idPiece");
				String idTypeObjet = rs.getString("idTypeObjet");
				String nom = rs.getString("nomObjet");
				struct st=new struct(idObjet,idPiece,idTypeObjet,nom);
				s.addElement(st);
			}
		} catch (SQLException e) {
			System.out.println("ERR BDD 2");
			e.printStackTrace();
		}
		return s;
	}
	public void initi(Vector<struct> s){
		for(struct str : s){
			int idObjet = Integer.parseInt(str.idObjet);
			int idPiece = Integer.parseInt(str.idPiece);
			int idTypeObjet = Integer.parseInt(str.idTypeObjet);
			String nom = str.nom;
			switch(idTypeObjet){
			case 1 : recupAttTV(idObjet, idPiece, nom);break;
			case 2 : recupAttLumiere(idObjet, idPiece, nom);break;
			case 3 : recupAttChauffage(idObjet, idPiece, nom);break;
			case 4 : recupAttVolet(idObjet, idPiece, nom);break;
			case 5 : recupAttBain(idObjet, idPiece, nom);break;
			case 6 : recupAttFour(idObjet, idPiece, nom);break;
			}
		}
	}
	public Hashtable<Integer, String> getModelePiece(){
		Hashtable<Integer,String> tab =new Hashtable<Integer, String>();
		try{
			ResultSet rs = st.executeQuery("SELECT * FROM piecetype");
			while(rs.next()){
				int id =rs.getInt(1);
				String nom=rs.getString(2);
				tab.put(id, nom);

			}
		}catch(SQLException e){}

		return tab;


	}
	public Hashtable<Integer, String> getModeleTechno(){
		Hashtable<Integer, String> tab = new Hashtable<Integer, String>();
		try{
			ResultSet rs = st.executeQuery("SELECT * FROM typeObjet");
			while(rs.next()){
				int id =rs.getInt(1);
				String nom=rs.getString(2);
				tab.put(id, nom);
			}
		}catch (SQLException e) {}

		return tab;
	}
}