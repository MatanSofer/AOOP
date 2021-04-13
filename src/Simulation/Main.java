package Simulation;
import java.awt.FileDialog;        
import java.awt.Frame;
import java.io.File;
import Country.*;
import IO.SimulationFile;

public class Main {

	public static void main(String[] args) {
		
		SimulationFile simulationfile = new SimulationFile(loadFileFunc() );
		Map map = simulationfile.getMap(); //return value from simulation ; reference to map.
		System.out.println(map); //print all settle string
		map.SickPplInnit();//Initialise 1% (infect population).
		System.out.println("\n-------In the first intiallization : -------\n");
		map.printSickPpl();//Print sick population.
		System.out.println("\n-------Simulating virus spread in 6 days-------\n");
		map.simulate6Days();//Simulates virus spread in 6 days.
		System.out.println("\n--------------------------");
		System.out.println("END OF SIMULATION RESULT\n");
		map.printSickPpl();//Print sick population.
		
		
    
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
