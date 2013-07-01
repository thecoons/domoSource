package com.GetEtat.beans;

import java.util.*;

/**
* @author Yann Roseau, Jonathan Fernandez
* Classe decrivant chaque piece de la maison
*
*/
public class Piece {

	private int idPiece;
	private String nomPiece;
	protected int tempMin;
	protected int tempMax;
	private Vector<Objet> listObjets;
	
	
	
	/**
	 * Constructeur de la classe piece en passant par parametre l'id de la piece <br>
	 * Ce constructeur va appeller un autre constructeur avec un nom par defaut
	 * @param id identifiant de la 
	 */
	
	public Piece(int id){	
		this(id, "Ma Piece "+id);
    }
	
	/**
	 * Constructeur de la classe piece en passant par parametre l'id de la piece  et le nom de la piece<br>
	 * Ce constructeur va appeller un autre constructeur avec des temperatures min et max par defaut
	 * @param id identifiant de la piece
	 * @param nomPiece nom de la piece
	 */
	public Piece(int id, String nomPiece){	
    	this(id,nomPiece,10,25);
    }
	
	/**
	 * Constructeur de la classe piece en passant par parametre l'id de la piece  et le nom de la piece, ainsi que les temperatures min et max<br>
	 * Ce constructeur va instancier le vecteur d'objets.
	 * @param id id de la piece
	 * @param nomPiece nom de la piece
	 * @param tempMin temperature min de la piece
	 * @param tempMax temperature max de la piece
	 */
	public Piece(int id, String nomPiece, int tempMin, int tempMax){	
    	idPiece=id;
    	this.nomPiece = nomPiece;
    	this.tempMin=tempMin;
    	this.tempMax=tempMax;
    	listObjets=new Vector<Objet>();
    }
    
	/**
	 * accesseur de l'identifiant de la piece
	 * @return l'identifiant de la piece
	 */
    public int getIdPiece(){
    	return idPiece;
    }
    
    /**
     * accesseur du nom de la piece
     * @return le nom de la piece
     */
    public String getNomPiece(){
    	return nomPiece;
    }
    
    /**
     * manipulateur du nom de la piece
     * @param newNomPiece nouveau nom de la piece
     */
    
    public void setNomPiece(String newNomPiece){
    	this.nomPiece = newNomPiece;
    }
    
    /**
     * manipulateur de la temperature min de la piece
     * @param tempMin température min a modifier
     */
    public boolean setTempMin(int tempMin){
    	if(tempMin <= tempMax)
    		this.tempMin=tempMin;
    	else
    		return false;
    	return true;
    }
    
    /**
     * manipulateur de la temperature max de la piece
     * @param tempMax temperature max à modifier
     */
    public boolean setTempMax(int tempMax){
    	if(tempMax >= tempMin)
    		this.tempMax=tempMax;
    	else
    		return false;
    	return true;
    }
    
    /**
     * accesseur de la temperature min de la piece
     * @return temperature min de la piece
     */
    public int getTempMin() {
		return tempMin;
	}

	/**
	 * accesseur de la temperature max de la piece
	 * @return temperature max de la piece 
	 */
	public int getTempMax() {
		return tempMax;
	}
	
    /**
     * accesseur de la liste des objets de la piece
     * @return la liste des objets de la piece
     */
    public Vector<Objet> getListObjets() {
		return listObjets;
	}
    
    /**
     * Ajoute un objet a la piece
     * @param nouvelObjet objet a ajotuer a la piece
     */
    public void ajoutObjet(Objet nouvelObjet){
    	if(!nouvelObjet.equals(null) && !listObjets.contains(nouvelObjet)){
    		listObjets.add(nouvelObjet);
    	}
    }
    
    
    /**
     * Supprime un objet de la piece
     * @param ancienObjet objet a supprimer dans le piece
     */
    protected void supprObjet(Objet ancienObjet){
    	listObjets.remove(ancienObjet);
    }
    
    
}
