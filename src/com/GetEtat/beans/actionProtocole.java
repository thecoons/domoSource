package com.GetEtat.beans;

import java.util.Hashtable;

public class actionProtocole {
	private String nomPiece;
	private String nomTechno;
	private String nomAction;
	private Hashtable<String, String> ht;
	
	public actionProtocole(String nomPiece, String nomTechno, String nomAction, Hashtable<String, String> ht){
		this.nomPiece=nomPiece;
		this.nomTechno=nomTechno;
		this.nomAction=nomAction;
		this.ht=ht;
	}
	
	public String getNomPiece(){ return nomPiece; }
	public String getNomTechno(){ return nomTechno; }
	public String getNomAction(){ return nomAction; }
	public Hashtable<String, String> getListAttribut(){ return ht; }
}
