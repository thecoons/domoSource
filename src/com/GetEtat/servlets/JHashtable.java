package com.GetEtat.servlets;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

public class JHashtable extends Object{
	private Hashtable ht;
	
	public JHashtable(){}
	public JHashtable(Hashtable ht){this.ht=ht;}
	public JHashtable(JHashtable jht){ht=jht.toHashtable();}
	
	public Hashtable toHashtable(){ return ht; }	
	public Object get(Object key){ return ht.get(key); }
	public JHashtable getAndPut(Object key){
		if(ht.get(key) == null)
			ht.put(key, new Hashtable());
		return new JHashtable((Hashtable) ht.get(key));
	}
	public Object put(Object key, Object value){ return ht.put(key, value); }
	public Object remove(Object key){ return ht.remove(key); }
	
	public JHashtable maisons(){ 
		return new JHashtable(ht).getAndPut("Maisons"); 
		}
	
	public JHashtable maison(int idMaison){ 
		return maisons().getAndPut(String.valueOf(idMaison));
		}
	
	public JHashtable protocoles(int idMaison){ 
		return maison(idMaison).getAndPut("Protocoles"); 
		}
	
	public JHashtable protocole(int idMaison, String nameProtocole){ 
		return protocoles(idMaison).getAndPut(nameProtocole); 
		}
	
	public JHashtable action(int idMaison, String nameProtocole, String nameAction){
		return protocole(idMaison, nameProtocole).getAndPut(nameAction); 
		}
	
	public String toString(){
		return ht.toString(); 
		}

	
	public Vector<String> listeProtocoles(int idMaison){
		Vector<String> v = new Vector<String>();
		String idMaisonS = String.valueOf(idMaison);
		if(maisons().get(idMaisonS)!=null){			
			Iterator it;
			it=protocoles(idMaison).toHashtable().keySet().iterator();
			while(it.hasNext())
			{
			   Object key=it.next();
			   v.add(key.toString());
			}
		}
		return v;
	}
	public Vector<String> listeActions(int idMaison, String nomProtocole){
		Vector<String> v = new Vector<String>();
		String idMaisonS = String.valueOf(idMaison);
		if(maisons().get(idMaisonS)!=null && protocoles(idMaison).get(nomProtocole)!=null){			
			Iterator it;
			it=protocole(idMaison, nomProtocole).toHashtable().keySet().iterator();
			while(it.hasNext())
			{
			   Object key=it.next();
			   v.add(key.toString());
			}
		}
		return v;
	}
	
}
