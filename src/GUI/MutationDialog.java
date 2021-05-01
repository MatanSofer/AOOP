package GUI;

import java.awt.Dimension;
import java.awt.Frame;

import java.awt.GridLayout;

import javax.swing.JCheckBox;

import javax.swing.JDialog;

import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import Virus.*;

public class MutationDialog extends JDialog {

	private String[] names = { "British", "Chinese", "SouthAfrican" };
	private JPanel mainpanel = new JPanel();
	private static Object[][] checkbox;
	public MutationDialog(Frame window) {
		super(window, "Mutations", true);
		setPreferredSize(new Dimension(499,111));
		setResizable(false);

		checkbox = new Boolean[3][3];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(i==0)
					checkbox[i][j]=Virus.SouthAfricanVariant.getMutations1()[j];
				if(i==1)
					checkbox[i][j]=Virus.ChineseVariant.getMutations1()[j];
				if(i==2)
					checkbox[i][j]=Virus.BritishVariant.getMutations1()[j];
				}
			}

		String[] columnNames = { "African", "Chinese", "British" };

		JTable mainTable = new JTable(checkbox, columnNames) {
			@Override
			public Class getColumnClass (int column) {
				   return Boolean.class;
				}
			
		};
		
		mainTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                for(int i=0;i<mainTable.getModel().getRowCount();i++)
                {
                  if ((Boolean) mainTable.getModel().getValueAt(i,0))
                  {  
                    System.out.println(">\t"+mainTable.getSelectedRow() +">\t"+ mainTable.getSelectedColumn());
                    
                    Boolean flag= (Boolean)checkbox[mainTable.getSelectedRow()][mainTable.getSelectedColumn()];
                    toggleMutation(mainTable.getSelectedRow(), mainTable.getSelectedColumn(), flag);
                    
                    
                    break;
                   
                  }
               }     
            }
  });
		
		
		JScrollPane scrollPane = new JScrollPane(mainTable);
		JTable rowTable = new RowNumberTable(mainTable);
		scrollPane.setRowHeaderView(rowTable);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());
		this.add(scrollPane);

		pack();
		setVisible(true);

	}
	

	 

	private void toggleMutation(int row, int column, boolean bool)
	{
			
		if(row == 0 && column == 0 )
			SouthAfricanVariant.setMutation(0, bool);
		if(row == 0 && column == 1 )
			SouthAfricanVariant.setMutation(1, bool);
		if(row == 0 && column == 2 )
			SouthAfricanVariant.setMutation(2, bool);
		
		if(row == 1 && column == 0 )
			ChineseVariant.setMutation(0, bool);
		if(row == 1 && column == 1 )
			ChineseVariant.setMutation(1, bool);
		if(row == 1 && column == 2 )
			ChineseVariant.setMutation(2, bool);
		
		if(row == 2 && column == 0 )
			BritishVariant.setMutation(0, bool);
		if(row == 2 && column == 1 )
			BritishVariant.setMutation(1, bool);
		if(row == 2 && column == 2 )
			BritishVariant.setMutation(2, bool);
		
		
		System.out.println("SouthAfricanVariant");
		for(int i = 0 ; i < 3 ;i++)
		{
			System.out.println(SouthAfricanVariant.getMutations1()[i]);		
		}
		System.out.println("ChineseVariant");
		for(int i = 0 ; i < 3 ;i++)
		{		
		System.out.println(ChineseVariant.getMutations1()[i]);
		}
		System.out.println("BritishVariant");
		for(int i = 0 ; i < 3 ;i++)
		{
		System.out.println(BritishVariant.getMutations1()[i]);
		}
//		if (virus == names[0])
//			BritishVariant.setMutation(mutation, bool);
//		else if (virus == names[1])
//			ChineseVariant.setMutation(mutation, bool);
//		else if (virus == names[2])
//			SouthAfricanVariant.setMutation(mutation, bool);
	}

}