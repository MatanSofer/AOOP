//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Simulation;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import Country.*;
import GUI.*;
import Population.*;
import Virus.*;
import java.util.List;

public class Main {

//All the static files we are using.
	private static Map map = new Map();
	private static StatWindow statwindow;
	private static MainWindow mainwindow;
	private static boolean playing = false;
	private static boolean stop = true;

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() { // Doing GUI Changes on the AWT stack.
			public void run() {
				statwindow = new StatWindow();
				statwindow.setVisible(false);
				mainwindow = new MainWindow(statwindow);
				mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				statwindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});

		System.out.println("\n-------Simulating virus spread-------\n");
		simulate(); // Simulates virus spread.

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

		// Wait for a file to be uploaded or to resume
		while (!playing || stop) // (I dont want the simulatin to run befor The GUI loads up.)
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		for (int p = 0; p >= 0; p++) { // Loops Forever.
			updateAll();// Updates Gui
			while (!playing || stop)
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			try {
				Thread.sleep(250 * MainWindow.getSliderValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Clock.nextTick();// move the clock one tick forward
			System.out.println("\"############################################   Simulation number : " + (p + 1)
					+ "    ############################################"); // the number of simulation

			simulateMapContagious();
			simulateMapRecover();
			simulateMoveSettle();
			
			for(Settlement settlement : map.getSettlements()) {
				settlement.killPeople();
			}
			
			vaccinateMap();
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
			for (int j = 0; j < getMap().getSettlements()[i].getSickPeople().size() * 0.2; j++) // run for 20% of people
																								// in the settle
			{
				virus = ((Sick) getMap().getSettlements()[i].getSickPeople().get(j)).getVirus();
				for (int k = 0; k < 3; k++) {
					if (getMap().getSettlements()[i].getNonSickPeople().size()+1 < k )
						if (virus.tryToContagion(getMap().getSettlements()[i].getSickPeople().get(j),getMap().getSettlements()[i].getNonSickPeople().get(k))) {
							newSicko = getMap().getSettlements()[i].getNonSickPeople().get(k).contagion(virus);
							getMap().getSettlements()[i].addPerson(newSicko);
							getMap().getSettlements()[i].getNonSickPeople().remove(k);
							getMap().getSettlements()[i].setColor(getMap().getSettlements()[i].calculateRamzorGrade());
						}
				}

			}

		}

	}

	private static void simulateMapRecover() {
		// for each settle all sick people who past 25 days from the day they were

		Person conv;
		for (int i = 0; i < getMap().getSettlements().length; i++) // run for each settle
		{

			for (int j = 0; j < getMap().getSettlements()[i].getSickPeopleSize(); j++) {
				if (((Sick) getMap().getSettlements()[i].getSickPeople().get(j)).daysFromContagion() > 25) {
					conv = ((Sick) getMap().getSettlements()[i].getSickPeople().get(j)).recover();
					getMap().getSettlements()[i].addPerson(conv);
					getMap().getSettlements()[i].getSickPeople().remove(j);
					getMap().getSettlements()[i].setColor(getMap().getSettlements()[i].calculateRamzorGrade());

				}
			}
		}
	}

	private static void simulateMoveSettle() {
		// for each settle we have to check 0.03 from people who try to move to a
		// connected settle and to transfer them
		int size;
		int randomSettleIndex;
		List<Person> currentPpl;
		Settlement currentDestenation;
		for (Settlement settlement : getMap().getSettlements()) {
			if (settlement.getconnectedSettlements().size() > 0) {
				size = (int) (settlement.getSickPeopleSize() * 0.3);
				for (int i = 0; i < size; i++) { // For 30% of sick ppl
					currentPpl = settlement.getSickPeople();
					randomSettleIndex = ((int) ((Math.random()) * settlement.getconnectedSettlements().size()));
					currentDestenation = settlement.getconnectedSettlements().get(randomSettleIndex);
					if (currentPpl.get(i) != null)
						if (currentDestenation.transferPerson(currentPpl.get(i), settlement)) {// Attempts to transfer
																								// (Boolean)
							currentDestenation.addPerson(currentPpl.get(i));
							currentPpl.remove(i);
							settlement.setColor(settlement.calculateRamzorGrade());
						}
				}
				size = (int) (settlement.getNonSickPeopleSize() * 0.3);
				for (int i = 0; i < size; i++) { // For 30% of Non-sick ppl
					currentPpl = settlement.getNonSickPeople();
					randomSettleIndex = ((int) ((Math.random()) * settlement.getconnectedSettlements().size()));
					currentDestenation = settlement.getconnectedSettlements().get(randomSettleIndex);
					if (currentPpl.get(i) != null)
						if (currentDestenation.transferPerson(currentPpl.get(i), settlement)) {// Attempts to transfer
																								// (Boolean)
							currentDestenation.addPerson(currentPpl.get(i));
							currentPpl.remove(i);
							settlement.setColor(settlement.calculateRamzorGrade());
						}
				}
			}
		}
	}

	private static void vaccinateMap() {
		for (Settlement settlement : getMap().getSettlements()) {
			if (settlement.getNonSickPeopleSize() > 0 && settlement.getVaccineDose() > 0) {
				for (int i = 0; i < settlement.getNonSickPeopleSize(); i++)
					if (settlement.getNonSickPeopleSize() > 0 && settlement.getVaccineDose() > 0)
						if (settlement.getNonSickPeople().get(i) instanceof Healthy) {
							settlement.addPerson(((Healthy) settlement.getNonSickPeople().get(i)).vaccinate());
							settlement.getNonSickPeople().remove(settlement.getNonSickPeople().get(i));
							settlement.reduceOneVaccineDose();
						}

			}
		}

	}

}
