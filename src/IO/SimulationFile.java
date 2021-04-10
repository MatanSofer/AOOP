package IO;

import java.io.*;
import Country.*;
import Location.*;
import Population.*;
import Virus.*;
import IO.*;
import java.util.*;

public class SimulationFile 
{

		SimulationFile(File file) {
		try (BufferedReader br = new BufferedReader( new FileReader(file))) {
				System.out.println(file.getName() + " ****************");
				
				
				//Make an empty array of settelmets****
				List<Settlement> arr = new ArrayList<Settlement>();
				
					
				//spaces removal	
				String s1 = br.readLine();
				while(s1 != null)
				{
					String words[] = s1.split(";");
					s1 = br.readLine();
					
					Healthy[] people = new Healthy[Integer.parseInt(words[6])];
					for(int i = 0 ; i < Integer.parseInt(words[6]) ; i ++)
					{
						
						people[i] = new Healthy(CalculateAge() , locations[i] , new Settlement())
					}
					
					Point point = new Point(Integer.parseInt(words[2]),Integer.parseInt(words[3]));
					Size size = new Size(Integer.parseInt(words[4]),Integer.parseInt(words[5]));
					Location location = new Location(size,point); //need to replace just for check
					if(words[0] == "City")
					{
						City city = new City(words[1],location,people,RamzorColor.Green);
						arr.add(city);
					}
					if(words[0] == "Moshav")
					{	
						Moshav moshav = new Moshav(words[1],location,people,RamzorColor.Green);
						arr.add(moshav);
					}
					if(words[0] == "Kibbutz")
					{	
						Kibbutz kibbutz = new Kibbutz(words[1],location,people,RamzorColor.Green);
						arr.add(kibbutz);
					}
					
				}
				//for loop for each line of text***
				
				//Read line of txt (each artibutes seperated by ;)***
				
				//Build an array of persons in the area of the settelment***
				
				//Build settelment with the given artibutes***
				
				//
					
					
				System.out.println("EOF ****************");
				}
		catch(FileNotFoundException e) {
			System.err.printf("File %s not found : %s%n", file.getName(), e.getMessage());
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
		

		public int CalculateAge()
		{
			double y =  Math.random()*4;  
			double x = 4;///made it because idk how to
			int res = (int)((5*x)+y);
			return res;
		}

}
