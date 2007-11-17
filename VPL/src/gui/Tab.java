package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tab {
	
	private String title;
	private WorkArea wa;
	private JPanel tab;
	private JButton changeTitle;
	private JTextField tabLabel;
	
	public Tab(WorkArea wa) {
		
		this.title = wa.getName();
		this.wa = wa;
		
		init();
	}
	
	private void init() {
		
		tab = new JPanel();
		changeTitle = new JButton();
		
		tabLabel = new JTextField(title);
		tabLabel.setEditable(false);
		
		changeTitle.setPreferredSize(new Dimension (10, 10));
		changeTitle.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!tabLabel.isEditable()) {
					
					tabLabel.setEditable(true);
				}
				else{
					
					wa.setName(tabLabel.getText());
					tabLabel.setEditable(false);
				}
			}
		});
		
		tab.add(tabLabel);
		tab.add(changeTitle);
	}
	
	public JPanel getTab() {
		
		return this.tab;
	}
	
}
