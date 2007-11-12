/**
 * Representaci&oacuten gr&aacutefica de la Figura Union
 *  
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package sprite;

import java.awt.Color;

public class SpriteUnion extends Sprite {

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
	public SpriteUnion(int x, int y, int height, int width) {
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.UNION_IMAGE);
	}

	/**
	 * Constructor por omisi&oacute;n
	 * 
	 */
	public SpriteUnion() {
		this.loadImageFile(SpriteConfig.UNION_IMAGE);
		calcValues();
	}

	/**
	 * M&eacute;todo que agrega los conectores a la figura
	 * 
	 */
	@Override
	public void attachConnectors() {
		Sprite s = new Square(0, 0, 9, 14, Color.magenta);
		attach(68, 9, s);

		s = new Circle(0, 0, 9, 9);
		attach(1, 46, s);

		s = new Circle(0, 0, 9, 9);
		attach(69, 45, s);

		s = new Square(0, 0, 9, 14, Color.cyan);
		attach(1, 9, s);

		s = new Square(0, 0, 9, 14, Color.cyan);
		attach(1, 29, s);

	}
}