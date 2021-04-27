//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Simulation;
import java.awt.FileDialog;        
import java.awt.Frame;
import java.io.File;

import javax.swing.JFrame;

import Country.*;
import GUI.*;
import IO.SimulationFile;

public class Main {
	
	private static Map map = new Map();

	public static void main(String[] args) {
		
		
		
		
		SimulationFile simulationfile = new SimulationFile(loadFileFunc());
		map = simulationfile.getMap(); //return value from simulation ; reference to map.
		System.out.println(map); //print all settle string
		
		MainWindow mainwindow = new MainWindow();
		StatWindow statwindow = new StatWindow(map);
		
		map.SickPplInnit();//Initialize 1% (infect population).
		System.out.println("\n-------In the first intiallization : -------\n");
		map.printSickPpl();//Print sick population.
		System.out.println("\n-------Simulating virus spread in 6 days-------\n");
		map.simulate6Days();//Simulates virus spread in 6 days.
		System.out.println("\n--------------------------");
		System.out.println("END OF SIMULATION RESULT\n");
		map.printSickPpl();//Print sick population.
		
		statwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static File loadFileFunc() 
	{
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
	

}
