/**
 * Clase que contiene los datos del valor de los vertices.
 * 
 * @author Andr&eacute; Freyr&iacute;a Cede�o
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figuero Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package struct;

import java.io.Serializable;
import gui.*;

public class StructE<E> implements Serializable {

	private Line line;
	private E value;

	/**
	 * Constructor por omisi&oacute;n.
	 * 
	 * @param line
	 *            <code>Line</code>
	 * @param value
	 *            <code>value</code>
	 */
	public StructE(Line line, E value) {

		setLine(line);
		setValue(value);
	}

	/**
	 * M&eacute;todo para especificar el <code>Line</code>.
	 * 
	 * @param line
	 *            <code>Line</code>
	 */
	public void setLine(Line line) {

		this.line = line;
	}

	/**
	 * M&eacute;todo para especificar el segundo par&aacute;metro.
	 * 
	 * @param value
	 *            <code>E</code>
	 */
	public void setValue(E value) {

		this.value = value;
	}

	/**
	 * M&eacute;todo para obtener el <code>Line</code>.
	 * 
	 * @return <code>Line</code>
	 */
	public Line getLine() {

		return line;
	}

	/**
	 * M&eacute;todo para obtener el segundo par&aacute;metro.
	 * 
	 * @return <code>E</code>
	 */
	public E getValue() {

		return value;
	}

	/**
	 * M&eacute;todo para realizar comparaciones entre structs.
	 * 
	 * @param s
	 *            <code>StructE<E></code>
	 * @return <code>boolean</code>
	 */
	public boolean equals(StructE<E> s) {

		return this.line.equals(s.getLine());
	}
}