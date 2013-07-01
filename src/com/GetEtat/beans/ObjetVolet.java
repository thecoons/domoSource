package com.GetEtat.beans;

import java.util.Hashtable;


/**
 * 
 * @author Clement Simon, Jonathan Fernandez
 * Contrustcteur representant un volet. Cette classe herite de la classe Objet.
 *
 */

public class ObjetVolet extends Objet{

	public ObjetVolet(){
		this(0,0,null,0);
	}

	/**
	 * Constructeur du volet
	 * @param idObjet identifiant du volet a creer
	 * @param idPiece id de la piece dans laquelle va se situer le volet
	 * @param nomObjet nom du volet a creer
	 * @param etatObjet etat du volet lors de la creation
	 */
	public ObjetVolet(int idObjet, int idPiece, String nomObjet, int etatObjet) {
		super(idObjet, idPiece, nomObjet, etatObjet);
		super.idTypeObjet = 4;
	}
	
	public Hashtable<Integer, String> recupListAttributs(){listAttributs = new Hashtable<Integer, String>();
		listAttributs = new Hashtable<Integer, String>();
		listAttributs.put(1, "etat");
		return listAttributs;
	}
}