package GUI;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public  class Dialog extends JDialog    //this is dialog for doses dialog
{
		private  JTextField doses;
		JButton button6;
		statsJTable allStats;
		private int result = -1;
		
		
		public Dialog(Frame window,JButton button6,statsJTable allStats) {
			super(window, "Add Doses", true);
			this.button6 = button6;
			this. allStats=allStats;
			JPanel dosepanel = new JPanel();
			dosepanel.setLayout(new BoxLayout(dosepanel,BoxLayout.PAGE_AXIS));
			
			
			JButton ok = new JButton("ENTER");
				ok.addActionListener(new ActionListener() { //set table and doses values
				public void actionPerformed(ActionEvent e) {
					allStats.getmodel().setValueAt(doses.getText(),allStats.getCurrRow(),4);
					setVisible(false);
					}
				});
			
				
				
				
			button6.addActionListener(new ActionListener() { //to save number of doses user entered
			public void actionPerformed(ActionEvent e) {
				if (showDialog() == JOptionPane.OK_OPTION) {
					doses.setText(doses.getText());
					
				}
			}
			});
			
			
			dosepanel.add(new JLabel("number of doses : "));
			dosepanel.add(doses = new JTextField());
			dosepanel.add(ok);
			this.add(dosepanel)	;
			
		}
		
		public int showDialog() {
				setLocationRelativeTo(getParent());
				pack();
				setVisible(true);
				return result;
			}
		
		public String getDosesText() {
				return doses.getText();
			}
		
		
		
}