
package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import graph.*;
import sprite.*;
import struct.*;

public class WorkArea extends JComponent {
	
	private ArrayList<Sprite> sprites;
	private ArrayList<Line> lines;
	private DragNDrop dragNDrop;	
	private Connect connect;
	private AddCode addCode;
	private Graph g;
	private String name;
	private ToolBar toolbar;
	private boolean clicked = false;
	private Sprite buttonClicked;
	
	private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor dragCursor = DragSource.DefaultMoveDrop;

	
	/**
	 * Constructor por omisi&oacuten.
	 */		
	public WorkArea(String name, ToolBar toolbar) {		
		this.toolbar = toolbar;
		this.sprites = new ArrayList();
		this.lines = new ArrayList();
		this.dragNDrop = new DragNDrop(this);
		this.connect = new Connect(sprites, lines, this);
		this.addCode = new AddCode(sprites, this);		
		this.name = name;
		g = new Graph();
		addListeners();
	}
	
	/**
	 * M&eacutetodo para obtener el n&uacutemero de <code>Sprite</code>.
	 *
	 * @return		<code>int</code>
	 */
	public int getNumSprites() {
		
		return this.sprites.size();
	}
	
	/**
	 * M&eacutetodo para obtener el n&uacutemero de <code>Line</code>.
	 *
	 * @return		<code>int</code>
	 */
	public int getNumLines() {
		
		return this.lines.size();
	}
	
	/**
	 * M&eacutetodo para obtener el grafo.
	 *
	 * @return		<code>Graph</code>
	 */
	public Graph getGraph() {
	
		return this.g;
	}
	
	public Connect getConnect() {
		
		return connect;
	}
	
	public DragNDrop getDragNDrop() {
		
		return dragNDrop;
	}
	
	/**
	 * M&eacutetodo para obtener el nombre.
	 *
	 * @return		<code>String</code>
	 */
	public String getName() {
	
		return this.name;
	}
	
	/**
	 * M&eacutetodo para obtener el grafo.
	 *
	 * @return		<code>Graph</code>
	 */
	public void setGraph(Graph g) {
	
		this.g = g;
		
		for(int i = 0; i < g.getNumVertices(); i++) {
				
			StructV stv = (StructV) g.getVertexAt(i).getValue();
			sprites.add(stv.getSprite());
		}
		
		for(int i = 0; i < g.getNumEdges(); i++) {
				
			StructE ste = (StructE) g.getEdgeAt(i).getWeight();
			lines.add(ste.getLine());
		}	
	}
	
	/**
	 * M&eacutetodo para agregar un <code>Sprite</code>.
	 * 
	 * @param sprite		<code>Sprite</code>
	 */
	public void addSprite(Sprite sprite) {
		
		sprites.add(sprite);
	}
	
	/**
	 * M&eacutetodo para agregar un <code>Line</code>.
	 * 
	 * @param line			<code>Line</code>
	 */
	public void addLine(Line line) {
		
		lines.add(line);
	}
	
	/**
	 * M&eacutetodo para para que el componente se dibuje a si mismo 
	 * dibujando cada uno de los objetos Sprite y Line.
	 * 
	 * @param g		<code>Graphics</code>
	 */
	public void paintComponent(Graphics g) {	    
		
		int spriteNum = sprites.size ();
		int lineNum = lines.size();
	    
		for(int i = 0; i < spriteNum; i++) {
			
			Sprite f = sprites.get(i);
	      	f.paintSprite(g);
	    }
		
		for(int i = 0; i < lineNum; i++) {
			
			Line l = lines.get(i);
		    l.paintLine(g);
		}
	}
	
	/**
	 * M&eacutetodo para borrar las estructuras de <code>Sprite</code>
	 * <code>Line</code> y <code>Graph</code>.
	 */
	public void flush() {
		
		sprites.clear ();
		lines.clear ();
		g.clear();
		//toolbar.reset();
	}

	/**
	 * M&eacutetodo para agregar los <code>Listeners</code> correspondientes a cada componente
	 * del toolbar.
	 * 
	 * @param button		<code>JButton</code>
	 * @param sprite		<code>Sprite</code>
	 */
	public void addListeners(){		
		final ToolBar toolbar = getToolbar();
		final WorkArea wa = this;
		
		for(int i=0;i<toolbar.getLabels().size();i++){
					
			final JLabel label = toolbar.getLabels().get(i);
			final Sprite sprite = toolbar.getTools().get(i);

			label.addMouseListener(new MouseAdapter() {

	        	public void mousePressed(MouseEvent m){	        		
	        		
	        		try {	        			
		        		wa.setCursor(dragCursor);
		        		toolbar.getToolBar().setCursor(dragCursor);		        				        		
		        		
	        			if(sprite.getClass()==SpriteBegin.class && wa.getGraph().getHead()!=null){	        				
	        				wa.setClicked (sprite);
	        			}
	        			else{
	        				wa.setClicked (sprite.clone());
	        			}
	        			
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}

					wa.setClicked (true);          	  	
	        	}

	        	public void mouseReleased(MouseEvent m) {

	        		if(wa.isClicked ()) {

	        			wa.setClicked (false);
	        			Sprite temp = wa.getClicked ();

        				if(wa.getMousePosition() != null) {

	        				temp.setX((int) wa.getMousePosition().getX() - temp.getWidth() / 2);
		        			temp.setY((int) wa.getMousePosition().getY() - temp.getHeight() / 2);			        			
		        			
		        			String stringClass = temp.getClass().toString();
							String spriteName = stringClass.substring(19).toLowerCase();
							
							Hashtable<String, Object> tempHashTable = new Hashtable<String, Object>();
							tempHashTable.put("name", spriteName);
		        			
							StructV<Hashtable<String, Object>> st = new StructV<Hashtable<String, Object>>(temp, tempHashTable);
		        			
		        			if(wa.getGraph().getHead()==null || sprite.getClass()!=SpriteBegin.class) {		        				
		        				wa.addSprite(temp);
		        				wa.getGraph().addVertex(st);		        				
		        			}

		        			if(sprite.getClass()==SpriteBegin.class && wa.getGraph().getHead()==null)
	                    		wa.getGraph().setHead(st);
	        			}
	        			
	        			wa.repaint();

	        			wa.setCursor(normalCursor);
	        			toolbar.getToolBar().setCursor(normalCursor);	        			
	        			//getMenuBar().getMenuBar().repaint();
	        		}
	        	}
	        });		
		}
	}

	/**
	 * M&eacutetodo para especificar que se ha hecho click sobre un componente
	 * 
	 * @param clicked		<code>true</code>
	 */
	public void setClicked (boolean clicked){		
		
		this.clicked = clicked;
	}
	
	/**
	 * M&eacutetodo para especificar el componente sobre el cual se hizo click.
	 * 
	 * @param s		el <code>Sprite</code>	
	 */
	public void setClicked (Sprite s){
				
		buttonClicked = (Sprite) s;
	}	
	
	/**
	 * M&eacutetodo para obtener el componente en el cual se hizo click.
	 *
	 * @return		<code>Sprite</code>
	 */
	public Sprite getClicked (){
				
		return buttonClicked;
	}
	
	/**
	 * Indica si ha hecho click sobre alg&uacuten componente del toolbar.
	 *
	 * @return		<code>boolean</code>
	 */ 
	public boolean isClicked (){
				
		return clicked;
	}

	
	public ToolBar getToolbar() {
		return toolbar;
	}

	public void setToolbar(ToolBar toolbar) {
		this.toolbar = toolbar;
	}

	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public void setSprites(ArrayList<Sprite> sprites) {
		this.sprites = sprites;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}	
}