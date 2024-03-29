/**
 * Clase que contiene los elementos para agregar informaci&oacute a los componentes.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Activity {

	private Hashtable<String, Object> contents;

	/**
	 * Constructor por omisi&oacute;n.
	 * 
	 * @param numContents
	 *            <code>int</code>
	 */
	public Activity(int numContents) {
		this.contents = new Hashtable(numContents);
	}

	/**
	 * M&eacute;todo para agregar un elemento junto con su nombre/llave.
	 * 
	 * @param name
	 *            <code>String</code>
	 * @param o
	 *            <code>Object</code>
	 */
	public void add(String name, Object o) {
		this.contents.put(name, o);
	}

	/**
	 * M&eacute;todo para obtener un elemento por su nombre/llave.
	 * 
	 * @param name
	 *            <code>String</code>
	 * @return <code>Object</code>
	 */
	public Object get(String name) {
		return this.contents.get(name);
	}

	/**
	 * M&eacute;todo para obtener un elemento por &iacute;ndice.
	 * 
	 * @param index
	 *            <code>int</code>
	 *            
	 * @return <code>Object</code>
	 */
	public Object getContentAt(int index) {

		ArrayList<String> order = (ArrayList<String>) this.contents
				.get("order");

		String key = order.get(index);

		Object o = this.contents.get(key);

		return o;
	}

	/**
	 * M&eacute;todo para obtener una llave por &iacute;ndice.
	 * 
	 * @param index
	 *            <code>int</code>
	 *            
	 * @return <code>Object</code>
	 */
	public String getKeyAt(int index) {

		ArrayList<String> order = (ArrayList<String>) this.contents
				.get("order");

		String key = order.get(index);

		return key;
	}

	/**
	 * M&eacute;todo para obtener la cantidad de elementos.
	 * 
	 * @return <code>int</code>
	 */
	public int getContentsCount() {
		return this.contents.size();
	}

	public int getKeyCount() {

		int count = 0;
		Object o;

		for (Enumeration e = this.contents.keys(); e.hasMoreElements();) {

			if (!e.nextElement().equals("order"))
				count++;
		}
		return count;
	}
}