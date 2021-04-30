package GUI;

import java.awt.Frame;

import java.awt.GridLayout;

import javax.swing.JCheckBox;

import javax.swing.JDialog;

import javax.swing.JLabel;

import javax.swing.JPanel;
import Virus.*;



public class MutationDialog extends JDialog {
	
			String [] names = {"British","Chinese","SouthAfrican"};
			JPanel mainpanel = new JPanel(); 
	
			
			
	public MutationDialog (Frame window){
		super(window, "Mutations",true);	
		setLayout(new GridLayout(0, 4, 25, 25));
		JCheckBox checkbox ;
		
		add(new JLabel("\t"));
		for(String name : names){
			add(new JLabel(name));
		}
		for (int i = 0; i < 3; i++) {
			add(new JLabel(names[i]));
			for (int j = 0; j < 3; j++) {
				if(i==j) {
					add(new JLabel("V"));
				}
				else {
					add(checkbox = new JCheckBox());
					checkbox.setSelected(false);
					// check state
					if (checkbox.isSelected()) {
						//Enable mutation
						toggleMutation(names[i],names[j],true);

					} else {
						//Disable mutation
						toggleMutation(names[i],names[j],false);	 
					}
				}
			}
        }
		
		pack();
		setVisible(true);
				
	}
	
	private void toggleMutation(String virus,String mutation,boolean bool){
		if(virus == names[0])
			BritishVariant.setMutation(mutation, bool);
		else if(virus == names[1])
			ChineseVariant.setMutation(mutation, bool);
		else if(virus == names[2])
			SouthAfricanVariant.setMutation(mutation, bool);
	}
	
	
	

}