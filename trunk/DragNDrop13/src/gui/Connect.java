/**
 * Connect crea la liga entre dos componentes usando a Connector.
 * 
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figueroa Ramirez 
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import sprite.Sprite;
import struct.StructE;
import struct.StructV;

public class Connect implements MouseListener, MouseMotionListener {

	private ArrayList<Sprite> spriteList;
	private ArrayList<Line> lineArray;
	private WorkArea wa;
	private boolean connectFlag;
	private int sourceIndex;
	private int destIndex;
	private Sprite source;
	private Sprite dest;
	private Sprite sourceConnector;
	private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	private Cursor dragCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
	private Cursor overCursor = new Cursor(Cursor.HAND_CURSOR);
	public Line draggedLine;
	/**
	 * Constructor. Se especifican el arreglo de figuras y el de conectores a dibujar
	 * y la interfaz gr&aacutefica donde dibujar.
	 *
	 * @param spriteList			<code>ArrayList</code>
	 * @param lineArray				<code>ArrayList</code>
	 * @param gui					<code>GUI</code>
	 */		
	public Connect (ArrayList<Sprite> spriteList, ArrayList<Line> lineArray, WorkArea wa){		

		connectFlag = false;
		this.spriteList = spriteList;
		this.lineArray = lineArray;		
		this.wa = wa;
		wa.addMouseListener(this);
		wa.addMouseMotionListener(this);
		sourceConnector = null;
		draggedLine = null;
		
	}	

	/**
	 * Identifica el conector sobre el que se hace click para guardar la referencia y
	 * crear la liga con el conector donde se suelte el bot&oacute del mouse.
	 * 
	 * @param e		<code>MouseEvent</code>
	 */
	public void mouseClicked(MouseEvent e){	
	}

	public void mouseEntered(MouseEvent e){			
	}

	public void mouseDragged(MouseEvent e){
			
		if(this.draggedLine!=null){
			this.draggedLine.setX2(e.getX());
			this.draggedLine.setY2(e.getY());
			wa.repaint();			
		}			
	}
	
	public void mouseExited(MouseEvent e){				
	}

	public void mousePressed(MouseEvent e) {
		
		if(wa.getDragNDrop().getBeingDragged() != null) return;
		int figureNum = spriteList.size();

		int x = e.getX();
		int y = e.getY();
		Sprite s1;
		Sprite s2 = null;

		for (int i = 0; i < figureNum; i++) {

			s1 = spriteList.get(i);

			Rectangle r = new Rectangle(x, y, s1.getHeight(), s1.getWidth());

			if (s1.intersects(r)){
				
				for(int j = 0; j < s1.getNumSprite(); j++){
					s2 = s1.getSprite(j);

					r = new Rectangle(x, y, s2.getHeight(), s2.getWidth());

					if(s2.intersects(r)){
						sourceIndex = j;
						source = s1;
						sourceConnector = s2;
						connectFlag = true;
						
						draggedLine = new Line(source, sourceIndex);
						lineArray.add(draggedLine);
												
						wa.setCursor (dragCursor);
						return;
					}
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e){
		if(connectFlag){
			int figureNum = spriteList.size();
			int x = e.getX();
			int y = e.getY();
			Sprite s1;
			Sprite s2 = null;
			

			for (int i = 0; i < figureNum; i++) {

				s1 = spriteList.get(i);

				Rectangle r = new Rectangle(x, y, s1.getHeight(), s1.getWidth());

				if (s1.intersects(r)){
					
					for(int j = 0; j < s1.getNumSprite(); j++){
						s2 = s1.getSprite(j);

						r = new Rectangle(x, y, s2.getHeight(), s2.getWidth());

						if(s2.intersects(r)){
							
							destIndex = j;
							dest = s1;							
																											
							if(sourceConnector.getClass() == s2.getClass() && !source.equals(dest)){
																	
								draggedLine.setDest(dest);
								draggedLine.setDestIndex(destIndex);
								StructE st = new StructE(draggedLine, 0);
								StructV stS = new StructV(source, 0);
								StructV stD = new StructV(dest, 0);
								wa.getGraph().addEdge(stS, stD, st);
								wa.repaint();
								connectFlag = false;
								wa.setCursor (normalCursor);
								return;
							}
						}
					}				
				}				
			}
			lineArray.remove(lineArray.size()-1);
			connectFlag = false;
			wa.setCursor (normalCursor);
			wa.repaint();
			return;	
		}		
		
	}
	public void mouseMoved(MouseEvent arg0) {
				
	}

	public boolean isConnectFlag() {
		return connectFlag;
	}
	
}
