package GUI;

import java.awt.Frame;

import java.awt.GridLayout;

import javax.swing.BorderFactory;

import javax.swing.JCheckBox;

import javax.swing.JDialog;

import javax.swing.JLabel;

import javax.swing.JPanel;



public class MutationDialog extends JDialog {
	
			String [] names = {"British","Chinese","SouthAfrican"};
			JPanel mainpanel = new JPanel(); 
			   
		public MutationDialog (Frame window){
			super(window, "Mutations",true);
			GridLayout mylayout = new GridLayout(4,4);
			this.mainpanel.setLayout(mylayout);
			
			JPanel panel = new JPanel();
			panel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			
			this.mainpanel.add(panel);
			
			for(String name : names){
				panel.add(new JLabel(name));
				this.mainpanel.add(panel);
			}
			
			for(String name1 : names){
				panel.add(new JLabel(name1));
				this.mainpanel.add(panel);
				for(String name2 : names){
					
					JCheckBox checkbox = new JCheckBox();
 					// add to a container
					panel.add(checkbox);
					this.mainpanel.add(panel);
					// set state
					checkbox.setSelected(false);
					// check state
					if (checkbox.isSelected()) {
						
					    // do something...
	
					} else {
						
					    // do something else...
					 
					}
					
				}
			}
			
			this.add(mainpanel);

			
			pack();
			setVisible(true);
	}	
	
	private void toggleMutation(String virus,String mutation){
		
		
	}
	
	

}