/**
 * Clase que representa una Figura Circulo
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package sprite;

import java.awt.Graphics;

public class Circle extends Sprite {

	/**
	 * Constructor donde se especifican coordenadas y dimensiones.
	 * 
	 * @param x
	 *            <code>int</code>
	 * @param y
	 *            <code>int</code>
	 * @param height
	 *            <code>int</code>
	 * @param width
	 *            <code>int</code>
	 * @param color
	 *            <code>Color</code>
	 */
	public Circle(int x, int y, int height, int width) {
		super(x, y, width, height);
	}

	/**
	 * M&eacutetodo para pintar el Sprite actual
	 * 
	 * @param g
	 *            Objeto Grafico <code>Graphics</code>
	 */
	@Override
	public void paintSprite(Graphics g) {
	}

	/**
	 * M&eacutetodo para pintar el Sprite actual
	 * 
	 * @param g
	 *            Objeto Grafico <code>Graphics</code>
	 * @param x
	 *            Coordenada en x <code>int</code>
	 * @param y
	 *            Coordenada en y <code>int</code>
	 */
	@Override
	protected void paintSprite(Graphics g, int x, int y) {
		setX(x);
		setY(y);
	}

	/**
	 * M&eacutetodo para crear un clon del objeto y sus atributos.
	 * 
	 * @return <code>Circle</code>
	 * @throws CloneNotSupportedException
	 */
	@Override
	public Sprite clone() {
		return new Circle(this.x, this.y, this.height, this.width);
	}
}