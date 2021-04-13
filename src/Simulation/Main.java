package Simulation;
import Virus.*;
import java.awt.FileDialog;        
import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Country.*;
import IO.SimulationFile;
import Population.*;

public class Main {

	public static void main(String[] args) {
		
		
		//File file = new File("MaorMatanPath.txt"); //create a file and send file object simulation.
		SimulationFile simulationfile = new SimulationFile(loadFileFunc() );
		Map map = simulationfile.getMap(); //return value from simulation reference to map.
//		int settleNumber=map.getSettlements().length; 
//		Settlement settle;  
//		int settleSize;
//		Person temp;
//		int sickCounter=0;
		
		map.SickPplInnit();

//		for(int i = 0 ; i < settleNumber ;i++) //run for each settle .
//		{
//			settle = map.getSettlements()[i];
//			settleSize = settle.getPeople().size();
//			
//			for(int j = 0 ; j < (int)(settleSize*0.01) ; j++ ) //make 1 percent of them as sick people .
//			{                                                 //the modulo allowed us to set the viruses random.
//				if(j%3==0)
//				{
//					temp = settle.getPeople().get(j).contagion(new SouthAfricanVariant());
//					settle.getPeople().set(j,temp);
//				}
//				else if(j%3==1)
//				{
//					temp = settle.getPeople().get(j).contagion(new ChineseVariant());
//					settle.getPeople().set(j,temp);
//				}
//				else 
//				{
//					temp = settle.getPeople().get(j).contagion(new BritishVariant());
//					settle.getPeople().set(j,temp);
//				}
//			}
//			
//		}



		
        //check number of sick for each settle , then print it . 
		System.out.println("-------In the first intiallization : -------");
//		for(int i = 0 ;i < settleNumber ; i++)
//		{
//			sickCounter=0;
//			for(int j = 0; j < map.getSettlements()[i].getPeople().size() ;j++)
//			{
//				if(map.getSettlements()[i].getPeople().get(j) instanceof Sick)
//				{
//					sickCounter++;
//				}
//			}
//			System.out.println("Settlement " + map.getSettlements()[i].getName() +" Sick number are : " + sickCounter);
//			
//		}

		map.printSickPpl();
	
		

//		List<Integer> NewSickIndexes = new ArrayList<Integer>(); // this helps us to recognize new sick people so we can to skip on them and dont let them try contage new people
//		int numberofTryingToContagion;     // number of contagion tries
//		sickCounter=0;     // count number of sick for each settle
//		int randomPersonFronSettle;     //this var will get a random value in range of( 0  to settle population)
//		IVirus virus;
//		for(int p = 0 ; p < 5 ; p++)   // the simulation should be ran 5 times
//		{
//			
//			System.out.println("\"############################################   Simulation number : " +(p+1) +"    ############################################");   //the number of simulation
//			for(int i = 0 ; i < settleNumber ;i++)  // run for each settle
//			{
//
//				 numberofTryingToContagion = 0 ;
//				for(int j = 0; j < map.getSettlements()[i].getPeople().size() ;j++) // run for all the people in the settle
//				{
//				 
//					if(map.getSettlements()[i].getPeople().get(j) instanceof Sick && NewSickIndexes.contains(j)==false) //2 condition :people is sick , the sick man isnt a new sick.
//					{
//						
//						for(int k = 0 ; k < 6 ; k++) //try 6 times
//						{
//							numberofTryingToContagion++;
//							randomPersonFronSettle = (int)(Math.random()*map.getSettlements()[i].getPeople().size()); //random index in population
//							//System.out.println("Random man settle " +(i+1)+ " is index : " + randomPersonFronSettle); //for checking the random indexes
//							
//							virus = ((Sick)map.getSettlements()[i].getPeople().get(j)).getVirus(); //virus refernce to the virus of the sick 
//							if(virus.tryToContagion(map.getSettlements()[i].getPeople().get(j) , map.getSettlements()[i].getPeople().get(randomPersonFronSettle))) //if the sick succeed to contage the second person
//							{
//								NewSickIndexes.add(randomPersonFronSettle); //add new sick index to the dynamic array
//								temp = map.getSettlements()[i].getPeople().get(randomPersonFronSettle).contagion(virus); //temp person refernce to the new sick 
//								map.getSettlements()[i].getPeople().set(randomPersonFronSettle, temp); //switch between the refrences (same person with same details ,just became sick)
//							}
//							
//						}
//						
//					}
//					
//				}
//
//				System.out.println("----------------------");
//				map.getSettlements()[i].setColor(map.getSettlements()[i].calculateRamzorGrade()); //setting the ramzor grade and color
//				System.out.println("Number of trying in settle "+ map.getSettlements()[i].getName()+ " that the sick population try to contagion are : "+numberofTryingToContagion); //printing number of tries
//				System.out.println("New ramzor rating : "+ map.getSettlements()[i].getRamzorRating()); //printing new ramzor grade
//				System.out.println("New ramzor color : "+ map.getSettlements()[i].getColor()); //printing new ramzor color
//				for(int k = 0 ;k < settleNumber ; k++) //check in the end of simulation how many sick people
//				Clock.nextTick();//move the clock one tick foward
//				sickCounter=0;
//				for(int j = 0; j < map.getSettlements()[i].getPeople().size() ;j++)
//				{
//					if(map.getSettlements()[i].getPeople().get(j) instanceof Sick)
//					{
//						sickCounter++;
//					}
//				}
//				System.out.println("After simulation " + (p+1) +" Settlement  " + map.getSettlements()[i].getName() +" Sick number are : " + sickCounter );	
//				
//			}
//		}

		map.simulate6Days();
		
		
//				for(int i = 0 ;i < settleNumber ; i++) //check in the end of simulation how many sick people
//				{
//					sickCounter=0;
//					for(int j = 0; j < map.getSettlements()[i].getPeople().size() ;j++)
//					{
//						if(map.getSettlements()[i].getPeople().get(j) instanceof Sick)
//						{
//							sickCounter++;
//						}
//					}
//					System.out.println("Settlement  " + map.getSettlements()[i].getName() +" Sick number are : " + sickCounter);	
//				}   
		
		System.out.println("--------------------------");
		System.out.println("END OF SIMULATION RESULT");
		map.printSickPpl();
    
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
