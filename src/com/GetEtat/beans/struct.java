package com.GetEtat.beans;

public class struct {
	public String idObjet;
	public String idPiece ;
	public String idTypeObjet;
	public String nom ;
	
	public struct(String idObjet, String idPiece, String idTypeObjet, String nom) {
		super();
		this.idObjet = idObjet;
		this.idPiece = idPiece;
		this.idTypeObjet = idTypeObjet;
		this.nom = nom;
	}
}
