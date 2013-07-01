package com.GetEtat.beans;

import java.util.Hashtable;


/**
* @author Yann Roseau, Jonathan Fernandez
*
*Classe abstraite representa un objet et regroupant la liste des atttributs et des methodes communs à tous les objets
*/
public abstract class Objet {
	
	protected final int idObjet;
	protected int idTypeObjet;
	protected final int idPiece;
	protected String nomObjet;
	protected int etatObjet;
	
	protected Hashtable<Integer, String> listAttributs;
	
	
	/**
	 * Constructeur de la classe abstraite Objet
	 * @param idObjet identifiant de l'objet a creer
	 * @param idTypeObjet identifiant du type d'objet a creer
	 * @param idPiece identifiant de la piece dans laquelle se trouve l'objet
	 * @param nomObjet nom de l'objet a creer
	 * @param etatObjet etat de l'obejt a creer
	 */
	public Objet(int idObjet, int idPiece, String nomObjet, int etatObjet){
		this.idObjet = idObjet;
		this.idPiece = idPiece;
		this.nomObjet = nomObjet;
		this.etatObjet = etatObjet;
	}
	
	/**
	 * Accesseur de l'identifiant de l'objet
	 * @return identifiant de l'objet
	 */
	public int getIdObjet(){
		return idObjet;
	}
	
	/**
	 * Accesseur de l'identifiant du type d'objet
	 * @return identifiant du type d'objet
	 */
	public int getIdTypeObjet(){
		return idTypeObjet;
	}
	
	
	/**
	 * Accesseur de l'identifiant de la piece dans laquelle se trouve l'objet
	 * @return identifiant de la piece
	 */
	public int getIdPiece(){
		return idPiece;
	}
	
	/**
	 * Accesseur du nom de l'objet
	 * @return nom de l'objet
	 */
	public String getNomObjet(){
		return nomObjet;
	}
	
	/**
	 * Manipulateur du nom de l'objet
	 * @param nouveauNomObjet Nouveau nom de l'objet
	 */
	public void setNomObjet(String nouveauNomObjet){
		this.nomObjet = nouveauNomObjet;
	}
	
	/**
	 * Accesseur de l'etat de l'objet
	 * @return l'etat actuel de l'objet
	 */
	public int getEtatObjet(){
		return etatObjet;
	}
	
	/**
	 * Manipulateur de l'etat de l'objet
	 * @param nouveauEtatObjet nouvel etat de l'objet
	 */
	public void setEtatObjet(int nouveauEtatObjet){
		this.etatObjet = nouveauEtatObjet;
	}
	
}
