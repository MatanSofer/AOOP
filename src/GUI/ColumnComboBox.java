package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class ColumnComboBox extends JPanel implements ActionListener
{
	private static final String[] names = {"Settlement Name" , "Settlement Type", "RamzorColor" }; //combobox names
	private  JComboBox<String> combo;
	private static int column; //holds current column
	
	public ColumnComboBox()
	{
		this.setLayout(new BoxLayout(this , BoxLayout.PAGE_AXIS));  //set layout and add combo box
		combo = new JComboBox<String>(names);
		this.add(combo);
		combo.addActionListener(this);
		this.setVisible(true);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==combo)
		{
			switch(combo.getItemAt(combo.getSelectedIndex())) //to know which column we hold so we can filter
			{
			case "Settlement Name":column=0;break;
			case "Settlement Type":column=1;break;
			case "RamzorColor":column=2;break;

			}
		}
		
		
	}
	
	public static int getColumn()  //return current column
	{
		return column;
	}
	
	
	
	

}
