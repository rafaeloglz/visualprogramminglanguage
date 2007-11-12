/**
 * Representaci&oacute;n gr&aacute;fica de la Figura Begin
 *  
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package sprite;
import java.awt.Color;

public class SpriteBegin extends Sprite {

	/**
	 * Constructor donde se especifican coordenadas y dimensiones.
	 *
	 * @param x				<code>int</code>
	 * @param y				<code>int</code>
	 * @param height		<code>int</code>
	 * @param width			<code>int</code>
	 */	
	public SpriteBegin(int x, int y, int height, int width){
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.BEGIN_IMAGE);
	}

	/**
	 * Constructor por omisi&oacute;n
	 *
	 */
	public SpriteBegin() {
		this.loadImageFile(SpriteConfig.BEGIN_IMAGE);
		calcValues();
	}

	/**
	 * M&eacute;todo que agrega los conectores a la figura
	 *
	 */
	@Override
	public void attachConnectors(){
		Sprite s = new Square(0, 0, 11, 11, Color.magenta);
		attach(59, 9, s);		
	}
}