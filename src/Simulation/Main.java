//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Simulation;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Country.*;
import GUI.*;
import IO.SimulationFile;
import Population.*;
import Virus.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Main {

	private static Map map = new Map();
	public static StatWindow statwindow;
	public static MainWindow mainwindow;
	private static boolean playing = false;
	private static boolean stop = true;

	public static void main(String[] args) {

//		SimulationFile simulationfile = new SimulationFile(loadFileFunc());
//		map = simulationfile.getMap(); //return value from simulation ; reference to map.
//		System.out.println(map); //print all settle string

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				statwindow = new StatWindow();
				statwindow.setVisible(false);
				mainwindow = new MainWindow(statwindow);
			}
		});

//		map.SickPplInnit();//Initialize 1% (infect population).
//		System.out.println("\n-------In the first intiallization : -------\n");
		map.printSickPpl();// Print sick population.
		System.out.println("\n-------Simulating virus spread-------\n");
		simulate();// Simulates virus spread.
		System.out.println("\n--------------------------");
		System.out.println("END OF SIMULATION RESULT\n");
		map.printSickPpl();// Print sick population.

		statwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static File loadFileFunc() {
		FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
		fd.setVisible(true);
		if (fd.getFile() == null)
			return null;
		File f = new File(fd.getDirectory(), fd.getFile());
		System.out.println(f.getPath());
		return f;
	}

	public static Map getMap() {
		return map;
	}

	public static void setMap(Map newMap) {
		map = newMap;
	}

	public static void simulate() {
		Person temp;
		IVirus virus;
		List<Integer> NewSickIndexes = new ArrayList<Integer>(); // this helps us to recognize new sick people so we can
																	// to skip on them and dont let them try contage new
																	// people
		int numberofTryingToContagion; // number of contagion tries
		int sickCounter = 0; // count number of sick for each settle
		int randomPersonFronSettle; // this var will get a random value in range of( 0 to settle population)
		int sliderValue;

		// Wait for a file to be uploaded or to resume
		while (!playing || stop)
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		for (int p = 0; p >= 0; p++) {
			
			updateAll();//Updates Gui
			
			// Wait for a file to be re-uploaded or to resume
			while (!playing || stop)
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				Thread.sleep(250 * MainWindow.getSliderValue());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Clock.nextTick();// move the clock one tick forward
			

			NewSickIndexes.clear();

			System.out.println("\"############################################   Simulation number : " + (p + 1)
					+ "    ############################################"); // the number of simulation

			// for each sattle check 0.2 sick people and each one from sick people will try
			// to contagion 5 health people
			simulateMapContagious();
			// for each settle all sick people who past 25 days from the day they were
			// nidbeku become cov...
			simulateMapRecover();
			
			
			
			
			// for each settle we have to check 0.03 from people who try to move to a
			// connected settle and to transfer them
			simulateMoveSettle();
			// if settle has more then 0 doses and also health people we have to vaccine
			// them and decrease this.doses;(1 is enough)

			
			
			
			
			
			
			
//			
//			for (int i = 0; i < getMap().getSettlements().length; i++) // run for each settle
//			{
//				for (int j = 0; j < getMap().getSettlements()[i].getsickPeople().size(); j++) // run for all the people in the settle
//				{
//					virus = ((Sick) getMap().getSettlements()[i].getsickPeople().get(j)).getVirus();
//						for(int k = 0 ; k < 3; k++ )
//						{
//							virus.tryToContagion(getMap().getSettlements()[i].getsickPeople().get(j),
//								getMap().getSettlements()[i].gethealthygetPeople().get(k));
//						}
//					
//				}
//			}
//			
//			
//			

//			NewSickIndexes.clear();
//			System.out.println("\"############################################   Simulation number : " + (p + 1)
//					+ "    ############################################"); // the number of simulation
//			for (int i = 0; i < getMap().getSettlements().length; i++) // run for each settle
//			{
//
//				numberofTryingToContagion = 0;
//				for (int j = 0; j < getMap().getSettlements()[i].getPeople().size(); j++) // run for all the people in the settle
//				{
//					if (getMap().getSettlements()[i].getPeople().get(j) instanceof Sick
//							&& NewSickIndexes.contains(j) == false) // 2 condition :people is sick , the sick man isnt a new sick.
//					{
//						for (int k = 0; k < 10; k++) // try 6 times
//						{
//							numberofTryingToContagion++;
//							randomPersonFronSettle = (int) (Math.random() * getMap().getSettlements()[i].getPeople().size()); // random index in population
//
//							virus = ((Sick) getMap().getSettlements()[i].getPeople().get(j)).getVirus(); // virus refernce to the virus of the sick
//							if (virus.tryToContagion(getMap().getSettlements()[i].getPeople().get(j),
//									getMap().getSettlements()[i].getPeople().get(randomPersonFronSettle))) // if the sick succeed to contage the second person
//							{
//								NewSickIndexes.add(randomPersonFronSettle); // add new sick index to the dynamic array
//								temp = getMap().getSettlements()[i].getPeople().get(randomPersonFronSettle).contagion(virus); // temp person refernce to the new sick
//								getMap().getSettlements()[i].getPeople().set(randomPersonFronSettle, temp); // switch between
//																										// the refrences (same person with same details ,just became sick)
//							}
//
//						}
//
//					}
//
//				}
//			
//				System.out.println("----------------------");
//				getMap().getSettlements()[i].setColor(getMap().getSettlements()[i].calculateRamzorGrade()); // setting the ramzor grade and color
//				System.out.println("Number of trying in settle " + getMap().getSettlements()[i].getName()
//						+ " that the sick population try to contagion are : " + numberofTryingToContagion); // printing number of tries
//				System.out.println("New ramzor rating : " + getMap().getSettlements()[i].getRamzorRating()); // printing new ramzor grade
//				System.out.println("New ramzor color : " + getMap().getSettlements()[i].getColor()); // printing new ramzor color
//				
//					
//				sickCounter = 0;
//				for (int j = 0; j <getMap().getSettlements()[i].getPeople().size(); j++) {
//					if (getMap().getSettlements()[i].getPeople().get(j) instanceof Sick) {
//						sickCounter++;
//					}
//				}
//				System.out.println("After simulation " + (p + 1) + " Settlement  " + getMap().getSettlements()[i].getName()
//						+ " Sick number are : " + sickCounter);
//				
//				getMap().getSettlements()[i].updateSickHealthy();
//				
//			}

		}

	}

	public static void updateAll() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainwindow.updateMap();
				statwindow.updateTable();
			}
		});
	}

	public static void setPlaying(boolean playing) {
		Main.playing = playing;
	}

	public static boolean getPlay() {
		return playing;
	}

	public static void setStop(boolean stop) {
		Main.stop = stop;
	}

	public static boolean getStop() {
		return stop;
	}
	
	private static void simulateMapContagious() {
		IVirus virus;
		Person newSicko;
		for (int i = 0; i < getMap().getSettlements().length; i++) // run for each settle
		{
			for (int j = 0; j < getMap().getSettlements()[i].getSickPeople().size()*0.2; j++) // run for 20% of people in the settle
			{
				virus = ((Sick) getMap().getSettlements()[i].getSickPeople().get(j)).getVirus();
					for(int k = 0 ; k < 3; k++ )
					{
						if(virus.tryToContagion(getMap().getSettlements()[i].getSickPeople().get(j), getMap().getSettlements()[i].getNonSickPeople().get(k)))
						{
							newSicko=getMap().getSettlements()[i].getNonSickPeople().get(k).contagion(virus);
							getMap().getSettlements()[i].addPerson(newSicko);
							getMap().getSettlements()[i].getNonSickPeople().remove(k);
						}
					}
				
			}
		}

	}
	// for each settle all sick people who past 25 days from the day they were
	// nidbeku become cov...
	private static void  simulateMapRecover()
	{
		Person conv;
		for (int i = 0; i < getMap().getSettlements().length; i++) // run for each settle
		{
			
			for(int j = 0 ; j  < getMap().getSettlements()[i].getSickPeopleSize();j++)		
			{
				if(((Sick)getMap().getSettlements()[i].getSickPeople().get(j)).daysFromContagion()  > 25 )
				{
					conv = ((Sick)getMap().getSettlements()[i].getSickPeople().get(j)).recover();
					getMap().getSettlements()[i].addPerson(conv );
					getMap().getSettlements()[i].getSickPeople().remove(j);
					
				}
			}
		}
	}

	// for each settle we have to check 0.03 from people who try to move to a
	// connected settle and to transfer them
	
	
	private static void simulateMoveSettle()
	{
		int halfFromArray;
		int index,randomSettle;
		Person inSickArray,inOtherArray;
		Settlement des1 , des2;
		for (int i = 0; i < getMap().getSettlements().length; i++) // run for each settle
		{
			
			for(int j = 0 ; j  < getMap().getSettlements()[i].getPeopleSize()*0.03;j++)		
			{
				halfFromArray= (int)(getMap().getSettlements()[i].getPeopleSize()*0.03*0.5);
				
				if(getMap().getSettlements()[i].getconnectedSettlements().size() > 0)
				{
					randomSettle = (int)(Math.random()*getMap().getSettlements()[i].getconnectedSettlements().size());
					for(int k = 0 ; k < halfFromArray ; k++)
					{
						inSickArray = getMap().getSettlements()[i].getSickPeople().get(k);
						des1 = getMap().getSettlements()[i].getconnectedSettlements().get(randomSettle);
						
						inOtherArray = getMap().getSettlements()[i].getNonSickPeople().get(randomSettle);
						des2 = getMap().getSettlements()[i].getconnectedSettlements().get(randomSettle);
						
						if(getMap().getSettlements()[i].transferPerson(inSickArray ,des1 ))
						{
							getMap().getSettlements()[randomSettle]
						}
						if(getMap().getSettlements()[i].transferPerson(inOtherArray, des2 ))
						{
							
						}
						
					}
				}
				
				
				
			}
		}
	}
}


