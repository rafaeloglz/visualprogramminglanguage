/**
 * Clase que guarda el c&oacutedigo en un archivo de texto.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package dataio;

import java.io.File;
import java.io.PrintWriter;

public class CodeWriter {

	private String code;
	private String path;
	private String filename;
	private PrintWriter out;

	/**
	 * Constructor por omisi&oacute;n.
	 * 
	 * @param code
	 *            <code>String</code>
	 * @param path
	 *            <code>String</code>
	 * @param filename
	 *            <code>String</code>
	 */
	public CodeWriter(String code, String path, String filename) {

		this.code = code;
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
	 * M&eacute;todo para guardar el c&oacute;digo en el archivo con el nombre y
	 * ruta especificados.
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean write() {

		boolean success = false;

		try {

			out = new PrintWriter(new File(path + filename));
			out.write(code);
			out.close();
			success = true;
		} catch (Exception e) {
			return false;
		}

		return success;
	}
}