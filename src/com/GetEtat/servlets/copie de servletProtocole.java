package com.GetEtat.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class servletProtocole extends HttpServlet{
	private static final File myFile = new File("/home/mina/ProjectDomo/domotique/src/com/GetEtat/protocole.json");
	JHashtable protocoleCourant;

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String delete 		= request.getParameter("delete"); 	// delete = "protocole" | "action"
		//String update 		= request.getParameter("update"); // protocole
		String submit 		= request.getParameter("submit");	// if(submit==0) protocole_annul�
		String execute 		= request.getParameter("execute");
		//String idMaisonS 	= request.getParameter("idMaison");

		String nomProtocole = request.getParameter("nomProtocole");
		String nomAction    = request.getParameter("nomAction");
		
		String idTypeTechno = request.getParameter("idTypeTechno");
		String idElemTechno = request.getParameter("idElemTechno");
		
		String Etat = request.getParameter( "etat");
		String tempPiece = request.getParameter("temperaturePiece");
		String vol = request.getParameter("volume");
		String chaine = request.getParameter("chaine");
		String lum = request.getParameter("luminosite");
		String temperatureBain = request.getParameter("temperatureBain");
		String temperatureFour = request.getParameter("temperatureFour");
		String ecoulementBain = request.getParameter("ecoulementBain");

		boolean returnToServletVue = true;
		
		HttpSession session = request.getSession();
		try{
			int idMaison = 1;
			//if(idMaisonS != null) idMaison = Integer.parseInt(idMaisonS);
			
			if(!submit.equals(null)){
				if(submit.equals("0")) // protocole annule
					protocoleCourant.maison(idMaison).remove("Protocoles");
				else  // protocole valide
					submitProtocole(idMaison);
				
				
			}else if(!delete.equals(null)){
				if(delete.equals("protocole")){
					deleteProtocole(idMaison, nomProtocole);
				}else if(delete.equals("action")){
					deleteAction(idMaison, nomProtocole, nomAction);
				}
				
				
			}else if(!execute.equals(null)){
				if(executeProtocole(request, response, idMaison, nomProtocole)){
					this.getServletContext().getRequestDispatcher("/servletTechno").forward(request, response);
					returnToServletVue = false;
				}
			}else{
				//if(add.equals("protocole")){
				//	creerProtocole(idMaison, nomProtocole);
				//}else if(add.equals("action")){
				
					nomAction = (nomAction.equals(null)?idTypeTechno+idElemTechno:nomAction);
					JHashtable monAction = protocoleCourant.action(idMaison,nomProtocole, nomAction);
					monAction.put("idTypeTechno", idTypeTechno);
					monAction.put("idElemTechno", idElemTechno);
					monAction.put("etat", Etat);
					if(tempPiece != null)	monAction.put("temperaturePiece", tempPiece);
					if(vol != null)			monAction.put("volume", vol);
					if(chaine != null)		monAction.put("chaine", chaine);
					if(lum != null)			monAction.put("luminosite", lum);
					if(temperatureBain != null)	monAction.put("temperatureBain", temperatureBain);
					if(temperatureFour != null) monAction.put("temperatureFour", temperatureFour);
					if(ecoulementBain != null)	monAction.put("ecoulementBain", ecoulementBain);
				//}
			}
			
			
		}catch(NullPointerException e){}

		if(returnToServletVue)
			this.getServletContext().getRequestDispatcher("/servletVue").forward(request, response);
		
	}

	/**
	 * creer un protocole pour une maison
	 * @param idMaison 
	 * @param nomProtocole
	 * @return retourne la liste des protocoles d'une maison
	 */
	public void creerProtocole(int idMaison,String nomProtocole){
		JsonConverter j = new JsonConverter(myFile);
		JHashtable jht = new JHashtable(j.decodeJSON());
		String nouveauNomProtocole = nomProtocole;
		Vector<String> listeP = jht.listeProtocoles(idMaison);
		int i = 1;
		while(listeP.contains(nouveauNomProtocole)){
			nouveauNomProtocole = nomProtocole + " (" + i++ + ")";
		}		
		protocoleCourant.protocole(idMaison,nouveauNomProtocole);
	}

	/**
	 * creer un protocole courant a partir d'un protocole existant d'une maison
	 * @param idMaison 
	 * @param nomProtocole
	 * @return retourne la liste des protocoles d'une maison
	 */
	public void updateProtocole(int idMaison,String nomProtocole){
		JsonConverter j = new JsonConverter(myFile);
		JHashtable jht = new JHashtable(j.decodeJSON());
		protocoleCourant.protocoles(idMaison).put(nomProtocole, jht.protocole(idMaison,nomProtocole));
	}

	/**
	 * supprimer une action pour un protocole courant
	 * @param idMaison
	 * @param nomProtocole
	 * @param nomAction
	 * @return retourne la liste des actions du protocole courant donne d'une maison donnee
	 */
	public Vector<String> deleteAction(int idMaison, String nomProtocole, String nomAction){
		protocoleCourant.protocole(idMaison,nomProtocole).remove(nomAction);
		return protocoleCourant.listeActions(idMaison, nomProtocole);
	}

	/**
	 * ecrit le protocole courant dans le json
	 * @param idMaison
	 */
	public void submitProtocole(int idMaison){
		JsonConverter j = new JsonConverter(myFile);
		JHashtable jht = new JHashtable(j.decodeJSON());
		String nomProtocoleCourant = protocoleCourant.listeProtocoles(idMaison).firstElement();
		jht.protocoles(idMaison).put(nomProtocoleCourant, protocoleCourant.protocole(idMaison, nomProtocoleCourant));
		protocoleCourant.maisons().remove(idMaison);
		j.encodeJSON(jht.toHashtable());
	}

	/**
	 * supprime un protocole donne d'une maison
	 * @param idMaison
	 * @param nomProtocole
	 */
	public void deleteProtocole(int idMaison, String nomProtocole){
		JsonConverter j = new JsonConverter(myFile);
		JHashtable jht = new JHashtable(j.decodeJSON());
		jht.protocoles(idMaison).remove(nomProtocole);
		j.encodeJSON(jht.toHashtable());
	}


	/**
	 * executer un protocole 
	 * @param nomProtocole
	 * @return
	 */
	public boolean executeProtocole(HttpServletRequest request, HttpServletResponse response, int idMaison, String nomProtocole) throws ServletException{
		Hashtable<Integer, Hashtable<String, Object>> listActions = new Hashtable<Integer, Hashtable<String, Object>>();
		JsonConverter j = new JsonConverter(myFile);
		JHashtable jht = new JHashtable(j.decodeJSON());
		Vector<String> listAction=jht.listeActions(idMaison, nomProtocole);
		if(listAction.isEmpty())
			return false;
		for(String action : listAction)
		{
			JHashtable monAction = jht.action(1, nomProtocole, action);
			int idtypeTech = Integer.parseInt((String) monAction.get("TECHNO"));
			int idTechno = Integer.parseInt((String) monAction.get("IDTECHNO"));
			String actionAfaire = (String) monAction.get("ACTION");
			String value = (String) monAction.get("VALUE");
			listActions.put(1, decodeAction(idtypeTech, idTechno, actionAfaire, value));
		}		
		request.setAttribute("Techno", "executeProtocole");
		request.setAttribute("listAction", listAction);
		return true;
	}

	/**
	 * recuperer l'action a faire 
	 * @param idPiece
	 * @param idtypeTech
	 * @param idTechno
	 * @param action
	 * @param value
	 * @return
	 * @throws ServletException 
	 */
	private Hashtable<String, Object> decodeAction(int idtypeTech, int idTechno, String action, String value){
		Hashtable<String, Object> toReturn = new Hashtable<String, Object>();

		toReturn.put("Techno", "edit");
		toReturn.put("idtypeTechno", idtypeTech);
		toReturn.put("idTechno", idTechno);
		toReturn.put(action, value);

		return toReturn;
	}

}
