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

	public void simulate6Days() {
		Person temp;
		IVirus virus;
		List<Integer> NewSickIndexes = new ArrayList<Integer>(); // this helps us to recognize new sick people so we can
																	// to skip on them and dont let them try contage new people
		int numberofTryingToContagion; // number of contagion tries
		int sickCounter = 0; // count number of sick for each settle
		int randomPersonFronSettle; // this var will get a random value in range of( 0 to settle population)

		for (int p = 0; p < 100; p++) // the simulation should be ran 5 times
		{
			Clock.nextTick();// move the clock one tick forward
			
			//MainWindow.getGraphicMap().repaint();
			
			NewSickIndexes.clear();
			System.out.println("\"############################################   Simulation number : " + (p + 1)
					+ "    ############################################"); // the number of simulation
			for (int i = 0; i < this.getSettlements().length; i++) // run for each settle
			{

				numberofTryingToContagion = 0;
				for (int j = 0; j < this.getSettlements()[i].getPeople().size(); j++) // run for all the people in the settle
				{
					if (this.getSettlements()[i].getPeople().get(j) instanceof Sick
							&& NewSickIndexes.contains(j) == false) // 2 condition :people is sick , the sick man isnt a new sick.
					{
						for (int k = 0; k < 10; k++) // try 6 times
						{
							numberofTryingToContagion++;
							randomPersonFronSettle = (int) (Math.random() * this.getSettlements()[i].getPeople().size()); // random index in population

							virus = ((Sick) this.getSettlements()[i].getPeople().get(j)).getVirus(); // virus refernce to the virus of the sick
							if (virus.tryToContagion(this.getSettlements()[i].getPeople().get(j),
									this.getSettlements()[i].getPeople().get(randomPersonFronSettle))) // if the sick succeed to contage the second person
							{
								NewSickIndexes.add(randomPersonFronSettle); // add new sick index to the dynamic array
								temp = this.getSettlements()[i].getPeople().get(randomPersonFronSettle).contagion(virus); // temp person refernce to the new sick
								this.getSettlements()[i].getPeople().set(randomPersonFronSettle, temp); // switch between
																										// the refrences (same person with same details ,just became sick)
							}

						}

					}

				}
			
				System.out.println("----------------------");
				this.getSettlements()[i].setColor(this.getSettlements()[i].calculateRamzorGrade()); // setting the ramzor grade and color
				System.out.println("Number of trying in settle " + this.getSettlements()[i].getName()
						+ " that the sick population try to contagion are : " + numberofTryingToContagion); // printing number of tries
				System.out.println("New ramzor rating : " + this.getSettlements()[i].getRamzorRating()); // printing new ramzor grade
				System.out.println("New ramzor color : " + this.getSettlements()[i].getColor()); // printing new ramzor color
				
					
				sickCounter = 0;
				for (int j = 0; j < this.getSettlements()[i].getPeople().size(); j++) {
					if (this.getSettlements()[i].getPeople().get(j) instanceof Sick) {
						sickCounter++;
					}
				}
				System.out.println("After simulation " + (p + 1) + " Settlement  " + this.getSettlements()[i].getName()
						+ " Sick number are : " + sickCounter);
				
				this.getSettlements()[i].updateSickHealthy();
				
			}
			
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
