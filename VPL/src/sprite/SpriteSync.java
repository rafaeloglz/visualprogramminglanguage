/**
 * Representaci&oacuten gr&aacutefica de la Figura Sync
 *  
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package sprite;

import java.awt.Color;

public class SpriteSync extends Sprite {

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
	public SpriteSync(int x, int y, int height, int width) {
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.SYNC_IMAGE);
	}

	/**
	 * Constructor por omisi&oacute;n
	 * 
	 */
	public SpriteSync() {
		this.loadImageFile(SpriteConfig.SYNC_IMAGE);
		calcValues();
	}

	/**
	 * M&eacute;todo que agrega los conectores a la figura
	 * 
	 */
	@Override
	public void attachConnectors() {
		Sprite s = new Square(0, 0, 7, 40, Color.cyan);
		attach(2, 2, s);

		s = new Square(0, 0, 9, 14, Color.magenta);
		attach(66, 2, s);

		s = new Circle(0, 0, 10, 10);
		attach(2, 42, s);
	}
}