//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package IO;
import java.io.*;
import Country.*;
import Country.Map;
import Location.*;
import Population.*;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.SouthAfricanVariant;

import java.util.*;

public class SimulationFile {
	
	private Map map; //map field include all settlement list

	public SimulationFile(File file) { //file parameter
		try (BufferedReader br = new BufferedReader( new FileReader(file) )) 
		{
		
				System.out.println(file.getName() + " ****************");
				int populationCapacity;
				List<Settlement> arr = new ArrayList<Settlement>();//Make an empty array of settlements
				List<String> origin = new ArrayList<String>();//Make an empty array of origin settlements
				List<String> destination = new ArrayList<String>();//Make an empty array of destination settlements to connect
				String s1 = br.readLine();
				while(s1 != null) // reading each line from file until we finish read all lines
				{
					
					Settlement settlement; //settlement reference (for all settle built )
					
					String check[] =s1.split(";");
					String words[] = null ;
					String words1[] = null;
					
					if(check[0].equals("#"))
					{
						words1 = s1.split(";");
	                	for (int i = 0 ; i < words1.length ;i++) //this loop for spaces removal
	                    	words1[i]=words1[i].trim();
	                	origin.add(words1[1]);
	                	destination.add(words1[2]);
	                	s1 = br.readLine();
	                	continue;
					}	
				  
						words = s1.split(";"); //split each line for indexes sapereted by ;
	                    for (int i = 0 ; i < words.length ;i++) //this loop for spaces removal
	                    	words[i]=words[i].trim();

					s1 = br.readLine(); //read next line
					
					List<Person> people = new ArrayList<Person>(); //new empty dynamic people list
					Point point = new Point(Integer.parseInt(words[2]),Integer.parseInt(words[3])); //build new point
					Size size = new Size(Integer.parseInt(words[4]),Integer.parseInt(words[5]));  //build new size object
					Location location = new Location(size,point);  //build new location object
					populationCapacity = (int)(Integer.parseInt(words[6]) * 1.3); //max population capacity
					
					//using factory for building settlements
					settlement = FactorySettlements.getSettlement(words[0],words[1],location,people,RamzorColor.Green,populationCapacity);
					arr.add(settlement);
					

					for(int i = 0 ; i < Integer.parseInt(words[6]) ; i ++) { //create and add all healthy people to the settle population
						
						settlement.addPerson(new Healthy(CalculateAge() , Settlement.randomLocation(point,size) ,settlement));
					}
	
				
				}
				
				this.map = new Map(arr); 
				connectSettle(origin ,destination,arr); //takes the list of connected settle and connect them
				
				System.out.println("EOF ****************");
			}
		catch(FileNotFoundException e) { 
			System.err.printf("File %s not found : %s%n", file.getName(), e.getMessage());
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
		
    public void connectSettle(List<String> origin ,List<String> destination , List<Settlement> arr) {
    	for(int i = 0 ; i <arr.size() ; i++)
    	{
    		for(int j = 0 ; j < origin.size(); j++)
    		{
    			if(arr.get(i).getName().equals(origin.get(j)))
    			{
    				for(int k = 0 ; k < arr.size() ; k++)
    				{
    						if(arr.get(k).getName().equals(destination.get(j)))
    						{
    							arr.get(i).appendconnectedSettlements(arr.get(k));
    							arr.get(k).appendconnectedSettlements(arr.get(i));
			
    						}
    				}
    				
    			}
    		}
    		
    	}
    	
    	SickPplInnit();
    	
    		
    }
	

	public Map getMap() {// return map field
		return map;
	}

	
	
	public int CalculateAge() { //function that calculate age by normal distribution
		double y =  Math.random()*4;  
        Random x = new Random();
        double val;
        do {
        	  val = x.nextGaussian();
        	
        	} while (val <= -1 || val>=1);
       
        
       return (int)((5*((val*6)+9))+y);

	}
	
	
	public void SickPplInnit() {
		// Cpuntagious innitialization
		int settleNumber = map.getSettlements().size();
		Settlement settle;
		int settleSize;
		Person temp;

		for (int i = 0; i < settleNumber; i++) { // run for each settle .
			settle = map.getSettlements().get(i);
			settleSize = settle.getNonSickPeople().size();

			for (int j = 0; j < (int) (settleSize * 0.01); j++) // make 1 percent of them as sick people .
			{ // the modulo allowed us to set the viruses random.
				if (j % 3 == 0) {
					temp =settle.getNonSickPeople().get(j).contagion(new SouthAfricanVariant());
					settle.addPerson(temp);
					temp =settle.getNonSickPeople().remove(j);
				} else if (j % 3 == 1) {
					temp =settle.getNonSickPeople().get(j).contagion(new ChineseVariant());
					settle.addPerson(temp);
					temp =settle.getNonSickPeople().remove(j);
				} else {
					temp =settle.getNonSickPeople().get(j).contagion(new BritishVariant());
					settle.addPerson(temp);
					temp =settle.getNonSickPeople().remove(j);
				}
			}
			settle.setColor(settle.calculateRamzorGrade()); // setting the ramzor
																								// grade and color

		}
	}
	
}






