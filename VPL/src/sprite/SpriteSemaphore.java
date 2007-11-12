/**
 * Representaci&oacute;n gr&aacute;fica de la Figura Semaphore
 *  
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package sprite;

public class SpriteSemaphore extends Sprite {

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
	public SpriteSemaphore(int x, int y, int height, int width) {
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.SEMAPHORE_IMAGE);
	}

	/**
	 * Constructor por omisi&oacute;n
	 * 
	 */
	public SpriteSemaphore() {
		this.loadImageFile(SpriteConfig.SEMAPHORE_IMAGE);
		calcValues();
	}

	/**
	 * M&eacute;todo que agrega los conectores a la figura
	 * 
	 */
	@Override
	public void attachConnectors() {
		Sprite s = new Circle(0, 0, 8, 44);
		attach(2, 6, s);

		s = new Circle(0, 0, 8, 44);
		attach(60, 6, s);
	}
}