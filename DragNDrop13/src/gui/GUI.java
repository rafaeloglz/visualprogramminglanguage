/**
 * GUI es el &aacuterea donde se dibujan los componentes, y se mueven utilizando DnD.
 * 
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figueroa Ramirez 
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graph.*;
import sprite.*;
import struct.StructV;

public class GUI extends JComponent {

	private JFrame frame;
	private JTabbedPane tabPanel;
	private ToolBar toolbar;
	private MenuBar menu;
	private JTextArea textArea;
	private ArrayList<WorkArea> workAreas;
	private int tabIndex;

	/**
	 * Constructor de la clase.
	 */		
	public GUI(){
		frame = new JFrame ("DragNDrop v10");
		textArea = new JTextArea ();
		workAreas = new ArrayList();
		tabPanel = new JTabbedPane();
		toolbar = new ToolBar ("Herramientas", JToolBar.VERTICAL, workAreas, this);
		menu = new MenuBar(this);			
		init();
	}
	
	/**
	 * M&eacutetodo para obtener el frame.
	 *
	 * @return		<code>JFrame</code>
	 */
	public JFrame getFrame (){
			 	
	 	return frame;
	}	
	
	/**
	 * M&eacutetodo para obtener el toolbar.
	 *
	 * @return		<code>ToolBar</code>
	 */ 
	public ToolBar getToolBar (){
				
		return toolbar;
	}	
	
	/**
	 * M&eacutetodo para obtener el toolbar.
	 *
	 * @return		<code>ToolBar</code>
	 */
	public MenuBar getMenuBar (){
		
		return menu;
	}
	
	/**
	 * M&eacutetodo para obtener el &aacuterea de texto.
	 *
	 * @return		<code>JTextArea</code>
	 */
	public JTextArea getTextArea (){
		
		return textArea;
	}

	public ArrayList<Graph> getGraphs() {

		ArrayList<Graph> graphs;

		graphs = new ArrayList();

		for(int i = 0; i < workAreas.size(); i++)
			graphs.add(workAreas.get(i).getGraph());

		return graphs;
	}
	
	/**
	 * M&eacutetodo para borrar las estructuras de <code>Sprite</code>
	 * <code>Line</code> y <code>Graph</code>.
	 */
	public void newProyect() {
		
		WorkArea wa;
		
		tabPanel.removeAll();
		workAreas.clear();
		
		wa = new WorkArea("main", getToolbar());		
		workAreas.add(wa);
		tabPanel.addTab(wa.getName(), wa);		
		tabIndex = 0;
	}

	public void openProyect(Object proyect) {
		
		ArrayList<Graph> graphs;
		
		graphs = (ArrayList<Graph>) proyect;
		
		tabPanel.removeAll();
		workAreas.clear();
		tabIndex = 0;
		
		WorkArea main;
		
		main = new WorkArea("main", getToolbar());
		main.setGraph(graphs.get(0));
		workAreas.add(main);
		tabPanel.addTab(main.getName(), main);
		
		for(int i = 1; i < graphs.size(); i++) {
			
			WorkArea wa;
			
			wa = new WorkArea("method" + tabIndex, getToolbar());
			wa.setGraph(graphs.get(i)); 
			workAreas.add(wa);
			tabPanel.addTab(wa.getName(), wa);
			
			tabIndex++;
		}			
	}

	/**
	 * M&eacutetodo para inicializar a GUI.
	 */
	public void init(){
		
		JScrollPane scrollPanel1, scrollPanel2;
		WorkArea wa;
				
		scrollPanel1 = new JScrollPane (toolbar.getToolBar ());
		scrollPanel2 = new JScrollPane (textArea);
		
		wa = new WorkArea("main", getToolbar());
		workAreas.add(wa);
		tabPanel.addTab(wa.getName(), wa);
        frame.getContentPane ().add (scrollPanel1, BorderLayout.WEST);
        frame.getContentPane().add (tabPanel, BorderLayout.CENTER);
		frame.getContentPane ().add (scrollPanel2, BorderLayout.SOUTH); 
		frame.setJMenuBar(menu.getMenuBar());
					
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.pack();     	    	       
	   	frame.setSize(600, 600);
        frame.setVisible(true);    
	}
	 
	/**
	 * M&eacutetodo para agregar nuevos tabs de trabajo.
	 */
	public void addTab() {
		
		WorkArea wa;
		
		wa = new WorkArea("method" + tabIndex, getToolbar());
		workAreas.add(wa);
		tabPanel.addTab(wa.getName(), wa);
		
		ArrayList<Sprite> sprites = this.getWorkAreas().get(0).getSprites();		
		
		for(int i=0;i<sprites.size();i++){
			if(sprites.get(i) instanceof SpriteVar){
				wa.addSprite(sprites.get(i));	
			}
		}
		
		tabIndex++;
	}

	public ArrayList<WorkArea> getWorkAreas() {
		return workAreas;
	}

	public void setWorkAreas(ArrayList<WorkArea> workAreas) {
		this.workAreas = workAreas;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public ToolBar getToolbar() {
		return toolbar;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub		
	}	
}