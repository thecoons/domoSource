package com.GetEtat.beans;

import java.util.Hashtable;

/**
 * 
 * @author Clement Simon, Jonathan Fernandez
 * Classe representant un four. Elle herite de la classe abstraite Objet.
 *
 */
public class ObjetFour extends Objet{
	
	private int temperatureFour;
	
	public ObjetFour(){
		this(0,0,null,0);
	}

	/**
	 * Constructeur du four
	 * @param idObjet identifiant du four a creer
	 * @param idPiece id de la piece dans laquelle va se situer le four
	 * @param nomObjet nom du four a creer
	 * @param etatObjet etat du four lors de la creation
	 */
	public ObjetFour(int idObjet, int idPiece, String nomObjet, int etatObjet) {
		this(idObjet, idPiece, nomObjet, etatObjet, 0);
	}
	
	/**
	 * Constructeur du four
	 * @param idObjet identifiant du four a creer
	 * @param idPiece id de la piece dans laquelle va se situer le four
	 * @param nomObjet nom du four a creer
	 * @param etatObjet etat du four lors de la creation
	 * @param temperature four du four
	 */
	public ObjetFour(int idObjet, int idPiece, String nomObjet, int etatObjet, int temperature) {
		super(idObjet, idPiece, nomObjet, etatObjet);
		this.temperatureFour = temperature;
		super.idTypeObjet = 6;
	}
	
	/**
	 * Manipulateur de la temperature du four
	 * @param temperature : temperature du four
	 * @return changement effectuee ou non
	 */
	public boolean setTemperatureFour(int temperature) {
		if(temperature > 0 && temperature <280 )
			this.temperatureFour = temperature;
		else
			return false;
		return true;
	}
	
	/**
	 * Accesseur sur la temperature du four
	 * @return retourne la temperature actuelle du four
	 */
	public int getTemperatureFour() {
		return temperatureFour;
	}
	
	public Hashtable<Integer, String> recupListAttributs(){
		listAttributs = new Hashtable<Integer, String>();
		listAttributs.put(1, "etat");
		listAttributs.put(2, "temperatureFour");
		return listAttributs;
	}
	
}
