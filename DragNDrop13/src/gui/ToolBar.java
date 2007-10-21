/**
 * componentes a utilizar en el &aacuterea de trabajo.
 * 
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figueroa Ramirez 
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.Cursor;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import java.awt.*;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;

import sprite.*;
import struct.StructV;

public class ToolBar{
	
	private boolean clicked;
	private Cursor dragCursor = DragSource.DefaultMoveDrop;
	private GUI gui;
	private ArrayList<JLabel> labels;
	private String name;
	private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	private int orientation;
	private JToolBar toolbar;
	private Sprite toolClicked;
	private ArrayList<Sprite> tools;
	
    /** Nombre por omisi&oacuten **/
	public static final String DEFAULT_NAME = "Herramientas";
	
	/** Orientaci&oacuten por omisi&oacuten **/
	public static final int DEFAULT_ORIENTATION = JToolBar.HORIZONTAL;

	/**
	 * Constructor por omisi&oacuten.
	 */
	public ToolBar (GUI gui){
		
		this (DEFAULT_NAME, DEFAULT_ORIENTATION, gui);
	}	
	
	/**
	 * Constructor donde se especifica el nombre y el orientaci&oacuten.
	 *
	 * @param name				el nombre del ToolBar
	 * @param orientation		la orientaci&oacuten del toolbar		
	 */
	public ToolBar (String name, int orientation, GUI gui){
		
		this.name = name;
		this.orientation = orientation;
		this.gui = gui;
		this.labels = new ArrayList<JLabel>();
		this.tools = new ArrayList<Sprite>();
		
		this.toolbar = new JToolBar (name, orientation);
		this.toolbar.setFloatable(false);
		
		buildToolBar ();
	}
	
	/**
	 * M&eacutetodo para agregar los <code>Listeners</code> correspondientes a 
	 * cada componente en el toolbar.
	 * 
	 * @param button		<code>JButton</code>
	 * @param sprite		<code>Sprite</code>
	 */
	private void addListener(JLabel label, final Sprite sprite) {
		
		label.addMouseListener(new MouseAdapter() {        
        	
        	public void mousePressed(MouseEvent m){
        		
        		gui.getJFrame().setCursor(dragCursor);

        		try {
        			
        			//if(sprite.getClass()==SpriteBegin.class /*&& gui.getGraph().getHead()!=null*/){
        				//setClicked (sprite);
        			//}
        			//else{
    					setClickedTool (sprite.clone());
        			//}
        			
				} catch(CloneNotSupportedException e) {
					e.printStackTrace();
				}

          	    setClicked(true);          	  	
        	}

        	public void mouseReleased(MouseEvent m) {

        		if(isToolClicked()) {

        			setClicked (false);

        			Sprite temp = getClickedTool();
        			
        			for(int i = 0; i < gui.getWorkAreaCount(); i++) {
        				
        				WorkArea wa = gui.getWorkAreaAt(i);
        				
        				if(wa.getMousePosition() != null) {
        					
	        				temp.setX((int) wa.getMousePosition().getX() - temp.getWidth() / 2);
		        			temp.setY((int) wa.getMousePosition().getY() - temp.getHeight() / 2);
	
		        			String stringClass = temp.getClass().toString();
							String spriteName = stringClass.substring(19).toLowerCase();
							
							Hashtable<String, Object> tempHashTable = new Hashtable<String, Object>();
							tempHashTable.put("name", spriteName);
		        			
							StructV<Hashtable<String, Object>> st = new StructV<Hashtable<String, Object>>(temp, tempHashTable);
		        			
		        			if(wa.getGraph().getHead()==null || sprite.getClass()!= SpriteBegin.class) {
		        				
		        				wa.addSprite(temp);
		        				wa.getGraph().addVertex(st);
		        			}
	
		        			if(sprite.getClass()==SpriteBegin.class && wa.getGraph().getHead()==null)
	                    		wa.getGraph().setHead(st);
	        			}
	        			
	        			wa.repaint();
        			}
        		}
        		
        		gui.getJFrame().setCursor(normalCursor);
    			getJToolBar().repaint();
        	}
        });		
	}

	
	/**
	 * M&eacutetodo para agregar componentes a la barra de herramientas.
	 * 
	 * @param sprite
	 */
	private void addTool(Sprite s) {
		
		final Sprite tool = s;
		
		Icon icon = new Icon() {
			
			public void paintIcon(Component c, Graphics g, int x, int y) {
				
				tool.setX(x);
				tool.setY(y);
				tool.paintSprite(g);
			}
			
			public int getIconHeight() {
				
				return tool.getHeight();
			}
			
			public int getIconWidth() {
    	
    			return tool.getWidth();
  			}
		};

        JLabel label = new JLabel(icon);
        labels.add(label);
        this.addListener(label, tool);
        tools.add(tool);
        toolbar.add(label);     
        toolbar.addSeparator();
}
	
	/**
	 * Crea los componentes a utilizar en el toolbar junto con sus iconos. Los agrega
	 * al toolbar y les agrega su <code>Listener</code>.
	 */
	private void buildToolBar (){
		
		toolbar.addSeparator();
				
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();		
		
		sprites.add(new SpriteBegin());
		sprites.add(new SpriteVar());
		sprites.add(new SpriteEnd());
		sprites.add(new SpriteInstruction());
		sprites.add(new SpriteIf());
		sprites.add(new SpriteFor());
		sprites.add(new SpriteWhile());		
		sprites.add(new SpriteFunction());
		sprites.add(new SpriteSemaphore());
		sprites.add(new SpriteLock());
		sprites.add(new SpriteParallel());
		sprites.add(new SpriteUnion());
		sprites.add(new SpriteSync());		
		
		for(int i = 0; i < sprites.size(); i++){ 
			addTool(sprites.get(i));
		}	
		
        toolbar.setSize (50, 50);        
	}
	
	public ArrayList<JLabel> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList<JLabel> labels) {
		this.labels = labels;
	}

	public ArrayList<Sprite> getTools() {
		return tools;
	}

	public void setTools(ArrayList<Sprite> tools) {
		this.tools = tools;
	}
	
	/**
	 * M&eacutetodo para obtener el componente en el cual se hizo click.
	 *
	 * @return		<code>Sprite</code>
	 */
	public Sprite getClickedTool() {
				
		return this.toolClicked;
	}
		
	/**
	 * M&eacutetodo para obtener el <code>JToolBar</code>.
	 *
	 * @return		el <code>JToolBar</code>
	 */
	public JToolBar getJToolBar (){		
		
		return toolbar;
	}
	
	/**
	 * Indica si ha hecho click sobre alg&uacuten componente del toolbar.
	 *
	 * @return		<code>boolean</code>
	 */ 
	public boolean isToolClicked() {
				
		return clicked;
	}
	
	/**
	 * M&eacutetodo para especificar si se hizo click sobre un componente
	 * de <code>ToolBar</code>.
	 * 
	 * @param s		el <code>Sprite</code>	
	 */
	public void setClicked (boolean b){
				
		this.clicked = b;
	}	
	
	/**
	 * M&eacutetodo para especificar el componente sobre el cual se hizo click.
	 * 
	 * @param s		el <code>Sprite</code>	
	 */
	public void setClickedTool (Sprite s){
				
		this.toolClicked = (Sprite) s;
	}	
}