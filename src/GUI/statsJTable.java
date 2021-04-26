package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.util.regex.PatternSyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableRow;

import Country.*;

public class statsJTable extends JPanel
{
	private Map map;
	private TableRowSorter<StatCenterTable>sorter;
	private StatCenterTable model;
	private JTable table;
	private JTextField tf1;
	
	
	
	
	
	public statsJTable(Map map)
	{
		
		model = new StatCenterTable(map);
		table = new JTable(model);
		
		table.setRowSorter(sorter = new TableRowSorter<StatCenterTable>(model));
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(1010,100));
		table.setBackground(Color.CYAN);
		table.setFillsViewportHeight(true);
		this.add(new JScrollPane(table));
		
		
        this.setVisible(true);
	}
	
	public StatCenterTable getStatCenterTable()
	{
		return this.model ;
	}
	public JTable getJTable()
	{
		return this.table ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void filter(JTextField tf1)
	{
		this.tf1=tf1;
		this.tf1.setToolTipText("Filter Name Column");
		this.tf1.getDocument().addDocumentListener(new DocumentListener()
				{
					public void insertUpdate(DocumentEvent e) {newFilter();}
					public void removeUpdate(DocumentEvent e) {newFilter();}
					public void changedUpdate(DocumentEvent e) {newFilter();}
				});
	}
	private void newFilter()
	{
		try {sorter.setRowFilter(RowFilter.regexFilter(tf1.getText(),1));
		}catch(PatternSyntaxException e) {
			//wont update
		}
	}
	
	
	
	
	
	
	
}
