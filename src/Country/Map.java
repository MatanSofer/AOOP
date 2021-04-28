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

	public void SickPplInnit() {
		// Cpuntagious innitialization
		int settleNumber = this.settlements.length;
		Settlement settle;
		int settleSize;
		Person temp;

		for (int i = 0; i < settleNumber; i++) // run for each settle .
		{
			settle = this.settlements[i];
			settleSize = settle.getPeople().size();

			for (int j = 0; j < (int) (settleSize * 0.01); j++) // make 1 percent of them as sick people .
			{ // the modulo allowed us to set the viruses random.
				if (j % 3 == 0) {
					temp = settle.getPeople().get(j).contagion(new SouthAfricanVariant());
					settle.getPeople().set(j, temp);
				} else if (j % 3 == 1) {
					temp = settle.getPeople().get(j).contagion(new ChineseVariant());
					settle.getPeople().set(j, temp);
				} else {
					temp = settle.getPeople().get(j).contagion(new BritishVariant());
					settle.getPeople().set(j, temp);
				}
			}
			this.getSettlements()[i].setColor(this.getSettlements()[i].calculateRamzorGrade()); // setting the ramzor grade and color

		}
	}

	public void printSickPpl() {

		// check number of sick for each settle , then print it .
		int sickCounter;

		for (int i = 0; i < this.getSettlements().length; i++) {
			sickCounter = 0;
			for (int j = 0; j < this.getSettlements()[i].getPeople().size(); j++) {
				if (this.getSettlements()[i].getPeople().get(j) instanceof Sick) {
					sickCounter++;
				}
			}
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
	
	public void SimulationV2() {
		
		
	}

}
