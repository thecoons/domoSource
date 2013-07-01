package com.GetEtat.beans;

import java.util.Hashtable;

public class ObjetLumiere extends Objet{
	
	private int luminosite; // en pourcentage
	
	public ObjetLumiere(){
		this(0,0,null,0);
	}

	/**
	 * Constructeur de la lumiere
	 * @param idObjet identifiant de la lumiere a creer
	 * @param idPiece id de la piece dans laquelle va se situer la lumiere
	 * @param nomObjet nom de la lumiere a creer
	 * @param etatObjet etat de la lumiere lors de la creation
	 */
	public ObjetLumiere(int idObjet, int idPiece, String nomObjet, int etatObjet) {
		this(idObjet, idPiece, nomObjet, etatObjet, 0);
	}
	
	/**
	 * Constructeur de la lumiere
	 * @param idObjet identifiant de la lumiere a creer
	 * @param idPiece id de la piece dans laquelle va se situer la lumiere
	 * @param nomObjet nom de la lumiere a creer
	 * @param etatObjet etat de la lumiere lors de la creation
	 * @param luminosite de la lumiere
	 */
	public ObjetLumiere(int idObjet, int idPiece, String nomObjet, int etatObjet, int luminosite) {
		super(idObjet, idPiece, nomObjet, etatObjet);
		this.luminosite = luminosite;
		super.idTypeObjet = 2;
	}
	

	/**
	 * Manipulateur de la luminosite de la lampe
	 * @param luminosite luminosite de la lampe
	 * @return modification effectuee ou pas
	 */
	public boolean setLuminosite(int luminosite){
		if(luminosite >= 0 && luminosite <= 100)
			this.luminosite = luminosite;
		else
			return false;
		return true;
	}
	
	/**
	 * Accesseur de la luminosite
	 * @return intensite de la luminosite (en %)
	 */
	public int getLuminosite(){
		return this.luminosite;
	}
	
	public Hashtable<Integer, String> recupListAttributs(){
		listAttributs = new Hashtable<Integer, String>();
		listAttributs.put(1, "etat");
		listAttributs.put(2, "luminosite");
		return listAttributs;
	}

}
