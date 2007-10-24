/**
 * Clase que genera el c&oacutedigo a partir del grafo.
 * 
 * @author Andr&eacute Freyr&iacutea Cede�o
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figuero Ram&iacuterez
 * @author Jos&eacute Roberto Ram&iacuterez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import sprite.Sprite;
import sprite.SpriteBegin;
import sprite.SpriteEnd;
import struct.StructV;

public class AddCode implements MouseListener, ActionListener {

	private ActivityBuilder activityBuilder;
	private ArrayList<Activity> activityList;
	private ActivityDirector director;
	private ArrayList<JFrame> frameList;
	private ArrayList<Sprite> spriteList;
	private ArrayList<StructV> structVList;
	private StructV stv;
	private WorkArea wa;
	
	/**
	 * Constructor. Se especifican el arreglo de figuras y la interfaz
	 * gr&aacutefica donde dibujar.
	 * 
	 * @param wa		<code>WorkArea</code>
	 */
	public AddCode(WorkArea wa) {
		
		this.activityBuilder = new ActivityBuilder(this);
		this.activityList = new ArrayList<Activity>();
		this.director = ActivityDirector.getInstance();
		this.frameList = new ArrayList<JFrame>();
		this.structVList = new ArrayList<StructV>();
		this.spriteList = new ArrayList<Sprite>();	
		this.wa = wa;
		this.wa.addMouseListener(this);		
	}

	/**
	 * Este M&eacutetodo espera por los eventos de aceptar y cancelar dentro del
	 * area de edici&oacuten de cada uno de los Sprite. Cuando se da click a
	 * "Aceptar", el contenido se a�ade al nodo que le corresponde dentro del
	 * grafo, en caso de dar click a cancelar, simplemente se cierra la ventana.
	 * 
	 * @param e
	 *            <code>DragGestureEvent</code>
	 */
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < frameList.size(); i++) {
			
			String acceptName = "accept" + i;
			String cancelName = "cancel"+ i;
			
			if(acceptName.equals(e.getActionCommand())){
				
				JFrame f = frameList.get(i);
				Activity a = activityList.get(i);
				JTextArea ta;
				JTextField tf;
				Hashtable <String, Object> contents = 
				(Hashtable<String, Object>) this.structVList.get(this.structVList.size()-1).getValue();
				
				for(int j = 0; j < a.getNumText(); j++) {
					
					if(a.getContentAt(j) instanceof JTextArea){
						
						ta = (JTextArea) a.getContentAt(j);
						String code = ta.getText();
						contents.put(a.getKeyAt(j), code);
					}
					else if(a.getContentAt(j) instanceof JTextField){
						
						tf = (JTextField) a.getContentAt(j);
						String code = tf.getText();
						contents.put(a.getKeyAt(j), code);
					}
					
					this.structVList.get(i).setValue(contents);
				}		
				
				this.spriteList.set(i, null);
				this.frameList.get(i).setVisible(false);		
			}
		
			if(cancelName.equals(e.getActionCommand())){
				
				this.spriteList.set(i, null);
				this.frameList.get(i).setVisible(false);			
			}
		}
	}
	
	/**
	 * Este M&eacutetodo escucha eventos del tipo mouseClicked. Cuando detecta
	 * un doble click, genera la interfaz necesaria para que el usuario edite el
	 * contenido del Sprite. Asimismo, se limita el n&uacutemero de instancias
	 * de interfaz que pueden estar abiertas a la vez.
	 * 
	 * @param e
	 *            <code>MouseEvent</code>
	 */
	public void mouseClicked(MouseEvent e) {
		
		JFrame f;
		Sprite s;
		Activity a;
		int x = e.getX();
		int y = e.getY();

		if(e.getClickCount() == 2) {

			for(int i = 0; i < this.wa.getSpriteCount(); i++) {
				
				s = this.wa.getSpriteAt(i);

				Rectangle r = new Rectangle(x, y, s.getHeight(), s.getWidth());

				if(s.intersects(r)) {
					
					if(s.getClass()==SpriteBegin.class || s.getClass()==SpriteEnd.class) 
						return;
					
					for(int k = 0; k < spriteList.size(); k++) {
						
						Sprite tempSprite = spriteList.get(k);
						
						if(tempSprite != null)
							if (tempSprite.equals(s))
								return;
					}

					this.spriteList.add(s);

					for(int z = 0; z < this.wa.getGraph().getNumVertices(); z++) {
						
						stv = (StructV)this.wa.getGraph().getVertexAt(z).getValue();
						
						if (this.stv.getSprite().equals(s))
							structVList.add(stv);
					}
					
					String stringClass = s.getClass().toString();
					String spriteName = stringClass.substring(19).toLowerCase();
					
					this.director.construct(this.activityBuilder, spriteName, this.structVList.get(structVList.size()-1));
					
					f = this.activityBuilder.getActivityFrame();
					a = this.activityBuilder.getActivity();
					f.setVisible(true);
					
					this.activityList.add(a);
					this.frameList.add(f);
				}
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
