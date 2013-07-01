package com.GetEtat.beans;

import java.util.Hashtable;


/**
 * 
 * @author Clement Simon, Jonathan Fernandez
 * Classe representant le chauffage d'une piece. Cette classe herite de la classe Objet. <br>
 * L'attribut temperatureChauffage represente la temperature souhaitee dans la piece.
 *
 */

public class ObjetChauffage extends Objet{
	
	private int temperatureChauffage;

	public ObjetChauffage(){
		this(0,0,null,0);
	}

	/**
	 * Constructeur du chauffage
	 * @param idObjet identifiant du chauffage a creer
	 * @param idPiece id de la piece dans laquelle va se situer le chauffage
	 * @param nomObjet nom du chauffage a creer
	 * @param etatObjet etat du chauffage lors de la creation
	 */
	public ObjetChauffage(int idObjet, int idPiece, String nomObjet, int etatObjet) {
		this(idObjet, idPiece, nomObjet, etatObjet, 0);
	}
	
	/**
	 * Constructeur du chauffage
	 * @param idObjet identifiant du chauffage a creer
	 * @param idPiece id de la piece dans laquelle va se situer le chauffage
	 * @param nomObjet nom du chauffage a creer
	 * @param etatObjet etat du chauffage lors de la creation
	 * @param temperature temperature du chauffage
	 */
	public ObjetChauffage(int idObjet, int idPiece, String nomObjet, int etatObjet, int temperature) {
		super(idObjet, idPiece, nomObjet, etatObjet);
		this.temperatureChauffage = temperature;
		super.idTypeObjet = 3;
	}

	/**
	 * Manipulateur de la temperature souhaitee de la piece
	 * @param temperature : temperature souhaitee dans la piece 
	 * @return si le changement a ete effectue ou non
	 * 
	 */
	public boolean setTemperatureChauffage(int temperature,int tempMin,int tempMax) {
		if(temperature>=tempMin && temperature<=tempMax){
			this.temperatureChauffage = temperature;
		}
		else return false;
		return true;
		
	}
	/**
	 * Accesseur sur la temperature souhaitee de la piece
	 * @return temperature actuelle de la piece
	 */
	public int getTemperatureChauffage() {
		return temperatureChauffage;
	}
	
	public Hashtable<Integer, String> recupListAttributs(){
		listAttributs = new Hashtable<Integer, String>();
		listAttributs.put(1, "etat");
		listAttributs.put(2, "temperaturePiece");
		return listAttributs;
	}
	
}
