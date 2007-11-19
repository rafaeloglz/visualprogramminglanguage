/**
 * Representaci&oacute;n gr&aacute;fica de la Figura For
 *  
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package sprite;

import java.awt.Color;

public class SpriteFor extends Sprite {

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
	 */
	public SpriteFor(int x, int y, int height, int width) {
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.FOR_IMAGE);
	}

	/**
	 * Constructor por omisi&oacute;n
	 * 
	 */
	public SpriteFor() {
		this.loadImageFile(SpriteConfig.FOR_IMAGE);
		calcValues();
	}

	/**
	 * M&eacute;todo que agrega los conectores a la figura
	 * 
	 */
	@Override
	public void attachConnectors() {
		
		Sprite s = new Circle(0, 0, 10, 10);
		attach(1, 44, s);

		s = new Square(0, 0, 9, 14, Color.magenta);
		attach(25, 0, s);

		s = new Square(0, 0, 9, 14, Color.magenta);
		attach(67, 2, s);

		s = new Square(0, 0, 9, 14, Color.cyan);
		attach(1, 2, s);

		s = new Square(0, 0, 9, 14, Color.cyan);
		attach(37, 0, s);	

		s = new Circle(0, 0, 10, 10);
		attach(67, 44, s);
	}
}