package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;

import javax.swing.BoxLayout;
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
import javax.swing.table.TableRowSorter;

import Country.Map;

public class StatWindow extends JFrame
{

//		private final JLabel lb1,lb2;
		private  JTextField tf1;
		private Map map;
		private statsJTable allStats;
		private ColumnComboBox combobox;
		private TableRowSorter<StatCenterTable>sorter;
		
		public StatWindow(Map map)
		{	
			
			super("Statistics Window");
			this.map=map;
			allStats = new statsJTable(map);
			
			combobox=new ColumnComboBox();
//			combobox.add
//			combobox.actionPerformed(ActionEvent e);
//			{
//						
//			});
//			
			
			JTextField tf1=new JTextField();
			tf1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println(combobox.getColumn());
						tf1.setToolTipText("Filter Name Column");
						tf1.getDocument().addDocumentListener(new DocumentListener()
								{
									public void insertUpdate(DocumentEvent e) {newFilter();}
									public void removeUpdate(DocumentEvent e) {newFilter();}
									public void changedUpdate(DocumentEvent e) {newFilter();}
								});
				}
				private void newFilter()
				{
						try {sorter.setRowFilter(RowFilter.regexFilter(tf1.getText(),combobox.getColumn()));
						}catch(PatternSyntaxException e) {
							//wont update
						}
				
				}			
			});
				
			
			
//			allStats.addActionListener(new ActionListener()
//			{
//				public void actionPerformed(ActionEvent e)
//				{
//					
//				}			;
//			});
			
			JButton button4=new JButton("Save");
			button4.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}			;
			});
			
			JButton button5=new JButton("Add Sick");
			button5.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}			;
			});
			
			JButton button6=new JButton("Vaccinate");
			button6.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}			;
		
			
			
				
				
		});

			
		
		
		this.setLayout(new BorderLayout());

		
		JPanel north= new JPanel();
		JPanel center = new JPanel();
		JPanel south =  new JPanel();
		
		center.setLayout(new BoxLayout(center , BoxLayout.LINE_AXIS));
		north.setLayout(new BoxLayout(north , BoxLayout.LINE_AXIS));
		south.setLayout(new BoxLayout(south, BoxLayout.LINE_AXIS));
		
		
		north.add(combobox);
		north.add(tf1);	
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
	}

