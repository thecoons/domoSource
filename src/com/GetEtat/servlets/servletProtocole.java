package com.GetEtat.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.GetEtat.beans.Piece;
import com.GetEtat.beans.actionProtocole;

public class servletProtocole extends HttpServlet {
	private static final File myFile = new File(
			"/home/worldmaster/workspace/minoProj/domotique/protocole.json");
	private ConnexionDB con = null;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	HttpSession session;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String delete = request.getParameter("delete"); // delete = "protocole"
														// | "action"
		// String update = request.getParameter("update"); // protocole
		String submit = request.getParameter("submit"); // if(submit==0)
														// protocole_annul�
		String execute = request.getParameter("execute");
		// String idMaisonS = request.getParameter("idMaison");

		String nomProtocole = request.getParameter("nomProtocole");
		String nomAction = request.getParameter("nomAction");

		String idTypeTechno = request.getParameter("idtypeTechno");
		String idElemTechno = request.getParameter("idTechno");

		String Etat = request.getParameter("etat");
		String tempPiece = request.getParameter("temperaturePiece");
		String vol = request.getParameter("volume");
		String chaine = request.getParameter("chaineTV");
		String lum = request.getParameter("luminosite");
		String temperatureBain = request.getParameter("temperatureBain");
		String temperatureFour = request.getParameter("temperatureFour");
		String ecoulementBain = request.getParameter("ecoulementBain");
		
		String initialiseListeProtocoles =request.getParameter("identifiant");

		boolean returnToServletVue = true;
		boolean returnToVue = false;

		CreateSession();

		session = request.getSession();
		int idMaison = 1;

		request.setAttribute("listePiece", session.getAttribute("listePiece"));

		System.out.println("SERVLET PROTOCOLE");

		try {
			// if(idMaisonS != null) idMaison = Integer.parseInt(idMaisonS);

			if (submit != null) {
				if (submit.equals("0")) { // protocole annule
					session.setAttribute("protocoleC", new Hashtable());
				} else { // protocole valide
					submitProtocole(idMaison, nomProtocole);
				}

			} else if (delete != null) {
				if (delete.equals("protocole")) {
					deleteProtocole(idMaison, nomProtocole);
				} else if (delete.equals("action")) {
					deleteAction(nomAction);
				}

			} else if (execute != null) {
				if (executeProtocole(request, response, idMaison, nomProtocole)) {
					this.getServletContext()
							.getRequestDispatcher("/servletTechno")
							.forward(request, response);
					returnToServletVue = false;
				}
			} else if(initialiseListeProtocoles != null){
				returnToVue = true;
			}else {
				// nomAction =
				// ((nomAction==null)?idTypeTechno+"."+idElemTechno:nomAction);
				Hashtable ht = (Hashtable) session.getAttribute("protocoleC");
				if (ht == null)
					ht = new Hashtable();
				Hashtable monAction = new Hashtable();
				// monAction.put("idTypeTechno", idTypeTechno);
				// monAction.put("idElemTechno", idElemTechno);
				monAction.put("etat", Etat);
				if (tempPiece != null)
					monAction.put("temperaturePiece", tempPiece);
				if (vol != null)
					monAction.put("volume", vol);
				if (chaine != null)
					monAction.put("chaineTV", chaine);
				if (lum != null)
					monAction.put("luminosite", lum);
				if (temperatureBain != null)
					monAction.put("temperatureBain", temperatureBain);
				if (temperatureFour != null)
					monAction.put("temperatureFour", temperatureFour);
				if (ecoulementBain != null)
					monAction.put("ecoulementBain", ecoulementBain);
				ht.put(idElemTechno, monAction);
				session.setAttribute("protocoleC", ht);
				// System.out.println(ht.toString());
			}

		} catch (NullPointerException e) {
			System.out.println("NULPOINTEREXCEPTION");
		}

		JsonConverter j = new JsonConverter(myFile);
		JHashtable jht = new JHashtable(j.decodeJSON());

		Hashtable<String, Vector<actionProtocole>> listP = new Hashtable<String, Vector<actionProtocole>>();

		Iterator<String> it;
		it = jht.protocoles(idMaison).toHashtable().keySet().iterator(); // on
																			// cree
																			// un
																			// iterator
																			// sur
																			// les
																			// clés
																			// de
		// ton hashmap
		while (it.hasNext()) {
			Object key = it.next();
			Object value = jht.protocoles(idMaison).get(key);

			Vector<actionProtocole> listeActionsP = new Vector<actionProtocole>();

			Hashtable ht = jht.protocole(idMaison, (String) key).toHashtable();
			if (ht != null) {
				Iterator<String> iter;
				iter = ht.keySet().iterator(); // on cree un iterator sur les
												// clés
												// de
				// ton hashmap
				while (iter.hasNext()) {
					Object k = iter.next();
					Object v = ht.get(k);
					listeActionsP.add(new actionProtocole(nomPiece((String) k),
							nomTechno((String) k), (String) k,
							(Hashtable<String, String>) v));
				}
			}

			listP.put((String) key, listeActionsP);
		}
		session.setAttribute("listeProtocole", listP);
		request.setAttribute("listeProtocoles", listP);// jht.protocoles(idMaison).toHashtable());
		System.out.print("listP:");
		System.out.println(listP.toString());

