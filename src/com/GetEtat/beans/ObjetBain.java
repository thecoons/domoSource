package com.GetEtat.beans;

import java.util.Hashtable;

/**
 * 
 * @author Clement Simon, Jonathan Fernandez
 * Classe repsentant une baignoire qui herite de la classe Objet
 *
 */

public class ObjetBain extends Objet{
	
	private int temperatureBain;
	private int ecoulementBain;
		
	public ObjetBain(){
		this(0,0,null,0);
	}

	
	/**
	 * Constructeur de la baignoire
	 * @param idObjet identifiant de la baignoire a cree
	 * @param idPiece id de la piece dans laquelle va se situer la baignoire
	 * @param nomObjet nom de la baignoire a creer
	 * @param etatObjet etat de la baignoire lors de la creation
	 */
	public ObjetBain(int idObjet, int idPiece, String nomObjet, int etatObjet) {
		this(idObjet, idPiece, nomObjet, etatObjet, 0);
	}
	
	/**
	 * Constructeur de la baignoire
	 * @param idObjet identifiant de la baignoire a cree
	 * @param idPiece id de la piece dans laquelle va se situer la baignoire
	 * @param nomObjet nom de la baignoire a creer
	 * @param etatObjet etat de la baignoire lors de la creation
	 * @param temperature temperature de l'eau de la baignoire
	 */
	public ObjetBain(int idObjet, int idPiece, String nomObjet, int etatObjet, int temperature){
		this(idObjet, idPiece, nomObjet, etatObjet, temperature, 0);
	}
	
	/**
	 * Constructeur de la baignoire qui appelle le constructeur de sa classe mere Objet
	 * @param idObjet identifiant de la baignoire a cree
	 * @param idPiece id de la piece dans laquelle va se situer la baignoire
	 * @param nomObjet nom de la baignoire a creer
	 * @param etatObjet etat de la baignoire lors de la creation
	 * @param temperature temperature de l'eau de la baignoire
	 * @param ecoulementBain niveau de l'eau du bain (en %)
	 */
	public ObjetBain(int idObjet, int idPiece, String nomObjet, int etatObjet, int temperature, int ecoulementBain){
		super(idObjet, idPiece, nomObjet, etatObjet);
		this.temperatureBain = temperature;
		this.ecoulementBain = ecoulementBain;
		super.idTypeObjet = 5;
	}

	/**
	 * Accesseur de la temperature de l'eau du bain
	 * @return temperature actuelle de l'eau du bain
	 */
	public int getTemperatureBain() {
		return temperatureBain;
	}


	/**
	 * Manipulateur de la température de l'eau du bain
	 * @param temperature temperature de l'eau du bain
	 */
	public boolean setTemperatureBain(int temperature) {
		if(temperature>10 && temperature<50)
			this.temperatureBain = temperature;
		else 
			return false  ;
		return true;			
	}

	/**
	 * Accesseur de l'ecoulement du bain
	 * @return Retourne le niveau de l'eau (en %) dans la baignoire
	 */
	public int getEtatEcoulement() {
		return ecoulementBain;
	}


	/**
	 * Manipulateur de l'ecoulement du bain
	 * @param ecoulementBain niveau d'eau voulu (en %) dans la baignoire
	 */
	public boolean setEtatEcoulement(int ecoulementBain) {
		if(ecoulementBain>=0 && ecoulementBain<=100)
			this.ecoulementBain = ecoulementBain;
		else 
			return false;
		return true;
	}
	
	public Hashtable<Integer, String> recupListAttributs(){
		listAttributs = new Hashtable<Integer, String>();
		listAttributs.put(1, "etat");
		listAttributs.put(2, "temperatureBain");
		listAttributs.put(3, "ecoulementBain");
		return listAttributs;
	}
	
	
	
}
