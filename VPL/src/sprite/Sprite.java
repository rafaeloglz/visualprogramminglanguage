/**
 * Representaci&oacute;n gr&aacute;fica de los componentes de la aplicacion.
 *  
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Sprite implements Transferable, Serializable, Cloneable {
	
	protected int x;
	protected int y;
	protected int relX;
	protected int relY;
	protected int height;
	protected int width;
	protected ArrayList<Sprite> spriteList;
	protected boolean connectable;
	protected boolean used;
	protected ImageIcon background;
	protected Color fillColor;
	protected Color borderColor;	
	public static DataFlavor spriteDataFlavor;
	public DataFlavor [] supportedFlavors = {spriteDataFlavor, DataFlavor.stringFlavor};
	
	/**
	 * Constructor por omisi&oacute;n.
	 */
	public Sprite () {		
		spriteDataFlavor = new DataFlavor (Sprite.class, "Sprite");		
		setSpriteList(new ArrayList<Sprite>());
		attachConnectors();
	}
	
	/**
	 * Constructor donde se especifican coordenadas y dimensiones.
	 *
	 * @param x				<code>int</code>
	 * @param y				<code>int</code>
	 * @param height		<code>int</code>
	 * @param width			<code>int</code>
	 */
	public Sprite (int x, int y, int height, int width) {
		spriteDataFlavor = new DataFlavor (Sprite.class, "Sprite");
		setSpriteList(new ArrayList<Sprite>());
		setX(x);
		setY(y);
		setHeight(height);
		setWidth(width);
		attachConnectors();
	}
	
	/**
     *calcValues calcula el valor de Ancho y Alto en base a los Sprites
     *que se contengan.      
     **/
	public void calcValues(){
		Iterator<Sprite> i = spriteList.iterator();
		int maxX = 0, maxY = 0;
		
		if (background!=null){
			maxX=background.getIconWidth();
			maxY=background.getIconHeight();
		}
		
		this.setHeight(maxY);
		this.setWidth(maxX);
	}
	
	/**
	 * Metodo que especifica los conectores del Sprite.
	 * Se a�aden a spriteList en posicion relativa al Sprite.
	 */
	protected void attachConnectors(){}
	
	/**
	 * M&eacute;todo para especificar el valor relativo de la coordenada x.
	 *
	 * @param x		<code>int</code>
	 */
	public void setX(int x) {
		
		this.x = x + relX;
	}
	
	/**
	 * M&eacute;todo para especificar el valor relativo de la coordenada y.
	 *
	 * @param y		<code>int</code>
	 */
	public void setY(int y) {
		
		this.y = y + relY;
	}	
	
	/**
	 * M&eacute;todo para especificar la altura.
	 *
	 * @param height 	<code>int</code>
	 */
	public void setHeight(int height) {
		
		this.height = height;
	}
	
	/**
	 * M&eacute;todo para especificar el ancho.
	 *
	 * @param width 	<code>int</code>
	 */
	public void setWidth(int width) {
		
		this.width = width;
	}
	
	/**
	 * M&eacute;todo para especificar la lista de <code>Sprite</code> que contiene.
	 *
	 * @param list 		<code>ArrayList<Sprite></code>
	 */
	protected void setSpriteList(ArrayList<Sprite> list) {
		
		spriteList = list;
	}
	
	/**
	 * M&eacute;todo para especificar si se puede conectar.
	 *
	 * @param c 		<code>boolean</code>
	 */
	public void setConnectable(boolean c) {
		
		connectable = c;
	}
	
	/**
	 * M&eacute;todo para especificar si ya est&aacute; conectado.
	 *
	 * @param u 		<code>boolean</code>
	 */
	public void setUsed(boolean u) {
		
		used = u;
	}

	/**
	 * M&eacute;todo para carga una imagen como background desde un archivo externo
	 *
	 * @param filename 		<code>String</code>
	 */
	
	public void loadImageFile(String filename){
		
		Image bck = null;
						
		try {
			
			bck = ImageIO.read( new File( SpriteConfig.IMAGE_PATH + filename ) );
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		setBackground(new ImageIcon (bck));
		
	}
		
	/**
	 * M&eacute;todo para especificar el fondo.
	 *
	 * @param img 		<code>Image</code>
	 */
	public void setBackground(ImageIcon img) {
		
		background = img;
	}
	
	/**
	 * M&eacute;todo para especificar el color de relleno.
	 *
	 * @param c			<code>Color</code>
	 */
	public void setFillColor(Color c) {
		
		fillColor = c;
	}
	
	/**
	 * M&eacute;todo para especificar el color del borde.
	 *
	 * @param c			<code>Color</code>
	 */
	public void setBorderColor(Color c) {
		
		borderColor = c;
	}
	
	/**
	 * M&eacute;todo para obtener el valor relativo de la coordenada x. 
	 *
	 * @return			<code>int</code>
	 */
	public int getX() {
		
		return x;
	}
	
	/**
	 * M&eacute;todo para obtener el valor relativo de la coordenada y. 
	 *
	 * @return			<code>int</code>
	 */
	public int getY() {
		
		return y;
	}	
		
	/**
	 * M&eacute;todo para obtener la altura. 
	 *
	 * @return			<code>int</code>
	 */
	public int getHeight() {
		
		return height;
	}
	
	/**
	 * M&eacute;todo para obtener el ancho. 
	 *
	 * @return			<code>int</code>
	 */
	public int getWidth() {
		
		return width;
	}
	
	/**
	 * M&eacute;todo para obtener la lista de <code>Sprite</code> que contiene. 
	 *
	 * @return			<code>ArrayList<Sprite></code>
	 */
	protected ArrayList<Sprite> getSpriteList() {
		
		return spriteList;
	}
	
	/**
	 * M&eacute;todo para obtener un elemento de la lista de <code>Sprite</code> que contiene. 
	 *
	 * @return			<code>ArrayList<Sprite></code>
	 */
	public Sprite getSprite(int index) {
		
		return spriteList.get(index);
	}
	
	/**
	 * M&eacute;todo para obtener el fondo. 
	 *
	 * @return			<code>Image</code>
	 */
	public ImageIcon getBackground() {
		
		return background;
	}
	
	/**
	 * M&eacute;todo para obtener el color de relleno. 
	 *
	 * @return			<code>Color</code>
	 */
	public Color getFillColor() {
		
		return fillColor;
	}
	
	/**
	 * M&eacute;todo para obtener el color de borde. 
	 *
	 * @return			<code>Color</code>
	 */
	public Color getBorderColor() {
		
		return borderColor;
	}

	/**
	 * M&eacute;todo para saber si el Sprite actual es conectable 
	 *
	 * @return			<code>boolean</code>
	 */	
	public boolean getConnectable(){
		return connectable;
	}
	
	/**
	 * M&eacute;todo para obtener si el Sprite actual esta siendo utilizado 
	 *
	 * @return			<code>boolean</code>
	 */	
	public boolean isUsed() {		
		return used;
	}
	
	/**
	 * M&eacute;todo para obtener el numero el Sprites que contiene el Sprite Actual 
	 *
	 * @return			<code>int</code>
	 */	
	public int getNumSprite(){
		return spriteList.size();
	}
	
	/**
	 * M&eacute;todo para dibujar al <code>Sprite</code>.
	 *
	 * @param g 		<code>Graphics</code>
	 */
	public void paintSprite(Graphics g){
		g.drawImage(getBackground().getImage(), getX(), getY(), null);
		
		for(int i=0;i<this.getSpriteList().size();i++){
			Sprite tmp = getSpriteList().get(i);
			tmp.paintSprite(g,x,y);			
		}
	}
	
	/**
	 * M&eacute;todo para asignar el valor Real de la coordenada en X del Sprite 
	 *
	 * @param	x	<code>int</code>
	 */
	protected void setRelX(int x){
		this.relX = x;
	}

	/**
	 * M&eacute;todo para asignar el valor Real de la coordenada en Y del Sprite 
	 *
	 * @param	y	<code>int</code>
	 */
	protected void setRelY(int y){
		this.relY = y;
	}
	
	
	/**
	 * M&eacute;todo para agregar un Sprite al Sprite actual 
	 *
	 * @param	relX	<code>int</code>
	 * @param	relY	<code>int</code>
	 * @param	s		<code>Sprite</code>
	 */
	protected void attach(int relX, int relY, Sprite s){
		s.setRelX(relX);
		s.setRelY(relY);
		s.setX(x);
		s.setY(y);
		spriteList.add(s);
	}

	/**
	 * M&eacute;todo para pintar el Sprite actual 
	 *
	 * @param	relX	<code>int</code>
	 * @param	relY	<code>int</code>
	 * @param	s		<code>Sprite</code>
	 */
	protected void paintSprite(Graphics g, int x, int y){
	}
	
	/**
	 * M&eacute;todo para  determinar si el <code>Sprite</code> intersecta con el rect&aacute;ngulo enviado 
	 * como para&aacute;etro.
	 *
	 * @param r		<code>Rectangle2D</code>	
	 * @return		<code>boolean</code>
	 */
	public boolean intersects(Rectangle2D r) {
		if (r == null) return false;
		if(r.getX() > getX() && r.getX() < getX() + getWidth())
			if(r.getY() > getY() && r.getY() < getY() + getHeight()){
				return true;
			}
		
		return false;
	}
	
	/**
  	 * M&eacute;todo para obtener al objeto en el formato solicitado o una excepci&oacute;n.
  	 *
  	 * @param flavor	<code>DataFlavor</code>
  	 * @return			<code>boolean</code>
  	 */
	public Object getTransferData(DataFlavor flavor)throws UnsupportedFlavorException {
	
		if(flavor.equals(spriteDataFlavor)) return this;
    	else if(flavor.equals(DataFlavor.stringFlavor)) return toString();
   		else throw new UnsupportedFlavorException(flavor);
	}
	
	/**
  	 * M&eacute;todo para obtener los DataFlavor soportados.
  	 *
  	 * @return 		<code>DataFlavor[]</code>
  	 */
	public DataFlavor [] getTransferDataFlavors() {
  
  		return supportedFlavors.clone ();
  	}
	
	/** 
  	 * M&eacute;todo para revisar si se soporta el DataFlavor.
  	 * 
  	 * @param flavor	<code>DataFlavor</code>
  	 * @return			<code>boolean</code>
  	 */
	public boolean isDataFlavorSupported(DataFlavor flavor) {
	
		return (flavor.equals(spriteDataFlavor) || flavor.equals(DataFlavor.stringFlavor));
	}
	
	/**
  	 * M&eacute;todo para crear un clon del objeto y sus atributos.
  	 * 
  	 * @return		<code>Sprite</code>
  	 */
	@Override
	public Sprite clone() throws CloneNotSupportedException{
		
		try{
			Sprite s = (Sprite) super.clone();
			s.setSpriteList(new ArrayList<Sprite>());
			s.attachConnectors();
			return s;
		}
		catch(CloneNotSupportedException e){
			return this;
		}
	}
}