package GUI;
import javax.swing.table.AbstractTableModel;
import Country.*;
import Simulation.Main;

class StatCenterTable extends AbstractTableModel
{
	private final String[] columnNames = {"Settlement Name" , "Settlement Type"               //table column names
		,"RamzorColor" , "Sick percentages" , "Vaccine dose number","Dead people Number"
		,"Current population number" ,"Sick people in settle" , "Non-Sick peopleIn settle"};
	
	private Map map;
	public StatCenterTable(Map map)
	{
		this.map=map;
	}
	public String getColumnName(int column)	   //enter col name
	{
		
		return this.columnNames[column];
	}
	
	public int getRowCount()  //count rows by number of settles
	{
		if(map.getSettlements().length == 0)
		{
			return 0 ;
		}
		return map.getSettlements().length;
	}

	
	public int getColumnCount() //number of columns in table
	{
		return 9;
	}


	public Object getValueAt(int rowIndex, int columnIndex)  //enter data to table
	{
		
		if(map.getSettlements().length == 0){
			return 0;
		}
		Settlement settlement = map.at(rowIndex);
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
		Settlement settlement = map.at(row);
		switch(col) {
		case 4:  settlement.addVaccineDose(i);
			this.fireTableDataChanged();
		}
		
	}
}
