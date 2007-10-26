/**
 * Clase que implementa el men&uacute de la aplicaci&oacuten.
 * 
 * @author Andr&eacute Freyr&iacutea Cedeno
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figuero Ram&iacuterez
 * @author Jos&eacute Roberto Ram&iacuterez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import graph.CodeMaker;
import graph.LoadAndSave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar {

	private CodeMaker cm;
	private LoadAndSave ls;
	private GUI gui;
	private JMenuBar menuBar;
	private JMenu menu1;
	private JMenu menu2;
	private JMenuItem menuItem1_1;
	private JMenuItem menuItem1_2;
	private JMenuItem menuItem1_3;
	private JMenuItem menuItem1_4;
	private JMenuItem menuItem2_1;
	private JMenuItem menuItem2_2;
	private Icon calcIcon;
	private String filename;
	
	/**
	 * Constructor por omisi&oacuten.
	 * 
	 * @param gui		<code>GUI</code>
	 */	
	public MenuBar(GUI gui) {
		
		this.gui = gui;
		this.ls = new LoadAndSave();
		
		this.menuBar = new JMenuBar();
		this.menu1 = new JMenu("Archivo");
		this.menu2 = new JMenu("Proyecto");
		
		this.menuItem1_1 = new JMenuItem("Nuevo...");
		this.menuItem1_2 = new JMenuItem("Abrir...");
		this.menuItem1_3 = new JMenuItem("Guardar...");
		this.menuItem1_4	= new JMenuItem("Salir");	
		
		this.calcIcon = new ImageIcon("calc.png");
		this.menuItem2_1 = new JMenuItem("Generar codigo", this.calcIcon);
		this.menuItem2_2 = new JMenuItem("Nuevo tab");
		
		filename = "Project.java";
		
		this.buildMenu();
	}
	
	/**
	 * M&eacutetodo para obtener el <code>JMenuBar</code>.
	 *
	 * @return			<code>JMenuBar</code>
	 */
	public JMenuBar getMenuBar (){		
		
		return this.menuBar;
	}
	
	/**
	 * M&eacutetodo para construir el men&uacute.
	 */
	private void buildMenu(){
			
		this.menuBar.add(this.menu1);
		this.menuBar.add(this.menu1);
		this.menuBar.add(this.menu2);

		KeyStroke ctrlK = KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK);
		this.menuItem2_1.setAccelerator(ctrlK);
		this.menuItem2_1.addActionListener(new ActionListener (){

        	public void actionPerformed(ActionEvent e) {

        		String msg = "";
        		
    			ArrayList<String> nombresMetodos = new ArrayList<String>();
    		
    			for(int i=0; i<gui.getGraphs().size(); i++){
    				nombresMetodos.add(gui.getWorkAreaAt(i).getName());        				
    			}
    		
    			cm = new CodeMaker(gui.getGraphs(), nombresMetodos);

        		if(cm.make()){
        			if(cm.writeToFile("",filename))
        				msg = "Codigo guardado en "+filename+".";
        			else msg = "Error al guardar el codigo.";
        		}	
        		else msg = "Error al guardar el codigo.";
        		
        		gui.getTextArea().setText(msg);
        	}
        });
	
		KeyStroke ctrlT = KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK);
		this.menuItem2_2.setAccelerator(ctrlT);
		this.menuItem2_2.addActionListener(new ActionListener (){
        	
        	public void actionPerformed(ActionEvent e) {
        		gui.addTab();
        	}
        });
		
		KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK);
		this.menuItem1_1.setAccelerator(ctrlN);
		this.menuItem1_1.addActionListener(new ActionListener (){
        	
        	public void actionPerformed(ActionEvent e) {         		
        	
        		gui.newProyect();
        		gui.getTextArea().setText("Nuevo proyecto.");	
        	}
        });
		
		KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK);
		this.menuItem1_2.setAccelerator(ctrlO);
		this.menuItem1_2.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
				
				Object graphs;
				String path="", filename = "vpl.txt", msg = "";
			
				graphs = ls.load(path, filename);
				
				if(graphs != null){
					
					gui.openProyect(graphs);
					msg = "Archivo cargado desde " + path + filename;
				}
				else msg = "Error al cargar el archivo";
				
				gui.getTextArea().setText(msg);
			}		});
		
		KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK);
		this.menuItem1_3.setAccelerator(ctrlS);
		this.menuItem1_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String path = "", filename = "vpl.txt", msg = "";
				
				if(ls.save(gui.getGraphs(), path, filename))
					msg = "Archivo guardado en " + path + filename;
				else msg = "Error al guardar el archivo";
				
				gui.getTextArea().setText(msg);
			}
		});
		
		KeyStroke ctrlE = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK);
		this.menuItem1_4.setAccelerator(ctrlE);
		this.menuItem1_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		this.menu1.add(this.menuItem1_1);
		this.menu1.add(this.menuItem1_2);
		this.menu1.add(this.menuItem1_3);
		this.menu1.add(this.menuItem1_4);
		this.menu2.add(this.menuItem2_1);	
		this.menu2.add(this.menuItem2_2);
	}	
}