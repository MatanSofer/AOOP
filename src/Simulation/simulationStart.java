package Simulation;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Country.Map;
import Country.Settlement;
import GUI.MainWindow;
import GUI.StatWindow;

public class simulationStart {

	//All the static files we are using.
		private Map map=new Map();
		private StatWindow statwindow;
		private MainWindow mainwindow;
		
		public simulationStart()
		{
			SwingUtilities.invokeLater(new Runnable() { // Doing GUI Changes on the AWT stack.
				public void run() {
					
					statwindow = new StatWindow(map);
					statwindow.setVisible(false);
					mainwindow = new MainWindow(statwindow,map);
					mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					statwindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				}
			});

			System.out.println("\n-------Simulating virus spread-------\n");
			simulate(); // Simulates virus spread.
		}
		
		public void simulate() {
			
			// Wait for a file to be uploaded or to resume
			while (!map.getPlay() || map.getStop()) // (I dont want the simulatin to run befor The GUI loads up.)
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			for (int p = 0; p >= 0; p++) { // Loops Forever.
					
					map.updateAll(mainwindow,statwindow);// Updates Gui
					while (!map.getPlay() || map.getStop())
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

				
				
					for(Settlement settlement : map.getSettlements()) {
						settlement.simulateMapContagious();
					}
					for(Settlement settlement : map.getSettlements()) {
						settlement.simulateMapRecover();
					}
					for(Settlement settlement : map.getSettlements()) {
						settlement.simulateMoveSettle();
					}
					for(Settlement settlement : map.getSettlements()) {
						settlement.vaccinateMap();
					}
					for(Settlement settlement : map.getSettlements()) {
						settlement.killPeople();
				}
				
					
			}

		}
		
		
		
		
		
}
