package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import IO.*;
import Country.Map;

public class StatWindow extends JFrame 
{


		private  JTextField tf1;
		private Map map;
		private statsJTable allStats;
		private ColumnComboBox combobox;
		private TableRowSorter<StatCenterTable>sorter;
		JButton button6;
		public StatWindow(Map map )
		{	
			
			super("Statistics Window");
			this.map=map;
			JTextField tf1=new JTextField();
			allStats = new statsJTable(map,tf1);
			Dialog doseFrame = new Dialog(this,button6 =new JButton("Vaccinate"),allStats);
			combobox=new ColumnComboBox();

			
			JButton button4=new JButton("Save");
			button4.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					StatisticsFile.SaveTable("Table"+Simulation.Clock.now()+"ticks", allStats.getJTable());
				}			
			});
			
			JButton button5=new JButton("Add Sick");
			button5.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int selectedRow = allStats.getCurrRow();
					map.getSettlements()[selectedRow].jtableMakeSick();
					allStats.fireTableDataChanged();
				}			
			
	
				
		});

			
		
		
		this.setLayout(new BorderLayout());

		
		JPanel north= new JPanel();
		JPanel center = new JPanel();
		JPanel south =  new JPanel();
		
		JLabel spaces = new JLabel("                       "
				+ "                                       "
				+ "                                     "
				+ "                          ");
		center.setLayout(new BoxLayout(center , BoxLayout.LINE_AXIS));
		north.setLayout(new BoxLayout(north , BoxLayout.LINE_AXIS));
		south.setLayout(new BoxLayout(south, BoxLayout.LINE_AXIS));
		
		
		north.add(combobox);
		north.add(tf1);	
		south.add(spaces);
		south.add(button4);
		south.add(button5);
		south.add(button6);
		center.add(allStats);
		
		
		
		this.add(center , BorderLayout.CENTER);
		this.add(north , BorderLayout.NORTH);
		this.add(south , BorderLayout.SOUTH);
		
	
		
		//this.setSize(1050,250);
		
		this.pack();
		this.setVisible(true);
		
		}	
	
	public void updateTable() {
		allStats.fireTableDataChanged();
	}
	
	
}
	

