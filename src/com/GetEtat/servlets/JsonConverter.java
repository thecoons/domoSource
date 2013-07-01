package com.GetEtat.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;

/**
 * 
 * @author Yann
 *
 */
public class JsonConverter {
	private File jsonFile;
	
	public JsonConverter(){}
	public JsonConverter(File file){jsonFile=file;}
	
	/**
	 * 
	 * @return Retourne le fichier jsonFile
	 */
	public File getFile(){return jsonFile;}
	/**
	 * 
	 * @param file : fichier au format json
	 */
	public void setFile(File file){jsonFile=file;}
	
	/**
	 * 
	 * @return Retourne le contenu du fichier
	 */
	private static String readJSON(File file){
		String SFile = "";
		try{
			InputStream ips=new FileInputStream(file);//path + "/" + name);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while((ligne=br.readLine())!=null){
				SFile+=ligne;
			}
			br.close();
			System.out.println("File read : "+SFile);
		}catch (Exception e){
			System.out.println("Impossible de lire le fichier!");
		}
		return SFile;
	}
	private static void writeJSON(File file, String content){
		try{
			FileWriter writer = new FileWriter(file);
			writer.write(content);
			writer.flush();
			writer.close();
			System.out.println("File write:" + content);
		}catch (IOException e){
			System.out.println("Impossible de cr�er le fichier!");
		}
	}
	
	/**
	 * 
	 * @param str : Chaine de d�part
	 * @return Retourne str priv� de ses guillements de d�but et de fin (s'il existe)
	 */
	private static String deleteQuote(String str){
		int i = 0; int j = str.length();
		if(str.substring(0, 1).equals("\""))
			i = 1;
		if(str.substring(str.length()-1, str.length()).equals("\""))
			j = str.length()-1;
		return str.substring(i,j);
	}
	/**
	 * 
	 * @param str : Chaine de d�part
	 * @return Retourne la chaine de d�part �chap�e pour les caract�res \ { } ,
	 */
	private static String escapeSpecialCharacter(String str){
		String toreturn = str;
		toreturn = toreturn.replaceAll("\\\\", "##ANTISLASH##");
		toreturn = toreturn.replaceAll("\\{", "\\\\{");
		toreturn = toreturn.replaceAll("\\}", "\\\\}");
		toreturn = toreturn.replaceAll(",", "\\\\,");
		toreturn = toreturn.replaceAll("##ANTISLASH##", "\\\\\\\\");
		//str.replaceAll("\\\\([^{},]{1})", "\\\\\\\\$1"); //pose probl�me si le dernier caract�re est \
		return toreturn;
	}
	
	/**
	 * 
	 * @return Retourne une hashtable correspondant au fichier json "jsonFile"
	 */
	public Hashtable decodeJSON(){
		if(jsonFile!=null)
			return decodeJSON(jsonFile);
		else
			System.out.println("Le fichier json n'a pas �t� initialis�.");
		return null;
	}
	/**
	 * 
	 * @param file : fichier au format json
	 * @return Retourne une hashtable correspondant au fichier json
	 */
	public static Hashtable decodeJSON(File file){
		
		Hashtable obj = new Hashtable();
		
		if(file.exists()){
			Stack<Hashtable> pile = new Stack<Hashtable>();		
			pile.push(obj);
			
			String key = "";
			boolean caractSpecial = false;
			String SFile = readJSON(file);
			SFile = SFile.substring(1, SFile.length()-1);	// pour supprimer les accolades de d�but et fin
			
			while(!pile.isEmpty() && !SFile.isEmpty()){
				int i = 1;
				String caractere = SFile.substring(0,i);
				if(!caractSpecial && caractere.equals("}")){
					pile.pop(); // d�pile
					if(i < SFile.length() && SFile.substring(i,i+1).equals(","))
						i++;
				}else if(!caractSpecial && caractere.equals(":")){
					key = deleteQuote(key);
					if(SFile.substring(i,i+1).equals("{")){
						Hashtable obj2 = new Hashtable();
						pile.peek().put(key, obj2);
						pile.push(obj2); // empile
						i++;
					}else{
						boolean caractSpecialV = false;
						String value = "";
						String newCaratere = SFile.substring(i,i+1);
						while(i < SFile.length() && (caractSpecialV || (!newCaratere.equals("}") && !newCaratere.equals(",")))){
							caractSpecialV = !caractSpecialV && newCaratere.equals("\\");
							if(!caractSpecialV)
								value += newCaratere;
							if(++i < SFile.length())
								newCaratere = SFile.substring(i,i+1);
						}
						pile.peek().put(key,deleteQuote(value));
						if(!caractSpecialV && newCaratere.equals(","))
							i++;
					}
					key = "";
				}else{
					caractSpecial = !caractSpecial && caractere.equals("\\");
					if(!caractSpecial)
						key += caractere;
				}
				SFile = SFile.substring(i, SFile.length());
			}
		}
		return obj;
	}
	
	/**
	 * 
	 * @param ht : hashtable � exporter
	 * @return Retour l'�quivalent json de ht
	 */
	public static String exportJSON(Hashtable ht){
		String str = "{";
		
		int nbElements = 0;
		
		Iterator it;
		it=ht.keySet().iterator();
		while(it.hasNext())
		{
		   nbElements++;
			
		   Object key=it.next();
		   Object value=ht.get(key);
		   
		   if(nbElements > 1)
			   str += ",";
		   
		   str += "\"" + escapeSpecialCharacter(key.toString()) + "\":";
		   
		   if(value instanceof Hashtable)
			   str += exportJSON((Hashtable)value);
		   else
			   str += "\"" + escapeSpecialCharacter(value.toString()) + "\"";
		}
		
		str += "}";
		
		return str;		
	}
	/**
	 * Exporte la hashtable ht au format json sur le fichier json "jsonFile"
	 * @param ht : hashtable � exporter au format json
	 */
	public void encodeJSON(Hashtable ht){
		if(jsonFile!=null)
			encodeJSON(ht, jsonFile);
		else
			System.out.println("Le fichier json n'a pas �t� initialis�.");
	}
	/**
	 * Exporte la hashtable ht au format json sur le fichier file
	 * @param ht : hashtable � exporter au format json
	 * @param file : fichier au format json
	 */
	public static void encodeJSON(Hashtable ht, File file){
		writeJSON(file, exportJSON(ht));
	}
	
	
	/**
	 * Exemple d'utilisation
	 */
	public static void main(String[] args){
		File myFile = new File("D:/Program files disque D/eclipse/Workspaces_Eclipe/test.json");
				
		// r�cup�re le contenu d'un fichier json et le transforme en JHashtable
		JsonConverter j = new JsonConverter(myFile);
			JHashtable jht = new JHashtable(j.decodeJSON());
		/*---OU--- (version static)
		 *  JHashtable jht = new JHashtable(decodeJSON(myFile));
		 */		
			
		// exemple de cr�ation de cl� avec JHashtable
		jht.maison(3).put("KEY_1","VALUE_1");
		// --- OU --- (revient au m�me)
		jht.getAndPut("Maisons").getAndPut("3").put("KEY_2","VALUE_2");
		
		// exemple d'affichage de valeur avec JHashtable
		System.out.println("Maisons > 3 > KEY_1 : " + jht.maison(3).get("KEY_1"));
		System.out.println("Maisons > 3 > KEY_2 : " + jht.maison(3).get("KEY_2"));
		System.out.println("Maisons > 3 > KEY_3 : " + jht.maison(3).get("KEY_3"));	// cl� inexistante : renvoi un object null
		
		// exemple de suppression de cl� avec JHashtable
		jht.maison(3).remove("KEY_1");
		jht.maison(3).remove("KEY_2");
		
		// de la m�me fa�on pour les protocoles/actions:		
		jht.action(3, "D�part maison", "Action n�1").put("IDTECHNO","VALUE_4");
		jht.protocole(3, "D�part maison").getAndPut("Action n�1").put("ACTION","VALUE_5");
		jht.getAndPut("Maisons").getAndPut("3").getAndPut("Protocoles").getAndPut("D�part maison").getAndPut("Action n�1").put("VALEUR","VALUE_6");
		
		// exporte la JHashtable en json et l'�crit dans un fichier
		j.encodeJSON(jht.toHashtable());
		/*---OU--- (version static)
		 *  encodeJSON(jht.toHashtable(), myFile);
		 */
			
		/*
		 * Normalisation du fichier json � tenir:
		 * 
		 * IDMAISON � remplacer par le v�ritable identifiant maison
		 * 
		 * Maisons > IDMAISON > Protocoles > NOMPROTOCOLE > NOMACTION
		 * 
		 * {"Maisons":{
		 * 		"IDMAISON":{
		 * 			"Protocoles":{
		 * 				"NOMPROTOCOLE":{
		 * 					"NOMACTION":{
		 * 						"IDTECHNO":"",
		 * 						"ACTION":"",
		 * 						"VALEUR":""
		 * 					}
		 * 				}
		 * 			}
		 * 		}
		 * }
		 * 
		 */
	}
}