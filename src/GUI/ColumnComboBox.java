package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Country.Map;

public class ColumnComboBox extends JPanel implements ActionListener
{
	private static final String[] names = {"Settlement Name" , "Settlement Type"
			,"RamzorColor" , "Sick percentages" , "Vaccine dose number","Dead people Number"
			,"Current population number"};
	
	private  JComboBox<String> combo;
	private static int column;
	
	public ColumnComboBox()
	{
				
		
		this.setLayout(new BoxLayout(this , BoxLayout.PAGE_AXIS));
		combo = new JComboBox<String>(names);
		this.add(combo);
		combo.addActionListener(this);
		this.setVisible(true);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		int column;
		if(e.getSource()==combo)
		{
			switch(combo.getItemAt(combo.getSelectedIndex()))
			{
			case "Settlement Name":column=0;break;
			case "Settlement Type":column=1;break;
			case "RamzorColor":column=2;break;
			case "Sick percentages":column=3;break;
			case "Vaccine dose number":column=4;break;
			case "Dead people Number":column=5;break;
			case "Current population number":column=6;break;
			}
		}
		
		
	}
	
	public int getColumn()
	{
		return this.column;
	}
	

}
