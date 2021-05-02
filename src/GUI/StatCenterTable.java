package GUI;
import javax.swing.table.AbstractTableModel;
import Country.*;
import Simulation.Main;

class StatCenterTable extends AbstractTableModel
{
	private final String[] columnNames = {"Settlement Name" , "Settlement Type"               //table column names
		,"RamzorColor" , "Sick percentages" , "Vaccine dose number","Dead people Number"
		,"Current population number" ,"Sick people in settle" , "Non-Sick peopleIn settle"};
	
	
		
	public String getColumnName(int column)	   //enter col name
	{
		return this.columnNames[column];
	}
	
	public int getRowCount()  //count rows by number of settles
	{
		return Main.getMap().getSettlements().length;
	}

	
	public int getColumnCount() //number of columns in table
	{
		return 9;
	}


	public Object getValueAt(int rowIndex, int columnIndex)  //enter data to table
	{
		Settlement settlement = Main.getMap().at(rowIndex);
		switch(columnIndex)
		{
			case 0: return settlement.getName();
			case 1: return settlement.getType();
			case 2: return settlement.getColor().toString();
			case 3: return settlement.contagiousPrecentString();
			case 4: return settlement.getVaccineDose();
			case 5: return settlement.getdeadPopulation();
			case 6: return settlement.getPeopleSize();
			case 7: return settlement.getSickPeopleSize();
			case 8: return settlement.getNonSickPeopleSize();
		}
		return null;
		
	}
	
	
	public boolean isCellEditable(int rowIndex , int columnIndex)
	{
		return columnIndex >0 ;
	}
	
	public void setValueAt(String aValue , int row , int col) //to set values in table
	{
		
	
		int i=Integer.parseInt(aValue);  
		Settlement settlement = Main.getMap().at(row);
		switch(col) {
		case 4:  settlement.addVaccineDose(i);
			this.fireTableDataChanged();
		}
		
	}
	
	
	
	
	
}
