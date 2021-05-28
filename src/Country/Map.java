//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Country;
import java.util.*;

import javax.swing.SwingUtilities;

import GUI.MainWindow;
import GUI.StatWindow;
import Simulation.Main;


public class Map {

	private Settlement[] settlements; // field include all settles
	private Map map;
	private boolean playing = false;
	private boolean stop = true;
	
	
	
	
	
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
	public void setMap(Map map)
	{
		this.map=map;
		this.settlements = new Settlement[map.getSettlements().length];
		for (int i = 0; i < map.getSettlements().length; i++) {
			this.settlements[i] = map.getSettlements()[i];
		}
		
	}
	public Map getMap() {
		
		return this.map;
	}
	public Settlement[] getSettlements() // return the list of settlements
	{
		return this.settlements;
	}

	
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public boolean getPlay() {
		return playing;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public boolean getStop() {
		return stop;
	}
	public  void updateAll(MainWindow mainwindow , StatWindow statwindow) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainwindow.updateMap();
				statwindow.updateTable();
			}
		});

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
