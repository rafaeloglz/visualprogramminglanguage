/**
 * Connect crea la liga entre dos componentes usando a Connector.
 * 
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figueroa Ramirez 
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import sprite.Sprite;
import struct.StructE;
import struct.StructV;

public class Connect implements MouseListener, MouseMotionListener {

	private boolean connectFlag;
	private Sprite dest;
	private int destIndex;
	private Cursor dragCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
	public Line draggedLine;
	private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	private Sprite source;
	private Sprite sourceConnector;
	private int sourceIndex;
	private WorkArea wa;

	/**
	 * Constructor. Se especifican el arreglo de figuras y el de conectores a dibujar
	 * y la interfaz gr&aacutefica donde dibujar.
	 *
	 * @param gui					<code>GUI</code>
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
	 * M&eacutetodo para determinar si actualmente se esta en el proceso
	 * de conectar dos elementos.
	 * 
	 * @return				<code>boolean</code>
	 */
	public boolean isConnectFlag() {
		
		return connectFlag;
	}
	
	/**
	 * Identifica el conector sobre el que se hace click para guardar la referencia y
	 * crear la liga con el conector donde se suelte el bot&oacute del mouse.
	 * 
	 * @param e		<code>MouseEvent</code>
	 */
	public void mouseClicked(MouseEvent e) {	
	}
	
	public void mouseDragged(MouseEvent e) {
		
		if(this.draggedLine != null) {
			
			this.draggedLine.setX2(e.getX());
			this.draggedLine.setY2(e.getY());
			this.wa.repaint();			
		}			
	}
	
	public void mouseEntered(MouseEvent e) {			
	}
	
	public void mouseExited(MouseEvent e) {				
	}

	public void mousePressed(MouseEvent e) {
		
		if(this.wa.getDragNDrop().getBeingDragged() != null) 
			return;

		int x = e.getX();
		int y = e.getY();
		Sprite s1;
		Sprite s2 = null;

		for (int i = 0; i < this.wa.getSpriteCount(); i++) {

			s1 = wa.getSpriteAt(i);

			Rectangle r = new Rectangle(x, y, s1.getHeight(), s1.getWidth());

			if (s1.intersects(r))
				for(int j = 0; j < s1.getNumSprite(); j++){
					
					s2 = s1.getSprite(j);

					r = new Rectangle(x, y, s2.getHeight(), s2.getWidth());

					if(s2.intersects(r)){
						
						this.sourceIndex = j;
						this.source = s1;
						this.sourceConnector = s2;
						this.connectFlag = true;
						
						this.draggedLine = new Line(source, sourceIndex);
						this.wa.addLine(this.draggedLine);
						
						this.wa.setCursor (dragCursor);
						return;
					}
				}
		}
	}

	public void mouseReleased(MouseEvent e){
		
		if(this.connectFlag){
			
			int x = e.getX();
			int y = e.getY();
			Sprite s1;
			Sprite s2 = null;

			for (int i = 0; i < this.wa.getSpriteCount(); i++) {

				s1 = wa.getSpriteAt(i);

				Rectangle r = new Rectangle(x, y, s1.getHeight(), s1.getWidth());

				if (s1.intersects(r))
					
					for(int j = 0; j < s1.getNumSprite(); j++) {
						
						s2 = s1.getSprite(j);

						r = new Rectangle(x, y, s2.getHeight(), s2.getWidth());

						if(s2.intersects(r)) {
							
							this.destIndex = j;
							this.dest = s1;							
																											
							if(this.sourceConnector.getClass() == s2.getClass() && 
							!source.equals(dest)) {
																	
								this.draggedLine.setDest(dest);
								this.draggedLine.setDestIndex(destIndex);
								
								StructE st = new StructE(draggedLine, 0);
								StructV stS = new StructV(source, 0);
								StructV stD = new StructV(dest, 0);
								System.out.println(sourceIndex);
								this.wa.getGraph().addEdge(stS, stD, st, sourceIndex);
								this.wa.repaint();
								
								this.connectFlag = false;
								this.wa.setCursor (normalCursor);
								
								return;
							}
						}
					}								
			}
		
			this.connectFlag = false;
			
			this.wa.removeLine(this.wa.getLineCount() - 1);
			this.wa.setCursor (normalCursor);
			this.wa.repaint();
			
			return;	
		}		
		
	}
	public void mouseMoved(MouseEvent arg0) {
				
	}
}
