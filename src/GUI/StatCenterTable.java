package GUI;
import javax.swing.table.AbstractTableModel;
import Country.*;

class StatCenterTable extends AbstractTableModel
{
	private  Map map;
	private final String[] columnNames = {"Settlement Name" , "Settlement Type"
		,"RamzorColor" , "Sick percentages" , "Vaccine dose number","Dead people Number"
		,"Current population number" ,"Sick people in settle" , "Healthy peopleIn settle"};
	
	
	public StatCenterTable(Map map)
	{
		this.map =map;
	}
		
	public String getColumnName(int column)	
	{
		return this.columnNames[column];
	}
	
	public int getRowCount()
	{
		return map.getSettlements().length;
	}

	
	public int getColumnCount() 
	{
		return 9;
	}


	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Settlement settlement = map.at(rowIndex);
		switch(columnIndex)
		{
			case 0: return settlement.getName();
			case 1: return settlement.getType();
			case 2: return settlement.getColor();
			case 3: return settlement.contagiousPercent();
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
		Settlement settlement = map.at(row);
		switch(col) {
		case 4:  settlement.setVaccineDose(i);

		}
		System.out.println(map.getSettlements()[row].getVaccineDose());
	}
	
	
	
	
	
}
