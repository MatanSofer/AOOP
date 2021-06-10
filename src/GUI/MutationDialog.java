package GUI;

import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import Country.Map;
import Virus.*;

public class MutationDialog extends JDialog {

	private String[] names = { "Chinese","British","SouthAfrican" };
	private JPanel mainpanel = new JPanel();
	private static Object[][] checkbox;
	
	
	
	
	public MutationDialog(Frame window ) {      //opens dialog mutation frame
		super(window, "Mutations", true);
		setPreferredSize(new Dimension(499,111));
		setResizable(false);

		checkbox = new Boolean[3][3]; //boolean values for optional mutations
		
		for (int i = 0; i < 3; i++) {        //when we open frame we get current mutation values
			for (int j = 0; j < 3; j++) {
					checkbox[i][j]=VirusManager.getData()[i][j];
				}
			}

		String[] columnNames = { "Chinese", "British","African" };

		JTable mainTable = new JTable(checkbox, columnNames) {    //set them to boolean values
			public Class getColumnClass (int column) {
				   return Boolean.class;
				}
			
		};
		
		mainTable.getModel().addTableModelListener(new TableModelListener() {  //reaction to click on box and send it to function who change actual values
            public void tableChanged(TableModelEvent e) {
                for(int i=0;i<mainTable.getModel().getRowCount();i++)
                {
                 {  
                    Boolean flag= (Boolean)checkbox[mainTable.getSelectedRow()][mainTable.getSelectedColumn()];
                    toggleMutation(mainTable.getSelectedRow(), mainTable.getSelectedColumn(), flag);
                    break;
                 }
               }     
            }
            
            
  });
		
		
		JScrollPane scrollPane = new JScrollPane(mainTable); //add all to frame
		JTable rowTable = new RowNumberTable(mainTable);
		scrollPane.setRowHeaderView(rowTable);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());
		this.add(scrollPane);

		pack();
		setVisible(true);

	}
	

	 

	private void toggleMutation(int row, int column, boolean bool) //update mutation in varients classes
	{

		if (VirusManager.getData()[row][column] != bool) //by virus manager class
			VirusManager.Change(row, column);
		
		
	}

}