package IO;
import java.io.*;
import Country.*;
import Country.Map;
import Location.*;
import Population.*;
import java.util.*;

public class SimulationFile {

	private Map map; //map field include all settlement list

	public SimulationFile(File file)  //file parameter
	{
		try (BufferedReader br = new BufferedReader( new FileReader(file))) 
		{
				System.out.println(file.getName() + " ****************");


		
				
				List<Settlement> arr = new ArrayList<Settlement>();//Make an empty array of settlements
				String s1 = br.readLine();
				while(s1 != null) // reading each line from file until we finish read all lines
				{
					
					Settlement settlement; //settlement referens (for all settle built )
					String words[] = s1.split(";"); //split each line for indexes sapereted by ;
                    for (int i = 0 ; i < words.length ;i++) //this loop for spaces removal
                    	words[i]=words[i].trim();
					s1 = br.readLine(); //read next line
					
					List<Person> people = new ArrayList<Person>(); //new empty dynamic people list

					Point point = new Point(Integer.parseInt(words[2]),Integer.parseInt(words[3])); //build new point
					Size size = new Size(Integer.parseInt(words[4]),Integer.parseInt(words[5]));  //build new size object
					Location location = new Location(size,point);  //build new location object
					
					if(words[0].equals("City"))            //check what is the right settle type , then build it by all the other object and green light 
					{
						settlement = new City(words[1],location,people,RamzorColor.Green);
						arr.add(settlement);
					}
					else if(words[0].equals("Moshav"))
					{	
						settlement = new Moshav(words[1],location,people,RamzorColor.Green);
						arr.add(settlement);
					}
					else if(words[0].equals("Kibbutz"))
					{	
						settlement = new Kibbutz(words[1],location,people,RamzorColor.Green);
						arr.add(settlement);
					}
					else                 //this is for condition when we didnt fount a match for settle kind from file
					{
						
						settlement = new Settlement(words[1],location,people,RamzorColor.Green);
						arr.add(settlement);
						System.out.println("Couldnt read settlement - " + words[1]);
					}
					
					for(int i = 0 ; i < Integer.parseInt(words[6]) ; i ++) //create and add all healthy people to the settle population
					{
						
						settlement.addPerson(new Healthy(CalculateAge() , Settlement.randomLocation(point,size) ,settlement));
					}
					


				this.map = new Map(arr); 
				}
				System.out.println("EOF ****************");
			}
		catch(FileNotFoundException e) { 
			System.err.printf("File %s not found : %s%n", file.getName(), e.getMessage());
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
		

	

	public Map getMap() // return map field
	{
		return map;
	}

	
	
	public int CalculateAge() //function that calculate age by normal distribution
	{
		double y =  Math.random()*4;  
        Random x = new Random();
        double val;
        do {
        	  val = x.nextGaussian();
        	
        	} while (val <= -1 || val>=1);
       
        
       return (int)((5*((val*6)+9))+y);

	}
	
	
	
}






