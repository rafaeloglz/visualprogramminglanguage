package gui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class Tab {
	
	private String title;
	private WorkArea wa;
	private JTextField tabLabel;
	
	public Tab(WorkArea wa) {
		
		this.title = wa.getName();
		this.wa = wa;
		
		init();
	}
	
	private void init() {
		
		tabLabel = new JTextField(title);
		tabLabel.setOpaque(false);
		tabLabel.setEditable(false);
		
		tabLabel.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				if(e.getClickCount() == 2) {
					
					tabLabel.setEditable(true);
					tabLabel.setFocusable(true);
				}		
			}
		});
		
		tabLabel.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {
				
				wa.setName(tabLabel.getText());
				tabLabel.setEditable(false);
			}
		});
	}
	
	public JTextField getTab() {
		
		return this.tabLabel;
	}
	
}
