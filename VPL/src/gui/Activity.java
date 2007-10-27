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

import javax.swing.JTable;
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
		this.contents = new Hashtable(numContents);
	}
	
	/**
	 * M&eacutetodo para agregar un elemento junto con su nombre/llave.
	 *
	 * @param name				<code>String</code>
	 * @param o					<code>Object</code>
	 */
	public void add(String name, Object o) {
		this.contents.put(name, o);
	} 
	
	/**
	 * M&eacutetodo para obtener un elemento por su nombre/llave.
	 *
	 * @param name				<code>String</code>
	 * @return 					<code>Object</code>
	 */
	public Object get(String name) {
		return this.contents.get(name);
	}
	
	/**
	 * M&eacutetodo para obtener un elemento por &iacutendice.
	 *
	 * @param name				<code>int</code>
	 * @return 					<code>Object</code>
	 */
	public Object getContentAt(int index) {
		
		ArrayList<String> order = (ArrayList<String>) this.contents.get("order");
		
		String key = order.get(index);
		
		Object o = this.contents.get(key);
		
		return o;
	}
	
	/**
	 * M&eacutetodo para obtener una llave por &iacutendice.
	 *
	 * @param name				<code>int</code>
	 * @return 					<code>Object</code>
	 */
	public String getKeyAt(int index) {
		
		ArrayList<String> order = (ArrayList<String>) this.contents.get("order");
		
		String key = order.get(index);
		
		return key;
	}
	
	/**
	 * M&eacutetodo para obtener la cantidad de elementos.
	 *
	 * @return 					<code>int</code>
	 */
	public int getContentsCount() {
		return this.contents.size();
	}
	
	public int getKeyCount(){
		
		int count = 0;
		Object o;
		
		for(Enumeration e = this.contents.keys(); e.hasMoreElements();) {
			
			if(!e.nextElement().equals("order"))
				count++;
		}
		return count;
	}
}