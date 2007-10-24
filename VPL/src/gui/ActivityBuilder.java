/**
 * Clase que contiene los elementos para agregar informaci&oacute a los componentes.
 * 
 * @author Andr&eacute Freyr&iacutea Cedeño
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figuero Ram&iacuterez
 * @author Jos&eacute Roberto Ram&iacuterez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import struct.StructV;

public class ActivityBuilder {
	
	private AddCode addCode;
	private Activity activity;
	private JFrame activityFrame;
	private String configFilename;
	private BufferedReader in;
	private int activityIndex;
		
	/**
	 * Constructor por omisi&oacuten.
	 *
	 * @param addCode		<code>AddCode</code>
	 */
	public ActivityBuilder(AddCode addCode) {
		
		this.addCode = addCode;
		this.configFilename = "config.txt";
		this.activityIndex = 0;
	}
	
	/**
	 * M&eacutetodo para construir una ventana a partir de una actividad.
	 *
	 * @param activity			<code>String</code>
	 */
	public void buildActivity(String activity, StructV stv) {
		
		ArrayList<String> activityContents;
		JScrollPane scrollPanel;
		JPanel panel1, panel2;
		JButton accept;
		JButton cancel;
		JLabel[] labels;
		ArrayList<String> order = new ArrayList<String>();
		
		activityContents = searchInFile(activity);
		this.activity = new Activity (activityContents.size());
		this.activityFrame = new JFrame();
		
		accept = new JButton("Aceptar");
		accept.addActionListener(this.addCode);
		accept.setActionCommand("accept" + this.activityIndex);
		cancel = new JButton("Cancelar");
		cancel.addActionListener(this.addCode);
		cancel.setActionCommand("cancel" + this.activityIndex);
		
		panel1 = new JPanel();
		panel1.add(accept);
		panel1.add(cancel);
		
		labels = new JLabel[activityContents.size()];
						
		for(int i = 0; i < activityContents.size(); i++) {			
			this.activity.add(activityContents.get(i).split(":", 2)[0], 
			this.buildContent(activityContents.get(i).split(":", 2)[1]));
			order.add(activityContents.get(i).split(":", 2)[0]);
		}
			
		this.activity.add("order", order);
				
		for(int i = 0; i < this.activity.getNumText(); i++) {
			labels[i] = new JLabel(this.activity.getKeyAt(i));
		}
		
		for(int i = 0; i < this.activity.getNumText(); i++){
			if(this.activity.getContentAt(i) instanceof JTextField){
				String key = this.activity.getKeyAt(i);
				Hashtable <String, Object> hash = (Hashtable<String, Object>) stv.getValue();
				JTextField tf = (JTextField) this.activity.getContentAt(i);
				tf.setText((String) hash.get(key));
			}
			else if(this.activity.getContentAt(i) instanceof JTextArea){
				String key = this.activity.getKeyAt(i);
				Hashtable <String, Object> hash = (Hashtable<String, Object>) stv.getValue();
				JTextArea ta = (JTextArea) this.activity.getContentAt(i);
				ta.setText((String) hash.get(key));
			}
		}
		
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0, 2));
			
		for(int i = 0; i < this.activity.getNumText(); i++) {			
			panel2.add(labels[i]);
			panel2.add((JComponent)this.activity.getContentAt(i));
		}
		
		
			
		
		scrollPanel = new JScrollPane(panel2);
		
		this.activityFrame.getContentPane().add(scrollPanel, BorderLayout.CENTER);
		this.activityFrame.getContentPane().add(panel1, BorderLayout.SOUTH);
		this.activityFrame.setSize(400, 200);
		this.activityFrame.pack();
		this.activityIndex++;
	}
	
	/**
	 * M&eacutetodo para buscar en el archivo de configuraci&oacuten la informaci&oacuten
	 * necesaria para construir la actividad junto con sus elementos.
	 *
	 * @param activity			<code>String</code>
	 * @return					<code>ArrayList<String></code>
	 */
	private ArrayList<String> searchInFile(String activity) {
		
		String line;
		ArrayList<String> contents;
		
		line = "";
		contents = new ArrayList();
		
		try {
			
			in = new BufferedReader(new FileReader(new File(configFilename)));
			line = in.readLine();
			
			while(line != null) {
				
				if(line.equals("[" + activity.toLowerCase() + "]")) {
					
					line = in.readLine();
					
					while(line != null && !line.equals("")) {
						
						contents.add(line);
						line = in.readLine();
					}
				}
				
				line = in.readLine();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return contents;
	}
	
	/**
	 * M&eacutetodo para construir el JComponent indicado en content.
	 * 
	 * @param content			<code>String</code>
	 * @return					<code>JComponent</code>
	 */
	private JComponent buildContent(String content) {
		
		String jcomponent;
		String[] param;
		JComponent jc;
		
		jcomponent = content.split(":")[0];
		param = content.split(":")[1].split(",");
		jc = null;
		
		if(jcomponent.equals("TextField"))
			jc = new JTextField(Integer.parseInt(param[0]));
		
		if(jcomponent.equals("TextArea"))
			jc = new JTextArea(Integer.parseInt(param[0]), Integer.parseInt(param[1]));
			
		if(jcomponent.equals("Table"))
			jc = new JTable(Integer.parseInt(param[0]), Integer.parseInt(param[1]));
		
		return jc; 
	}
	
	/**
	 * M&eacutetodo para obtener la actividad.
	 *
	 * @return			<code>Activity</code>
	 */
	public Activity getActivity() {
		return this.activity;
	}
	
	/**
	 * M&eacutetodo para obtener la ventana de la actividad
	 *
	 * @return			<code>Activity</code>
	 */
	public JFrame getActivityFrame() {
		return this.activityFrame;
	}
}