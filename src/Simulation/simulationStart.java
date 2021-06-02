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
					
					statwindow = StatWindow.getInstance(map);	//create stat window and hiding it
					statwindow.setVisible(false);
					mainwindow = MainWindow.getInstance(statwindow,map); //create main window
					mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					statwindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				}
			});

			System.out.println("\n-------Simulating virus spread-------\n");
		
		}
		

		
		
		
		
}
