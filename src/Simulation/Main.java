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

	public static void main(String[] args) {
		
		
		
		
		SimulationFile simulationfile = new SimulationFile(loadFileFunc());
		Map map = simulationfile.getMap(); //return value from simulation ; reference to map.
		System.out.println(map); //print all settle string
		map.SickPplInnit();//Initialize 1% (infect population).
		System.out.println("\n-------In the first intiallization : -------\n");
		map.printSickPpl();//Print sick population.
		System.out.println("\n-------Simulating virus spread in 6 days-------\n");
		map.simulate6Days();//Simulates virus spread in 6 days.
		System.out.println("\n--------------------------");
		System.out.println("END OF SIMULATION RESULT\n");
		map.printSickPpl();//Print sick population.
		MainWindow mainwindow = new MainWindow(map);
		StatWindow statwindow = new StatWindow(map);
		statwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static File loadFileFunc() 
	{
        FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null)
            return null;
        File f = new File(fd.getDirectory(), fd.getFile());
        System.out.println(f.getPath());
        return f;
    }

}
