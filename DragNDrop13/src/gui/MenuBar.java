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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import graph.*;

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
	
	/**
	 * Constructor por omisi&oacuten.
	 * 
	 * @param gui		<code>GUI</code>
	 */	
	public MenuBar(GUI gui) {
		
		this.gui = gui;
		ls = new LoadAndSave();
		
		menuBar = new JMenuBar();
		menu1 = new JMenu("Archivo");
		menu2 = new JMenu("Proyecto");
		
		menuItem1_1 = new JMenuItem("Nuevo...");
		menuItem1_2 = new JMenuItem("Abrir...");
		menuItem1_3 = new JMenuItem("Guardar...");
		menuItem1_4	= new JMenuItem("Salir");	
		
		calcIcon = new ImageIcon("calc.png");
		menuItem2_1 = new JMenuItem("Generar codigo", calcIcon);
		menuItem2_2 = new JMenuItem("Nuevo tab");
		
		buildMenu();
	}
	
	/**
	 * M&eacutetodo para obtener el <code>JMenuBar</code>.
	 *
	 * @return			<code>JMenuBar</code>
	 */
	public JMenuBar getMenuBar (){		
		
		return menuBar;
	}
	
	/**
	 * M&eacutetodo para construir el men&uacute.
	 */
	private void buildMenu(){
			
		menuBar.add(menu1);
		menuBar.add(menu1);
		menuBar.add(menu2);
		 
		KeyStroke ctrlK = KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK);
		menuItem2_1.setAccelerator(ctrlK);
		menuItem2_1.addActionListener(new ActionListener (){
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		String msg = "";
        		
        		
        		for(int i=0;i<gui.getGraphs().size();i++){
        			//gui.getGraphs().get(i).print();        			
        			cm = new CodeMaker(gui.getGraphs().get(i));
        			
	        		if(cm.make()){	        			
	        			if(cm.writeToFile("",gui.getWorkAreas().get(i).getName()+".java"))
	        				msg = "Codigo guardado en "+gui.getWorkAreas().get(i).getName()+".java.";
	        			else msg = "Error al guardar el codigo.";
	        		}	
	        		else msg = "Error al guardar el codigo.";
	        		
	        		gui.getTextArea().setText(msg);
	        		
        		}
        	}
        });
	
		KeyStroke ctrlT = KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK);
		menuItem2_2.setAccelerator(ctrlT);
		menuItem2_2.addActionListener(new ActionListener (){
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		gui.addTab();
        	}
        });
		
		KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK);
		menuItem1_1.setAccelerator(ctrlN);
		menuItem1_1.addActionListener(new ActionListener (){
        	
        	public void actionPerformed(ActionEvent e) {         		
        	
        		gui.newProyect();
        		gui.getTextArea().setText("Nuevo proyecto.");	
        	}
        });
		
		KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK);
		menuItem1_2.setAccelerator(ctrlO);
		menuItem1_2.addActionListener(new ActionListener() {
			
	public void actionPerformed(ActionEvent e) {
				
				Object graphs;
				String path, filename, msg;
				
				path = "";
				filename = "vpl.txt";
				msg = "";
				
				graphs = ls.load(path, filename);
				
				if(graphs != null){
					
					gui.openProyect(graphs);
					gui.repaint();
					msg = "Archivo cargado desde " + path + filename;
				}
				else msg = "Error al cargar el archivo";
				
				gui.getTextArea().setText(msg);
			}		});
		
		KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK);
		menuItem1_3.setAccelerator(ctrlS);
		menuItem1_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String path, filename, msg;
				
				path = "";
				filename = "vpl.txt";
				msg = "";
				
				if(ls.save(gui.getGraphs(), path, filename))
					msg = "Archivo guardado en " + path + filename;
				else msg = "Error al guardar el archivo";
				
				gui.getTextArea().setText(msg);
			}
		});
		
		KeyStroke ctrlE = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK);
		menuItem1_4.setAccelerator(ctrlE);
		menuItem1_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		
		menu1.add(menuItem1_1);
		menu1.add(menuItem1_2);
		menu1.add(menuItem1_3);
		menu1.add(menuItem1_4);
		menu2.add(menuItem2_1);	
		menu2.add(menuItem2_2);
	}	
}
