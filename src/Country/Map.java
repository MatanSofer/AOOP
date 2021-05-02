//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Country;
import java.util.*;
import GUI.MainWindow;
import Population.*;
import Simulation.Clock;
import Virus.*;

public class Map {

	private Settlement[] settlements; // field include all settles

	public Map(List<Settlement> other) // constructor get a dynamic array but we convert it to static array(as field type)
	{
		this.settlements = new Settlement[other.size()];
		for (int i = 0; i < other.size(); i++) {
			this.settlements[i] = other.get(i);
		}
	}
	
	public Map()
	{
		this.settlements = new Settlement[0];
	}

	public Settlement[] getSettlements() // return the list of settlements
	{
		return this.settlements;
	}

	public boolean isEqual(Map other) // is equal
	{
		if (this.getSettlements().length != (other.getSettlements().length))
			return false;
		for (int i = 0; i <= this.getSettlements().length; i++) {
			if (!this.getSettlements()[i].isEqual(other.getSettlements()[i]))
				return false;
		}
		return true;
	}


	public void printSickPpl() {

		// check number of sick for each settle , then print it .
		int sickCounter;

		for (int i = 0; i < this.getSettlements().length; i++) {
			sickCounter = this.getSettlements()[i].getSickPeopleSize();
			System.out
			.println("Settlement " + this.getSettlements()[i].getName() + " Sick number are : " + sickCounter);

		}
	}

	

	public String toString() {
		String settlementlist = "-------toString for all settlements-------\n";
		for (int i = 0; i < this.settlements.length; i++) 
		{
			
			settlementlist +=settlements[i].toString()+"\n";
		}
		return settlementlist;
	}

	public Settlement at(int rowIndex) 
	{
		
		return this.getSettlements()[rowIndex];
	}
	
	

}
