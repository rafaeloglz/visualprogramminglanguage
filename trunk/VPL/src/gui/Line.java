/**
 * Connector es la clase que representa la liga entre dos componentes.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.io.Serializable;

import sprite.Sprite;

public class Line implements Serializable {

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Sprite source;
	private Sprite dest;
	private Sprite sourceCon;
	private Sprite destCon;
	private int sourceIndex;
	private int destIndex;

	/**
	 * Constructor donde se especifica las figura inicial y final y el
	 * identificador del conector de cada uno.
	 * 
	 * @param source
	 *            <code>Sprite</code>
	 * @param dest
	 *            <code>Sprite</code>
	 * @param sourceIndex
	 *            <code>int</code>
	 * @param destIndex
	 *            <code>int</code>
	 */
	public Line(Sprite source, Sprite dest, int sourceIndex, int destIndex) {

		this.source = source;
		this.dest = dest;
		this.sourceIndex = sourceIndex;
		this.destIndex = destIndex;
	}

	/**
	 * Constructor donde se especifica las figura inicial y final y el
	 * identificador del conector de cada uno.
	 * 
	 * @param source
	 *            <code>Sprite</code>
	 * @param sourceIndex
	 *            <code>int</code>
	 */
	public Line(Sprite source, int sourceIndex) {

		this.source = source;
		this.dest = null;
		this.sourceIndex = sourceIndex;
		this.destIndex = -1;
	}

	/**
	 * Metodo para especificar la coordenada en x inicial.
	 * 
	 * @param x
	 *            <code>int</code>
	 */
	public void setX1(int x) {

		x1 = x;
	}

	/**
	 * Metodo para especificar la coordenada en x final.
	 * 
	 * @param x
	 *            <code>int</code>
	 */
	public void setX2(int x) {

		x2 = x;
	}

	/**
	 * Metodo para especificar la coordenada en x inicial.
	 * 
	 * @param y
	 *            <code>int</code>
	 */
	public void setY1(int y) {

		y1 = y;
	}

	/**
	 * Metodo para especificar la coordenada en y final.
	 * 
	 * @param y
	 *            <code>int</code>
	 */
	public void setY2(int y) {

		y2 = y;
	}

	/**
	 * Metodo para especificar la figura inicial.
	 * 
	 * @param source
	 *            <code>Sprite</code>
	 */
	public void setSource(Sprite source) {

		this.source = source;
	}

	/**
	 * Metodo para especificar la figura final.
	 * 
	 * @param dest
	 *            <code>Sprite</code>
	 */
	public void setDest(Sprite dest) {

		this.dest = dest;
	}

	/**
	 * Metodo para especificar index2.
	 * 
	 * @param dest
	 *            <code>Sprite</code>
	 */
	public void setDestIndex(int destIndex) {

		this.destIndex = destIndex;
	}

	/**
	 * Metodo para obtener la coordenada x inicial.
	 * 
	 * @return <code>int</code>
	 */
	public int getX1() {

		return x1;
	}

	/**
	 * Metodo para obtener la coordenada index2.
	 * 
	 * @return <code>int</code>
	 */
	public int getDestIndex() {

		return this.destIndex;
	}

	/**
	 * Metodo para obtener la coordenada index2.
	 * 
	 * @return <code>int</code>
	 */
	public int getSourceIndex() {

		return this.sourceIndex;
	}

	/**
	 * Metodo para obtener la coordenada y inicial.
	 * 
	 * @return <code>int</code>
	 */
	public int getY1() {

		return y1;
	}

	/**
	 * Metodo para obtener la coordenada x final.
	 * 
	 * @return <code>int</code>
	 */
	public int getX2() {

		return x2;
	}

	/**
	 * Metodo para obtener la coordenada y final.
	 * 
	 * @return <code>int</code>
	 */
	public int getY2() {

		return y2;
	}

	/**
	 * Metodo para obtener la figura inicial.
	 * 
	 * @return <code>Sprite</code>
	 */
	public Sprite getSource() {

		return source;
	}

	/**
	 * Metodo para obtener la figura final.
	 * 
	 * @return <code>Sprite</code>
	 */
	public Sprite getDest() {

		return dest;
	}

	/**
	 * Realiza la conexi&oacuten entre dos conectores. Guarda las coordenadas
	 * iniciales y finales x, y con base a las coordenadas de los conectores.
	 */
	public void connect() {
		sourceCon = source.getSprite(getSourceIndex());
		setX1(sourceCon.getX() + sourceCon.getWidth() / 2);
		setY1(sourceCon.getY() + sourceCon.getHeight() / 2);

		if (dest != null) {
			destCon = dest.getSprite(getDestIndex());
			setX2(destCon.getX() + destCon.getWidth() / 2);
			setY2(destCon.getY() + destCon.getHeight() / 2);
		}
	}

	/**
	 * Dibuja una l&iacutenea con las coordenadas [(x1,y1), (x2,y2)] que
	 * representa la liga entre componentes.
	 * 
	 * @param g
	 *            <code>Graphics</code>
	 */
	public void paintLine(Graphics g) {

		connect();

		Line2D.Double line = new Line2D.Double(getX1(), getY1(), getX2(),
				getY2());
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.draw(line);
	}

	/**
	 * Obtiene la propiedad source Connector de tipo Sprite
	 */
	public Sprite getSourceCon() {
		return sourceCon;
	}

	/**
	 * Obtiene la propiedad destiny Connector de tipo Sprite
	 */
	public Sprite getDestCon() {
		return destCon;
	}
}
