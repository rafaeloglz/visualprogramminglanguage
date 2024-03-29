/**
 * Connect crea la liga entre dos componentes usando a Connector.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import sprite.Sprite;
import sprite.SpriteFor;
import sprite.SpriteWhile;
import sprite.Square;
import struct.StructE;
import struct.StructV;

public class Connect implements MouseListener, MouseMotionListener {

	private boolean connectFlag;
	private Sprite dest;
	private int destIndex;
	private Cursor dragCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
	private Line draggedLine;
	private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	private Sprite source;
	private Sprite sourceConnector;
	private int sourceIndex;
	private WorkArea wa;

	/**
	 * Constructor. Se especifican el arreglo de figuras y el de conectores a
	 * dibujar y la interfaz gr&aacute;fica donde dibujar.
	 * 
	 * @param wa
	 *            <code>WorkArea</code>
	 */
	public Connect(WorkArea wa) {

		this.connectFlag = false;
		this.draggedLine = null;
		this.sourceConnector = null;
		this.wa = wa;
		this.wa.addMouseListener(this);
		this.wa.addMouseMotionListener(this);
	}

	/**
	 * M&eacute;todo para determinar si actualmente se esta en el proceso de
	 * conectar dos elementos.
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isConnectFlag() {

		return connectFlag;
	}

	/**
	 * M&eacute;todo que identifica el conector sobre el que se hace click para
	 * guardar la referencia y crear la liga con el conector donde se suelte el
	 * bot&oacute; del mouse.
	 * 
	 * @param e
	 *            <code>MouseEvent</code>
	 */
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * M&eacute;todo que identifica cuando se ha hecho un drag sobre un
	 * componente
	 * 
	 * @param e
	 *            <code>MouseEvent</code>
	 */
	public void mouseDragged(MouseEvent e) {
		
		if (this.draggedLine != null) {

			this.draggedLine.setX2(e.getX());
			this.draggedLine.setY2(e.getY());
			this.wa.repaint();
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	/**
	 * M&eacute;todo que identifica cuando se ha presionado el boton izquierdo
	 * del mouse
	 * 
	 * @param e
	 *            <code>MouseEvent</code>
	 */
	public void mousePressed(MouseEvent e) {

		if (this.wa.getDragNDrop().getBeingDragged() != null)
			return;

		int x = e.getX();
		int y = e.getY();
		Sprite s1;
		Sprite s2 = null;

		for (int i = 0; i < this.wa.getSpriteCount(); i++) {

			s1 = wa.getSpriteAt(i);

			Rectangle r = new Rectangle(x, y, s1.getHeight(), s1.getWidth());

			if (s1.intersects(r))
				for (int j = 0; j < s1.getNumSprite(); j++) {

					s2 = s1.getSprite(j);

					r = new Rectangle(x, y, s2.getHeight(), s2.getWidth());

					if (s2.intersects(r)) {

						this.sourceIndex = j;
						this.source = s1;
						this.sourceConnector = s2;

						if (sourceIndex > 1 && sourceConnector instanceof Square /*|| hasBeenConnected(sourceConnector)*/)												
							return;

						this.connectFlag = true;
						this.draggedLine = new Line(source, sourceIndex);
						this.wa.addLine(this.draggedLine);
						this.wa.setCursor(dragCursor);
						
						return;
					}
				}
		}
	}

	/**
	 * M&eacute;todo que identifica cuando se ha soltado el boton izquierdo del
	 * mouse despues de haberlo presionado.
	 * 
	 * @param e
	 *            <code>MouseEvent</code>
	 */

	public void mouseReleased(MouseEvent e) {

		if (this.connectFlag) {

			int x = e.getX();
			int y = e.getY();
			Sprite s1;
			Sprite s2 = null;

			for (int i = 0; i < this.wa.getSpriteCount(); i++) {

				s1 = wa.getSpriteAt(i);

				Rectangle r = new Rectangle(x, y, s1.getHeight(), s1.getWidth());

				if (s1.intersects(r))

					for (int j = 0; j < s1.getNumSprite(); j++) {

						s2 = s1.getSprite(j);

						r = new Rectangle(x, y, s2.getHeight(), s2.getWidth());

						if (s2.intersects(r)) {

							this.destIndex = j;
							this.dest = s1;

							if (this.sourceConnector.getClass() == s2.getClass()
									&& !source.equals(dest)) {
								if (destIndex <= 1 && s2 instanceof Square
										/*|| hasBeenConnected(s2)*/
										|| generatesLoop(source, dest))
									break;

								this.draggedLine.setDest(this.dest);
								this.draggedLine.setDestIndex(this.destIndex);

								StructE st = new StructE(this.draggedLine, 0);
								StructV stS = new StructV(this.source, 0);
								StructV stD = new StructV(this.dest, 0);
								this.wa.getGraph().addEdge(stS, stD, st, this.sourceIndex);
								this.wa.repaint();

								this.connectFlag = false;
								this.wa.setCursor(normalCursor);
								
								return;
							}
						}
					}
			}

			this.connectFlag = false;

			this.wa.removeLine(this.wa.getLineCount() - 1);
			this.wa.setCursor(normalCursor);
			this.wa.repaint();

			return;
		}

	}

	public void mouseMoved(MouseEvent arg0) {
	}

	/**
	 * M&eacute;todo que determina cuando un Sprite ha sido conectado
	 * 
	 * @param sprite
	 *            <code>Sprite</code>
	 */
	public boolean hasBeenConnected(Sprite sprite) {			
		
		for (int i = 0; i < wa.getLineCount(); i++) {
			Sprite tempSource = wa.getLineAt(i).getSourceCon();
			Sprite tempDest = wa.getLineAt(i).getDestCon();

			if (tempDest == null)
				return false;

			if (tempSource.equals(sprite) || tempDest.equals(sprite))
				return true;
		}

		return false;
	}

	/**
	 * M&eacute;todo que identifica si se ha generado un clico entre dos Sprites
	 * 
	 * @param source
	 *            <code>Sprite</code>
	 * @param dest
	 *            <code>Sprite</code>
	 */
	public boolean generatesLoop(Sprite source, Sprite dest) {

		if ((source instanceof SpriteWhile || source instanceof SpriteFor)
				|| (dest instanceof SpriteWhile || dest instanceof SpriteFor))
			return false;

		for (int i = 0; i < wa.getLineCount(); i++) {
			Sprite tempSource = wa.getLineAt(i).getSource();
			Sprite tempDest = wa.getLineAt(i).getDest();
			if (tempSource.equals(dest) && tempDest.equals(source))
				return true;
		}

		return false;
	}
}
