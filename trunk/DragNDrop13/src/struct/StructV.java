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
import sprite.*;

public class StructV<V> implements Serializable {
	
	private Sprite sprite;
	private V value;
	
	/**
	 * Constructor por omisi&oacuten.
	 *
	 * @param sprite		<code>Sprite</code>
	 * @param value			<code>value</code>
	 */
	public StructV(Sprite sprite, V value) {
		
		setSprite(sprite);
		setValue(value);
	}
	
	/**
	 * M&eacutetodo para especificar el <code>Sprite</code>.
	 *
	 * @param sprite		<code>Sprite</code>
	 */
	public void setSprite(Sprite sprite) {
		
		this.sprite = sprite;
	}
	
	/**
	 * M&eacutetodo para especificar el segundo par&aacutemetro.
	 *
	 * @param value			<code>V</code>
	 */
	public void setValue(V value) {
		
		this.value = value;
	}
	
	/**
	 * M&eacutetodo para obtener el <code>Sprite</code>.
	 *
	 * @return			<code>Sprite</code>
	 */
	public Sprite getSprite() {
		
		return sprite;
	}
	
	/**
	 * M&eacutetodo para obtener el segundo par&aacutemetro.
	 * @return 
	 * @return 
	 *
	 * @return			<code>V</code>
	 */
	public V getValue() {		
		return value;
	}
	
	/**
	 * M&eacutetodo para realizar comparaciones entre structs.
	 * 
	 * @param s			<code>StructV<V></code>
	 * @return			<code>boolean</code>
	 */
	public boolean equalsTo(StructV<V> s) {
		
		return this.sprite.equals(s.getSprite());
	}
}