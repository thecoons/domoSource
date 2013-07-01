package com.GetEtat.beans;

public class warning {
	private String parametre;
	private String element;
	
	public warning(String parametre, String element){
		this.parametre = parametre;
		this.element = element;
	}
	
	public String getParametre(){ return parametre; }
	public String getElement(){ return element; }
	
}
