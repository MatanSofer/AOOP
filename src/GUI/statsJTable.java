package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.util.regex.PatternSyntaxException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;


import Country.*;

public class statsJTable extends JPanel
{
	private Map map;
	private TableRowSorter<StatCenterTable>sorter;
	private StatCenterTable model;
	private JTable table;
	private JTextField tf1;
	
	
	
	
	
	public statsJTable(JTextField tf1)
	{
		
		model = new StatCenterTable();  //create model
		table = new JTable(model);  //create jtable with model
		this.tf1=tf1;
		table.setRowSorter(sorter = new TableRowSorter<StatCenterTable>(model));   //set all table settings
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(1010,100));
		table.setBackground(Color.lightGray);
		table.setFillsViewportHeight(true);
		this.add(new JScrollPane(table));
		
		this.add(tf1 );
		tf1.setToolTipText("FILTER NAME COLUMN");
		tf1.getDocument().addDocumentListener(new DocumentListener() { //for filtering
			
				public void insertUpdate(DocumentEvent e) {newFilter();}
				public void removeUpdate(DocumentEvent e) {newFilter();}
				public void changedUpdate(DocumentEvent e) {newFilter();}
			});
		
		
		
        this.setVisible(true);
	}
	
	
	public JTable getJTable() //return table
	{
		return this.table ;
	}
	public int getCurrRow() //return the last row we clicked
	{
		return table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()); 
	}

	public StatCenterTable getmodel() //return model
	{
		return this.model;
	}
	

	private void newFilter() //filter by column and text
	{
		int currentColumn = ColumnComboBox.getColumn();
		
			try {sorter.setRowFilter(RowFilter.regexFilter(tf1.getText(),currentColumn));
		}catch(PatternSyntaxException e) {
		
		}
		
		
	}
	public void newFilter1(String name)  //open from graphic map the clicked settle
	{
		try {sorter.setRowFilter(RowFilter.regexFilter(name,0));
		}catch(PatternSyntaxException e) {
			
		}
	}
	public void fireTableDataChanged() { //for refresh data
		model.fireTableDataChanged();
	}
	
	
	
	
	
	
	
}
