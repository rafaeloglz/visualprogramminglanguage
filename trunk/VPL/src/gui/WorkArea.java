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
	
	private AddCode addCode;
	private Connect connect;
	private Cursor dragCursor = DragSource.DefaultMoveDrop;
	private DragNDrop dragNDrop;
	private Graph graph;
	private ArrayList<Line> lines;
	private String name;
	private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	private ToolBar toolbar;
	private ArrayList<Sprite> sprites;

	/**
	 * Constructor por omisi&oacuten.
	 */		
	public WorkArea(String name, ToolBar toolbar) {		
		
		this.addCode = new AddCode(this);
		this.connect = new Connect(this);
		this.dragNDrop = new DragNDrop(this);
		this.graph = new Graph();
		this.lines = new ArrayList<Line>();
		this.name = name;
		this.toolbar = toolbar;
		this.sprites = new ArrayList<Sprite>();
	}
	
	/**
	 * M&eacutetodo para agregar un <code>Line</code>.
	 * 
	 * @param line			<code>Line</code>
	 */
	public void addLine(Line line) {
		
		this.lines.add(line);
	}
	
	/**
	 * M&eacutetodo para agregar los <code>Listeners</code> correspondientes a cada componente
	 * del toolbar.
	 * 
	 * @param button		<code>JButton</code>
	 * @param sprite		<code>Sprite</code>
	 */

	/*private void addListeners() {		
		
		final ToolBar toolbar = this.toolbar;
		final WorkArea wa = this;
		
		for(int i = 0; i < toolbar.getLabels().size(); i++) {
					
			final JLabel label = toolbar.getLabels().get(i);
			final Sprite sprite = toolbar.getTools().get(i);

			label.addMouseListener(new MouseAdapter() {

	        	public void mousePressed(MouseEvent m) {	        		
	        		
	        		try {	        			
		        		wa.setCursor(dragCursor);
		        		toolbar.getJToolBar().setCursor(dragCursor);		        				        		
		        		
	        			//if(sprite.getClass()==SpriteBegin.class && wa.getGraph().getHead()!=null)	        				
	        				//wa.setClickedTool (sprite);
	        			//else
	        				wa.setClickedTool (sprite.clone());
	        			
					}catch(CloneNotSupportedException e) {
						
						e.printStackTrace();
					}

					wa.setClicked(true);          	  	
	        	}

	        	public void mouseReleased(MouseEvent m) {

	        		if(wa.isToolClicked()) {

	        			wa.setClicked (false);
	        			Sprite temp = wa.getClickedTool();

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
	        			toolbar.getJToolBar().setCursor(normalCursor);	        			
	        		}
	        	}
	        });		
		}
	}*/
	
	/**
	 * M&eacutetodo para agregar un <code>Sprite</code>.
	 * 
	 * @param sprite		<code>Sprite</code>
	 */
	public void addSprite(Sprite sprite) {
		
		this.sprites.add(sprite);
	}
	
	/**
	 * M&eacutetodo para borrar las estructuras de <code>Sprite</code>
	 * <code>Line</code> y <code>Graph</code>.
	 */
	public void clear() {
		
		this.sprites.clear ();
		this.lines.clear ();
		this.graph.clear();
	}
	
	/**
	 * M&eacutetodo para obtener el objeto encargado de realizar las
	 * uniones entre componentes.
	 * 
	 * @return			<code>Connect</code>
	 */
	public Connect getConnect() {
		
		return this.connect;
	}
	
	/**
	 * M&eacutetodo para obtener el objeto encargado de realizar las
	 * uniones entre componentes.
	 * 
	 * @return			<code>DragNDrop</code>
	 */
	public DragNDrop getDragNDrop() {
		
		return this.dragNDrop;
	}
	
	/**
	 * M&eacutetodo para obtener el grafo.
	 *
	 * @return		<code>Graph</code>
	 */
	public Graph getGraph() {
	
		return this.graph;
	}
	
	/**
	 * M&eacutetodo para obtener un <code>Line</code> por &iacutendice.
	 * 
	 * @param index			<code>int</code>
	 * @return				<code>Line</code>
	 */
	public Line getLineAt(int index) {
		
		return this.lines.get(index);
	}
	
	/**
	 * M&eacutetodo para obtener el n&uacutemero de <code>Line</code>.
	 *
	 * @return		<code>int</code>
	 */
	public int getLineCount() {
		
		return this.lines.size();
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
	 * M&eacutetodo para obtener un <code>Sprite</code> por &iacutendice.
	 * 
	 * @param index			<code>int</code>
	 * @return				<code>Sprite</code>
	 */
	public Sprite getSpriteAt(int index) {
		
		return this.sprites.get(index);
	}
	
	/**
	 * M&eacutetodo para obtener el n&uacutemero de <code>Sprite</code>.
	 *
	 * @return		<code>int</code>
	 */
	public int getSpriteCount() {
		
		return this.sprites.size();
	}
	
	/**
	 * M&eacutetodo para para que el componente se dibuje a si mismo 
	 * dibujando cada uno de los objetos Sprite y Line.
	 * 
	 * @param g		<code>Graphics</code>
	 */
	public void paintComponent(Graphics g) {	    
		
		for(int i = 0; i < this.sprites.size(); i++) {
			
			Sprite f = this.sprites.get(i);
	      	f.paintSprite(g);
	    }
		
		for(int i = 0; i < this.lines.size(); i++) {
			
			Line l = this.lines.get(i);
		    l.paintLine(g);
		}
	}
	
	public Line removeLine(int index) {
		
		return this.lines.remove(index);
	}
	
	public Sprite removeSprite(int index) {
		
		return this.sprites.remove(index);
	}
	
	/**
	 * M&eacutetodo para obtener el grafo.
	 *
	 * @return		<code>Graph</code>
	 */
	public void setGraph(Graph g) {
	
		this.graph = g;
		
		for(int i = 0; i < g.getNumVertices(); i++) {
				
			StructV stv = (StructV) g.getVertexAt(i).getValue();
			this.sprites.add(stv.getSprite());
		}
		
		for(int i = 0; i < g.getNumEdges(); i++) {
				
			StructE ste = (StructE) g.getEdgeAt(i).getWeight();
			this.lines.add(ste.getLine());
		}	
	}
}