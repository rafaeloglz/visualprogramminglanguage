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
import sprite.*;

public class StructV<V> implements Serializable {

	private Sprite sprite;
	private V value;

	/**
	 * Constructor por omisi&oacute;n.
	 * 
	 * @param sprite
	 *            <code>Sprite</code>
	 * @param value
	 *            <code>value</code>
	 */
	public StructV(Sprite sprite, V value) {

		setSprite(sprite);
		setValue(value);
	}

	/**
	 * M&eacute;todo para especificar el <code>Sprite</code>.
	 * 
	 * @param sprite
	 *            <code>Sprite</code>
	 */
	public void setSprite(Sprite sprite) {

		this.sprite = sprite;
	}

	/**
	 * M&eacute;todo para especificar el segundo par&aacute;metro.
	 * 
	 * @param value
	 *            <code>V</code>
	 */
	public void setValue(V value) {

		this.value = value;
	}

	/**
	 * M&eacute;todo para obtener el <code>Sprite</code>.
	 * 
	 * @return <code>Sprite</code>
	 */
	public Sprite getSprite() {

		return sprite;
	}

	/**
	 * M&eacute;todo para obtener el segundo par&aacute;metro.
	 * 
	 * @return <code>V</code>
	 */
	public V getValue() {
		return value;
	}

	/**
	 * M&eacute;todo para realizar comparaciones entre structs.
	 * 
	 * @param s
	 *            <code>StructV<V></code>
	 * @return <code>boolean</code>
	 */
	public boolean equalsTo(StructV<V> s) {

		return this.sprite.equals(s.getSprite());
	}
}