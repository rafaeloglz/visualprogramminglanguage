/**
 * componentes a utilizar en el &aacuterea de trabajo.
 * 
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figueroa Ramirez 
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.MatteBorder;
import java.awt.Window.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.dnd.*;
import java.util.ArrayList;
import javax.swing.border.*;
import sprite.*;
import struct.StructV;

public class ToolBar{
	
	private GUI gui;	
	private JToolBar toolbar;
	private String name;
	private int orientation;
	public Sprite begin,end;
	private ArrayList<WorkArea> workAreas;	
	private ArrayList<JLabel> labels;
	private ArrayList<Sprite> tools;
			    
    /** Nombre por omisi&oacuten **/
	public static final String DEFAULT_NAME = "Herramientas";
	/** Orientaci&oacuten por omisi&oacuten **/
	public static final int DEFAULT_ORIENTATION = JToolBar.HORIZONTAL;

	/**
	 * Constructor por omisi&oacuten.
	 */
	public ToolBar (ArrayList<WorkArea> workAreas, GUI gui){
		
		this (DEFAULT_NAME, DEFAULT_ORIENTATION, workAreas, gui);
	}	
	
	/**
	 * Constructor donde se especifica el nombre y el orientaci&oacuten.
	 *
	 * @param name				el nombre del ToolBar
	 * @param orientation		la orientaci&oacuten del toolbar		
	 */
	public ToolBar (String name, int orientation, ArrayList<WorkArea> workAreas, GUI gui){
		
		this.name = name;
		this.orientation = orientation;
		this.workAreas = workAreas;
		this.gui = gui;
		this.labels = new ArrayList<JLabel>();
		this.tools = new ArrayList<Sprite>();
		
		toolbar = new JToolBar (name, orientation);
		
		toolbar.setFloatable(false);
		
		buildToolBar ();
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

	private void addTool(Sprite sprite) {
			
			final Sprite tool = sprite;
			
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
	        tools.add(tool);

	        toolbar.add(label);     
	        toolbar.addSeparator();
	}
		
	/**
	 * M&eacutetodo para obtener el <code>JToolBar</code>.
	 *
	 * @return		el <code>JToolBar</code>
	 */
	public JToolBar getToolBar (){		
		
		return toolbar;
	}
		
	public void reset(){
		
		begin = end = null;
	}	
}