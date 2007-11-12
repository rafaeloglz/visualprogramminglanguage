/**
 * Clase que crea objetos a partir de un archivo.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package dataio;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Load<L> {

	private L obj;
	private String path;
	private String filename;
	private ObjectInputStream in;

	/**
	 * Constructor por omisi&oacuten.
	 * 
	 * @param path
	 *            <code>String</code>
	 * @param filename
	 *            <code>String</code>
	 */
	public Load(String path, String filename) {

		obj = null;
		this.path = path;
		this.filename = filename;
	}

	/**
	 * M&eacutetodo para especificar el path del archivo.
	 * 
	 * @param path
	 *            <code>String</code>
	 */
	public void setFilePath(String path) {

		this.path = path;
	}

	/**
	 * M&eacutetodo para especificar el nombre del archivo.
	 * 
	 * @param filename
	 *            <code>String</code>
	 */
	public void setFileName(String filename) {

		this.filename = filename;
	}

	/**
	 * M&eacutetodo para obtener la ruta del archivo.
	 * 
	 * @return <code>String</code>
	 */
	public String getFilePath() {

		return path;
	}

	/**
	 * M&eacutetodo para obtener el nombre del archivo.
	 * 
	 * @return <code>String</code>
	 */
	public String getFileName() {

		return filename;
	}

	/**
	 * M&eacutetodo para crear el objeto a parti del archivo con el nombre y
	 * ruta especificados.
	 * 
	 * @return <code>L</code>
	 */
	public L loadObj() {

		try {

			in = new ObjectInputStream(new FileInputStream(new File(path
					+ filename)));
			obj = (L) in.readObject();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return obj;
	}
}