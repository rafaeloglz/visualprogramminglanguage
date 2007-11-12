/**
 * Clase que guarda objetos serializables en un archivo.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package dataio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Save<S> {

	private S obj;
	private String path;
	private String filename;
	private ObjectOutputStream out;

	/**
	 * Constructor por omisi&oacute;n.
	 * 
	 * @param obj
	 *            <code>S</code>
	 * @param path
	 *            <code>String</code>
	 * @param filename
	 *            <code>String</code>
	 */
	public Save(S obj, String path, String filename) {

		this.obj = obj;
		this.path = path;
		this.filename = filename;
	}

	/**
	 * M&eacute;todo para especificar el path del archivo.
	 * 
	 * @param path
	 *            <code>String</code>
	 */
	public void setFilePath(String path) {

		this.path = path;
	}

	/**
	 * M&eacute;todo para especificar el nombre del archivo.
	 * 
	 * @param filename
	 *            <code>String</code>
	 */
	public void setFileName(String filename) {

		this.filename = filename;
	}

	/**
	 * M&eacute;todo para obtener la ruta del archivo.
	 * 
	 * @return <code>String</code>
	 */
	public String getFilePath() {

		return path;
	}

	/**
	 * M&eacute;todo para obtener el nombre del archivo.
	 * 
	 * @return <code>String</code>
	 */
	public String getFileName() {

		return filename;
	}

	/**
	 * M&eacute;todo para guardar el objeto en el archivo con el nombre y ruta
	 * especificados.
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean saveObj() {

		boolean success = false;

		try {

			out = new ObjectOutputStream(new FileOutputStream(new File(path
					+ filename)));
			out.writeObject(obj);
			out.close();
			success = true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return success;
	}
}