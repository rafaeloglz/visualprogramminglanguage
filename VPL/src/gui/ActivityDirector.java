/**
 * Clase que inicia al Builder es un Singleton
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import struct.StructV;

public class ActivityDirector {

	private static ActivityDirector instance;

	/**
	 * Constructor por omisi&oacuten.
	 * 
	 */
	private ActivityDirector() {
	}

	/**
	 * M&eacute;todo que obtiene la instancia de la clase, la cual aplica
	 * Singleton
	 * 
	 */
	public static ActivityDirector getInstance() {
		if (instance == null) {
			instance = new ActivityDirector();
		}
		return instance;
	}

	/**
	 * M&eacute;todo que construye un Activity
	 * 
	 * @param activityBuilder
	 *            <code>ActivityBuilder</code>
	 * @param activity
	 *            <code>String</code>
	 * @param stv
	 *            <code>StructV</code>
	 */
	public void construct(ActivityBuilder activityBuilder, String activity,
			StructV stv) {
		activityBuilder.buildActivity(activity, stv);
	}
}