/**
 * Clase que contiene los elementos para agregar informaci&oacute; a los componentes.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import struct.StructV;

public class ActivityBuilder {

	private AddCode addCode;
	private Activity activity;
	private JFrame activityFrame;
	private String configFilename;
	private BufferedReader in;
	private int activityIndex;

	/**
	 * Constructor por omisi&oacute;n.
	 * 
	 * @param addCode
	 *            <code>AddCode</code>
	 */
	public ActivityBuilder(AddCode addCode) {

		this.addCode = addCode;
		this.configFilename = "config.txt";
		this.activityIndex = 0;
	}

	/**
	 * M&eacute;todo para construir una ventana a partir de una actividad.
	 * 
	 * @param activity
	 *            <code>String</code>
	 */
	public void buildActivity(String activity, StructV stv) {

		ArrayList<String> activityContents = searchInFile(activity);
		this.activity = new Activity(activityContents.size());
		this.activityFrame = new JFrame();

		JButton accept = new JButton("Aceptar");
		accept.addActionListener(this.addCode);
		accept.setActionCommand("accept" + this.activityIndex);
		JButton cancel = new JButton("Cancelar");
		cancel.addActionListener(this.addCode);
		cancel.setActionCommand("cancel" + this.activityIndex);

		JPanel panel1 = new JPanel();
		panel1.add(accept);
		panel1.add(cancel);

		JLabel[] labels = new JLabel[activityContents.size()];

		ArrayList<String> order = new ArrayList<String>();

		for (int i = 0; i < activityContents.size(); i++) {
			this.activity.add(activityContents.get(i).split(":", 2)[0], this
					.buildContent(activityContents.get(i).split(":", 2)[1]));
			order.add(activityContents.get(i).split(":", 2)[0]);
		}

		this.activity.add("order", order);

		for (int i = 0; i < this.activity.getKeyCount(); i++)
			labels[i] = new JLabel(this.activity.getKeyAt(i));

		for (int i = 0; i < this.activity.getKeyCount(); i++) {

			if (this.activity.getContentAt(i) instanceof JTextField) {

				String key = this.activity.getKeyAt(i);
				Hashtable<String, Object> hash = (Hashtable<String, Object>) stv
						.getValue();
				JTextField tf = (JTextField) this.activity.getContentAt(i);
				tf.setText((String) hash.get(key));
			} else if (this.activity.getContentAt(i) instanceof JTextArea) {

				String key = this.activity.getKeyAt(i);
				Hashtable<String, Object> hash = (Hashtable<String, Object>) stv
						.getValue();
				JTextArea ta = (JTextArea) this.activity.getContentAt(i);
				ta.setText((String) hash.get(key));
			} else if (this.activity.getContentAt(i) instanceof JTable) {

				String key = this.activity.getKeyAt(i);
				Hashtable<String, Object> hash = (Hashtable<String, Object>) stv
						.getValue();
				JTable table = (JTable) this.activity.getContentAt(i);

				for (int r = 0; r < table.getRowCount(); r++) {
					for (int c = 0; c < table.getColumnCount(); c++) {

						String cell = (String) hash.get("var" + r);

						if (cell != null) {

							cell = cell.split(" ")[c];

							if (!cell.equals("null"))
								table.setValueAt(cell, r, c);
						}
					}
				}
			}
		}

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel panel2 = new JPanel();
		panel2.setLayout(gridbag);

		for (int i = 0; i < this.activity.getKeyCount(); i++) {

			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.weightx = 0.0;
			gbc.weighty = 0.0;

			if (this.activity.getContentAt(i) instanceof JTable) {

				JTable table = (JTable) this.activity.getContentAt(i);

				gbc.gridx = 0;
				gbc.gridy = i;
				panel2.add(labels[i], gbc);

				gbc.gridx = 0;
				gbc.gridy = i + 1;
				panel2.add(table.getTableHeader(), gbc);

				gbc.gridx = 0;
				gbc.gridy = i + 2;
				panel2.add(table, gbc);
			} else {

				gbc.gridx = 0;
				gbc.gridy = i;
				panel2.add(labels[i], gbc);

				gbc.gridx = 1;
				gbc.gridy = i;
				panel2.add((JComponent) this.activity.getContentAt(i), gbc);
			}
		}

		this.activityFrame.getContentPane().add(panel2, BorderLayout.CENTER);
		this.activityFrame.getContentPane().add(panel1, BorderLayout.SOUTH);
		this.activityFrame.pack();
		this.activityFrame.setResizable(false);
		this.activityFrame.setLocationByPlatform(true);
		this.activityFrame
				.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.activityFrame.setVisible(true);

		this.activityIndex++;
	}

	/**
	 * M&eacute;todo para buscar en el archivo de configuraci&oacute;n la
	 * informaci&oacute;n necesaria para construir la actividad junto con sus
	 * elementos.
	 * 
	 * @param activity
	 *            <code>String</code>
	 * @return <code>ArrayList<String></code>
	 */
	private ArrayList<String> searchInFile(String activity) {

		String line;
		ArrayList<String> contents;

		line = "";
		contents = new ArrayList();

		try {

			in = new BufferedReader(new FileReader(new File(configFilename)));
			line = in.readLine();

			while (line != null) {

				if (line.equals("[" + activity.toLowerCase() + "]")) {

					line = in.readLine();

					while (line != null && !line.equals("")) {

						contents.add(line);
						line = in.readLine();
					}
				}

				line = in.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contents;
	}

	/**
	 * M&eacute;todo para construir el JComponent indicado en content.
	 * 
	 * @param content
	 *            <code>String</code>
	 * @return <code>JComponent</code>
	 */
	private JComponent buildContent(String content) {

		String jcomponent;
		String[] param;
		JComponent jc;

		jcomponent = content.split(":")[0];
		param = content.split(":")[1].split(",");
		jc = null;

		if (jcomponent.equals("TextField"))
			jc = new JTextField(Integer.parseInt(param[0]));

		if (jcomponent.equals("TextArea"))
			jc = new JTextArea(Integer.parseInt(param[0]), Integer
					.parseInt(param[1]));

		if (jcomponent.equals("Table")) {

			String[] colsNames = param[2].split("#");
			String[][] rowData = new String[Integer.parseInt(param[0])][Integer
					.parseInt(param[1])];

			JTable table = new JTable(rowData, colsNames);
			table.add(table.getTableHeader(), 0);

			jc = table;
		}

		return jc;
	}

	/**
	 * M&eacute;todo para obtener la actividad.
	 * 
	 * @return <code>Activity</code>
	 */
	public Activity getActivity() {
		return this.activity;
	}

	/**
	 * M&eacute;todo para obtener la ventana de la actividad
	 * 
	 * @return <code>Activity</code>
	 */
	public JFrame getActivityFrame() {
		return this.activityFrame;
	}
}