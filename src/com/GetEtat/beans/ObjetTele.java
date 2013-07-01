package com.GetEtat.beans;

import java.util.Hashtable;

/**
* @author Yann Roseau, Jonathan Fernandez
* Classe representant une tele. Cette classe herite de la classe Objet
*
*/
public class ObjetTele extends Objet{
	private int chaine;
	private int volume;
	
	public ObjetTele(){
		this(0,0,null,0);
	}
	/**
	 * Constructeur de la baignoire
	 * @param idObjet identifiant de la baignoire a cree
	 * @param idPiece id de la piece dans laquelle va se situer la baignoire
	 * @param nomObjet nom de la baignoire a creer
	 * @param etatObjet etat de la baignoire lors de la creation
	 */
	public ObjetTele(int idObjet, int idPiece, String nomObjet, int etatObjet) {
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
	public ObjetTele(int idObjet, int idPiece, String nomObjet, int etatObjet, int chaine){
		this(idObjet, idPiece, nomObjet, etatObjet, chaine, 0);
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
	public ObjetTele(int idObjet, int idPiece, String nomObjet, int etatObjet, int chaine, int volume){
		super(idObjet, idPiece, nomObjet, etatObjet);
		this.chaine = chaine;
		this.volume = volume;
		super.idTypeObjet = 1;
	}


	/**
	 * Manipulateur de la chaine de la tele
	 * @param nouvelleChaine nouvelle chaine de la tele
	 * @return modification effectuee ou pas
	 */
	public boolean setChaine(int nouvelleChaine){
		if(nouvelleChaine > 0 && nouvelleChaine < 1000)
			this.chaine = nouvelleChaine;
		else
			return false;
		return true;
	}

	
	/**
	 * Accesseur de la chaine de la tele
	 * @return chaine actuelle de la tele
	 */
	public int getChaine(){
		return chaine;
	}
	

	/**
	 * Manipulateur du volume de la tele
	 * @param nouveauVolume nouveau volume de la tele
	 * @return modification effectuee ou pas
	 */
	public boolean setVolume(int nouveauVolume){
		if(nouveauVolume > 0 && nouveauVolume < 100)
			this.volume = nouveauVolume;
		else
			return false;
		return true;
	}
	

	/**
	 * Accesseur sur le volume de la tele
	 * @return volume de la tele
	 */
	public int getVolume(){
		return volume;
	}
	
	public Hashtable<Integer, String> recupListAttributs(){
		listAttributs = new Hashtable<Integer, String>();
		listAttributs.put(1, "etat");
		listAttributs.put(2, "chaineTV");
		listAttributs.put(3, "volume");
		return listAttributs;
	}
}
