/**
 * Clase que contiene los componentes a utilizar en el &aacute;rea de trabajo.
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
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.*;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;

import sprite.*;
import struct.StructV;

public class ToolBar {

	private boolean clicked;
	private Cursor dragCursor = DragSource.DefaultMoveDrop;
	private GUI gui;
	private String name;
	private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	private int orientation;
	private JToolBar toolbar;
	private Sprite toolClicked;

	/** Nombre por omisi&oacute;n * */
	public static final String DEFAULT_NAME = "Herramientas";

	/** Orientaci&oacute;n por omisi&oacute;n * */
	public static final int DEFAULT_ORIENTATION = SwingConstants.HORIZONTAL;

	/**
	 * Constructor por omisi&oacute;n.
	 */
	public ToolBar(GUI gui) {

		this(DEFAULT_NAME, DEFAULT_ORIENTATION, gui);
	}

	/**
	 * Constructor donde se especifica el nombre y el orientaci&oacute;n.
	 * 
	 * @param name
	 *            el nombre del ToolBar
	 * @param orientation
	 *            la orientaci&oacute;n del toolbar
	 */
	public ToolBar(String name, int orientation, GUI gui) {

		this.name = name;
		this.orientation = orientation;
		this.gui = gui;
		
		this.toolbar = new JToolBar(this.name, this.orientation);
		this.toolbar.setFloatable(false);

		buildToolBar();
	}

	/**
	 * M&eacute;todo para agregar los <code>Listeners</code> correspondientes
	 * a cada componente en el toolbar.
	 * 
	 * @param button
	 *            <code>JButton</code>
	 * @param sprite
	 *            <code>Sprite</code>
	 */
	private void addListener(JLabel label, final Sprite sprite) {

		label.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent m) {

				gui.getJFrame().setCursor(dragCursor);

				try {

					setClickedTool(sprite.clone());
				} catch (CloneNotSupportedException e) {

					e.printStackTrace();
				}

				setClicked(true);
			}

			@Override
			public void mouseReleased(MouseEvent m) {

				if (isToolClicked()) {

					setClicked(false);

					Sprite temp = getClickedTool();

					for (int i = 0; i < gui.getWorkAreaCount(); i++) {

						WorkArea wa = gui.getWorkAreaAt(i);

						if (wa.getMousePosition() != null) {

							temp.setX((int) wa.getMousePosition().getX()
									- temp.getWidth() / 2);
							temp.setY((int) wa.getMousePosition().getY()
									- temp.getHeight() / 2);

							String stringClass = temp.getClass().toString();
							String spriteName = stringClass.substring(19)
									.toLowerCase();

							Hashtable<String, Object> tempHashTable = new Hashtable<String, Object>();
							tempHashTable.put("name", spriteName);

							StructV<Hashtable<String, Object>> stv = new StructV<Hashtable<String, Object>>(
									temp, tempHashTable);
							
							if(temp instanceof SpriteGlobalVar && 
								wa.getGlobalVar()[0] == null) {
								
								wa.addSprite(temp);
								wa.getGraph().addVertex(stv);

								gui.addGlobalVar(temp, stv);
							}
							else if(temp instanceof SpriteVar) {
								
								if(!wa.isLocalVarSet()) {

									wa.addSprite(temp);
									wa.getGraph().addVertex(stv);
								}	
							}
							else if (temp instanceof SpriteBegin 
									&& wa.getGraph().getHead() == null) {
								
								wa.addSprite(temp);
								wa.getGraph().addVertex(stv);
								wa.getGraph().setHead(stv);
							}		
							else if(!(temp instanceof SpriteVar)
									&& !(temp instanceof SpriteGlobalVar)
									&& !(temp instanceof SpriteBegin)) {
								
								wa.addSprite(temp);
								wa.getGraph().addVertex(stv);
							}
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
	 * M&eacute;todo para agregar componentes a la barra de herramientas.
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
		this.addListener(label, tool);
		toolbar.add(label);
		toolbar.addSeparator();
	}

	/**
	 * Crea los componentes a utilizar en el toolbar junto con sus iconos. Los
	 * agrega al toolbar y les agrega su <code>Listener</code>.
	 */
	private void buildToolBar() {

		toolbar.addSeparator();

		ArrayList<Sprite> sprites = new ArrayList<Sprite>();

		sprites.add(new SpriteBegin());
		sprites.add(new SpriteEnd());
		sprites.add(new SpriteGlobalVar());
		sprites.add(new SpriteVar());
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

		for (int i = 0; i < sprites.size(); i++) {
			addTool(sprites.get(i));
		}

		toolbar.setSize(50, 50);
	}

	/**
	 * M&eacute;todo para obtener el componente en el cual se hizo click.
	 * 
	 * @return <code>Sprite</code>
	 */
	public Sprite getClickedTool() {

		return this.toolClicked;
	}

	/**
	 * M&eacute;todo para obtener el <code>JToolBar</code>.
	 * 
	 * @return el <code>JToolBar</code>
	 */
	public JToolBar getJToolBar() {

		return toolbar;
	}

	/**
	 * Indica si ha hecho click sobre alg&uacute;n componente del toolbar.
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isToolClicked() {

		return clicked;
	}

	/**
	 * M&eacute;todo para especificar si se hizo click sobre un componente de
	 * <code>ToolBar</code>.
	 * 
	 * @param b
	 *            <code>boleean</code>
	 */
	public void setClicked(boolean b) {

		this.clicked = b;
	}

	/**
	 * M&eacute;todo para especificar el componente sobre el cual se hizo click.
	 * 
	 * @param s
	 *            el <code>Sprite</code>
	 */
	public void setClickedTool(Sprite s) {

		this.toolClicked = s;
	}
}