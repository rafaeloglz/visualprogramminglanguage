/**
 * Clase que contiene los datos del valor de los vertices.
 * 
 * @author Andr&eacute Freyr&iacutea Cedeño
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figuero Ram&iacuterez
 * @author Jos&eacute Roberto Ram&iacuterez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package struct;

import java.io.Serializable;
import gui.*;

public class StructE<E> implements Serializable {
	
	private Line line;
	private E value;
	
	/**
	 * Constructor por omisi&oacuten.
	 *
	 * @param line			<code>Line</code>
	 * @param value			<code>value</code>
	 */
	public StructE(Line line, E value) {
		
		setLine(line);
		setValue(value);
	}
	
	/**
	 * M&eacutetodo para especificar el <code>Line</code>.
	 *
	 * @param line			<code>Line</code>
	 */
	public void setLine(Line line) {
		
		this.line = line;
	}
	
	/**
	 * M&eacutetodo para especificar el segundo par&aacutemetro.
	 *
	 * @param value			<code>E</code>
	 */
	public void setValue(E value) {
		
		this.value = value;
	}
	
	/**
	 * M&eacutetodo para obtener el <code>Line</code>.
	 *
	 * @return			<code>Line</code>
	 */
	public Line getLine() {
		
		return line;
	}
	
	/**
	 * M&eacutetodo para obtener el segundo par&aacutemetro.
	 *
	 * @return			<code>E</code>
	 */
	public E getValue() {
		
		return value;
	}
	
	/**
	 * M&eacutetodo para realizar comparaciones entre structs.
	 * 
	 * @param s			<code>StructE<E></code>
	 * @return			<code>boolean</code>
	 */
	public boolean equals (StructE<E> s) {
		
		return this.line.equals(s.getLine());
	}
}