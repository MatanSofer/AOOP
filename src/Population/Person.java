package Population;
import Location.*;
import Country.*;
import Virus.*;
public abstract class Person
{
	private int age;	
	private Point location;
	private Settlement settlement;
	 
	public abstract double contagionProbability();
	public abstract Person contagion(IVirus other);
		
	public Person(int age , Point location , Settlement settlement)
	{
		this.age=age;
		this.location = new Point(location);
		this.settlement = new Settlement(settlement);
	}
		   	
	public int getAge()
	{
		return age;
	}
	public Settlement getSettlement()
	{
		return settlement;
	}
	public Point getLocation()	
	{
		return location;
	}
	public String toString()
	{
		return "The age of the Person is"+age+"/"
		+location.toString()+
		 settlement.getName();
	}   
	   
}
