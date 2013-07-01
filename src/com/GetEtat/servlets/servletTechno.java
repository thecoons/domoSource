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

import com.GetEtat.beans.*;

public class servletTechno extends HttpServlet{
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

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String Techno = request.getParameter("Techno");
		String idTypeTechno = request.getParameter("idtypeTechno");
		String idElemTechno = request.getParameter("idTechno");
		String nomTechno=request.getParameter("nomTechno");
		String idElemPiece = request.getParameter("idPiece");

		String Etat = request.getParameter( "etat");
		String tempPiece = request.getParameter("temperaturePiece");
		String vol = request.getParameter("volume");
		String chaine = request.getParameter("chaineTV");
		String lum = request.getParameter("luminosite");
		String temperatureBain = request.getParameter("temperatureBain");
		String temperatureFour = request.getParameter("temperatureFour");
		String ecoulementBain = request.getParameter("ecoulementBain");


		if(Techno == null) Techno = (String) request.getAttribute("Techno");	// pour servletProtocole.java
		Vector<Hashtable<String, Object>> listAction = (Vector<Hashtable<String, Object>>) request.getAttribute("listAction"); // pour servletProtocole.java

		CreateSession();

		try{
			if(Techno.equals("add")){				
				int idPiece= Integer.parseInt(idElemPiece);
				int idTypeTech= Integer.parseInt(idTypeTechno);
				int idObject = insertObjetIntoBdd(idTypeTech,idPiece,nomTechno);
				switch(idTypeTech){
				case 1 : insertTV(idObject,0, 0, 0);break;
				case 2 : insertLumiere(idObject, 0, 100);break;
				case 3 : insertChauffage(idObject,0, 25);break;
				case 4 : insertVolet(idObject,0);break;
				case 5 : insertBain(idObject,0, 0, 20);break;
				case 6 : insertFour(idObject,0, 150);break;
				}

			}else if(Techno.equals("remove")){
				int idTechno= Integer.parseInt(idElemTechno);
				deleteObjet(idTechno);

			}else if(Techno.equals("edit")){
				int idTypeTech= Integer.parseInt(idTypeTechno);
				int idTech= Integer.parseInt(idElemTechno);
				int etat= Integer.parseInt(Etat);

				switch(idTypeTech){
				case 1 : setTV(request, etat, chaine, vol, idTech); break;
				case 2 : setLumiere(request, etat, lum, idTech); break;
				case 3 : setChauffage(request, etat, tempPiece, idTech); break;
				case 4 : updateVolet(etat, idTech); break;
				case 5 : setBain(request, etat, ecoulementBain, temperatureBain, idTech); break;
				case 6 : setFour(request, etat, temperatureFour, idTech); break;
				}
			}else if(Techno.equals("executeProtocole")){
				for (Hashtable<String, Object> action : listAction){
					String ATechno = (String) action.get("Techno");
					String AidTypeTechno = (String) action.get("idtypeTechno");
					String AidElemTechno = (String) action.get("idTechno");

					String AEtat = (String) action.get("etat");
					String AtempPiece = (String) action.get("temperaturePiece");
					String Avol = (String) action.get("volume");
					String Achaine = (String) action.get("chaineTV");
					String Alum = (String) action.get("luminosite");
					String AtemperatureBain = (String) action.get("temperatureBain");
					String AtemperatureFour = (String) action.get("temperatureFour");
					String AecoulementBain = (String) action.get("ecoulementBain");
					
					if(ATechno.equals("edit")){	// uniquement l'�dition pour les actions des protocoles
						int idTypeTech= Integer.parseInt(AidTypeTechno);
						int idTech= Integer.parseInt(AidElemTechno);
						
						int etat= Integer.parseInt(AEtat);
						//if(AEtat != null)	etat= Integer.parseInt(AEtat);
						//else				etat= get("etat", idTech);
						
						switch(idTypeTech){
						case 1 : 
							//if(Achaine == null)				Achaine= getS("chaineTV", idTech);
							//if(Avol == null)				Avol= getS("volume", idTech);
							setTV(request, etat, Achaine, Avol, idTech); break;
							
						case 2 : 
							//if(Alum == null)				Alum= getS("luminosite", idTech);
							setLumiere(request, etat, Alum, idTech); break;
							
						case 3 : 
							//if(tempPiece == null)			tempPiece= getS("temperaturePiece", idTech);
							setChauffage(request, etat, AtempPiece, idTech); break;
							
						case 4 : updateVolet(etat, idTech); break;
						case 5 : 
							//if(AecoulementBain == null)		AecoulementBain= getS("temperatureBain", idTech);
							//if(AtemperatureBain == null)	AtemperatureBain= getS("ecoulementBain", idTech); // pas en bdd??
							setBain(request, etat, AecoulementBain, AtemperatureBain, idTech); break;
							
						case 6 : 
							//if(AtemperatureFour == null)	AtemperatureFour= getS("temperatureFour", idTech);
							setFour(request, etat, AtemperatureFour, idTech); break;
						}
					}					
				}
			}
		}catch(NullPointerException e){}
		this.getServletContext().getRequestDispatcher("/servletVue").forward(request, response);
		Destroy();

	}
	
	private void setTV(HttpServletRequest request, int etat, String chaine, String vol, int idTech){
		int chaineTV= Integer.parseInt(chaine);
		int volume= Integer.parseInt(vol);
		ObjetTele tv=new ObjetTele();
		//gestion des warning
		Vector<warning> w = (Vector<warning>) request.getAttribute("warning");
		if(w==null)
			w = new Vector<warning>();
		if(!tv.setVolume(volume))
			w.add(new warning("tv", "volume"));
		if(!tv.setChaine(chaineTV))
			w.add(new warning("tv", "chaine"));
		request.setAttribute("warning", w);
		//update de la bdd
		if(tv.setVolume(volume) && tv.setChaine(chaineTV))
			updateTV(etat, volume, chaineTV, idTech);
	}
	
	private void setLumiere(HttpServletRequest request, int etat, String lum, int idTech){
		int luminosite= Integer.parseInt(lum);
		ObjetLumiere l =new ObjetLumiere();
		//gestion des warning
		Vector<warning> w = (Vector<warning>) request.getAttribute("warning");
		if(w==null)
			w = new Vector<warning>();
		if(!l.setLuminosite(luminosite))
			w.add(new warning("lumiere", "luminosite"));
		request.setAttribute("warning", w);
		//update de la bdd
		if(l.setLuminosite(luminosite))
			updateLumiere(etat, luminosite, idTech);
	}
	
	private void setChauffage(HttpServletRequest request, int etat, String tempPiece, int idTech){
		int temperature = Integer.parseInt(tempPiece);
		ObjetChauffage c =new ObjetChauffage();
		System.out.println(tempMin(idTech));
		System.out.println(tempMax(idTech));
		//gestion des warning
		Vector<warning> w = (Vector<warning>) request.getAttribute("warning");
		if(w==null)
			w = new Vector<warning>();
		if(!c.setTemperatureChauffage(temperature, tempMin(idTech), tempMax(idTech)))
			w.add(new warning("chauffage", "temperature"));
		request.setAttribute("warning", w);
		//update de la bdd
		if(c.setTemperatureChauffage(temperature, tempMin(idTech), tempMax(idTech)))
			updateChauffage(etat, temperature, idTech);
	}
	
	private void setBain(HttpServletRequest request, int etat, String ecoulementBain, String temperatureBain, int idTech){
		int ecoulBain= Integer.parseInt(ecoulementBain);
		int tempBain= Integer.parseInt(temperatureBain);
		ObjetBain b =new ObjetBain();
		//gestion des warning
		Vector<warning> w = (Vector<warning>) request.getAttribute("warning");
		if(w==null)
			w = new Vector<warning>();
		if(!b.setTemperatureBain(tempBain))
			w.add(new warning("bain", "temperature"));
		if(!b.setEtatEcoulement(ecoulBain))
			w.add(new warning("bain", "ecoulement"));
		request.setAttribute("warning", w);
		//update de la bdd
		if(b.setTemperatureBain(tempBain) && b.setEtatEcoulement(ecoulBain))
			updateBain(etat, ecoulBain, tempBain, idTech);
	}
	
	private void setFour(HttpServletRequest request, int etat, String temperatureFour, int idTech){
		int tempFour= Integer.parseInt(temperatureFour);
		ObjetFour f= new ObjetFour();
		//gestion des warning
		Vector<warning> w = (Vector<warning>) request.getAttribute("warning");
		if(w==null)
			w = new Vector<warning>();
		if(!f.setTemperatureFour(tempFour))
			w.add(new warning("four", "temperature"));
		request.setAttribute("warning", w);
		//update de la bdd
		if(f.setTemperatureFour(tempFour))
			updateFour(etat, tempFour, idTech);
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
	 * * Destruction de la Connection et la session une fois qu'on a termine de communiquer avec la bdd
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
	 * @param table
	 * @return id
	 */
	public int selectNewId(String table){
		int id=0;
		try {
			rs = st.executeQuery("select max(id) from "+table);
			while (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * inserer les donnees d'une techno dans la bdd 
	 * @param table de la techno a modifier
	 * @param idt id de la techno 
	 * @param idP id de la piece a laquelle elle est rattache 
	 * @param nom nom de la techno
	 */
	public int insertObjetIntoBdd(int idt,int idP,String nom){
		int idObject=0;
		try{
			st.executeUpdate("insert into objet (idPiece,idTypeObjet,nomObjet) values (" + idP + ","+idt+",'"+nom+"')");
			rs = st.executeQuery("select idObjet from objet where nomObjet="+"'"+ nom +"'"+" and idTypeObjet="+"'"+idt+"'");
			rs.first();
			idObject = rs.getInt("idObjet");
			rs.close();
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idObject;
		
	}

	/**
	 * inserer dans la BDD la techno TV
	 * @param etat
	 * @param volume
	 * @param chaine
	 */
	public void insertTV(int idObject,int etat,int volume,int chaine){
		try{
			st.executeUpdate("insert into listAttributs (idObjet,etat,chaineTV,volume) values ("+ idObject +"," + etat + ","+ chaine +","+ volume+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * inserer dans la BDD la techno four
	 * @param etat
	 * @param temperatureFour
	 */
	public void insertFour(int idObject, int etat,int temperatureFour){
		try{
			st.executeUpdate("insert into listAttributs (idObjet,etat,temperatureFour) values ("+ idObject +","+ etat + ","+ temperatureFour+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * inserer dans la BDD la techno Bain
	 * @param etat
	 * @param ecoulementBain
	 */
	public void insertBain(int idObject,int etat,int ecoulementBain,int temperatureBain){
		try{
			st.executeUpdate("insert into listAttributs (idObjet,etat,temperatureBain,ecoulementBain) values ("+idObject+"," + etat + ","+ ecoulementBain+","+temperatureBain+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * inserer dans la BDD la techno lumiere
	 * @param etat
	 * @param luminosite
	 */
	public void insertLumiere(int idObject,int etat,int luminosite){
		try{
			st.executeUpdate("insert into listAttributs (idObjet,etat,luminosite) values ("+idObject+"," + etat + ","+ luminosite+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * inserer dans la BDD la techno chauffage
	 * @param etat
	 * @param luminosite
	 */
	public void insertChauffage(int idObject,int etat,int temperature){
		try{
			st.executeUpdate("insert into listAttributs (idObjet,etat,temperaturePiece) values ("+idObject+"," + etat + ","+ temperature+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * inserer dans la BDD la techno volet
	 * @param etat
	 */
	public void insertVolet(int idObject,int etat){
		try{
			st.executeUpdate("insert into listAttributs (idObjet,etat) values ("+idObject+"," + etat+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param etat
	 * @param volume
	 * @param chaine
	 */
	public void updateTV(int etat,int volume,int chaine,int idTech){
		try{
			st.executeUpdate("Update listAttributs SET etat= "+etat+", volume= "+volume+", chaineTV= "+chaine+" WHERE idObjet= "+idTech);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param etat
	 * @param ecoulement
	 * @param temperatureBain
	 * @param idTech
	 */
	public void updateBain(int etat,int ecoulement,int temperatureBain,int idTech){
		try{
			st.executeUpdate("Update listAttributs SET etat= "+etat+", temperatureBain= "+temperatureBain+", ecoulement= "+ecoulement+" WHERE idObjet= "+idTech);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param etat
	 * @param luminosite
	 * @param idTech
	 */
	public void updateLumiere(int etat,int luminosite,int idTech){
		try{
			st.executeUpdate("Update listAttributs SET etat= "+etat+", luminosite= "+luminosite+" WHERE idObjet= "+idTech);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param etat
	 * @param temperatureFour
	 * @param idTech
	 */
	public void updateFour(int etat,int temperatureFour,int idTech){
		try{
			st.executeUpdate("Update listAttributs SET etat= "+ etat +", temperatureFour= "+temperatureFour+" WHERE idObjet= "+idTech);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param etat
	 * @param idTech
	 */
	public void updateVolet(int etat,int idTech){
		try{
			st.executeUpdate("Update listAttributs set etat= "+ etat +" WHERE idObjet= "+idTech);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param etat
	 * @param temperature
	 * @param idTech
	 */
	public void updateChauffage(int etat,int temperature,int idTech){
		try{
			st.executeUpdate("Update listAttributs SET etat= "+ etat +", temperaturePiece= "+temperature+" WHERE idObjet= "+idTech);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param idTechno
	 */
	public void deleteObjet(int idTechno){
		try{
			st.executeUpdate("Delete FROM objet WHERE idObjet= "+idTechno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *get temp max 
	 */
	public int tempMax(int idObjet) {
		int temp = 0;
		try {
			rs = st.executeQuery("SELECT temperatureMax FROM piece, objet WHERE idPiece=ID AND idObjet = "+idObjet);
			while (rs.next()) {
				temp = rs.getInt("temperatureMax");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	/**
	 *get temperature min 
	 */
	public int tempMin(int idObjet) {
		int temp = 0;
		try {
			rs = st.executeQuery("SELECT temperatureMin FROM piece, objet WHERE idPiece=ID AND idObjet = "+idObjet);
			while (rs.next()) {
				temp = rs.getInt("temperatureMin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	
	private int get(String col, int idObjet) {
		int get = 0;
		try {
			rs = st.executeQuery("SELECT " + col + " FROM objet WHERE idObjet = "+idObjet);
			while (rs.next()) {
				get = rs.getInt("etat");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return get;
	}
	private String getS(String col, int idObjet){ return String.valueOf(get(col, idObjet)); }
}
