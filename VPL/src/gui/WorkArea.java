/**
 * Clase que representa el Area de trabajo
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.dnd.DragSource;
import java.util.ArrayList;
import javax.swing.JComponent;
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
	 * Constructor por omisi&oacute;n.
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
	 * M&eacute;todo para agregar un <code>Line</code>.
	 * 
	 * @param line
	 *            <code>Line</code>
	 */
	public void addLine(Line line) {

		this.lines.add(line);
	}

	/**
	 * M&eacute;todo para agregar un <code>Sprite</code>.
	 * 
	 * @param sprite
	 *            <code>Sprite</code>
	 */
	public void addSprite(Sprite sprite) {

		this.sprites.add(sprite);
	}

	/**
	 * M&eacute;todo para borrar las estructuras de <code>Sprite</code>
	 * <code>Line</code>
	 * y <code>Graph</code>.
	 */
	public void clear() {

		this.sprites.clear();
		this.lines.clear();
		this.graph.clear();
	}

	/**
	 * M&eacute;todo para obtener el objeto encargado de realizar las uniones
	 * entre componentes.
	 * 
	 * @return <code>Connect</code>
	 */
	public Connect getConnect() {

		return this.connect;
	}

	/**
	 * M&eacute;todo para obtener el objeto encargado de realizar las uniones
	 * entre componentes.
	 * 
	 * @return <code>DragNDrop</code>
	 */
	public DragNDrop getDragNDrop() {

		return this.dragNDrop;
	}

	/**
	 * M&eacute;todo para obtener el grafo.
	 * 
	 * @return <code>Graph</code>
	 */
	public Graph getGraph() {

		return this.graph;
	}

	/**
	 * M&eacute;todo para obtener un <code>Line</code> por &iacute;ndice.
	 * 
	 * @param index
	 *            <code>int</code>
	 * @return <code>Line</code>
	 */
	public Line getLineAt(int index) {

		return this.lines.get(index);
	}

	/**
	 * M&eacute;todo para obtener el n&uacute;mero de <code>Line</code>.
	 * 
	 * @return <code>int</code>
	 */
	public int getLineCount() {

		return this.lines.size();
	}

	/**
	 * M&eacute;todo para obtener el nombre.
	 * 
	 * @return <code>String</code>
	 */
	@Override
	public String getName() {

		return this.name;
	}

	/**
	 * M&eacute;todo para obtener un <code>Sprite</code> por &iacute;ndice.
	 * 
	 * @param index
	 *            <code>int</code>
	 * @return <code>Sprite</code>
	 */
	public Sprite getSpriteAt(int index) {

		return this.sprites.get(index);
	}

	/**
	 * M&eacute;todo para obtener el n&uacute;mero de <code>Sprite</code>.
	 * 
	 * @return <code>int</code>
	 */
	public int getSpriteCount() {

		return this.sprites.size();
	}

	/**
	 * M&eacute;todo para para que el componente se dibuje a si mismo dibujando
	 * cada uno de los objetos Sprite y Line.
	 * 
	 * @param g
	 *            <code>Graphics</code>
	 */
	@Override
	public void paintComponent(Graphics g) {

		for (int i = 0; i < this.sprites.size(); i++) {

			Sprite f = this.sprites.get(i);
			f.paintSprite(g);
		}

		for (int i = 0; i < this.lines.size(); i++) {

			Line l = this.lines.get(i);
			l.paintLine(g);
		}
	}

	/**
	 * M&eacute;todo que remueve una Linea del area de Trabajo
	 * 
	 * @return <code>Line</code>
	 */

	public Line removeLine(int index) {

		return this.lines.remove(index);
	}

	/**
	 * M&eacute;todo que remueve una Figura del area de Trabajo
	 * 
	 * @return <code>Sprite</code>
	 */

	public Sprite removeSprite(int index) {

		return this.sprites.remove(index);
	}

	/**
	 * M&eacute;todo para obtener el grafo.
	 * 
	 * @param g <code>Graph</code>
	 */
	public void setGraph(Graph g) {

		this.graph = g;

		for (int i = 0; i < g.getNumVertices(); i++) {

			StructV stv = (StructV) g.getVertexAt(i).getValue();
			this.sprites.add(stv.getSprite());
		}

		for (int i = 0; i < g.getNumEdges(); i++) {

			StructE ste = (StructE) g.getEdgeAt(i).getWeight();
			this.lines.add(ste.getLine());
		}
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
}