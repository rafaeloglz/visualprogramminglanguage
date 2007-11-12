/**
 * Clase que define la configuraci&oacute;n de las figuras
 *  
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package sprite;

public class SpriteConfig {
	//Directorio en donde se encuentran las imagenes
	public static final String IMAGE_PATH = "images/";	
	//Tipo de Imagen
	public static final String IMAGE_FORMAT = ".jpg";	
	//Imagenes a cargar en Sprites
	public static final String END_IMAGE = "end"+IMAGE_FORMAT;
	public static final String INSTRUCTION_IMAGE = "instruction"+IMAGE_FORMAT;
	public static final String LOCK_IMAGE = "lock"+IMAGE_FORMAT;
	public static final String FOR_IMAGE = "for"+IMAGE_FORMAT;
	public static final String FUNCTION_IMAGE = "function"+IMAGE_FORMAT;
	public static final String GLOBALVAR_IMAGE = "globalvar" + IMAGE_FORMAT;
	public static final String IF_IMAGE = "if"+IMAGE_FORMAT;
	public static final String PARALLEL_IMAGE = "parallel"+IMAGE_FORMAT;
	public static final String SEMAPHORE_IMAGE = "semaphore"+IMAGE_FORMAT;
	public static final String SYNC_IMAGE = "sync"+IMAGE_FORMAT;
	public static final String BEGIN_IMAGE = "begin" + IMAGE_FORMAT;
	public static final String UNION_IMAGE = "union" + IMAGE_FORMAT;
	public static final String WHILE_IMAGE = "while" + IMAGE_FORMAT;
	public static final String VAR_IMAGE = "var" + IMAGE_FORMAT;
}