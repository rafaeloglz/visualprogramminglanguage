/**
 * Clase encarga de los procesos de guardar y cargar archivos de trabajo.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package graph;

import dataio.*;

public class LoadAndSave<O> {

	private Load<O> in;
	private Save<O> out;

	/**
	 * M&eacute;todo para guardar al grafo.
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean save(O obj, String path, String filename) {

		out = new Save(obj, path, filename);
		return out.saveObj();
	}

	/**
	 * M&eacute;todo para cargar al grafo.
	 * 
	 * @return <code>boolean</code>
	 */
	public O load(String path, String filename) {

		O obj;

		in = new Load(path, filename);
		obj = in.loadObj();

		return obj;
	}
}