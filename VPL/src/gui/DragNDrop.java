/**
 * Esta clase se encarga de hacer <code>DragNDrop</code> de <code>Sprite</code>.
 *
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import sprite.*;

public class DragNDrop implements DragGestureListener, DragSourceListener,
		DropTargetListener {

	private Sprite beingDragged;
	private Cursor dragCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	private DragSource dragSource;
	private DropTarget dropTarget;
	private Cursor normalCursor = DragSource.DefaultMoveDrop;
	private WorkArea wa;

	/**
	 * Constructor donde se especifican las figuras, las l&iacuteneas y el gui.
	 * 
	 * @param sprites
	 *            <code>ArrayList<Sprite></code>
	 * @param lines
	 *            <code>ArrayList<Sprite></code>
	 * @param gui
	 *            <code>GUI</code>
	 */
	public DragNDrop(WorkArea wa) {

		this.wa = wa;
		this.dragSource = DragSource.getDefaultDragSource();
		this.dragSource.createDefaultDragGestureRecognizer(this.wa,
				DnDConstants.ACTION_COPY_OR_MOVE, this);
		this.dropTarget = new DropTarget(this.wa, this);
	}

	/**
	 * M&eacutetodo que se dispara cuando se comienza a realizar el drag de la
	 * fuente.
	 * 
	 * @param e
	 *            <code>DragSourceDragEvent</code>
	 */
	public void dragEnter(DragSourceDragEvent e) {
	}

	/**
	 * M&eacutetodo que es invocado cuando el usuario draguea algo sobre el
	 * &aacuterea de trabajo.
	 * 
	 * @param e
	 *            <code>DragSourceDragEvent</code>
	 */
	public void dragEnter(DropTargetDragEvent e) {
	}

	/**
	 * M&eacutetodo que se dispara cuando se comienza a terminar el drag de la
	 * fuente.
	 * 
	 * @param e
	 *            <code>DragSourceEvent</code>
	 */
	public void dragExit(DragSourceEvent e) {
	}

	/**
	 * M&eacutetodo que se dispara cuando se termina de realizar el drag del
	 * destino.
	 * 
	 * @param e
	 *            <code>DragTargetEvent</code>
	 */
	public void dragExit(DropTargetEvent e) {
	}

	/**
	 * M&eacutetodo que se dispara cuando se comienza a realizar el drag sobre
	 * la fuente.
	 * 
	 * @param e
	 *            <code>DragSourceDragEvent</code>
	 */
	public void dragOver(DragSourceDragEvent e) {
	}

	/**
	 * M&eacutetodo que se dispara cuando se realizar el drag sobre el destino.
	 * 
	 * @param e
	 *            <code>DragTargetDragEvent</code>
	 */
	public void dragOver(DropTargetDragEvent e) {
	}

	/**
	 * Este M&eacutetodo implementa la interfaz del
	 * <code>DragGestureListener</code>. Sera invocada cuando el
	 * <code>DragGestureRecognizer</code> piense que el usuario ha iniciado un
	 * drag. Intenta reconocer que figura esta siendo tomada, e iniciara un drag
	 * sobre el objeto.
	 * 
	 * @param e
	 *            <code>DragGestureEvent</code>
	 */
	public void dragGestureRecognized(DragGestureEvent e) {

		if (this.wa.getConnect().isConnectFlag())
			return;

		MouseEvent inputEvent = (MouseEvent) e.getTriggerEvent();
		int x = inputEvent.getX();
		int y = inputEvent.getY();

		for (int i = 0; i < this.wa.getSpriteCount(); i++) {

			Sprite f = this.wa.getSpriteAt(i);

			Rectangle r1 = new Rectangle(x, y, f.getWidth(), f.getWidth());
			Sprite dragSprite = f;

			if (f.getNumSprite() == 0 && f.intersects(r1)) {

				Cursor cursor = getDragCursor(e.getDragAction());
				this.beingDragged = dragSprite;
				e.startDrag(cursor, dragSprite, this);

				return;
			} else {

				for (int j = 0; j < f.getNumSprite(); j++) {

					Sprite f2 = f.getSprite(j);
					Rectangle r2 = new Rectangle(x, y, f2.getWidth(), f2
							.getWidth());

					if (f.intersects(r1) && !f2.intersects(r2)) {

						Cursor cursor = getDragCursor(e.getDragAction());
						this.beingDragged = dragSprite;
						e.startDrag(cursor, dragSprite, this);

						return;
					}
				}
			}
		}
	}

	/**
	 * Este M&eacutetodo, y los cuatro que le siguen, implementan la interfaz
	 * del <code>DragSourceListener</code>. <code>dragDropEnd()</code> es
	 * invocado cuando el usuario suelta la figura que estaba tomando.
	 * 
	 * @param e
	 *            <code>DragSourceDragEvent</code>
	 */
	public void dragDropEnd(DragSourceDropEvent e) {
	}

	/**
	 * M&eacutetodo clave del <code>DropTargetListener</code>. Se invoca
	 * cuando el usuario suelta algo sobre el &aacuterea de trabajo.
	 * 
	 * @param e
	 *            <code>DragTargetDropEvent</code>
	 */
	public void drop(DropTargetDropEvent e) {

		Point p = e.getLocation();
		beingDragged.setX((int) p.getX() - beingDragged.getWidth() / 2);
		beingDragged.setY((int) p.getY() - beingDragged.getHeight() / 2);
		beingDragged = null;
		wa.repaint();
		e.dropComplete(true);

	}

	/**
	 * M&eacutetodo que se dispara cuando se comienza a realizar el drag de la
	 * fuente.
	 * 
	 * @param e
	 *            <code>DragSourceDragEvent</code>
	 */
	public void dropActionChanged(DragSourceDragEvent e) {
	}

	/**
	 * M&eacutetodo que se dispara cuando se realiza un cambio en la accion del
	 * drop
	 * 
	 * @param e
	 *            <code>DragTargetDragEvent</code>
	 */
	public void dropActionChanged(DropTargetDragEvent e) {
	}

	public Sprite getBeingDragged() {
		return beingDragged;
	}

	/**
	 * M&eacutetodo para cambiar el cursor de acuerdo a la acci&oacuten
	 * realizada.
	 * 
	 * @param action
	 *            <code>int</code>
	 * @return <code>Cursor</code>
	 */
	private Cursor getDragCursor(int action) {

		Cursor cursor;

		switch (action) {

		case DnDConstants.ACTION_COPY:
			cursor = DragSource.DefaultCopyDrop;
			break;
		case DnDConstants.ACTION_MOVE:
			cursor = DragSource.DefaultMoveDrop;
			break;
		default:
			cursor = DragSource.DefaultMoveDrop;
			break;
		}

		return cursor;
	}

	/**
	 * M&eacutetodo que obtiene el <code>DropTarget</code>.
	 * 
	 * @return <code>DropTarget</code>
	 */
	public DropTarget getDropTarget() {

		return this.dropTarget;
	}
}
