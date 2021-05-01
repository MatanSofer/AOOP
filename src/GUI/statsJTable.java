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
	
	
	
	
	
	public statsJTable(Map map ,JTextField tf1)
	{
		
		model = new StatCenterTable();
		table = new JTable(model);
		this.tf1=tf1;
		table.setRowSorter(sorter = new TableRowSorter<StatCenterTable>(model));
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(1010,100));
		table.setBackground(Color.lightGray);
		table.setFillsViewportHeight(true);
		this.add(new JScrollPane(table));
		
		this.add(tf1 );
		tf1.setToolTipText("FILTER NAME COLUMN");
		tf1.getDocument().addDocumentListener(new DocumentListener() {
			
				public void insertUpdate(DocumentEvent e) {newFilter();}
				public void removeUpdate(DocumentEvent e) {newFilter();}
				public void changedUpdate(DocumentEvent e) {newFilter();}
			});
		
		
		
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
	public int getCurrRow()
	{
		return table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()); ///Problamatic << matan Fix this plz
	}
//	public JTable getCurrTable()
//	{
//	//	return table.getRowSorter().convertRowIndexToModel(table.getSelectedRow());
//	}
	public StatCenterTable getmodel()
	{
		return this.model;
	}
	

	private void newFilter()
	{
		try {sorter.setRowFilter(RowFilter.regexFilter(tf1.getText(),ColumnComboBox.getColumn()));
		}catch(PatternSyntaxException e) {
			//wont update
		}
	}
	public void newFilter1(String name)
	{
		try {sorter.setRowFilter(RowFilter.regexFilter(name,0));
		}catch(PatternSyntaxException e) {
			//wont update)
		}
	}
	public void fireTableDataChanged() {
		// TODO Auto-generated method stub
		model.fireTableDataChanged();
	}
	
	
	
	
	
	
	
}
