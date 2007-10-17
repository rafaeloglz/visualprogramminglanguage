/**
 * Clase que contiene los elementos para agregar informaci&oacute a los componentes.
 * 
 * @author Andr&eacute Freyr&iacutea Cedeño
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figuero Ram&iacuterez
 * @author Jos&eacute Roberto Ram&iacuterez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Activity {
	
	private Hashtable<String, Object> contents;
	
	/**
	 * Constructor por omisi&oacuten.
	 *
	 * @param numContents		<code>int</code>
	 */
	public Activity(int numContents) {
		
		contents = new Hashtable(numContents);
	}
	
	/**
	 * M&eacutetodo para agregar un elemento junto con su nombre/llave.
	 *
	 * @param name				<code>String</code>
	 * @param o					<code>Object</code>
	 */
	public void add(String name, Object o) {
		
		contents.put(name, o);
	} 
	
	/**
	 * M&eacutetodo para obtener un elemento por su nombre/llave.
	 *
	 * @param name				<code>String</code>
	 * @return 					<code>Object</code>
	 */
	public Object get(String name) {
		
		return contents.get(name);
	}
	
	/**
	 * M&eacutetodo para obtener un elemento por &iacutendice.
	 *
	 * @param name				<code>int</code>
	 * @return 					<code>Object</code>
	 */
	public Object getContentAt(int index) {
		
		ArrayList<String> order = (ArrayList<String>) contents.get("order");
		
		String key = order.get(index);
		
		Object o = contents.get(key);
		
		return o;
		/*Object o;
		int i;

		o = null;
		i = 0;
		
		for(Enumeration e = contents.keys(); e.hasMoreElements();) {
			
			o = contents.get(e.nextElement());
			
			if(i == index)
				break;
			
			i++;
		}
		
		return o;*/
	}
	
	/**
	 * M&eacutetodo para obtener una llave por &iacutendice.
	 *
	 * @param name				<code>int</code>
	 * @return 					<code>Object</code>
	 */
	public String getKeyAt(int index) {
		
		ArrayList<String> order = (ArrayList<String>) contents.get("order");
		
		String key = order.get(index);
		
		//Object o = contents.get(key);
		
		return key;
		
		/*String key;
		int i;
		
		key = "";
		i = 0;
		
		for(Enumeration e = contents.keys(); e.hasMoreElements();) {
			
			key = (String)e.nextElement();
			
			if(i == index)
				break;
			
			i++;
		}
		
		return key;*/
	}
	
	/**
	 * M&eacutetodo para obtener la cantidad de elemento.
	 *
	 * @return 					<code>int</code>
	 */
	public int getNumContents() {
		
		return this.contents.size();
	}
	
	public int getNumText(){
		
		int count = 0;
		Object o;
		
		for(Enumeration e = contents.keys(); e.hasMoreElements();) {
			
			o = contents.get(e.nextElement());
			
			if( o instanceof JTextField || o instanceof JTextArea){
				count++;
			}
		
		}
		return count;
	}
}