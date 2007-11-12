/**
 * GUI es el &aacute;rea donde se dibujan los componentes, y se mueven utilizando DnD.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import graph.*;

public class GUI {

	private JFrame frame;
	private MenuBar menu;
	private int tabCount;
	private JTabbedPane tabPanel;
	private JTextArea textArea;
	private ToolBar toolbar;
	private ArrayList<WorkArea> workAreas;

	/**
	 * Constructor de la clase.
	 */
	public GUI() {

		this.frame = new JFrame("Visual Programming Language");
		this.menu = new MenuBar(this);
		this.tabPanel = new JTabbedPane();
		this.textArea = new JTextArea();
		this.toolbar = new ToolBar("Herramientas", SwingConstants.VERTICAL,
				this);
		this.workAreas = new ArrayList<WorkArea>();

		this.init();
	}

	/**
	 * M&eacute;todo para agregar nuevos tabs de trabajo.
	 */
	public void addTab() {

		WorkArea wa = new WorkArea("method" + tabCount, toolbar);
		this.workAreas.add(wa);
		this.tabPanel.addTab(wa.getName(), wa);
		this.tabCount++;
	}

	/**
	 * M&eacute;todo para obtener el frame.
	 * 
	 * @return <code>JFrame</code>
	 */
	public JFrame getJFrame() {

		return this.frame;
	}

	/**
	 * M&eacute;todo para obtener el toolbar.
	 * 
	 * @return <code>ToolBar</code>
	 */
	public MenuBar getMenuBar() {

		return this.menu;
	}

	/**
	 * M&eacute;todo para obtener el &aacute;rea de texto.
	 * 
	 * @return <code>JTextArea</code>
	 */
	public JTextArea getTextArea() {

		return this.textArea;
	}

	/**
	 * M&eacute;todo para obtener el toolbar.
	 * 
	 * @return <code>ToolBar</code>
	 */
	public ToolBar getToolBar() {

		return this.toolbar;
	}

	/**
	 * M&eacute;todo para obtener un <code>WorkArea</code> por &iacute;ndice.
	 * 
	 * @return <code>ToolBar</code>
	 */
	public WorkArea getWorkAreaAt(int index) {

		return this.workAreas.get(index);
	}

	/**
	 * M&eacute;todo para obtener la cantidad de <code>WorkArea</code>'s.
	 * 
	 * @return <code>int</code>
	 */
	public int getWorkAreaCount() {

		return this.workAreas.size();
	}

	public ArrayList<Graph> getGraphs() {

		ArrayList<Graph> graphs = new ArrayList();

		for (int i = 0; i < workAreas.size(); i++)
			graphs.add(this.workAreas.get(i).getGraph());

		return graphs;
	}

	/**
	 * M&eacute;todo para inicializar a GUI.
	 */
	public void init() {

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		JScrollPane scrollPanel1 = new JScrollPane(this.toolbar.getJToolBar());
		JScrollPane scrollPanel2 = new JScrollPane(this.textArea);

		WorkArea wa = new WorkArea("main", toolbar);
		this.workAreas.add(wa);
		this.tabPanel.addTab(wa.getName(), wa);

		this.frame.setLayout(gridbag);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		this.frame.getContentPane().add(scrollPanel1, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 4.0;
		gbc.weighty = 10.0;
		this.frame.getContentPane().add(this.tabPanel, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 1.0;
		this.frame.getContentPane().add(scrollPanel2, gbc);

		this.frame.setJMenuBar(menu.getMenuBar());

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setSize(600, 600);
		this.frame.setLocationByPlatform(true);
		this.frame.setResizable(false);
		this.frame.setVisible(true);

		this.tabCount = 0;
	}

	/**
	 * M&eacute;todo para borrar las estructuras de <code>Sprite</code>
	 * <code>Line</code>
	 * y <code>Graph</code>.
	 */
	public void newProyect() {

		this.tabPanel.removeAll();
		this.workAreas.clear();

		WorkArea wa = new WorkArea("main", toolbar);
		this.workAreas.add(wa);
		this.tabPanel.addTab(wa.getName(), wa);
		this.tabCount = 0;
	}

	/**
	 * M&eacute;todo para abrir un proyecto guardado previamente.
	 * 
	 * @param proyect
	 *            <code>Object</code>
	 */
	public void openProyect(Object proyect) {

		ArrayList<Graph> graphs = (ArrayList<Graph>) proyect;

		this.tabPanel.removeAll();
		this.workAreas.clear();
		this.tabCount = 0;

		WorkArea main = new WorkArea("main", toolbar);
		main.setGraph(graphs.get(0));
		this.workAreas.add(main);
		this.tabPanel.addTab(main.getName(), main);

		for (int i = 1; i < graphs.size(); i++) {

			WorkArea wa = new WorkArea("method" + tabCount, toolbar);
			wa.setGraph(graphs.get(i));
			this.workAreas.add(wa);
			this.tabPanel.addTab(wa.getName(), wa);
			this.tabCount++;
		}
	}
}