		Vector<actionProtocole> listeActions = new Vector<actionProtocole>();
		System.out.println(listeActions.size());
		Hashtable ht = (Hashtable) session.getAttribute("protocoleC");
		if (ht != null) {
			System.out.println(ht.toString());
			Iterator<String> iter;
			iter = ht.keySet().iterator(); // on cree un iterator sur les clés
											// de
											// ton hashmap
			while (iter.hasNext()) {
				Object key = iter.next();
				Object value = ht.get(key);
				listeActions.add(new actionProtocole(nomPiece((String) key),
						nomTechno((String) key), (String) key,
						(Hashtable<String, String>) value));
			}
		}
		System.out.println(listeActions.size());
		request.setAttribute("protocoleCourant", listeActions);

		if (returnToVue)
			this.getServletContext().getRequestDispatcher("/servletVue")
					.forward(request, response);
		else if (returnToServletVue)
			this.getServletContext().getRequestDispatcher("/protocoles.jsp")
					.forward(request, response);

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
	 * creer un protocole courant a partir d'un protocole existant d'une maison
	 * 
	 * @param idMaison
	 * @param nomProtocole
	 * @return retourne la liste des protocoles d'une maison
	 */
	public void updateProtocole(int idMaison, String nomProtocole) {
		JsonConverter j = new JsonConverter(myFile);
		JHashtable jht = new JHashtable(j.decodeJSON());
		session.setAttribute("protocoleC", jht
				.protocole(idMaison, nomProtocole).toHashtable());
	}

	/**
	 * supprimer une action pour un protocole courant
	 * 
	 * @param idMaison
	 * @param nomProtocole
	 * @param nomAction
	 * @return retourne la liste des actions du protocole courant donne d'une
	 *         maison donnee
	 */
	public void deleteAction(String nomAction) {
		Hashtable ht = (Hashtable) session.getAttribute("protocoleC");
		ht.remove(nomAction);
		session.setAttribute("protocoleC", ht);
	}

	/**
	 * ecrit le protocole courant dans le json
	 * 
	 * @param idMaison
	 */
	public void submitProtocole(int idMaison, String nomProtocoleCourant) {
		JsonConverter j = new JsonConverter(myFile);
		JHashtable jht = new JHashtable(j.decodeJSON());
		String nouveauNomProtocole = nomProtocoleCourant;
		Vector<String> listeP = jht.listeProtocoles(idMaison);
		int i = 1;
		if (!listeP.isEmpty()) {
			while (listeP.contains(nouveauNomProtocole)) {
				nouveauNomProtocole = nomProtocoleCourant + " (" + i++ + ")";
			}
		}
		Hashtable ht = (Hashtable) session.getAttribute("protocoleC");
		jht.protocoles(idMaison).put(nouveauNomProtocole, ht);
		session.setAttribute("protocoleC", new Hashtable());
		j.encodeJSON(jht.toHashtable());
		// System.out.println("File write : " + jht.toString());
	}

	/**
	 * supprime un protocole donne d'une maison
	 * 
	 * @param idMaison
	 * @param nomProtocole
	 */
	public void deleteProtocole(int idMaison, String nomProtocole) {
		JsonConverter j = new JsonConverter(myFile);
		JHashtable jht = new JHashtable(j.decodeJSON());
		jht.protocoles(idMaison).remove(nomProtocole);
		j.encodeJSON(jht.toHashtable());
	}

	/**
	 * executer un protocole
	 * 
	 * @param nomProtocole
	 * @return
	 */
	public boolean executeProtocole(HttpServletRequest request,
			HttpServletResponse response, int idMaison, String nomProtocole)
			throws ServletException {
		Vector<Hashtable<String, Object>> allActions = new Vector<Hashtable<String, Object>>();
		JsonConverter j = new JsonConverter(myFile);
		JHashtable jht = new JHashtable(j.decodeJSON());
		Vector<String> listAction = jht.listeActions(idMaison, nomProtocole);

		if (listAction.isEmpty())
			return false;
		for (String action : listAction) {
			JHashtable monAction = jht.action(1, nomProtocole, action);
			String idtypeTech = typeTechno(action);
			String idTechno = action;

			Hashtable<String, Object> toReturn = new Hashtable<String, Object>();
			toReturn.put("Techno", "edit");
			toReturn.put("idtypeTechno", idtypeTech);
			toReturn.put("idTechno", action);

			Iterator<String> it;
			it = monAction.toHashtable().keySet().iterator(); // on cree un
																// iterator sur
																// les clés de
																// ton hashmap
			while (it.hasNext()) {
				Object key = it.next();
				Object value = monAction.get(key);
				toReturn.put((String) key, (String) value);
			}

			allActions.add(toReturn);
		}
		request.setAttribute("Techno", "executeProtocole");
		request.setAttribute("listAction", allActions);
		return true;
	}

	private String nomTechno(String idObjet) {
		String get = "";
		try {
			rs = st.executeQuery("SELECT nomObjet FROM objet WHERE idObjet = "
					+ idObjet);
			while (rs.next()) {
				get = rs.getString("nomObjet");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return get;
	}

	private String typeTechno(String idObjet) {
		String get = "";
		try {
			rs = st.executeQuery("SELECT idTypeObjet FROM objet WHERE idObjet = "
					+ idObjet);
			while (rs.next()) {
				get = rs.getString("idTypeObjet");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return get;
	}

	private String nomPiece(String idObjet) {
		String get = "";
		try {
			rs = st.executeQuery("SELECT piece.nom FROM piece, objet WHERE ID=idPiece AND idObjet = "
					+ idObjet);
			while (rs.next()) {
				get = rs.getString("nom");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return get;
	}

}
