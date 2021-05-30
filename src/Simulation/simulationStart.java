package Simulation;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Country.Map;
import Country.Settlement;
import GUI.MainWindow;
import GUI.StatWindow;

public class simulationStart {

	    
		private Map map=new Map();
		private StatWindow statwindow;
		private MainWindow mainwindow;
		
		public simulationStart() //when we are calling constructor 
		{
			SwingUtilities.invokeLater(new Runnable() { // Doing GUI Changes on the AWT stack.
				public void run() {
					
					statwindow = new StatWindow(map);	//create stat window and hiding it
					statwindow.setVisible(false);
					mainwindow = new MainWindow(statwindow,map); //create main window
					mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					statwindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				}
			});

			System.out.println("\n-------Simulating virus spread-------\n");
		
		}
		

		
		
		
		
}
