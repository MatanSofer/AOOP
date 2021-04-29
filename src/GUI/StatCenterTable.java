package GUI;
import javax.swing.table.AbstractTableModel;
import Country.*;
import Simulation.Main;

class StatCenterTable extends AbstractTableModel
{
	private final String[] columnNames = {"Settlement Name" , "Settlement Type"
		,"RamzorColor" , "Sick percentages" , "Vaccine dose number","Dead people Number"
		,"Current population number" ,"Sick people in settle" , "Healthy peopleIn settle"};
	
	
	public StatCenterTable()
	{
	}
		
	public String getColumnName(int column)	
	{
		return this.columnNames[column];
	}
	
	public int getRowCount()
	{
		return Main.getMap().getSettlements().length;
	}

	
	public int getColumnCount() 
	{
		return 9;
	}


	public Object getValueAt(int rowIndex, int columnIndex)
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
			case 8: return settlement.getHealthyPeopleSize();
		}
		return null;
		
	}
	
	
	public boolean isCellEditable(int rowIndex , int columnIndex)
	{
		return columnIndex >0 ;
	}
	
	public void setValueAt(String aValue , int row , int col)
	{
		
		System.out.println(row);
		System.out.println(col);
		int i=Integer.parseInt(aValue);  
		Settlement settlement = Main.getMap().at(row);
		switch(col) {
		case 4:  settlement.setVaccineDose(i);
			this.fireTableDataChanged();
		}
		System.out.println(Main.getMap().getSettlements()[row].getVaccineDose());
	}
	
	
	
	
	
}
